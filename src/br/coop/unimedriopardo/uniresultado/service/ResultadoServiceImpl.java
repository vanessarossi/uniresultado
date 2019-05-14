package br.coop.unimedriopardo.uniresultado.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import br.coop.unimedriopardo.uniresultado.connect.ConnectionWebService;
import br.coop.unimedriopardo.uniresultado.model.LogEnvio;
import br.coop.unimedriopardo.uniresultado.model.Resultado;
import br.coop.unimedriopardo.uniresultado.model.Usuario;
import br.coop.unimedriopardo.uniresultado.repository.RepositorioExame;
import br.coop.unimedriopardo.uniresultado.repository.RepositorioLogEnvio;
import br.coop.unimedriopardo.uniresultado.repository.RepositorioResultado;
import br.coop.unimedriopardo.uniresultado.util.Impressao;

@Service
@Transactional
public class ResultadoServiceImpl implements ResultadoService {
	
	private final RepositorioResultado repositorioResultado;
	private final RepositorioLogEnvio repositorioLogEnvio;

	@Autowired
	public ResultadoServiceImpl(RepositorioResultado repositorioResultado, RepositorioExame repositorioExame, RepositorioLogEnvio repositorioLogEnvio) {
		super();
		this.repositorioResultado = repositorioResultado;
		this.repositorioLogEnvio = repositorioLogEnvio;
	}

	@Override
	public Resultado salvar(Resultado resultado, Usuario usuario, MultipartFile anexo) {
		String anexoConvertido = null;
		
		try {
			anexoConvertido = Base64.encodeBase64String(anexo.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		resultado.setAnexo(anexoConvertido);
		resultado.setData(new Date());
		resultado.setPrestador(usuario.getPrestador()); 
		return repositorioResultado.save(resultado);
	}

	@Override
	public Resultado pesquisaPorId(Integer id) {
		return repositorioResultado.findOne(id);
	}

	@Override
	public void cancelar(Integer id) {
		Resultado resultado = repositorioResultado.findOne(id);
		resultado.setStatus("C");
		resultado.setDataCancelamento(new Date());
		repositorioResultado.save(resultado);
	}

	@Override
	public void enviarResultadosLimitados(Usuario usuarioLogado) {
		PageRequest pageRequest = new PageRequest(0, 20);
		Page<Resultado> resultados = repositorioResultado.findByPrestador_idAndStatus(usuarioLogado.getPrestador().getId(), "P", pageRequest);
		List<Resultado> listaResultado = resultados.getContent();
		ConnectionWebService webService = new ConnectionWebService();
		for (Resultado resultado : listaResultado) {
			LogEnvio logEnvio = null;
			try {
				logEnvio = webService.enviar(usuarioLogado, resultado);
				repositorioLogEnvio.save(logEnvio);
				if (logEnvio.getStatus() != "ER") {
					resultado.setStatus("E");
				}else if(logEnvio.getStatus() == "ER") {
					resultado.setStatus("ER");
				}
				repositorioResultado.save(resultado);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void enviarExamesPendente(Usuario usuarioLogado) {
		List<Resultado> resultadosPendente = repositorioResultado.findByPrestador_idAndStatus(usuarioLogado.getPrestador().getId(), "P");
		ConnectionWebService webService = new ConnectionWebService();
		for (Resultado resultado : resultadosPendente) {
			
			LogEnvio logEnvio = null;
			try {
				logEnvio = webService.enviar(usuarioLogado, resultado);
				repositorioLogEnvio.save(logEnvio);
				
				if (logEnvio.getStatus() != "ER") {
					resultado.setStatus("E");
				}else if(logEnvio.getStatus() == "ER") {
					resultado.setStatus("ER");
				}
				repositorioResultado.save(resultado);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void converterResultadoEmPDF(Integer id) {
		Resultado resultado = repositorioResultado.findOne(id);
		FileOutputStream fileOutputStream = null;
		File pasta = new Impressao().criarPasta();
		File file = new File(pasta,id.toString()+".pdf");
		try {
			fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(Base64.decodeBase64(resultado.getAnexo()));
			fileOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String retornaCaminho() {
		return new Impressao().caminho();
	}
	
	@Override
	public void validarResultados(Usuario usuarioLogado) {
		List<Resultado> resultadosImportado = repositorioResultado.findByPrestador_idAndStatus(usuarioLogado.getPrestador().getId(),"I");
		for (Resultado resultado : resultadosImportado) {
			if (resultado.getExames().size() > 0 ) {
				resultado.setStatus("P");
			}else {
				resultado.setStatus("EV");
			}
			repositorioResultado.save(resultado);
		}
	}

	@Override
	public Page<Resultado> listarImportadosPorPrestador(Usuario usuarioLogado, Pageable pageable) {
		return repositorioResultado.findByPrestador_idAndStatus(usuarioLogado.getPrestador().getId(),"I", pageable);
	}
	
	@Override
	public Page<Resultado> listarPorPrestador(Usuario usuarioLogado, Pageable pageable) {
		return repositorioResultado.findByPrestador_idOrderByIdDesc(usuarioLogado.getPrestador().getId(), pageable);
	}

	@Override
	public Page<Resultado> listarPendenteEnvioPorPrestador(Usuario usuarioLogado, Pageable pageable) {
		return repositorioResultado.findByPrestador_idAndStatus(usuarioLogado.getPrestador().getId(),"P", pageable);
	}

	@Override
	public Page<Resultado> listarErroValidacaoPorPrestador(Usuario usuarioLogado, Pageable pageable) {
		return repositorioResultado.findByPrestador_idAndStatus(usuarioLogado.getPrestador().getId(), "EV", pageable);
	}

	@Override
	public Page<Resultado> listarPorPrestadorEStatus(Usuario usuarioLogado, String status, Pageable pageable) {
		return repositorioResultado.findByPrestador_idAndStatus(usuarioLogado.getPrestador().getId(),status, pageable);
	}

	@Override
	public void importarExamesErroImportacao(Usuario usuarioLogado) {
		// repositorioResultado.importarErroValidacao(usuarioLogado.getPrestador().getId());
	}
}

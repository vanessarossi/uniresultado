package br.coop.unimedriopardo.uniresultado.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import br.coop.unimedriopardo.uniresultado.connect.ConnectionWebService;
import br.coop.unimedriopardo.uniresultado.models.Exame;
import br.coop.unimedriopardo.uniresultado.models.LogEnvio;
import br.coop.unimedriopardo.uniresultado.models.Resultado;
import br.coop.unimedriopardo.uniresultado.models.Usuario;
import br.coop.unimedriopardo.uniresultado.repositories.RepositorioExame;
import br.coop.unimedriopardo.uniresultado.repositories.RepositorioLogEnvio;
import br.coop.unimedriopardo.uniresultado.repositories.RepositorioResultado;
import br.coop.unimedriopardo.uniresultado.util.Impressao;

@Service
@Transactional
public class ResultadoServiceImpl implements ResultadoService {
	
	private final RepositorioResultado repositorioResultado;
	private final RepositorioExame repositorioExame;

	private final RepositorioLogEnvio repositorioLogEnvio;

	@Autowired
	public ResultadoServiceImpl(RepositorioResultado repositorioResultado, RepositorioExame repositorioExame, RepositorioLogEnvio repositorioLogEnvio) {
		super();
		this.repositorioResultado = repositorioResultado;
		this.repositorioExame = repositorioExame;
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
		List<Exame> exames = resultado.getExames();
		Resultado resultadoSalvo = repositorioResultado.save(resultado);
		
		for (Exame exame : exames) {
			exame.setResultado(resultadoSalvo);
			repositorioExame.save(exame);
		}
		return resultado;
	}

	@Override
	public Resultado pesquisaPorId(Integer id) {
		return repositorioResultado.findOne(id);
	}

	@Override
	public List<Resultado> listarPendentePorPrestador(Usuario usuarioLogado) {
		return repositorioResultado.findByPrestador_idAndStatus(usuarioLogado.getPrestador().getId(),"P");
	}

	@Override
	public void cancelar(Integer id) {
		Resultado resultado = repositorioResultado.findOne(id);
		if(resultado.getNrExecucaoOperadora().equals("")) {
			resultado.setNrExecucaoOperadora("nao informado");
		}if(resultado.getNrCartaoBeneficiario().equals("")) {
			resultado.setNrCartaoBeneficiario("nao informado");
		}
		resultado.setStatus("C");
		resultado.setDataCancelamento(new Date());
		repositorioResultado.save(resultado);
	}

	@Override
	public void enviarExamesSelecionados(List<Resultado> resultados) {
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
					resultado.setStatus("P");
				}
				repositorioResultado.save(resultado);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

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
	public List<Resultado> listarPorPrestador(Usuario usuarioLogado) {
		return repositorioResultado.findByPrestador_idOrderByIdDesc(usuarioLogado.getPrestador().getId());
	}
}

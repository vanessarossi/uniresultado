package br.coop.unimedriopardo.uniresultado.services;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import br.coop.unimedriopardo.uniresultado.connect.ConnectionWebService;
import br.coop.unimedriopardo.uniresultado.models.Exame;
import br.coop.unimedriopardo.uniresultado.models.Resultado;
import br.coop.unimedriopardo.uniresultado.models.Usuario;
import br.coop.unimedriopardo.uniresultado.repositories.RepositorioExame;
import br.coop.unimedriopardo.uniresultado.repositories.RepositorioPrestador;
import br.coop.unimedriopardo.uniresultado.repositories.RepositorioResultado;

@Service
@Transactional
public class ResultadoServiceImpl implements ResultadoService {
	
	private final RepositorioResultado repositorioResultado;
	private final RepositorioExame repositorioExame;
	private final RepositorioPrestador repositorioPrestador;

	@Autowired
	public ResultadoServiceImpl(RepositorioResultado repositorioResultado, RepositorioExame repositorioExame, RepositorioPrestador repositorioPrestador) {
		super();
		this.repositorioResultado = repositorioResultado;
		this.repositorioExame = repositorioExame;
		this.repositorioPrestador = repositorioPrestador;
	}

	@Override
	public Resultado salvar(Resultado resultado, MultipartFile anexo) {
		String anexoConvertido = null;
		
		try {
			anexoConvertido = Base64.encodeBase64String(anexo.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		resultado.setAnexo(anexoConvertido);
		resultado.setData(new Date());
		resultado.setPrestador(repositorioPrestador.findOne(1)); 
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
	public List<Resultado> listarPendentePorPrestador() {
		// prestador ainda com gambiarra apenas para teste
		return repositorioResultado.findByPrestador_idAndStatus(2,"P");
	}

	@Override
	public void cancelar(Integer id) {
		Resultado resultado = repositorioResultado.findOne(id);
		resultado.setStatus("C");
		resultado.setDataCancelamento(new Date());
		repositorioResultado.save(resultado);
	}

	@Override
	public void enviarExamesSelecionados(List<Resultado> resultados) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enviarExamesPendente() {
		List<Resultado> resultadosPendente = repositorioResultado.findByPrestador_idAndStatus(2, "P");
		ConnectionWebService webService = new ConnectionWebService();
		
		for (Resultado resultado : resultadosPendente) {
		
				try {
					webService.enviar(new Usuario(),resultado);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
		
		
	}
}

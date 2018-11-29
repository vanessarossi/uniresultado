package br.coop.unimedriopardo.uniresultado.services;

import java.io.IOException;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import br.coop.unimedriopardo.uniresultado.models.Exame;
import br.coop.unimedriopardo.uniresultado.models.Resultado;
import br.coop.unimedriopardo.uniresultado.repositories.RepositorioExame;
import br.coop.unimedriopardo.uniresultado.repositories.RepositorioResultado;

@Service
@Transactional
public class ResultadoServiceImpl implements ResultadoService {
	
	private final RepositorioResultado repositorioResultado;
	private final RepositorioExame repositorioExame;

	@Autowired
	public ResultadoServiceImpl(RepositorioResultado repositorioResultado, RepositorioExame repositorioExame) {
		super();
		this.repositorioResultado = repositorioResultado;
		this.repositorioExame = repositorioExame;
	}

	@Override
	public Resultado salvar(Resultado resultado, MultipartFile anexo) {
		byte[] anexoConvertido = null;
		try {
			anexoConvertido = Base64.decodeBase64(anexo.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		resultado.setAnexo(anexoConvertido);
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
	public void deletar(Integer id) {
		repositorioResultado.delete(id);
	}
}

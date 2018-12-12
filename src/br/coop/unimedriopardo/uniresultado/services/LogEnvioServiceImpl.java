package br.coop.unimedriopardo.uniresultado.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import br.coop.unimedriopardo.uniresultado.models.LogEnvio;
import br.coop.unimedriopardo.uniresultado.models.Usuario;
import br.coop.unimedriopardo.uniresultado.repositories.RepositorioLogEnvio;

public class LogEnvioServiceImpl implements LogEnvioService {


	private final RepositorioLogEnvio repositorioLogEnvio;
	
	@Autowired
	public LogEnvioServiceImpl(RepositorioLogEnvio repositorioLogEnvio) {
		super();
		this.repositorioLogEnvio = repositorioLogEnvio;
	}
	
	@Override
	public List<LogEnvio> listagemOrdenadaDoPrestador(Usuario usuarioLogado) {
		// TODO Auto-generated method stub
		return null;
	}

}

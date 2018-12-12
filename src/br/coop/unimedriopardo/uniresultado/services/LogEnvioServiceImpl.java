package br.coop.unimedriopardo.uniresultado.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.coop.unimedriopardo.uniresultado.models.LogEnvio;
import br.coop.unimedriopardo.uniresultado.models.Usuario;
import br.coop.unimedriopardo.uniresultado.repositories.RepositorioLogEnvio;

@Service
@Transactional
public class LogEnvioServiceImpl implements LogEnvioService {


	private final RepositorioLogEnvio repositorioLogEnvio;
	
	@Autowired
	public LogEnvioServiceImpl(RepositorioLogEnvio repositorioLogEnvio) {
		super();
		this.repositorioLogEnvio = repositorioLogEnvio;
	}
	
	@Override
	public List<LogEnvio> listagemOrdenadaDoPrestador(Usuario usuarioLogado) {
		return repositorioLogEnvio.findByPrestador_idOrderByIdDesc(usuarioLogado.getPrestador().getId());
	}

}

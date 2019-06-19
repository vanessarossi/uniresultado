package br.coop.unimedriopardo.uniresultado.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.coop.unimedriopardo.uniresultado.model.LogEnvio;
import br.coop.unimedriopardo.uniresultado.model.Usuario;
import br.coop.unimedriopardo.uniresultado.repository.RepositorioLogEnvio;

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
	public Page<LogEnvio> listagemOrdenadaDoPrestadorPorStatus(Usuario usuarioLogado, String status, String data, Pageable pageable) {
		Page<LogEnvio> logEnvios = repositorioLogEnvio.findByPrestador_idAndStatusAndDataEnvioOrderByIdDesc(usuarioLogado.getPrestador().getId(), status ,data, pageable);
		return logEnvios;
	}
	
	@Override
	public Page<LogEnvio> listagemOrdenadaDoPrestador(Usuario usuarioLogado, String data, Pageable pageable) {
		Page<LogEnvio> logEnvios = repositorioLogEnvio.findByPrestador_idAndDataEnvioOrderByIdDesc(usuarioLogado.getPrestador().getId(),data, pageable);
		return logEnvios;
	}

	@Override
	public LogEnvio pequisarPorId(Integer id) {
		return repositorioLogEnvio.findById(id).orElse(new LogEnvio());
	}

}
package br.coop.unimedriopardo.uniresultado.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;

import br.coop.unimedriopardo.uniresultado.model.LogEnvio;
import br.coop.unimedriopardo.uniresultado.model.Usuario;

public interface LogEnvioService {
	
	public List<LogEnvio> listagemOrdenadaDoPrestador(Usuario usuarioLogado,Date data, Pageable  pageable);

}

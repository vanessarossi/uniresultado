package br.coop.unimedriopardo.uniresultado.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import br.coop.unimedriopardo.uniresultado.model.LogEnvio;
import br.coop.unimedriopardo.uniresultado.model.Usuario;

public interface LogEnvioService {
	
	public Page<LogEnvio> listagemOrdenadaDoPrestador(Usuario usuarioLogado,String data, Pageable  pageable);

}

package br.coop.unimedriopardo.uniresultado.services;

import java.util.List;

import br.coop.unimedriopardo.uniresultado.models.LogEnvio;
import br.coop.unimedriopardo.uniresultado.models.Usuario;

public interface LogEnvioService {
	
	public List<LogEnvio> listagemOrdenadaDoPrestador(Usuario usuarioLogado);

}

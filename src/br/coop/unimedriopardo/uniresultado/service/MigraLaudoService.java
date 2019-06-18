package br.coop.unimedriopardo.uniresultado.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.coop.unimedriopardo.uniresultado.model.MigraLaudo;
import br.coop.unimedriopardo.uniresultado.model.Usuario;

public interface MigraLaudoService {

	public Page<MigraLaudo> listarPorPrestador(Usuario usuarioLogado, Pageable pageable);
	
	public void converterMigra(Usuario usuarioLogado);
	public void excluirMigra(Integer id);
}

package br.coop.unimedriopardo.uniresultado.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.coop.unimedriopardo.uniresultado.model.Usuario;

public interface UsuarioService {
	
	public Usuario salvar(Usuario usuario);
	public Page<Usuario> listagemOrdenada(Pageable pageable);
	public Usuario pesquisaPorId(Integer id);
	public void deletar(Integer id);
	public Usuario pesquisaPorLogin(String login);

}

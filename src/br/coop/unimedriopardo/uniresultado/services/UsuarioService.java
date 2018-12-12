package br.coop.unimedriopardo.uniresultado.services;

import java.util.List;

import br.coop.unimedriopardo.uniresultado.models.Usuario;

public interface UsuarioService {
	
	public Usuario salvar(Usuario usuario);
	public List<Usuario> listagemOrdenada();
	public Usuario pesquisaPorId(Integer id);
	public void deletar(Integer id);
	public Usuario pesquisaPorLogin(String login);

}

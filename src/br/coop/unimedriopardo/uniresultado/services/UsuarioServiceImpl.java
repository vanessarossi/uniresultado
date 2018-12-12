package br.coop.unimedriopardo.uniresultado.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.coop.unimedriopardo.uniresultado.models.Usuario;
import br.coop.unimedriopardo.uniresultado.repositories.RepositorioUsuario;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService{

	private final RepositorioUsuario repositorioUsuario;
	
	@Autowired
	public UsuarioServiceImpl(RepositorioUsuario repositorioUsuario) {
		super();
		this.repositorioUsuario = repositorioUsuario;
	}
	
	@Override
	public Usuario salvar(Usuario usuario) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));	
		return repositorioUsuario.save(usuario);
	}

	@Override
	public List<Usuario> listagemOrdenada() {
		return repositorioUsuario.findAll(new Sort("nome"));
	}

	@Override
	public Usuario pesquisaPorId(Integer id) {
		return repositorioUsuario.findOne(id);
	}

	@Override
	public void deletar(Integer id) {
		repositorioUsuario.delete(id);
	}

	@Override
	public Usuario pesquisaPorLogin(String login) {
		return repositorioUsuario.findByLogin(login);
	}

	
}

package br.coop.unimedriopardo.uniresultado.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.coop.unimedriopardo.uniresultado.model.Usuario;
import br.coop.unimedriopardo.uniresultado.repository.RepositorioUsuario;

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
	public Page<Usuario> listagemOrdenada(Pageable pageable) {
		Page<Usuario> usuarios = repositorioUsuario.findAll(pageable);
		return usuarios;
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

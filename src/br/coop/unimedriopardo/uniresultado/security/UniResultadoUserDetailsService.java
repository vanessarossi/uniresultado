package br.coop.unimedriopardo.uniresultado.security;

import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.coop.unimedriopardo.uniresultado.models.Usuario;
import br.coop.unimedriopardo.uniresultado.repositories.RepositorioUsuario;

public class UniResultadoUserDetailsService implements UserDetailsService{
	

	@Autowired
	private RepositorioUsuario repositorioUsuario;
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Usuario usuario = repositorioUsuario.findByLogin(login);
		if (usuario == null) {
			throw new UsernameNotFoundException("Usu�rio n�o encontrado");
		}
		Set<GrantedAuthority> perfis = new HashSet<GrantedAuthority>();
		perfis.add(new SimpleGrantedAuthority(usuario.getPerfil()));
		return new User(usuario.getLogin(), usuario.getSenha(), perfis);
	}
}

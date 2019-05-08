package br.coop.unimedriopardo.uniresultado.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.coop.unimedriopardo.uniresultado.model.Usuario;

public interface RepositorioUsuario extends JpaRepository<Usuario, Integer> {

	public Usuario findByLogin(String login);
}

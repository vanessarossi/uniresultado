package br.coop.unimedriopardo.uniresultado.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import br.coop.unimedriopardo.uniresultado.models.Usuario;

public interface RepositorioUsuario extends JpaRepository<Usuario, Integer> {

}

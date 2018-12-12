package br.coop.unimedriopardo.uniresultado.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import br.coop.unimedriopardo.uniresultado.models.LogEnvio;
import br.coop.unimedriopardo.uniresultado.models.Usuario;

public interface RepositorioLogEnvio extends JpaRepository<LogEnvio, Integer> {

	public List<LogEnvio> findByPrestador_id(Integer id);
}

package br.coop.unimedriopardo.uniresultado.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import br.coop.unimedriopardo.uniresultado.models.LogEnvio;


public interface RepositorioLogEnvio extends JpaRepository<LogEnvio, Integer> {

	public List<LogEnvio> findByPrestador_idOrderByIdDesc(Integer id);
}

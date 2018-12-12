package br.coop.unimedriopardo.uniresultado.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import br.coop.unimedriopardo.uniresultado.models.LogEnvio;

public interface RepositorioLogEnvio extends JpaRepository<LogEnvio, Integer> {

}

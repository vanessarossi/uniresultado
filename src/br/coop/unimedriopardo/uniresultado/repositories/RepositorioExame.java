package br.coop.unimedriopardo.uniresultado.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import br.coop.unimedriopardo.uniresultado.models.Exame;


public interface RepositorioExame extends JpaRepository<Exame, Integer> {

}

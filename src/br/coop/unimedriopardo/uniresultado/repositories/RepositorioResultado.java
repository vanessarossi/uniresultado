package br.coop.unimedriopardo.uniresultado.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.coop.unimedriopardo.uniresultado.models.Resultado;

public interface RepositorioResultado extends JpaRepository<Resultado, Integer> {

}

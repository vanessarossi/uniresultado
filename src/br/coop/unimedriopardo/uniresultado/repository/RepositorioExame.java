package br.coop.unimedriopardo.uniresultado.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.coop.unimedriopardo.uniresultado.model.Exame;


public interface RepositorioExame extends JpaRepository<Exame, Integer> {

}

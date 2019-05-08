package br.coop.unimedriopardo.uniresultado.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.coop.unimedriopardo.uniresultado.model.LogEnvio;


public interface RepositorioLogEnvio extends JpaRepository<LogEnvio, Integer> {

	public List<LogEnvio> findByPrestador_idAndDataOrderByIdDesc(Integer id, Date data, Pageable pageable);
}

package br.coop.unimedriopardo.uniresultado.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import br.coop.unimedriopardo.uniresultado.model.LogEnvio;


public interface RepositorioLogEnvio extends JpaRepository<LogEnvio, Integer> {

	public Page<LogEnvio> findByPrestador_idAndStatusAndDataEnvioOrderByIdDesc(Integer id, String status, String data, Pageable pageable);
	public Page<LogEnvio> findByPrestador_idAndDataEnvioOrderByIdDesc(Integer id,String data, Pageable pageable);
}
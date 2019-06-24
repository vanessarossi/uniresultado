package br.coop.unimedriopardo.uniresultado.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import br.coop.unimedriopardo.uniresultado.model.MigraLaudo;

public interface RepositorioMigraLaudo extends JpaRepository<MigraLaudo, Integer>{
	
	public Page<MigraLaudo> findByPrestador_idAndStatus(Integer prestadorId, String status, Pageable pageable);
	public List<MigraLaudo> findByPrestador_idAndStatus(Integer prestadorId, String status);
	
}

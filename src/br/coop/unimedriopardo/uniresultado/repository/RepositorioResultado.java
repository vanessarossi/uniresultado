package br.coop.unimedriopardo.uniresultado.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.coop.unimedriopardo.uniresultado.model.Resultado;

public interface RepositorioResultado extends JpaRepository<Resultado, Integer> {
	
	public List<Resultado> findByPrestador_idAndStatus(Integer id, String status);
	public Page<Resultado> findByPrestador_idAndStatus(Integer id, String status, Pageable pageable);
	public Page<Resultado> findByPrestador_idOrderByIdDesc(Integer id, Pageable pageable);
}



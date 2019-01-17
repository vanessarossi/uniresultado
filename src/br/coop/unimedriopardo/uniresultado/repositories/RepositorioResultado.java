package br.coop.unimedriopardo.uniresultado.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import br.coop.unimedriopardo.uniresultado.models.Resultado;

public interface RepositorioResultado extends JpaRepository<Resultado, Integer> {
	
	public List<Resultado> findByPrestador_idAndStatus(Integer id, String status);
	public List<Resultado> findByPrestador_idOrderByIdDesc(Integer id);
}



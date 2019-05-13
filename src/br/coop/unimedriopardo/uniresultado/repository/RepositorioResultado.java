package br.coop.unimedriopardo.uniresultado.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.coop.unimedriopardo.uniresultado.model.Resultado;

public interface RepositorioResultado extends JpaRepository<Resultado, Integer> {
	
	public List<Resultado> findByPrestador_idAndStatus(Integer id, String status);
	public Page<Resultado> findByPrestador_idAndStatus(Integer id, String status, Pageable pageable);
	public Page<Resultado> findByPrestador_idOrderByIdDesc(Integer id, Pageable pageable);
	
	@Query(nativeQuery = true, value = "SELECT FN_VOLTAR_IMPORTACAO_EXAMES(:prestador_id) FROM DUAL")
    public String importarErroValidacao(@Param("prestador_id")Integer prestador_id);
	
	@Query(nativeQuery = true, value = "SELECT FN_VALIDAR_EXAMES(:prestador_id) FROM DUAL")
    public String validarExames(@Param("prestador_id")Integer prestador_id);
}



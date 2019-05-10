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
	
	@Query(value = "{EXECUTE PROC_LIBERAR_ERRO_VALIDACAO(:prestador_id)}", nativeQuery = true)
    public void liberarErroValidacao(@Param("prestador_id")Integer prestador_id);
	
	@Query(value = "{EXECUTE PROC_VALIDAR_EXAME_PRESTADOR(:prestador_id)}", nativeQuery = true)
    public void validarExames(@Param("prestador_id")Integer prestador_id);
}



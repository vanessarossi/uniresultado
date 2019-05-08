package br.coop.unimedriopardo.uniresultado.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.coop.unimedriopardo.uniresultado.model.Prestador;

public interface PrestadorService {
	
	public Prestador salvar(Prestador prestador);
	public List<Prestador> listagemOrdenada();
	public Page<Prestador> listagemOrdenada(Pageable pageable);
	public Prestador pesquisaPorId(Integer id);
	public void deletar(Integer id);
}

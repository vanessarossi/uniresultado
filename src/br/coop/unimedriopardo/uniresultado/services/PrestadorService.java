package br.coop.unimedriopardo.uniresultado.services;

import java.util.List;

import br.coop.unimedriopardo.uniresultado.models.Prestador;

public interface PrestadorService {
	
	public Prestador salvar(Prestador prestador);
	public List<Prestador> listagemOrdenada();
	public Prestador pesquisaPorId(Integer id);
	public void deletar(Integer id);
}

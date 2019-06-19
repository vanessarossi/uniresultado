package br.coop.unimedriopardo.uniresultado.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.coop.unimedriopardo.uniresultado.model.Prestador;
import br.coop.unimedriopardo.uniresultado.repository.RepositorioPrestador;


@Service
@Transactional
public class PrestadorServiceImpl implements PrestadorService {
	
	private final RepositorioPrestador repositorioPrestador;
	
	@Autowired
	public PrestadorServiceImpl(RepositorioPrestador repositorioPrestador) {
		super();
		this.repositorioPrestador = repositorioPrestador;
	}

	@Override
	public Prestador salvar(Prestador prestador) {
		return repositorioPrestador.save(prestador);
	}

	@Override
	public List<Prestador> listagemOrdenada() {
		return repositorioPrestador.findAll(new Sort("nome"));
	}

	@Override
	public Prestador pesquisaPorId(Integer id) {
		return repositorioPrestador.findById(id).orElse(new Prestador());
	}

	@Override
	public void deletar(Integer id) {
		repositorioPrestador.deleteById(id);
	}

	@Override
	public Page<Prestador> listagemOrdenada(Pageable pageable) {
		Page<Prestador> prestadores = repositorioPrestador.findAll(pageable);
		return prestadores;
	}
}

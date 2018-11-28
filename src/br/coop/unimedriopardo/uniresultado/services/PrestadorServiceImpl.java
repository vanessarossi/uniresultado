package br.coop.unimedriopardo.uniresultado.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.coop.unimedriopardo.uniresultado.models.Prestador;
import br.coop.unimedriopardo.uniresultado.repositories.RepositorioPrestador;


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
		return repositorioPrestador.findOne(id);
	}

	@Override
	public void deletar(Integer id) {
		repositorioPrestador.delete(id);
	}
}

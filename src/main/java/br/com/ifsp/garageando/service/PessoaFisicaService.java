package br.com.ifsp.garageando.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifsp.garageando.model.PessoaFisica;
import br.com.ifsp.garageando.repository.PessoaFisicaRepository;

/**
 * 28 de nov de 2019
 *
 * @author gregory.feijon
 */

@Service
public class PessoaFisicaService implements IService<PessoaFisica> {

	@Autowired
	private PessoaFisicaRepository pessoaFisicaRepository;

	@Override
	public List<PessoaFisica> findAll() {
		return pessoaFisicaRepository.findAll();
	}

	@Override
	public Optional<PessoaFisica> findById(Long id) {
		return pessoaFisicaRepository.findById(id);
	}

	@Override
	public Optional<PessoaFisica> save(PessoaFisica pessoaFisica) {
		return Optional.ofNullable(pessoaFisicaRepository.save(pessoaFisica));
	}

	@Override
	public void delete(PessoaFisica pessoaFisica) {
		pessoaFisicaRepository.delete(pessoaFisica);
	}

	@Override
	public void deleteById(Long id) {
		pessoaFisicaRepository.deleteById(id);
	}

	@Override
	public boolean existsById(Long id) {
		return pessoaFisicaRepository.existsById(id);
	}
}

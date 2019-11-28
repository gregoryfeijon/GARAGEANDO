package br.com.ifsp.garageando.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifsp.garageando.model.PessoaJuridica;
import br.com.ifsp.garageando.repository.PessoaJuridicaRepository;

/**
 * 28 de nov de 2019
 *
 * @author gregory.feijon
 */

@Service
public class PessoaJuridicaService implements IService<PessoaJuridica> {

	@Autowired
	private PessoaJuridicaRepository pessoaJuridicaRepository;

	@Override
	public List<PessoaJuridica> findAll() {
		return pessoaJuridicaRepository.findAll();
	}

	@Override
	public Optional<PessoaJuridica> findById(Long id) {
		return pessoaJuridicaRepository.findById(id);
	}

	@Override
	public Optional<PessoaJuridica> save(PessoaJuridica pessoaJuridica) {
		return Optional.ofNullable(pessoaJuridicaRepository.save(pessoaJuridica));
	}

	@Override
	public void delete(PessoaJuridica pessoaJuridica) {
		pessoaJuridicaRepository.delete(pessoaJuridica);
	}

	@Override
	public void deleteById(Long id) {
		pessoaJuridicaRepository.deleteById(id);
	}

	@Override
	public boolean existsById(Long id) {
		return pessoaJuridicaRepository.existsById(id);
	}

}

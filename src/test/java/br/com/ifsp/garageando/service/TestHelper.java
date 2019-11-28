package br.com.ifsp.garageando.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ifsp.garageando.repository.AvaliacaoRepository;
import br.com.ifsp.garageando.repository.EnderecoRepository;
import br.com.ifsp.garageando.repository.EventoRepository;
import br.com.ifsp.garageando.repository.FaixaHorarioDisponivelRepository;
import br.com.ifsp.garageando.repository.LocalRepository;
import br.com.ifsp.garageando.repository.UsuarioRepository;

/**
 * 27 de nov de 2019
 *
 * @author gregory.feijon
 */

@Component
public class TestHelper {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private LocalRepository localRepository;

	@Autowired
	private FaixaHorarioDisponivelRepository faixaHorarioRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private EventoRepository eventoRepository;

	@Autowired
	private AvaliacaoRepository avaliacaoRepository;

	public void limpaBanco() {
		usuarioRepository.deleteAll();
		localRepository.deleteAll();
		faixaHorarioRepository.deleteAll();
		enderecoRepository.deleteAll();
		eventoRepository.deleteAll();
		avaliacaoRepository.deleteAll();
	}
}

package com.startersapi.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.startersapi.model.Desafio;
import com.startersapi.model.Starter;
import com.startersapi.model.SubmissaoDesafios;
import com.startersapi.repository.DesafioRepository;
import com.startersapi.repository.StarterRepository;
import com.startersapi.repository.SubmissaoDesafiosRepository;
import com.startersapi.service.exception.StarterOuDesafioInexistenteException;

@Service
public class SubmissaoDesafiosService {
	@Autowired
	private SubmissaoDesafiosRepository repository;
	
	@Autowired
	private StarterRepository starterRepository;
	
	@Autowired
	private DesafioRepository desafioRepository;
	
	public SubmissaoDesafios salvar(SubmissaoDesafios submissao) {
		Optional<Starter> starter = starterRepository.findById(submissao.getStarter().getCodigo());
		Optional<Desafio> desafio = desafioRepository.findById(submissao.getDesafio().getCodigo());
		
		if(starter.isEmpty() || desafio.isEmpty()) {
			throw new StarterOuDesafioInexistenteException();
		}
		
		return repository.save(submissao);
	}
	
	
	public SubmissaoDesafios buscarSubmissaoPeloCodigo(Long codigo) {
		Optional<SubmissaoDesafios> submissaoSalva = repository.findById(codigo);
		
		if(submissaoSalva.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		
		SubmissaoDesafios submissaoNova = submissaoSalva.get();
		return submissaoNova;
	}
	
	public SubmissaoDesafios atualizar(Long codigo, SubmissaoDesafios submissao) {
		Optional<SubmissaoDesafios> submissaoSalva = repository.findById(codigo);
		
		if(submissaoSalva.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		
		SubmissaoDesafios submissaoNova = submissaoSalva.get();
		BeanUtils.copyProperties(submissao, submissaoNova, "codigo");
		return repository.save(submissaoNova);
	}
}

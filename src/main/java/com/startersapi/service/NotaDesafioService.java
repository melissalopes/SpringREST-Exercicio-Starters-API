package com.startersapi.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.startersapi.model.NotaDesafio;
import com.startersapi.model.SubmissaoDesafios;
import com.startersapi.repository.NotaDesafioRepository;
import com.startersapi.repository.SubmissaoDesafiosRepository;
import com.startersapi.service.exception.SubmissaoInexistenteException;

@Service
public class NotaDesafioService {
	
	@Autowired
	private NotaDesafioRepository repository;
	
	@Autowired
	private SubmissaoDesafiosRepository submissaoRepository;
	
	//FALTA FAZER O TRATAMENTO DO ERRO
	public NotaDesafio salvar(NotaDesafio nota) {
		Optional<SubmissaoDesafios> submissao = submissaoRepository.findById(nota.getSubmissaoDesafios().getCodigo());
		
		if(submissao.isEmpty()) {
			throw new SubmissaoInexistenteException();
		}
		
		return repository.save(nota);
	}
	
	
	public NotaDesafio buscarNotaDesafioPeloCodigo(Long codigo) {
		Optional<NotaDesafio> nota = repository.findById(codigo);
		
		if(nota.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		
		NotaDesafio notaSalva = nota.get();
		return notaSalva;
	}
	
	public NotaDesafio atualizar(Long codigo, NotaDesafio nota) {
		Optional<NotaDesafio> notaSalva = repository.findById(codigo);
		
		if(notaSalva.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		
		NotaDesafio notaNova = notaSalva.get();
		BeanUtils.copyProperties(nota, notaNova, "codigo");
		return repository.save(notaNova);
	}
	
}

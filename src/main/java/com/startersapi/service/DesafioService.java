package com.startersapi.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.startersapi.model.Desafio;
import com.startersapi.repository.DesafioRepository;

@Service
public class DesafioService {
	@Autowired
	private DesafioRepository repository;
	
	public Desafio buscarDesafioPeloCodigo(Long codigo) {
		Optional<Desafio> desafioSalvo = repository.findById(codigo);
		
		if(desafioSalvo.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		
		Desafio desafioNovo = desafioSalvo.get();
		return desafioNovo;
	}
	
	public Desafio atualizar(Long codigo, Desafio desafio) {
		Optional<Desafio> desafioSalvo = repository.findById(codigo);
		
		if(desafioSalvo.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		
		Desafio desafioNovo = desafioSalvo.get();
		BeanUtils.copyProperties(desafio, desafioNovo, "codigo");
		return repository.save(desafioNovo);
	}
	
}

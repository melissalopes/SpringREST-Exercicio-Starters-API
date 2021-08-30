package com.startersapi.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.startersapi.model.Starter;
import com.startersapi.repository.StarterRepository;


@Service
public class StarterService {
	@Autowired
	private StarterRepository repository;
	
	public Starter buscarStarterPeloCodigo(Long codigo) {
		Optional<Starter> starterSalvo = repository.findById(codigo);
		
		if(starterSalvo.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		
		Starter starterNovo = starterSalvo.get();
		return starterNovo;
	}
	
	public Starter atualizar(Long codigo, Starter starter) {
		Optional<Starter> starterSalvo = repository.findById(codigo);
		
		if(starterSalvo.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		
		Starter starterNovo = starterSalvo.get();
		BeanUtils.copyProperties(starter, starterNovo, "codigo");
		return repository.save(starterNovo);
	}

}

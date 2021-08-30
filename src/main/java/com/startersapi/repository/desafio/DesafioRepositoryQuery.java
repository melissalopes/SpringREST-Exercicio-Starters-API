package com.startersapi.repository.desafio;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.startersapi.model.Desafio;

public interface DesafioRepositoryQuery {
	public Page<Desafio> listar(Pageable pageable);
}

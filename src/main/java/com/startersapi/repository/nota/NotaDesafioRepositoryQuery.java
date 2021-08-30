package com.startersapi.repository.nota;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.startersapi.model.NotaDesafio;

public interface NotaDesafioRepositoryQuery {
	public Page<NotaDesafio> listar(Pageable pageable);
}

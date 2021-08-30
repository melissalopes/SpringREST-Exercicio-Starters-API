package com.startersapi.repository.submissao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.startersapi.model.SubmissaoDesafios;

public interface SubmissaoDesafiosRepositoryQuery {
	public Page<SubmissaoDesafios> listar(Pageable pageable);
}

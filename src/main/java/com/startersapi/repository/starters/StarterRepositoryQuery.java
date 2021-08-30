package com.startersapi.repository.starters;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.startersapi.model.Starter;

public interface StarterRepositoryQuery {
	public Page<Starter> listar(Pageable pageable);
}

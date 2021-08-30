package com.startersapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.startersapi.model.SubmissaoDesafios;
import com.startersapi.repository.submissao.SubmissaoDesafiosRepositoryQuery;

public interface SubmissaoDesafiosRepository extends JpaRepository<SubmissaoDesafios, Long>, SubmissaoDesafiosRepositoryQuery{

}

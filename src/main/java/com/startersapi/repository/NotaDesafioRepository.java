package com.startersapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.startersapi.model.NotaDesafio;
import com.startersapi.repository.nota.NotaDesafioRepositoryQuery;


public interface NotaDesafioRepository extends JpaRepository<NotaDesafio, Long>, NotaDesafioRepositoryQuery{

}

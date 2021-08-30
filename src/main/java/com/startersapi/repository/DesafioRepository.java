package com.startersapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.startersapi.model.Desafio;
import com.startersapi.repository.desafio.DesafioRepositoryQuery;

public interface DesafioRepository extends JpaRepository<Desafio, Long>, DesafioRepositoryQuery{

}

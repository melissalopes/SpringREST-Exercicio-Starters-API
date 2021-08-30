package com.startersapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.startersapi.model.Starter;
import com.startersapi.repository.starters.StarterRepositoryQuery;

public interface StarterRepository extends JpaRepository<Starter, Long>, StarterRepositoryQuery{

}

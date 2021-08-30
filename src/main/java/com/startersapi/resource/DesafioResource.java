package com.startersapi.resource;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.startersapi.event.RecursoCriadoEvent;
import com.startersapi.model.Desafio;
import com.startersapi.repository.DesafioRepository;
import com.startersapi.service.DesafioService;

@RestController
@RequestMapping("/desafios")
@PreAuthorize("hasRole('ROLE_INSTRUTOR')")
public class DesafioResource {
	@Autowired
	private DesafioRepository repository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private DesafioService desafioService;
	
	@GetMapping
	public Page<Desafio> listar(Pageable pageable){
		return repository.listar(pageable);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Desafio> criar(@Validated @RequestBody Desafio desafio, HttpServletResponse response){
		Desafio desafioSalvo = repository.save(desafio);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, desafioSalvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(desafioSalvo);
	}	
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Desafio> buscarPeloCodigo(@PathVariable Long codigo){
		Desafio desafioSalvo = desafioService.buscarDesafioPeloCodigo(codigo);
		return ResponseEntity.ok(desafioSalvo);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Desafio> atualizar(@PathVariable Long codigo, @Validated @RequestBody Desafio desafio){
		Desafio desafioSalvo = desafioService.atualizar(codigo, desafio);
		return ResponseEntity.ok(desafioSalvo);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		repository.deleteById(codigo);
	}
}

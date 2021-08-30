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
import com.startersapi.model.Starter;
import com.startersapi.repository.StarterRepository;
import com.startersapi.service.StarterService;

@RestController
@RequestMapping("/starters")
@PreAuthorize("hasRole('ROLE_INSTRUTOR')")
public class StarterResource {
	@Autowired
	private StarterRepository repository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private StarterService starterService;
	
	@GetMapping
	public Page<Starter> listar(Pageable pageable){
		return repository.listar(pageable);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Starter> criar(@Validated @RequestBody Starter starter, HttpServletResponse response){
		Starter starterSalvo = repository.save(starter);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, starterSalvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(starterSalvo);
	}	
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Starter> buscarPeloCodigo(@PathVariable Long codigo){
		Starter starterSalvo = starterService.buscarStarterPeloCodigo(codigo);
		return ResponseEntity.ok(starterSalvo);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Starter> atualizar(@PathVariable Long codigo, @Validated @RequestBody Starter starter){
		Starter starterSalvo = starterService.atualizar(codigo, starter);
		return ResponseEntity.ok(starterSalvo);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		repository.deleteById(codigo);
	}
}

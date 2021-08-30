package com.startersapi.resource;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.startersapi.event.RecursoCriadoEvent;
import com.startersapi.exceptionhandler.ApiExceptionHandler.Erro;
import com.startersapi.model.SubmissaoDesafios;
import com.startersapi.repository.SubmissaoDesafiosRepository;
import com.startersapi.service.SubmissaoDesafiosService;
import com.startersapi.service.exception.StarterOuDesafioInexistenteException;

@RestController
@RequestMapping("/submissoes")
public class SubmissaoDesafiosResource {
	@Autowired
	private SubmissaoDesafiosRepository repository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private SubmissaoDesafiosService submissaoDesafiosService;
	
	@Autowired
	private MessageSource message;
	
	@GetMapping
	@PreAuthorize("hasRole('ROLE_INSTRUTOR')")
	public Page<SubmissaoDesafios> listar(Pageable pageable){
		return repository.listar(pageable);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasAnyRole('ROLE_STARTER', 'ROLE_INSTRUTOR')")
	public ResponseEntity<SubmissaoDesafios> criar(@Validated @RequestBody SubmissaoDesafios submissao, HttpServletResponse response){
		SubmissaoDesafios submissaoSalva = submissaoDesafiosService.salvar(submissao);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, submissaoSalva.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(submissaoSalva);
	}	
	
	@GetMapping("/{codigo}")
	@PreAuthorize("hasRole('ROLE_INSTRUTOR')")
	public ResponseEntity<SubmissaoDesafios> buscarPeloCodigo(@PathVariable Long codigo){
		SubmissaoDesafios submissaoSalva = submissaoDesafiosService.buscarSubmissaoPeloCodigo(codigo);
		return ResponseEntity.ok(submissaoSalva);
	}
	
	@PutMapping("/{codigo}")
	@PreAuthorize("hasRole('ROLE_INSTRUTOR')")
	public ResponseEntity<SubmissaoDesafios> atualizar(@PathVariable Long codigo, @Validated @RequestBody SubmissaoDesafios submissao){
		SubmissaoDesafios submissaoSalva = submissaoDesafiosService.atualizar(codigo, submissao);
		return ResponseEntity.ok(submissaoSalva);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasRole('ROLE_INSTRUTOR')")
	public void remover(@PathVariable Long codigo) {
		repository.deleteById(codigo);
	}
	
	@ExceptionHandler({ StarterOuDesafioInexistenteException.class })
	@PreAuthorize("hasRole('ROLE_INSTRUTOR')")
	public ResponseEntity<Object> handleStarterOuDesafioInexistenteException(StarterOuDesafioInexistenteException ex){	
		String msgUsuario = message.getMessage("recurso.nao-encontrado", null, LocaleContextHolder.getLocale());
		String msgDesenvolvedor = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(msgUsuario, msgDesenvolvedor));
		return ResponseEntity.badRequest().body(erros);
	}
}

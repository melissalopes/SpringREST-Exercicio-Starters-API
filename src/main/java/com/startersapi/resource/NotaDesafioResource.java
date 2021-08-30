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
import com.startersapi.model.NotaDesafio;
import com.startersapi.repository.NotaDesafioRepository;
import com.startersapi.service.NotaDesafioService;
import com.startersapi.service.exception.SubmissaoInexistenteException;

@RestController
@RequestMapping("/notas")
@PreAuthorize("hasRole('ROLE_INSTRUTOR')")
public class NotaDesafioResource {
	@Autowired
	private NotaDesafioRepository repository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private NotaDesafioService notaDesafioService;
	
	@Autowired
	private MessageSource message;
	
	@GetMapping
	public Page<NotaDesafio> listar(Pageable pageable){
		return repository.listar(pageable);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<NotaDesafio> criar(@Validated @RequestBody NotaDesafio nota, HttpServletResponse response){
		NotaDesafio notaSalva = notaDesafioService.salvar(nota);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, notaSalva.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(notaSalva);
	}	
	
	@GetMapping("/{codigo}")
	public ResponseEntity<NotaDesafio> buscarPeloCodigo(@PathVariable Long codigo){
		NotaDesafio notaSalva = notaDesafioService.buscarNotaDesafioPeloCodigo(codigo);
		return ResponseEntity.ok(notaSalva);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<NotaDesafio> atualizar(@PathVariable Long codigo, @Validated @RequestBody NotaDesafio nota){
		NotaDesafio notaSalva = notaDesafioService.atualizar(codigo, nota);
		return ResponseEntity.ok(notaSalva);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		repository.deleteById(codigo);
	}
	
	@ExceptionHandler({ SubmissaoInexistenteException.class })
	public ResponseEntity<Object> handleSubmissaoInexistenteException(SubmissaoInexistenteException ex){	
		String msgUsuario = message.getMessage("recurso.nao-encontrado", null, LocaleContextHolder.getLocale());
		String msgDesenvolvedor = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(msgUsuario, msgDesenvolvedor));
		return ResponseEntity.badRequest().body(erros);
	}
}

package com.example.proyectoses.service.impl;

import com.example.proyectoses.service.ICRUD;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

public abstract class CRUDImpl<T, ID> implements ICRUD<T, ID> {

	protected abstract ReactiveMongoRepository<T, ID> getRepo();
	
	@Override
	public Mono<T> registrar(T t) {
		return getRepo().save(t);
	}

	@Override
	public Mono<T> modificar(T t) {
		return getRepo().save(t);
	}

	@Override
	public Flux<T> listar() {
		return getRepo().findAll();
	}

	@Override
	public Mono<T> listarPorId(ID id) {
		return getRepo().findById(id);
	}

	@Override
	public Mono<Void> eliminar(ID id) {
		return getRepo().deleteById(id);
	}	
	



}

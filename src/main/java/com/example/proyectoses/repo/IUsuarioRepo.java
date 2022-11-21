package com.example.proyectoses.repo;

import com.example.proyectoses.entity.Usuario;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;


import reactor.core.publisher.Mono;

public interface IUsuarioRepo extends ReactiveMongoRepository<Usuario, String>{
		
	Mono<Usuario> findOneByUsuario(String usuario);	
}

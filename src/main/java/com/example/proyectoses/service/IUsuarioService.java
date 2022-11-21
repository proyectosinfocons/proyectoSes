package com.example.proyectoses.service;

import com.example.proyectoses.entity.Usuario;
import com.example.proyectoses.security.User;

import reactor.core.publisher.Mono;

public interface IUsuarioService extends ICRUD<Usuario, String>{

	Mono<Usuario> registrarHash(Usuario usuario);
	Mono<User> buscarPorUsuario(String usuario);

}

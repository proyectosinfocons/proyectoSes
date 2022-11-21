package com.example.proyectoses.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.example.proyectoses.entity.Usuario;
import com.example.proyectoses.repo.IRolRepo;
import com.example.proyectoses.repo.IUsuarioRepo;
import com.example.proyectoses.security.User;
import com.example.proyectoses.service.IUsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class UsuarioServiceImpl extends CRUDImpl<Usuario, String> implements IUsuarioService {

	private IUsuarioRepo repo;
	

	private IRolRepo rolRepo;
	
	private BCryptPasswordEncoder bcrypt;
	
	@Override
	protected ReactiveMongoRepository<Usuario, String> getRepo() {
		return repo; 
	}
	
	@Override
	public Mono<User> buscarPorUsuario(String usuario) {
		Mono<Usuario> monoUsuario = repo.findOneByUsuario(usuario);
		
		List<String> roles = new ArrayList<String>();
		
		return monoUsuario.flatMap(u -> {
			return Flux.fromIterable(u.getRoles())
					.flatMap(rol -> {
						return rolRepo.findById(rol.getId())
								.map(r -> {
									roles.add(r.getNombre());
									return r;
								});
					}).collectList().flatMap(list -> {
						u.setRoles(list);
						return Mono.just(u);
					});
		})	
		.flatMap(u -> {			
			return Mono.just(new User(u.getUsuario(), u.getClave(), u.getEstado(), roles));
		});
	}

	@Override
	public Mono<Usuario> registrarHash(Usuario usuario) {
		usuario.setClave(bcrypt.encode(usuario.getClave()));
		return repo.save(usuario);		
	}
}

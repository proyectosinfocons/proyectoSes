package com.example.proyectoses.service.impl;

import com.example.proyectoses.entity.Cliente;
import com.example.proyectoses.repo.IClienteRepo;
import com.example.proyectoses.service.IClienteService;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ClienteServiceImpl extends CRUDImpl<Cliente, String> implements IClienteService {


	private IClienteRepo repo;

	@Override
	protected ReactiveMongoRepository<Cliente, String> getRepo() {		
		return repo;
	}
	

}

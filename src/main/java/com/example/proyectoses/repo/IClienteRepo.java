package com.example.proyectoses.repo;

import com.example.proyectoses.entity.Cliente;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface IClienteRepo extends ReactiveMongoRepository<Cliente, String>{

}
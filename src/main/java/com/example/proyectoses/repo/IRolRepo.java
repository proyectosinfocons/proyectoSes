package com.example.proyectoses.repo;

import com.example.proyectoses.entity.Rol;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;


public interface IRolRepo extends ReactiveMongoRepository<Rol, String>{

}

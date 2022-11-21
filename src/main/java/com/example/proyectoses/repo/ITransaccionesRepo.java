package com.example.proyectoses.repo;

import com.example.proyectoses.entity.Transacciones;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ITransaccionesRepo extends ReactiveMongoRepository<Transacciones, String> {
}

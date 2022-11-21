package com.example.proyectoses.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonInclude;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "roles")
public class Rol {

	@Id
	private String id;

	@Field(name = "nombre")
	private String nombre;


}

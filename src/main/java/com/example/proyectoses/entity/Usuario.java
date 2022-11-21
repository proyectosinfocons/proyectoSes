package com.example.proyectoses.entity;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonInclude;



@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "usuarios")
public class Usuario {

	@Id
	private String id;


	@ApiModelProperty(notes = "usuario")
	@Field(name = "usuario")
	private String usuario;


	@ApiModelProperty(notes = "clave")
	@Field(name = "clave")
	private String clave;

	@ApiModelProperty(notes = "estado")
	@Field(name = "estado")
	private Boolean estado;


	@ApiModelProperty(notes = "Roles")
	@Field(name = "roles")
	private List<Rol> roles;



}

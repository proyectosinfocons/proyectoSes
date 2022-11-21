package com.example.proyectoses.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Data
public class RequestMontoDTO {

	@Id
	private String id;

	private Double monto;

	private Double montototaltipoCambio;

	private String nombre;

	private String tipoDocumento;

	private int numeroDocumento;


	private int cantidadCuotas;

}

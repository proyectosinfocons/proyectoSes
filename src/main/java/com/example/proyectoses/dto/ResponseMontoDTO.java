package com.example.proyectoses.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseMontoDTO {
	
	  private Double monto;
	
	  private Double montototaltipoCambio;

	private String nombre;

	private String tipoDocumento;

	private int numeroDocumento;

	private int cantidadCuotas;

}

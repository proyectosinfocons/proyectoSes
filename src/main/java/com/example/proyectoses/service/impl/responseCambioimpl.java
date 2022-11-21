package com.example.proyectoses.service.impl;


import com.example.proyectoses.dto.RequestMontoDTO;
import com.example.proyectoses.dto.ResponseMontoDTO;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;


@Component
public class responseCambioimpl{


	public ResponseMontoDTO responsemonto(RequestMontoDTO requestmonto, Double montototal) {
		// TODO Auto-generated method stub
		   return Mono.just(ResponseMontoDTO.builder()
			        .monto(requestmonto.getMonto())
			        .montototaltipoCambio(montototal)
			        .tipoDocumento(requestmonto.getTipoDocumento())
				    .cantidadCuotas(requestmonto.getCantidadCuotas())
			        .numeroDocumento(requestmonto.getNumeroDocumento())
			        .nombre(requestmonto.getNombre())
			        .build()).block();
	}

}

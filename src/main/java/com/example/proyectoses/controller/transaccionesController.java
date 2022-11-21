package com.example.proyectoses.controller;

import com.example.proyectoses.dto.RequestMontoDTO;
import com.example.proyectoses.dto.ResponseMontoDTO;
import com.example.proyectoses.entity.Cliente;
import com.example.proyectoses.entity.Transacciones;
import com.example.proyectoses.service.ITransaccionesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;


@Api
@AllArgsConstructor
@RestController
@RequestMapping("/transacciones")
public class transaccionesController {


    private ITransaccionesService service;


    @ApiOperation(value = "Listar Transaccione")
    @GetMapping
    public Mono<ResponseEntity<Flux<Transacciones>>> listar() {
        Flux<Transacciones> fxTransacciones = service.listar();

        return Mono.just(ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fxTransacciones)
        );
    }


    @PostMapping
    public Mono<ResponseEntity<Transacciones>> registrar(@RequestBody Transacciones requestCambioMontoDto, final ServerHttpRequest req) {

        Double	 montotal = null;
        if(requestCambioMontoDto.getCantidadCuotas()<=1){

            montotal=requestCambioMontoDto.getMontoPrestamo() * 10;
            requestCambioMontoDto.setMontoFinal(montotal);
        }

        if(requestCambioMontoDto.getCantidadCuotas()>=2){

            montotal=requestCambioMontoDto.getMontoPrestamo() * 20;
            requestCambioMontoDto.setMontoFinal(montotal);
        }else{
            montotal=requestCambioMontoDto.getMontoPrestamo() * 30;
            requestCambioMontoDto.setMontoFinal(montotal);
        }

        return  service.registrar(requestCambioMontoDto)
                .map(p -> ResponseEntity.created(URI.create(req.getURI().toString().concat("/").concat(p.getId())))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(p)
                );
    }



    @ApiOperation(value = "Modificar transacciones")
    @PutMapping("/{id}")
    public Mono<ResponseEntity<Transacciones>> modificar(@PathVariable("id") String id, @RequestBody Transacciones transacciones){

        Mono<Transacciones> monoBody = Mono.just(transacciones);
        Mono<Transacciones> monoBD = service.listarPorId(id);

        return monoBD
                .zipWith(monoBody, (bd, cl) -> {
                    bd.setId(id);
                    bd.setNombres(cl.getNombres());
                    bd.setTipoDocumento(cl.getTipoDocumento());
                    bd.setCantidadCuotas(cl.getCantidadCuotas());
                    bd.setMontoFinal(cl.getMontoFinal());
                    bd.setMontoPrestamo(cl.getMontoPrestamo());
                    return bd;
                })
                .flatMap(service::modificar)
                .map(pl -> ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(pl))
                .defaultIfEmpty(new ResponseEntity<Transacciones>(HttpStatus.NOT_FOUND));
    }



    @ApiOperation(value = "Eliminar transacciones")
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> eliminar(@PathVariable("id") String id){
        return service.listarPorId(id)
                .flatMap(p -> {
                    return service.eliminar(p.getId()) //Mono<Void>
                            .then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
                })
                .defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
    }


    @ApiOperation(value = "Listar transacciones por id")
    @GetMapping("/{id}")
    public Mono<ResponseEntity<Transacciones>> listarPorId(@PathVariable("id") String id) {
        return service.listarPorId(id)
                .map(p -> ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(p)
                )
                .defaultIfEmpty(ResponseEntity.notFound().build());

    }
}

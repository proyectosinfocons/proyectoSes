package com.example.proyectoses.controller;


//import com.example.proyectoses.service.clienteService;
import com.example.proyectoses.entity.Cliente;
import com.example.proyectoses.service.IClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;


@Api
@AllArgsConstructor
@RestController
@RequestMapping("/cliente")
public class clienteController {


    private IClienteService service;





    @ApiOperation(value = "Listar Clientes")
    @GetMapping
    public Mono<ResponseEntity<Flux<Cliente>>> listar() {
        Flux<Cliente> fxClientes = service.listar();

        return Mono.just(ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fxClientes)
        );
    }

    @ApiOperation(value = "Listar Clientes por id")
    @GetMapping("/{id}")
    public Mono<ResponseEntity<Cliente>> listarPorId(@PathVariable("id") String id) {
        return service.listarPorId(id)
                .map(p -> ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(p)
                )
                .defaultIfEmpty(ResponseEntity.notFound().build());

    }
    @ApiOperation(value = "Registrar clientes")
    @PostMapping
    public Mono<ResponseEntity<Cliente>> registrar(@RequestBody Cliente client, final ServerHttpRequest req) {
            if(client.getTipoDocumento().equals("1")){
                client.setTipoDocumento("DNI");
            }else {
                client.setTipoDocumento("CARNET");
            }
        return service.registrar(client)
                .map(p -> ResponseEntity.created(URI.create(req.getURI().toString().concat("/").concat(p.getId())))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(p)
                );
    }



    @ApiOperation(value = "Modificar Clientes")
    @PutMapping("/{id}")
    public Mono<ResponseEntity<Cliente>> modificar(@PathVariable("id") String id, @RequestBody Cliente cliente){

        Mono<Cliente> monoBody = Mono.just(cliente);
        Mono<Cliente> monoBD = service.listarPorId(id);

        return monoBD
                .zipWith(monoBody, (bd, cl) -> {
                    bd.setId(id);
                    bd.setNombres(cl.getNombres());
                    bd.setNumeroDocumento(cl.getNumeroDocumento());
                    bd.setTipoDocumento(cl.getTipoDocumento());
                    return bd;
                })
                .flatMap(service::modificar)
                .map(pl -> ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(pl))
                .defaultIfEmpty(new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND));
    }

    @ApiOperation(value = "Eliminar clientes")
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> eliminar(@PathVariable("id") String id){
        return service.listarPorId(id)
                .flatMap(p -> {
                    return service.eliminar(p.getId()) //Mono<Void>
                            .then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
                })
                .defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
    }


}

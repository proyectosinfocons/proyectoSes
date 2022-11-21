package com.example.proyectoses;


import com.example.proyectoses.entity.Cliente;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class transacServiceTest {


    @Autowired
    private WebTestClient cliente;


    @Test
    public void listarTest() {

        cliente.get()
                .uri("/transacciones")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(Cliente.class)
                .returnResult().getResponseBody()
                .stream()
                .count();
    }


}

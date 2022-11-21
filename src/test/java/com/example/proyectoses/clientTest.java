package com.example.proyectoses;


import com.example.proyectoses.entity.Cliente;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class clientTest {

    @Autowired
    private WebTestClient cliente;


    @Test
    public void listarTest() {

        cliente.get()
                .uri("/cliente")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(Cliente.class)
                .returnResult().getResponseBody()
                .stream()
                .count();
    }

    @Test
    public void eliminarTest() {
        Cliente cliente1 = new Cliente();
        cliente1.setId("63777bf0d408b135d6979ee34");
        cliente.delete()
                .uri("/cliente/" + cliente1.getId())
                .exchange()
                .expectStatus().isNotFound();
    }


    @Test
    public void listarporId() {
        Cliente cliente1 = new Cliente();
        cliente1.setId("63777bf0d408b135d6979ee34");
        cliente.get()
                .uri("/cliente/" + cliente1.getId())
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    public void registrarTest() {
        Cliente client4 = new Cliente();
        client4.setNombres("fefefefe");
        client4.setTipoDocumento("1");
        client4.setNumeroDocumento(234424);

        cliente.post()
                .uri("/cliente")
                .body(Mono.just(client4), Cliente.class)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.nombres").isNotEmpty()
                .jsonPath("$.tipoDocumento").isNotEmpty()
                .jsonPath("$.numeroDocumento").isNumber();


    }



    @Test
    public void registrarTest2() {
        Cliente client3 = new Cliente();
        client3.setNombres("fefefefe");
        client3.setTipoDocumento("2");
        client3.setNumeroDocumento(234424);

        cliente.post()
                .uri("/cliente")
                .body(Mono.just(client3), Cliente.class)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.nombres").isNotEmpty()
                .jsonPath("$.tipoDocumento").isNotEmpty()
                .jsonPath("$.numeroDocumento").isNumber();


    }
//    @Test
//    public void modificarTest() {
//        Cliente client = new Cliente();
//        client.setId("63783957b3feec6d1a368655");
//        client.setNombres("arroz pollo");
//        client.setTipoDocumento("1");
//        client.setNumeroDocumento(34533636);
//
//        cliente.put()
//                .uri("/cliente/" + client.getId())
//                .body(Mono.just(client), Cliente.class)
//                .exchange()
//                .expectStatus().isNotFound()
//                .expectHeader().contentType(MediaType.APPLICATION_JSON)
//                .expectBody()
//                .jsonPath("$.nombres").isNotEmpty()
//                .jsonPath("$.tipoDocumento").isNotEmpty()
//                .jsonPath("$.numeroDocumento").isNumber();
//    }

}

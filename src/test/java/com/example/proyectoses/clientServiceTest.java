package com.example.proyectoses;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class clientServiceTest {

//    @InjectMocks
//    private ClienteServiceImpl clienteService;
//
//    @Mock
//    private IClienteRepo clienteRepository;
//


//    @Test
//    public void shouldGetclient() {
//        cliente client2 = cliente.builder()
//                .id("id")
//                .nombres("nombres")
//                .numeroDocumento(424242552)
//                .tipoDocumento("tipoDocumento")
//                .build();
//        when(clienteRepository.findAll()).thenReturn(Flux.just(client2));
//        Mono<cliente> clientMono = clienteService.listarPorId(TEST_EMAIL);
//        StepVerifier
//                .create(userMono)
//                .consumeNextWith(newUser -> {
//                    assertEquals(newUser.getEmail(), TEST_EMAIL);
//                })
//                .verifyComplete();
//    }

}

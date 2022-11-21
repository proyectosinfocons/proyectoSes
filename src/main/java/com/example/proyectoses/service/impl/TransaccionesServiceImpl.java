package com.example.proyectoses.service.impl;



import com.example.proyectoses.dto.RequestMontoDTO;
import com.example.proyectoses.dto.ResponseMontoDTO;
import com.example.proyectoses.entity.Transacciones;
import com.example.proyectoses.repo.ITransaccionesRepo;
import com.example.proyectoses.service.ITransaccionesService;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class TransaccionesServiceImpl  extends CRUDImpl<Transacciones, String> implements ITransaccionesService {

    private ITransaccionesRepo repo;

    private responseCambioimpl montoService;

    @Override
    protected ReactiveMongoRepository<Transacciones, String> getRepo() {
       return repo;
    }

    public ResponseMontoDTO listatipodecambio(RequestMontoDTO montocambioDTO) {
        Double	 montotal = null;
        if(montocambioDTO.getCantidadCuotas()<=1){

            montotal=montocambioDTO.getMonto() * 10;
        }

        if(montocambioDTO.getCantidadCuotas()>=2){

            montotal=montocambioDTO.getMonto() * 20;
        }else{
            montotal=montocambioDTO.getMonto() * 30;

        }



        return montoService.responsemonto(montocambioDTO, montotal);
    }
}

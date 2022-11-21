package com.example.proyectoses.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "transacciones")
public class Transacciones {



    @Id
    private String id;


    private String tipoDocumento;


    private String nombres;


    private double montoPrestamo;


    private int cantidadCuotas;

    private double montoFinal;





}




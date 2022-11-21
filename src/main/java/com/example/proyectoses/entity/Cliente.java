package com.example.proyectoses.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "cliente")
public class Cliente {


    @Id
    private String id;


    @ApiModelProperty(notes = "Nombre de cliente")
    @NotNull
    @Size(min = 50)
    private String nombres;


    @ApiModelProperty(notes = "Tipo de documento")
    @NotNull
    @Size(min = 50)
    private String tipoDocumento;

    @ApiModelProperty(notes = "Numero de documento del cliente")
    @NotNull
    @Size(max = 8)
    private int numeroDocumento;





}

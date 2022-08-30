package com.dh.odontogrupo1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Paciente {
    private int id;
    private String nome;
    private String sobrenome;
    private Endereco endereco;
    private String rg;
    private LocalDate dataCadastro;

}

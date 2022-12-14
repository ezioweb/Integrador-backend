package com.dh.odontogrupo1.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String sobrenome;
    private String rg;
    private LocalDate dataCadastro;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="endereco_id")
    private Endereco endereco;

}

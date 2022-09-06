package com.dh.odontogrupo1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rua",nullable = false,length = 100)
    private String rua;

    @Column(name = "numero",nullable = false,length = 100)
    private String numero;

    @Column(name = "bairro",nullable = false,length = 100)
    private String bairro;
}

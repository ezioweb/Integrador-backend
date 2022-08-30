package com.dh.odontogrupo1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Consulta {
    private int id;
    private Dentista dentista;
    private Paciente paciente;
    private LocalDateTime dataHoraConsulta;

}

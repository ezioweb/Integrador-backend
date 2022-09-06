package com.dh.odontogrupo1.model.dto;


import com.dh.odontogrupo1.model.Paciente;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)

public class PacienteDTO {

    private String nomePaciente;

    public PacienteDTO(Paciente paciente){
        this.nomePaciente = paciente.getNome() + " " + paciente.getSobrenome();
    }
    }



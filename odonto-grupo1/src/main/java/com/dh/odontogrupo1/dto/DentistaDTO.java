package com.dh.odontogrupo1.dto;

import com.dh.odontogrupo1.model.Dentista;
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
public class DentistaDTO {
    private String nomecompleto;

    public DentistaDTO(Dentista dentista){
        this.nomecompleto = dentista.getNome() + " " + dentista.getSobrenome();
    }
}

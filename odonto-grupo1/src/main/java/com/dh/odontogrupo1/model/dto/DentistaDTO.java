package com.dh.odontogrupo1.model.dto;

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

    private Long id;
    private String nomecompleto;

    public DentistaDTO(Dentista dentista){
        this.id = dentista.getId();

        this.nomecompleto = dentista.getNome() + " " + dentista.getSobrenome();
    }
}

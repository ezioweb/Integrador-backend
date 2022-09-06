package com.dh.odontogrupo1.model.dto;


import com.dh.odontogrupo1.model.Endereco;
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

public class EnderecoDTO {

    private String rua;
    private String numero;
    private String bairro;

    public EnderecoDTO(Endereco endereco){
        this.rua = endereco.getRua();
        this.numero = endereco.getNumero();
        this.bairro = endereco.getBairro();
    }

}

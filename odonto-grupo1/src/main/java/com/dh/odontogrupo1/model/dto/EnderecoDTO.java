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
    private Long id;

    private String rua;
    private String numero;
    private String bairro;

    public EnderecoDTO(Endereco endereco){
        this.id = endereco.getId();

        this.rua = endereco.getRua();
        this.numero = endereco.getNumero();
        this.bairro = endereco.getBairro();
    }

}

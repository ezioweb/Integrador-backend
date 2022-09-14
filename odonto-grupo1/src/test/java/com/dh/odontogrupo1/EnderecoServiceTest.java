package com.dh.odontogrupo1;

import com.dh.odontogrupo1.model.Dentista;
import com.dh.odontogrupo1.model.Endereco;
import com.dh.odontogrupo1.service.DentistaService;
import com.dh.odontogrupo1.service.EnderecoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EnderecoServiceTest {

    @Autowired
    EnderecoService service;
    static Endereco endereco;

    @BeforeAll
    static void doBefore() {
        endereco = new Endereco();
        endereco.setRua("Rua das Cenouras");
        endereco.setNumero("1234");
        endereco.setBairro("Jd das Canoas");
    }

    @Test
    void naoDeveRetornarNullAoSalvarEndereco() {
        Endereco enderecoSalvo;
        enderecoSalvo = service.salvarEndereco(endereco);

        Assertions.assertNotNull(enderecoSalvo.getId());
    }

}

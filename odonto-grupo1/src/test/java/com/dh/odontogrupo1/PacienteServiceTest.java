package com.dh.odontogrupo1;

import com.dh.odontogrupo1.model.Endereco;
import com.dh.odontogrupo1.model.Paciente;
import com.dh.odontogrupo1.service.DentistaService;
import com.dh.odontogrupo1.service.EnderecoService;
import com.dh.odontogrupo1.service.PacienteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PacienteServiceTest {

    @Autowired
    PacienteService pacienteService;

    static Paciente paciente;

    static Endereco endereco;

    @Autowired
    static
    EnderecoService enderecoService;

    static Endereco enderecoSalvo;


    @BeforeAll
    static void doBefore(){

        endereco = new Endereco();
        endereco.setRua("Avenida do Tetra");
        endereco.setBairro("Jd Brasil");
        endereco.setNumero("123");

        enderecoSalvo = enderecoService.salvarEndereco(endereco);


        paciente.setNome("Ayrton");
        paciente.setSobrenome("Senna");
        paciente.setRg("123456");
        paciente.setEndereco(enderecoSalvo);
    }

    @Test
    void salvamentoPacienteOK(){
        Paciente pacienteSalvo;

        pacienteSalvo = pacienteService.salvar(paciente);

        Assertions.assertNotNull(pacienteSalvo.getId());

    }
}

package com.dh.odontogrupo1;

import com.dh.odontogrupo1.model.Endereco;
import com.dh.odontogrupo1.model.Paciente;
import com.dh.odontogrupo1.service.EnderecoService;
import com.dh.odontogrupo1.service.PacienteService;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PacienteServiceTest {

    @Autowired
    PacienteService pacienteService;

    final static Logger log = Logger.getLogger(PacienteServiceTest.class);
    static Paciente paciente = new Paciente();

    static Endereco endereco = new Endereco();


    static Endereco enderecoSalvo;


    @BeforeAll
    static void doBefore(){

        endereco.setRua("Avenida do Tetra");
        endereco.setBairro("Jd Brasil");
        endereco.setNumero("123");
        log.info("Criando endereço: " + endereco);


        paciente.setNome("Ayrton");
        paciente.setSobrenome("Senna");
        paciente.setRg("123456");
        paciente.setEndereco(endereco);
    }

    @Test
    void salvamentoPacienteOK(){
        Paciente pacienteSalvo;
        log.info("Salvando paciente ... ");
        pacienteSalvo = pacienteService.salvar(paciente);
        log.info("Paciente salvo: " + pacienteSalvo);
        Assertions.assertNotNull(pacienteSalvo.getId());

    }
}

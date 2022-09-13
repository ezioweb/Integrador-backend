package com.dh.odontogrupo1.service;

import com.dh.odontogrupo1.model.Consulta;
import com.dh.odontogrupo1.model.Dentista;
import com.dh.odontogrupo1.model.Endereco;
import com.dh.odontogrupo1.model.Paciente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;


@SpringBootTest
class ConsultaServiceTest {

    @Autowired
    ConsultaService consultaService;

//    @Autowired
    static Consulta consulta;

    @BeforeAll
    static void doBefore(){
//        dentista = new Dentista();
//        dentista.setId(1L);
//        dentista.setNome("Dr. Abobrinha");
//        dentista.setSobrenome("Alcachofro");
//        dentista.setMatricula("54321");
//
//        endereco = new Endereco();
//        endereco.setId(1L);
//        endereco.setRua("Rua Pacovan");
//        endereco.setBairro("Bananal");
//        endereco.setNumero("333");
//
//        paciente = new Paciente();
//        paciente.setId(1L);
//        paciente.setNome("Godofredo");
//        paciente.setSobrenome("Peloponeso");
//        paciente.setRg("101918172");
//        paciente.setDataCadastro(LocalDate.now());
//        paciente.setEndereco(endereco);

        consulta = new Consulta();
        consulta.setDataHoraConsulta(LocalDate.of(2022, 10, 12));
    }

    @Test
    void naoDeveRetornarNullAoSalvarConsulta() {
        Consulta consultaSalva = new Consulta();
        consultaSalva = consultaService.salvar(consulta);

        Assertions.assertNotNull(consultaSalva.getId());
    }

    @Test
    void deveRetornarTrueAoBuscarConsultaPorId() {
        Optional<Consulta> consultaObtida =  consultaService.buscarPorId(1L);

        Assertions.assertTrue(consultaObtida.isPresent());
    }

    @Test
    void atualizar() {
        consultaService.atualizar(consulta);

    }
}













package com.dh.odontogrupo1;

import com.dh.odontogrupo1.exception.ResourceNotFoundException;
import com.dh.odontogrupo1.model.Consulta;
import com.dh.odontogrupo1.service.ConsultaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
class ConsultaServiceTest {

    @Autowired
    ConsultaService consultaService;

    static Consulta consulta;

    @BeforeAll
    static void doBefore(){

        consulta = new Consulta();
        consulta.setDataHoraConsulta(LocalDate.of(2022, 10, 12));
    }

    @Test
    void deveRetornarTrueAposSalvarEBuscarConsultaPorId() throws ResourceNotFoundException{
        consultaService.salvar(consulta);
        List<Consulta> consultas = consultaService.buscarTodos();
        Consulta primeiraConsultaNaLista = consultas.get(0);
        Long id = primeiraConsultaNaLista.getId();

        Optional<Consulta> consultaObtida =  consultaService.buscarPorId(id);

        Assertions.assertTrue(consultaObtida.isPresent());
    }

    @Test
    void deveVerificarQueAposAlterarDataEBuscarConsultaADataFoiAlterada() throws ResourceNotFoundException{
        consultaService.salvar(consulta);
        List<Consulta> consultas = consultaService.buscarTodos();
        Consulta primeiraConsultaNaLista = consultas.get(0);
        Long id = primeiraConsultaNaLista.getId();

        consulta.setId(id);
        consulta.setDataHoraConsulta(LocalDate.of(2022, 10, 13));
        LocalDate resultadoEsperado = consulta.getDataHoraConsulta();

        consultaService.atualizar(consulta);

        Optional<Consulta> consultaObtida = consultaService.buscarPorId(id);
        LocalDate resultadoObtido = consultaObtida.get().getDataHoraConsulta();
        Assertions.assertEquals(resultadoEsperado, resultadoObtido);
    }

    @Test
    void buscarTodasConsultas() {
        List<Consulta> consultas = consultaService.buscarTodos();

        Assertions.assertNotNull(consultas);
    }

    @Test
    void aposSalvarEExcluirAConsultaDoIdDeveRetornarFalse() throws ResourceNotFoundException {
        consultaService.salvar(consulta);
        List<Consulta> consultas = consultaService.buscarTodos();
        Consulta primeiraConsultaNaLista = consultas.get(0);
        Long id = primeiraConsultaNaLista.getId();

        consultaService.excluir(id);

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class, ()-> consultaService.buscarPorId(consulta.getId()));

        Assertions.assertTrue(exception.getMessage().contains("Id informado"));

    }


}













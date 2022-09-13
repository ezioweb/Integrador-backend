package com.dh.odontogrupo1;

import com.dh.odontogrupo1.model.Consulta;
import com.dh.odontogrupo1.model.Dentista;
import com.dh.odontogrupo1.service.DentistaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DentistaServiceTest {

    @Autowired
    DentistaService service;
    static Dentista dentista;

    @BeforeAll
    static void doBefore() {
        dentista = new Dentista();
        dentista.setNome("Dr. Abobrinha");
        dentista.setSobrenome("Alcachofro");
        dentista.setMatricula("54321");
    }

    @Test
    void naoDeveRetornarNullAoSalvarDentista() {
        Dentista dentistaSalvo = new Dentista();
        dentistaSalvo = service.salvar(dentista);

        Assertions.assertNotNull(dentistaSalvo.getId());
    }

}

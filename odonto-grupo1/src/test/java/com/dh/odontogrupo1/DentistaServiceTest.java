package com.dh.odontogrupo1;

import com.dh.odontogrupo1.exception.ResourceAlreadyExistsException;
import com.dh.odontogrupo1.exception.ResourceNotFoundException;
import com.dh.odontogrupo1.model.Consulta;
import com.dh.odontogrupo1.model.Dentista;
import com.dh.odontogrupo1.model.Endereco;
import com.dh.odontogrupo1.model.dto.DentistaDTO;
import com.dh.odontogrupo1.service.DentistaService;
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
    void naoDeveRetornarNullAoSalvarDentista() throws ResourceAlreadyExistsException {
        Dentista dentistaSalvo = new Dentista();
        dentistaSalvo = service.salvar(dentista);

        Assertions.assertNotNull(dentistaSalvo.getId());
    }

    @Test
    void buscarTodosDentistas()  {

        List<DentistaDTO> dentistas = service.buscarTodos();

        Assertions.assertNotNull(dentistas);
    }

    @Test
    void excluirDentista() throws ResourceAlreadyExistsException, ResourceNotFoundException {
        service.salvar(dentista);
        List<DentistaDTO> dentistas = service.buscarTodos();
        DentistaDTO ultimoDentistaCadastrado = dentistas.get(dentistas.size() -1);
        Long id = ultimoDentistaCadastrado.getId();

        service.excluir(id);

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class, ()-> service.buscarPorId(dentista.getId()));

        Assertions.assertTrue(exception.getMessage().contains("Id não encontrado"));

    }

    @Test
    void alterarDadosDentista() throws ResourceAlreadyExistsException, ResourceNotFoundException {
        service.salvar(dentista);
        List<DentistaDTO> dentistas = service.buscarTodos();
        DentistaDTO ultimoDentistaCadastrado = dentistas.get(dentistas.size() -1);
        Long id = ultimoDentistaCadastrado.getId();

        dentista.setId(id);
        dentista.setNome("joao");
        dentista.setSobrenome("silva");
        dentista.setMatricula("6666");
        String resultadoEsperado = dentista.getNome() + dentista.getSobrenome() + dentista.getMatricula();

        service.atualizar(dentista);

        Optional<Dentista> dentistaObtido = service.buscarPorId(id);
        String resultadoObtido = dentistaObtido.get().getNome()
                                +dentistaObtido.get().getSobrenome()
                                +dentistaObtido.get().getMatricula();

        Assertions.assertEquals(resultadoEsperado, resultadoObtido);

    }

    @Test
    void buscarDentistaPorId() throws ResourceAlreadyExistsException, ResourceNotFoundException {

        service.salvar(dentista);
        List<DentistaDTO> dentistas = service.buscarTodos();
        DentistaDTO ultimoDentistaCadastrado = dentistas.get(dentistas.size() -1);
        Long id = ultimoDentistaCadastrado.getId();
        Optional<Dentista> dentistaSalvo;
        dentistaSalvo = service.buscarPorId(id);

        Assertions.assertNotNull(dentistaSalvo);
    }

}

package com.dh.odontogrupo1;

import com.dh.odontogrupo1.exception.ResourceAlreadyExistsException;
import com.dh.odontogrupo1.exception.ResourceNotFoundException;
import com.dh.odontogrupo1.model.Endereco;
import com.dh.odontogrupo1.model.Paciente;
import com.dh.odontogrupo1.model.dto.PacienteDTO;
import com.dh.odontogrupo1.service.DentistaService;
import com.dh.odontogrupo1.service.EnderecoService;
import com.dh.odontogrupo1.service.PacienteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class PacienteServiceTest {

    @Autowired
    PacienteService pacienteService;

    @Autowired
    static Paciente paciente;

    @Autowired
    static Endereco endereco;
    static Endereco endereco2;


    @BeforeAll
    static void doBefore(){

        endereco = new Endereco();
        endereco.setRua("Avenida do Tetra");
        endereco.setBairro("Jd Brasil");
        endereco.setNumero("123");

        paciente = new Paciente();
        paciente.setNome("Ayrton");
        paciente.setSobrenome("Senna");
        paciente.setRg("123456");
        paciente.setEndereco(endereco);
    }

    @Test
    void salvamentoPacienteOK() throws ResourceAlreadyExistsException {
        Paciente pacienteSalvo;

        pacienteSalvo = pacienteService.salvar(paciente);

        Assertions.assertNotNull(pacienteSalvo.getId());

    }

    @Test
    void buscarTodos(){
        List<PacienteDTO> pacientes = pacienteService.buscarTodos();
        Assertions.assertNotNull(pacientes);
    }

    @Test
    void excluirPaciente() throws ResourceAlreadyExistsException, ResourceNotFoundException {
        pacienteService.salvar(paciente);
        List<PacienteDTO> pacientes = pacienteService.buscarTodos();
        PacienteDTO ultimoPacienteCadastrado = pacientes.get(pacientes.size()-1);
        Long id = ultimoPacienteCadastrado.getId();

        pacienteService.excluir(id);

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,() -> pacienteService.buscarPorId(paciente.getId())
        );

        Assertions.assertTrue(exception.getMessage().contains("Id não encontrado"));


    }

    @Test
    void alterarPaciente() throws ResourceAlreadyExistsException, ResourceNotFoundException {

        pacienteService.salvar(paciente);
        List<PacienteDTO> pacientes = pacienteService.buscarTodos();
        PacienteDTO ultimoPacienteCadastrado = pacientes.get(pacientes.size()-1);
        Long id = ultimoPacienteCadastrado.getId();

        paciente.setId(id);
        paciente.setNome("Rubens");
        paciente.setSobrenome("Barrichelo");
        paciente.setRg("987654");
        String resultadoEsperado = paciente.getNome()
                                 + paciente.getSobrenome()
                                 + paciente.getRg()
                                 + paciente.getEndereco();

        pacienteService.alterar(paciente);
        Optional<Paciente> pacienteObtido = pacienteService.buscarPorId(id);
        String resultadoObtido = pacienteObtido.get().getNome()
                               + pacienteObtido.get().getSobrenome()
                               + pacienteObtido.get().getRg()
                               + pacienteObtido.get().getEndereco();

        Assertions.assertEquals(resultadoEsperado, resultadoObtido);
    }

    @Test
    void buscarPorId() throws ResourceAlreadyExistsException, ResourceNotFoundException {
        pacienteService.salvar(paciente);
        List<PacienteDTO> pacientes = pacienteService.buscarTodos();
        PacienteDTO ultimoPacienteCadastrado = pacientes.get(pacientes.size()-1);
        Long id = ultimoPacienteCadastrado.getId();
        Optional<Paciente> pacienteSalvo;
        pacienteSalvo = pacienteService.buscarPorId(id);

        Assertions.assertNotNull(pacienteSalvo);

    }
}

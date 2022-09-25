package com.dh.odontogrupo1;

import com.dh.odontogrupo1.exception.ResourceAlreadyExistsException;
import com.dh.odontogrupo1.exception.ResourceNotFoundException;
import com.dh.odontogrupo1.model.Dentista;
import com.dh.odontogrupo1.model.Endereco;
import com.dh.odontogrupo1.model.Paciente;
import com.dh.odontogrupo1.model.dto.DentistaDTO;
import com.dh.odontogrupo1.model.dto.EnderecoDTO;
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

@SpringBootTest
public class EnderecoServiceTest {

    @Autowired
    EnderecoService service;
    static Endereco endereco;

    @Autowired
    PacienteService pacienteService;

    @Autowired
    static Paciente paciente;

    @BeforeAll
    static void doBefore() {
        endereco = new Endereco();
        endereco.setRua("Rua das Cenouras");
        endereco.setNumero("1234");
        endereco.setBairro("Jd das Canoas");

        paciente = new Paciente();
        paciente.setNome("Ayrton");
        paciente.setSobrenome("Senna");
        paciente.setRg("123456");
        paciente.setEndereco(endereco);
    }

    @Test
    void buscarEnderecoPorId() throws ResourceNotFoundException {
        Optional<Endereco> enderecoSalvo;
        enderecoSalvo = service.buscaPorId(endereco.getId());

        Assertions.assertNotNull(enderecoSalvo);
    }

    @Test
    void buscarTodosEnderecos(){
        List<EnderecoDTO> enderecos = service.buscarTodos();

        Assertions.assertNotNull(enderecos);
    }

    @Test
    void alterarDadosEndereco() throws ResourceNotFoundException, ResourceAlreadyExistsException {
        Paciente pacienteSalvo;
        pacienteSalvo = pacienteService.salvar(paciente);
        List<EnderecoDTO> enderecos = service.buscarTodos();
        EnderecoDTO ultimoEnderecoCadastrado = enderecos.get(enderecos.size() -1);
        Long id = ultimoEnderecoCadastrado.getId();

        endereco.setId(id);
        endereco.setRua("Rua Picanha");
        endereco.setNumero("4321");
        endereco.setBairro("Jd churrascaria");
        String resultadoEsperado = endereco.getRua() + endereco.getNumero() + endereco.getBairro();

        service.alterar(endereco);

        Optional<Endereco> enderecoObtido = service.buscaPorId(id);
        String resultadoObtido = enderecoObtido.get().getRua()
                +enderecoObtido.get().getNumero()
                +enderecoObtido.get().getBairro();

        Assertions.assertEquals(resultadoEsperado, resultadoObtido);


    }

}

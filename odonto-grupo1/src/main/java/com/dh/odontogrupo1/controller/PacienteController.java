package com.dh.odontogrupo1.controller;

import com.dh.odontogrupo1.model.Paciente;
import com.dh.odontogrupo1.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/paciente")
public class PacienteController {
    @Autowired
    PacienteService service;

    @PostMapping
    public Paciente salvarPaciente(@RequestBody Paciente paciente) throws SQLException {
        return service.salvar(paciente);
    }

    @GetMapping
    public List<Paciente> buscarTodosPacientes() throws SQLException {
        return service.buscarTodos();
    }

    @DeleteMapping
    public void excluirPaciente(@RequestParam("id") int id) throws SQLException {
        service.excluir(id);
    }

    @PatchMapping
    public void alterarRg(@RequestBody Paciente paciente) throws SQLException {
        service.alterar(paciente);
    }

    @RequestMapping("/buscaid")
    public ResponseEntity buscarPacientePorId(@RequestParam("id") int id) throws SQLException {
        Optional<Paciente> pacienteOptional = service.buscarPorId(id);
        if(pacienteOptional.isEmpty()){
            return new ResponseEntity("Paciente não encontrado", HttpStatus.NOT_FOUND);
        }
        Paciente paciente = pacienteOptional.get();

        return new ResponseEntity(paciente, HttpStatus.OK);
    }
}

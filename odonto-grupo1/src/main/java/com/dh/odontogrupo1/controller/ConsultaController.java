package com.dh.odontogrupo1.controller;


import com.dh.odontogrupo1.exception.ResourceNotFoundException;
import com.dh.odontogrupo1.model.Consulta;
import com.dh.odontogrupo1.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {
    @Autowired
    ConsultaService service;
    @PostMapping
    public Consulta salvarConsulta(@RequestBody Consulta consulta) throws ResourceNotFoundException {
        return service.salvar(consulta);
    }

    @GetMapping
    public List<Consulta> buscarTodasConsultas() {
        return service.buscarTodos();
    }

    @DeleteMapping
    public void excluirConsulta(@RequestParam("id") Long id) throws ResourceNotFoundException {
        service.excluir(id);
    }

    @PatchMapping
    public void alterarConsulta(@RequestBody Consulta consulta) throws ResourceNotFoundException {
        service.atualizar(consulta);
    }

    @RequestMapping("/buscaid")
    public ResponseEntity buscarConsultaPorId(@RequestParam("id") Long id) throws ResourceNotFoundException {
        Optional<Consulta> consultaOptional = service.buscarPorId(id);
        Consulta consulta = consultaOptional.get();

        return new ResponseEntity(consulta, HttpStatus.OK);
    }

}

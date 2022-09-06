package com.dh.odontogrupo1.controller;

import com.dh.odontogrupo1.model.Consulta;
import com.dh.odontogrupo1.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {
    @Autowired
    ConsultaService service;
    @PostMapping
    public Consulta salvarConsulta(@RequestBody Consulta consulta) throws SQLException {
        return service.salvar(consulta);
    }

    @GetMapping
    public List<Consulta> buscarTodasConsultas() throws SQLException {
        return service.buscarTodos();
    }

    @DeleteMapping
    public void excluirConsulta(@RequestParam("id") Long id) throws SQLException {
        service.excluir(id);
    }

    @PatchMapping
    public void alterarConsulta(@RequestBody Consulta consulta) throws SQLException {
        service.atualizar(consulta);
    }

    @RequestMapping("/buscaid")
    public ResponseEntity buscarConsultaPorId(@RequestParam("id") Long id) throws SQLException {
        Optional<Consulta> consultaOptional = service.buscarPorId(id);
        if(consultaOptional.isEmpty()){
            return new ResponseEntity("Dentista não encontrado", HttpStatus.NOT_FOUND);
        }
        Consulta consulta = consultaOptional.get();

        return new ResponseEntity(consulta, HttpStatus.OK);
    }

}

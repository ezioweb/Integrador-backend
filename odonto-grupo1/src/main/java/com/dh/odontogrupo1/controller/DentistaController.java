package com.dh.odontogrupo1.controller;

import com.dh.odontogrupo1.model.Dentista;
import com.dh.odontogrupo1.service.DentistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dentista")
public class DentistaController {
    @Autowired
    DentistaService service;
    @PostMapping
    public Dentista salvarDentista(@RequestBody Dentista dentista) throws SQLException {
        return service.salvar(dentista);
    }

    @GetMapping
    public List<Dentista> buscarTodosDentistas() throws SQLException {
        return service.buscarTodos();
    }

    @DeleteMapping
    public void excluirDentista(@RequestParam("id") int id) throws SQLException {
        service.excluir(id);
    }

    @PatchMapping
    public void alterarMatricula(@RequestBody Dentista dentista) throws SQLException {
        service.atualizar(dentista);
    }

    @RequestMapping("/buscaid")
    public ResponseEntity buscarDentistaPorId(@RequestParam("id") int id) throws SQLException {
        Optional<Dentista> dentistaOptional = service.buscarPorId(id);
        if(dentistaOptional.isEmpty()){
            return new ResponseEntity("Dentista não encontrado", HttpStatus.NOT_FOUND);
        }
        Dentista dentista = dentistaOptional.get();

        return new ResponseEntity(dentista, HttpStatus.OK);
    }

}

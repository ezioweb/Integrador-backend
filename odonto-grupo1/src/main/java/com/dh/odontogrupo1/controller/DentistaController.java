package com.dh.odontogrupo1.controller;

import com.dh.odontogrupo1.model.Dentista;
import com.dh.odontogrupo1.service.DentistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

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

}

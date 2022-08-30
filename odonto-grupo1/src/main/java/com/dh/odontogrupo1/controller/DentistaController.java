package com.dh.odontogrupo1.controller;

import com.dh.odontogrupo1.model.Dentista;
import com.dh.odontogrupo1.service.DentistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/clinica")
public class DentistaController {

    @Autowired
    DentistaService service;

    @PostMapping
    public Dentista salvarDentista(@RequestBody Dentista dentista) throws SQLException {
        return service.salvar(dentista);
    }

}

package com.dh.odontogrupo1.controller;


import com.dh.odontogrupo1.model.Endereco;
import com.dh.odontogrupo1.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {
    @Autowired
    EnderecoService service;
    @PostMapping
    public Endereco salvarEndereco(@RequestBody Endereco endereco) throws SQLException{
        return service.salvarEndereco(endereco);
    }

    @GetMapping
    public List<Endereco> buscarTodosEnderecos() throws SQLException{
        return service.buscarTodos();
    }

    @DeleteMapping
    public void excluirDentista(@RequestParam("id") int id) throws SQLException{
        service.excluir(id);
    }

    @PatchMapping
    public void alterarEndereco(@RequestBody Endereco endereco) throws SQLException{
        service.alterar(endereco);
    }

    @RequestMapping("/buscaPorId")
    public ResponseEntity buscarEnderecoPorId(@RequestParam("id") int id) throws SQLException{
        Optional<Endereco> enderecoOptional = service.buscaPorId(id);
        if(enderecoOptional.isEmpty()){
            return new ResponseEntity("Endereco nao encontrado", HttpStatus.NOT_FOUND);
        }
        Endereco endereco = enderecoOptional.get();

        return new ResponseEntity(endereco,HttpStatus.OK);
    }

}

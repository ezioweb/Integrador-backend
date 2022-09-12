package com.dh.odontogrupo1.controller;


import com.dh.odontogrupo1.model.Endereco;
import com.dh.odontogrupo1.model.dto.EnderecoDTO;
import com.dh.odontogrupo1.service.EnderecoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    EnderecoService service;

    @PostMapping
    public Endereco salvarEndereco(@RequestBody Endereco endereco) {
        return service.salvarEndereco(endereco);
    }

    @GetMapping
    public List<EnderecoDTO> buscarTodosEnderecos() {
        return service.buscarTodos();
    }

    @DeleteMapping
    public void excluirDentista(@RequestParam("id") Long id) {
        service.excluir(id);
    }

    @PatchMapping
    public void alterarEndereco(@RequestBody Endereco endereco) {
        service.alterar(endereco);
    }

    @RequestMapping(value = "/buscaPorId", method = RequestMethod.GET)
    public ResponseEntity buscarEnderecoPorId(@RequestParam("id") Long id){

        ObjectMapper mapper = new ObjectMapper();

        Optional<Endereco> enderecoOptional = service.buscaPorId(id);
        if(enderecoOptional.isEmpty()){
            return new ResponseEntity("Endereco nao encontrado", HttpStatus.NOT_FOUND);
        }
        Endereco endereco = enderecoOptional.get();
        EnderecoDTO enderecoDTO = mapper.convertValue(endereco, EnderecoDTO.class);

        return new ResponseEntity(endereco,HttpStatus.OK);
    }

}
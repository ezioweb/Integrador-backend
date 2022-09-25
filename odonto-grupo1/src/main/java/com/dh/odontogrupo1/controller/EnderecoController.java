package com.dh.odontogrupo1.controller;


import com.dh.odontogrupo1.exception.ResourceNotFoundException;
import com.dh.odontogrupo1.model.Endereco;
import com.dh.odontogrupo1.model.dto.EnderecoDTO;
import com.dh.odontogrupo1.service.EnderecoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    final static Logger log = Logger.getLogger(EnderecoController.class);

    @Autowired
    EnderecoService service;


    @GetMapping
    public List<EnderecoDTO> buscarTodosEnderecos() {
        return service.buscarTodos();
    }


    @PatchMapping
    public void alterarEndereco(@RequestBody Endereco endereco) throws ResourceNotFoundException {
        service.alterar(endereco);
    }

    @RequestMapping(value = "/buscaPorId", method = RequestMethod.GET)
    public ResponseEntity buscarEnderecoPorId(@RequestParam("id") Long id) throws ResourceNotFoundException{

//        ObjectMapper mapper = new ObjectMapper();
//
//        Optional<Endereco> enderecoOptional = service.buscaPorId(id);
//        if(enderecoOptional.isEmpty()){
//            log.error("Endereco ID:'" + id + "' não encontrado");
//            return new ResponseEntity("Endereco nao encontrado", HttpStatus.NOT_FOUND);
//        }
//        Endereco endereco = enderecoOptional.get();
//        EnderecoDTO enderecoDTO = mapper.convertValue(endereco, EnderecoDTO.class);

        return new ResponseEntity(service.buscaPorId(id),HttpStatus.OK);
    }

}

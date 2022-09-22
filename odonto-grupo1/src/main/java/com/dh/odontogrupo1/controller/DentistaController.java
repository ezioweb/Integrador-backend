package com.dh.odontogrupo1.controller;
import com.dh.odontogrupo1.exception.ResourceAlreadyExistsException;
import com.dh.odontogrupo1.exception.ResourceNotFoundException;
import com.dh.odontogrupo1.model.dto.DentistaDTO;
import com.dh.odontogrupo1.model.Dentista;
import com.dh.odontogrupo1.service.DentistaService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/dentista")
@CrossOrigin("*")
public class DentistaController {

    final static Logger log = Logger.getLogger(DentistaController.class);

    @Autowired
    DentistaService service;

//    @PostMapping
    @RequestMapping(method = RequestMethod.POST, produces = {"application/json","application/xml"}, consumes = {"application/json","application/xml"})
    public Dentista salvarDentista(@RequestBody Dentista dentista) throws ResourceAlreadyExistsException {

        return service.salvar(dentista);
    }

    @RequestMapping(method = RequestMethod.GET, produces = {"application/json","application/xml"})
    public List<DentistaDTO> buscarTodosDentistas() {
        return service.buscarTodos();
    }

    @DeleteMapping
    public void excluirDentista(@RequestParam("id") Long id) throws ResourceNotFoundException {
        service.excluir(id);
    }

    @PatchMapping
    public void alterarDados(@RequestBody Dentista dentista) throws ResourceNotFoundException {
        service.atualizar(dentista);
    }

    @GetMapping("/buscaid")
    public ResponseEntity buscarDentistaPorId(@RequestParam("id") Long id) throws ResourceNotFoundException {

        return new ResponseEntity(service.buscarPorId(id), HttpStatus.OK);
    }

}

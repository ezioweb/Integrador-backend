package com.dh.odontogrupo1.controller;

import com.dh.odontogrupo1.model.Consulta;
import com.dh.odontogrupo1.service.ConsultaService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {

    final static Logger log = Logger.getLogger(ConsultaController.class);

    @Autowired
    ConsultaService service;
    @PostMapping
    public Consulta salvarConsulta(@RequestBody Consulta consulta) {

        return service.salvar(consulta);
    }

    @GetMapping
    public List<Consulta> buscarTodasConsultas() {
        return service.buscarTodos();
    }

    @DeleteMapping
    public void excluirConsulta(@RequestParam("id") Long id) {
        service.excluir(id);
    }

    @PatchMapping
    public void alterarConsulta(@RequestBody Consulta consulta)  {
        service.atualizar(consulta);
    }

    @RequestMapping("/buscaid")
    public ResponseEntity buscarConsultaPorId(@RequestParam("id") Long id)  {
        Optional<Consulta> consultaOptional = service.buscarPorId(id);
        if(consultaOptional.isEmpty()){
            log.error("Consulta ID:'" + id +"' não encontrado");
            return new ResponseEntity("Consulta não encontrada", HttpStatus.NOT_FOUND);
        }
        Consulta consulta = consultaOptional.get();

        return new ResponseEntity(consulta, HttpStatus.OK);
    }

}

package com.dh.odontogrupo1.service;

import com.dh.odontogrupo1.exception.ResourceNotFoundException;
import com.dh.odontogrupo1.model.Consulta;
import com.dh.odontogrupo1.repository.ConsultaRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {

    final static Logger log = Logger.getLogger(ConsultaService.class);

    @Autowired
    ConsultaRepository repository;

    public Consulta salvar(Consulta consulta){

        log.info("Salvando consulta");

        return repository.save(consulta);
    }

    public List<Consulta> buscarTodos() {

        log.info("Buscando todas as consultas");

        return repository.findAll();
    }

    public void excluir(Long id) throws ResourceNotFoundException {

        repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Erro ao tentar excluir a consulta, id informado não existe"));

        log.info("Deletando a consulta de ID:" + id);

        repository.deleteById(id);
    }

    public Consulta atualizar(Consulta consulta){

        log.info("Atualizando a consulta");

        return repository.save(consulta);
    }

    public Optional<Consulta> buscarPorId(Long id) throws ResourceNotFoundException{

        repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Erro ao buscar consulta. Id informado não existe."));

        log.info("Buscando consulta por ID: " + id);

        return repository.findById(id);
    }
}

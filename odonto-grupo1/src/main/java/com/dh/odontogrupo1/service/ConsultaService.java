package com.dh.odontogrupo1.service;

import com.dh.odontogrupo1.model.Consulta;
import com.dh.odontogrupo1.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {

    @Autowired
    ConsultaRepository repository;

    public Consulta salvar(Consulta consulta){
        return repository.save(consulta);
    }

    public List<Consulta> buscarTodos() throws SQLException {
        return repository.findAll();
    }

    public void excluir(Long id){
        repository.deleteById(id);
    }

    public Consulta atualizar(Consulta consulta){
        return repository.save(consulta);
    }

    public Optional<Consulta> buscarPorId(Long id){
        return repository.findById(id);
    }
}

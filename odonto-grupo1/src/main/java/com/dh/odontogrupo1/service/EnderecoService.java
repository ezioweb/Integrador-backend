package com.dh.odontogrupo1.service;

import com.dh.odontogrupo1.model.Endereco;
import com.dh.odontogrupo1.model.dto.EnderecoDTO;
import com.dh.odontogrupo1.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    EnderecoRepository repository;

    public Endereco salvarEndereco(Endereco endereco) {
        return repository.save(endereco);
    }

    public List<EnderecoDTO> buscarTodos() {
        List<Endereco> listEndereco = repository.findAll();

        List<EnderecoDTO> listEnderecoDTO = new ArrayList<>();

        for(Endereco e: listEndereco){
            listEnderecoDTO.add(new EnderecoDTO(e));
        }
        return listEnderecoDTO;
    }

    public void alterar(Endereco endereco) {
        repository.save(endereco);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }

    public Optional<Endereco> buscaPorId(Long id) {
        return repository.findById(id);
    }
}

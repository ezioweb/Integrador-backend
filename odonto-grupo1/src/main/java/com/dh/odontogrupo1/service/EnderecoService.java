package com.dh.odontogrupo1.service;

import com.dh.odontogrupo1.exception.ResourceNotFoundException;
import com.dh.odontogrupo1.model.Endereco;
import com.dh.odontogrupo1.model.dto.EnderecoDTO;
import com.dh.odontogrupo1.repository.EnderecoRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    final static Logger log = Logger.getLogger(EnderecoService.class);

    @Autowired
    EnderecoRepository repository;

    public Endereco salvarEndereco(Endereco endereco) {

        log.info("Salvando cadastro de endereco");

        return repository.save(endereco);
    }

    public List<EnderecoDTO> buscarTodos() {

        log.info("Buscando todos os enderecos");

        List<Endereco> listEndereco = repository.findAll();

        List<EnderecoDTO> listEnderecoDTO = new ArrayList<>();

        for(Endereco e: listEndereco){
            listEnderecoDTO.add(new EnderecoDTO(e));
        }
        return listEnderecoDTO;
    }

    public void alterar(Endereco endereco) {

        log.info("Atualizando endereco");

        repository.save(endereco);
    }

    public void excluir(Long id) throws ResourceNotFoundException{

        repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Erro ao tentar excluir o endereço, id informado não existe"));

        log.info("Excluindo o endereco de id: " + id);
        repository.deleteById(id);
    }

    public Optional<Endereco> buscaPorId(Long id) throws ResourceNotFoundException{
        log.info("Buscando endereco pelo id: " + id);
        return Optional.ofNullable(repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Erro ao tentar buscar o endereço. Endereço pesquisado não encontrado")));

    }
}

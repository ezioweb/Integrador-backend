package com.dh.odontogrupo1.service;

import com.dh.odontogrupo1.exception.ResourceNotFoundException;
import com.dh.odontogrupo1.model.dto.DentistaDTO;
import com.dh.odontogrupo1.model.Dentista;
import com.dh.odontogrupo1.repository.DentistaRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.ResourceAccessException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DentistaService {

    final static Logger log = Logger.getLogger(DentistaService.class);

    @Autowired
    DentistaRepository repository;

    public Dentista salvar(Dentista dentista){

        log.info("Salvando cadastro de dentista");

        return repository.save(dentista);
    }

    public List<DentistaDTO> buscarTodos()  {

        log.info("Buscando todos os dentistas");

        List<Dentista> listDentista = repository.findAll();

        List<DentistaDTO> listDentistaDTO = new ArrayList<>();


        for (Dentista d : listDentista){
            listDentistaDTO.add(new DentistaDTO(d));
        }

        return listDentistaDTO;
    }

    public void excluir(Long id) throws ResourceNotFoundException {
        log.info("Deletando dentista de ID: " + id);

        repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("-- Erro ao tentar excluir. Id não encontrado --"));
        repository.deleteById(id);
    }

    public Dentista atualizar(Dentista dentista) {

        log.info("Atualizando cadastro de Dentista");

        return repository.save(dentista);
    }

    public Optional<Dentista> buscarPorId(Long id) {

        log.info("Buscando dentista pelo ID:" +id);

        return repository.findById(id);
    }
}

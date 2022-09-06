package com.dh.odontogrupo1.service;

import com.dh.odontogrupo1.dto.DentistaDTO;
import com.dh.odontogrupo1.model.Dentista;
import com.dh.odontogrupo1.repository.DentistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DentistaService {

    @Autowired
    DentistaRepository repository;

    public Dentista salvar(Dentista dentista){
        return repository.save(dentista);
    }

    public List<DentistaDTO> buscarTodos()  {
        List<Dentista> listDentista = repository.findAll();

        List<DentistaDTO> listDentistaDTO = new ArrayList<>();


        for (Dentista d : listDentista){
            listDentistaDTO.add(new DentistaDTO(d));
        }

        return listDentistaDTO;
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }

    public Dentista atualizar(Dentista dentista) {
        return repository.save(dentista);
    }

    public Optional<Dentista> buscarPorId(Long id) {
        return repository.findById(id);
    }
}

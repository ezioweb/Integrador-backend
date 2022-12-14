package com.dh.odontogrupo1.service;

import com.dh.odontogrupo1.exception.ResourceAlreadyExistsException;
import com.dh.odontogrupo1.exception.ResourceNotFoundException;
import com.dh.odontogrupo1.model.dto.DentistaDTO;
import com.dh.odontogrupo1.model.Dentista;
import com.dh.odontogrupo1.repository.DentistaRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DentistaService {

    final static Logger log = Logger.getLogger(DentistaService.class);

    @Autowired
    DentistaRepository repository;

    public Dentista salvar(Dentista dentista) throws ResourceAlreadyExistsException {
        log.info("Salvando cadastro de dentista");

        Dentista dentistaCadastrado = repository.findByMatricula(dentista.getMatricula());
        if(dentistaCadastrado != null){
            throw new ResourceAlreadyExistsException("Matricula de Dentista já cadastrado");
        }

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

    public Dentista atualizar(Dentista dentista) throws ResourceNotFoundException {
        log.info("Atualizando cadastro de Dentista");

        if(repository.findById(dentista.getId()).isEmpty()){
            throw new ResourceNotFoundException("Dentista não encontrado para alteração");
        }
        return repository.save(dentista);
    }

    public Optional<Dentista> buscarPorId(Long id) throws ResourceNotFoundException {
        log.info("Buscando dentista pelo ID:" +id);

        return Optional.ofNullable(repository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("-- Erro ao buscar dentista. Id não encontrado --")));


    }
}

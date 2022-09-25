package com.dh.odontogrupo1.service;


import com.dh.odontogrupo1.exception.ResourceAlreadyExistsException;
import com.dh.odontogrupo1.exception.ResourceNotFoundException;
import com.dh.odontogrupo1.model.Paciente;
import com.dh.odontogrupo1.model.dto.PacienteDTO;
import com.dh.odontogrupo1.repository.PacienteRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    final static Logger log = Logger.getLogger(PacienteService.class);

    @Autowired
    PacienteRepository repository;

    public Paciente salvar(Paciente paciente) throws ResourceAlreadyExistsException {

        log.info("Salvando paciente: " + paciente);
        Paciente pacienteCadastrado = repository.findByNomeAndSobrenome(paciente.getNome(), paciente.getSobrenome());
        if(pacienteCadastrado != null){
            throw new ResourceAlreadyExistsException("-- Nome de Paciente já cadastrado --");
        }

        paciente.setDataCadastro(LocalDate.now());
        return repository.save(paciente);
    }

    public List<PacienteDTO> buscarTodos(){

        log.info("Buscando todos os pacientes");

        List<Paciente> listPaciente = repository.findAll();
        List<PacienteDTO> listPacienteDTO = new ArrayList<>();

        for (Paciente p : listPaciente){
            listPacienteDTO.add(new PacienteDTO(p));
        }
        return listPacienteDTO;
    }

    public Paciente alterar(Paciente paciente) throws ResourceNotFoundException {

        log.info("Atualizando Paciente");

        if(buscarPorId(paciente.getId()).isEmpty()){
            throw new ResourceNotFoundException("-- Paciente não encontrado para alteração --");
        }

        return repository.save(paciente);
    }


    public void excluir(Long id) throws ResourceNotFoundException {
        log.info("Excluindo o paciente de ID: " + id);
        repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("-- Erro ao tentar excluir. Id não encontrado --"));
        repository.deleteById(id);
    }

    public Optional<Paciente> buscarPorId(Long id) throws ResourceNotFoundException {
        log.info("Buscando paciente pelo id: " + id);
        repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("-- Erro ao buscar paciente. Id não encontrado --"));
        return repository.findById(id);
    }

}

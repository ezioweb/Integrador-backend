package com.dh.odontogrupo1.service;


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

    public Paciente salvar(Paciente paciente){

        log.info("Salvando paciente: " + paciente);

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

    public Paciente alterar(Paciente paciente){

        log.info("Atualizando Paciente");

        return repository.save(paciente);
    }


    public void excluir(Long id){
        log.info("Excluindo o paciente de ID: " + id);

        repository.deleteById(id);
    }

    public Optional<Paciente> buscarPorId(Long id){

        log.info("Buscando paciente pelo id: " + id);

        return repository.findById(id);
    }

}

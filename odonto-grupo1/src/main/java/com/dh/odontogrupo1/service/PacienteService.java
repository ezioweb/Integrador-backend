package com.dh.odontogrupo1.service;


import com.dh.odontogrupo1.model.Paciente;
import com.dh.odontogrupo1.model.dto.PacienteDTO;
import com.dh.odontogrupo1.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {
    @Autowired
    PacienteRepository repository;

    public Paciente salvar(Paciente paciente){
        paciente.setDataCadastro(LocalDate.now());
        return repository.save(paciente);
    }

    public List<PacienteDTO> buscarTodos(){
        List<Paciente> listPaciente = repository.findAll();
        List<PacienteDTO> listPacienteDTO = new ArrayList<>();

        for (Paciente p : listPaciente){
            listPacienteDTO.add(new PacienteDTO(p));
        }
        return listPacienteDTO;
    }

    public Paciente alterar(Paciente paciente){
        return repository.save(paciente);
    }


    public void excluir(Long id){
        repository.deleteById(id);
    }

    public Optional<Paciente> buscarPorId(Long id){
        return repository.findById(id);
    }

}

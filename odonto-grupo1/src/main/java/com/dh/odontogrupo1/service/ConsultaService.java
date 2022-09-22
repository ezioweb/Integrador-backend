package com.dh.odontogrupo1.service;

import com.dh.odontogrupo1.exception.ResourceNotFoundException;
import com.dh.odontogrupo1.model.Consulta;
import com.dh.odontogrupo1.model.Dentista;
import com.dh.odontogrupo1.model.Paciente;
import com.dh.odontogrupo1.repository.ConsultaRepository;
import com.dh.odontogrupo1.repository.DentistaRepository;
import com.dh.odontogrupo1.repository.PacienteRepository;
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

    @Autowired
    PacienteRepository pacienteRepository;

    @Autowired
    DentistaRepository dentistaRepository;

    public Consulta salvar(Consulta consulta) throws ResourceNotFoundException {
        log.info("Salvando consulta");
        Dentista dentista = consulta.getDentista();
        Optional<Dentista> idDentista = dentistaRepository.findById(dentista.getId());
        Paciente paciente = consulta.getPaciente();
        Optional<Paciente> idPaciente = pacienteRepository.findById(paciente.getId());

        if(idDentista.isEmpty() || idPaciente.isEmpty()){
            throw new ResourceNotFoundException("Paciente e/ou Dentista não encontrados");
        }

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

    public Consulta atualizar(Consulta consulta) throws ResourceNotFoundException {

        log.info("Atualizando a consulta");

        if(repository.findById(consulta.getId()).isEmpty()){
            throw new ResourceNotFoundException("Consulta não encontrada para alteração");
        }

        return salvar(consulta);
    }

    public Optional<Consulta> buscarPorId(Long id) throws ResourceNotFoundException{

        repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Erro ao buscar consulta. Id informado não existe."));

        log.info("Buscando consulta por ID: " + id);

        return repository.findById(id);
    }
}

package com.dh.odontogrupo1.repository;

import com.dh.odontogrupo1.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    Paciente findByNomeAndSobrenome(String nome, String sobrenome);
}


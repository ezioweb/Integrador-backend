package com.dh.odontogrupo1.service;

import com.dh.odontogrupo1.dao.IDao;
import com.dh.odontogrupo1.model.Consulta;
import com.dh.odontogrupo1.model.Dentista;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {

    @Autowired
    IDao<Consulta> consultaDao;

    public Consulta salvar(Consulta consulta) throws SQLException {
        return consultaDao.salvar(consulta);
    }

    public List<Consulta> buscarTodos() throws SQLException {
        return consultaDao.buscarTodos();
    }

    public void excluir(int id) throws SQLException {
        consultaDao.excluir(id);
    }

    public void atualizar(Consulta consulta) throws SQLException {
        consultaDao.alterar(consulta);
    }

    public Optional<Consulta> buscarPorId(int id) throws SQLException {
        return consultaDao.buscarPorId(id);
    }
}

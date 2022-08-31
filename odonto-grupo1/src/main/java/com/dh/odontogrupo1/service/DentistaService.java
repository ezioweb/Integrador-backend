package com.dh.odontogrupo1.service;

import com.dh.odontogrupo1.dao.IDao;
import com.dh.odontogrupo1.model.Dentista;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class DentistaService {

    @Autowired
    IDao<Dentista> dentistaDao;

    public Dentista salvar(Dentista dentista) throws SQLException {
        return dentistaDao.salvar(dentista);
    }

    public List<Dentista> buscarTodos() throws SQLException {
        return dentistaDao.buscarTodos();
    }
}

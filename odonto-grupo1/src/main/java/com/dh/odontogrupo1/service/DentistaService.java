package com.dh.odontogrupo1.service;

import com.dh.odontogrupo1.dao.IDao;
import com.dh.odontogrupo1.dao.impl.DentistaDAOH2;
import com.dh.odontogrupo1.model.Dentista;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class DentistaService {

    @Autowired
    DentistaDAOH2 dentistaDao;

    public Dentista salvar(Dentista dentista) throws SQLException {
        return dentistaDao.salvar(dentista);
    }

}

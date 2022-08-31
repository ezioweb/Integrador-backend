package com.dh.odontogrupo1.service;

import com.dh.odontogrupo1.dao.IDao;
import com.dh.odontogrupo1.model.Endereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    IDao<Endereco> enderecoDAOH2;

    public Endereco salvarEndereco(Endereco endereco) throws SQLException{
        return enderecoDAOH2.salvar(endereco);
    }

    public List<Endereco> buscarTodos() throws SQLException{
        return enderecoDAOH2.buscarTodos();
    }

    public void alterar(Endereco endereco) throws SQLException{
        enderecoDAOH2.alterar(endereco);
    }

    public void excluir(int id) throws SQLException{
        enderecoDAOH2.excluir(id);
    }

    public Optional<Endereco> buscaPorId(int id) throws SQLException{
        return enderecoDAOH2.buscarPorId(id);
    }
}

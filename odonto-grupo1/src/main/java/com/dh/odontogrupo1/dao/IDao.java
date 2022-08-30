package com.dh.odontogrupo1.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface IDao<T> {
    public T salvar(T t) throws SQLException;
    public List<T> buscarTodos() throws SQLException;
    public void alterar(T t) throws SQLException;
    public Optional<T> buscarPorId(int id) throws SQLException;
    public void excluir(int id) throws SQLException;
}

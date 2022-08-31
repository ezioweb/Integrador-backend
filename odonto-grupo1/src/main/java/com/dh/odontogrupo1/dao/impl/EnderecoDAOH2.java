package com.dh.odontogrupo1.dao.impl;

import com.dh.odontogrupo1.dao.ConfiguracaoJDBC;
import com.dh.odontogrupo1.dao.IDao;
import com.dh.odontogrupo1.model.Endereco;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;


public class EnderecoDAOH2 implements IDao<Endereco> {
    private ConfiguracaoJDBC configuracaoJDBC;
    private static Logger log = Logger.getLogger(EnderecoDAOH2.class);



    @Override
    public Endereco salvar(Endereco endereco) throws SQLException {
        log.info("Abrindo conexão");

        String SQLInsert = String.format("INSERT INTO endereco(rua, numero, bairro)"
                + "VALUES ('%s,'%s','%s');"
                , endereco.getRua()
                , endereco.getNumero()
                , endereco.getBairro());

        Connection connection = null;

        try{
            log.info("Cadastrando Endereço:" + endereco.getRua() + " " + endereco.getNumero() + " " + endereco.getBairro());
            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver","jdbc:h2:~/odontoclinica;INIT=RUNSCRIPT FROM 'create.sql'","sa","");
            Statement stmt = connection.createStatement();

            stmt.execute(SQLInsert,Statement.RETURN_GENERATED_KEYS);

            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()){
                endereco.setId(rs.getInt(1));
            }

            }catch (Exception e){
            e.printStackTrace();
            log.error("Erro ao cadastrar endereço: " + e.getMessage());
            } finally {
                connection.close();
            }

        return endereco;
    }

    @Override
    public List<Endereco> buscarTodos() throws SQLException {
        return null;
    }

    @Override
    public void alterar(Endereco endereco) throws SQLException {

    }

    @Override
    public Optional<Endereco> buscarPorId(int id) throws SQLException {
        return Optional.empty();
    }

    @Override
    public void excluir(int id) throws SQLException {

    }
}

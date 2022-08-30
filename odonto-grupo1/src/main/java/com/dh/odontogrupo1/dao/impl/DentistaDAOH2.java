package com.dh.odontogrupo1.dao.impl;

import com.dh.odontogrupo1.dao.ConfiguracaoJDBC;
import com.dh.odontogrupo1.dao.IDao;
import com.dh.odontogrupo1.model.Dentista;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Configuration
public class DentistaDAOH2 implements IDao<Dentista> {

    private ConfiguracaoJDBC configuracaoJDBC;
    final static Logger log = Logger.getLogger(DentistaDAOH2.class);

    @Override
    public Dentista salvar(Dentista dentista) throws SQLException {
        log.info("Abrindo conexão");

        String SQLInsert = String.format("INSERT INTO dentista (nome, sobrenome, matricula)" +
                " VALUES ('%s','%s', '%s')", dentista.getNome(), dentista.getSobrenome(), dentista.getMatricula());
        Connection connection = null;

        try{
            log.info("Cadastrando dentista: " + dentista.getNome());
            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver","jdbc:h2:~/odontoclinica;INIT=RUNSCRIPT FROM 'create.sql'","sa","");
            connection = configuracaoJDBC.getConnection();
            Statement statement = connection.createStatement();

            statement.execute(SQLInsert,Statement.RETURN_GENERATED_KEYS);

            ResultSet resultSet = statement.getGeneratedKeys();

            if(resultSet.next())
                dentista.setId(resultSet.getInt(1));

        } catch (Exception e) {
            e.printStackTrace();
            log.error("Erro ao cadastrar dentista: "+ e.getMessage());
        } finally {
            connection.close();
        }

        return dentista;
    }

    @Override
    public List<Dentista> buscarTodos() throws SQLException {
        return null;
    }

    @Override
    public void alterar(Dentista dentista) throws SQLException {

    }

    @Override
    public Optional<Dentista> buscarPorId(int id) throws SQLException {
        return Optional.empty();
    }

    @Override
    public void excluir(int id) throws SQLException {

    }
}

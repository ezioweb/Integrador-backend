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
import java.util.ArrayList;
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
            log.info("Fechando conexao");
        }

        return dentista;
    }

    @Override
    public List<Dentista> buscarTodos() throws SQLException {
        log.info("Abrindo conexão");
        Connection connection = null;
        Statement statement = null;
        String sqlSelect = "SELECT * FROM dentista";

        List<Dentista> dentistas = new ArrayList<>();

        try{
            log.info("Exibindo todos os dentistas");
            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver","jdbc:h2:~/odontoclinica;INIT=RUNSCRIPT FROM 'create.sql'","sa","");
            connection = configuracaoJDBC.getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlSelect);

            log.debug("Buscando todos os dentistas");
            while (resultSet.next()) {
                dentistas.add(criarDentista(resultSet));
            }

        } catch (SQLException e){
            e.printStackTrace();
            log.error("Erro ao buscar dentistas: " + e.getMessage());
        } finally {
            connection.close();
            log.info("Fechando conexao");
        }
        return dentistas;
    }

    @Override
    public void alterar(Dentista dentista) throws SQLException {
        log.info("Abrindo conexao");
        String sqlUpdate = String.format("UPDATE dentista SET matricula = '%s' where id = '%s';",
                dentista.getMatricula(), dentista.getId());
        Connection connection = null;

        try {
            log.info("Alterando matricula do id: " + dentista.getId());
            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver","jdbc:h2:~/odontoclinica;INIT=RUNSCRIPT FROM 'create.sql'","sa","");
            connection = configuracaoJDBC.getConnection();
            Statement statement = connection.createStatement();

            statement.execute(sqlUpdate);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Erro ao alterar matricula: " + e.getMessage());
        } finally {
            log.info("Fechando conexão");
            connection.close();
        }
    }

    @Override
    public Optional<Dentista> buscarPorId(int id) throws SQLException {
        log.debug("Abrindo uma conexão");
        Connection connection = null;
        Statement stmt = null;
        String query = String.format("SELECT * FROM dentista WHERE ID = %s",id);
        Dentista dentista = null;

        try {
            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver","jdbc:h2:~/odontoclinica;INIT=RUNSCRIPT FROM 'create.sql'","sa","");
            connection = configuracaoJDBC.getConnection();

            log.debug("Buscando dentista de id: " + id);
            stmt = connection.createStatement();
            ResultSet resultado = stmt.executeQuery(query);

            while (resultado.next()) {
                dentista = criarDentista(resultado);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            log.error("Erro ao buscar dentista: " + e.getMessage());
        }finally {

            log.debug("Fechando a conexão");
            connection.close();
        }

        return dentista != null ? Optional.of(dentista) : Optional.empty();
    }

    @Override
    public void excluir(int id) throws SQLException {
        log.info("Abrindo Conexão");
        Connection conn = null;
        Statement stmt = null;
        String sqlDelete = String.format("DELETE FROM dentista WHERE id='%s'", id);
        try{
            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver","jdbc:h2:~/odontoclinica;INIT=RUNSCRIPT FROM 'create.sql'","sa","");
            conn = configuracaoJDBC.getConnection();
            stmt = conn.createStatement();
            stmt.execute(sqlDelete);
            log.debug("Excluindo dentista de id: " + id);
        } catch(SQLException e){
            e.printStackTrace();
            log.error("Erro ao excluir dentista: " + e.getMessage());
        } finally {
            log.info("Fechando conexão");
            conn.close();
        }

    }

    private Dentista criarDentista(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(1);
        String nome = resultSet.getString(2);
        String sobrenome = resultSet.getString(3);
        String matricula = resultSet.getString(4);
        return new Dentista(id,nome,sobrenome,matricula);
    }
}

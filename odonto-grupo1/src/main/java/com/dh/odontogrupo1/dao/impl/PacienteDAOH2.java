package com.dh.odontogrupo1.dao.impl;

import com.dh.odontogrupo1.dao.ConfiguracaoJDBC;
import com.dh.odontogrupo1.dao.IDao;
import com.dh.odontogrupo1.model.Paciente;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PacienteDAOH2 implements IDao<Paciente> {

    private ConfiguracaoJDBC configuracaoJDBC;
    final static Logger log = Logger.getLogger(PacienteDAOH2.class);

    @Override
    public Paciente salvar(Paciente paciente) throws SQLException {
        log.info("Abrindo conexão");
        // TODO: 30/08/2022 - revisar endereço e data de cadastro 
        String SQLInsert = String.format("INSERT INTO paciente (nome, sobrenome, endereco, rg, dataCadastro)" +
                        " VALUES ('%s','%s', '%s', '%s', '%s')", paciente.getNome(), paciente.getSobrenome(),
                paciente.getEndereco(), paciente.getRg(), paciente.getDataCadastro());
        Connection connection = null;

        try {
            log.info("Cadastrando paciente: " + paciente.getNome());
            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver", "jdbc:h2:~/odontoclinica;INIT=RUNSCRIPT FROM 'create.sql'", "sa", "");
            connection = configuracaoJDBC.getConnection();
            Statement statement = connection.createStatement();

            statement.execute(SQLInsert, Statement.RETURN_GENERATED_KEYS);

            ResultSet resultSet = statement.getGeneratedKeys();

            if (resultSet.next())
                paciente.setId(resultSet.getInt(1));

        } catch (Exception e) {
            e.printStackTrace();
            log.error("Erro ao cadastrar dentista: " + e.getMessage());
        } finally {
            connection.close();
        }
        return paciente;
    }

    @Override
    public List<Paciente> buscarTodos() throws SQLException {
        log.info("Abrindo conexão");

        String SQLSelect = "SELECT * FROM paciente";
        Connection connection;
        Statement statement = null;
        List<Paciente> pacientes = new ArrayList<>();

        try {
            log.info("Exibindo todos os pacientes");
            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver", "jdbc:h2:~/odontoclinica;INIT=RUNSCRIPT FROM 'create.sql'", "sa", "");
            connection = configuracaoJDBC.getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQLSelect);

            while (resultSet.next()) pacientes.add(criarPaciente(resultSet));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            statement.close();
        }
        return pacientes;
    }

    @Override
    public void alterar(Paciente paciente) throws SQLException {
        log.info("Abrindo conexão");
        String sqlUpdate = String.format("UPDATE paciente SET rg = '%s' where id = '%s';",
                paciente.getRg(), paciente.getId());
        Connection connection = null;

        try {
            log.info("Alterando o RG do id: " + paciente.getId());
            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver","jdbc:h2:~/odontoclinica;INIT=RUNSCRIPT FROM 'create.sql'","sa","");
            connection = configuracaoJDBC.getConnection();
            Statement statement = connection.createStatement();

            statement.execute(sqlUpdate);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Erro ao alterar RG: " + e.getMessage());
        } finally {
            log.info("Fechando conexão");
            connection.close();
        }

    }

    @Override
    public Optional<Paciente> buscarPorId(int id) throws SQLException {
        log.debug("Abrindo uma conexão");
        Connection connection = null;
        Statement stmt = null;
        String query = String.format("SELECT * FROM paciente WHERE ID = %s",id);
        Paciente paciente = null;

        try {
            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver","jdbc:h2:~/odontoclinica;INIT=RUNSCRIPT FROM 'create.sql'","sa","");
            connection = configuracaoJDBC.getConnection();

            log.debug("Buscando paciente de id: " + id);
            stmt = connection.createStatement();
            ResultSet resultado = stmt.executeQuery(query);

            while (resultado.next()) {
                paciente = criarPaciente(resultado);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            log.error("Erro ao buscar dentista: " + e.getMessage());
        }finally {

            log.debug("Fechando a conexão");
            connection.close();
        }

        return paciente != null ? Optional.of(paciente) : Optional.empty();
    }

    @Override
    public void excluir(int id) throws SQLException {
        log.info("Abrindo Conexão");
        Connection conn = null;
        Statement stmt = null;
        String sqlDelete = String.format("DELETE FROM paciente WHERE id='%s'", id);
        try{
            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver","jdbc:h2:~/odontoclinica;INIT=RUNSCRIPT FROM 'create.sql'","sa","");
            conn = configuracaoJDBC.getConnection();
            stmt = conn.createStatement();
            stmt.execute(sqlDelete);
            log.debug("Excluindo paciente de id: " + id);
        } catch(SQLException e){
            e.printStackTrace();
            log.error("Erro ao excluir paciente: " + e.getMessage());
        } finally {
            log.info("Fechando conexão");
            conn.close();
        }
    }

    private Paciente criarPaciente(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(1);
        String nome = resultSet.getString(2);
        String sobrenome = resultSet.getString(3);
//        String endereco = resultSet.getString(4);
        String rg = resultSet.getString(5);
//        Date dataCadastro = resultSet.getDate(6);
        return new Paciente(id, nome, sobrenome, rg);
    }
}

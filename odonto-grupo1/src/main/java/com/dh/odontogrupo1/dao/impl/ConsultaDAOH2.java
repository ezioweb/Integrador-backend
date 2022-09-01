package com.dh.odontogrupo1.dao.impl;

import com.dh.odontogrupo1.dao.ConfiguracaoJDBC;
import com.dh.odontogrupo1.dao.IDao;
import com.dh.odontogrupo1.model.Consulta;
import com.dh.odontogrupo1.model.Dentista;
import com.dh.odontogrupo1.model.Paciente;
import com.dh.odontogrupo1.service.DentistaService;
import com.dh.odontogrupo1.service.PacienteService;
import com.dh.odontogrupo1.util.Util;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Configuration
public class ConsultaDAOH2 implements IDao<Consulta> {

    private ConfiguracaoJDBC configuracaoJDBC;
    final static Logger log = Logger.getLogger(ConsultaDAOH2.class);

    @Override
    public Consulta salvar(Consulta consulta) throws SQLException {
        log.info("Abrindo conexão");

        String SQLInsert = String.format("INSERT INTO consulta (id_dentista, id_paciente, datahoraconsulta)" +
                " VALUES ('%d','%d', '%s')", consulta.getDentista().getId(), consulta.getPaciente().getId(), consulta.getDataHoraConsulta());
        Connection connection = null;

        try{
            log.info("Cadastrando consulta: ");
            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver","jdbc:h2:~/odontoclinica;INIT=RUNSCRIPT FROM 'create.sql'","sa","");
            connection = configuracaoJDBC.getConnection();

            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO consulta "
                            + "(id_dentista, id_paciente, datahoraconsulta) "
                            + "VALUES "
                            + "(?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, consulta.getDentista().getId());
            statement.setInt(2, consulta.getPaciente().getId());
            statement.setTimestamp(3, Util.DateToTimeStamp(consulta.getDataHoraConsulta()));

            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();

            if(resultSet.next())
                consulta.setId(resultSet.getInt(1));

        } catch (Exception e) {
            e.printStackTrace();
            log.error("Erro ao cadastrar consulta: "+ e.getMessage());
        } finally {
            connection.close();
            log.info("Fechando conexao");
        }

        return consulta;
    }

    @Override
    public List<Consulta> buscarTodos() throws SQLException {
        log.info("Abrindo conexão");
        Connection connection = null;
        Statement statement = null;
        String sqlSelect = "SELECT * FROM consulta";

        List<Consulta> consultas = new ArrayList<>();

        try{
            log.info("Exibindo todas as consultas");
            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver","jdbc:h2:~/odontoclinica;INIT=RUNSCRIPT FROM 'create.sql'","sa","");
            connection = configuracaoJDBC.getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlSelect);

            log.debug("Buscando todas as consultas");
            while (resultSet.next()) {
                consultas.add(criarConsulta(resultSet));
            }

        } catch (SQLException e){
            e.printStackTrace();
            log.error("Erro ao buscar consultas: " + e.getMessage());
        } finally {
            connection.close();
            log.info("Fechando conexao");
        }
        return consultas;
    }

    @Override
    public void alterar(Consulta consulta) throws SQLException {
        log.info("Abrindo conexao");

        Connection connection = null;

        try {
            log.info("Alterando consulta do id: " + consulta.getId());
            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver","jdbc:h2:~/odontoclinica;INIT=RUNSCRIPT FROM 'create.sql'","sa","");
            connection = configuracaoJDBC.getConnection();


            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE consulta SET id_dentista = ?, id_paciente = ?, datahoraconsulta = ? where id = ?;",
                    Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, consulta.getDentista().getId());
            statement.setInt(2, consulta.getPaciente().getId());
            statement.setTimestamp(3, Util.DateToTimeStamp(consulta.getDataHoraConsulta()));
            statement.setInt(4, consulta.getId());

            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            log.error("Erro ao alterar consulta: " + e.getMessage());
        } finally {
            log.info("Fechando conexão");
            connection.close();
        }
    }

    @Override
    public Optional<Consulta> buscarPorId(int id) throws SQLException {
        log.debug("Abrindo uma conexão");
        Connection connection = null;
        Statement stmt = null;
        String query = String.format("SELECT * FROM consulta WHERE ID = %s",id);
        Consulta consulta = null;

        try {
            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver","jdbc:h2:~/odontoclinica;INIT=RUNSCRIPT FROM 'create.sql'","sa","");
            connection = configuracaoJDBC.getConnection();

            log.debug("Buscando consulta de id: " + id);
            stmt = connection.createStatement();
            ResultSet resultado = stmt.executeQuery(query);

            while (resultado.next()) {
                consulta = criarConsulta(resultado);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            log.error("Erro ao buscar consulta: " + e.getMessage());
        }finally {

            log.debug("Fechando a conexão");
            connection.close();
        }

        return consulta != null ? Optional.of(consulta) : Optional.empty();
    }

    @Override
    public void excluir(int id) throws SQLException {
        log.info("Abrindo Conexão");
        Connection conn = null;
        Statement stmt = null;
        String sqlDelete = String.format("DELETE FROM consulta WHERE id='%s'", id);
        try{
            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver","jdbc:h2:~/odontoclinica;INIT=RUNSCRIPT FROM 'create.sql'","sa","");
            conn = configuracaoJDBC.getConnection();
            stmt = conn.createStatement();
            stmt.execute(sqlDelete);
            log.debug("Excluindo consulta de id: " + id);
        } catch(SQLException e){
            e.printStackTrace();
            log.error("Erro ao excluir consulta: " + e.getMessage());
        } finally {
            log.info("Fechando conexão");
            conn.close();
        }

    }

    private Consulta criarConsulta(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(1);

        int idDentista = resultSet.getInt(2);
        DentistaService dentistaService = new DentistaService();
        Dentista dentista = dentistaService.buscarPorId(idDentista).get();

        int idPaciente = resultSet.getInt(3);
        PacienteService pacienteService = new PacienteService();
        Paciente paciente = pacienteService.buscarPorId(idDentista).get();

        Date dataConsulta = resultSet.getDate(4) ;

        return new Consulta(id,dentista,paciente, dataConsulta);
    }
}

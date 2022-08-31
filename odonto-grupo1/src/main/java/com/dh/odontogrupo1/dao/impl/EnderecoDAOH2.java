package com.dh.odontogrupo1.dao.impl;

import com.dh.odontogrupo1.dao.ConfiguracaoJDBC;
import com.dh.odontogrupo1.dao.IDao;
import com.dh.odontogrupo1.model.Endereco;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
        log.info("Abrindo conexao");
        String SQLSelect = "SELECT * FROM endereco;";
        Connection connection;
        Statement stmt = null;
        List<Endereco> enderecos = new ArrayList<>();

        try{
            log.info("Exibindo todos os enderecos");
            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver","jdbc:h2:~/odontoclinica;INIT=RUNSCRIPT FROM 'create.sql'","sa","");
            connection = configuracaoJDBC.getConnection();
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(SQLSelect);

            while(rs.next()){
                enderecos.add(criarEndereco(rs));
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            stmt.close();
        }

        return enderecos;
    }

    @Override
    public void alterar(Endereco endereco) throws SQLException {

        String SQLUpdate = String.format("UPDATE endereco " +
                                         "   SET rua    = '%s'" +
                                         "      ,numero = '%s'" +
                                         "      ,bairro = '%s'" +
                                         " WHERE id     = '%s' ;"
                                         ,endereco.getRua()
                                         ,endereco.getNumero()
                                         ,endereco.getBairro()
                                         ,endereco.getId());

        Connection connection = null;

        try {
            log.info("Alterando o cadastro do produto: " + endereco.getId());
            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver","jdbc:h2:~/odontoclinica;INIT=RUNSCRIPT FROM 'create.sql'","sa","");
            connection = configuracaoJDBC.getConnection();
            Statement stmt = connection.createStatement();

            stmt.execute(SQLUpdate);
        }catch (Exception e){
            e.printStackTrace();
            log.error("Erro ao alterar o endreco " + e.getMessage());

        }finally {
            log.info("fechando conexao");
            connection.close();
        }
    }

    @Override
    public Optional<Endereco> buscarPorId(int id) throws SQLException {
        log.info("Abrindo conexao");
        Connection connection = null;
        Statement stmt = null;
        String qry = String.format("SELECT * FROM endereco WHERE id = '%s'",id);
        Endereco endereco = null;
        try{
            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver","jdbc:h2:~/odontoclinica;INIT=RUNSCRIPT FROM 'create.sql'","sa","");
            connection = configuracaoJDBC.getConnection();

            log.info("buscando endereco com id: " + id);
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(qry);
            while (rs.next()){
                endereco = criarEndereco(rs);
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            log.info("fechando conexao");
            stmt.close();
        }

        return endereco != null ? Optional.of(endereco) :  Optional.empty();
    }

    @Override
    public void excluir(int id) throws SQLException {

        log.info("abrindo conexao");
        Connection connection = null;
        Statement stmt = null;
        String SQLDelete = String.format("DELETE FROM endereco WHERE id = '%s';",id);
        try{
            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver","jdbc:h2:~/odontoclinica;INIT=RUNSCRIPT FROM 'create.sql'","sa","");
            connection = configuracaoJDBC.getConnection();
            log.info("excluindo endereco com id: " + id);
            stmt = connection.createStatement();
            stmt.execute(SQLDelete);
            stmt.close();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            log.info("fechando conexao");
            connection.close();
        }

    }

    private Endereco criarEndereco(ResultSet rs) throws SQLException{
        int id = rs.getInt(1);
        String rua = rs.getString(2);
        String numero = rs.getString(3);
        String bairro = rs.getString(4);
        return new Endereco(id,rua,numero,bairro);
    }

}

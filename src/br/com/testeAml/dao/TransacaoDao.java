
package br.com.testeAml.dao;

import br.com.testeAml.entidade.Transacao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TransacaoDao {
    
    protected Connection conexao;
    protected PreparedStatement preparando;
    protected ResultSet resultSet;
    
    public void salvar(Transacao transacao) throws SQLException{
        String sql = "INSERT INTO Transacao (orgao, portador, favorecido, tipoTransacao, valor) VALUES (?,?,?,?,?)";
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparando.setString(1, transacao.getOrgao());
            preparando.setString(2, transacao.getPortador());
            preparando.setString(3, transacao.getFavorecido());
            preparando.setString(4, transacao.getTipoTransacao());
            preparando.setDouble(5, transacao.getValor());
            preparando.executeUpdate();
            preparando.getGeneratedKeys();
            
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao salvar a transacao:" + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando);
        }
    }
    
    public List<Transacao> listarTransacoes() throws SQLException{
        List<Transacao> listaTransacao = new ArrayList<>();
        String consulta = "SELECT * FROM Transacao";
        
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(consulta);
            resultSet = preparando.executeQuery();
            while (resultSet.next()) {                
                Transacao t = new Transacao();
                t.setIdTransacao(resultSet.getLong("idTransacao"));
                t.setOrgao(resultSet.getString("orgao"));
                t.setPortador(resultSet.getString("portador"));
                t.setFavorecido(resultSet.getString("favorecido"));
                t.setTipoTransacao(resultSet.getString("tipoTransacao"));
                t.setValor(resultSet.getDouble("valor"));
                listaTransacao.add(t);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar as transacoes " + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }
        return listaTransacao;
    }
    
    public List<Transacao> listarTransacoesSigilosas() throws SQLException{
        List<Transacao> listaTransacao = new ArrayList<>();
        String consulta = "SELECT idTransacao, orgao, valor FROM Transacao where tipoTransacao = 'Sigiloso' ";
        
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(consulta);
            resultSet = preparando.executeQuery();
            while (resultSet.next()) {                
                Transacao t = new Transacao();
                t.setIdTransacao(resultSet.getLong("idTransacao"));
                t.setOrgao(resultSet.getString("orgao"));
                t.setValor(resultSet.getDouble("valor"));
                listaTransacao.add(t);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar as transacoes sigilosas " + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando, resultSet);
        }
        return listaTransacao;
    }
    
    public List<Transacao> listarTransacoesSigilosasPorOrgao() throws SQLException{
        List<Transacao> listaTransacao = new ArrayList<>();
        String consulta = "SELECT idTransacao, orgao, Count(*) FROM transacao where tipoTransacao = 'Sigiloso' group by orgao having Count(*) > 1 order by Count(*) DESC";
        
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(consulta);
            resultSet = preparando.executeQuery();
            while (resultSet.next()) {                
                Transacao t = new Transacao();
                t.setIdTransacao(resultSet.getLong("idTransacao"));
                t.setOrgao(resultSet.getString("orgao"));
                t.setCount(resultSet.getInt("count(*)"));
                listaTransacao.add(t);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar as transacoes sigilosas por orgao " + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando);
        }
        return listaTransacao;
    }
    
    public List<Transacao> listarTransacoesSigilosasPorFavorecido() throws SQLException{
        List<Transacao> listaTransacao = new ArrayList<>();
        String consulta = "SELECT idTransacao, favorecido, Count(*) FROM transacao group by favorecido having Count(*) > 1 order by Count(*) DESC ";
        
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(consulta);
            resultSet = preparando.executeQuery();
            while (resultSet.next()) {                
                Transacao t = new Transacao();
                t.setIdTransacao(resultSet.getLong("idTransacao"));
                t.setFavorecido(resultSet.getString("favorecido"));
                t.setCount(resultSet.getInt("count(*)"));
                if (!t.getFavorecido().equals("NAO SE APLICA") && !t.getFavorecido().equals("SEM INFORMACAO") && !t.getFavorecido().equals("Sigiloso")) {
                    listaTransacao.add(t);
                }
                
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar as transacoes por favorecidos " + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando);
        }
        return listaTransacao;
    }
    
    public List<Transacao> listarTransacoesSaque() throws SQLException{
        List<Transacao> listaTransacao = new ArrayList<>();
        String consulta = "SELECT idTransacao, portador, orgao, Count(*) FROM transacao WHERE tipoTransacao = 'saque' group by portador having Count(*) > 1 order by Count(*) DESC";
        
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(consulta);
            resultSet = preparando.executeQuery();
            while (resultSet.next()) {                
                Transacao t = new Transacao();
                t.setIdTransacao(resultSet.getLong("idTransacao"));
                t.setPortador(resultSet.getString("portador"));
                t.setOrgao(resultSet.getString("orgao"));
                t.setCount(resultSet.getInt("count(*)"));
                listaTransacao.add(t);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar as transacoes de saque " + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando);
        }
        return listaTransacao;
    }
    
    public List<Transacao> listarTransacoesSigilosasValorPorOrgao(String orgao) throws SQLException{
        List<Transacao> listaTransacao = new ArrayList<>();
        String consulta = "SELECT idTransacao, orgao, valor, FROM transacao WHERE orgao = ? and tipoTransacao = 'sigiloso' ";
        
        try {
            conexao = FabricaConexao.abrirConexao();
            preparando = conexao.prepareStatement(consulta);
            preparando.setString(1, orgao);
            resultSet = preparando.executeQuery();
            while (resultSet.next()) {                
                Transacao t = new Transacao();
                t.setIdTransacao(resultSet.getLong("idTransacao"));
                t.setOrgao(resultSet.getString("orgao"));
                t.setValor(resultSet.getDouble("valor"));
                listaTransacao.add(t);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar as transacoes de saque " + e.getMessage());
        } finally {
            FabricaConexao.fecharConexao(conexao, preparando);
        }
        return listaTransacao;
    }
    
}

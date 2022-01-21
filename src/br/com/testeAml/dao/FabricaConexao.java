
package br.com.testeAml.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class FabricaConexao {
    
    public static Connection abrirConexao() throws SQLException{
        String senhaDoBancoDeDadosMySql = "";
        
        Connection driver = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            driver = DriverManager.getConnection("jdbc:mysql://localhost:3306/testeAml"
            +"?useTimezone=true&serverTimezone=America/Sao_Paulo&zeroDateTimeBehavior=convertToNull",
                    "root", senhaDoBancoDeDadosMySql);
        } catch (Exception e) {
            System.err.println("Erro ao conectar com o banco" + e.getMessage());
        }
        return driver;
    }
    public static void fecharConexao(Connection conn, Statement psmt, ResultSet rs) throws SQLException{
        rs.close();
        conn.close();
        psmt.close();
    }
    public static void fecharConexao(Connection conn, Statement psmt) throws SQLException{
        conn.close();
        psmt.close();
    }
    
}

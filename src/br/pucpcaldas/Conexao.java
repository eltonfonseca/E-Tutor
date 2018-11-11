/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pucpcaldas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Elton
 */
public class Conexao {
    
    private Connection con;
    private Statement st;
    private ResultSet rs;
    private static Conexao conexao;
    private final String url;
    private final String driver;
    private final String usuario;
    private final String senha;
    
    public Conexao() {
        
        this.url = "jdbc:mysql://localhost/tutor?useTimezone=true&serverTimezone=UTC"; 
        this.usuario = "root";
        this.senha = "root";
        this.driver = "com.mysql.cj.jdbc.Driver";
    }
    
    public void abrirConexao() {
        try {
            Class.forName(driver).newInstance();
            this.con = DriverManager.getConnection(url, usuario, senha);
            
            System.out.println("Deu certo");
        }
        catch(ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            System.out.println("Erro na conexao com o banco!" + e);
        }
    }
    
    public void fecharConexao() throws SQLException {
        if(this.con != null)
            this.con.close();
    }
    
    public static Conexao getConexao() {
        if(conexao == null)
            conexao = new Conexao();
        return conexao;
    }
    
    public Connection getCon() {
        return this.con;
    }
    
    public Statement getStatement() {
        return this.st;
    }
    
    public ResultSet getResultSet() {
        return this.rs;
    }
    
    public void setStatement(Statement st) {
        this.st = st;
    }
    
    public void setResultSet(ResultSet rs) {
        this.rs = rs;
    }
}

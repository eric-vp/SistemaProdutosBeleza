/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Pichau
 */
public class Conexao {
    private Connection conn;
    private String url = "jdbc:mysql://localhost:3306/produtos_beleza";
    private String user = "root";
    private String password = "1234";
    
    public Connection conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");            
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conexao realizada com sucesso");
        } catch (ClassNotFoundException ex) {
            System.out.println("O Driver não está disponível na biblioteca");
        } catch (SQLException ex) {
            System.out.println("Sintaxe de comando invalida ");
        } 
        return conn;
    }
    
        public void desconectar() {
        try {
            if(conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Conexão com banco de dados encerrada.");
            }
        } catch (SQLException ex) {
            System.out.println("Nao foi possivel desconectar do banco dados.");
        }
    }
}

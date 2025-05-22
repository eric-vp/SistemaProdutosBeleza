/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usuario;


import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Pichau
 */
public class UsuarioBD {
    public static Usuario validarUsusario(Usuario usuario) {
        Usuario usuarioEncontrado = null;
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        
        String sql = "SELECT * FROM usuarios WHERE login = ? AND senha = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getLogin());
            stmt.setString(2, usuario.getSenha());
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuarioEncontrado = new Usuario();
                    usuarioEncontrado.setId(rs.getInt("id"));
                    usuarioEncontrado.setNome(rs.getString("nome"));                    
                    usuarioEncontrado.setLogin(rs.getString("login"));
                    usuarioEncontrado.setSenha(rs.getString("senha"));
                    usuarioEncontrado.setTipo(rs.getString("tipo"));                    
                }
            }            
        } catch (SQLException e) {
            System.out.println("Erro ao validar usuário: " + e.getMessage());
        }
        
        return usuarioEncontrado;
    }
}

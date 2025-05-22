/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package relatorio;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import javax.swing.JOptionPane;

/**
 *
 * @author Pichau
 */
public class RelatorioDAO {
    public Relatorio gerarRelatorio(LocalDate inicio, LocalDate fim) {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        
        String sql = """
            SELECT COUNT(*) AS total_vendas, SUM(total_pagamento) AS total_pago
            FROM vendas
            WHERE DATE(data) BETWEEN ? AND ?
        """;
        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, java.sql.Date.valueOf(inicio));
            ps.setDate(2, java.sql.Date.valueOf(fim));

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int totalVendas = rs.getInt("total_vendas");
                double totalPago = rs.getDouble("total_pago");

                return new Relatorio(totalVendas, totalPago);
            } 
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao gerar relatório: " + e.getMessage());
        }
        
        return new Relatorio(0, 0.0);
    }
}

package venda;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class VendaDAO {
    public void cadastrar(Venda venda) {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        
        String sql = "insert into vendas (status, cliente_id, data, total_pagamento) values (?,?,?,?)";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, venda.getStatus());
            ps.setInt(2, venda.getClienteId());
            ps.setDate(3, new java.sql.Date(venda.getData().getTime()));
            ps.setDouble(4, venda.getTotalPagamento());         
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Venda inserida com sucesso!");
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro no acesso ao Banco de Dados : "+ sqle.getMessage());
        }
    }
    
    public void editar(Venda venda) {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        
        String sql = "update vendas set status = ?, cliente_id = ?, data = ?, total_pagamento = ? where id = ?";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, venda.getStatus());
            ps.setInt(2, venda.getClienteId());  
            ps.setDate(3, new java.sql.Date(venda.getData().getTime()));
            ps.setDouble(4, venda.getTotalPagamento()); 
            ps.setInt(5, venda.getId());
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Venda editada com sucesso!");
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro no acesso ao Banco de Dados : "+ sqle.getMessage());
        }
    }
    
    public void excluir(Venda venda) {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();        
        String sql = "delete from vendas where id = ?";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, venda.getId());
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Venda deletada com sucesso!");    
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro no acesso ao Banco de Dados : "+ sqle.getMessage());
        }
    }
    
    public List<Venda> listar() {
        List<Venda> lista = new ArrayList<>();
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        
        if (conn == null) {
            System.err.println("Erro de conexão ao listar vendas.");
            return lista;
        }
        
        String sql = "select * from vendas";
        
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Venda v = new Venda();
                v.setId(rs.getInt("id"));
                v.setStatus(rs.getString("status"));
                v.setClienteId(rs.getInt("cliente_id"));
                v.setData(rs.getDate("data"));
                v.setTotalPagamento(rs.getDouble("total_pagamento"));
                lista.add(v);
            }           
        } catch (SQLException e) {
            System.err.println("Erro ao listar produtos: " + e.getMessage());
        } finally {
            conexao.desconectar();
        }
        
        return lista;
    }
    
    public List<Venda> pesquisar(String pesquisa) {
        List<Venda> lista = new ArrayList<>();
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        
        if (conn == null) {
            System.err.println("Erro de conexão ao listar fornecedores.");
            return lista;
        }
        
        String sql = "select * from vendas where status like ?";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + pesquisa + "%");            
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Venda v = new Venda();
                v.setId(rs.getInt("id"));
                v.setStatus(rs.getString("status"));
                v.setClienteId(rs.getInt("cliente_id"));
                v.setData(rs.getDate("data"));
                v.setTotalPagamento(rs.getDouble("total_pagamento"));
                lista.add(v);
            }         
        } catch (SQLException e) {
            System.err.println("Erro ao listar vendas: " + e.getMessage());
        } finally {
            conexao.desconectar();
        }
        
        return lista;
    }
}

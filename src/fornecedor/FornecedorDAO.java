package fornecedor;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class FornecedorDAO {
    public void cadastrar(Fornecedor fornecedor) {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        
        String sql = "insert into fornecedores (nome, cnpj, telefone) values (?,?,?)";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, fornecedor.getNome());
            ps.setString(2, fornecedor.getCnpj());
            ps.setString(3, fornecedor.getTelefone());            
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Fornecedor inserido com sucesso!");
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro no acesso ao Banco de Dados : "+ sqle.getMessage());
        }
    }
    
    public void editar(Fornecedor fornecedor) {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        
        String sql = "update fornecedores set nome = ?, cnpj = ?, telefone = ? where id = ?";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, fornecedor.getNome());
            ps.setString(2, fornecedor.getCnpj());
            ps.setString(3, fornecedor.getTelefone());    
            ps.setInt(4, fornecedor.getId());            
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Fornecedor editado com sucesso!");
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro no acesso ao Banco de Dados : "+ sqle.getMessage());
        }
    }
    
    public void excluir(Fornecedor fornecedor) {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();        
        String sql = "delete from fornecedores where id = ?";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, fornecedor.getId());
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Fornecedor deletado com sucesso!");    
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro no acesso ao Banco de Dados : "+ sqle.getMessage());
        }
    }
    
    public List<Fornecedor> listar() {
        List<Fornecedor> lista = new ArrayList<>();
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        
        if (conn == null) {
            System.err.println("Erro de conexão ao listar fornecedores.");
            return lista;
        }
        
        String sql = "select * from fornecedores";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Fornecedor f = new Fornecedor();
                f.setId(rs.getInt("id"));
                f.setNome(rs.getString("nome"));
                f.setCnpj(rs.getString("cnpj"));
                f.setTelefone(rs.getString("telefone"));
                lista.add(f);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar fornecedores: " + e.getMessage());
        } finally {
            conexao.desconectar();
        }
        
        return lista;
    }
    
    public List<Fornecedor> pesquisar(String pesquisa) {
        List<Fornecedor> lista = new ArrayList<>();
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        
        if (conn == null) {
            System.err.println("Erro de conexão ao listar fornecedores.");
            return lista;
        }
        
        String sql = "select * from fornecedores where nome like ?";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + pesquisa + "%");            
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Fornecedor f = new Fornecedor();
                f.setId(rs.getInt("id"));
                f.setNome(rs.getString("nome"));
                f.setCnpj(rs.getString("cnpj"));
                f.setTelefone(rs.getString("telefone"));
                lista.add(f);
            }         
        } catch (SQLException e) {
            System.err.println("Erro ao listar fornecedores: " + e.getMessage());
        } finally {
            conexao.desconectar();
        }
        
        return lista;
    }
}

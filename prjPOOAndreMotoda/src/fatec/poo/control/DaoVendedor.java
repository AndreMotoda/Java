package fatec.poo.control;

import fatec.poo.model.Vendedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author andremotoda
 */
public class DaoVendedor {
    
    private Connection conn;
    
    public DaoVendedor(Connection conn){
        this.conn = conn;
    }
    
    public Vendedor consultar(String cpf){
        Vendedor vendedor = null;
        
        PreparedStatement ps = null;
        
        try{
            ps = conn.prepareStatement("SELECT * FROM Vendedor WHERE CPF = ?");
            ps.setString(1, cpf);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                vendedor = new Vendedor(cpf,
                                    rs.getString("Nome"),
                                    rs.getDouble("SalarioBase"));
                vendedor.setEndereco(rs.getString("Endereco"));
                vendedor.setCidade(rs.getString("Cidade"));
                vendedor.setUf(rs.getString("Uf"));
                vendedor.setCep(rs.getString("Cep"));
                vendedor.setDDD(rs.getString("DDD"));
                vendedor.setTelefone(rs.getString("Telefone"));
                vendedor.setTaxaComissao(rs.getDouble("TaxaComissao"));
            }
        }
        catch(SQLException ex){ 
             System.out.println(ex.toString());   
        }
        return vendedor;
    }
    
    public void incluir(Vendedor vendedor){
        PreparedStatement ps=null;
        try{
            ps = conn.prepareStatement("INSERT INTO Vendedor(Cpf, Nome, SalarioBase, Endereco, "
                    + "Cidade, Uf, Cep, DDD, Telefone, TaxaComissao) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, vendedor.getCpf());
            ps.setString(2, vendedor.getNome());
            ps.setDouble(3, vendedor.getSalarioBase());
            ps.setString(4, vendedor.getEndereco());
            ps.setString(5, vendedor.getCidade());
            ps.setString(6, vendedor.getUf());
            ps.setString(7, vendedor.getCep());
            ps.setString(8, vendedor.getDDD());
            ps.setString(9, vendedor.getTelefone());
            ps.setDouble(10, vendedor.getTaxaComissao());
            
            ps.execute();
        }
        catch(SQLException ex){
             System.out.println(ex.toString());
        }
    }
    
    public void alterar(Vendedor vendedor){
        PreparedStatement ps=null;
        try{
            ps = conn.prepareStatement("UPDATE Vendedor SET Nome = ?, SalarioBase = ?, Endereco = ?, "
                    + "Cidade = ?, Uf = ?, Cep = ?, DDD = ?, Telefone = ?, TaxaComissao = ? WHERE Cpf = ?");
            
            ps.setString(1, vendedor.getNome());
            ps.setDouble(2, vendedor.getSalarioBase());
            ps.setString(3, vendedor.getEndereco());
            ps.setString(4, vendedor.getCidade());
            ps.setString(5, vendedor.getUf());
            ps.setString(6, vendedor.getCep());
            ps.setString(7, vendedor.getDDD());
            ps.setString(8, vendedor.getTelefone());
            ps.setDouble(9, vendedor.getTaxaComissao());
            ps.setString(10, vendedor.getCpf());
            
            ps.execute();
        }
        catch(SQLException ex){
            System.out.println(ex.toString());
        }
    }
    
    public void excluir(Vendedor vendedor){
        PreparedStatement ps=null;
        try{
            ps = conn.prepareStatement("DELETE FROM Vendedor WHERE Cpf = ?");
            ps.setString(1, vendedor.getCpf());
            
            ps.execute();
        }
        catch(SQLException ex){
            System.out.println(ex.toString());
        }
    }
}

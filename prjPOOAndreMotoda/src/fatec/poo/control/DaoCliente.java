package fatec.poo.control;

import fatec.poo.model.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author andremotoda
 */
public class DaoCliente {
    private Connection conn;
    
    public DaoCliente(Connection conn){
        this.conn = conn;
    }
    
    public Cliente consultar(String cpf){
        Cliente cliente = null;
        
        PreparedStatement ps = null;
        
        try{
            ps = conn.prepareStatement("SELECT * FROM Cliente WHERE CPF = ?");
            ps.setString(1, cpf);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                cliente = new Cliente(cpf,
                                    rs.getString("Nome"),
                                    rs.getDouble("LimiteCredito"));
                cliente.setEndereco(rs.getString("Endereco"));
                cliente.setCidade(rs.getString("Cidade"));
                cliente.setUf(rs.getString("Uf"));
                cliente.setCep(rs.getString("Cep"));
                cliente.setDDD(rs.getString("DDD"));
                cliente.setTelefone(rs.getString("Telefone"));
                cliente.setLimiteDisponivel(rs.getDouble("LimiteDisponivel"));
            }
        }
        catch(SQLException ex){ 
             System.out.println(ex.toString());
        }
        return cliente;
    }
    
    public void incluir(Cliente cliente){
        PreparedStatement ps=null;
        try{
            ps = conn.prepareStatement("INSERT INTO Cliente(Cpf, Nome, LimiteCredito, Endereco, "
                    + "Cidade, Uf, Cep, DDD, Telefone, LimiteDisponivel) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, cliente.getCpf());
            ps.setString(2, cliente.getNome());
            ps.setDouble(3, cliente.getLimiteCredito());
            ps.setString(4, cliente.getEndereco());
            ps.setString(5, cliente.getCidade());
            ps.setString(6, cliente.getUf());
            ps.setString(7, cliente.getCep());
            ps.setString(8, cliente.getDDD());
            ps.setString(9, cliente.getTelefone());
            ps.setDouble(10, cliente.getLimiteCredito());
            
            ps.execute();
        }
        catch(SQLException ex){
             System.out.println(ex.toString());
        }
    }
    
    public void alterar(Cliente cliente){
        PreparedStatement ps=null;
        try{
            ps = conn.prepareStatement("UPDATE Cliente SET Nome = ?, LimiteCredito = ?, Endereco = ?, "
                    + "Cidade = ?, Uf = ?, Cep = ?, DDD = ?, Telefone = ?, LimiteDisponivel = ? WHERE Cpf = ?");
            
            ps.setString(1, cliente.getNome());
            ps.setDouble(2, cliente.getLimiteCredito());
            ps.setString(3, cliente.getEndereco());
            ps.setString(4, cliente.getCidade());
            ps.setString(5, cliente.getUf());
            ps.setString(6, cliente.getCep());
            ps.setString(7, cliente.getDDD());
            ps.setString(8, cliente.getTelefone());
            ps.setDouble(9, cliente.getLimiteDisponivel());
            ps.setString(10, cliente.getCpf());
            
            ps.execute();
        }
        catch(SQLException ex){
            System.out.println(ex.toString());
        }
    }
    
    public void excluir(Cliente cliente){
        PreparedStatement ps=null;
        try{
            ps = conn.prepareStatement("DELETE FROM Cliente WHERE Cpf = ?");
            ps.setString(1, cliente.getCpf());
            
            ps.execute();
        }
        catch(SQLException ex){
            System.out.println(ex.toString());
        }
    }
}

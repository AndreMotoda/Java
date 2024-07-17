package fatec.poo.control;

import fatec.poo.model.Pedido;
import fatec.poo.control.DaoCliente;
import fatec.poo.control.DaoVendedor;
import fatec.poo.model.Cliente;
import fatec.poo.model.Vendedor;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author andremotoda
 */
public class DaoPedido {
    
    private Connection conn;
    
    public DaoPedido(Connection conn){
        this.conn = conn;
    }
    
    public Pedido consultar(String numero){
        Pedido pedido = null;
        
        PreparedStatement ps = null;
        
        try{
            ps = conn.prepareStatement("SELECT * FROM Pedido WHERE NmrPedido = ?");
            ps.setString(1, numero);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                pedido = new Pedido(numero);
                pedido.setDataEmissao(rs.getString("DtEmissao"));
                pedido.setValor(rs.getDouble("Valor"));
                pedido.setCliente(new DaoCliente(conn).consultar(rs.getString("CpfCliente_Pedido")));
                pedido.setVendedor(new DaoVendedor(conn).consultar(rs.getString("CpfVendedor_Pedido")));
            }
        }
        catch(SQLException ex){ 
             System.out.println(ex.toString());   
        }
        return pedido;
    }
    
    public void incluir(Pedido pedido){
        PreparedStatement ps=null;
        try{
            ps = conn.prepareStatement("INSERT INTO Pedido(NmrPedido, DtEmissao, Valor, CpfCliente_Pedido, "
                    + "CpfVendedor_Pedido) VALUES(?, ?, ?, ?, ?)");
            ps.setString(1, pedido.getNumero());
	    ps.setString(2, pedido.getDataEmissao());
            ps.setDouble(3, pedido.getValor());
            ps.setString(4, pedido.getCliente().getCpf());
            ps.setString(5, pedido.getVendedor().getCpf());

            ps.execute();

            DaoCliente daoCliente = new DaoCliente(conn);
            Cliente cliente = daoCliente.consultar(pedido.getCliente().getCpf());
            cliente.addPedido(pedido);
            daoCliente.alterar(cliente);
            DaoVendedor daoVendedor = new DaoVendedor(conn);
	    Vendedor vendedor = daoVendedor.consultar(pedido.getVendedor().getCpf());
            vendedor.addPedido(pedido);
            daoVendedor.alterar(vendedor);
        }
        catch(SQLException ex){
             System.out.println(ex.toString());
        }
    }
    
    public void alterar(Pedido pedido){
        DaoCliente daoCliente = new DaoCliente(conn);
        PreparedStatement ps=null;
        try{
            
            double diferenca = (new DaoPedido(conn).consultar(pedido.getNumero())).getValor() - pedido.getValor();

            ps = conn.prepareStatement("UPDATE Pedido SET DtEmissao = ?, Valor = ?, CpfCliente_Pedido = ?, "
                    + "CpfVendedor_Pedido = ? WHERE NmrPedido = ?");
            
            ps.setString(1, pedido.getDataEmissao());
            ps.setDouble(2, pedido.getValor());
            ps.setString(3, pedido.getCliente().getCpf());
            ps.setString(4, pedido.getVendedor().getCpf());
            ps.setString(5, pedido.getNumero());
            
            ps.execute();

            pedido.getCliente().setLimiteDisponivel(pedido.getCliente().getLimiteDisponivel() + diferenca);
            daoCliente.alterar(pedido.getCliente());
        }
        catch(SQLException ex){
            System.out.println(ex.toString());
        }
    }
    
    public void excluir(Pedido pedido){
        PreparedStatement ps=null;
        try{
            ps = conn.prepareStatement("DELETE FROM Pedido WHERE NmrPedido = ?");
            ps.setString(1, pedido.getNumero());

            ps.execute();	    

            DaoCliente daoCliente = new DaoCliente(conn);
            Cliente cliente = daoCliente.consultar(pedido.getCliente().getCpf());
            cliente.removePedido(pedido);
            daoCliente.alterar(cliente);
            DaoVendedor daoVendedor = new DaoVendedor(conn);
	    Vendedor vendedor = daoVendedor.consultar(pedido.getVendedor().getCpf());
            vendedor.removePedido(pedido);
            daoVendedor.alterar(vendedor);
        }
        catch(SQLException ex){
            System.out.println(ex.toString());
        }
    }
}

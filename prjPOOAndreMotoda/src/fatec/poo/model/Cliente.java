package fatec.poo.model;

import fatec.poo.model.Pedido;
import java.util.ArrayList;

/**
 *
 * @author andremotoda
 */
public class Cliente extends Pessoa{
    private double limiteCredito;
    private double limiteDisponivel;
    private ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
    
    public Cliente(String cpf, String nome, double limiteCredito){
        super(cpf, nome);
        this.limiteCredito = limiteCredito;
    }

    /**
     * @return the limiteCredito
     */
    public double getLimiteCredito() {
        return limiteCredito;
    }

    /**
     * @param limiteCredito the limiteCredito to set
     */
    public void setLimiteCredito(double limiteCredito) {
        this.limiteCredito = limiteCredito;
    }

    /**
     * @return the limiteDisponivel
     */
    public double getLimiteDisponivel() {
        return limiteDisponivel;
    }

    public void setLimiteDisponivel(double limiteDisponivel) {
        this.limiteDisponivel = limiteDisponivel;
    }
    
    public void removePedido(Pedido pedido){
        pedidos.remove(pedido);
        limiteDisponivel = limiteDisponivel + pedido.getValor();
    }
    
    public void addPedido(Pedido pedido){
        pedidos.add(pedido);
        limiteDisponivel = limiteDisponivel - pedido.getValor();
        pedido.setCliente(this);
    }
}
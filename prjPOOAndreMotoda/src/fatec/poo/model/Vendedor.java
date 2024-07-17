package fatec.poo.model;

import java.util.ArrayList;

/**
 *
 * @author andremotoda
 */
public class Vendedor extends Pessoa{
    private double salarioBase;
    private double taxaComissao;
    private ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
    
    public Vendedor(String cpf, String nome, double salarioBase){
        super(cpf, nome);
        this.salarioBase = salarioBase;
    }

    /**
     * @return the salarioBase
     */
    public double getSalarioBase() {
        return salarioBase;
    }

    /**
     * @param salarioBase the salarioBase to set
     */
    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }

    /**
     * @return the taxaComissao
     */
    public double getTaxaComissao() {
        return taxaComissao;
    }

    /**
     * @param taxaComissao the taxaComissao to set
     */
    public void setTaxaComissao(double taxaComissao) {
        this.taxaComissao = taxaComissao;
    }
    
    public void addPedido(Pedido pedido){
        pedidos.add(pedido);
        pedido.setVendedor(this);
    }
    
    public void removePedido(Pedido pedido){
        pedidos.remove(pedido);
    }
}

package fatec.poo.model;

import fatec.poo.model.Vendedor;
import fatec.poo.model.Cliente;
import java.util.Date;

/**
 *
 * @author andremotoda
 */
public class Pedido {
    private String numero;
    private String dataEmissao;
    private double valor;
    private Vendedor vendedor;
    private Cliente cliente;
    
    public Pedido (String numero){
        this.numero = numero;
    }

    /**
     * @return the dataEmissao
     */
    public String getDataEmissao() {
        return dataEmissao;
    }

    /**
     * @param dataEmissao the dataEmissao to set
     */
    public void setDataEmissao(String dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    /**
     * @return the valor
     */
    public double getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(double valor) {
        this.valor = valor;
    }

    /**
     * @return the vendedor
     */
    public Vendedor getVendedor() {
        return vendedor;
    }

    /**
     * @param vendedor the vendedor to set
     */
    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getNumero(){
        return numero;
    }
}
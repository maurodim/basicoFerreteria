/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacion.clientes;

/**
 *
 * @author hernan
 */
public class FormasDePago {
    private int numeroFormaDePago;
    private String descripcionFormaDePago;

    public int getNumeroFormaDePago() {
        return numeroFormaDePago;
    }

    public void setNumeroFormaDePago(int numeroFormaDePago) {
        this.numeroFormaDePago = numeroFormaDePago;
    }

    public String getDescripcionFormaDePago() {
        return descripcionFormaDePago;
    }

    public void setDescripcionFormaDePago(String descripcionFormaDePago) {
        this.descripcionFormaDePago = descripcionFormaDePago;
    }

    public FormasDePago() {
        this.numeroFormaDePago = 1;
        this.descripcionFormaDePago = "CONTADO";
    }
    
}

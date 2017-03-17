/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacion.clientes;

/**
 *
 * @author hernan
 */
public class Comprobante {
    private int numeroComprobante;
    private String descripcionComprobante;

    public void setNumeroComprobante(int numeroComprobante) {
        this.numeroComprobante = numeroComprobante;
    }

    public void setDescripcionComprobante(String descripcionComprobante) {
        this.descripcionComprobante = descripcionComprobante;
    }

    public int getNumeroComprobante() {
        return numeroComprobante;
    }

    public String getDescripcionComprobante() {
        return descripcionComprobante;
    }

    public Comprobante() {
        this.numeroComprobante = 1;
        this.descripcionComprobante = "factura BU";
    }
    
}

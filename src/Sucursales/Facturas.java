/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sucursales;

/**
 *
 * @author mauro
 */
public class Facturas {
    private Integer numero;
    private Integer numeroDeCliente;
    private String nombredeCliente;
    private String fechaEmision;
    private String codigoArticulo;
    private Double cantidadArticulo;
    private Double precioUnitario;
    private Double totalBruto;
    private Double totalIva;
    private Double totalIb;
    private Double totalNeto;

    public Facturas() {
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getNumeroDeCliente() {
        return numeroDeCliente;
    }

    public void setNumeroDeCliente(Integer numeroDeCliente) {
        this.numeroDeCliente = numeroDeCliente;
    }

    public String getNombredeCliente() {
        return nombredeCliente;
    }

    public void setNombredeCliente(String nombredeCliente) {
        this.nombredeCliente = nombredeCliente;
    }

    public String getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getCodigoArticulo() {
        return codigoArticulo;
    }

    public void setCodigoArticulo(String codigoArticulo) {
        this.codigoArticulo = codigoArticulo;
    }

    public Double getCantidadArticulo() {
        return cantidadArticulo;
    }

    public void setCantidadArticulo(Double cantidadArticulo) {
        this.cantidadArticulo = cantidadArticulo;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Double getTotalBruto() {
        return totalBruto;
    }

    public void setTotalBruto(Double totalBruto) {
        this.totalBruto = totalBruto;
    }

    public Double getTotalIva() {
        return totalIva;
    }

    public void setTotalIva(Double totalIva) {
        this.totalIva = totalIva;
    }

    public Double getTotalIb() {
        return totalIb;
    }

    public void setTotalIb(Double totalIb) {
        this.totalIb = totalIb;
    }

    public Double getTotalNeto() {
        return totalNeto;
    }

    public void setTotalNeto(Double totalNeto) {
        this.totalNeto = totalNeto;
    }
    
    
    
    
}

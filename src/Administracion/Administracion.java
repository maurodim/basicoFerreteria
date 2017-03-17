/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Administracion;

import Depositos.Depositos;
import Sucursales.Sucursales;

/**
 *
 * @author mauro
 */
public class Administracion {
    private Depositos deposito;
    private Sucursales sucursal;
    private TipoComprobante tipoDeComprobante;
    

    public Administracion(Depositos deposito, Sucursales sucursal) {
        this.deposito = deposito;
        this.sucursal = sucursal;
    }

    public Administracion() {
    }

    public Depositos getDeposito() {
        return deposito;
    }

    public void setDeposito(Depositos deposito) {
        this.deposito = deposito;
    }

    public Sucursales getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursales sucursal) {
        this.sucursal = sucursal;
    }
    
    
    
}

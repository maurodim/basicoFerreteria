/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import Sucursales.Sucursales;
import Depositos.Depositos;

/**
 *
 * @author mauro
 * OBJETO QUE CONTIENE LOS PARAMETROS DE CONFIGURACION DE LA EMPRESA QUE EJECUTA EL SISTEMA
 * 
 */
public class Empresa {
    private Depositos deposito;
    private Sucursales sucursales;

    public Empresa() {
    }

    public Depositos getDeposito() {
        return deposito;
    }

    public void setDeposito(Depositos deposito) {
        this.deposito = deposito;
    }

    public Sucursales getSucursales() {
        return sucursales;
    }

    public void setSucursales(Sucursales sucursales) {
        this.sucursales = sucursales;
    }
    
    
}

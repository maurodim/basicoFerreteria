/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesPrograma;

import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultListModel;

/**
 *
 * @author mauro
 */
public interface Cajeables {
    public Object AbrirCaja(Object caja);
    public Boolean CerrarCaja(Object caja);
    public Double SaldoDeCaja(ArrayList listadoBilletes);
    public Boolean NuevoMovimiento(Object factura);
    public Object ModificarMovimiento(Integer idMovimiento);
    public Boolean EliminarMovimiento(Integer idMovimiento,Integer idComprobante,Integer tipoComprobante);
    public Object ArquearCaja(Object caja);
    public Boolean VerificarCaja(int numeroDeUsuario,int numeroDeSucursal,String fecha);
    public Object CargarCaja(int numeroDeUsuario, int numeroDeSucursal, String fecha);
    public Object NuevoGasto(Object factura);
    public DefaultListModel LeerComprobante(Integer idComprobante,Integer tipoComprobante,Integer tipoMovimiento);
}

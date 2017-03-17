/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author mauro
 */
public interface Comprobable {
    public Integer nuevoComprobante(Object objeto);
    public Boolean agregarItem(Object item);
    public Boolean modificarComprobante(Object objeto);
    public Boolean eliminarComprobante(Object objeto);
    public Object leerComprobante(Integer numero);
    public ArrayList listarComprobantesPorFecha(Date fecha);
    public ArrayList listarComprobantesPorDeposito(Integer numeroDeposito);
    public ArrayList listarComprobantesPorProveedor(Integer numeroProveedor);
    
    
}

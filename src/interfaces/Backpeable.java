/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.HashMap;
import java.util.Hashtable;

/**
 *
 * @author mauro
 */
public interface Backpeable {
    public Object leerUsuarios(String nombre,String clave);
    public Hashtable leerArticulos();
    public Hashtable leerProveedores();
    public Hashtable leerClientes();
    public Object leerDeposito();
    public Boolean backapearObjetos();
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.ArrayList;

/**
 *
 * @author mauro
 */
public interface UsuariosMovibles {
    public Boolean registrarIngreso(Object objeto);
    public Boolean registrarSalida(Object objeto);
    public Object validarClave(String usuario,String clave);// valida clave y carga objeto usuario
    public Boolean modificarDatosUsuario(Object objeto);
    public Boolean nuevoUsuario(Object objeto);
    public ArrayList listarUsuarios();
    public Object cargarUsuario(Integer numeroUsuario);//Se realiza la carga por el numero, se entiende que sale de un listado
    
}

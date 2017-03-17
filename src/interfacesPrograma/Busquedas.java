/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesPrograma;

import java.util.ArrayList;

/**
 *
 * @author Administrador
 */
public interface Busquedas {
    public ArrayList buscar(String nombre);
    public ArrayList filtrar(String numeroCliente,String nombreCliente);
    public ArrayList listar(String cliente);
    public void marcarContactado(Integer item);
    public void modificarDatosCliente(Object cliente);    
}

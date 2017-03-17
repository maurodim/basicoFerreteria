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
public interface Personalizable {
    public Boolean agregar(Object objeto);
    public Boolean modificar(Object objeto);
    public Boolean eliminar(Object objeto);
    public Object buscarPorNumero(Integer id);
    public Object buscarPorNombre(String nombre);
    public Object buscarPorCuit(String cuit);
    public ArrayList listar();
    public ArrayList listarPorNombre(String nombre);
    public ArrayList listarPorCuit(String cuit);
}

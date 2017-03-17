/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesPrograma;

import java.util.ArrayList;

/**
 *
 * @author hernan
 */
public interface Facturar {
    public Boolean guardar(Object oob);
    public Boolean modificar(Object oob);
    public Boolean nuevo(Object oob);
    public ArrayList listar();
    public Object leer(Object oob);
    public void imprimirComprobante(int tipoComprobante,Object oob);
    public ArrayList listadoBusqueda(String criterio);
    public ArrayList listadoBusquedaMayorista(String criterio);
    public Boolean guardarNuevoCliente(Object cliente);
    public Boolean modificarDatosDelCliente(Object cliente);
    public ArrayList listarClientes(String nombre);
    public Object cargarPorCodigoDeBarra(String codigoDeBarra);
    public Object cargarPorCodigoDeBarraMayorista(String codigoDeBarra);
    public Integer leerNumeroDeComprobanteSiguiente(Integer numeroComprobante);
    public Object cargarPorCodigoAsignado(Integer id);
    
}

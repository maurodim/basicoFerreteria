/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mauro
 */
public interface Articulable {
    public Boolean AltaObjeto(Object objeto);
    public Boolean ModificaionObjeto(Object objeto);
    public Boolean EliminacionDeObjeto(Object objeto);
    public Boolean MovimientoDeAjusteDeCantidades(Object objeto,Double cantidadMovimiento,String observaciones);
    public ArrayList ListarPorSucursal(Object objeto);
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
    public Integer guardarNuevo(Object cliente);
    public ArrayList buscar(Integer rubro,Integer subRubro,String criterio);
    public Integer nuevoArticulo(Object objeto);
    public Boolean modificarArticulo(Object objeto);
    public ArrayList modificarPrecios(ArrayList listado,Double porcPrecio,Double porcCosto);
    public DefaultTableModel mostrarListado(ArrayList listado);
    public DefaultTableModel mostrarListadoBusqueda(ArrayList listado);
    public ArrayList buscarParaFacturar(Integer rubro,Integer subRubro,String criterio,Integer idCliente);
    public ArrayList filtrador(ArrayList rubro1, ArrayList subRubro);
    public ArrayList aplicarGanancia(ArrayList listado,Double ganancia);
    public void depurarFiltrador(ArrayList rubro1);
    public ArrayList modificarPreciosValor(ArrayList listado,Double porcPrecio,Double porcCosto);
    public DefaultTableModel actualizarListado(ArrayList listado);
    public void NuevoMasivo(ArrayList listado);
    public void ModificadoMasivo(ArrayList listado);
    public DefaultTableModel mostrarListadoParaSeleccionRubro(ArrayList listado,Integer idRubro);
    public void asignarMasivoRubros(ArrayList listado,Integer idRubro);
    public ArrayList listadoPorRubro(Integer idR);
    public void desAsignarMasivoRubros(ArrayList listado);
    public DefaultTableModel mostrarListadoParaSeleccionDeProveedor(ArrayList listado,Integer idRubro);
    public void asignarMasivoProveedor(ArrayList listado,Integer idRubro);
    public ArrayList listadoPorProveedor(Integer idR);
    public void desAsignarMasivoProveedor(ArrayList listado);
}

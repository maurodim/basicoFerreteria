/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FacturaE;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mauro di
 */
public interface Facturable {
    public Integer nuevaFactura(Object ped);
    public ArrayList cargarDetallefactura(Integer idPed);
    public Object cargarEncabezadoFactura(Integer idPed,Integer tipo);
    public ArrayList listar();
    public ArrayList listarPorCliente(Integer idClient);
    public ArrayList listarPorEstado(Integer idClient,int estado);
    public Boolean modificarFactura(Object ped);
    public void eliminarFactura(Integer idPed);
    public DefaultTableModel mostrarListado(ArrayList lista);
    public void transformarEnRemito(Object ped,ArrayList detalle);
    public void transformarEnrecibo(Object ped,ArrayList detalle);
    public void transformarEndetalle(Object ped,ArrayList detalle);
    public ArrayList convertirAArticulos(ArrayList detalle);
    public ArrayList listarPorClienteNoRemitidas(Integer idClient);
    public ArrayList listarAdeudadaas(Integer idClient);
    public Boolean identificarPedidoAFactura(Integer idPedido,Integer idFactura);
    public void actualizadorDeEstado(Object factu);
    public Object cargarIdFactura(Integer id);
}

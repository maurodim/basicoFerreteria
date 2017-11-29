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
public interface Movible {
    public ArrayList ListarMovimientos(Integer id);
    public Double AjustarSaldo(Double saldoActual,Integer idCliente);
    public DefaultTableModel MostrarMovimientos(ArrayList listado);
    public DefaultTableModel MostrarListaDePrecios(ArrayList listado);
    public ArrayList ListarPreciosCliente(Object cliente);
    public ArrayList ListarMovimientosPorFechas(Integer id,String desde,String hasta);
}

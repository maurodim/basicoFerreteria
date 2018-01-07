/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mauro
 */
public interface Rubable {
    public Integer nuevo(Object rub);
    public void modificarR(Object rub);
    public Object cargar(Integer id);
    public ArrayList listarTodos();
    public ArrayList listarPorParametro(String param);
    public void asignarAMasivo(ArrayList listado);
    public DefaultComboBoxModel mostrarListadoenCombo(ArrayList listado);
    public DefaultTableModel mostrarListadoEnTabla(ArrayList listado);
    public Boolean verificarActualizado();
    public void modificarPrecio(Double porciento,int idRubro,int tipo);
    
}

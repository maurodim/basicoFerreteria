/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FacturaE;


//import Cotizaciones.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mauro
 */
public class MiModeloTablaArticulos extends DefaultTableModel {
    @Override
    public Class getColumnClass(int colum){
        
          
        return String.class;
      }    
}

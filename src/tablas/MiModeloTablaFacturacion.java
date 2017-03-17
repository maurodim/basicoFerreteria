/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tablas;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hernan
 */
public class MiModeloTablaFacturacion extends DefaultTableModel {
    @Override
    public Class getColumnClass(int colum){
        
          
        return String.class;
      }    
}

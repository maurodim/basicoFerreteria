/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tablas;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mauro
 */
public class MiTablaRemitosInternos extends DefaultTableModel{
        @Override
    public Class getColumnClass(int colum){
        if(colum==3)return Boolean.class;
        
          
        return String.class;
        
        }       
}

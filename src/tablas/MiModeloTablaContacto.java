/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tablas;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrador
 */
public class MiModeloTablaContacto extends DefaultTableModel{
    @Override
          public Class getColumnClass(int colum){
        
          if(colum==2) {
                  return Boolean.class;
              }
        return String.class;
      }
}

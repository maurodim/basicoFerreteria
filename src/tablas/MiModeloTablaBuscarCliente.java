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
public class MiModeloTablaBuscarCliente extends DefaultTableModel {
    @Override
    public Class getColumnClass(int colum){
        
          if(colum==3) {
            return int.class;
        }
          if(colum==4) {
            return int.class;
        }
          if(colum==8) {
            return Boolean.class;
        }
        return String.class;
      }
}

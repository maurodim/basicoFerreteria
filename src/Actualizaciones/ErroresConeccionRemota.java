/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actualizaciones;

import interfaces.Transaccionable;
import javax.swing.SwingWorker;
import objetos.ConeccionLocal;

/**
 *
 * @author mauro di
 */
public class ErroresConeccionRemota extends SwingWorker{
    private String sql;
    private Transaccionable tt=new ConeccionLocal();

    public void setSql(String sql) {
        this.sql = sql;
    }
    
    
    @Override
    protected Object doInBackground() throws Exception {
       String ss="insert into fallas (st) values ('"+sql+"')";
            tt.guardarRegistro(sql);
            return null;
    }
    
}

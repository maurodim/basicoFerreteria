/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author mauro
 */
public interface Transaccionable {
    public Boolean guardarRegistro(String sql);//puede ser utiliazdo para insert o update
    public Boolean guardarRegistros(ArrayList listadoDeSentencias);//puede ser utiliazdo para insert o update
    public ResultSet leerConjuntoDeRegistros(String sql);    
    
}

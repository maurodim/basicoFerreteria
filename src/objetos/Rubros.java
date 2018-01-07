/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import interfaces.Componable;
import interfaces.Personalizable;
import interfaces.Rubable;
import interfaces.Transaccionable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mauro
 */
public class Rubros implements Componable,Personalizable,Rubable{
    private Integer id;
    private String descripcion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public DefaultListModel LlenarList(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DefaultTableModel LlenarTabla(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ComboBoxModel LlenarCombo(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DefaultListModel LlenarListConArray(ArrayList listado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DefaultTableModel LlenarTablaConArray(ArrayList listado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ComboBoxModel LlenarComboConArray(ArrayList listado) {
        DefaultComboBoxModel modelo=new DefaultComboBoxModel();
        Iterator it=listado.listIterator();
        Rubros rub;
        while(it.hasNext()){
            rub=(Rubros) it.next();
            modelo.addElement(rub.getDescripcion());
        }
        return modelo;
    }

    @Override
    public int posicionEnCombo(Object objeto, ArrayList listado) {
        int posicion=0;
        Rubros rub=(Rubros) objeto;
        Rubros rubro;
        int marcador=listado.size();
        for(int a=0;a < marcador;a++){
            rubro=(Rubros)listado.get(a);
            if(rubro.getId()==rub.getId())posicion=a;
        }
        return posicion;
    }

    @Override
    public DefaultTableModel LlenarTablaConArrayEnDolares(ArrayList listado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DefaultTableModel LlenarTablaConArrayEnMonedas(ArrayList listado, Object moneda) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean agregar(Object objeto) {
        Transaccionable tra=new Conecciones();
        Rubros rub=(Rubros) objeto;
        String sql="insert into rubros (descripcion) values ('"+rub.getDescripcion()+"')";
        tra.guardarRegistro(sql);
        return true;
    }

    @Override
    public Boolean modificar(Object objeto) {
        Transaccionable tra=new Conecciones();
        Rubros rub=(Rubros) objeto;
        String sql="update rubros set descripcion='"+rub.getDescripcion()+"' where id="+rub.getId();
        tra.guardarRegistro(sql);
        return true;
    }

    @Override
    public Boolean eliminar(Object objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object buscarPorNumero(Integer id) {
        Rubros rubr=new Rubros();
        Transaccionable tra=new Conecciones();
        String sql="select * from rubros where id="+id;
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                rubr.setId(rs.getInt("id"));
                rubr.setDescripcion(rs.getString("descripcion"));
                
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Rubros.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rubr;
    }

    @Override
    public Object buscarPorNombre(String nombre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object buscarPorCuit(String cuit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList listar() {
        Transaccionable tra=new Conecciones();
        ArrayList listado=new ArrayList();
        Rubros rubro=null;
        String sql="select * from rubros order by descripcion";
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                rubro=new Rubros();
                rubro.setId(rs.getInt("id"));
                rubro.setDescripcion(rs.getString("descripcion"));
                listado.add(rubro);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Rubros.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listado;
    }

    @Override
    public ArrayList listarPorNombre(String nombre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList listarPorCuit(String cuit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DefaultComboBoxModel mostrarEnCombo(ArrayList listado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DefaultTableModel mostrarEnTabla(ArrayList listado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer nuevo(Object rub) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object cargar(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList listarTodos() {
       Transaccionable tra=new Conecciones();
        ArrayList listado=new ArrayList();
        Rubros rubro=null;
        String sql="select * from rubros order by descripcion";
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                rubro=new Rubros();
                rubro.setId(rs.getInt("id"));
                rubro.setDescripcion(rs.getString("descripcion"));
                listado.add(rubro);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Rubros.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listado;
    }

    @Override
    public ArrayList listarPorParametro(String param) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void asignarAMasivo(ArrayList listado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DefaultComboBoxModel mostrarListadoenCombo(ArrayList listado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DefaultTableModel mostrarListadoEnTabla(ArrayList listado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean verificarActualizado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificarPrecio(Double porciento, int idRubro, int tipo) {
        String sql = null;
        switch(tipo){
            case 1:
                sql="update articulos set COSTO=(COSTO * "+porciento+") where idrubro="+idRubro;
                break;
            case 2:
                sql="update articulos set PRECIO=(PRECIO * "+porciento+") where idrubro="+idRubro;
                break;
            case 3:
                sql="update articulos set SERVICIO=(SERVICIO * "+porciento+") where idrubro="+idRubro;
                break;
        }
        Transaccionable tra=new Conecciones();
        tra.guardarRegistro(sql);
    }

    @Override
    public void modificarR(Object rub) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

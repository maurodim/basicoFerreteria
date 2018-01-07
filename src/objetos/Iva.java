/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;


import interfaces.Personalizable;
import interfaces.Transaccionable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mauro
 */
public class Iva implements Personalizable{
    private Integer id;
    private String descripcion;
    private Double tasa;
    private int predeterminado;
    private Transaccionable tra=new ConeccionLocal();
    private String sql;
    private ResultSet rs;

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

    public Double getTasa() {
        return tasa;
    }

    public void setTasa(Double tasa) {
        this.tasa = tasa;
    }

    public int getPredeterminado() {
        return predeterminado;
    }

    public void setPredeterminado(int predeterminado) {
        this.predeterminado = predeterminado;
    }

    public int Seleccionado(ArrayList listado,int id){
        int posicion=0;
        Iterator it=listado.listIterator();
        Iva iva;
        int a=0;
        while(it.hasNext()){
            iva=(Iva) it.next();
            if(iva.getId()==id){
                posicion=a;
            }
            a++;
        }
        return posicion;
    }
    @Override
    public Boolean agregar(Object objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean modificar(Object objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean eliminar(Object objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object buscarPorNumero(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        ArrayList listado=new ArrayList();
        Iva iva;
        sql="select * from tipoiva";
        rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                iva=new Iva();
                iva.setId(rs.getInt("id"));
                iva.setDescripcion(rs.getString("descripcion"));
                iva.setTasa(rs.getDouble("tasa"));
                iva.setPredeterminado(rs.getInt("idFiscal"));
                listado.add(iva);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Iva.class.getName()).log(Level.SEVERE, null, ex);
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
        DefaultComboBoxModel modelo=new DefaultComboBoxModel();
        Iterator it=listado.listIterator();
        Iva iva;
        while(it.hasNext()){
            iva=(Iva) it.next();
            modelo.addElement(iva.getDescripcion());
        }
        return modelo;
    }
    @Override
    public DefaultTableModel mostrarEnTabla(ArrayList listado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    
    
}

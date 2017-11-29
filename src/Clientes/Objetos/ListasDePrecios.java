/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clientes.Objetos;

import interfaces.Personalizable;
import interfaces.Transaccionable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import objetos.Conecciones;

/**
 *
 * @author hernan
 */
public class ListasDePrecios implements Personalizable{
    private int numeroLista;
    private String descripcionLista;
    private Double porcentaje;

    public int getNumeroLista() {
        return numeroLista;
    }

    public String getDescripcionLista() {
        return descripcionLista;
    }

    public Double getPorcentaje() {
        return porcentaje;
    }

    public ListasDePrecios() {
        this.numeroLista = 0;
        this.descripcionLista = "";
        this.porcentaje = 1.00;
    }

    @Override
    public Boolean agregar(Object objeto) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean modificar(Object objeto) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean eliminar(Object objeto) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object buscarPorNumero(Integer id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object buscarPorNombre(String nombre) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object buscarPorCuit(String cuit) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList listar() {
       ArrayList listadoList=new ArrayList();
       ListasDePrecios lista=null;
       Transaccionable tra=new Conecciones();
       String sql="select * from coeficienteslistas";
       ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                lista=new ListasDePrecios();
                lista.descripcionLista=rs.getString("descripcion");
                lista.porcentaje=rs.getDouble("coeficiente");
                lista.numeroLista=rs.getInt("id");
                listadoList.add(lista);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(ListasDePrecios.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       return listadoList;
    }

    @Override
    public ArrayList listarPorNombre(String nombre) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList listarPorCuit(String cuit) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
}

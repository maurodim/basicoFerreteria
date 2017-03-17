/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sucursales;

import interfaceGraficas.Inicio;
import interfaces.Transaccionable;
import interfacesPrograma.Cajeables;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import objetos.ConeccionLocal;
import objetos.Conecciones;

/**
 *
 * @author mauro
 */
public class Billetes {
    private static ArrayList listadoBill=new ArrayList();
    private Integer id;
    private String descripcion;
    private Double valor;
    private Double valorIndividual;

    public static ArrayList getListadoBill() {
        return listadoBill;
    }

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

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double getValorIndividual() {
        return valorIndividual;
    }

    public void setValorIndividual(Double valorIndividual) {
        this.valorIndividual = valorIndividual;
    }
    
    public static void cargarLista(){
        
        Transaccionable tra;
        /*
        if(Inicio.coneccionRemota){
            tra=new Conecciones();
        }else{
        */
        tra=new ConeccionLocal();
        //}
        String sql="select * from billetes";
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        
        try {
            listadoBill.clear();
            while(rs.next()){
                Billetes billetes=new Billetes();
                billetes.setDescripcion(rs.getString("descripcion"));
                billetes.setId(rs.getInt("id"));
                billetes.setValor(rs.getDouble("valor"));
                //System.out.println(" DESCRIPCION DE BILLETES "+billetes.descripcion);
                listadoBill.add(billetes);
            
                
            }
            //System.out.println(" TOTAL ARRAY BILLETES "+listadoBill.size());
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Billetes.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    
    
}

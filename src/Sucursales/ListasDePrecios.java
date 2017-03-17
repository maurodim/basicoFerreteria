/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sucursales;

import interfaceGraficas.Inicio;
import interfaces.Editables;
import interfaces.Transaccionable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import objetos.ConeccionLocal;
import objetos.Conecciones;

/**
 *
 * @author mauro
 */
public class ListasDePrecios implements Editables{
    private static ConcurrentHashMap listadoDeListas=new ConcurrentHashMap();
    private Integer id;
    private Double coeficiente;
    private String desccripcion;

    public ListasDePrecios() {
    }

    public ListasDePrecios(Integer id) {
        this.id = id;
        ListasDePrecios listaD=(ListasDePrecios)listadoDeListas.get(id);
        this.coeficiente=listaD.getCoeficiente();
        this.desccripcion=listaD.getDesccripcion();
    }

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getCoeficiente() {
        return coeficiente;
    }

    public void setCoeficiente(Double coeficiente) {
        this.coeficiente = coeficiente;
    }

    public String getDesccripcion() {
        return desccripcion;
    }

    public void setDesccripcion(String desccripcion) {
        this.desccripcion = desccripcion;
    }
    public static void cargarMap(){
         Transaccionable tra;
         /*
         if(Inicio.coneccionRemota){
             tra=new Conecciones();
         }else{
         */ 
             tra=new ConeccionLocal();
         //}
        String sql="select * from coeficienteslistas";
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            listadoDeListas.clear();
            Integer numero=0;
            while(rs.next()){
                ListasDePrecios lista=new ListasDePrecios();
                lista.setId(rs.getInt("id"));
                lista.setDesccripcion(rs.getString("descripcion"));
                lista.setCoeficiente(rs.getDouble("coeficiente"));
                numero=lista.getId();
                listadoDeListas.putIfAbsent(numero,lista);
                
            }
            rs.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ListasDePrecios.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        //if(Inicio.coneccionRemota)BackapearListasDePrecios();
    }
    public static void BackapearListasDePrecios(){
        ListasDePrecios rs=new ListasDePrecios();
        Transaccionable tra=new ConeccionLocal();
        String sql="delete from coeficienteslistas";
        tra.guardarRegistro(sql);
        Enumeration<ListasDePrecios> elementos=listadoDeListas.elements();
            while(elementos.hasMoreElements()){
                rs=(ListasDePrecios)elementos.nextElement();
                sql="insert into coeficienteslistas (id,coeficiente,descripcion) values ("+rs.getId()+","+rs.getCoeficiente()+",'"+rs.getDesccripcion()+"')";
                //System.out.println("LISTAS DE PRECIOS  BACKAPEARLISTAS --"+sql);
                tra.guardarRegistro(sql);
                
                }
            }
    public static ArrayList Listado(){
        ArrayList listado=new ArrayList();
        ListasDePrecios rs=new ListasDePrecios();
         Enumeration<ListasDePrecios> elementos=listadoDeListas.elements();
            while(elementos.hasMoreElements()){
                rs=(ListasDePrecios)elementos.nextElement();
                listado.add(rs);
                }
        return listado;
    }

    @Override
    public Boolean AltaObjeto(Object objeto) {
        ListasDePrecios listaDePrecios=(ListasDePrecios)objeto;
        Boolean verif=true;
        Transaccionable tra=new Conecciones();
        Double coe=listaDePrecios.getCoeficiente() / 100;
        coe=coe + 1;
        String sql="insert into coeficienteslistas (coeficiente,descripcion) values ("+coe+",'"+listaDePrecios.getDesccripcion()+"')";
        tra.guardarRegistro(sql);
        cargarMap();
        return verif;
    }

    @Override
    public Boolean ModificaionObjeto(Object objeto) {
        ListasDePrecios listaDePrecios=(ListasDePrecios)objeto;
        Boolean verif=true;
        Transaccionable tra=new Conecciones();
        Double coe=listaDePrecios.getCoeficiente() / 100;
        coe=coe + 1;
        String sql="update coeficienteslistas set coeficiente="+coe+",descripcion='"+listaDePrecios.getDesccripcion()+" where id="+listaDePrecios.getId();
        tra.guardarRegistro(sql);
        cargarMap();
        return verif;
    }

    @Override
    public Boolean EliminacionDeObjeto(Object objeto) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    @Override
    public Boolean MovimientoDeAjusteDeCantidades(Object objeto, Double cantidadMovimiento, String observaciones) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList ListarPorSucursal(Object objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    }
    


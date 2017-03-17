/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Actualizaciones;


import com.mysql.jdbc.exceptions.MySQLSyntaxErrorException;

import interfaces.Backpeable;
import interfaces.Transaccionable;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import objetos.ConeccionLocal;
import objetos.Conecciones;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/**
 *
 * @author mauro
 */
public class BkDeConeccion1 implements Backpeable{
    private long elements;
    private long attributes;
    private long caracteres;
    private long espaciosEnBlanco;
    private ArrayList listadoSentenciasArt=new ArrayList();
    private ArrayList listadoSentenciasCaja=new ArrayList();
    private ArrayList listadoSentenciasClientes=new ArrayList();
    private ArrayList listadoSentenciasProveedores=new ArrayList();
    private ArrayList listadoSentenciasComprobantes=new ArrayList();
    private static Transaccionable tra=new Conecciones();
    private static Transaccionable tt=new ConeccionLocal();
    
    
        Boolean verif=false;
    public static Boolean guardarSentencias(String sql){
        Boolean verif=false;
        
        
            verif=tra.guardarRegistro(sql);
        
        return verif;
    }
    public synchronized Boolean procesosDeCierre(){
        Boolean ver=false;
        Boolean ver1=false;
        Boolean ver2=false;
        Boolean ver3=false;
        Boolean ver4=false;
        Boolean ver5=false;
        Sentencias sentencia;
        
        String sql="select * from movimientosarticulos where estado is null order by id limit 0,2000";
        //String sentencia="";
        ResultSet rs=tt.leerConjuntoDeRegistros(sql);
        try {
            String textoSentencia="insert into movimientosarticulos (tipoMovimiento,idArticulo,cantidad,numeroDeposito,tipoComprobante,numeroComprobante,numeroCliente,fechaComprobante,numeroUsuario,precioDeCosto,precioDeVenta,precioServicio,idcaja,idOriginal) values ";
            sentencia=new Sentencias();
            while(rs.next()){
                
                textoSentencia+="("+rs.getInt("tipomovimiento")+","+rs.getInt("idarticulo")+","+rs.getDouble("cantidad")+","+rs.getInt("numerodeposito")+","+rs.getInt("tipocomprobante")+","+rs.getInt("numerocomprobante")+","+rs.getInt("numerocliente")+",'"+rs.getString("fechacomprobante")+"',"+rs.getInt("numerousuario")+","+rs.getDouble("preciodecosto")+","+rs.getDouble("preciodeventa")+","+rs.getDouble("precioservicio")+","+rs.getInt("idCaja")+","+rs.getInt("id")+"),";
                sentencia.setId(rs.getInt("id"));
                
                
            }
            int cantidad=textoSentencia.length();
            textoSentencia=textoSentencia.substring(0, cantidad -1);
            sentencia.setTexto(textoSentencia);
            listadoSentenciasArt.add(sentencia);
            rs.close();
            
            cantidad=0;
            textoSentencia="";
            sql="select * from movimientoscaja where estado is null order by id limit 0,2000";
            rs=tt.leerConjuntoDeRegistros(sql);
            sentencia=new Sentencias();
            textoSentencia="insert into movimientoscaja (numeroUsuario,idCliente,numeroSucursal,numeroComprobante,tipoComprobante,monto,tipoMovimiento,idCaja,cantidad,pagado,observaciones,tipoCliente,idOriginal) values ";
            while(rs.next()){
                
                textoSentencia+="("+rs.getInt("numerousuario")+","+rs.getInt("idcliente")+","+rs.getInt("numerosucursal")+","+rs.getInt("numerocomprobante")+","+rs.getInt("tipocomprobante")+","+rs.getDouble("monto")+","+rs.getInt("tipomovimiento")+","+rs.getInt("idcaja")+","+rs.getDouble("cantidad")+","+rs.getInt("pagado")+",'"+rs.getString("observaciones")+"',"+rs.getInt("tipocliente")+","+rs.getInt("id")+"),";
                sentencia.setId(rs.getInt("id"));
                //System.out.println(sentencia.getTexto());
                //listadoSentenciasCaja.add(sentencia);
            }
            cantidad=textoSentencia.length();
            textoSentencia=textoSentencia.substring(0, cantidad -1);
            sentencia.setTexto(textoSentencia);
            listadoSentenciasCaja.add(sentencia);
            rs.close();

            sql="select * from movimientosclientes where estado is null order by id limit 0,2000";
            //aca tengo que poner un group by numero de comprobantes and numeroProveedor
            
            rs=tt.leerConjuntoDeRegistros(sql);
            sentencia=new Sentencias();
            cantidad=0;
            textoSentencia="insert into movimientosclientes (numeroProveedor,monto,pagado,numeroComprobante,idRemito,idUsuario,idCaja,tipoComprobante,idSucursal,idOriginal) values ";
            while(rs.next()){
                
                textoSentencia+="("+rs.getInt("numeroproveedor")+","+rs.getDouble("monto")+","+rs.getInt("pagado")+","+rs.getInt("numerocomprobante")+","+rs.getInt("idRemito")+","+rs.getInt("idusuario")+","+rs.getInt("idcaja")+","+rs.getInt("tipocomprobante")+","+rs.getInt("idsucursal")+","+rs.getInt("id")+"),";
                sentencia.setId(rs.getInt("id"));
                //listadoSentenciasClientes.add(sentencia);
            }
            cantidad=textoSentencia.length();
            textoSentencia=textoSentencia.substring(0, cantidad -1);
            sentencia.setTexto(textoSentencia);
            listadoSentenciasClientes.add(sentencia);
            rs.close();
            
            sql="select * from movimientosproveedores where estado is null";
            rs=tt.leerConjuntoDeRegistros(sql);
            while(rs.next()){
                sentencia=new Sentencias();
                sentencia.setTexto("insert into movimientosproveedores (numeroProveedor,monto,pagado,numeroComprobante,idRemito,idUsuario,idCaja,fechaPago,tipoComprobante,idSucursal,idOriginal) values ("+rs.getInt("numeroproveedor")+","+rs.getDouble("monto")+","+rs.getInt("pagado")+","+rs.getInt("numerocomprobante")+","+rs.getInt("idRemito")+","+rs.getInt("idusuario")+","+rs.getInt("idcaja")+",'"+rs.getString("fechapago")+"',"+rs.getInt("tipocomprobante")+","+rs.getInt("idsucursal")+","+rs.getInt("id")+")");
                sentencia.setId(rs.getInt("id"));
               listadoSentenciasProveedores.add(sentencia);
            }
            rs.close();

            sql="select * from tipocomprobantes";
            rs=tt.leerConjuntoDeRegistros(sql);
            while(rs.next()){
                sentencia=new Sentencias();
                sentencia.setTexto("update tipocomprobantes set numeroActivo="+rs.getInt("numeroactivo")+" where numero="+rs.getInt("numero"));
                listadoSentenciasComprobantes.add(sentencia);
                
            }
            rs.close();
            
            //ACA COMIENZAN LOS UPDATES
            //if(ProbarConeccion()){
            
            
                       
            
            
           
            Sentencias st=new Sentencias();
            Iterator itA=listadoSentenciasArt.listIterator();
            if(ProbarConeccion()){
                System.out.println("entro en ver 1");
            while(itA.hasNext()){
                st=(Sentencias)itA.next();
                if(tra.guardarRegistro(st.getTexto())){
                    sql="update movimientosarticulos set estado=1 where estado is null and id < "+st.getId();
                    System.out.println(st.getTexto());
            tt.guardarRegistro(sql);  
                }
            }
            }
            
            Iterator itCa=listadoSentenciasCaja.listIterator();
            //st="";
            if(ProbarConeccion()){
                System.out.println("entro en ver 2");
            while(itCa.hasNext()){
                st=(Sentencias)itCa.next();
                if(tra.guardarRegistro(st.getTexto())){
                    sql="update movimientoscaja set estado=1 where estado is null and id < "+st.getId();
                    tt.guardarRegistro(sql);
                }
            }
        
            }
            Iterator itCl=listadoSentenciasClientes.listIterator();
            //st="";
            if(ProbarConeccion()){
                System.out.println("entro en ver 3");
            while(itCl.hasNext()){
                st=(Sentencias)itCl.next();
                if(tra.guardarRegistro(st.getTexto())){
                            sql="update movimientosclientes set estado =1 where estado is null and id <"+st.getId();
                            tt.guardarRegistro(sql);    
    
                }
                
            }
            }
            Iterator itP=listadoSentenciasProveedores.listIterator();
//            st="";
            if(ProbarConeccion()){
                System.out.println("entro en ver 4");
            while(itP.hasNext()){
                st=(Sentencias)itP.next();
                if(tra.guardarRegistro(st.getTexto())){
                   sql="update movimientosproveedores set estado=1 where id="+st.getId();
            tt.guardarRegistro(sql);   
                }
            }
            }
            Iterator itCo=listadoSentenciasComprobantes.listIterator();
//            st="";
            if(ProbarConeccion()){
                System.out.println("entro en ver 5");
            while(itCo.hasNext()){
                st=(Sentencias)itCo.next();
                tra.guardarRegistro(st.getTexto());
            }
            }
            
            listadoSentenciasArt.clear();
            listadoSentenciasCaja.clear();
            listadoSentenciasClientes.clear();
            listadoSentenciasProveedores.clear();
            listadoSentenciasComprobantes.clear();
            File folder=new File("C:\\Gestion\\Administrador");
            if(!folder.isDirectory()){
                System.out.println(" no es administrador¡¡¡¡");
                
            }else{
                System.out.println(" administrador¡¡¡¡");
                Integer cantidadLocal=0;
                sql="select movimientosarticulos.id from movimientosarticulos";
                rs=tt.leerConjuntoDeRegistros(sql);
                while(rs.next()){
                    cantidadLocal=rs.getInt("id");
                }
                System.out.println("cantidad local "+cantidadLocal);
                //int desde=0;
                Integer hasta=200;
                Integer limite=0;
                Integer desde=0;
                sql="select movimientosarticulos.numero from movimientosarticulos order by numero desc limit 0,1";
                ResultSet rx=tra.leerConjuntoDeRegistros(sql);
                while(rx.next()){
                    System.out.println("limite "+rx.getInt("numero"));
                    limite=rx.getInt("numero");
                }
                while(desde < limite){
                    hasta=hasta + 200;
                    desde=desde + 200;
                sql="select * from movimientosarticulos where numero > "+cantidadLocal+" order by numero limit "+desde+","+hasta;
                System.out.println(sql);
                rx=tra.leerConjuntoDeRegistros(sql);
                int posicion=0;
                
                while(rx.next()){
                    System.out.println("a descargar "+rx.getInt("numero"));
                    st=new Sentencias();
                    posicion++;
                    st.setId(posicion);
                    st.setTexto("insert into movimientosarticulos(tipoMovimiento,idarticulo,cantidad,numerodeposito,tipocomprobante,numerocomprobante,numerocliente,fechacomprobante,numerousuario, preciodecosto,preciodeventa,precioservicio,estado,idcaja) values ("+rx.getInt("tipoMovimiento")+","+rx.getInt("idArticulo")+","+rx.getDouble("cantidad")+","+rx.getInt("numerodeposito")+","+rx.getInt("tipocomprobante")+","+rx.getInt("numerocomprobante")+","+rx.getInt("numerocliente")+",'"+rx.getDate("fechacomprobante")+"',"+rx.getInt("numerousuario")+","+rx.getDouble("preciodecosto")+","+rx.getDouble("preciodeventa")+","+rx.getDouble("precioservicio")+",1,"+rx.getInt("idcaja")+")");
                    listadoSentenciasArt.add(st);
                }
                Iterator il=listadoSentenciasArt.listIterator();
                while(il.hasNext()){
                    st=(Sentencias)il.next();
                    System.out.println(st.getId()+" - "+st.getTexto());
                    tt.guardarRegistro(st.getTexto());
                }
                listadoSentenciasArt.clear();
                }
                
            }
            ver=true;
            
        } catch (MySQLSyntaxErrorException ee){
            Logger.getLogger(BkDeConeccion1.class.getName()).log(Level.SEVERE, null, ee);    
            
        } catch (SQLException ex) {
            Logger.getLogger(BkDeConeccion1.class.getName()).log(Level.SEVERE, null, ex);
        
        }
        
        
        return ver;
    }
    private Boolean ProbarConeccion(){
        Boolean verif=false;
        
        String sql="select id from articulos limit 0,1";
        if(tra!=null){
            
        }else{
            tra=new Conecciones();
        }
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                verif=true;
                
            }
            if(rs!=null){
                tra=new Conecciones();
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(BkDeConeccion1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException ee){
            return false;
        }
        return verif;
    }
    public void limpiarBasesLocal(){
        Transaccionable tra=new ConeccionLocal();
        String sql="delete from movimientosarticulos where estado=1";
        tra.guardarRegistro(sql);
        sql="delete from movimientoscaja where estado=1";
        tra.guardarRegistro(sql);
        sql="delete from movimientosclientes where estado=1";
        tra.guardarRegistro(sql);
        sql="delete from movimientosproveedores where estado=1";
        tra.guardarRegistro(sql);
        //sql="delete from movimientosdesucursales where estado=1";
        //tra.guardarRegistro(sql);
        
    }
    @Override
    public Hashtable leerArticulos() {
        Hashtable art=new Hashtable();
        
        return art;
    }

    @Override
    public Hashtable leerProveedores() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Hashtable leerClientes() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object leerDeposito() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean backapearObjetos() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object leerUsuarios(String nombre, String clave) {
         throw new UnsupportedOperationException("Not supported yet.");
    }
    }


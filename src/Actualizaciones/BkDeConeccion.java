/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Actualizaciones;

import Sucursales.Sucursales;
import Sucursales.Usuarios;
import com.mysql.jdbc.exceptions.MySQLSyntaxErrorException;
import interfaceGraficas.Inicio;
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
import objetos.Menus;
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
public class BkDeConeccion implements Backpeable{
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
        Transaccionable tra=new Conecciones();
        System.out.println(sql);
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
        
        String sql="select * from movimientosarticulos where estado is null";
        //String sentencia="";
        ResultSet rs=tt.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                sentencia=new Sentencias();
                sentencia.setTexto("insert into movimientosarticulos (tipoMovimiento,idArticulo,cantidad,numeroDeposito,tipoComprobante,numeroComprobante,numeroCliente,fechaComprobante,numeroUsuario,precioDeCosto,precioDeVenta,precioServicio,idcaja) values ("+rs.getInt("tipomovimiento")+","+rs.getInt("idarticulo")+","+rs.getDouble("cantidad")+","+rs.getInt("numerodeposito")+","+rs.getInt("tipocomprobante")+","+rs.getInt("numerocomprobante")+","+rs.getInt("numerocliente")+",'"+rs.getString("fechacomprobante")+"',"+rs.getInt("numerousuario")+","+rs.getDouble("preciodecosto")+","+rs.getDouble("preciodeventa")+","+rs.getDouble("precioservicio")+","+Inicio.caja.getNumero()+")");
                sentencia.setId(rs.getInt("id"));
                listadoSentenciasArt.add(sentencia);
                
            }
            rs.close();
            
            sql="select * from movimientoscaja where estado is null";
            rs=tt.leerConjuntoDeRegistros(sql);
            while(rs.next()){
                sentencia=new Sentencias();
                sentencia.setTexto("insert into movimientoscaja (numeroUsuario,idCliente,numeroSucursal,numeroComprobante,tipoComprobante,monto,tipoMovimiento,idCaja,cantidad,pagado,observaciones,tipoCliente) values ("+rs.getInt("numerousuario")+","+rs.getInt("idcliente")+","+rs.getInt("numerosucursal")+","+rs.getInt("numerocomprobante")+","+rs.getInt("tipocomprobante")+","+rs.getDouble("monto")+","+rs.getInt("tipomovimiento")+","+rs.getInt("idcaja")+","+rs.getDouble("cantidad")+","+rs.getInt("pagado")+",'"+rs.getString("observaciones")+"',"+rs.getInt("tipocliente")+")");
                sentencia.setId(rs.getInt("id"));
                System.out.println(sentencia.getTexto());
                listadoSentenciasCaja.add(sentencia);
            }
            rs.close();

            sql="select * from movimientosclientes where estado is null";
            rs=tt.leerConjuntoDeRegistros(sql);
            while(rs.next()){
                sentencia=new Sentencias();
                sentencia.setTexto("insert into movimientosclientes (numeroProveedor,monto,pagado,numeroComprobante,idRemito,idUsuario,idCaja,tipoComprobante,idSucursal) values ("+rs.getInt("numeroproveedor")+","+rs.getDouble("monto")+","+rs.getInt("pagado")+","+rs.getInt("numerocomprobante")+","+rs.getInt("idRemito")+","+rs.getInt("idusuario")+","+rs.getInt("idcaja")+","+rs.getInt("tipocomprobante")+","+rs.getInt("idsucursal")+")");
                sentencia.setId(rs.getInt("id"));
                listadoSentenciasClientes.add(sentencia);
            }
            rs.close();
            
            sql="select * from movimientosproveedores where estado is null";
            rs=tt.leerConjuntoDeRegistros(sql);
            while(rs.next()){
                sentencia=new Sentencias();
                sentencia.setTexto("insert into movimientosproveedores (numeroProveedor,monto,pagado,numeroComprobante,idRemito,idUsuario,idCaja,fechaPago,tipoComprobante,idSucursal) values ("+rs.getInt("numeroproveedor")+","+rs.getDouble("monto")+","+rs.getInt("pagado")+","+rs.getInt("numerocomprobante")+","+rs.getInt("idRemito")+","+rs.getInt("idusuario")+","+rs.getInt("idcaja")+",'"+rs.getString("fechapago")+"',"+rs.getInt("tipocomprobante")+","+rs.getInt("idsucursal")+")");
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
                    sql="update movimientosarticulos set estado=1 where id="+st.getId();
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
                    sql="update movimientoscaja set estado=1 where id="+st.getId();
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
                            sql="update movimientosclientes set estado =1 where id="+st.getId();
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
            ver=true;
        } catch (MySQLSyntaxErrorException ee){
            Logger.getLogger(BkDeConeccion.class.getName()).log(Level.SEVERE, null, ee);    
            
        } catch (SQLException ex) {
            Logger.getLogger(BkDeConeccion.class.getName()).log(Level.SEVERE, null, ex);
        
        }
        
        
        return ver;
    }
    private Boolean ProbarConeccion(){
        Boolean verif=false;
        
        String sql="select id from articulos limit 0,1";
        
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
            Logger.getLogger(Actualiza.class.getName()).log(Level.SEVERE, null, ex);
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
         Usuarios usu=new Usuarios();
         Integer numeroEquipo=0;
        try {
            File archivo=null;
        FileReader fr=null;
        BufferedReader br=null;
         archivo = new File ("C:\\Gestion\\idEquipo.txt");
         if(archivo.exists()){
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);
 
         // Lectura del fichero
         String linea;
          //Transaccionable tra=new Conecciones();
         while((linea=br.readLine())!=null){
             
            System.out.println("Equipo Numero :"+linea);
           numeroEquipo=Integer.parseInt(linea);
           // if(tra.guardarRegistro(linea));
      }
         }

           //Inicio.coneccionRemota=ProbarConeccion();
       Transaccionable tras=new ConeccionLocal();
                       String sql="select * from usuarios where nombreUsuario like '"+nombre+"' and clave like '"+clave+"'";
                       //System.out.println(sql);
                       ResultSet rs=tras.leerConjuntoDeRegistros(sql);
                   
                           
                      
                        while(rs.next()){
                        usu=new Usuarios();
                        usu.setNivelDeAutorizacion(rs.getInt("autorizacion"));
                        usu.setNombre(rs.getString("nombre"));
                        usu.setNumero(rs.getInt("numero"));
                        usu.setNumeroId(rs.getInt("numero"));
                        usu.setSucursal(new Sucursales(rs.getInt("sucursal")));
                        usu.setEquipo(numeroEquipo);
                             
                        }
                        sql="select * from tipoacceso where numero="+usu.getNivelDeAutorizacion();
                        //System.out.println(sql);
                        rs=tras.leerConjuntoDeRegistros(sql);
                        while(rs.next()){
                               usu.setMenu(new Menus(rs.getBoolean("menu1"),rs.getBoolean("menu2"),rs.getBoolean("menu3"),rs.getBoolean("menu4"),rs.getBoolean("menu5"),rs.getBoolean("menu6"),rs.getBoolean("menu7")));                    
                           
                        }
                        rs.close();
                        
        } catch (SQLException ex) {
            Logger.getLogger(BkDeConeccion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BkDeConeccion.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(ex);
            numeroEquipo=0;
        } catch (IOException ex) {
            Logger.getLogger(BkDeConeccion.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(ex);
            numeroEquipo=0;
        }
         return usu;
    }
    }


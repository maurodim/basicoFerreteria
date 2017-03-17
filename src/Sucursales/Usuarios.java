/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sucursales;

import Administracion.TipoAcceso;
import interfaceGraficas.Inicio;
import interfaces.Personalizable;
import interfaces.Transaccionable;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import objetos.ConeccionLocal;
import objetos.Conecciones;
import objetos.Menus;

/**
 *
 * @author mauro
 */
public class Usuarios extends TipoAcceso implements Personalizable{
    private int numeroId;
    private String nombre;
    private String direccion;
    private String telefono;
    private String mail;
    private String localidad;
    private String nombreDeUsuario;
    private String clave;
    private Integer nivelDeAutorizacion;
    private Sucursales sucursal;
    private Menus menu;
    private static ArrayList lista=new ArrayList();
    private Integer equipo;

    public Integer getEquipo() {
        return equipo;
    }

    public void setEquipo(Integer equipo) {
        this.equipo = equipo;
    }
    

    public Integer getNivelDeAutorizacion() {
        return nivelDeAutorizacion;
    }

    public void setNivelDeAutorizacion(Integer nivelDeAutorizacion) {
        this.nivelDeAutorizacion = nivelDeAutorizacion;
    }
    
    
    public Menus getMenu() {
        return menu;
    }

    public void setMenu(Menus menu) {
        this.menu = menu;
    }

    
    public Sucursales getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursales sucursal) {
        this.sucursal = sucursal;
    }
    

    public Usuarios(int numero) {
        this.numeroId = numero;
    }

    public Usuarios(String nombreDeUsuario, String clave) {
        this.nombreDeUsuario = nombreDeUsuario;
        this.clave = clave;
        super.setNivel(1);
    }

    public Usuarios() {
       
    }

    public int getNumeroId() {
        return numeroId;
    }

    public void setNumeroId(int numero) {
        this.numeroId = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        if(direccion.equals(""))direccion=" ";
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        if(telefono.equals(""))telefono=" ";
        this.telefono = telefono;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        if(mail.equals(""))mail=" ";
        this.mail = mail;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        if(localidad.equals(""))localidad=" ";
        this.localidad = localidad;
    }

    public String getNombreDeUsuario() {
        return nombreDeUsuario;
    }

    public void setNombreDeUsuario(String nombreDeUsuario) {
        this.nombreDeUsuario = nombreDeUsuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
    public static void BackapearUsuarios(){
        Transaccionable tra=new ConeccionLocal();
        //lista.clear();
        Cargar();
        String sql="delete from usuarios";
        tra.guardarRegistro(sql);
        Usuarios usu=new Usuarios();
        //System.out.println("TAMAÑO LISTA "+lista.size());
        Iterator itUs=lista.listIterator();
        String parteSentencia="";
        while(itUs.hasNext()){
            usu=(Usuarios)itUs.next();
            parteSentencia=usu.getNumeroId()+",'"+usu.getNombre()+"','"+usu.getDireccion()+"','"+usu.getLocalidad()+"','"+usu.getTelefono()+"','"+usu.getMail()+"','"+usu.getNombreDeUsuario()+"','"+usu.getClave()+"',"+usu.getNivelDeAutorizacion()+","+usu.getNivel()+","+usu.getSucursal().getNumero();
            //System.out.println("A VER "+parteSentencia);
            sql="insert into usuarios (numero,nombre,direccion,localidad,telefono,mail,nombreusuario,clave,autorizacion,numerotipoacceso,sucursal) values ("+parteSentencia+")";
            //System.out.println("SENTENCIA BACKAPEARUSUARIOS --"+sql);
            tra.guardarRegistro(sql);
        }
        lista.clear();
        if(Inicio.coneccionRemota){
            String sentencia="";
            sql="delete from tipoacceso";
            tra.guardarRegistro(sql);
            sql="select * from tipoacceso";
            Transaccionable tt=new Conecciones();
            ResultSet rs=tt.leerConjuntoDeRegistros(sql);
            try {
                while(rs.next()){
                    sentencia="insert into tipoacceso(numero,descripcion,nivel,menu1,menu2,menu3,menu4,menu5,menu6,menu7) values ("+rs.getInt("numero")+",'"+rs.getString("descripcion")+"',"+rs.getInt("nivel")+","+rs.getInt("menu1")+","+rs.getInt("menu2")+","+rs.getInt("menu3")+","+rs.getInt("menu4")+","+rs.getInt("menu5")+","+rs.getInt("menu6")+","+rs.getInt("menu7")+")";
                    tra.guardarRegistro(sentencia);
                    
                }
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    private static void Cargar(){
        try {
            
            String sql="select *,(select tipoacceso.menu1 from tipoacceso where tipoacceso.numero=usuarios.autorizacion)as menu1,(select tipoacceso.menu2 from tipoacceso where tipoacceso.numero=usuarios.autorizacion)as menu2,(select tipoacceso.menu3 from tipoacceso where tipoacceso.numero=usuarios.autorizacion)as menu3,(select tipoacceso.menu4 from tipoacceso where tipoacceso.numero=usuarios.autorizacion)as menu4,(select tipoacceso.menu5 from tipoacceso where tipoacceso.numero=usuarios.autorizacion)as menu5,(select tipoacceso.menu6 from tipoacceso where tipoacceso.numero=usuarios.autorizacion)as menu6,(select tipoacceso.menu7 from tipoacceso where tipoacceso.numero=usuarios.autorizacion)as menu7 from usuarios";
            String sql1="";
            Usuarios us=null;
            Transaccionable traUs=new Conecciones();
            ResultSet rr=traUs.leerConjuntoDeRegistros(sql);
            
            while(rr.next()){
                us=new Usuarios();
                us.nombre=rr.getString("nombre");
                us.direccion=rr.getString("direccion");
                us.localidad=rr.getString("localidad");
                us.mail=rr.getString("mail");
                us.numeroId=rr.getInt("numero");
                us.telefono=rr.getString("telefono");
                us.nombreDeUsuario=rr.getString("nombreUsuario");
                us.clave=rr.getString("clave");
                us.sucursal=new Sucursales(rr.getInt("sucursal"));
                us.setNivel(rr.getInt("autorizacion"));
                us.setNivelDeAutorizacion(rr.getInt("autorizacion"));
                //System.err.println("USUARIOS "+us.nombre);
                us.setMenu(new Menus(rr.getBoolean("menu1"),rr.getBoolean("menu2"),rr.getBoolean("menu3"),rr.getBoolean("menu4"),rr.getBoolean("menu5"),rr.getBoolean("menu6"),rr.getBoolean("menu7")));
                lista.add(us);
                
            }
            
            rr.close();
        } catch (SQLException ex) {
            Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ArrayList listarUsuario(){
        ArrayList listadoUsuarios=new ArrayList();
        try {
            
            String sql="select *,(select tipoacceso.menu1 from tipoacceso where tipoacceso.numero=usuarios.autorizacion)as menu1,(select tipoacceso.menu2 from tipoacceso where tipoacceso.numero=usuarios.autorizacion)as menu2,(select tipoacceso.menu3 from tipoacceso where tipoacceso.numero=usuarios.autorizacion)as menu3,(select tipoacceso.menu4 from tipoacceso where tipoacceso.numero=usuarios.autorizacion)as menu4,(select tipoacceso.menu5 from tipoacceso where tipoacceso.numero=usuarios.autorizacion)as menu5,(select tipoacceso.menu6 from tipoacceso where tipoacceso.numero=usuarios.autorizacion)as menu6,(select tipoacceso.menu7 from tipoacceso where tipoacceso.numero=usuarios.autorizacion)as menu7 from usuarios";
            String sql1="";
            Usuarios us=null;
            Transaccionable traUs=new Conecciones();
            ResultSet rr=traUs.leerConjuntoDeRegistros(sql);
            
            while(rr.next()){
                us=new Usuarios();
                us.nombre=rr.getString("nombre");
                us.direccion=rr.getString("direccion");
                us.localidad=rr.getString("localidad");
                us.mail=rr.getString("mail");
                us.numeroId=rr.getInt("numero");
                us.telefono=rr.getString("telefono");
                us.nombreDeUsuario=rr.getString("nombreUsuario");
                us.clave=rr.getString("clave");
                us.setNivel(rr.getInt("autorizacion"));
                us.setNivelDeAutorizacion(rr.getInt("autorizacion"));
                //System.err.println("USUARIOS "+us.nombre);
                us.setMenu(new Menus(rr.getBoolean("menu1"),rr.getBoolean("menu2"),rr.getBoolean("menu3"),rr.getBoolean("menu4"),rr.getBoolean("menu5"),rr.getBoolean("menu6"),rr.getBoolean("menu7")));
                us.setSucursal(new Sucursales(rr.getInt("sucursal")));
                listadoUsuarios.add(us);
                
            }
            
            rr.close();
        } catch (SQLException ex) {
            Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listadoUsuarios;
    }

    @Override
    public Boolean registrarIngreso(Object objeto) {
        //return super.registrarIngreso(objeto);
        String sql="insert into movimientosusuarios (numeroUsuario,tipoacceso) values ("+Inicio.usuario.getNumeroId()+","+Inicio.usuario.getNivelDeAutorizacion()+")";
        Transaccionable tra=new Conecciones();
        return tra.guardarRegistro(sql);
    }

    @Override
    public Boolean registrarSalida(Object objeto) {
        String sql="update movimientosusuarios set salida=";
        Transaccionable tra=new Conecciones();
        return tra.guardarRegistro(sql);
        //return super.registrarSalida(objeto);
    }

    @Override
    public Object validarClave(String usuario, String clave) {
        Transaccionable tra=new Conecciones();
        Usuarios usu=null;
        Integer numeroEquipo=0;
        try{
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
        String sql="select *,(select tipoacceso.menu1 from tipoacceso where tipoacceso.numero=usuarios.autorizacion)as menu1,(select tipoacceso.menu2 from tipoacceso where tipoacceso.numero=usuarios.autorizacion)as menu2,(select tipoacceso.menu3 from tipoacceso where tipoacceso.numero=usuarios.autorizacion)as menu3,(select tipoacceso.menu4 from tipoacceso where tipoacceso.numero=usuarios.autorizacion)as menu4,(select tipoacceso.menu5 from tipoacceso where tipoacceso.numero=usuarios.autorizacion)as menu5,(select tipoacceso.menu6 from tipoacceso where tipoacceso.numero=usuarios.autorizacion)as menu6,(select tipoacceso.menu7 from tipoacceso where tipoacceso.numero=usuarios.autorizacion)as menu7 from usuarios where nombreUsuario like '"+usuario+"' and clave like '"+clave+"'";
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
       
        
        
            while(rs.next()){
            usu=new Usuarios();
            usu.setNivelDeAutorizacion(rs.getInt("autorizacion"));
            usu.setNombre(rs.getString("nombre"));
            usu.setNumero(rs.getInt("numero"));
            usu.setNumeroId(rs.getInt("numero"));
            usu.setSucursal(new Sucursales(rs.getInt("sucursal")));
            usu.setEquipo(numeroEquipo);
                    usu.setMenu(new Menus(rs.getBoolean("menu1"),rs.getBoolean("menu2"),rs.getBoolean("menu3"),rs.getBoolean("menu4"),rs.getBoolean("menu5"),rs.getBoolean("menu6"),rs.getBoolean("menu7")));                    
               
            }
            rs.close();
            
        } catch (SQLException ex) {
            try {
                //System.out.println("NO SE CONECTA, ACA CARGA LOS OBJETOS"); 
                Transaccionable tras=new ConeccionLocal();
                String sql="select *,(select tipoacceso.menu1 from tipoacceso where tipoacceso.numero=usuarios.autorizacion)as menu1,(select tipoacceso.menu2 from tipoacceso where tipoacceso.numero=usuarios.autorizacion)as menu2,(select tipoacceso.menu3 from tipoacceso where tipoacceso.numero=usuarios.autorizacion)as menu3,(select tipoacceso.menu4 from tipoacceso where tipoacceso.numero=usuarios.autorizacion)as menu4,(select tipoacceso.menu5 from tipoacceso where tipoacceso.numero=usuarios.autorizacion)as menu5,(select tipoacceso.menu6 from tipoacceso where tipoacceso.numero=usuarios.autorizacion)as menu6,(select tipoacceso.menu7 from tipoacceso where tipoacceso.numero=usuarios.autorizacion)as menu7 from usuarios where nombreUsuario like '"+usuario+"' and clave like '"+clave+"'";
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
                         usu.setMenu(new Menus(rs.getBoolean("menu1"),rs.getBoolean("menu2"),rs.getBoolean("menu3"),rs.getBoolean("menu4"),rs.getBoolean("menu5"),rs.getBoolean("menu6"),rs.getBoolean("menu7")));                    
                    
                 }
                 rs.close();
             
                 Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex1);
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(ex);
            numeroEquipo=0;
        } catch (IOException ex) {
            Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(ex);
            numeroEquipo=0;
        }
        return usu;
    }

    @Override
    public Boolean modificarDatosUsuario(Object objeto) {
        Boolean verif=false;
        Usuarios usuario=(Usuarios)objeto;
        Transaccionable tra=new Conecciones();
        String sql="update usuarios set nombre='"+usuario.getNombre()+"',direccion='"+usuario.getDireccion()+"',telefono='"+usuario.getTelefono()+"',mail='"+usuario.getMail()+"',nombreUsuario='"+usuario.getNombreDeUsuario()+"',clave='"+usuario.getClave()+"',autorizacion="+usuario.getNivelDeAutorizacion()+",numeroTipoAcceso="+usuario.getNivelDeAutorizacion()+",sucursal="+usuario.getSucursal().getNumero()+" where numero="+usuario.getNumeroId();
        verif=tra.guardarRegistro(sql);
        
        return verif;
    }

    @Override
    public Boolean nuevoUsuario(Object objeto) {
        Boolean verif=false;
        Usuarios usuario=(Usuarios)objeto;
        Transaccionable tra=new Conecciones();
        String sql="insert into usuarios (nombre,direccion,telefono,mail,nombreUsuario,clave,autorizacion,numeroTipoAcceso,sucursal) values ('"+usuario.getNombre()+"','"+usuario.getDireccion()+"','"+usuario.getTelefono()+"','"+usuario.getMail()+"','"+usuario.getNombreDeUsuario()+"','"+usuario.getClave()+"',"+usuario.getNivelDeAutorizacion()+","+usuario.getNivelDeAutorizacion()+","+usuario.getSucursal().getNumero()+")";
        verif=tra.guardarRegistro(sql);
        
        return verif;
    }

    @Override
    public ArrayList listarUsuarios() {
        return super.listarUsuarios();
    }

    @Override
    public Object cargarUsuario(Integer numeroUsuario) {
        Transaccionable tra=new Conecciones();
        Usuarios usu=null;
        String sql="select *,(select tipoacceso.menu1 from tipoacceso where tipoacceso.numero=usuarios.autorizacion)as menu1,(select tipoacceso.menu2 from tipoacceso where tipoacceso.numero=usuarios.autorizacion)as menu2,(select tipoacceso.menu3 from tipoacceso where tipoacceso.numero=usuarios.autorizacion)as menu3,(select tipoacceso.menu4 from tipoacceso where tipoacceso.numero=usuarios.autorizacion)as menu4,(select tipoacceso.menu5 from tipoacceso where tipoacceso.numero=usuarios.autorizacion)as menu5,(select tipoacceso.menu6 from tipoacceso where tipoacceso.numero=usuarios.autorizacion)as menu6,(select tipoacceso.menu7 from tipoacceso where tipoacceso.numero=usuarios.autorizacion)as menu7 from usuarios where numero="+numeroUsuario;
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
       
        
        try {
            while(rs.next()){
            usu=new Usuarios();
            usu.setNivelDeAutorizacion(rs.getInt("autorizacion"));
            usu.setNombre(rs.getString("nombre"));
            usu.setNumero(rs.getInt("numero"));
            usu.setDireccion(rs.getString("direccion"));
            usu.setClave(rs.getString("clave"));
            usu.setTelefono(rs.getString("telefono"));
            usu.setMail(rs.getString("mail"));
            usu.setSucursal(new Sucursales(rs.getInt("sucursal")));
           
            
                    usu.setMenu(new Menus(rs.getBoolean("menu1"),rs.getBoolean("menu2"),rs.getBoolean("menu3"),rs.getBoolean("menu4"),rs.getBoolean("menu5"),rs.getBoolean("menu6"),rs.getBoolean("menu7")));                    
               
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usu;
    }

    @Override
    public Boolean agregar(Object objeto) {
        Boolean verif=false;
        Usuarios usuario=(Usuarios)objeto;
        Transaccionable tra=new Conecciones();
        String sql="insert into usuarios (nombre,direccion,telefono,mail,nombreUsuario,clave,autorizacion,numeroTipoAcceso,sucursal) values ('"+usuario.getNombre()+"','"+usuario.getDireccion()+"','"+usuario.getTelefono()+"','"+usuario.getMail()+"','"+usuario.getNombreDeUsuario().toUpperCase()+"','"+usuario.getClave()+"',"+usuario.getNivelDeAutorizacion()+","+usuario.getNivelDeAutorizacion()+","+usuario.getSucursal().getNumero()+")";
        verif=tra.guardarRegistro(sql);
        
        return verif;
    }

    @Override
    public Boolean modificar(Object objeto) {
        Boolean verif=false;
        Usuarios usuario=(Usuarios)objeto;
        Transaccionable tra=new Conecciones();
        String sql="update usuarios set nombre='"+usuario.getNombre()+"',direccion='"+usuario.getDireccion()+"',telefono='"+usuario.getTelefono()+"',mail='"+usuario.getMail()+"',nombreUsuario='"+usuario.getNombreDeUsuario().toUpperCase()+"',clave='"+usuario.getClave()+"',autorizacion="+usuario.getNivelDeAutorizacion()+",numeroTipoAcceso="+usuario.getNivelDeAutorizacion()+",sucursal="+usuario.getSucursal().getNumero()+" where numero="+usuario.getNumeroId();
        verif=tra.guardarRegistro(sql);
        
        return verif;
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
        throw new UnsupportedOperationException("Not supported yet.");
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

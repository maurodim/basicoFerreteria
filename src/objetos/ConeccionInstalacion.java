/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import Compras.Proveedores;
import Depositos.Depositos;
import Sucursales.Cajas;
import Sucursales.ListasDePrecios;
import Sucursales.Sucursales;
import Sucursales.Usuarios;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import facturacion.clientes.ClientesTango;
import interfaceGraficas.Inicio;
import interfaces.Transaccionable;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author mauro
 */
public class ConeccionInstalacion implements Transaccionable{
    private Connection con;
    private PreparedStatement st;
    private String driver1="org.apache.derby.jdbc.EmbeddedDriver";

    public ConeccionInstalacion() {
              MysqlDataSource dataSource=new MysqlDataSource();
		try{
			//Class.forName(driver1).newInstance();
                    dataSource.setUser("root");//maurodim
                    dataSource.setDatabaseName("information_schema");//maurodim_lseriea
                    dataSource.setPassword("");//mau*2012
                    dataSource.setServerName("localhost");//201.235.253.65
                    con=dataSource.getConnection();
                //st=dbConnection.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(ConeccionInstalacion.class.getName()).log(Level.SEVERE, null, ex);
            }

        
    }

    @Override
    public Boolean guardarRegistro(String sql) {
        Boolean coneccion=true;
        try {
            //System.out.println("SENTENCIA "+sql);
            st=con.prepareStatement(sql);
            st.executeUpdate();
            //this.st.executeQuery(sql);
            
        } catch (SQLException ex) {
            Logger.getLogger(Conecciones.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(ex);
            coneccion=false;
            FileWriter fichero=null;
            PrintWriter pw=null;
            try {
                fichero = new FileWriter("C:\\Gestion\\"+Inicio.fechaDia+" - erroresDeConeccion.txt",true);
                pw=new PrintWriter(fichero);
                pw.println(sql);
            } catch (IOException ex1) {
                Logger.getLogger(Conecciones.class.getName()).log(Level.SEVERE, null, ex1);
            }finally{
                         try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
            }
            
        }
        return coneccion;
    }

    @Override
    public Boolean guardarRegistros(ArrayList listadoDeSentencias) {
        String sql="";
        Boolean coneccionCorrecta=true;
        Iterator il=listadoDeSentencias.listIterator();
        while(il.hasNext()){
            sql=(String)il.next();
            try {
                st.executeUpdate(sql);
            } catch (SQLException ex) {
                Logger.getLogger(Conecciones.class.getName()).log(Level.SEVERE, null, ex);
                coneccionCorrecta=false;
            }
        }
        
        return coneccionCorrecta;
    }

    @Override
    public ResultSet leerConjuntoDeRegistros(String sql) {
        ResultSet rs=null;
        try {
            st=con.prepareStatement(sql);
            st.execute();
            rs=st.getResultSet();
        } catch (SQLException ex) {
            //System.out.println("NO SE CONECTA, ACA CARGA LOS OBJETOS");
            Logger.getLogger(Conecciones.class.getName()).log(Level.SEVERE, null, ex);
            //System.out.println("NO ENTRO LA CONECCION");
        }
        return rs;
    }
    public static void CrearDb(){
        Connection dbConnection = null;
        try {
            
                String strUrl = "jdbc:derby:C:\\Gestion\\DB\\respaldo.db;create=true";
                    Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
                     dbConnection = DriverManager.getConnection (strUrl,"maurodim","mau*2012");
                     String sql="CREATE TABLE APP.articulos( ID  INTEGER not null primary key, BARRAS   varchar(30) default NULL, NOMBRE   varchar(49) default NULL, SERVICIO   double default NULL, COSTO   double default NULL, PRECIO   double default NULL, MINIMO   INTEGER default NULL, STOCK  INTEGER default NULL, PROVEEDOR  INTEGER default NULL, RUBRON  varchar(12) default NULL, ALTA  varchar(19) default NULL, INHABILITADO  INTEGER not null , idRubro   INTEGER not null,equivalencia  double not null, modificaPrecio  INTEGER not null, modificaServicio INTEGER not null,recargo double not null,servicio1 Double default 0.00)";
                     //st=dbConnection.createStatement();
                     PreparedStatement pstm=dbConnection.prepareStatement(sql);
                     pstm.execute();
                     pstm.close();
                     sql="CREATE TABLE APP.billetes (id  INTEGER not null primary key,descripcion  varchar(30) not null,valor  double not null)";
                     PreparedStatement pstm1=dbConnection.prepareStatement(sql);
                     pstm1.execute();
                     pstm1.close();
                     sql="CREATE TABLE APP.caja (numero  INTEGER primary key,numeroSucursal  INTEGER not null,numeroUsuario  INTEGER not null,tipoMovimiento  INTEGER not null,saldoinicial double not null,estado INTEGER not null,tipo INTEGER not null,saldofinal double,fechacierre varchar(30),diferencia double)";
                     PreparedStatement pstm2=dbConnection.prepareStatement(sql);
                     pstm2.execute();
                     pstm2.close();
                     sql="CREATE TABLE APP.coeficienteslistas (id  INTEGER not null primary key,coeficiente  double not null,descripcion  varchar(30) not null)";
                     PreparedStatement pstm3=dbConnection.prepareStatement(sql);
                     pstm3.execute();
                     pstm3.close();
                     sql="CREATE TABLE APP.comprobantes  (numero  INTEGER not null primary key,descripcion  varchar(4) not null,destinatarioCondicion  INTEGER not null,descargaStock  INTEGER not null)";
                     PreparedStatement pstm4=dbConnection.prepareStatement(sql);
                     pstm4.execute();
                     pstm4.close();
                     sql="CREATE TABLE APP.depositos (numero  INTEGER not null primary key,descripcion  varchar(100) not null,direccion  varchar(200) not null,telefono  varchar(200) not null)";
                     PreparedStatement pstm5=dbConnection.prepareStatement(sql);
                     pstm5.execute();
                     pstm5.close();
                     sql="CREATE TABLE APP.listcli  (COD_CLIENT  varchar(6),RAZON_SOCI  varchar(60),DOMICILIO  varchar(30),COND_VTA INTEGER not null,TELEFONO_1  varchar(30),LISTADEPRECIO INTEGER not null,NUMERODECUIT  varchar(30),CUPODECREDITO  double default NULL,empresa  varchar(3),id  INTEGER not null primary key,saldo double not null,saldoactual double not null,TIPO_IVA INTEGER not null,COEFICIENTE INTEGER not null)";
                     PreparedStatement pstm6=dbConnection.prepareStatement(sql);
                     pstm6.execute();
                     pstm6.close();
                     sql="CREATE TABLE APP.proveedores (ID  INTEGER default NULL,nombre  varchar(19) default NULL,DOMICILIO  varchar(100) default NULL,LOCALIDAD  varchar(8) default NULL,TELEFONO  varchar(10) default NULL,INHABILITADO  INTEGER default NULL,numero  INTEGER not null primary key,mail  varchar(200) not null,saldo double)";
                     PreparedStatement pstm7=dbConnection.prepareStatement(sql);
                     pstm7.execute();
                     pstm7.close();
                     sql="CREATE TABLE APP.sucursal (numero  INTEGER not null primary key,descripcion  varchar(100) not null,direccion  varchar(100) not null,telefono  varchar(100) not null,deposito  INTEGER not null)";
                     PreparedStatement pstm8=dbConnection.prepareStatement(sql);
                     pstm8.execute();
                     pstm8.close();
                     sql="CREATE TABLE APP.tipoacceso (numero INTEGER not null primary key,descripcion  varchar(20) not null,nivel  INTEGER not null,menu1  INTEGER not null,menu2  INTEGER not null,menu3  INTEGER not null,menu4  INTEGER not null,menu5  INTEGER not null,menu6  INTEGER not null,menu7  INTEGER not null)";
                     PreparedStatement pstm9=dbConnection.prepareStatement(sql);
                     pstm9.execute();
                     pstm9.close();
                     sql="CREATE TABLE APP.tipocomprobantes  (numero  INTEGER not null primary key,descripcion  varchar(50) not null,numeroActivo  INTEGER not null,numeroSucursal  INTEGER not null)";
                     PreparedStatement pstm10=dbConnection.prepareStatement(sql);
                     pstm10.execute();
                     
                    
                     pstm10.close();
                     sql="CREATE TABLE APP.usuarios  (numero INTEGER not null primary key,nombre  varchar(70) not null,direccion  varchar(200) not null,localidad  varchar(70) not null,telefono  varchar(100) not null,mail  varchar(100) not null,nombreUsuario  varchar(100) not null,clave  varchar(100) not null,autorizacion  INTEGER not null,numeroTipoAcceso  INTEGER not null,sucursal  INTEGER not null)";
                     PreparedStatement pstm11=dbConnection.prepareStatement(sql);
                     pstm11.execute();
                    
                     pstm11.close();
                     sql="CREATE TABLE APP.fallas (st varchar(300) not null,estado INTEGER not null)";
                     PreparedStatement pstm12=dbConnection.prepareStatement(sql);
                     pstm12.execute();
                     pstm12.close();
                     sql="CREATE TABLE APP.tipomovimientos(ID INTEGER not null primary key,DESCRIPCION varchar(30)not null,DESTINOOPERACION INTEGER not null,MULTIPLOOP INTEGER not null)";
                     PreparedStatement pstm13=dbConnection.prepareStatement(sql);
                     pstm13.execute();
                     pstm13.close();
                     sql="CREATE TABLE APP.movimientosarticulos (tipoMovimiento INTEGER not null,idArticulo INTEGER not null,cantidad double not null,numeroDeposito INTEGER not null,tipoComprobante INTEGER not null,numeroComprobante INTEGER not null,numeroCliente INTEGER NOT NULL,fechaComprobante varchar(30) not null,numeroUsuario INTEGER NOT NULL,precioDeCosto double not null,precioDeVenta double not null,precioServicio double NOT NULL,estado INTEGER,fecha date default current_date,idcaja integer)";
                     PreparedStatement pstm14=dbConnection.prepareStatement(sql);
                     pstm14.execute();
                     pstm14.close();
                     sql="CREATE TABLE APP.movimientoscaja (numeroUsuario INTEGER not null,idCliente INTEGER,numeroSucursal INTEGER NOT NULL,numeroComprobante INTEGER NOT NULL,tipoComprobante INTEGER NOT NULL,monto Double,tipoMovimiento INTEGER NOT NULL,idCaja INTEGER NOT NULL,cantidad double,pagado INTEGER NOT NULL,observaciones varchar(100),estado INTEGER,tipoCliente INTEGER)";
                     PreparedStatement pstm15=dbConnection.prepareStatement(sql);
                     pstm15.execute();
                     pstm15.close();
                     sql="CREATE TABLE APP.movimientosclientes (numeroProveedor INTEGER NOT NULL,monto double not null,pagado INTEGER,numeroComprobante INTEGER,idRemito INTEGER,idUsuario INTEGER NOT NULL,idCaja INTEGER NOT NULL,fechaPago varchar(20),tipoComprobante INTEGER,idSucursal INTEGER NOT NULL,estado INTEGER)";
                     PreparedStatement pstm16=dbConnection.prepareStatement(sql);
                     pstm16.execute();
                    pstm16.close();
                    sql="CREATE TABLE APP.movimientosproveedores (numeroProveedor INTEGER NOT NULL,monto double not null,pagado INTEGER,numeroComprobante varchar(30),idRemito INTEGER,idUsuario INTEGER NOT NULL,idCaja INTEGER NOT NULL,fechaPago varchar(20),tipoComprobante INTEGER,idSucursal INTEGER NOT NULL,estado INTEGER)";
                    PreparedStatement pstm17=dbConnection.prepareStatement(sql);
                    pstm17.execute();
                    pstm17.close();
                    sql="CREATE TABLE APP.movimientosusuarios (numeroUsuario INTEGER NOT NULL,tipoAcceso INTEGER,entrada varchar(30) not null,estado INTEGER NOT NULL)";
                    PreparedStatement pstm18=dbConnection.prepareStatement(sql);
                    pstm18.execute();
                    pstm18.close();
                    sql="CREATE TABLE APP.movimientosdesucursales (depOrigen INTEGER,depDestino INTEGER,idArticulo INTEGER not null,cantidad double not null,confirmado INTEGER,numeroRemito INTEGER,idUsuario INTEGER,diferencia double,idUsuarioRecep INTEGER not null)";
                    PreparedStatement pstm19=dbConnection.prepareStatement(sql);
                    pstm19.execute();
                    sql="create table app.combo (id integer not null primary key,fecha date,idarticulo integer not null,cantidad double,articulopadre integer not null)";
                    pstm19=dbConnection.prepareStatement(sql);
                    pstm19.execute();
                    pstm19.close();
                    /*
                    sql="insert into APP.usuarios (numero,nombre,direccion,telefono,mail,nombreusuario,clave,autorizacion,numerotipoacceso,localidad,sucursal) values (1,'administrador','piedras 6738','155451500','contacto@maurodi.com.ar','MM','mm',1,1,'santa fe',1)";
                    PreparedStatement pstm20=dbConnection.prepareStatement(sql);
                    pstm20.execute();
                    pstm20.close();
                    */
                    
                    /*
                    Articulos.RecargarMap();
                    Proveedores.cargarListadoProv();
                    ClientesTango.cargarMap();
                    ListasDePrecios.cargarMap();
                    */ 
                    Depositos deposito=new Depositos();
                    deposito.setNumero(1);
                    Inicio.deposito=deposito;
                    
                    Articulos.BackapearMap(1);
                    Proveedores.BackapearProveedores();
                    ClientesTango.BackapearClientes();
                    ListasDePrecios.BackapearListasDePrecios();
                    Cajas.BackapearCajas();
                    Cajas.LeerCajaAdministradora();
                    Inicio.coneccionRemota=true;
                    Usuarios.BackapearUsuarios();
                    Inicio.coneccionRemota=false;
                    Sucursales.BackapearSucursales();
                    Depositos.BackapearDepositos();
                    
                    Articulos.RecargarMap(1);
                    Proveedores.cargarListadoProv();
                    ClientesTango.cargarMap();
                    ListasDePrecios.cargarMap();
                    
                     JOptionPane.showMessageDialog(null,"BASE DE DATOS CORRECTAMENTE CREADA");
        } catch (SQLException ex) {
            Logger.getLogger(ConeccionInstalacion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConeccionInstalacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        //return dbConnection;
        //return dbConnection;
        //return dbConnection;
        //return dbConnection;
    }
}

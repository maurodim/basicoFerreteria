/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import Conversores.Numeros;
import com.mysql.jdbc.CommunicationsException;
import interfaceGraficas.Inicio;
import interfaces.Editables;
import interfaces.Modificable;
import interfaces.Transaccionable;
import interfacesPrograma.Facturar;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mauro
 */
public class Articulos implements Facturar,Editables,Modificable{
    private String codigoDeBarra;
    private String codigoAsignado;
    private Integer rubro;
    private String nrubro;
    private Integer numeroId;
    private String descripcionArticulo;
    private Double cantidad;
    private Double precioUnitario;
    private Double precioIva;
    private Double precioUnitarioNeto;
    private Double equivalencia;
    private Double precioDeCosto;
    private Double stockMinimo;
    private Double stockActual;
    private Double precioServicio=0.00;
    private Boolean confirmado;
    private Double recargo;
    private Boolean modificaPrecio;
    private static ConcurrentHashMap listadoBarr=new ConcurrentHashMap();
    private static ConcurrentHashMap listadoNom=new ConcurrentHashMap();
    private static ConcurrentHashMap listadoCodigo=new ConcurrentHashMap();
    private Double diferenciaRemitida;
    private Boolean modificaServicio;
    private Double precioServicio1=0.00;
    private static ConcurrentHashMap listadoBarr1=new ConcurrentHashMap();
    private static ConcurrentHashMap listadoNom1=new ConcurrentHashMap();
    private static ConcurrentHashMap listadoCodigo1=new ConcurrentHashMap();
    private ArrayList combo;
    private Integer idCombo;
    private static ArrayList listCombo=new ArrayList();
    private Integer idDeposito;
    private static Transaccionable tra=new ConeccionLocal();
    private static ResultSet rr;

    public Integer getIdDeposito() {
        return idDeposito;
    }

    public void setIdDeposito(Integer idDeposito) {
        this.idDeposito = idDeposito;
    }
    

    public Integer getIdCombo() {
        return idCombo;
    }

    public void setIdCombo(Integer idCombo) {
        this.idCombo = idCombo;
    }

    
    public ArrayList getCombo() {
        return combo;
    }

    public void setCombo(ArrayList combo) {
        this.combo = combo;
    }
    

    public Double getPrecioServicio1() {
        return precioServicio1;
    }

    public void setPrecioServicio1(Double precioServicio1) {
        this.precioServicio1 = precioServicio1;
    }
    

    public Boolean getModificaServicio() {
        return modificaServicio;
    }

    public void setModificaServicio(Boolean modificaServicio) {
        this.modificaServicio = modificaServicio;
    }

    
    public Double getDiferenciaRemitida() {
        return diferenciaRemitida;
    }

    public void setDiferenciaRemitida(Double diferenciaRemitida) {
        this.diferenciaRemitida = diferenciaRemitida;
    }
    
    

    public Boolean getModificaPrecio() {
        return modificaPrecio;
    }

    public void setModificaPrecio(Boolean modificaPrecio) {
        this.modificaPrecio = modificaPrecio;
    }
    
    public Double getRecargo() {
        return recargo;
    }

    public void setRecargo(Double recargo) {
        this.recargo = recargo;
    }
    
    

    public Boolean getConfirmado() {
        return confirmado;
    }

    public void setConfirmado(Boolean confirmado) {
        this.confirmado = confirmado;
    }
    

    public Double getPrecioServicio() {
        return precioServicio;
    }

    public void setPrecioServicio(Double precioServicio) {
        if(precioServicio==null){
            precioServicio=0.00;
        }
        this.precioServicio = precioServicio;
    }
    

    public Double getStockActual() {
        return stockActual;
    }

    public void setStockActual(Double stockActual) {
        this.stockActual = stockActual;
        /*
         * codigo vista stockart
         * select articulos.id,(select sum(movimientosarticulos.cantidad) from movimientosarticulos where movimientosarticulos.idArticulo=articulos.id)as stock from articulos order by id
         */
    }
    
    public Double getPrecioDeCosto() {
        return precioDeCosto;
    }

    public void setPrecioDeCosto(Double precioDeCosto) {
        this.precioDeCosto = precioDeCosto;
    }

    public Double getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(Double stockMinimo) {
        this.stockMinimo = stockMinimo;
    }
    

    public Double getEquivalencia() {
        return equivalencia;
    }

    public void setEquivalencia(Double equivalencia) {
        this.equivalencia = equivalencia;
    }
    

    public String getNrubro() {
        return nrubro;
    }

    public void setNrubro(String nrubro) {
        this.nrubro = nrubro;
    }

    public Articulos() {
    }

    public String getCodigoDeBarra() {
        return codigoDeBarra;
    }

    public void setCodigoDeBarra(String codigoDeBarra) {
        this.codigoDeBarra = codigoDeBarra;
    }

    public String getCodigoAsignado() {
        return codigoAsignado;
    }

    public void setCodigoAsignado(String codigoAsignado) {
        this.codigoAsignado = codigoAsignado;
    }

    public Integer getRubro() {
        return rubro;
    }

    public void setRubro(Integer rubro) {
        this.rubro = rubro;
    }

    public Integer getNumeroId() {
        return numeroId;
    }

    public void setNumeroId(Integer numeroId) {
        this.numeroId = numeroId;
    }

    public String getDescripcionArticulo() {
        return descripcionArticulo;
    }

    public void setDescripcionArticulo(String descripcionArticulo) {
        this.descripcionArticulo = descripcionArticulo;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        
        this.precioUnitario = precioUnitario;
    }

    public Double getPrecioIva() {
        return precioIva;
    }

    public void setPrecioIva(Double precioIva) {
        this.precioIva = precioIva;
    }

    public Double getPrecioUnitarioNeto() {
        
        return precioUnitarioNeto;
    }

    public void setPrecioUnitarioNeto(Double precioUnitarioNeto) {
      
        this.precioUnitarioNeto = precioUnitarioNeto;
        //System.err.println("RECIBIDO "+precioUnitarioNeto+" resultado "+this.precioUnitarioNeto+" recargo "+this.recargo+"\n");
    }
    
    public static void CargarMap(){
        // ACA SE CARGA EL MAP PARA TENER ACCESO A LOS ARTICULOS SIN ESTAR CONECTADO , LA CLAVE EL CODIGO DE BARRA
        listadoBarr.clear();
             listadoNom.clear();
             listadoCodigo.clear();
        Transaccionable tra;
        //ArrayList resultado=new ArrayList();
        String sql="";
        Articulos articulo=null;
        /*
        if(listadoBarr.size()==0){
            tra=new Conecciones();
            sql="select *,(select stockart.stock from stockart where stockart.id=articulos.ID)as stock,(select rubros.recargo from rubros where rubros.id=articulos.idRubro)as recargo from articulos where INHABILITADO=0";
            
        }else{
         */
            tra=new ConeccionLocal();
            
            sql="select articulos.ID,articulos.NOMBRE,articulos.BARRAS,articulos.recargo,articulos.PRECIO,articulos.equivalencia,articulos.COSTO,articulos.MINIMO,(articulos.STOCK) as stock,articulos.SERVICIO, articulos.modificaPrecio,articulos.modificaServicio,articulos.SERVICIO1,articulos.idcombo from articulos where INHABILITADO=0";
            
        //}
        
        
        ResultSet rr=tra.leerConjuntoDeRegistros(sql);
        String codA="";
        try {
            while(rr.next()){
                articulo=new Articulos();
                articulo.setCodigoAsignado(rr.getString("ID"));
                articulo.setDescripcionArticulo(rr.getString("NOMBRE"));
                articulo.setNumeroId(rr.getInt("ID"));
                articulo.setCodigoDeBarra(rr.getString("BARRAS"));
                articulo.setRecargo(rr.getDouble("recargo"));
                articulo.setPrecioUnitarioNeto(rr.getDouble("PRECIO"));
                articulo.setEquivalencia(rr.getDouble("equivalencia"));
                articulo.setPrecioDeCosto(rr.getDouble("COSTO"));
                articulo.setStockMinimo(rr.getDouble("MINIMO"));
                articulo.setStockActual(rr.getDouble("stock"));
                articulo.setPrecioServicio(rr.getDouble("SERVICIO"));
                articulo.setModificaPrecio(rr.getBoolean("modificaPrecio"));
                articulo.setModificaServicio(rr.getBoolean("modificaServicio"));
                articulo.setPrecioServicio1(rr.getDouble("servicio1"));
                articulo.setIdCombo(rr.getInt("idcombo"));
                listadoBarr.putIfAbsent(articulo.getCodigoDeBarra(),articulo);
                codA=articulo.getCodigoAsignado();
                listadoCodigo.putIfAbsent(codA,articulo);
                
                //resultado.add(articulo);
            }
                  } catch (SQLException ex) {
            Logger.getLogger(Articulos.class.getName()).log(Level.SEVERE, null, ex);
            //System.out.println("ACA DEBE LEER EN LE ARCHIVO");
            
        }
        /*
        if(Inicio.coneccionRemota){
        sql="select *,(select stockart.stock from stockart where stockart.id=articulos.ID)as stock,(select rubros.recargo from rubros where rubros.id=articulos.idRubro)as recargo from articulos where INHABILITADO=0 order by NOMBRE";
        }else{
        */ 
            sql="select articulos.ID,articulos.NOMBRE,articulos.BARRAS,articulos.recargo,articulos.PRECIO,articulos.equivalencia,articulos.COSTO,articulos.MINIMO,(articulos.STOCK) as stock,articulos.SERVICIO, articulos.modificaPrecio,articulos.modificaServicio,articulos.servicio1 from articulos where INHABILITADO=0 order by NOMBRE";
        //}
        rr=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rr.next()){
                articulo=new Articulos();
                articulo.setCodigoAsignado(rr.getString("ID"));
                articulo.setDescripcionArticulo(rr.getString("NOMBRE"));
                articulo.setNumeroId(rr.getInt("ID"));
                articulo.setCodigoDeBarra(rr.getString("BARRAS"));
                articulo.setRecargo(rr.getDouble("recargo"));
                articulo.setPrecioUnitarioNeto(rr.getDouble("PRECIO"));
                articulo.setEquivalencia(rr.getDouble("equivalencia"));
                articulo.setPrecioDeCosto(rr.getDouble("COSTO"));
                articulo.setStockMinimo(rr.getDouble("MINIMO"));
                articulo.setStockActual(rr.getDouble("stock"));
                articulo.setPrecioServicio(rr.getDouble("SERVICIO"));
                articulo.setModificaPrecio(rr.getBoolean("modificaPrecio"));
                articulo.setModificaServicio(rr.getBoolean("modificaServicio"));
                articulo.setPrecioServicio1(rr.getDouble("SERVICIO1"));
                String nom=rr.getString("NOMBRE");
                listadoNom.putIfAbsent(nom,articulo);
                //resultado.add(articulo);
            }
            rr.close();
            //System.out.println(" CANTIDAD MAP inicial "+listadoBarr.size());
           
        } catch (SQLException ex) {
            Logger.getLogger(Articulos.class.getName()).log(Level.SEVERE, null, ex);
        }
        //if(Inicio.coneccionRemota)BackapearMap();
        //if(Inicio.coneccionRemota)BackapearMap();
    }
    public static synchronized void RecargarMap(Integer funcion){
        

        //System.out.println(" CANTIDAD MAP 1 - "+listadoBarr1.size());
        // ACA SE CARGA EL MAP PARA TENER ACCESO A LOS ARTICULOS SIN ESTAR CONECTADO , LA CLAVE EL CODIGO DE BARRA
        Transaccionable tra=new Conecciones();
        //ArrayList resultado=new ArrayList();
        Articulos articulo=null;
        
        /*
        String sql="select *,(select stockart.stock from stockart where stockart.id=articulos.ID)as stock,(select rubros.recargo from rubros where rubros.id=articulos.idRubro)as recargo from articulos where INHABILITADO=0 and actualizacion=0";
        ResultSet rr=tra.leerConjuntoDeRegistros(sql);
        try {
            listadoBarr1.clear();
             listadoNom1.clear();
             listadoCodigo1.clear();
            while(rr.next()){
                articulo=new Articulos();
                articulo.setCodigoAsignado(rr.getString("ID"));
                articulo.setDescripcionArticulo(rr.getString("NOMBRE"));
                articulo.setNumeroId(rr.getInt("ID"));
                articulo.setCodigoDeBarra(rr.getString("BARRAS"));
                articulo.setRecargo(rr.getDouble("recargo"));
                articulo.setPrecioUnitarioNeto(rr.getDouble("PRECIO"));
                articulo.setEquivalencia(rr.getDouble("equivalencia"));
                articulo.setPrecioDeCosto(rr.getDouble("COSTO"));
                articulo.setStockMinimo(rr.getDouble("MINIMO"));
                articulo.setStockActual(rr.getDouble("stock"));
//                System.out.println(Inicio.sucursal.getDireccion());
                try{
                if(Inicio.sucursal.getDireccion().equals("1")){
                articulo.setPrecioServicio(rr.getDouble("SERVICIO"));
                }else{
                    articulo.setPrecioServicio(rr.getDouble("SERVICIO1"));
                }
                }catch(NullPointerException nEx){
                    articulo.setPrecioServicio(rr.getDouble("SERVICIO"));
                    articulo.setPrecioServicio1(rr.getDouble("SERVICIO1"));
                }
                articulo.setModificaPrecio(rr.getBoolean("modificaPrecio"));
                articulo.setModificaServicio(rr.getBoolean("modificaServicio"));
                listadoBarr1.putIfAbsent(articulo.getCodigoDeBarra(),articulo);
               listadoCodigo1.putIfAbsent(articulo.getCodigoAsignado(),articulo);
                //resultado.add(articulo);
            }
                  } catch (SQLException ex) {
            Logger.getLogger(Articulos.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        Transaccionable tta=new Conecciones();
        String sql1="";
        Boolean completo=false;
        String sql="";
        listadoNom1.clear();
        int contador=0;
        ArrayList comboD;
        ResultSet rr;
        switch (funcion){
            case 1:
                sql="select *,(0.00)as stock from articulos where INHABILITADO=0 order by ID";
                break;
            case 2:
                sql="select *,(select sum(cantidad) from movimientosarticulos where movimientosarticulos.numerodeposito="+Inicio.deposito.getNumero()+" and movimientosarticulos.idArticulo=articulosmodificacion.ID)as stock from articulosmodificacion";
                break;
            case 3:
                sql="select *,(select sum(cantidad) from movimientosarticulos where movimientosarticulos.numerodeposito="+Inicio.deposito.getNumero()+" and movimientosarticulos.idArticulo=articulosnuevos.ID)as stock from articulosnuevos";
                break;
            case 4:
                sql="select * from articuloseliminados";
                break;
            default:
                System.out.println("OJO QUE NO ENTRO EN NINGUNA OPCION");
                break;
        }
        System.out.println(sql);
        rr=tra.leerConjuntoDeRegistros(sql);
        try {
            
            while(rr.next()){
                articulo=new Articulos();
                articulo.setCodigoAsignado(rr.getString("ID"));
                
                articulo.setDescripcionArticulo(rr.getString("NOMBRE"));
                articulo.setNumeroId(rr.getInt("ID"));
                if(articulo.getNumeroId()==321){
                  //  JOptionPane.showMessageDialog(null,"coca");
                }
                articulo.setCodigoDeBarra(rr.getString("BARRAS"));
                articulo.setRecargo(rr.getDouble("recargo"));
                articulo.setPrecioUnitarioNeto(rr.getDouble("PRECIO"));
                articulo.setEquivalencia(rr.getDouble("equivalencia"));
                articulo.setPrecioDeCosto(rr.getDouble("COSTO"));
                articulo.setStockMinimo(rr.getDouble("MINIMO"));
                articulo.setStockActual(rr.getDouble("stock"));
                try{
                if(Inicio.sucursal.getDireccion().equals("1")){
                articulo.setPrecioServicio(rr.getDouble("SERVICIO"));
                }else{
                    articulo.setPrecioServicio(rr.getDouble("SERVICIO"));
                    articulo.setPrecioServicio1(rr.getDouble("SERVICIO"));
                }
                }catch(NullPointerException nEx){
                    articulo.setPrecioServicio(rr.getDouble("SERVICIO"));
                    articulo.setPrecioServicio1(rr.getDouble("SERVICIO"));
                }
                articulo.setModificaPrecio(rr.getBoolean("modificaPrecio"));
                articulo.setModificaServicio(rr.getBoolean("modificaServicio"));
                articulo.setIdCombo(rr.getInt("idcombo"));
                /*
                if(articulo.getIdCombo() > 0){
                   sql1="select * from combo where articuloPadre ="+articulo.getNumeroId();
                   comboD=new ArrayList();
                   Articulos arti;
                   ResultSet rs=tta.leerConjuntoDeRegistros(sql1);
                   while(rs.next()){
                       arti=new Articulos();
                       arti.setCantidad(rs.getDouble("cantidad"));
                       arti.setNumeroId(rs.getInt("idarticulo"));
                       comboD.add(arti);
                   }
                   rs.close();
                   articulo.setCombo(comboD);
                }
                */
                String nom=rr.getString("NOMBRE");
                listadoNom1.putIfAbsent(nom,articulo);
                //resultado.add(articulo);
            }
            
            contador=contador + 100;
            System.out.println(sql);
            
//            sql="update actualizaciones set estado=1 where iddeposito="+Inicio.deposito.getNumero()+" and idobjeto=1";
//            tra.guardarRegistro(sql);
            
        } catch (SQLException ex) {
            Logger.getLogger(Articulos.class.getName()).log(Level.SEVERE, null, ex);
            
            completo=true;
        } catch(NullPointerException ey){
            System.err.println(" error de punto nulo en tabla combo :"+ey);
        }
            try {
                String sent="";
            sql="select * from combo";
            rr=tra.leerConjuntoDeRegistros(sql);
            listCombo=new ArrayList();
            while(rr.next()){
                sent="insert into combo (idarticulo,cantidad,articulopadre) values ("+rr.getInt("idarticulo")+","+rr.getDouble("cantidad")+","+rr.getInt("articuloPadre")+")";
                listCombo.add(sent);
            }
                rr.close();
            } catch (SQLException ex) {
                Logger.getLogger(Articulos.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Comunicacion interrumpida por superar el maximo de tiempo ");
            } catch(NullPointerException ey){
            System.err.println(" error de punto nulo en tabla combo :"+ey);
        }
        

    }
    public static synchronized void BackapearMap(Integer funcion){
        Transaccionable tt=new ConeccionLocal();
        System.out.println(" CANTIDAD MAP back "+listadoBarr1.size());
        String sql="";
    //String sql="delete from articulos";
      //  tt.guardarRegistro(sql);
        String criterio="";
        Integer canttt=listadoNom1.size();
        Integer contador=0;
        Articulos articulo=null;
        criterio=criterio.toUpperCase();
        int comboOk=0;
        Enumeration<Articulos> elementos=listadoNom1.elements();
        if(listCombo.size() > 0){
        sql="truncate table combo";
                tt.guardarRegistro(sql);
                comboOk=1;
        }
        if(funcion==1){
            sql="truncate table articulos";
                tt.guardarRegistro(sql);
                
        }
        while(elementos.hasMoreElements()){
            articulo=(Articulos)elementos.nextElement();
            int pos=articulo.getDescripcionArticulo().indexOf(criterio);
            int mod=0;
            int serv=0;
            if(articulo.getModificaPrecio())mod=1;
            if(articulo.getModificaServicio())serv=1;
            if(articulo.getDescripcionArticulo().equals(""))articulo.setDescripcionArticulo("--");
            
            switch (funcion){
            case 1:
                
                sql="insert into articulos (id,nombre,barras,servicio,costo,precio,minimo,stock,equivalencia,modificaprecio,modificaservicio,recargo,inhabilitado,idrubro,servicio1,idcombo) values ("+articulo.getNumeroId()+",'"+articulo.getDescripcionArticulo()+"','"+articulo.getCodigoDeBarra()+"',"+articulo.getPrecioServicio()+","+articulo.getPrecioDeCosto()+","+articulo.getPrecioUnitarioNeto()+","+articulo.getStockMinimo()+","+articulo.getStockActual()+","+articulo.getEquivalencia()+","+mod+","+serv+","+articulo.getRecargo()+",0,0,"+articulo.getPrecioServicio1()+","+articulo.getIdCombo()+")";
                break;
            case 2:
                
                sql="update articulos set nombre='"+articulo.getDescripcionArticulo()+"',barras='"+articulo.getCodigoDeBarra()+"',servicio="+articulo.getPrecioServicio()+",costo="+articulo.getPrecioDeCosto()+",precio="+articulo.getPrecioUnitarioNeto()+",minimo="+articulo.getStockMinimo()+",stock="+articulo.getStockActual()+",equivalencia="+articulo.getEquivalencia()+",modificaprecio="+mod+",modificaservicio="+serv+",recargo="+articulo.getRecargo()+",servicio1="+articulo.getPrecioServicio1()+",idcombo="+articulo.getIdCombo()+" where id="+articulo.getNumeroId();
                break;
            case 3:
               
                sql="insert into articulos (id,nombre,barras,servicio,costo,precio,minimo,stock,equivalencia,modificaprecio,modificaservicio,recargo,inhabilitado,idrubro,servicio1,idcombo) values ("+articulo.getNumeroId()+",'"+articulo.getDescripcionArticulo()+"','"+articulo.getCodigoDeBarra()+"',"+articulo.getPrecioServicio()+","+articulo.getPrecioDeCosto()+","+articulo.getPrecioUnitarioNeto()+","+articulo.getStockMinimo()+","+articulo.getStockActual()+","+articulo.getEquivalencia()+","+mod+","+serv+","+articulo.getRecargo()+",0,0,"+articulo.getPrecioServicio1()+","+articulo.getIdCombo()+")";
                break;
            case 4:
                sql="delete from articulos wher id="+articulo.getNumeroId();
                break;
            default:
                System.out.println("OJO QUE NO ENTRO EN NINGUNA OPCION");
                break;
        }
            //sql="insert into articulos (id,nombre,barras,servicio,costo,precio,minimo,stock,equivalencia,modificaprecio,modificaservicio,recargo,inhabilitado,idrubro,servicio1) values ("+articulo.getNumeroId()+",'"+articulo.getDescripcionArticulo()+"','"+articulo.getCodigoDeBarra()+"',"+articulo.getPrecioServicio()+","+articulo.getPrecioDeCosto()+","+articulo.getPrecioUnitarioNeto()+","+articulo.getStockMinimo()+","+articulo.getStockActual()+","+articulo.getEquivalencia()+","+mod+","+serv+","+articulo.getRecargo()+",0,0,"+articulo.getPrecioServicio1()+")";
            //sql="update articulos set nombre='"+articulo.getDescripcionArticulo()+"',barras='"+articulo.getCodigoDeBarra()+"',servicio="+articulo.getPrecioServicio()+",costo="+articulo.getPrecioDeCosto()+",precio="+articulo.getPrecioUnitarioNeto()+",minimo="+articulo.getStockMinimo()+",stock="+articulo.getStockActual()+",equivalencia="+articulo.getEquivalencia()+",modificaprecio="+mod+",modificaservicio="+serv+",recargo="+articulo.getRecargo()+",inhabilitado=0,idrubro=0,servicio1="+articulo.getPrecioServicio1()+" where id="+articulo.getNumeroId();
            contador++;
            System.out.println("van "+contador+" de "+canttt);
            System.out.println("hash "+sql);
            tt.guardarRegistro(sql);
           
            
        }
         if(listCombo.size() > 0){
                String art="";
                Iterator ic=listCombo.listIterator();
                while(ic.hasNext()){
                    art=(String)ic.next();
                    sql=art;
                    System.out.println("combo: "+sql);
                    tt.guardarRegistro(sql);
                }
            }
        //CargarMap();
    }
    public static synchronized void BackapearMap1(){
        Transaccionable tt=new ConeccionLocal();
        System.out.println(" CANTIDAD MAP back "+listadoNom1.size());
        //String sql="";
    String sql="truncate table articulos";
        tt.guardarRegistro(sql);
        sql="truncate table combo";
        tt.guardarRegistro(sql);
        String criterio="";
        Articulos articulo=null;
        criterio=criterio.toUpperCase();
        Enumeration<Articulos> elementos=listadoNom1.elements();
        while(elementos.hasMoreElements()){
            articulo=(Articulos)elementos.nextElement();
            int pos=articulo.getDescripcionArticulo().indexOf(criterio);
            int mod=0;
            int serv=0;
            if(articulo.getModificaPrecio())mod=1;
            if(articulo.getModificaServicio())serv=1;
            if(articulo.getDescripcionArticulo().equals(""))articulo.setDescripcionArticulo("--");
            
            sql="insert into articulos (id,nombre,barras,servicio,costo,precio,minimo,stock,equivalencia,modificaprecio,modificaservicio,recargo,inhabilitado,idrubro,servicio1,idcombo) values ("+articulo.getNumeroId()+",'"+articulo.getDescripcionArticulo()+"','"+articulo.getCodigoDeBarra()+"',"+articulo.getPrecioServicio()+","+articulo.getPrecioDeCosto()+","+articulo.getPrecioUnitarioNeto()+","+articulo.getStockMinimo()+","+articulo.getStockActual()+","+articulo.getEquivalencia()+","+mod+","+serv+","+articulo.getRecargo()+",0,0,"+articulo.getPrecioServicio1()+","+articulo.getIdCombo()+")";
            //sql="update articulos set nombre='"+articulo.getDescripcionArticulo()+"',barras='"+articulo.getCodigoDeBarra()+"',servicio="+articulo.getPrecioServicio()+",costo="+articulo.getPrecioDeCosto()+",precio="+articulo.getPrecioUnitarioNeto()+",minimo="+articulo.getStockMinimo()+",stock="+articulo.getStockActual()+",equivalencia="+articulo.getEquivalencia()+",modificaprecio="+mod+",modificaservicio="+serv+",recargo="+articulo.getRecargo()+",inhabilitado=0,idrubro=0,servicio1="+articulo.getPrecioServicio1()+" where id="+articulo.getNumeroId();
            System.out.println("hash "+sql);
            tt.guardarRegistro(sql);
            
            
        }
        if(listCombo.size() > 0){
                String art="";
                Iterator ic=listCombo.listIterator();
                while(ic.hasNext()){
                    art=(String)ic.next();
                    sql=art;
                    tt.guardarRegistro(sql);
                }
            }
        //CargarMap();
    }
    private ArrayList CargarCombo(Integer id){
        Transaccionable tt=new ConeccionLocal();
        String sql="select * from combo where articuloPadre="+id;
        ArrayList listadoA=new ArrayList();
        ResultSet rr=tt.leerConjuntoDeRegistros(sql);
        Articulos artic;
        try {
            while(rr.next()){
                artic=new Articulos();
                artic.setNumeroId(rr.getInt("idarticulo"));
                artic.setCantidad(rr.getDouble("cantidad"));
                listadoA.add(artic);
            }
            rr.close();
        } catch (SQLException ex) {
            Logger.getLogger(Articulos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listadoA;
    }
    @Override
    public Boolean guardar(Object oob) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean modificar(Object oob) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean nuevo(Object oob) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList listar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object leer(Object oob) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void imprimirComprobante(int tipoComprobante, Object oob) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList listadoBusqueda(String criterio) {
        
        ArrayList resultado=new ArrayList();
        Articulos articulo=null;
        criterio=criterio.toUpperCase();
        String sql="select *,(select sum(cantidad) FROM movimientosarticulos where movimientosarticulos.idArticulo=articulos.ID group by idArticulo,numeroDeposito limit 0,1)as sstock from articulos where nombre like '%"+criterio+"%'";
        
        //Transaccionable tra=new ConeccionLocal();
        rr=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rr.next()){
                articulo=new Articulos();
                articulo.setCodigoAsignado(rr.getString("ID"));
                articulo.setDescripcionArticulo(rr.getString("NOMBRE"));
                articulo.setNumeroId(rr.getInt("ID"));
                articulo.setCodigoDeBarra(rr.getString("BARRAS"));
                articulo.setRecargo(rr.getDouble("recargo"));
                articulo.setPrecioUnitarioNeto(rr.getDouble("PRECIO"));
                articulo.setEquivalencia(rr.getDouble("equivalencia"));
                articulo.setPrecioDeCosto(rr.getDouble("COSTO"));
                articulo.setStockMinimo(rr.getDouble("MINIMO"));
                articulo.setStockActual(rr.getDouble("sstock"));
                try{
                    if(Inicio.sucursal.getDireccion().equals("1")){
                        articulo.setPrecioServicio(0.00);
                    }else{
                        articulo.setPrecioServicio(0.00);
                    }
                }catch(NullPointerException nEx){
                    articulo.setPrecioServicio(0.00);
                    articulo.setPrecioServicio1(0.00);
                }
                articulo.setModificaPrecio(rr.getBoolean("modificaPrecio"));
                articulo.setModificaServicio(rr.getBoolean("modificaServicio"));
                String nom=rr.getString("NOMBRE");
                articulo.setIdCombo(rr.getInt("idcombo"));
                if(articulo.getIdCombo() > 0)articulo.setCombo(CargarCombo(articulo.getNumeroId()));
                resultado.add(articulo);
            }
            rr.close();
        } catch (SQLException ex) {
            Logger.getLogger(Articulos.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*
        Transaccionable tra=new Conecciones();
        ArrayList resultado=new ArrayList();
        Articulos articulo=null;
        String sql="select *,(select stockart.stock from stockart where stockart.id=articulos.ID)as stock,(select rubros.recargo from rubros where rubros.id=articulos.idRubro)as recargo from articulos where NOMBRE like '"+criterio+"%' and INHABILITADO=0";
        ResultSet rr=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rr.next()){
                articulo=new Articulos();
                articulo.setCodigoAsignado(rr.getString("ID"));
                articulo.setDescripcionArticulo(rr.getString("NOMBRE"));
                articulo.setNumeroId(rr.getInt("ID"));
                articulo.setCodigoDeBarra(rr.getString("BARRAS"));
                articulo.setRecargo(rr.getDouble("recargo"));
                articulo.setPrecioUnitarioNeto(rr.getDouble("PRECIO"));
                articulo.setEquivalencia(rr.getDouble("equivalencia"));
                articulo.setPrecioDeCosto(rr.getDouble("COSTO"));
                articulo.setStockMinimo(rr.getDouble("MINIMO"));
                articulo.setStockActual(rr.getDouble("stock"));
                articulo.setPrecioServicio(rr.getDouble("SERVICIO"));
                articulo.setModificaPrecio(rr.getBoolean("modificaPrecio"));
                resultado.add(articulo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Articulos.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        return resultado;
    }

    @Override
    public Boolean guardarNuevoCliente(Object cliente) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean modificarDatosDelCliente(Object cliente) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList listarClientes(String nombre) {
         ArrayList listado=new ArrayList();
        String sql="select id,nombre,barras,precio,equivalencia,costo,minimo,stock,servicio,servicio1,modificaprecio,modificaservicio,stock,recargo,idcombo from articulos where BARRAS like '"+nombre+"' and INHABILITADO=0";
        Transaccionable tra=new ConeccionLocal();
        ResultSet rr=tra.leerConjuntoDeRegistros(sql);
        Articulos articulo=new Articulos();
        
        try {
            while(rr.next()){
                articulo.setCodigoAsignado(rr.getString("ID"));
                articulo.setDescripcionArticulo(rr.getString("NOMBRE"));
                articulo.setNumeroId(rr.getInt("ID"));
                articulo.setCodigoDeBarra(rr.getString("BARRAS"));
                articulo.setRecargo(rr.getDouble("recargo"));
                articulo.setPrecioUnitarioNeto(rr.getDouble("PRECIO"));
                articulo.setEquivalencia(rr.getDouble("equivalencia"));
                articulo.setPrecioDeCosto(rr.getDouble("COSTO"));
                articulo.setStockMinimo(rr.getDouble("MINIMO"));
                articulo.setStockActual(rr.getDouble("stock"));
                
                        articulo.setPrecioServicio(0.00);
                
                articulo.setModificaPrecio(rr.getBoolean("modificaPrecio"));
                articulo.setModificaServicio(rr.getBoolean("modificaServicio"));
                String nom=rr.getString("NOMBRE");
                articulo.setIdCombo(rr.getInt("idcombo"));
                if(articulo.getIdCombo() > 0)articulo.setCombo(CargarCombo(articulo.getNumeroId()));
                listado.add(articulo);
            }
            rr.close();
        } catch (SQLException ex) {
            Logger.getLogger(Articulos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listado;
    }

    @Override
    public Object cargarPorCodigoDeBarra(String codigoDeBarra) {
        //Articulos articulo;
        //articulo=(Articulos)listadoBarr.get(codigoDeBarra);
        
        String sql="select id,nombre,barras,precio,equivalencia,costo,minimo,stock,servicio,servicio1,modificaprecio,modificaservicio,stock,recargo,idcombo from articulos where BARRAS like '"+codigoDeBarra+"' and INHABILITADO=0";
        rr=tra.leerConjuntoDeRegistros(sql);
        Articulos articulo=new Articulos();
        
        try {
            while(rr.next()){
                articulo.setCodigoAsignado(rr.getString("ID"));
                articulo.setDescripcionArticulo(rr.getString("NOMBRE"));
                articulo.setNumeroId(rr.getInt("ID"));
                articulo.setCodigoDeBarra(rr.getString("BARRAS"));
                articulo.setRecargo(rr.getDouble("recargo"));
                articulo.setPrecioUnitarioNeto(rr.getDouble("PRECIO"));
                articulo.setEquivalencia(rr.getDouble("equivalencia"));
                articulo.setPrecioDeCosto(rr.getDouble("COSTO"));
                articulo.setStockMinimo(rr.getDouble("MINIMO"));
                articulo.setStockActual(rr.getDouble("stock"));
                
                        articulo.setPrecioServicio(0.00);
                
                articulo.setModificaPrecio(rr.getBoolean("modificaPrecio"));
                articulo.setModificaServicio(rr.getBoolean("modificaServicio"));
                String nom=rr.getString("NOMBRE");
                articulo.setIdCombo(rr.getInt("idcombo"));
                if(articulo.getIdCombo() > 0)articulo.setCombo(CargarCombo(articulo.getNumeroId()));
            }
            rr.close();
        } catch (SQLException ex) {
            Logger.getLogger(Articulos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return articulo;
    }

    @Override
    public Boolean AltaObjeto(Object objeto) {
        Articulos articulo=(Articulos)objeto;
        Boolean ch=false;
        //String sql="insert into articulos (NOMBRE='"+articulo.getDescripcionArticulo()+"',SERVICIO="+articulo.getPrecioServicio()+",COSTO="+articulo.getPrecioDeCosto()+",PRECIO="+articulo.getPrecioUnitarioNeto()+",MINIMO="+articulo.getStockMinimo()+",BARRAS ='"+articulo.getCodigoDeBarra()+"',modificaPrecio="+articulo.getModificaPrecio()+" where ID="+articulo.getNumeroId();
        String sql="insert into articulos (NOMBRE,SERVICIO,COSTO,PRECIO,MINIMO,BARRAS,modificaPrecio,modificaServicio,SERVICIO1,idcombo,actualizacion,recargo) values ('"+articulo.getDescripcionArticulo()+"',"+articulo.getPrecioServicio()+","+articulo.getPrecioDeCosto()+",round("+articulo.getPrecioUnitarioNeto()+",2),"+articulo.getStockMinimo()+",'"+articulo.getCodigoDeBarra()+"',"+articulo.getModificaPrecio()+","+articulo.getModificaServicio()+","+articulo.getPrecioServicio1()+","+articulo.getIdCombo()+",3,"+articulo.getRecargo()+")";
        Transaccionable tra=new Conecciones();
        ch=tra.guardarRegistro(sql);
        sql="select last_insert_id()";
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        Integer ultimoArt=0;
        try {
            while(rs.next()){
                ultimoArt=rs.getInt(1);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Articulos.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(articulo.getCodigoDeBarra().equals("")){
          sql="update articulos set barras="+ultimoArt+" where id="+ultimoArt;
            tra.guardarRegistro(sql);  
        }
        if(articulo.getIdCombo() > 0){
            Articulos art=new Articulos();
            Iterator ic=articulo.getCombo().listIterator();
            while(ic.hasNext()){
                art=(Articulos)ic.next();
                sql="insert into combo (idarticulo,cantidad,articuloPadre) values ("+art.getNumeroId()+","+art.getCantidad()+","+ultimoArt+")";
                tra.guardarRegistro(sql);
            }
        }
        
        return ch;
    }

    @Override
    public Boolean ModificaionObjeto(Object objeto) {
        Articulos articulo=(Articulos)objeto;
        Boolean ch=false;
        String sql="update articulos set NOMBRE='"+articulo.getDescripcionArticulo()+"',SERVICIO="+articulo.getPrecioServicio()+",SERVICIO1="+articulo.getPrecioServicio1()+",COSTO="+articulo.getPrecioDeCosto()+",PRECIO=round("+articulo.getPrecioUnitarioNeto()+",2),MINIMO="+articulo.getStockMinimo()+",BARRAS ='"+articulo.getCodigoDeBarra()+"',modificaPrecio="+articulo.getModificaPrecio()+",modificaServicio="+articulo.getModificaServicio()+",actualizacion=2,idcombo="+articulo.getIdCombo()+",recargo="+articulo.getRecargo()+" where ID="+articulo.getNumeroId();
        Transaccionable tra=new Conecciones();
        ch=tra.guardarRegistro(sql);
        //sql="insert into actualizaciones (iddeposito,idobjeto,estado) values (1,1,2),(2,1,2),(3,1,2),(4,1,2),(5,1,2),(6,1,2),(7,1,2)";
        //tra.guardarRegistro(sql);
         if(articulo.getIdCombo() > 0){
            Articulos art=new Articulos();
            Iterator ic=articulo.getCombo().listIterator();
            while(ic.hasNext()){
                art=(Articulos)ic.next();
                sql="insert into combo (idarticulo,cantidad,articuloPadre) values ("+art.getNumeroId()+","+art.getCantidad()+","+articulo.getNumeroId()+")";
                tra.guardarRegistro(sql);
            }
        }
        return ch;
    }

    @Override
    public Boolean EliminacionDeObjeto(Object objeto) {
        Articulos articulo=(Articulos)objeto;
        Boolean verif=false;
        String sql="update articulos set INHABILITADO=1, actualizacion=4 where ID="+articulo.getNumeroId();
        Transaccionable tra=new Conecciones();
        verif=tra.guardarRegistro(sql);
        sql="insert into actualizaciones (iddeposito,idobjeto,estado) values (1,1,4),(2,1,4),(3,1,4),(4,1,4),(5,1,4),(6,1,4),(7,1,4)";
        tra.guardarRegistro(sql);
        
        return verif;
    }

    @Override
    public Integer leerNumeroDeComprobanteSiguiente(Integer numeroComprobante) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object cargarPorCodigoAsignado(Integer id) {
        Articulos articulo;
        String idd=String.valueOf(id);
        articulo=(Articulos)listadoCodigo.get(idd);
        return articulo;
    }

    @Override
    public Boolean MovimientoDeAjusteDeCantidades(Object objeto, Double cantidadMovimiento, String observaciones) {
        Articulos articulo=(Articulos)objeto;
        Boolean verif=false;
        
        String sql="insert into movimientosarticulos (tipoMovimiento,idArticulo,cantidad,numeroDeposito,tipoComprobante,numeroComprobante,numeroCliente,fechaComprobante,numerousuario,precioDeCosto,precioDeVenta,precioServicio,observaciones,idcaja) values (14,"+articulo.getNumeroId()+","+cantidadMovimiento+","+articulo.getIdDeposito()+",18,(select tipocomprobantes.numeroActivo + 1 from tipocomprobantes where tipocomprobantes.numero=18),1,'"+Inicio.fechaDia+"',"+Inicio.usuario.getNumeroId()+","+articulo.getPrecioDeCosto()+","+articulo.getPrecioUnitarioNeto()+","+articulo.getPrecioServicio()+",'"+observaciones+"',"+Inicio.caja.getNumero()+")";
        Transaccionable tra=new Conecciones();
        verif=tra.guardarRegistro(sql);
        sql="update tipocomprobantes set numeroActivo=numeroActivo + 1 where numero=18";
        tra.guardarRegistro(sql);
        return verif;
    }

    @Override
    public ArrayList ListarPorSucursal(Object objeto) {
        Articulos articulo=(Articulos)objeto;
        Articulos articuloI;
        ArrayList listado=new ArrayList();
        String sql="select sum(cantidad),idArticulo,numeroDeposito FROM movimientosarticulos where idArticulo="+String.valueOf(articulo.getCodigoAsignado())+" group by idArticulo,numeroDeposito";
        
        Transaccionable tra=new Conecciones();
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                articuloI=new Articulos();
                articuloI.setCantidad(rs.getDouble("sum(cantidad)"));
                articuloI.setNumeroId(rs.getInt("idArticulo"));
                articuloI.setIdDeposito(rs.getInt("numeroDeposito"));
                System.out.println(sql);
                listado.add(articuloI);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Articulos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listado;
    }

    @Override
    public ArrayList buscar(Integer rubro, Integer subRubro, String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer nuevoArticulo(Object objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean modificarArticulo(Object objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList modificarPrecios(ArrayList listado, Double porcPrecio, Double porcCosto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DefaultTableModel mostrarListado(ArrayList listado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DefaultTableModel mostrarListadoBusqueda(ArrayList listado) {
        DefaultTableModel modelo=new DefaultTableModel();
        Iterator it=listado.listIterator();
        Articulos articulo=new Articulos();
        modelo.addColumn("Codigo");
        modelo.addColumn("Descripcion");
        modelo.addColumn("Precio");
        Object [] fila=new Object[3];
        while(it.hasNext()){
            articulo=(Articulos)it.next();
            fila[0]=String.valueOf(articulo.getCodigoDeBarra());
            fila[1]=articulo.getDescripcionArticulo();
            fila[2]=" $"+Numeros.ConvertirNumero(articulo.getPrecioUnitarioNeto());
            
            //modelo.addElement(articulo.getDescripcionArticulo()+" $"+Numeros.ConvertirNumero(articulo.getPrecioUnitarioNeto()));
            modelo.addRow(fila);
        }
        return modelo;
    }

    @Override
    public ArrayList buscarParaFacturar(Integer rubro, Integer subRubro, String criterio, Integer idCliente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList filtrador(ArrayList rubro1, ArrayList subRubro) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList aplicarGanancia(ArrayList listado, Double ganancia) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void depurarFiltrador(ArrayList rubro1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList modificarPreciosValor(ArrayList listado, Double porcPrecio, Double porcCosto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DefaultTableModel actualizarListado(ArrayList listado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList listadoBusquedaMayorista(String criterio) {
        ArrayList resultado=new ArrayList();
        Articulos articulo=null;
        criterio=criterio.toUpperCase();
        String sql="select *,(select sum(cantidad) FROM movimientosarticulos where movimientosarticulos.idArticulo=articulos.ID group by idArticulo,numeroDeposito limit 0,1)as sstock from articulos where nombre like '%"+criterio+"%'";
        
        //Transaccionable tra=new ConeccionLocal();
        rr=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rr.next()){
                articulo=new Articulos();
                articulo.setCodigoAsignado(rr.getString("ID"));
                articulo.setDescripcionArticulo(rr.getString("NOMBRE"));
                articulo.setNumeroId(rr.getInt("ID"));
                articulo.setCodigoDeBarra(rr.getString("BARRAS"));
                articulo.setRecargo(rr.getDouble("recargo"));
                articulo.setPrecioUnitarioNeto(rr.getDouble("SERVICIO"));
                articulo.setEquivalencia(rr.getDouble("equivalencia"));
                articulo.setPrecioDeCosto(rr.getDouble("COSTO"));
                articulo.setStockMinimo(rr.getDouble("MINIMO"));
                articulo.setStockActual(rr.getDouble("sstock"));
                try{
                    if(Inicio.sucursal.getDireccion().equals("1")){
                        articulo.setPrecioServicio(0.00);
                    }else{
                        articulo.setPrecioServicio(0.00);
                    }
                }catch(NullPointerException nEx){
                    articulo.setPrecioServicio(0.00);
                    articulo.setPrecioServicio1(0.00);
                }
                articulo.setModificaPrecio(rr.getBoolean("modificaPrecio"));
                articulo.setModificaServicio(rr.getBoolean("modificaServicio"));
                String nom=rr.getString("NOMBRE");
                articulo.setIdCombo(rr.getInt("idcombo"));
                if(articulo.getIdCombo() > 0)articulo.setCombo(CargarCombo(articulo.getNumeroId()));
                resultado.add(articulo);
            }
            rr.close();
        } catch (SQLException ex) {
            Logger.getLogger(Articulos.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*
        Transaccionable tra=new Conecciones();
        ArrayList resultado=new ArrayList();
        Articulos articulo=null;
        String sql="select *,(select stockart.stock from stockart where stockart.id=articulos.ID)as stock,(select rubros.recargo from rubros where rubros.id=articulos.idRubro)as recargo from articulos where NOMBRE like '"+criterio+"%' and INHABILITADO=0";
        ResultSet rr=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rr.next()){
                articulo=new Articulos();
                articulo.setCodigoAsignado(rr.getString("ID"));
                articulo.setDescripcionArticulo(rr.getString("NOMBRE"));
                articulo.setNumeroId(rr.getInt("ID"));
                articulo.setCodigoDeBarra(rr.getString("BARRAS"));
                articulo.setRecargo(rr.getDouble("recargo"));
                articulo.setPrecioUnitarioNeto(rr.getDouble("PRECIO"));
                articulo.setEquivalencia(rr.getDouble("equivalencia"));
                articulo.setPrecioDeCosto(rr.getDouble("COSTO"));
                articulo.setStockMinimo(rr.getDouble("MINIMO"));
                articulo.setStockActual(rr.getDouble("stock"));
                articulo.setPrecioServicio(rr.getDouble("SERVICIO"));
                articulo.setModificaPrecio(rr.getBoolean("modificaPrecio"));
                resultado.add(articulo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Articulos.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        return resultado;
    }

    @Override
    public Object cargarPorCodigoDeBarraMayorista(String codigoDeBarra) {
        //Articulos articulo;
        //articulo=(Articulos)listadoBarr.get(codigoDeBarra);
        
        String sql="select id,nombre,barras,precio,equivalencia,costo,minimo,stock,servicio,servicio1,modificaprecio,modificaservicio,stock,recargo,idcombo from articulos where BARRAS like '"+codigoDeBarra+"' and INHABILITADO=0";
        rr=tra.leerConjuntoDeRegistros(sql);
        Articulos articulo=new Articulos();
        
        try {
            while(rr.next()){
                articulo.setCodigoAsignado(rr.getString("ID"));
                articulo.setDescripcionArticulo(rr.getString("NOMBRE"));
                articulo.setNumeroId(rr.getInt("ID"));
                articulo.setCodigoDeBarra(rr.getString("BARRAS"));
                articulo.setRecargo(rr.getDouble("recargo"));
                articulo.setPrecioUnitarioNeto(rr.getDouble("SERVICIO"));
                articulo.setEquivalencia(rr.getDouble("equivalencia"));
                articulo.setPrecioDeCosto(rr.getDouble("COSTO"));
                articulo.setStockMinimo(rr.getDouble("MINIMO"));
                articulo.setStockActual(rr.getDouble("stock"));
                
                        articulo.setPrecioServicio(0.00);
                
                articulo.setModificaPrecio(rr.getBoolean("modificaPrecio"));
                articulo.setModificaServicio(rr.getBoolean("modificaServicio"));
                String nom=rr.getString("NOMBRE");
                articulo.setIdCombo(rr.getInt("idcombo"));
                if(articulo.getIdCombo() > 0)articulo.setCombo(CargarCombo(articulo.getNumeroId()));
            }
            rr.close();
        } catch (SQLException ex) {
            Logger.getLogger(Articulos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return articulo;
    }
    
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Compras;

import Administracion.TipoComprobante;
import interfaceGraficas.Inicio;
import interfaces.Comprobable;
import interfaces.Transaccionable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import objetos.Articulos;
import objetos.Conecciones;

/**
 *
 * @author mauro
 */
public class Remitos implements Comprobable{
    private Integer numeroId;
    private Integer idProveedor;
    private Date fechaComprobante;
    private Date fechaRecepcion;
    private ArrayList articulos;
    private String numeroRemito;
    private Integer numeroDeposito;
    private Integer idUsuario;
    private Boolean guardaPrecioDeVenta;

    public Boolean getGuardaPrecioDeVenta() {
        return guardaPrecioDeVenta;
    }

    public void setGuardaPrecioDeVenta(Boolean guardaPrecioDeVenta) {
        this.guardaPrecioDeVenta = guardaPrecioDeVenta;
    }
    
    

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
    

    public Integer getNumeroDeposito() {
        return numeroDeposito;
    }

    public void setNumeroDeposito(Integer numeroDeposito) {
        this.numeroDeposito = numeroDeposito;
    }
    

    public Remitos() {
    }

    public Integer getNumeroId() {
        return numeroId;
    }

    public void setNumeroId(Integer numeroId) {
        this.numeroId = numeroId;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Date getFechaComprobante() {
        return fechaComprobante;
    }

    public void setFechaComprobante(Date fechaComprobante) {
        this.fechaComprobante = fechaComprobante;
    }

    public Date getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(Date fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public ArrayList getArticulos() {
        return articulos;
    }

    public void setArticulos(ArrayList articulos) {
        this.articulos = articulos;
    }

    public String getNumeroRemito() {
        return numeroRemito;
    }

    public void setNumeroRemito(String numeroRemito) {
        this.numeroRemito = numeroRemito;
    }

    @Override
    public Integer nuevoComprobante(Object objeto) {
        //String sql="CREATE TABLE IF NOT EXISTS `movimientos25` (`numero` int(11) NOT NULL AUTO_INCREMENT,`numeroArticulo` int(11) NOT NULL,`cantidad` int(11) NOT NULL,`condicion` int(11) NOT NULL DEFAULT '0',`numeroUsuario` int(11) NOT NULL,`fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,PRIMARY KEY (`numero`)) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5";
        //String sql="insert into clientes (nombre, direccion, localidad, telefono, mail, condIva, numeroCuit) VALUES (mauro, piedras 6738, santa fe, 155451500, contacto@maurodi.com.ar, 1, 0000000000)";
        Remitos rem=(Remitos)objeto;
        String sql="select * from tipocomprobantes where numero=3 and numeroSucursal=1";
        Integer veri=0;
        Transaccionable tra=new Conecciones();
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        Integer numero=0;
        try {
            while(rs.next()){
                numero=rs.getInt("numeroActivo");
                numero++;
                
            }
            rs.close();
            veri=numero;
            
            rem.setNumeroRemito(String.valueOf(numero));
            Iterator listA=rem.getArticulos().listIterator();
            String sql1="";
            while(listA.hasNext()){
                Articulos art=(Articulos)listA.next();
                Double cantidad=art.getCantidad();
                sql="insert into movimientosarticulos (tipoMovimiento,idArticulo,cantidad,numeroDeposito,tipoComprobante,numeroComprobante,numeroCliente,fechaComprobante,numerousuario,precioDeCosto,idcaja) values (5,"+art.getNumeroId()+","+cantidad+","+rem.getNumeroDeposito()+",3,'"+rem.getNumeroRemito()+"',"+rem.getIdProveedor()+",'"+rem.getFechaRecepcion()+"',"+rem.getIdUsuario()+","+art.getPrecioDeCosto()+","+Inicio.caja.getNumero()+")";
                if(tra.guardarRegistro(sql))//System.out.println(sql);
                
                sql1="update articulos set COSTO="+art.getPrecioDeCosto()+" where id="+art.getNumeroId();
                if(tra.guardarRegistro(sql1));//System.out.println(sql1);
                
                
                
            }
            sql="update tipocomprobantes set numeroActivo="+numero+" where numero=3 and numeroSucursal=1";
             tra.guardarRegistro(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Remitos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return veri;
    }

    @Override
    public Boolean agregarItem(Object item) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean modificarComprobante(Object objeto) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean eliminarComprobante(Object objeto) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object leerComprobante(Integer numero) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList listarComprobantesPorFecha(Date fecha) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList listarComprobantesPorDeposito(Integer numeroDeposito) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList listarComprobantesPorProveedor(Integer numeroProveedor) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Depositos;

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
public class RemitosInternos implements Comprobable{
    private Integer numero;
    private Integer id;
    private Integer idUusuario;
    private Date fecha;
    private String numeroDeFactura;
    private Integer depositoDestino;
    private Integer depositoOrigen;
    private ArrayList articulos;
    private static Integer numeroComprobante;
    private Integer idUsuarioRecep;

    public Integer getIdUsuarioRecep() {
        return idUsuarioRecep;
    }

    public void setIdUsuarioRecep(Integer idUsuarioRecep) {
        this.idUsuarioRecep = idUsuarioRecep;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getIdUusuario() {
        return idUusuario;
    }

    public void setIdUusuario(Integer idUusuario) {
        this.idUusuario = idUusuario;
    }


    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNumeroDeFactura() {
        return numeroDeFactura;
    }

    public void setNumeroDeFactura(String numeroDeFactura) {
        this.numeroDeFactura = numeroDeFactura;
    }

    public Integer getDepositoDestino() {
        return depositoDestino;
    }

    public void setDepositoDestino(Integer depositoDestino) {
        this.depositoDestino = depositoDestino;
    }

    public Integer getDepositoOrigen() {
        return depositoOrigen;
    }

    public void setDepositoOrigen(Integer depositoOrigen) {
        this.depositoOrigen = depositoOrigen;
    }

    public ArrayList getArticulos() {
        return articulos;
    }

    public void setArticulos(ArrayList articulos) {
        this.articulos = articulos;
    }

    public RemitosInternos() {
    }

    public RemitosInternos(Integer numero, Integer numeroProveedor) {
        this.numero = numero;
        this.idUusuario = numeroProveedor;
    }

    public RemitosInternos(Integer id) {
        this.id = id;
    }
    
    public static void numeroActual(){
        Transaccionable tra=new Conecciones();
        String sql="select * from tipocomprobantes where numero=4";
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
            numeroComprobante=rs.getInt("numeroActivo");
                
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(RemitosInternos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public Integer nuevoComprobante(Object objeto){
        numeroActual();
        RemitosInternos remInterno=(RemitosInternos)objeto;
        numeroComprobante++;
        Transaccionable tra=new Conecciones();
        String sql="";
        Iterator itRem=remInterno.articulos.listIterator();
        while(itRem.hasNext()){
            Articulos articulo=(Articulos)itRem.next();
            sql="insert into movimientosdesucursales (depOrigen,depDestino,idArticulo,cantidad,numeroRemito,idUsuario) values ("+remInterno.getDepositoOrigen()+","+remInterno.getDepositoDestino()+","+articulo.getNumeroId()+","+articulo.getCantidad()+","+numeroComprobante+","+Inicio.usuario.getNumeroId()+")";
            tra.guardarRegistro(sql);
            //carga el movimiento en la sucursal
            sql="insert into movimientosarticulos (tipoMovimiento,idArticulo,cantidad,numeroDeposito,tipoComprobante,numeroComprobante,numeroCliente,fechaComprobante,numerousuario,precioDeCosto,precioDeVenta,idCaja) values (6,"+articulo.getNumeroId()+","+articulo.getCantidad()+","+remInterno.getDepositoDestino()+",4,"+numeroComprobante+",1,'"+Inicio.fechaDia+"',"+Inicio.usuario.getNumeroId()+","+articulo.getPrecioDeCosto()+","+articulo.getPrecioUnitarioNeto()+","+Inicio.caja.getNumero()+")";
            System.out.println(sql);
            tra.guardarRegistro(sql);
            //carga el movimiento de resta en el deposito origen
            Double cant=articulo.getCantidad() * -1;
            sql="insert into movimientosarticulos (tipoMovimiento,idArticulo,cantidad,numeroDeposito,tipoComprobante,numeroComprobante,numeroCliente,fechaComprobante,numerousuario,precioDeCosto,precioDeVenta,idCaja) values (6,"+articulo.getNumeroId()+","+cant+","+remInterno.getDepositoOrigen()+",4,"+numeroComprobante+",1,'"+Inicio.fechaDia+"',"+Inicio.usuario.getNumeroId()+","+articulo.getPrecioDeCosto()+","+articulo.getPrecioUnitarioNeto()+","+Inicio.caja.getNumero()+")";
            tra.guardarRegistro(sql);
        }
        sql="update tipocomprobantes set numeroActivo="+numeroComprobante+" where numero=4";
        tra.guardarRegistro(sql);
        return numeroComprobante;
    }

    @Override
    public Boolean agregarItem(Object item) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean modificarComprobante(Object objeto) {
        RemitosInternos remitoInterno=(RemitosInternos)objeto;
        Boolean conf=false;
        Transaccionable tra=new Conecciones();
        String sql="";
        Iterator itL=remitoInterno.getArticulos().listIterator();
        while(itL.hasNext()){
            Articulos articulo=(Articulos)itL.next();
            sql="update movimientosdesucursales set diferencia=+"+articulo.getDiferenciaRemitida()+",idUsuarioRecep="+Inicio.usuario.getNumeroId()+",confirmado="+articulo.getConfirmado()+" where idArticulo="+articulo.getNumeroId()+" and numeroRemito="+remitoInterno.getNumero();
            conf=tra.guardarRegistro(sql);
        }
        
        return conf;
    }

    @Override
    public Boolean eliminarComprobante(Object objeto) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object leerComprobante(Integer numero) {
        //ACA LEO LOS REMITOS INTERNOS GENERADOS
        Transaccionable tra=new Conecciones();
        ArrayList listado=new ArrayList();
        String sql="select *,(select articulos.NOMBRE from articulos where articulos.ID=movimientosdesucursales.idArticulo)as descripcion from movimientosdesucursales where numeroRemito="+numero+" and confirmado=0";
        RemitosInternos remitoInterno=new RemitosInternos();
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                Articulos articulos=new Articulos();
                articulos.setCantidad(rs.getDouble("cantidad"));
                articulos.setNumeroId(rs.getInt("idArticulo"));
                articulos.setDescripcionArticulo(rs.getString("descripcion"));
                remitoInterno.setDepositoOrigen(rs.getInt("depOrigen"));
                remitoInterno.setDepositoDestino(rs.getInt("depDestino"));
                articulos.setConfirmado(rs.getBoolean("confirmado"));
                remitoInterno.setIdUsuarioRecep(rs.getInt("idUsuarioRecep"));
                remitoInterno.setNumero(rs.getInt("numeroRemito"));
                listado.add(articulos);
                
            }
            remitoInterno.setArticulos(listado);
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(RemitosInternos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return remitoInterno;
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

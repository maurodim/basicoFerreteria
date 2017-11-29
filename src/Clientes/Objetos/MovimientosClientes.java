/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clientes.Objetos;

import Conversores.Numeros;
import interfaces.Editables;
import interfaces.Movible;
import interfaces.Transaccionable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import objetos.ConeccionLocal;

/**
 *
 * @author mauro
 */
public class MovimientosClientes implements Movible,Editables{
    private Integer idCliente;
    private Double monto;
    private Integer pagado;
    private Integer numeroComprobante;
    private Integer idRemito;
    private Integer idUsuario;
    private Integer idCaja;
    private String fechaPago;
    private Integer tipoComprobante;
    private Integer idSucursal;
    private Integer estado;
    private Integer id;
    private Integer idComprobante;
    private Date fecha;
    private String nombreCliente;
    private String descripcionTipoComprobante;
    private Transaccionable tra;
    private ResultSet rs;

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Integer getPagado() {
        return pagado;
    }

    public void setPagado(Integer pagado) {
        this.pagado = pagado;
    }

    public Integer getNumeroComprobante() {
        return numeroComprobante;
    }

    public void setNumeroComprobante(Integer numeroComprobante) {
        this.numeroComprobante = numeroComprobante;
    }

    public Integer getIdRemito() {
        return idRemito;
    }

    public void setIdRemito(Integer idRemito) {
        this.idRemito = idRemito;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdCaja() {
        return idCaja;
    }

    public void setIdCaja(Integer idCaja) {
        this.idCaja = idCaja;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Integer getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(Integer tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public Integer getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Integer idSucursal) {
        this.idSucursal = idSucursal;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdComprobante() {
        return idComprobante;
    }

    public void setIdComprobante(Integer idComprobante) {
        this.idComprobante = idComprobante;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getDescripcionTipoComprobante() {
        return descripcionTipoComprobante;
    }

    public void setDescripcionTipoComprobante(String descripcionTipoComprobante) {
        this.descripcionTipoComprobante = descripcionTipoComprobante;
    }

    @Override
    public ArrayList ListarMovimientos(Integer id) {
        ArrayList listado=new ArrayList();
        tra=new ConeccionLocal();
        String sql="select *,(select listcli.RAZON_SOCI from listcli where listcli.id=movimientosclientes.numeroproveedor)as nombreC,(select tipomovimientos.descripcion from tipomovimientos where tipomovimientos.id=movimientosclientes.tipocomprobante)as descripcionC from movimientosclientes where numeroProveedor="+id+" and pagado=0";
        System.out.println(sql);
        MovimientosClientes mov;
        rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                mov=new MovimientosClientes();
                mov.setId(rs.getInt("id"));
                mov.setIdCaja(rs.getInt("idcaja"));
                mov.setIdCliente(rs.getInt("numeroproveedor"));
                mov.setIdComprobante(rs.getInt("idcomprobante"));
                mov.setIdRemito(rs.getInt("idremito"));
                mov.setIdSucursal(rs.getInt("idsucursal"));
                mov.setIdUsuario(rs.getInt("idusuario"));
                mov.setEstado(rs.getInt("estado"));
                mov.setFecha(rs.getDate("fecha"));
                mov.setFechaPago(rs.getString("fechapago"));
                mov.setMonto(rs.getDouble("monto"));
                mov.setNumeroComprobante(rs.getInt("numerocomprobante"));
                mov.setPagado(rs.getInt("pagado"));
                mov.setTipoComprobante(rs.getInt("tipocomprobante"));
                mov.setDescripcionTipoComprobante(rs.getString("descripcionC"));
                mov.setNombreCliente(rs.getString("nombreC"));
                listado.add(mov);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(MovimientosClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listado;
    }

    @Override
    public Double AjustarSaldo(Double saldoActual, Integer idCliente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DefaultTableModel MostrarMovimientos(ArrayList listado) {
        DefaultTableModel modelo=new DefaultTableModel();
        modelo.addColumn("FECHA");
        modelo.addColumn("TIPO");
        modelo.addColumn("MONTO");
        Object[] fila=new Object[3];
        Iterator it=listado.listIterator();
        MovimientosClientes movi;
        while(it.hasNext()){
            movi=(MovimientosClientes) it.next();
            fila[0]=Numeros.ConvertirFecha(movi.getFecha());
            fila[1]=movi.getDescripcionTipoComprobante();
            fila[2]=String.valueOf(movi.getMonto());
            modelo.addRow(fila);
        }
        return modelo;
    }

    @Override
    public DefaultTableModel MostrarListaDePrecios(ArrayList listado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList ListarPreciosCliente(Object cliente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList ListarMovimientosPorFechas(Integer id, String desde, String hasta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean AltaObjeto(Object objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean ModificaionObjeto(Object objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean EliminacionDeObjeto(Object objeto) {
        MovimientosClientes movi=(MovimientosClientes) objeto;
        Boolean verif=true;
        tra=new ConeccionLocal();
        String sql="delete from movimientosclientes where id="+movi.getId();
        System.out.println(sql);
        tra.guardarRegistro(sql);
        return verif;
    }

    @Override
    public Boolean MovimientoDeAjusteDeCantidades(Object objeto, Double cantidadMovimiento, String observaciones) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList ListarPorSucursal(Object objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}

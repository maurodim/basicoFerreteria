/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Administracion;

import interfaces.Comprobable;
import interfaces.Transaccionable;
import java.util.ArrayList;
import java.util.Date;
import objetos.Conecciones;

/**
 *
 * @author mauro
 */
public class TipoComprobante implements Comprobable {
    private Integer numero;
    private String descripcion;

    public TipoComprobante(Integer numero, String descripcion) {
        this.numero = numero;
        this.descripcion = descripcion;
    }

    public TipoComprobante() {
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public Integer nuevoComprobante(Object objeto) {
        TipoComprobante tipoComprobante=(TipoComprobante)objeto;
        Integer creado=0;
        String sql="";
        Transaccionable tran=new Conecciones();
        if(tran.guardarRegistro(sql)){
            sql="";
            if(tran.guardarRegistro(sql)){
                //creado=devuelve el last_id;
            };
        }
        return creado;
    }

    @Override
    public Boolean agregarItem(Object item) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean modificarComprobante(Object objeto) {
        TipoComprobante tipoComprobante=(TipoComprobante)objeto;
        Boolean creado=false;
        String sql="";
        Transaccionable tran=new Conecciones();
       
            if(tran.guardarRegistro(sql))creado=true;
        
        return creado;
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

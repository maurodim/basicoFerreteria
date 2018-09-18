/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vehiculos;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author mauro
 */
public class Unidad {
    private Integer id;
    private Integer idCliente;
    private String nombreCliente;
    private int idMarca;
    private String nombreMarca;
    private Integer modelo;
    private String descripcionModelo;
    private Integer kmActuales;
    private String observaciones;
    private Date fechaServicio;
    private Integer idOrdenDeTrabajo;
    private int idOperador;
    private String nombreOperador;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public String getNombreMarca() {
        return nombreMarca;
    }

    public void setNombreMarca(String nombreMarca) {
        this.nombreMarca = nombreMarca;
    }

    public Integer getModelo() {
        return modelo;
    }

    public void setModelo(Integer modelo) {
        this.modelo = modelo;
    }

    public String getDescripcionModelo() {
        return descripcionModelo;
    }

    public void setDescripcionModelo(String descripcionModelo) {
        this.descripcionModelo = descripcionModelo;
    }

    public Integer getKmActuales() {
        return kmActuales;
    }

    public void setKmActuales(Integer kmActuales) {
        this.kmActuales = kmActuales;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Date getFechaServicio() {
        return fechaServicio;
    }

    public void setFechaServicio(Date fechaServicio) {
        this.fechaServicio = fechaServicio;
    }

    public Integer getIdOrdenDeTrabajo() {
        return idOrdenDeTrabajo;
    }

    public void setIdOrdenDeTrabajo(Integer idOrdenDeTrabajo) {
        this.idOrdenDeTrabajo = idOrdenDeTrabajo;
    }

    public int getIdOperador() {
        return idOperador;
    }

    public void setIdOperador(int idOperador) {
        this.idOperador = idOperador;
    }

    public String getNombreOperador() {
        return nombreOperador;
    }

    public void setNombreOperador(String nombreOperador) {
        this.nombreOperador = nombreOperador;
    }
    
    private Unidad CargarPorId(Integer id){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    private ArrayList ListarPorCliente(Integer id){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    private ArrayList ListarUnidades(){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Administracion;

import interfaces.UsuariosMovibles;
import java.util.ArrayList;

/**
 *
 * @author mauro
 */
public class TipoAcceso implements UsuariosMovibles{
    private Integer numero;
    private String descripcion;
    private Integer nivel;

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

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public TipoAcceso() {
        this.numero=0;
        this.descripcion="Basico";
        this.nivel=1;
    }

    @Override
    public Boolean registrarIngreso(Object objeto) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean registrarSalida(Object objeto) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object validarClave(String usuario, String clave) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean modificarDatosUsuario(Object objeto) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean nuevoUsuario(Object objeto) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList listarUsuarios() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object cargarUsuario(Integer numeroUsuario) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    
}

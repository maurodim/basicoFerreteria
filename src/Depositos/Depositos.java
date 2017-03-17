/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Depositos;

import Compras.Proveedores;
import interfaceGraficas.Inicio;
import interfaces.Personalizable;
import interfaces.Transaccionable;
import interfaces.Trasladable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import objetos.Articulos;
import objetos.ConeccionLocal;
import objetos.Conecciones;

/**
 *
 * @author mauro
 */
public class Depositos implements Personalizable, Trasladable{
    private int numero;
    private String descripcion;
    private String telefono;
    private String direccion;
    private Proveedores proveedores;
    private static ArrayList remitosInternos=new ArrayList();
    
    public Depositos() {
    }

    public Depositos(int numero) {
        this.numero = numero;
        Transaccionable tra;
        /*
        if(Inicio.coneccionRemota){
            tra=new Conecciones();
        }else{
        */
        tra=new ConeccionLocal();
        //}
        String sql1="select * from depositos where numero ="+numero;
        ResultSet rr=tra.leerConjuntoDeRegistros(sql1);
        try {
            while(rr.next()){
                this.descripcion=rr.getString("descripcion");
                this.direccion=rr.getString("direccion");
                this.telefono=rr.getString("telefono");
                
            }
            int numeroRemitoInterno=0;
            if(Inicio.coneccionRemota){
            sql1="select movimientosdesucursales.numeroRemito from movimientosdesucursales where depDestino="+this.numero+" and confirmado=0 group by numeroRemito";
            rr=tra.leerConjuntoDeRegistros(sql1);
            while(rr.next()){
                numeroRemitoInterno=rr.getInt("numeroRemito");
                //System.out.println("REMITO INTERNO LEIDOOOOOO "+numeroRemitoInterno);
                JOptionPane.showMessageDialog(null,"USTED TIENE REMITOS INTERNOS A CONFIRMAR");
                remitosInternos.add(numeroRemitoInterno);
            }
            }
            rr.close();
        } catch (SQLException ex) {
            Logger.getLogger(Depositos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Proveedores getProveedores() {
        return proveedores;
    }

    public void setProveedores(Proveedores proveedores) {
        this.proveedores = proveedores;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public static ArrayList leerRemitosInternos(){
        return remitosInternos;
    }
    public static void BackapearDepositos(){
                
        String sentencia="";
        if(Inicio.coneccionRemota){
            Transaccionable tra=new Conecciones();
            Transaccionable tt=new ConeccionLocal();
            String sql="select * from depositos";
            tt.guardarRegistro("delete from depositos");
            ResultSet rs=tra.leerConjuntoDeRegistros(sql);
            try {
                while(rs.next()){
                    sentencia="insert into depositos (numero,descripcion,direccion,telefono) values ("+rs.getInt("numero")+",'"+rs.getString("descripcion")+"','"+rs.getString("direccion")+"','"+rs.getString("telefono")+"')";
                    tt.guardarRegistro(sentencia);
                    
                }
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Depositos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    @Override
    public Boolean agregar(Object objeto) {
        //
        Boolean verif=false;
        Depositos deposito=(Depositos)objeto;
        String sql="insert into depositos (descripcion,direccion) values ('"+deposito.getDescripcion()+"','"+deposito.getDireccion()+"')";
        Transaccionable tra=new Conecciones();
        verif=tra.guardarRegistro(sql);
        
        return verif;
        
    }

    @Override
    public Boolean modificar(Object objeto) {
        //
        Boolean verif=false;
        Depositos deposito=(Depositos)objeto;
        String sql="update depositos set descripcion='"+deposito.getDescripcion()+"',direccion='"+deposito.getDireccion()+"' where numero="+deposito.getNumero();
        Transaccionable tra=new Conecciones();
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
        ArrayList depo=new ArrayList();
        Depositos depositos=null;
       Transaccionable tra=new Conecciones();
        String sql1="select * from depositos order by numero";
        ResultSet rr=tra.leerConjuntoDeRegistros(sql1);
        try {
            while(rr.next()){
                depositos=new Depositos();
                depositos.setDescripcion(rr.getString("descripcion"));
                depositos.setDireccion(rr.getString("direccion"));
                depositos.setTelefono(rr.getString("telefono"));
                depositos.setNumero(rr.getInt("numero"));
                depo.add(depositos);
                
            }
            rr.close();
        } catch (SQLException ex) {
            Logger.getLogger(Depositos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return depo;
    }

    @Override
    public ArrayList listarPorNombre(String nombre) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList listarPorCuit(String cuit) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean GenerarRemitoInterno(Object ob) {
        RemitosInternos remitoInterno=(RemitosInternos)ob;
        Articulos articulos=new Articulos();
        Boolean guardado=false;
        Iterator ilA=remitoInterno.getArticulos().listIterator();
        String sql="";
        Transaccionable tra=new Conecciones();
        while(ilA.hasNext()){
            articulos=(Articulos)ilA.next();
            sql="insert into movimientosdesucursales (depOrigen,depDestino,idArticulo,cantidad,numeroRemito,idUsuario) values ("+remitoInterno.getDepositoOrigen()+","+remitoInterno.getDepositoDestino()+","+articulos.getCodigoAsignado()+","+articulos.getCantidad()+","+remitoInterno.getNumero()+","+remitoInterno.getIdUusuario()+")";
            tra.guardarRegistro(sql);
            guardado=true;
        }
        
        return guardado;
    }

    @Override
    public Boolean RecibirRemitoInterno(Object ob) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean ConfirmarRemitoInterno(Object ob) {
        RemitosInternos remitoInterno=(RemitosInternos)ob;
        Articulos articulos=new Articulos();
        Boolean guardado=false;
        Iterator ilA=remitoInterno.getArticulos().listIterator();
        String sql="";
        Transaccionable tra=new Conecciones();
        while(ilA.hasNext()){
            articulos=(Articulos)ilA.next();
            sql="update movimientosdesucursales set confirmado="+articulos.getConfirmado()+" where id="+remitoInterno.getId();
            tra.guardarRegistro(sql);
            guardado=true;
        }        
        return guardado;
    }

    @Override
    public ArrayList LeerRemitosInternos(Integer depositoDestino) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList LeerRemitosInternosEnviados(Integer depositoOrigen) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object StockPorDeposito(Integer numeroDeposito,Object artic) {
        Articulos articulo=(Articulos)artic;
        String sql="select *,(select sum(movimientosarticulos.cantidad) from movimientosarticulos where movimientosarticulos.numeroDeposito=depositos.numero and movimientosarticulos.idArticulo="+articulo.getCodigoAsignado()+")as movArt,(select sum(movimientosdesucursales.cantidad) from movimientosdesucursales where movimientosdesucursales.depOrigen=depositos.numero and movimientosdesucursales.idArticulo="+articulo.getCodigoAsignado()+")as movSal,(select sum(movimientosdesucursales.cantidad) from movimientosdesucursales where movimientosdesucursales.depDestino=depositos.numero and movimientosdesucursales.idArticulo="+articulo.getCodigoAsignado()+")as movEnt from depositos";
        
        return articulo;
    }

    @Override
    public ArrayList MovimientosPorDeposito(Integer numeroDeposito) {
        String sql="select ";
        ArrayList listado=new ArrayList();
        
        
        return listado;
    }
    
    
}

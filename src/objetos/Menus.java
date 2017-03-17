/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import interfaces.Personalizable;
import interfaces.Transaccionable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mauro
 */
public class Menus implements Personalizable{
    private Boolean menu1;
    private Boolean menu2;
    private Boolean menu3;
    private Boolean menu4;
    private Boolean menu5;
    private Boolean menu6;
    private Boolean menu7;
    private String nombre;
    private Integer numero;

    public Menus(Boolean menu1, Boolean menu2, Boolean menu3, Boolean menu4, Boolean menu5, Boolean menu6, Boolean menu7) {
        this.menu1 = menu1;
        this.menu2 = menu2;
        this.menu3 = menu3;
        this.menu4 = menu4;
        this.menu5 = menu5;
        this.menu6 = menu6;
        this.menu7 = menu7;
    }

    public Menus() {
    }

    public void setMenu1(Boolean menu1) {
        this.menu1 = menu1;
    }

    public void setMenu2(Boolean menu2) {
        this.menu2 = menu2;
    }

    public void setMenu3(Boolean menu3) {
        this.menu3 = menu3;
    }

    public void setMenu4(Boolean menu4) {
        this.menu4 = menu4;
    }

    public void setMenu5(Boolean menu5) {
        this.menu5 = menu5;
    }

    public void setMenu6(Boolean menu6) {
        this.menu6 = menu6;
    }

    public void setMenu7(Boolean menu7) {
        this.menu7 = menu7;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getMenu6() {
        return menu6;
    }

    public Boolean getMenu7() {
        return menu7;
    }
    

    public Boolean getMenu5() {
        return menu5;
    }

    public Boolean getMenu1() {
        return menu1;
    }

    public Boolean getMenu2() {
        return menu2;
    }

    public Boolean getMenu3() {
        return menu3;
    }

    public Boolean getMenu4() {
        return menu4;
    }

    @Override
    public Boolean agregar(Object objeto) {
        Boolean verif=false;
        Menus menu=(Menus)objeto;
        Transaccionable tra=new Conecciones();
        String sql="insert into tipoacceso (descripcion,menu1,menu2,menu3,menu4,menu5,menu6,menu7) values ('"+menu.getNombre()+"',"+menu.getMenu1()+","+menu.getMenu2()+","+menu.getMenu3()+","+menu.getMenu4()+","+menu.getMenu5()+","+menu.getMenu6()+","+menu.getMenu7()+")";
        verif=tra.guardarRegistro(sql);
        sql="select LAST_INSERT_ID()";
        int nivel=0;
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
            nivel=rs.getInt(1);
                
            }
            rs.close();
            sql="update tipoacceso set nivel="+nivel+" where numero="+nivel;
            tra.guardarRegistro(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Menus.class.getName()).log(Level.SEVERE, null, ex);
        }
        return verif;

    }

    @Override
    public Boolean modificar(Object objeto) {
        Boolean verif=false;
        Menus menu=(Menus)objeto;
        Transaccionable tra=new Conecciones();
        String sql="update tipoacceso set descripcion='"+menu.getNombre()+"',nivel="+menu.getNumero()+",menu1="+menu.getMenu1()+",menu2="+menu.getMenu2()+",menu3="+menu.getMenu3()+",menu4="+menu.getMenu4()+",menu5="+menu.getMenu5()+",menu6="+menu.getMenu6()+",menu7="+menu.getMenu7()+" where numero="+menu.getNumero();
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
        
        Transaccionable tra=new Conecciones();
        String sql="select * from tipoacceso";
        ResultSet rr=tra.leerConjuntoDeRegistros(sql);
        ArrayList listado=new ArrayList();
        try {
            while(rr.next()){
              Menus menu=new Menus();
              menu.setNombre(rr.getString("descripcion"));
              menu.setNumero(rr.getInt("numero"));
            menu.setMenu1(rr.getBoolean("menu1"));
             menu.setMenu2(rr.getBoolean("menu2"));
            menu.setMenu3(rr.getBoolean("menu3"));
            menu.setMenu4(rr.getBoolean("menu4"));
            menu.setMenu5(rr.getBoolean("menu5"));
            menu.setMenu6(rr.getBoolean("menu6"));
            menu.setMenu7(rr.getBoolean("menu7"));
            listado.add(menu);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(Menus.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listado;
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

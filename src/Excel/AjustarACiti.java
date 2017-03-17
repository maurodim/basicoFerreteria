/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Excel;

import Conversores.Numeros;
import interfaces.Transaccionable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import objetos.ConeccionLocal;

/**
 *
 * @author mauro
 */
public class AjustarACiti {
    public void Ajustar() throws SQLException{
        Transaccionable tra=new ConeccionLocal();
        ArrayList listado=new ArrayList();
        String sql="select *,(select listcli.RAZON_SOCI from listcli where listcli.id=movimientoscaja.idCliente)as razon,(select listcli.NUMERODECUIT from listcli where listcli.id=movimientoscaja.idCliente)as cuit from movimientoscaja where tipoComprobante > 2 and fecha between '2016-07-01' and '2016-07-07' and tipoMovimiento=1 group by idOriginal,numeroComprobante order by numeroComprobante";
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        String valores="";
        Double gravado=0.00;
        Double impuesto=0.00;
        double monto=0.00;
        int tipoClienteId=0;
        String numeroComprobante="";
        String fecha="";
        while(rs.next()){
            gravado=rs.getDouble("monto") / 1.21;
            monto=rs.getDouble("monto");
            impuesto=monto - gravado;
            numeroComprobante=String.valueOf(rs.getInt("numeroComprobante"));
            numeroComprobante=numeroComprobante.replaceFirst("8","0");
               if(rs.getInt("tipoCliente")==11){
                   tipoClienteId=99;
               }else{
                   tipoClienteId=80;
               }
               fecha=rs.getString("fecha").replace("-","");
               fecha=fecha.substring(0,8);
            valores="insert into fiscal (fecha,tipo,numero,gravado,impuesto,total,idcliente,tipoClienteId,razon,cuit) values (lpad("+fecha+",8,'0'),'"+rs.getInt("tipoComprobante")+"','"+numeroComprobante+"',"+gravado+","+impuesto+","+monto+","+rs.getInt("idCliente")+","+tipoClienteId+",'"+rs.getString("razon")+"','"+rs.getString("cuit")+"')";
            System.out.println(valores);
            listado.add(valores);
        }
        Iterator it=listado.listIterator();
        while(it.hasNext()){
            sql=(String)it.next();
            tra.guardarRegistro(sql);
        }
    }
}

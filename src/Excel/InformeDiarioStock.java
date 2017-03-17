/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Excel;

import interfaceGraficas.Inicio;
import interfaces.Transaccionable;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.swing.JOptionPane;
import objetos.ConeccionLocal;
import objetos.Conecciones;
import objetos.Mail;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.IndexedColors;

/**
 *
 * @author Usuario
 */
public class InformeDiarioStock {
    /*
    AQUI SE GENERA EL INFORME DIARIO DE STOCK POR SUCURSAL
    */
    public void GenerrarInformeStock() throws SQLException{
      HSSFWorkbook libro=new HSSFWorkbook();
        HSSFSheet hoja=libro.createSheet("Articulos");
        /*
         * GENERAR LAS SIGUIENTES HOJAS
         * 1- DETALLE DE MOVIMIENTOS DE CAJA - LEE EN MOVIMIENTOS CAJA INDENTIFICANDO EL TIPO DE MOVIMIENTO, USUARIOS Y 
         * NUMERO DE CAJA
         * 2- DETALLE DE ARTICULOS VENDIDOS: LISTADO DE MOVIEMIENTOS DE ARTICULOS, CON USUARIOS Y CAJA
         * 3- DETALLE DE GASTOS : MOVIMIENTOS DE CAJA DETALLANDO LOS GASTOS
         * 
         */
        HSSFSheet hoja1=libro.createSheet("Movimientos");
        HSSFSheet hoja2=libro.createSheet("Movimientos Caja");
       
        String ttx="celda numero :";
        HSSFRow fila=null;
        HSSFCell celda;
        HSSFCell celda1;
        HSSFCell celda2;
        HSSFCell celda3;
        HSSFCell celda4;
        HSSFCell celda5;
        HSSFCell celda6;
        HSSFCell celda7;
        HSSFCell celda8;
        HSSFCell celda9;
        HSSFCell celda10;
        HSSFFont fuente=libro.createFont();
        //fuente.setFontHeight((short)21);
        fuente.setFontName(fuente.FONT_ARIAL);
        fuente.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        String form=null;
        //String sql="select id,nombre,(select sum(movimientosarticulos.cantidad) from movimientosarticulos where movimientosarticulos.idarticulo=articulos.id and movimientosarticulos.numerodeposito="+Inicio.deposito.getNumero()+")as stock,(select sum(movimientosarticulos.cantidad) from movimientosarticulos where movimientosarticulos.idcaja="+Inicio.caja.getNumero()+" and movimientosarticulos.idarticulo=articulos.id)as cantidadVendida from articulos";
       String sql="select *,(select articulos.NOMBRE from articulos where articulos.ID=movimientosarticulos.idArticulo)as descripcion from movimientosarticulos where idcaja="+Inicio.caja.getNumero();
        System.out.println(sql);
        Transaccionable tra=new ConeccionLocal();
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        HSSFCellStyle titulo=libro.createCellStyle();
        titulo.setFont(fuente);
        //titulo.setFillBackgroundColor((short)22);
        titulo.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        titulo.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        //for(int a=0;a < 100;a++){
        int col=0;
        int a=0;
            if(a==0){
                fila=hoja.createRow(a);
            celda=fila.createCell(0);
            celda.setCellStyle(titulo);
            celda.setCellValue("id");
            celda1=fila.createCell(1);
            celda1.setCellStyle(titulo);
            celda1.setCellValue("nombre");
            celda2=fila.createCell(2);
            celda2.setCellStyle(titulo);
            celda2.setCellValue("Unidades Vendidas");
            celda3=fila.createCell(3);
            celda3.setCellStyle(titulo);
            celda3.setCellValue("Fecha");
            }
            while(rs.next()){
            a++;
            //col=rs.getInt("tipoMovimiento");
            switch(col){
                case 1:
                    
                    break;
                default:
                    
                    break;
            }
            fila=hoja.createRow(a);
            celda=fila.createCell(0);
            ttx=ttx;
            Double anterior=0.00;
            Double actual=0.00;
            Double vendido=0.00;
            celda.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            celda.setCellValue(rs.getInt("idArticulo"));
            celda1=fila.createCell(1);
            ttx=ttx;
            celda1.setCellType(HSSFCell.CELL_TYPE_STRING);
            celda1.setCellValue(rs.getString("descripcion"));
            celda2=fila.createCell(2);
            vendido=(rs.getDouble("cantidad")) * -1;
            //actual=rs.getDouble("stock");
            celda2.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            celda2.setCellValue(vendido);
            celda3=fila.createCell(3);
            //vendido=(rs.getDouble("cantidadVendida")) * -1;
            celda3.setCellType(HSSFCell.CELL_TYPE_STRING);
            celda3.setCellValue(rs.getString("fecha"));

        }
            //rs.close();
        
            // hoja 2
            
        form=null;
        sql="SELECT id,cantidad,preciodecosto,preciodeventa,precioservicio,fecha,numerocomprobante,(select listcli.RAZON_SOCI from listcli where listcli.codMmd=movimientosarticulos.numeroCliente limit 0,1)as nombreC,(select articulos.NOMBRE from articulos where articulos.ID=movimientosarticulos.idArticulo limit 0,1)as descA,(select usuarios.nombre from usuarios where usuarios.numero=movimientosarticulos.numeroUsuario limit 0,1) as nombreU FROM movimientosarticulos where tipoMovimiento =1 and idcaja="+Inicio.caja.getNumero();
        //System.out.println(sql);
        //tra=new Conecciones();
        rs=tra.leerConjuntoDeRegistros(sql);
        //HSSFCellStyle titulo=libro.createCellStyle();
        titulo.setFont(fuente);
        //titulo.setFillBackgroundColor((short)22);
        titulo.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        titulo.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        //for(int a=0;a < 100;a++){
        col=0;
        a=0;
            if(a==0){
                fila=hoja1.createRow(a);
            celda=fila.createCell(0);
            celda.setCellStyle(titulo);
            celda.setCellValue("Cajero");
            celda1=fila.createCell(1);
            celda1.setCellStyle(titulo);
            celda1.setCellValue("Descripcion Articulo");
            celda2=fila.createCell(2);
            celda2.setCellStyle(titulo);
            celda2.setCellValue("Cantidad");
            celda3=fila.createCell(3);
            celda3.setCellStyle(titulo);
            celda3.setCellValue("Precio de Costo");
            celda4=fila.createCell(4);
            celda4.setCellStyle(titulo);
            celda4.setCellValue("Precio de Venta");
            celda5=fila.createCell(5);
            celda5.setCellStyle(titulo);
            celda5.setCellValue("Fecha");
            celda6=fila.createCell(6);
            celda6.setCellStyle(titulo);
            celda6.setCellValue("Precio de Servicio");
            celda7=fila.createCell(7);
            celda7.setCellStyle(titulo);
            celda7.setCellValue("comprobante");

            celda8=fila.createCell(8);
            celda8.setCellStyle(titulo);
            celda8.setCellValue("Cliente");
            celda9=fila.createCell(9);
            celda9.setCellStyle(titulo);
            celda9.setCellValue("Total");
            celda10=fila.createCell(10);
            celda10.setCellStyle(titulo);
            celda10.setCellValue("idMovimiento");
            
            
            }
            while(rs.next()){
            a++;
            //col=rs.getInt("tipoMovimiento");
            switch(col){
                case 1:
                    
                    break;
                default:
                    
                    break;
            }
            fila=hoja1.createRow(a);
            celda=fila.createCell(0);
            ttx=ttx;
            celda.setCellType(HSSFCell.CELL_TYPE_STRING);
            celda.setCellValue(rs.getString("nombreU"));
            celda1=fila.createCell(1);
            ttx=ttx;
            celda1.setCellType(HSSFCell.CELL_TYPE_STRING);
            celda1.setCellValue(rs.getString("descA"));
            celda2=fila.createCell(2);
            celda2.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            celda2.setCellValue(rs.getDouble("cantidad"));
            celda3=fila.createCell(3);
            celda3.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            celda3.setCellValue(rs.getDouble("precioDeCosto"));
            celda4=fila.createCell(4);
            celda4.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            celda4.setCellValue(rs.getDouble("precioDeVenta"));
            
           
            celda5=fila.createCell(5);
            //celda5.setCellFormula(rs.getString("observaciones"));
            celda5.setCellType(HSSFCell.CELL_TYPE_STRING);
            celda5.setCellValue(" "+rs.getDate("fecha"));
            //celda5.setCellValue(rs.getDate("fecha"));
            celda6=fila.createCell(6);
            celda6.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            celda6.setCellValue(rs.getDouble("precioServicio"));
            celda7=fila.createCell(7);
            celda7.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            celda7.setCellValue(rs.getInt("numeroComprobante"));
            
            celda8=fila.createCell(8);
            celda8.setCellType(HSSFCell.CELL_TYPE_STRING);
            celda8.setCellValue(rs.getString("nombreC"));
            
            celda9=fila.createCell(9);
            celda9.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            Double tto=0.00;
            tto=rs.getDouble("precioServicio")+ rs.getDouble("precioDeVenta");
            celda9.setCellValue(tto);
            celda10=fila.createCell(10);
            celda10.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            celda10.setCellValue(rs.getInt("id"));
        }
            
            form=null;
        sql="SELECT *,(select usuarios.nombre from usuarios where usuarios.numero=movimientoscaja.numeroUsuario)as nombreUsuario,(select listcli.RAZON_SOCI from listcli where listcli.id=movimientoscaja.idCliente)as nombreCliente,(select tipocomprobantes.descripcion from tipocomprobantes where tipocomprobantes.numero=movimientoscaja.tipoComprobante)as nombreComprobante,(select tipomovimientos.DESCRIPCION from tipomovimientos where tipomovimientos.ID=movimientoscaja.tipoMovimiento)as nombreMov FROM movimientoscaja where idcaja="+Inicio.caja.getNumero()+" order by id";
        //System.out.println(sql);
        //tra=new Conecciones();
        rs=tra.leerConjuntoDeRegistros(sql);
        //HSSFCellStyle titulo=libro.createCellStyle();
        titulo.setFont(fuente);
        //titulo.setFillBackgroundColor((short)22);
        titulo.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        titulo.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        //for(int a=0;a < 100;a++){
        col=0;
        a=0;
            if(a==0){
                fila=hoja2.createRow(a);
            celda=fila.createCell(0);
            celda.setCellStyle(titulo);
            celda.setCellValue("Cajero");
            celda1=fila.createCell(1);
            celda1.setCellStyle(titulo);
            celda1.setCellValue("Cliente");
            celda2=fila.createCell(2);
            celda2.setCellStyle(titulo);
            celda2.setCellValue("Comprobante Numero");
            celda3=fila.createCell(3);
            celda3.setCellStyle(titulo);
            celda3.setCellValue("Tipo Comprobante");
            celda4=fila.createCell(4);
            celda4.setCellStyle(titulo);
            celda4.setCellValue("Monto");
            celda5=fila.createCell(5);
            celda5.setCellStyle(titulo);
            celda5.setCellValue("Fecha");
            celda6=fila.createCell(6);
            celda6.setCellStyle(titulo);
            celda6.setCellValue("Tipo de Movimiento");
            celda7=fila.createCell(7);
            celda7.setCellStyle(titulo);
            celda7.setCellValue("Condicion");

            celda8=fila.createCell(8);
            celda8.setCellStyle(titulo);
            celda8.setCellValue("idMovimiento");
            
            
            }
            while(rs.next()){
            a++;
            //col=rs.getInt("tipoMovimiento");
            switch(col){
                case 1:
                    
                    break;
                default:
                    
                    break;
            }
            fila=hoja2.createRow(a);
            celda=fila.createCell(0);
            ttx=ttx;
            celda.setCellType(HSSFCell.CELL_TYPE_STRING);
            celda.setCellValue(rs.getString("nombreUsuario"));
            celda1=fila.createCell(1);
            ttx=ttx;
            celda1.setCellType(HSSFCell.CELL_TYPE_STRING);
            celda1.setCellValue(rs.getString("nombreCliente"));
            celda2=fila.createCell(2);
            celda2.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            celda2.setCellValue(rs.getInt("numeroComprobante"));
            celda3=fila.createCell(3);
            celda3.setCellType(HSSFCell.CELL_TYPE_STRING);
            celda3.setCellValue(rs.getString("nombreComprobante"));
            celda4=fila.createCell(4);
            celda4.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            celda4.setCellValue(rs.getDouble("monto"));
            
           
            celda5=fila.createCell(5);
            //celda5.setCellFormula(rs.getString("observaciones"));
            celda5.setCellType(HSSFCell.CELL_TYPE_STRING);
            celda5.setCellValue(" "+rs.getDate("fecha"));
            //celda5.setCellValue(rs.getDate("fecha"));
            celda6=fila.createCell(6);
            celda6.setCellType(HSSFCell.CELL_TYPE_STRING);
            celda6.setCellValue(rs.getString("nombreMov"));
            celda7=fila.createCell(7);
            String pagado="PAGADO";
            if(rs.getInt("pagado")==0 && rs.getInt("tipoMovimiento")==1)pagado="CTA CTE";
            celda7.setCellType(HSSFCell.CELL_TYPE_STRING);
            celda7.setCellValue(pagado);
            
            celda8=fila.createCell(8);
            celda8.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            celda8.setCellValue(rs.getInt("id"));
        }
            
            
            rs.close();
        

//texto+="\r\n";
        String ruta="C://Informes//"+Inicio.fechaDia+"_"+Inicio.usuario.getNombre()+" - informeDeStock.xls";
        String nombree=Inicio.fechaDia+"_"+Inicio.usuario.getNombre()+" - informeDeStock.xls";
        try {
            FileOutputStream elFichero=new FileOutputStream(ruta);
            try {
                libro.write(elFichero);
                elFichero.close();
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+ruta);
                Mail mail=new Mail();
                mail.setDetalleListado(nombree);
                mail.setDireccionFile(ruta);
                mail.setAsunto("Informe de cierre de caja "+Inicio.fechaDia+" Sucursal: PocoPrecio 1 ");
                mail.enviarMailRepartoCargaCompleta();
            } catch (IOException ex) {
                Logger.getLogger(InformeMensual.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MessagingException ex) {
              Logger.getLogger(InformeDiarioStock.class.getName()).log(Level.SEVERE, null, ex);
              JOptionPane.showMessageDialog(null,"NO SE HA PODIDO ENVIAR EL MENSAJE MOTIVO :"+ex);
          }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(InformeMensual.class.getName()).log(Level.SEVERE, null, ex);
        }
        sql="select id,nombre,precio,servicio,(select sum(movimientosarticulos.cantidad) from movimientosarticulos where movimientosarticulos.idcaja="+Inicio.caja.getNumero()+" and movimientosarticulos.idarticulo=articulos.id)as cantidadVendida from articulos order by cantidadVendida desc";
        rs=tra.leerConjuntoDeRegistros(sql);
        System.out.println(sql);
        Transaccionable tt=new Conecciones();
        String sql1;
        Double cantVend;
        while(rs.next()){
            cantVend=rs.getDouble("cantidadVendida");
            if(cantVend < 0){
        //sql1="insert into movimientosarticulosF (tipoMovimiento,idArticulo,cantidad,numeroDeposito,tipoComprobante,numeroCliente,numerousuario,precioDeVenta,precioServicio,idcaja) values (1,"+rs.getInt("id")+",floor("+rs.getDouble("cantidadVendida")+" * 0.1),"+Inicio.deposito.getNumero()+",1,1,"+Inicio.usuario.getNumeroId()+","+rs.getDouble("precio")+","+rs.getDouble("servicio")+","+Inicio.caja.getNumero()+")";
        //System.out.println(sql1);
        //tt.guardarRegistro(sql1);
            }
                
                }
    }
}

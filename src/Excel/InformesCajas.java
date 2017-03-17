/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Excel;

import Conversores.Numeros;
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
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.IndexedColors;

/**
 *
 * @author mauro
 */
public class InformesCajas {
  public void GenerarInformeDeArticulos(String desde,String hasta) throws SQLException{
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
        String porcentajeG=JOptionPane.showInputDialog("Ingrese porcentaje de ganancia estimada");
        Double porcentaje=Numeros.ConvertirStringADouble(porcentajeG);
        Double resultado;
        Double totalisados;
        porcentaje=porcentaje / 100;
        //String sql="select id,nombre,(select sum(movimientosarticulos.cantidad) from movimientosarticulos where movimientosarticulos.idarticulo=articulos.id and movimientosarticulos.fecha between '"+desde+"' and '"+hasta+"')as stock,(select sum(movimientosarticulos.cantidad) from movimientosarticulos where movimientosarticulos.fecha between '"+desde+"' and '"+hasta+"' and movimientosarticulos.idarticulo=articulos.id)as cantidadVendida from articulos";
       String sql="select *,sum(cantidad)as cantidadT,sum(precioDeCosto * cantidad)as costoT,sum(precioDeVenta)as ventaT,(select articulos.NOMBRE from articulos where articulos.ID=movimientosarticulos.idArticulo)as descripcion from movimientosarticulos where fecha between '"+desde+"' and '"+hasta+"' group by idArticulo order by cantidadT";
        System.out.println(sql);
        Transaccionable tra=new ConeccionLocal();
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        HSSFCellStyle titulo=libro.createCellStyle();
        HSSFCellStyle celdaSt=null;
        DataFormat format=libro.createDataFormat();
        titulo.setFont(fuente);
        //titulo.setFillBackgroundColor((short)22);
        titulo.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        titulo.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        celdaSt=libro.createCellStyle();
        celdaSt.setDataFormat(format.getFormat("$ #,##0.00"));
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
            celda3.setCellValue("Precio de Costo");
            celda4=fila.createCell(4);
            celda4.setCellStyle(titulo);
            celda4.setCellValue("Precio de Venta");
            celda5=fila.createCell(5);
            celda5.setCellStyle(titulo);
            celda5.setCellValue("Fecha");
            celda6=fila.createCell(6);
            celda6.setCellStyle(titulo);
            celda6.setCellValue("Ganancia Estimada");
            celda7=fila.createCell(7);
            celda7.setCellStyle(titulo);
            celda7.setCellValue("Ganancia del "+porcentajeG+" %");
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
            resultado=0.00;
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
            vendido=(rs.getDouble("cantidadT")) * -1;
            //actual=rs.getDouble("stock");
            celda2.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            celda2.setCellValue(vendido);
            celda3=fila.createCell(3);
            //vendido=(rs.getDouble("cantidadT")) * -1;
            //actual=rs.getDouble("stock");
            celda3.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            celda3.setCellValue(rs.getDouble("costoT") * (-1));
            celda3.setCellStyle(celdaSt);
            celda4=fila.createCell(4);
            
            //vendido=(rs.getDouble("cantidadT")) * -1;
            //actual=rs.getDouble("stock");
            celda4.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            celda4.setCellValue(rs.getDouble("ventaT"));
            celda4.setCellStyle(celdaSt);
            celda5=fila.createCell(5);
            //vendido=(rs.getDouble("cantidadVendida")) * -1;
            celda5.setCellType(HSSFCell.CELL_TYPE_STRING);
            celda5.setCellValue(rs.getString("fecha"));
            celda6=fila.createCell(6);
            celda6.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            if(rs.getDouble("costoT")==0.00){
                totalisados=rs.getDouble("ventaT");
                resultado=totalisados * porcentaje;
            }else{
                resultado=rs.getDouble("ventaT");
                
                totalisados=rs.getDouble("costoT") * (-1);
                resultado=resultado - totalisados;
            }
            celda6.setCellValue(resultado);
            celda6.setCellStyle(celdaSt);
            celda7=fila.createCell(7);
            celda7.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            totalisados=rs.getDouble("ventaT");
            resultado=totalisados * porcentaje;
            celda7.setCellStyle(celdaSt);
            celda7.setCellValue(resultado);

        }
            
            /*
            
            CIERRE DE HOJA
            */
            
             hoja.autoSizeColumn((short)0);
        hoja.autoSizeColumn((short)1);
        hoja.autoSizeColumn((short)2);
        hoja.autoSizeColumn((short)3);
        hoja.autoSizeColumn((short)4);
        hoja.autoSizeColumn((short)5);
        hoja.autoSizeColumn((short)6);
        hoja.autoSizeColumn((short)7);
        
            //rs.close();
        
            // hoja 2
            
        form=null;
        sql="SELECT id,cantidad,preciodecosto,preciodeventa,precioservicio,fecha,numerocomprobante,(select listcli.RAZON_SOCI from listcli where listcli.codMmd=movimientosarticulos.numeroCliente limit 0,1)as nombreC,(select articulos.NOMBRE from articulos where articulos.ID=movimientosarticulos.idArticulo limit 0,1)as descA,(select usuarios.nombre from usuarios where usuarios.numero=movimientosarticulos.numeroUsuario limit 0,1) as nombreU FROM movimientosarticulos where tipoMovimiento =1 and fecha between '"+desde+"' and '"+hasta+"'";
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
        sql="SELECT *,(select usuarios.nombre from usuarios where usuarios.numero=movimientoscaja.numeroUsuario)as nombreUsuario,(select listcli.RAZON_SOCI from listcli where listcli.id=movimientoscaja.idCliente)as nombreCliente,(select tipocomprobantes.descripcion from tipocomprobantes where tipocomprobantes.numero=movimientoscaja.tipoComprobante)as nombreComprobante,(select tipomovimientos.DESCRIPCION from tipomovimientos where tipomovimientos.ID=movimientoscaja.tipoMovimiento)as nombreMov FROM movimientoscaja where fecha between '"+desde+"' and '"+hasta+"' order by id";
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
            celda8=fila.createCell(9);
            celda8.setCellType(HSSFCell.CELL_TYPE_STRING);
            celda8.setCellValue(rs.getString("observaciones"));
        }
            
            
            rs.close();
        

//texto+="\r\n";
        String ruta=Inicio.fechaDia+"_"+Inicio.usuario.getNombre()+" - informeMensual.xls";
        String nombree=Inicio.fechaDia+"_"+Inicio.usuario.getNombre()+" - informeMenusual.xls";
        try {
            FileOutputStream elFichero=new FileOutputStream(ruta);
            try {
                libro.write(elFichero);
                elFichero.close();
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+ruta);
                /*
                Mail mail=new Mail();
                mail.setDetalleListado(nombree);
                mail.setDireccionFile(ruta);
                mail.setAsunto("Informe mensual "+Inicio.fechaDia+" Sucursal: PocoPrecio 1 ");
                mail.enviarMailRepartoCargaCompleta();
                        */
            } catch (IOException ex) {
                Logger.getLogger(InformeMensual.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(InformeMensual.class.getName()).log(Level.SEVERE, null, ex);
        }
       
  }  
}

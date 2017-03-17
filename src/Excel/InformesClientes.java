/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Excel;

import facturacion.clientes.ClientesTango;
import interfaces.Transaccionable;
import interfacesPrograma.Busquedas;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import objetos.Conecciones;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.IndexedColors;

/**
 *
 * @author mauro di
 */
public class InformesClientes {
   public void GenerarInforme(ArrayList listadoClientes,String desde,String hasta) throws SQLException{
              HSSFWorkbook libro=new HSSFWorkbook();
        HSSFSheet hoja=libro.createSheet("Saldos de Clientes");
        HSSFSheet hoja1=libro.createSheet("Detalle de Saldos");
        
        /*
         * GENERAR LAS SIGUIENTES HOJAS
         * 1- DETALLE DE MOVIMIENTOS DE CAJA - LEE EN MOVIMIENTOS CAJA INDENTIFICANDO EL TIPO DE MOVIMIENTO, USUARIOS Y 
         * NUMERO DE CAJA
         * 2- DETALLE DE ARTICULOS VENDIDOS: LISTADO DE MOVIEMIENTOS DE ARTICULOS, CON USUARIOS Y CAJA
         * 3- DETALLE DE GASTOS : MOVIMIENTOS DE CAJA DETALLANDO LOS GASTOS
         * 
         */
        
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
        HSSFFont fuente=libro.createFont();
        //fuente.setFontHeight((short)21);
        fuente.setFontName(fuente.FONT_ARIAL);
        fuente.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        String form=null;
        String sql="";
        System.out.println(sql);
        Transaccionable tra=new Conecciones();
        ResultSet rs=null;
        HSSFCellStyle titulo=libro.createCellStyle();
        Iterator iCli=listadoClientes.listIterator();
        ClientesTango cliente=new ClientesTango();
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
            celda.setCellValue("Cod. Cliente");
            celda1=fila.createCell(1);
            celda1.setCellStyle(titulo);
            celda1.setCellValue("Nombre");
            celda2=fila.createCell(2);
            celda2.setCellStyle(titulo);
            celda2.setCellValue("Direccion");
            celda3=fila.createCell(3);
            celda3.setCellStyle(titulo);
            celda3.setCellValue("Teléfono");
            celda4=fila.createCell(4);
            celda4.setCellStyle(titulo);
            celda4.setCellValue("Cupo de Crédito");
            celda5=fila.createCell(5);
            celda5.setCellStyle(titulo);
            celda5.setCellValue("Saldo");
            }
            while(iCli.hasNext()){
                cliente=(ClientesTango)iCli.next();
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
            celda.setCellType(HSSFCell.CELL_TYPE_STRING);
            celda.setCellValue(cliente.getCodigoCliente());
            celda1=fila.createCell(1);
            ttx=ttx;
            celda1.setCellType(HSSFCell.CELL_TYPE_STRING);
            celda1.setCellValue(cliente.getRazonSocial());
            celda2=fila.createCell(2);
            celda2.setCellType(HSSFCell.CELL_TYPE_STRING);
            celda2.setCellValue(cliente.getDireccion());
            celda3=fila.createCell(3);
            celda3.setCellType(HSSFCell.CELL_TYPE_STRING);
            celda3.setCellValue(cliente.getTelefono());
            celda4=fila.createCell(4);
            celda4.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            celda4.setCellValue(cliente.getCupoDeCredito());
            
           
            celda5=fila.createCell(5);
            //celda5.setCellFormula(rs.getString("observaciones"));
            celda5.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            celda5.setCellValue(cliente.getSaldo());
            //celda5.setCellValue(rs.getDate("fecha"));
            
        }
          /*
           * segunda hoja
           */  
           Busquedas bus=new ClientesTango();
            sql="select numeroProveedor,fecha,monto,numeroComprobante,idUsuario,idCaja,tipoComprobante,idSucursal,(select listcli.razon_soci from listcli where listcli.codmmd=numeroProveedor)as nombreP from movimientosclientes where numeroProveedor > 1 and fecha between '"+desde+"' and '"+hasta+"' group by numeroComprobante order by numeroProveedor,fecha";
            System.out.println(sql);
        //fuente.setFontHeight((short)21);
        fuente.setFontName(fuente.FONT_ARIAL);
        fuente.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        form=null;
        //String sql="SELECT *,(select proveedores.nombre from proveedores where proveedores.numero=movimientosproveedores.numeroProveedor)as nombreP,if(pagado=0,'pendiente','pagado')as estado FROM movimientosproveedores where fecha between '"+desde+"' and '"+hasta+"'";
        //System.out.println(sql);
        //tra=new Conecciones();
        rs=tra.leerConjuntoDeRegistros(sql);
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
            celda.setCellValue("Cliente");
            celda1=fila.createCell(1);
            celda1.setCellStyle(titulo);
            celda1.setCellValue("Sucursal");
            celda2=fila.createCell(2);
            celda2.setCellStyle(titulo);
            celda2.setCellValue("Fecha");
            celda3=fila.createCell(3);
            celda3.setCellStyle(titulo);
            celda3.setCellValue("Monto");
            celda4=fila.createCell(4);
            celda4.setCellStyle(titulo);
            celda4.setCellValue("Caja Numero");
            celda5=fila.createCell(5);
            celda5.setCellStyle(titulo);
            celda5.setCellValue("tipo de comprobante");
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
            celda.setCellValue(rs.getString("nombreP"));
            celda1=fila.createCell(1);
            ttx=ttx;
            celda1.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            celda1.setCellValue(rs.getInt("idSucursal"));
            celda2=fila.createCell(2);
            celda2.setCellType(HSSFCell.CELL_TYPE_STRING);
            celda2.setCellValue(" "+rs.getString("fecha"));
            celda3=fila.createCell(3);
            celda3.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            celda3.setCellValue(rs.getDouble("monto"));
            celda4=fila.createCell(4);
            celda4.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            celda4.setCellValue(rs.getInt("idCaja"));
            celda5=fila.createCell(5);
            celda5.setCellType(HSSFCell.CELL_TYPE_STRING);
            if(rs.getInt("tipoComprobante")==11){
                celda5.setCellValue("Recibo de Pago");
            }else{
                celda5.setCellValue("Venta");
            }
        } 
         
            
        rs.close();
        //texto+="\r\n";
        String ruta="C://Informes//"+desde+"_"+hasta+"_informeDeClientes.xls";
        try {
            FileOutputStream elFichero=new FileOutputStream(ruta);
            try {
                libro.write(elFichero);
                elFichero.close();
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+ruta);
            } catch (IOException ex) {
                Logger.getLogger(InformeMensual.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(InformeMensual.class.getName()).log(Level.SEVERE, null, ex);
        }

    } 
}

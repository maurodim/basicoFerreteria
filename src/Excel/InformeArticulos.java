/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Excel;

import facturacion.clientes.ClientesTango;
import interfaces.Editables;
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
import objetos.Articulos;
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
public class InformeArticulos {
  public void GenerarInforme(ArrayList listadoClientes) throws SQLException{
              HSSFWorkbook libro=new HSSFWorkbook();
        HSSFSheet hoja=libro.createSheet("Listado de Articulos");
        ArrayList listadoPorSucursal=new ArrayList();
        Editables edi=new Articulos();
        
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
        HSSFCell celda9;
        HSSFCell celda10;
        HSSFCell celda11;
        HSSFFont fuente=libro.createFont();
        //fuente.setFontHeight((short)21);
        fuente.setFontName(fuente.FONT_ARIAL);
        fuente.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        String form=null;
        
        HSSFCellStyle titulo=libro.createCellStyle();
        Iterator iCli=listadoClientes.listIterator();
        Articulos cliente=new Articulos();
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
            celda.setCellValue("Codigo");
            celda1=fila.createCell(1);
            celda1.setCellStyle(titulo);
            celda1.setCellValue("Descripcion");
            celda2=fila.createCell(2);
            celda2.setCellStyle(titulo);
            celda2.setCellValue("Stock");
            celda3=fila.createCell(3);
            celda3.setCellStyle(titulo);
            celda3.setCellValue("Stock Mínimo");
            celda4=fila.createCell(4);
            celda4.setCellStyle(titulo);
            celda4.setCellValue("Costo");
            celda5=fila.createCell(5);
            celda5.setCellStyle(titulo);
            celda5.setCellValue("Precio de Venta");
            celda6=fila.createCell(6);
            celda6.setCellStyle(titulo);
            celda6.setCellValue("Servicio");
            }
            while(iCli.hasNext()){
                cliente=(Articulos)iCli.next();
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
            celda.setCellValue(cliente.getCodigoAsignado());
            celda1=fila.createCell(1);
            ttx=ttx;
            celda1.setCellType(HSSFCell.CELL_TYPE_STRING);
            celda1.setCellValue(cliente.getDescripcionArticulo());
            celda2=fila.createCell(2);
            celda2.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            celda2.setCellValue(cliente.getStockActual());
            celda3=fila.createCell(3);
            celda3.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            celda3.setCellValue(cliente.getStockMinimo());
            celda4=fila.createCell(4);
            celda4.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            celda4.setCellValue(cliente.getPrecioDeCosto());
            
           
            celda5=fila.createCell(5);
            //celda5.setCellFormula(rs.getString("observaciones"));
            celda5.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            celda5.setCellValue(cliente.getPrecioUnitarioNeto());
            //celda5.setCellValue(rs.getDate("fecha"));
            celda6=fila.createCell(6);
            //celda5.setCellFormula(rs.getString("observaciones"));
            celda6.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            celda6.setCellValue(cliente.getPrecioServicio());
            listadoPorSucursal=edi.ListarPorSucursal(cliente);
            Iterator il=listadoPorSucursal.listIterator();
            Articulos arr=new Articulos();
            int cont=0;
            while(il.hasNext()){
                arr=(Articulos)il.next();
                cont++;
                switch (cont){
                
                    case 1:
                        celda7=fila.createCell(7);
                celda7.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                celda7.setCellValue(arr.getCantidad());
                        break;
                    case 2:
                        celda8=fila.createCell(8);
                celda8.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                celda8.setCellValue(arr.getCantidad());
                        break;
                    case 3:
                        celda9=fila.createCell(9);
                celda9.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                celda9.setCellValue(arr.getCantidad());
                        break;
                    case 4:
                        celda10=fila.createCell(10);
                celda10.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                celda10.setCellValue(arr.getCantidad());
                        break;
                }
                
            }
        }
          
            
        
        //texto+="\r\n";
        String ruta="C://Informes//listadoDeArticulos.xls";
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

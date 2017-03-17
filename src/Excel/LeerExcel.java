/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Excel;

import Conversores.Numeros;
import interfaces.Transaccionable;
import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import objetos.ConeccionLocal;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;


/**
 *
 * @author mauro di
 */
public class LeerExcel {
    private String sql;
    private Transaccionable tra;
    
   public void leerExcel1(String fileName) throws SQLException {
       tra=new ConeccionLocal();
       List cellDataList = new ArrayList();
try
{
/**
* Create a new instance for FileInputStream class
*/
FileInputStream fileInputStream = new FileInputStream(fileName);
/**
* Create a new instance for POIFSFileSystem class
*/
POIFSFileSystem fsFileSystem = new POIFSFileSystem(fileInputStream);
/*
* Create a new instance for HSSFWorkBook Class
*/
HSSFWorkbook workBook = new HSSFWorkbook(fsFileSystem);
HSSFSheet hssfSheet = workBook.getSheetAt(0);
/**
* Iterate the rows and cells of the spreadsheet
* to get all the datas.
*/
Iterator rowIterator = hssfSheet.rowIterator();
while (rowIterator.hasNext())
{
HSSFRow hssfRow = (HSSFRow) rowIterator.next();
Iterator iterator = hssfRow.cellIterator();
List cellTempList = new ArrayList();
while (iterator.hasNext())
{
HSSFCell hssfCell = (HSSFCell) iterator.next();
cellTempList.add(hssfCell);
}
cellDataList.add(cellTempList);
}
}
catch (Exception e)
{
e.printStackTrace();
}
/**
* Call the printToConsole method to print the cell data in the
* console.
*/
printToConsole(cellDataList);
}
/**
* This method is used to print the cell data to the console.
* @param cellDataList - List of the data's in the spreadsheet.
*/
private void printToConsole(List cellDataList)
{
    String error=""; 
    int fila=0;
    
    Boolean verif=false;
    ArrayList lstArt=new ArrayList();
    String unidadDeMedida="";
    Double peso=0.00;
    
    Integer porc=0;
     String barra = null;
        String descripcion = null;
        String rubro = null;
        String talle1 = null;
        String talle2 = null;
        String talle3 = null;
        String talle4 = null;
        String talle5 = null;
        String talle6 = null;
        String talle7 = null;
        String talle8 = null;
        String talle9 = null;
        String precio = null;
        String talle=null;
        String sentencia="insert into articulos (BARRAS,NOMBRE,SERVICIO,COSTO,PRECIO) value ";
        
        
    for (int i = 0; i < cellDataList.size(); i++)
    {
        List cellTempList = (List) cellDataList.get(i);
        
        
       
        int alerta=0;
       
        for (int j = 0; j < cellTempList.size(); j++)
        {
            HSSFCell hssfCell = (HSSFCell) cellTempList.get(j);
            String stringCellValue = hssfCell.toString();
            //System.err.println("Contenido: "+j+" "+stringCellValue);
            //descripcion="";
            //if(i > 0){
                switch (j){
                    case 0:
                        //numeroComprobante=stringCellValue;
                        //descrip=String.valueOf(stringCellValue);
                        
                        //rubro=stringCellValue;
                        int hallado=stringCellValue.indexOf("L");
                        if(hallado > -1){
                            rubro=stringCellValue;
                            alerta=1;
                            System.out.println(j+" / RUBRO: "+rubro);
                        }else{
                            porc=Numeros.ConvertirStringAInteger(stringCellValue);
                            barra=String.valueOf(porc);
                            alerta=0;
                            System.out.println(j+" / BARRA: "+barra);
                        }
                        break;
                    case 1:
                        System.out.println(j+" / "+stringCellValue);
                        //System.out.println("nombre: "+j+" "+stringCellValue);
                        descripcion=stringCellValue;
                        break;
                    case 2:
                        System.out.println(j+" / "+stringCellValue);
                        //System.out.println("rfid: "+j+" "+stringCellValue);
                        if(alerta==1){
                            porc=Numeros.ConvertirStringAInteger(stringCellValue);
                            talle1=String.valueOf(porc);
                            //talle1=stringCellValue;
                            
                        }else{
                            precio=stringCellValue;
                            talle=talle1;
                        }
                        break;
                    case 3:
                        System.out.println(j+" / "+stringCellValue);
                        //System.out.println("Direccion: "+j+" "+stringCellValue);
                        if(alerta==1){
                            porc=Numeros.ConvertirStringAInteger(stringCellValue);
                            talle2=String.valueOf(porc);
                            //talle2=stringCellValue;
                            
                        }else{
                            precio=stringCellValue;
                            
                            talle=talle2;
                        }
                        break;
                    case 4:
                        System.out.println(j+" / "+stringCellValue);
                        //System.out.println("Teelfono: "+j+" "+stringCellValue);
                        if(alerta==1){
                            if(stringCellValue.equals("")){
                                
                            }else{
                            porc=Numeros.ConvertirStringAInteger(stringCellValue);
                            talle3=String.valueOf(porc);
                            }
                            //talle3=stringCellValue;
                            
                        }else{
                            precio=stringCellValue;
                            talle=talle3;
                        }
                        break;
                    case 5:
                        System.out.println(j+" / "+stringCellValue);
                        //System.out.println("Mail: "+j+" "+stringCellValue);
                        if(alerta==1){
                            if(stringCellValue.equals("")){
                                
                            }else{
                            porc=Numeros.ConvertirStringAInteger(stringCellValue);
                            talle4=String.valueOf(porc);
                            }
                            //talle4=stringCellValue;
                            
                        }else{
                            precio=stringCellValue;
                            talle=talle4;
                        }
                        
                        break;
                    case 6:
                        System.out.println(j+" / "+stringCellValue);
                        if(alerta==1){
                            if(stringCellValue.equals("")){
                                
                            }else{
                            porc=Numeros.ConvertirStringAInteger(stringCellValue);
                            talle5=String.valueOf(porc);
                            //talle5=stringCellValue;
                            }
                            
                        }else{
                            precio=stringCellValue;
                            talle=talle5;
                        }
                        //fila++;
                        break;
                    case 7:
                        System.out.println(j+" / "+stringCellValue);
                        //tra.guardarRegistro(sql);
                        if(alerta==1){
                            if(stringCellValue.equals("")){
                                
                            }else{
                            porc=Numeros.ConvertirStringAInteger(stringCellValue);
                            talle6=String.valueOf(porc);
                            }
                            //talle6=stringCellValue;
                            
                        }else{
                            precio=stringCellValue;
                            talle=talle6;
                        }
                        break;
                    case 8:
                        System.out.println(j+" / "+stringCellValue);
                        //fila++;
                        if(alerta==1){
                            if(stringCellValue.equals("")){
                                
                            }else{
                            porc=Numeros.ConvertirStringAInteger(stringCellValue);
                            talle7=String.valueOf(porc);
                            }
                            //talle7=stringCellValue;
                            
                        }else{
                            precio=stringCellValue;
                            talle=talle7;
                        }
                        break;
                    case 9:
                        System.out.println(j+" / "+stringCellValue);
                        //fila++;
                        if(alerta==1){
                            if(stringCellValue.equals("")){
                                
                            }else{
                            porc=Numeros.ConvertirStringAInteger(stringCellValue);
                            talle8=String.valueOf(porc);
                            }
                            //talle8=stringCellValue;
                            
                        }else{
                            precio=stringCellValue;
                            talle=talle8;
                        }
                        break;
                    case 10:
                        System.out.println(j+" / "+stringCellValue);
                        //fila++;
                        if(alerta==1){
                            if(stringCellValue.equals("")){
                                
                            }else{
                            porc=Numeros.ConvertirStringAInteger(stringCellValue);
                            talle9=String.valueOf(porc);
                            }
                            //talle9=stringCellValue;
                            
                        }else{
                            precio=stringCellValue;
                            talle=talle9;
                        }
                        
                        break;
                        
                }
                if (j > 1){
                    if(alerta== 0){
                        System.out.println(precio);
                        if(precio.equals("")){
                        }else{
                            sentencia+="('"+rubro+barra+talle+"','"+descripcion+" Talle:"+talle+"',0,0,"+precio+"),";
                            precio=null;
                        }
                    
                    }
                }
                //System.out.println("CODIGO: "+rubro+barra+talle+" $ "+precio);
                
                
            //}
            //System.err.println("FINAL");
            
            //fac.modificar(cliente);
            
        }
        
        
        System.out.println("  FINAL DE RENGLON");
        barra=null;
        fila++;
    }
    System.err.println(sentencia);
    JOptionPane.showMessageDialog(null,"PROCESO EXITOSO \n CANTIDAD DE FILAS PROCESADAS "+fila);
   }
}

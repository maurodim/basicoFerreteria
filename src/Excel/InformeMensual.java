/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Excel;

import interfaces.Transaccionable;
import java.awt.Desktop;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
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
 * @author mauro
 */
public class InformeMensual {
  /*
   * generar aca la vista para acceder
   * select *,(select sum(movimientoscaja.monto) from movimientoscaja where movimientoscaja.tipoMovimiento=1)as ventas,(select sum(movimientoscaja.monto) from movimientoscaja where movimientoscaja.tipoMovimiento=12)as gastosCaja,(select sum(movimientoscaja.monto) from movimientoscaja where movimientoscaja.tipoMovimiento=11)as pagoAProveedores from movimientoscaja group by DAY(fecha)
   * 
   * informemenusladecaja
   * SELECT *,(select sum(monto) from movimientoscaja where movimientoscaja.tipoMovimiento=1 and movimientoscaja.idCaja=caja.numero)as ventas,(select sum(monto) from movimientoscaja where movimientoscaja.tipoMovimiento=4 and movimientoscaja.idCaja=caja.numero)as retiroEfectivo,(select sum(monto) from movimientoscaja where movimientoscaja.tipoMovimiento=11 and movimientoscaja.idCaja=caja.numero)as pagoProveedores,(select sum(monto) from movimientoscaja where movimientoscaja.tipoMovimiento=12 and movimientoscaja.idCaja=caja.numero)as gastosDeCaja,(select sum(monto) from movimientoscaja where movimientoscaja.tipoMovimiento=13 and movimientoscaja.idCaja=caja.numero)as cobroCtaCte,(select usuarios.nombre from usuarios where usuarios.numero=caja.numeroUsuario)as nombreU FROM `caja`
   * 
   * 
   * informeMnesual2:
   * SELECT *,sum(monto)as tot FROM movimientoscaja group by DATE(fecha),tipoMovimiento
   * 
   * informemensualventa:
   * 
   * SELECT sum(tot)as totalVentas,fecha,tipoMovimiento from informemensual2 where tipoMovimiento=1 group by DAY(fecha)
   * 
   * informemensualgastoscaja
   * 
   * select sum(tot)as totalGastos,fecha,tipoMovimiento from informemensual2 where tipoMovimiento=12 group by DAY(fecha)
   * 
   * informemensualpagoproveedores
   * 
   * select sum(tot)as totalProv,fecha,tipoMovimiento from informemensual2 where tipoMovimiento=11 group by DAY(fecha)
   * 
   * informemensualcobranzaclientes
   * 
   * select sum(tot)as totalCob,fecha,tipoMovimiento from informemensual2 where tipoMovimiento=13 group by DAY(fecha)
   */
   public void GenerarInformeMensual(String desde,String hasta) throws SQLException, URISyntaxException{
       String uri="http://www.bambusoft.com.ar/pocoPrecio/index.php?desde="+desde+"&hasta="+hasta;
       //String uri2="http://www.bambusoft.com.ar/synm/informeArticulos.php?desde="+desde+"&hasta="+hasta;
       try {

            Desktop.getDesktop().browse(new URI(uri));
         //   Desktop.getDesktop().browse(new URI(uri2));

        } catch (URISyntaxException ex) {

            System.out.println(ex);

        }catch(IOException e){

            System.out.println(e);

        }
      
   }
}

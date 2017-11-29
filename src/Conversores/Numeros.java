/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Conversores;

import interfaceGraficas.Inicio;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mauro
 */
public class Numeros {
    private static String doble;
    private static String flotante;
    private static String fecha;
    
    public static String ConvertirNumero(Double num){
        DecimalFormat formato=new DecimalFormat("####.#");
        doble=formato.format(num);
        return doble;
    }
    public static String ConvetirNumeroCuatroDigitos(Double num){
        DecimalFormat formato=new DecimalFormat("####.####");
        doble=formato.format(num);
        return doble;
    }
    public static String ConvetirNumeroDosDigitos(Double num){
        DecimalFormat formato=new DecimalFormat("####.00");
        doble=formato.format(num);
        return doble;
    }
    public static String ConvertirFecha(Date ff){
        DecimalFormat fr=new DecimalFormat("00");
        Calendar c1=Calendar.getInstance();
	Calendar c2=new GregorianCalendar();
	String dia=Integer.toString(c2.get(Calendar.DAY_OF_MONTH));
	String mes=Integer.toString(c2.get(Calendar.MONTH));
	String ano=Integer.toString(c2.get(Calendar.YEAR));
	
        int da=Integer.parseInt(dia);
        int me=Integer.parseInt(mes);
        me++;
        dia=fr.format(da);
        mes=fr.format(me);
        fecha=ano+"-"+mes+"-"+dia;
        
        return fecha;
    }
    public static String ConvertirFechaFiscal(){
        DecimalFormat fr=new DecimalFormat("00");
        Calendar c1=Calendar.getInstance();
	Calendar c2=new GregorianCalendar();
	String dia=Integer.toString(c2.get(Calendar.DAY_OF_MONTH));
	String mes=Integer.toString(c2.get(Calendar.MONTH));
	String ano=Integer.toString(c2.get(Calendar.YEAR));
	
        int da=Integer.parseInt(dia);
        int me=Integer.parseInt(mes);
        me++;
        dia=fr.format(da);
        mes=fr.format(me);
        fecha=ano+mes+dia;
        
        return fecha;
    }
    public static String ConvertirFechaLeidaDeDateChooser(Calendar dateC){
        DecimalFormat fr=new DecimalFormat("00");
        DecimalFormat formato=new DecimalFormat("####.####");
        //SiderconCapaatos.listaPedidos.clear();
        SimpleDateFormat dia=new SimpleDateFormat("dd/mm/yyyy");
        //Date mes=Calendar.getInstance().getTime();
        //dateChooserCombo1.setDateFormat(dia);
        Calendar fechaNueva=dateC;
        int ano=fechaNueva.get(Calendar.YEAR);
        int mes=fechaNueva.get(Calendar.MONTH);
        mes++;
        int dd=fechaNueva.get(Calendar.DAY_OF_MONTH);
        String fecha1=ano+"-"+mes+"-"+dd;
        return fecha1;
    }
    public static Date ConvertirStringEnDate(String ff){
        SimpleDateFormat fh=new SimpleDateFormat("yyyy-MM-dd");
        Date fechaVal1 = null;    
        try {
            fechaVal1 = fh.parse(ff);
        } catch (ParseException ex) {
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fechaVal1;
    }
    public static Double ConvertirStringADouble(String num){
        if(num.equals("")){
            num="0.00";
        }
        num=num.replace(",",".");
        System.out.println(" rsultado "+num);
        Double dd=Double.parseDouble(num);
        return dd;
    }
    
    public static Integer ConvertirStringAInteger(String num){
        num=num.replace(".0","");
        //System.out.println(" rsultado "+num);
        Integer dd=Integer.parseInt(num);
        return dd;
    }
    public static String ConvertirNumeroExcell(Double num){
        DecimalFormatSymbols simbolo=new DecimalFormatSymbols();
        simbolo.setDecimalSeparator(',');
        DecimalFormat formato=new DecimalFormat("####.#",simbolo);
        doble=formato.format(num);
        return doble;
    }
    public static Double ConvertirEnCoeficiente(Double numero){
        Double coe=numero / 100;
        coe=coe + 1;
        return coe;
    }
    public static String ReConvertirEnCoeficiente(Double numero){
        Double coe=numero - 1;
        coe=coe * 100;
        String valor=String.valueOf(coe);
        return valor;
    }
    public static String LeerArticuloDeCodigo(String codigo){
        String validador=codigo.substring(6,7);
        //validador="1";
        Integer val=Integer.parseInt(validador);
        val=0;
        String precio;
        if(val > 0){
            precio=codigo.substring(1,7);
        }else{
            precio=codigo.substring(1,6);
        }
        Integer codd=Integer.parseInt(precio);
        precio=""+codd;
        return precio;
    }
    public static Double LeerPrecioDeCodigo(String codigo){
        String precio=codigo.substring(6,12);
        Integer decimal=Integer.parseInt(precio.substring(4));
        //decimal=decimal / 100;
        Integer entero=Integer.parseInt(precio.substring(0,4));
        
        Double pp=Double.parseDouble(entero+"."+decimal);
        return pp;
    }
    public static String ConvertirTasaIva(Double iva){
        Double resultado=iva * 1000;
        resultado=resultado - 1000;
        String tasaS=String.valueOf(resultado);
        tasaS=tasaS.substring(0,3);
        return tasaS;
    }
    public static String ConvetirDoubleAString(Double num){
        DecimalFormat formato=new DecimalFormat("####.###");
        doble=formato.format(num);
        return doble;
    }
    public static String ConvertirEnPrecioDeBarra(Double num){
        String valor=String.valueOf(num);
        valor=valor.replace(".","");
        switch (valor.length()){
            case 2:
                valor=valor +"00";
                break;
            case 3:
                valor=valor +"0";
                break;
        }
        String valC="00000";
        int cantt=valor.length();
        int pos=5 - cantt;
        valC=valC.substring(0,pos)+valor+"0";
        return valC;
    }
    public static String ConvertirEnCodigoDeBarra(String cc){
        int cant=cc.length();
        int cant1=6-cant;
        String vv="200000";
        String vvv=vv.substring(0, cant1);
        vvv=vvv + cc;
        return vvv;
    }
}

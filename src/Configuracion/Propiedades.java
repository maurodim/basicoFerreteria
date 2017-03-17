/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Configuracion;

import Conversores.Numeros;
import ObjetosBackUp.BackUp;
import ObjetosBackUp.Backapear;
import interfaces.Transaccionable;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import objetos.ConeccionInstalacion;
import objetos.ConeccionLocal;

/**
 *
 * @author mauro
 */
public class Propiedades {
    static String SERVER;
    static String BD="bbsgestion";
    static String USUARIO;
    static String CLAVE;
    static String CREADA;
    static String ARCHIVOBK;
    static String NOMBRECOMERCIO;
    static String LOGO;
    static String IMAGEN;
    static String CORREOCIERREDECAJA;
    static String CORREOCC;
    static String CORREOCCC;
    static String VERIF;
    static String VALOR;
    static String ID;

    public static String getVERIF() {
        return VERIF;
    }

    public static String getVALOR() {
        return VALOR;
    }

    public static String getID() {
        return ID;
    }
    
    

    public static String getCORREOCIERREDECAJA() {
        return CORREOCIERREDECAJA;
    }

    public static String getCORREOCC() {
        return CORREOCC;
    }

    public static String getCORREOCCC() {
        return CORREOCCC;
    }
    

    public static String getSERVER() {
        return SERVER;
    }

    public static String getBD() {
        return BD;
    }

    public static String getUSUARIO() {
        return USUARIO;
    }

    public static String getCLAVE() {
        return CLAVE;
    }

    public static String getCREADA() {
        return CREADA;
    }

    public static String getARCHIVOBK() {
        return ARCHIVOBK;
    }

    public static String getNOMBRECOMERCIO() {
        return NOMBRECOMERCIO;
    }

    public static String getLOGO() {
        return LOGO;
    }

    public static String getIMAGEN() {
        return IMAGEN;
    }
    
    
    public static void CargarPropiedades() throws ParseException{
        File archivo = new File ("Configuracion\\bbsGestion.properties");
         if(archivo.exists()){
         FileReader fr = null;
            try {
                fr = new FileReader (archivo);
                BufferedReader br = new BufferedReader(fr);
                // Lectura del fichero
                String linea;
                int renglon=0;
                //Transaccionable tra=new Conecciones();
                while((linea=br.readLine())!=null){
                    
                    
                    renglon++;
                    switch (renglon){
                        case 1:
                            break;
                        case 2:
                            SERVER=linea.substring(7);
                            break;
                        case 3:
                            BD=linea.substring(3);
                            break;
                        case 4:
                            USUARIO=linea.substring(8);
                            break;
                        case 5:
                            CLAVE=linea.substring(6);
                            break;
                        case 6:
                            CREADA=linea.substring(7);
                            break;
                        case 7:
                            ARCHIVOBK=linea.substring(10);
                            break;
                        case 9:
                            NOMBRECOMERCIO=linea.substring(15);
                            break;
                        case 10:
                            LOGO=linea.substring(5);
                            break;
                        case 11:
                            IMAGEN=linea.substring(7);
                            break;
                        case 12:
                            VERIF=linea.substring(6);
                            break;
                        case 13:
                            CORREOCIERREDECAJA=linea.substring(5);
                            break;
                        case 14:
                            VALOR=linea.substring(6);
                            break;
                        case 15:
                            ID=linea.substring(3);
                            break;
                        case 16:
                            CORREOCC=linea.substring(7);
                            break;
                        case 17:
                            CORREOCCC=linea.substring(8);
                            break;
                        default:
                            break;
                            
                    }
                    
                    System.out.println(renglon+" // "+SERVER+" // "+BD+" // "+USUARIO+" // "+CLAVE+" // "+CREADA+" // "+ARCHIVOBK+" // "+NOMBRECOMERCIO);
                    // if(tra.guardarRegistro(linea));
      }
                Date fecha=Numeros.ConvertirStringEnDate(VERIF);
                DecimalFormat fr1=new DecimalFormat("00");
                Calendar c1=Calendar.getInstance();
	Calendar c2=new GregorianCalendar();
	String dia=Integer.toString(c2.get(Calendar.DAY_OF_MONTH));
	String mes=Integer.toString(c2.get(Calendar.MONTH));
	String ano=Integer.toString(c2.get(Calendar.YEAR));
	
        int da=Integer.parseInt(dia);
        int me=Integer.parseInt(mes);
        me++;
        dia=fr1.format(da);
        mes=fr1.format(me);
        String fechaDia=ano+"-"+mes+"-"+dia;
	//System.err.println(fechaDia);
        //fecha="23/12/2011";
        String fh=ano+"-"+mes+"-"+dia;
        SimpleDateFormat ff=new SimpleDateFormat("yyyy-MM-dd");
        Date fechaVal = null;    
        
            fechaVal = Numeros.ConvertirStringEnDate(fh);
            //fechaVal = ff.parse(fh);
        
               
        if(fechaVal.after(fecha)){
            System.exit(0);
        }else{
            //System.exit(0);
        }
        if(CREADA.equals("0")){
                        
                        Transaccionable tra=new ConeccionInstalacion();
                        tra.guardarRegistro("create database "+BD);
                        tra.guardarRegistro("use "+BD);
                        tra.guardarRegistro("grant usage on *.* to '"+USUARIO+"'@localhost identified by '"+CLAVE+"'");
                        tra.guardarRegistro("grant all privileges on "+USUARIO+".* to "+USUARIO+"@localhost");
                        //tra.guardarRegistro("quit");
                        Backapear back=new BackUp();
                        back.RecuperarArchivos("Configuracion/"+ARCHIVOBK,BD);
                        
                    }
            
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Propiedades.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Propiedades.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    fr.close();
                    //JOptionPane.showMessageDialog(null,"INICIANDO CONFIGURACION Y CREACION DE LA BASE DE DATOS");
                } catch (IOException ex) {
                    Logger.getLogger(Propiedades.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        }
        //BD="siglox";
        
        
        
    }
    private void CrearBase(){
        Boolean veridi=false;
        Transaccionable tra=new ConeccionInstalacion();
        tra.guardarRegistro("create database "+BD);
        
        //return veridi;
    }
}

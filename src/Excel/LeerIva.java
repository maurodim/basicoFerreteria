/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Excel;

import Conversores.Numeros;
import interfaces.Transaccionable;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import objetos.ConeccionLocal;
import objetos.Conecciones;

/**
 *
 * @author mauro
 */
public class LeerIva {
    private String sql;
    private Transaccionable tra;
    
    public void leerArchivo(String archivo,String periodo1){
        tra=new ConeccionLocal();
        File file=new File(archivo);
       FileReader fr = null;
       BufferedReader br;
       String linea;
       String leido;
       
       String comprobante = null;
       String fecha = null;
       String numeroF = null;
       String idCliente = null;
       String cliente = null;
       String condicion = null;
       String cuit = null;
       String neto = null;
       String iva = null;
       String total = null;
       String espacio;
       Integer aa=0;
       String periodo=periodo1;
       Integer numero=0;
       String comparativo;
        try {
            fr=new FileReader(file);
            br=new BufferedReader(fr);
            while((linea=br.readLine())!=null){
                leido=linea;
                System.err.println("cantidad "+leido.length()+" // "+leido);
                sleep(500);
                
                    comparativo=leido.trim();
                    System.out.println(comparativo.length());
                    if(comparativo.length() < 100){
                    }else{
                    espacio=leido.substring(0,8);
                    comprobante=leido.substring(8,11);
                    fecha=leido.substring(0,8);
                    String ano=fecha.substring(0,4);
                    String mes=fecha.substring(4,6);
                    String dia=fecha.substring(6);
                    fecha=dia+"/"+mes+"/"+ano;
                    numeroF=leido.substring(18,36);
                    idCliente=leido.substring(62,69);
                    //numero=Integer.parseInt(idCliente.trim());
                    cliente=leido.substring(79,108);
                    //condicion=leido.substring(105,109);
                    cuit=leido.substring(59,78);
                    cuit=cuit.substring(8);
                    neto=leido.substring(135,143);
                    iva=leido.substring(153,161);
                    total=leido.substring(109,123);
                    int tot=total.length();
                    tot=tot - 2;
                    String decimal=total.substring(tot);
                    
                    String monto=total.substring(0,tot)+"."+decimal;
                    Double montt=Numeros.ConvertirStringADouble(monto);
                    Double nett=montt / 1.21;
                    Double grav=montt - nett;
                    neto=Numeros.ConvetirNumeroDosDigitos(nett);
                    iva=Numeros.ConvetirNumeroDosDigitos(grav);
                    total=Numeros.ConvetirNumeroDosDigitos(montt);
                    
                    //int cantidad=numeroF.length();
                    int comp=Integer.parseInt(comprobante);
                    switch (comp){
                            case 2:
                                comprobante="TFB.";
                                condicion="C.F.";
                                
                                comparativo=numeroF.substring(10);
                                numeroF="B0002-"+comparativo;
                                sql="insert into ivaventas (comprobante,fecha,numero,idcliente,cliente,condicion,cuit,neto,iva,total,periodo) values ('"+comprobante+"','"+fecha+"','"+numeroF+"',"+numero+",'"+cliente+"','"+condicion+"','"+cuit+"','"+neto+"','"+iva+"','"+total+"','"+periodo+"')";
                                
                                break;
                            case 83:
                                comprobante="TFB.";
                                condicion="C.F.";
                                
                                comparativo=numeroF.substring(10);
                                numeroF="B0002-"+comparativo;
                                sql="insert into ivaventas (comprobante,fecha,numero,idcliente,cliente,condicion,cuit,neto,iva,total,periodo) values ('"+comprobante+"','"+fecha+"','"+numeroF+"',"+numero+",'"+cliente+"','"+condicion+"','"+cuit+"','"+neto+"','"+iva+"','"+total+"','"+periodo+"')";
                                
                                break;
                            case 81:
                                comprobante="TFA.";
                                condicion="R.I.";
                                
                                comparativo=numeroF.substring(10);
                                numeroF="A0002-"+comparativo;
                                sql="insert into ivaventas (comprobante,fecha,numero,idcliente,cliente,condicion,cuit,neto,iva,total,periodo) values ('"+comprobante+"','"+fecha+"','"+numeroF+"',"+numero+",'"+cliente+"','"+condicion+"','"+cuit+"','"+neto+"','"+iva+"','"+total+"','"+periodo+"')";
                                
                                break;
                            case 82:
                                comprobante="TFB.";
                                condicion="C.F.";
                                
                                comparativo=numeroF.substring(10);
                                numeroF="B0002-"+comparativo;
                                sql="insert into ivaventas (comprobante,fecha,numero,idcliente,cliente,condicion,cuit,neto,iva,total,periodo) values ('"+comprobante+"','"+fecha+"','"+numeroF+"',"+numero+",'"+cliente+"','"+condicion+"','"+cuit+"','"+neto+"','"+iva+"','"+total+"','"+periodo+"')";
                                
                                break;
                            case 112:
                                comprobante="NCA.";
                                condicion="R.I.";
                                
                                comparativo=numeroF.substring(10);
                                numeroF="A0002-"+comparativo;
                                sql="insert into ivaventas (comprobante,fecha,numero,idcliente,cliente,condicion,cuit,neto,iva,total,periodo) values ('"+comprobante+"','"+fecha+"','"+numeroF+"',"+numero+",'"+cliente+"','"+condicion+"','"+cuit+"','"+neto+"','"+iva+"','"+total+"','"+periodo+"')";
                                
                                break;
                                
                        }
                    
                    //numero=Integer.parseInt(numeroF.trim());
                    
                    //sql="insert into ivaventas (comprobante,fecha,numero,idcliente,cliente,condicion,cuit,neto,iva,total,periodo) values ('"+comprobante+"','"+fecha+"','"+numeroF+"',"+numero+",'"+cliente+"','"+condicion+"','"+cuit+"','"+neto+"','"+iva+"','"+total+"','"+periodo+"')";
                    tra.guardarRegistro(sql);
                    System.out.println(sql);
                    
                }
                aa++;
                //System.out.println(aa);
                
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LeerIva.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LeerIva.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(LeerIva.class.getName()).log(Level.SEVERE, null, ex);
        }
             
    }
    
}

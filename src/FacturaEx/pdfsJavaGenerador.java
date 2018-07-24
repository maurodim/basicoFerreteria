/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FacturaEx;


import Clientes.Objetos.ClientesTango;
import Configuracion.Propiedades;
import Conversores.Numeros;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.Barcode;
import com.lowagie.text.pdf.Barcode128;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author mauro di
 */
public class pdfsJavaGenerador {
    private FacturaElectronica doc=new FacturaElectronica();
    private ClientesTango cliente=new ClientesTango();

    public void setCliente(ClientesTango cliente) {
        this.cliente = cliente;
    }
    
    
    public void setDoc(FacturaElectronica doc) {
        this.doc = doc;
    }
    
    
    
    public void run(){
        Document documento=new Document();
        int i=1;
        String clienteF=doc.getAfipPlastCbte().replace(":","_");
        String arch="Facturas Electronicas\\"+clienteF+"_factura.pdf";
        
        
        File fich=new File(arch);
        while(fich.exists()){
            i++;
            arch="Facturas Electronicas\\"+clienteF+i+"_factura.pdf";
            fich=new File(arch);
        }
        FileOutputStream fichero;
        Facturas factura=new Facturas();
        Facturable fac=new Facturas();
        factura=(Facturas)fac.cargarIdFactura(doc.getIdFactura());
        try {
            DetalleFacturas saldo=new DetalleFacturas();
            Facturable cotizable=new DetalleFacturas();
            ArrayList listado=new ArrayList();
            listado=cotizable.cargarDetallefactura(doc.getIdFactura());
            fichero=new FileOutputStream(arch);
            PdfWriter writer=PdfWriter.getInstance(documento, fichero);
            documento.open();
            PdfContentByte cb=writer.getDirectContent();
            //LineSeparator linea=new LineSeparator();
            Rectangle recta=new Rectangle(20,800,580,830);
            /*
            if(Propiedades.getLOGO() != ""){
                Image imagen= Image.getInstance(Propiedades.getLOGO());
                imagen.scaleAbsolute(190, 110);
                documento.add(imagen);
            }
            */
            BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA_BOLD,BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
            //cb.setFontAndSize(bf,16);
            for(int aa=0;aa < 3;aa++){
                if(aa > 0){
                    documento.newPage();
                }
            cb.setFontAndSize(bf,12);
            cb.beginText();
            //linea.setLineWidth((float) 0.1);
            //linea.drawLine(cb,20,570,830);
            //linea.draw(cb, 20,530,20, 30, 530);
            /*
            SE DIBUJA TODO EL FORMULARIO Y LUEGO SE COMPLETA
           
            */
            recta.setBorder(Rectangle.BOX);
            recta.setBorderWidth((float) 0.1);
            
            cb.rectangle(recta);
            
            recta=new Rectangle(20,700,580,800);
            recta.setBorder(Rectangle.BOX);
            recta.setBorderWidth((float) 0.1);
            cb.rectangle(recta);
            recta=new Rectangle(270,760,330,800);
            recta.setBorder(Rectangle.BOX);
            recta.setBorderWidth((float) 0.1);
            cb.rectangle(recta);
            //linea.drawLine(cb,300,45,700);
            recta=new Rectangle(20,680,580,697);
            recta.setBorder(Rectangle.BOX);
            recta.setBorderWidth((float) 0.1);
            cb.rectangle(recta);
            recta=new Rectangle(20,625,580,677);
            recta.setBorder(Rectangle.BOX);
            recta.setBorderWidth((float) 0.1);
            cb.rectangle(recta);
            recta=new Rectangle(20,70,580,190);
            recta.setBorder(Rectangle.BOX);
            recta.setBorderWidth((float) 0.1);
            cb.rectangle(recta);
            
            cb.setTextMatrix(270,810);
            if(aa==0)cb.showText("ORIGINAL");
            if(aa==1)cb.showText("DUPLICADO");
            if(aa==2)cb.showText("TRIPLICADO");
            cb.setFontAndSize(bf,21);
            cb.setTextMatrix(25,770);
            cb.showText(Propiedades.getNOMBRECOMERCIO());
            //cb.setTextMatrix(90,750);
            //cb.showText("ANTONIO");
            //cb.showText("eR&Re");
            //cb.add(imagen);
            cb.setFontAndSize(bf,9);
            cb.setTextMatrix(25,740);
            cb.showText("Razón Social: "+Propiedades.getRAZONSOCIAL());
            cb.setTextMatrix(25, 730);
            cb.showText("Domicilio Comercial: "+Propiedades.getDIRECCION());
            //cb.showText("PAPELES");
            //bf = BaseFont.createFont(BaseFont.HELVETICA,BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
            //cb.setFontAndSize(bf,10);
            cb.setTextMatrix(25,720);
            cb.showText("Telefono: "+Propiedades.getTELEFONO());
            cb.setTextMatrix(25,710);
            cb.showText("Condición frente la IVA: Responsable Inscripto");
            
            //cb.showText("de Rivadeneira Enrique y Rivadeneira Jorge S.H.");
            //bf = BaseFont.createFont(BaseFont.HELVETICA,BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
            cb.setFontAndSize(bf,22);
            cb.setTextMatrix(360,770);
            
            Integer comF=Integer.parseInt(doc.getTipoComprobante());
            
            String len=doc.getAfipPlastCbte();
        int cantiL=len.length();
        String cero="0";
        int reemplazo= 8 - cantiL;
        int finall=reemplazo + 1;
        reemplazo=reemplazo -1;
        String numero = "0";
        for(int a=1;a < finall;a++){
            numero+=cero;
            if(a == reemplazo){
                a=finall;
                numero+=len;
            }
            
        }
            
            switch (comF){
                case 1:
                    cb.showText("FACTURA");
                    cb.setTextMatrix(290, 775);
                    cb.setFontAndSize(bf,26);
                    cb.showText("A");
                    cb.setFontAndSize(bf,8);
                    cb.setTextMatrix(285,765);
                    cb.showText("COD. 0"+comF);
                    
                    break;
                case 2:
                    cb.showText("NTA DE DEBITO");
                    cb.setTextMatrix(290, 775);
                    cb.setFontAndSize(bf,26);
                    cb.showText("A");
                    cb.setFontAndSize(bf,8);
                    cb.setTextMatrix(285,765);
                    cb.showText("COD. 0"+comF);
                    break;
                case 3:
                    cb.showText("NTA DE CREDITO");
                    cb.setTextMatrix(290, 775);
                    cb.setFontAndSize(bf,26);
                    cb.showText("A");
                    cb.setFontAndSize(bf,8);
                    cb.setTextMatrix(285,765);
                    cb.showText("COD. 0"+comF);
                    break;
                case 6:
                    cb.showText("FACTURA");
                    cb.setTextMatrix(290, 775);
                    cb.setFontAndSize(bf,26);
                    cb.showText("B");
                    cb.setFontAndSize(bf,8);
                    cb.setTextMatrix(285,765);
                    cb.showText("COD. 0"+comF);
                    break;
                case 7:
                    cb.showText("NTA DE DEBITO");
                    cb.setTextMatrix(290, 775);
                    cb.setFontAndSize(bf,26);
                    cb.showText("B");
                    cb.setFontAndSize(bf,8);
                    cb.setTextMatrix(285,765);
                    cb.showText("COD. 0"+comF);
                    break;
                case 8:
                    cb.showText("NTA DE CREDITO");
                    cb.setTextMatrix(290, 775);
                    cb.setFontAndSize(bf,26);
                    cb.showText("B");
                    cb.setFontAndSize(bf,8);
                    cb.setTextMatrix(285,765);
                    cb.showText("COD. 0"+comF);
                    break;
                case 11:
                    cb.showText("FACTURA C");
                    cb.setTextMatrix(290, 775);
                    cb.setFontAndSize(bf,26);
                    cb.showText("C");
                    cb.setFontAndSize(bf,8);
                    cb.setTextMatrix(285,765);
                    cb.showText("COD. 0"+comF);
                    break;
                case 12:
                    cb.showText("NOTA DE DEBITO C");
                    cb.setTextMatrix(290, 775);
                    cb.setFontAndSize(bf,26);
                    cb.showText("C");
                    cb.setFontAndSize(bf,8);
                    cb.setTextMatrix(285,765);
                    cb.showText("COD. 0"+comF);
                    break;
                case 13:
                    cb.showText("NOTA DE CREDITO C");
                    cb.setTextMatrix(290, 775);
                    cb.setFontAndSize(bf,26);
                    cb.showText("C");
                    cb.setFontAndSize(bf,8);
                    cb.setTextMatrix(285,765);
                    cb.showText("COD. 0"+comF);
                    break;
            }
            bf = BaseFont.createFont(BaseFont.HELVETICA_BOLD,BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
            cb.setFontAndSize(bf,9);
            cb.setTextMatrix(360,755);
            cb.showText("Punto de Venta: 000"+Propiedades.getPTO()+" Comp. Nro:"+numero);
            //bf = BaseFont.createFont(BaseFont.COURIER,BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
            cb.setFontAndSize(bf,9);
            cb.setTextMatrix(360,745);
            cb.showText("Fecha de emisión: "+doc.getFechaCae());
            cb.setTextMatrix(360,730);
            cb.showText("CUIT: "+Propiedades.getCUIT());
            cb.setTextMatrix(360,720);
            cb.showText("Ing. Brutos: "+Propiedades.getINGBRUTOS());
            cb.setTextMatrix(360,710);
            cb.showText("Inicio Activ.: "+Propiedades.getINICIOACT());
            //cb.setTextMatrix(380,740);
            //cb.showText("Fecha "+doc.getFechaCae());
            bf = BaseFont.createFont(BaseFont.HELVETICA,BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
            cb.setFontAndSize(bf,9);
            cb.setTextMatrix(25,685);
            cb.showText("Período Facturado Desde: "+doc.getFechaCae().substring(0,10));
            cb.setTextMatrix(240,685);
            cb.showText("Hasta: "+doc.getFechaCae().substring(0,10));
            cb.setTextMatrix(360,685);
            cb.showText("Fecha de Vto. para el pago: "+doc.getFechaCae().substring(0,10));
            
            //fin segundo recuadro
            
            cb.setTextMatrix(40,660);
            cb.showText("Razon Social :"+cliente.getRazonSocial());
            cb.setTextMatrix(380,660);
            String condV="";
            if(doc.getEstado()==1){
                condV="CONTADO";
            }else{
                condV="CTA CTE";
            }
            cb.showText("Cond. Vta: "+condV);
            cb.setTextMatrix(380,650);
            Integer condIvv=Integer.parseInt(cliente.getCondicionIva());
            switch(condIvv){
                case 1:
                    cb.showText("Cond. Iva: Consumidor Final");
                    break;
                case 2:
                    cb.showText("Cond. Iva: Responsable Inscripto");
                    break;
                case 3:
                    cb.showText("Cond. Iva: Exento");
                    break;
                default:
                    cb.showText("Cond. Iva: Consumidor Final");
                    break;
            }
            //cb.showText("Cond. Iva: "+cliente.getCondicionIva());
            cb.setTextMatrix(40,650);
            cb.showText("Direccion: "+cliente.getDireccion());
            cb.setTextMatrix(40,640);
            
            //localidad=per.buscarPorNumero(cliente.getLocalidad())
            //cb.showText("Localidad :("+cliente.getCodigoPostal()+") - "+cliente.getLocalidad());
            //cb.setTextMatrix(40,650);
            //cb.showText("Telefono: "+cliente.getTelefono());
            cb.setTextMatrix(380,640);
            Integer tipo=Integer.parseInt(String.valueOf(doc.getCustomerTypeDoc()));
            switch (tipo){
                case 80:
                    cb.showText("Cuit: "+doc.getCustomerId());
                    break;
                case 86:
                    cb.showText("Cuil: "+doc.getCustomerId());
                    break;
                case 96:
                    cb.showText("Dni: "+doc.getCustomerId());
                    break;
            }
            
            
            int renglon=610;
            String vencimiento;
            String vencimiento1;
            String descripcion;
            String monto;
            String recargo;
            String total;
            String totalFinal;
            Double tot=0.00;
            Double totalD=0.00;
            Double grav=0.00;
            Double totalS=0.00;
            //aca empieza la iteracion
            
            //encabezados
            bf = BaseFont.createFont(BaseFont.HELVETICA_BOLD,BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
            cb.setFontAndSize(bf,9);
            cb.setTextMatrix(40,renglon);
                cb.showText("COD");
                cb.setTextMatrix(70,renglon);
                cb.showText("DESCRIPCION");
                cb.setTextMatrix(330,renglon);
                cb.showText("CANT.");
                cb.setTextMatrix(370,renglon);
                cb.showText("P. UNIT.");
                cb.setTextMatrix(450,renglon);
                cb.showText("DESC.");
                cb.setTextMatrix(500,renglon);
                cb.showText("P. TOTAL.");
                renglon=renglon - 20;
            
            //fin encabezados
            bf = BaseFont.createFont(BaseFont.HELVETICA,BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
            cb.setFontAndSize(bf,8);
            Iterator itl=listado.listIterator();
            String ano=doc.getCaeVto().substring(0,4);
            String mm=doc.getCaeVto().substring(4,6);
            String dd=doc.getCaeVto().substring(6);
            vencimiento="C.A.E. Nº: "+doc.getCae();
            vencimiento1="Fecha de Vto. C.A.E.: "+dd+"/"+mm+"/"+ano;
            String descripcionArt=null;
            int items=0;
            while(itl.hasNext()){
                saldo=(DetalleFacturas)itl.next();
                //vencimiento=saldo.getVencimientoString();
                
                descripcion="Numero Resumen de cta ";
                
                monto=Numeros.ConvertirNumero(saldo.getPrecioUnitario());
                recargo="";
                total="nada";
                //recargo=String.valueOf(saldo.getRecargo());
                //tot=tot + saldo.getTotal();
                //total=String.valueOf(saldo.getTotal());
                
                if(saldo.getIdArticulo()==0)items=1;
                if(items==1){
                    cb.setTextMatrix(70,renglon);

                    if(saldo.getDescripcionArticulo().length() > 40){
                        descripcionArt=saldo.getDescripcionArticulo().substring(0, 40);
                     }else{
                         descripcionArt=saldo.getDescripcionArticulo();
                     }
                    cb.showText(descripcionArt);
                     renglon=renglon - 10;
                }else{
                    cb.setTextMatrix(40,renglon);
                
                    cb.showText(String.valueOf(saldo.getIdArticulo()));
                    cb.setTextMatrix(70,renglon);
                    if(saldo.getDescripcionArticulo() != null){
                    if(saldo.getDescripcionArticulo().length() > 40){
                        descripcionArt=saldo.getDescripcionArticulo().substring(0, 40);
                     }else{
                         descripcionArt=saldo.getDescripcionArticulo();
                     }
                    }else{
                        descripcionArt="";
                    }
                    cb.showText(descripcionArt);
                    cb.setTextMatrix(330,renglon);
                    cb.showText(String.valueOf(saldo.getCantidad()));
                    tot=saldo.getCantidad() * saldo.getPrecioUnitario();
                    //tot=tot * 1.21;
                    if(comF==1 || comF==2 || comF==3){

                    cb.setTextMatrix(370,renglon);
                    cb.showText(Numeros.ConvertirNumero(saldo.getPrecioUnitario()));
                    cb.setTextMatrix(450,renglon);
                    if(saldo.getDescuento()!=null){
                        cb.showText(String.valueOf(saldo.getDescuento()));
                        totalD=totalD + saldo.getDescuento();
                    }else{
                        cb.showText("0.00");    
                    }
                    cb.setTextMatrix(500,renglon);
                    cb.showText(Numeros.ConvertirNumero(tot));
                    grav=grav + tot;
                    totalS=totalS + (tot);
                    //cb.setTextMatrix(440,renglon);

                    //cb.showText(Numeros.ConvertirNumero(tot));
                    renglon=renglon - 10;
                    System.out.println("renglon "+renglon);


                    }else{
                        //tot=tot * 1.21;
                        cb.setTextMatrix(370,renglon);
                        cb.showText(Numeros.ConvertirNumero(saldo.getPrecioUnitario()));
                        cb.setTextMatrix(450,renglon);
                        if(saldo.getDescuento()!=null){
                            cb.showText(String.valueOf(saldo.getDescuento()));
                            totalD=totalD + saldo.getDescuento();
                        }else{
                            cb.showText("0.00");    
                        }
                        cb.setTextMatrix(500,renglon);
                        cb.showText(Numeros.ConvertirNumero(tot));
                        grav=grav + tot;
                        totalS=totalS + (tot);
                        //cb.setTextMatrix(440,renglon);

                        //cb.showText(Numeros.ConvertirNumero(tot));
                        renglon=renglon - 10;
                        System.out.println("renglon "+renglon);
                    }
            }
                items=0;
            }
            
            factura.setTotal(totalS);
            
            String totalF="";
            if(factura.getTotal()!=null){
                totalF=Numeros.ConvertirNumero(factura.getTotal());
            }else{
                totalF="0.00";
            }
            //String letras=NumberToLetterConverter.convertNumberToLetter(factura.getTotal());
            bf = BaseFont.createFont(BaseFont.HELVETICA_BOLD,BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
            cb.setFontAndSize(bf,8);
            //cb.setTextMatrix(40,renglon);
            //cb.showText("SON PESOS: "+letras);
            //renglon=renglon -20;
            
            /*
            renglon=180;
            cb.setTextMatrix(380,renglon);
            cb.showText("Importe Neto Grav.");
            cb.setTextMatrix(480, renglon);
            cb.showText(letras);
            
            
            cb.setTextMatrix(180,renglon);
            cb.showText("T. DESC");
            cb.setTextMatrix(250,renglon);
            cb.showText("MONTO GRAVADO");
            cb.setTextMatrix(380,renglon);
            cb.showText("IVA 21 %");
            cb.setTextMatrix(480,renglon);
            cb.showText("TOTAL");
            renglon=renglon - 10;
            bf = BaseFont.createFont(BaseFont.HELVETICA,BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
            cb.setFontAndSize(bf,8);
            */
            if(comF==1 || comF==2 || comF==3){
                
                renglon=180;
            cb.setTextMatrix(380,renglon);
            cb.showText("Importe Neto Grav.");
            cb.setTextMatrix(480, renglon);
            //cb.showText(letras);
            
                
               // cb.setTextMatrix(40,renglon);
            Double sub=factura.getTotal() / 1.21;
            Double iva=factura.getTotal() - sub;
            cb.showText("$ "+Numeros.ConvertirNumero(sub));
            renglon=renglon - 10;
            cb.setTextMatrix(380,renglon);
            cb.showText("Iva 27%");
            cb.setTextMatrix(480,renglon);
            cb.showText("$ 0.00");
            renglon=renglon - 10;
            cb.setTextMatrix(380,renglon);
            cb.showText("Iva 21%");
            cb.setTextMatrix(480,renglon);
            cb.showText("$ "+Numeros.ConvertirNumero(iva));
            renglon=renglon - 10;
            cb.setTextMatrix(380,renglon);
            cb.showText("Iva 10,5%");
            cb.setTextMatrix(480,renglon);
            cb.showText("$ 0.00");
            renglon=renglon - 10;
            cb.setTextMatrix(380,renglon);
            cb.showText("Iva 5%");
            cb.setTextMatrix(480,renglon);
            cb.showText("$ 0.00");
            renglon=renglon - 10;
            cb.setTextMatrix(380,renglon);
            cb.showText("Iva 2,5%");
            cb.setTextMatrix(480,renglon);
            cb.showText("$ 0.00");
            renglon=renglon - 10;
            cb.setTextMatrix(380,renglon);
            cb.showText("Iva 0%");
            cb.setTextMatrix(480,renglon);
            cb.showText("$ 0.00");
            renglon=renglon - 10;
            cb.setTextMatrix(380,renglon);
            cb.showText("Importe Total");
            cb.setTextMatrix(480,renglon);
            cb.showText("$ "+totalF);
            
            
            
            }else{
                
                renglon=120;
            cb.setTextMatrix(380,renglon);
            cb.showText("Subtotal.");
            cb.setTextMatrix(480, renglon);
            cb.showText("$ "+totalF);
            
                
            /*    cb.setTextMatrix(40,renglon);
            Double sub=factura.getTotal() / 1.21;
            Double iva=factura.getTotal() - sub;
            cb.showText(totalF);
            */
            renglon=renglon - 10;
            
            cb.setTextMatrix(380,renglon);
            cb.showText("Importe Otros Tributos");
            cb.setTextMatrix(480,renglon);
            cb.showText("$ 0.00");
            renglon=renglon - 10;
            cb.setTextMatrix(380,renglon);
            cb.showText("Importe Total");
            cb.setTextMatrix(480,renglon);
            cb.showText("$ "+totalF);
            }
            
            /*
            totalFinal=doc.getImporteTotal();
            renglon=renglon - 20;
            cb.setTextMatrix(380,renglon);
            cb.showText("TOTAL NETO"+totalFinal);
            */
            
            //pie de documento
            bf = BaseFont.createFont(BaseFont.HELVETICA_BOLD,BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
            cb.setFontAndSize(bf,9);
            renglon=renglon - 10;
            cb.setTextMatrix(380,renglon);
            cb.showText(vencimiento);
            renglon=renglon - 10;
            cb.setTextMatrix(380,renglon);
            cb.showText(vencimiento1);
            cb.setTextMatrix(20,renglon);
            renglon=renglon - 50;
             Image imagen= Image.getInstance("imagenes/afip.JPG");
                //imagen.scaleAbsolute(84, 410);
                imagen.setAbsolutePosition(20,renglon);
                documento.add(imagen);
                Barcode128 codeEAN=new Barcode128();
                codeEAN.setCodeType(Barcode.CODE128);
                String codigoB=Propiedades.getCUIT()+comF+doc.getCae()+doc.getCaeVto()+"3";
                codeEAN.setCode(codigoB);
                //codeEAN.setSize(5);
                Image img=codeEAN.createImageWithBarcode(cb, Color.BLACK, Color.black);
                
                img.setAbsolutePosition(360,renglon);
                documento.add(img);
                /*
                BarcodeQRCode codQr=new BarcodeQRCode(codigoB,1,1,null);
                Image imQ=codQr.getImage();
                imQ.setAbsolutePosition(20, 60);
                imQ.scalePercent(200);
                documento.add((Element) imQ);
                */
                
            cb.endText();
            
            }
            
            documento.close();
            
            File f=new File(arch);
            if(f.exists()){
            
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+arch);
            }
            int confirmacion=0;
            /*
            if(doc.getArchivo().isEmpty()){
                
            }else{
                confirmacion=JOptionPane.showConfirmDialog(null, "DESEA NOTIFICAR POR MAIL?");
            if(confirmacion==0){
                //JOptionPane.showMessageDialog(null,"acepto");
                
            }
            }
                    */
            System.out.println("eligio "+confirmacion);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(pdfsJavaGenerador.class.getName()).log(Level.SEVERE, null, ex);
            
        } catch (DocumentException ex) {
            Logger.getLogger(pdfsJavaGenerador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(pdfsJavaGenerador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}

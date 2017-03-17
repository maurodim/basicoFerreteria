/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Excel;

import Conversores.Numeros;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPage;
import com.itextpdf.text.pdf.PdfWriter;
import interfaces.Transaccionable;
import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import objetos.ConeccionLocal;

/**
 *
 * @author mauro di
 */
public class pdfsJavaGenerador {
    private String periodo;
    private String encabezado;
    
    private String comprobante;
    private String fecha;
    private String numeroFactura;
    private String razonSocial;
    private String condicionIva;
    private String cuit;
    private String neto;
    private String iva;
    private String impInterno;
    private String percepcion;
    private String total;

    public String getComprobante() {
        return comprobante;
    }

    public void setComprobante(String comprobante) {
        this.comprobante = comprobante;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getCondicionIva() {
        return condicionIva;
    }

    public void setCondicionIva(String condicionIva) {
        this.condicionIva = condicionIva;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getNeto() {
        return neto;
    }

    public void setNeto(String neto) {
        this.neto = neto;
    }

    public String getIva() {
        return iva;
    }

    public void setIva(String iva) {
        this.iva = iva;
    }

    public String getImpInterno() {
        return impInterno;
    }

    public void setImpInterno(String impInterno) {
        this.impInterno = impInterno;
    }

    public String getPercepcion() {
        return percepcion;
    }

    public void setPercepcion(String percepcion) {
        this.percepcion = percepcion;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
    
    
    
    
    public void run(){
        Document documento=new Document();
        documento.setPageSize(PageSize.A4.rotate());
        int i=1;
        String arch=periodo+"_Iva Ventas.pdf";
        
        
        File fich=new File(arch);
        
        FileOutputStream fichero;
        try {
            pdfsJavaGenerador pdf;
            
            ArrayList listado=new ArrayList();
            String sql="select * from ivaventas where periodo='"+periodo+"' order by numero";
            Transaccionable tra=new ConeccionLocal();
            ResultSet rs=tra.leerConjuntoDeRegistros(sql);
            while(rs.next()){
                pdf=new pdfsJavaGenerador();
                pdf.setComprobante(rs.getString("comprobante"));
                pdf.setFecha(rs.getString("fecha"));
                pdf.setNumeroFactura(rs.getString("numero"));
                pdf.setRazonSocial(rs.getString("cliente"));
                pdf.setCondicionIva(rs.getString("condicion"));
                pdf.setCuit(rs.getString("cuit"));
                pdf.setNeto(rs.getString("neto"));
                pdf.setIva(rs.getString("iva"));
                pdf.setTotal(rs.getString("total"));
                listado.add(pdf);
            }
            rs.close();
            Integer totalItems=listado.size();
            Integer totalPaginas=totalItems /46;
            Integer contadorPaginas=1;
            totalPaginas=totalPaginas + 1;
            System.out.println(" items "+totalItems+" paginas "+totalPaginas);
            fichero=new FileOutputStream(arch);
            PdfWriter writer=PdfWriter.getInstance(documento, fichero);
            
            documento.open();
            //writer.addPageDictEntry(PdfName.ROTATE,PdfPage.SEASCAPE);
            writer.addPageDictEntry(PdfName.ROTATE,null);
            PdfContentByte cb=writer.getDirectContent();
            
            BaseFont bf = BaseFont.createFont(BaseFont.COURIER_BOLD,BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
            cb.setFontAndSize(bf,12);
            cb.beginText();
            //cb.setTextMatrix(250,820);
            //cb.showText("Subdiario de IVA Ventas");
            cb.setFontAndSize(bf,10);
            
            
            cb.setTextMatrix(20,480);
            cb.showText("Periodo "+periodo);
            //cb.setTextMatrix(750,550);
            //cb.showText("Hoja "+contadorPaginas+" de "+totalPaginas);
            
            int renglon=460;
            String vencimiento;
            String descripcion;
            String monto;
            String recargo;
            String total;
            String totalFinal;
            Double tot=0.00;
            String razonSocial;
            int itt=0;
            //aca empieza la iteracion
            
            //encabezados
            bf = BaseFont.createFont(BaseFont.COURIER_BOLD,BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
            cb.setFontAndSize(bf,8);
            cb.setTextMatrix(20,renglon);
                cb.showText("Cbte");
                cb.setTextMatrix(50,renglon);
                cb.showText("Fecha");
                cb.setTextMatrix(90,renglon);
                cb.showText("Nro Factura");
                cb.setTextMatrix(150,renglon);
                cb.showText("Cliente");
                cb.setTextMatrix(500,renglon);
                //tot=saldo.getCantidad() * saldo.getPrecioUnitario();
                cb.showText("Cond");
                cb.setTextMatrix(540,renglon);
                cb.showText("C.U.I.T.");
                cb.setTextMatrix(610,renglon);
                cb.showText("Neto");
                cb.setTextMatrix(660,renglon);
                cb.showText("I.V.A.");
                cb.setTextMatrix(700,renglon);
                cb.showText("Imp. Int.");
                cb.setTextMatrix(760,renglon);
                cb.showText("Total");
                renglon=renglon - 20;
            
            //fin encabezados
            bf = BaseFont.createFont(BaseFont.COURIER,BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
            cb.setFontAndSize(bf,6);
            Iterator itl=listado.listIterator();
            //vencimiento="Esta cotización tendrá vigencia 30 días ";
            Double montoCIva=0.00;
            Double descuento=0.00;
            Double descUnitario=0.00;
            Double descTotal=0.00;
            Double montoCIvaTotal=0.00;
            Double descuentoTotal=0.00;
            Double descUnitarioTotal=0.00;
            Double descTotalTotal=0.00;
            String descripcionArt=null;
            while(itl.hasNext()){
                pdf=(pdfsJavaGenerador) itl.next();
                
                //vencimiento=saldo.getVencimientoString();
                /*
                descripcion="Numero Resumen de cta ";
                montoCIva=saldo.getPrecioUnitario() * 1.21;
                monto=Numeros.ConvertirNumero(montoCIva);
                recargo="10%";
                total="nada";
                */

                //recargo=String.valueOf(saldo.getRecargo());
                //tot=tot + saldo.getTotal();
                //total=String.valueOf(saldo.getTotal());
                
                
                
                cb.setTextMatrix(20,renglon);
                cb.showText(pdf.getComprobante());
                cb.setTextMatrix(45,renglon);
                cb.showText(pdf.getFecha());
                cb.setTextMatrix(90,renglon);
                cb.showText(pdf.getNumeroFactura());
                cb.setTextMatrix(150,renglon);
                
                    razonSocial=pdf.getRazonSocial();
                
                cb.showText(razonSocial);
                cb.setTextMatrix(500,renglon);
                
                cb.showText(pdf.getCondicionIva());
                cb.setTextMatrix(540,renglon);
                cb.showText(pdf.getCuit());
                cb.setTextMatrix(600,renglon);
                
                if(pdf.getComprobante().equals("N.C.")){
                    String neto=pdf.getNeto();
                    String iva=pdf.getIva();
                    String totalP=pdf.getTotal();
                    pdf.setNeto("-"+neto.trim());
                    pdf.setIva("-"+iva.trim());
                    pdf.setTotal("-"+totalP.trim());
                }
                    montoCIva=montoCIva + Numeros.ConvertirStringADouble(pdf.getNeto());
                    descuento=descuento + Numeros.ConvertirStringADouble(pdf.getIva());
                    descTotal=descTotal + Numeros.ConvertirStringADouble(pdf.getTotal());
                
                cb.showText(pdf.getNeto());
                cb.setTextMatrix(650,renglon);
                
                cb.showText(pdf.getIva());
                cb.setTextMatrix(710,renglon);
                cb.showText("0.00");
                cb.setTextMatrix(750,renglon);
                
                cb.showText(pdf.getTotal());
                
                //descuento=descuento+saldo.getDescuento();
                renglon=renglon - 10;
                if(renglon < 30){
                    bf = BaseFont.createFont(BaseFont.COURIER_BOLD,BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
                    cb.setFontAndSize(bf,7);
                    cb.setTextMatrix(550,renglon);
                    cb.showText("Subtotal:");
                    cb.setTextMatrix(600,renglon);
                    cb.showText(Numeros.ConvetirNumeroDosDigitos(montoCIva));
                    cb.setTextMatrix(650,renglon);
                    cb.showText(Numeros.ConvetirNumeroDosDigitos(descuento));
                    cb.setTextMatrix(710,renglon);
                    cb.showText("0.00");
                    cb.setTextMatrix(750,renglon);
                    cb.showText(Numeros.ConvetirNumeroDosDigitos(descTotal));
                    montoCIvaTotal=montoCIvaTotal + montoCIva;
                    descuentoTotal=descuentoTotal + descuento;
                    
                    descTotalTotal=descTotalTotal + descTotal;
                    descuento=0.00;
                    descTotal=0.00;
                    montoCIva=0.00;
                    renglon=460;
                    cb.endText();
                    documento.newPage();
                    documento.setPageSize(PageSize.A4.rotate());
                    cb.beginText();
                        
            contadorPaginas++;        
            cb.setFontAndSize(bf,12);
            //cb.setTextMatrix(250,820);
            //cb.showText("Subdiario de IVA Ventas");
            cb.setFontAndSize(bf,10);
            cb.setTextMatrix(20,480);
            cb.showText("Periodo "+periodo);
            //cb.setTextMatrix(750,550);
            //cb.showText("Hoja "+contadorPaginas+" de "+totalPaginas);
            
            
            //aca empieza la iteracion
            
            //encabezados
            bf = BaseFont.createFont(BaseFont.COURIER_BOLD,BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
            cb.setFontAndSize(bf,8);
            cb.setTextMatrix(20,renglon);
                cb.showText("Cbte");
                cb.setTextMatrix(50,renglon);
                cb.showText("Fecha");
                cb.setTextMatrix(90,renglon);
                cb.showText("Nro Factura");
                cb.setTextMatrix(150,renglon);
                cb.showText("Cliente");
                cb.setTextMatrix(500,renglon);
                //tot=saldo.getCantidad() * saldo.getPrecioUnitario();
                cb.showText("Cond");
                cb.setTextMatrix(540,renglon);
                cb.showText("C.U.I.T.");
                cb.setTextMatrix(610,renglon);
                cb.showText("Neto");
                cb.setTextMatrix(660,renglon);
                cb.showText("I.V.A.");
                cb.setTextMatrix(700,renglon);
                cb.showText("Imp. Int.");
                cb.setTextMatrix(760,renglon);
                cb.showText("Total");
                renglon=renglon - 20;
            
            //fin encabezados
            bf = BaseFont.createFont(BaseFont.COURIER,BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
            cb.setFontAndSize(bf,6);
                }
            }
            
            
            
            bf = BaseFont.createFont(BaseFont.COURIER_BOLD,BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
                    cb.setFontAndSize(bf,7);
                    cb.setTextMatrix(540,renglon);
                    cb.showText("Subtotal:");
                    cb.setTextMatrix(600,renglon);
                    cb.showText(Numeros.ConvetirNumeroDosDigitos(montoCIva));
                    cb.setTextMatrix(650,renglon);
                    cb.showText(Numeros.ConvetirNumeroDosDigitos(descuento));
                    cb.setTextMatrix(710,renglon);
                    cb.showText("0.00");
                    cb.setTextMatrix(750,renglon);
                    cb.showText(Numeros.ConvetirNumeroDosDigitos(descTotal));
                    renglon=renglon - 10;
                    
                    
                    montoCIvaTotal=montoCIvaTotal + montoCIva;
                    descuentoTotal=descuentoTotal + descuento;
                    
                    descTotalTotal=descTotalTotal + descTotal;
                    cb.setTextMatrix(540,renglon);
                    cb.showText("Total:");
                    cb.setTextMatrix(600,renglon);
                    cb.showText(Numeros.ConvetirNumeroDosDigitos(montoCIvaTotal));
                    cb.setTextMatrix(650,renglon);
                    cb.showText(Numeros.ConvetirNumeroDosDigitos(descuentoTotal));
                    cb.setTextMatrix(710,renglon);
                    cb.showText("0.00");
                    cb.setTextMatrix(750,renglon);
                    cb.showText(Numeros.ConvetirNumeroDosDigitos(descTotalTotal));
                    
                    descuento=0.00;
                    descTotal=0.00;
                    montoCIva=0.00;
            
            cb.endText();
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
        } catch (SQLException ex) {
            Logger.getLogger(pdfsJavaGenerador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    public void encabezado(){
        
    }
}

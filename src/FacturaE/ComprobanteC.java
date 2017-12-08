/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FacturaE;

import Configuracion.Propiedades;
import interfaceGraficas.Inicio;
import interfaces.Transaccionable;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import objetos.Comprobantes;
import objetos.Conecciones;
import org.xml.sax.SAXException;

/**
 *
 * @author mauro
 */
public class ComprobanteC {
    
    
    
    
        public Object leer(Object comp) throws MalformedURLException, IOException, ParserConfigurationException, SAXException{
        Comprobantes compro=new Comprobantes();
        compro=(Comprobantes)comp;
        String key=Propiedades.getKEY();
        
        String charSet="UTF-8";
        String tipo="xml";
        
        String secreta=Propiedades.getSKEY();
        String cuit=compro.getCliente().getNumeroDeCuit().trim();
        Integer tipDocumento=0;
        Integer tipComprobante=0;
        FacturaElectronica fE=new FacturaElectronica();
        String idCliente=compro.getCliente().getNumeroDeCuit();
        fE.setEstado(compro.getPagado());
        if(idCliente.length() == 8 || idCliente.length()==11){
            
        }else{
            idCliente=JOptionPane.showInputDialog(null,"Ingrese numero de CUIT/CUIL o DNI Sin puntos ni guiones ",idCliente);
        }
        idCliente=idCliente.replace("-","");
        idCliente=idCliente.trim();
        Integer cantCuit=idCliente.length();
        switch(cantCuit){
            case 11:
                if(compro.getTipoComprobante()==2)tipDocumento=80;
                if(compro.getTipoComprobante()==1)tipDocumento=86;
                break;
            case 8:
                tipDocumento=96;
                break;
            
        }
        String tipoDocumento=String.valueOf(tipDocumento);
        if(Propiedades.getCONDICION().equals("2")){
            if(compro.getTipoComprobante()==1)tipComprobante=6;
            if(compro.getTipoComprobante()==2)tipComprobante=1;//1
            if(compro.getTipoComprobante()==9)tipComprobante=2;//2
            if(compro.getTipoComprobante()==10)tipComprobante=3;//3
            if(compro.getTipoComprobante()==11)tipComprobante=7;
            if(compro.getTipoComprobante()==12)tipComprobante=8;
        }else{
            if(compro.getTipoComprobante()==1)tipComprobante=11;
            if(compro.getTipoComprobante()==2)tipComprobante=11;//1
            if(compro.getTipoComprobante()==9)tipComprobante=12;//2
            if(compro.getTipoComprobante()==10)tipComprobante=13;//3
            if(compro.getTipoComprobante()==11)tipComprobante=12;
            if(compro.getTipoComprobante()==12)tipComprobante=13;
        }
        String tipoComprobante=String.valueOf(tipComprobante);
        System.out.println(tipComprobante);
        String importeTotal=String.valueOf(compro.getMontoTotal()).replace(".","_");
        String importeNeto=String.valueOf(compro.getMontoBruto());
        String importeEx="0.0";
        String impuestoLiq=String.valueOf(compro.getMontoIva());
        String nombre=compro.getCliente().getRazonSocial();
        nombre=nombre.trim();
        nombre=nombre.replace(" ","_");
        String direccion=compro.getCliente().getDireccion().trim();
        direccion=direccion.replace(" ","_");
        String cadena="http://www.bambusoft.com.ar/FE/servlet.php?vta="+compro.getId()+"&modo=PROD&nombre="+nombre+"&direccion="+direccion+"&tipoDocumento="+tipDocumento+"&idcliente="+idCliente+"&tipocomprobante="+tipoComprobante+"&total="+importeTotal+"&subtotal="+importeTotal;
        System.out.println(cadena+" // "+cadena.length());
        URL url = new URL(cadena);
        DataInputStream dis=new DataInputStream(url.openStream());
        String inputLine;
        String resultado = null;
         while ((inputLine = dis.readLine()) != null) {
                resultado=inputLine;
            }
            dis.close();
            System.out.println(resultado);
            //{"success":"true","error":0,"errorString":"","data":{"CAE":"67255488283051","AFIPLASTCBTE":"8","AFIPLASTID":"1","ID":"59750","PDF":"https:\/\/tufacturaelectronica.net\/api\/v5\/get-pdf?CAE=67255488283051&PUBLIC_KEY=NTk0ODMxMmNlZmU4Mi0xNzA2MTkwNTE2NDQ=&HASH=328add58128d2f7031570f63416c4c88"}}
            if(resultado.substring(26,27).equals("0")){
                fE.setRespuesta("OK");
                int posicion=resultado.indexOf("CAE")+6;
                int hasta=resultado.indexOf("AFIPLASTCBTE") - 3;
                System.out.println("desde "+posicion+" hasta "+hasta);
                String cae=resultado.substring(posicion,hasta);
                fE.setCae(cae);
                posicion=hasta + 18;
                hasta=resultado.indexOf("AFIPLASTID") -3;
                String cbte=resultado.substring(posicion,hasta);
                fE.setAfipPlastCbte(cbte);
                hasta= hasta + 16;
                String acortado=resultado.substring(hasta);
                posicion=0;
                hasta=acortado.indexOf("ID")- 3;
                String lastId=acortado.substring(posicion,hasta);
                fE.setAfipPlastId(lastId);
                posicion=hasta + 8;
                hasta=acortado.indexOf("PDF")-3;
                String id=acortado.substring(posicion,hasta);
                fE.setAfipQty("");
                
                
                
            }else{
                fE.setRespuesta("ERROR");
            }
            
            
        /*    
        URLConnection con = url.openConnection();
        
        Authenticator au = new Authenticator() {
         @Override
         protected PasswordAuthentication
            getPasswordAuthentication() {
            return new PasswordAuthentication
               ("mauro@bambusoft.com.ar", "SUtter001".toCharArray());
         }
      };
      Authenticator.setDefault(au);
        
       PrintStream outStream=new PrintStream(con.getOutputStream());
       outStream.println("clave="+key+"&vta="+compro.getId());
       outStream.close();
       DataInputStream inStream = new DataInputStream(con.getInputStream());
            String inputLine;

            while ((inputLine = inStream.readLine()) != null) {
                System.out.println(inputLine);
            }
            inStream.close();
     
            
      OutputStreamWriter out=new OutputStreamWriter(con.getOutputStream());
      //out.write("PUBLIC_KEY="+key);
      //out.write("SECRET_API="+secreta);
      //String cadena2;
      out.write("clave="+key);
      //out.write("AFIP_WEBSERVICES_MODE="+"HOMO");
      out.write("SALE_CLIENT_ID="+idCliente);
      out.write("vta="+compro.getId());
      System.out.println("vta="+compro.getId());
      out.write("nombre="+compro.getCliente().getRazonSocial());
      //out.write("CUSTOMER_NAME="+compro.getCliente().getRazonSocial());
      out.write("SALE_CONDITION=CONTADO");
      out.write("direccion= ");//+direccionCliente);
      out.write("tipodocumento="+tipDocumento);//96 dni
      out.write("tipocomprobante="+tipComprobante);//FACTURA C
      out.write("total="+compro.getMontoTotal());
      out.write("NET_AMOUNT=0");
      out.write("TAX=0.00");
      out.write("NO_TAX_AMOUNT=0.00");
      out.write("DISCOUNT=0");
      out.write("subtotal="+compro.getMontoTotal());
      out.write("DELIVERY_NOTE=");
      ArrayList listadoA=compro.getListadoDeArticulos();
      
      out.write("cant="+listadoA.size());//CANTIDAD DE PRODUCTOS
      int cont=0;
      Iterator it=listadoA.listIterator();
      Articulos art;
      Double total=0.00;
      while(it.hasNext()){
          art=(Articulos)it.next();
          cont++;
      //BUCLE PARA CADA PRODUCTO
      out.write("PRODUCT_QTY_"+cont+"="+art.getCantidad());
      out.write("PRODUCT_DESCRIPTION_"+cont+"="+art.getDescripcionArticulo());
      out.write("PRODUCT_UNIT_COST_"+cont+"="+art.getPrecioUnitarioNeto());
      out.write("PRODUCT_DISCOUNT_"+cont+"=0.00");
      out.write("PRODUCT_TAX_PERCENTAGE_"+cont+"=21");
      total=art.getCantidad() * art.getPrecioUnitarioNeto();
      out.write("PRODUCT_FINAL_COST_"+cont+"="+total);
      // final productos
      }
      out.write("PER_RET_IMP_GANANCIAS=0");
      out.write("PER_RET_IVA=0");
      out.write("PER_RET_ING_BRUTOS=0");
      out.write("IMP_INTERNOS=0");
      out.write("IMP_MUNICIPALES=0");
      out.write("TOTAL_IVA_27=0.00");
      out.write("TOTAL_IVA_21=0.00");
      out.write("TOTAL_IVA_10_5=0.00");
      out.write("TOTAL_IVA_5=0.00");
      out.write("TOTAL_IVA_2_5='0'");
      out.write("TOTAL_IVA_0='0'");
     // Arrays.sort(out);
      
      //System.err.println(out.toString());
      
      System.out.println("clave publica "+key);
      out.write("secreta="+secreta);
      
      //out.write("");
     
      out.close();
      
      BufferedReader in=new BufferedReader(new InputStreamReader(con.getInputStream()));
      String response;
      String cadena="";
      while((response=in.readLine())!=null){
          System.out.println("cadena "+response);
          cadena+=response;
          
      }
          
        System.out.println(cadena);          
      //String cadena=response;
      //in.close();
      
      
      DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
        DocumentBuilder db=dbf.newDocumentBuilder();
        System.err.println(cadena);
        InputSource archivo=new InputSource();
        
        archivo.setCharacterStream(new StringReader(cadena));
        Document documento=db.parse(archivo);
        //Document documento=db.parse(response);
        documento.getDocumentElement().normalize();
        org.w3c.dom.NodeList nodeLista=documento.getElementsByTagName("AFIP");
        int cantidad=nodeLista.getLength();
        System.out.println("Informacion de conecciones");
        
        for (int s = 0; s < cantidad; s++) {
            
	Node primerNodo = nodeLista.item(s);
	String titulo;
	String autor;
	String hits;
        System.err.println("numero nodo "+s);
        
	if (primerNodo.getNodeType() == Node.ELEMENT_NODE) {

	Element primerElemento = (Element) primerNodo;
        //Configuracion conf=new Configuracion();
        
	        org.w3c.dom.NodeList primerNombreElementoLista =primerElemento.getElementsByTagName("RESPONSE");
	Element primerNombreElemento =(Element) primerNombreElementoLista.item(0);
	        org.w3c.dom.NodeList primerNombre = primerNombreElemento.getChildNodes();
	 fE.setRespuesta(((Node) primerNombre.item(0)).getNodeValue().toString());
	System.out.println("respuesta : "  + fE.getRespuesta());
        //conf.setNombreConeccion(nombreConeccion);
	        org.w3c.dom.NodeList segundoNombreElementoLista =primerElemento.getElementsByTagName("CAE");
	Element segundoNombreElemento =(Element) segundoNombreElementoLista.item(0);
	        org.w3c.dom.NodeList segundoNombre = segundoNombreElemento.getChildNodes();

	fE.setCae(((Node) segundoNombre.item(0)).getNodeValue().toString());
	System.out.println("cae : "  + fE.getCae());
        //conf.setStringDeUrl(stringDeUrl);
	        org.w3c.dom.NodeList tercerNombreElementoLista =primerElemento.getElementsByTagName("CAE_VTO");
	Element tercerNombreElemento =(Element) tercerNombreElementoLista.item(0);
	        org.w3c.dom.NodeList tercerNombre = tercerNombreElemento.getChildNodes();
    	fE.setCaeVto(((Node) tercerNombre.item(0)).getNodeValue().toString());
	System.out.println("cae vencimiento : "  + fE.getCaeVto());
        //conf.setUsuario(usuario);
        org.w3c.dom.NodeList cuartoNombreElementoLista =primerElemento.getElementsByTagName("FECHA_CAE");
	Element cuartoNombreElemento =(Element) cuartoNombreElementoLista.item(0);
	        org.w3c.dom.NodeList cuartoNombre = cuartoNombreElemento.getChildNodes();
    	fE.setFechaCae(((Node) cuartoNombre.item(0)).getNodeValue().toString());
	System.out.println("fecha cae : "  + fE.getFechaCae());
        //conf.setUsuario(usuario);
        org.w3c.dom.NodeList quintoNombreElementoLista =primerElemento.getElementsByTagName("AFIPQTY");
	Element quintoNombreElemento =(Element) quintoNombreElementoLista.item(0);
	        org.w3c.dom.NodeList quintoNombre = quintoNombreElemento.getChildNodes();
    	fE.setAfipQty(((Node) quintoNombre.item(0)).getNodeValue().toString());
	System.out.println("afipqty : "  + fE.getAfipQty());
        //conf.setUsuario(usuario);
        org.w3c.dom.NodeList sextoNombreElementoLista =primerElemento.getElementsByTagName("AFIPLASTID");
	Element sextoNombreElemento =(Element) sextoNombreElementoLista.item(0);
	        org.w3c.dom.NodeList sextoNombre = sextoNombreElemento.getChildNodes();
    	fE.setAfipPlastId(((Node) sextoNombre.item(0)).getNodeValue().toString());
	System.out.println("afipplastid : "  + fE.getAfipPlastId());
        //conf.setUsuario(usuario);
        org.w3c.dom.NodeList septimoNombreElementoLista =primerElemento.getElementsByTagName("AFIPLASTCBTE");
	Element septimoNombreElemento =(Element) septimoNombreElementoLista.item(0);
	        org.w3c.dom.NodeList septimoNombre = septimoNombreElemento.getChildNodes();
    	fE.setAfipPlastCbte(((Node) septimoNombre.item(0)).getNodeValue().toString());
	System.out.println("afipplastcbte : "  + fE.getAfipPlastCbte());
        //conf.setClave(clave);
        //listadoConecciones.add(conf);
        
	}
        }
        in.close();
        }catch(java.net.UnknownHostException ex){
          Logger.getLogger(FacturaElectronica.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("En factura electronica: "+ex);
            fE.setRespuesta("ERROR");
        }catch(java.lang.NullPointerException ey){
          Logger.getLogger(FacturaElectronica.class.getName()).log(Level.SEVERE, null, ey);
            System.err.println("Parametros invalidos: "+ey);
            fE.setRespuesta("PARAMETROS");  
        }
        */
                
        fE.setIdFactura(compro.getIdFactura());
        fE.setIdCliente(compro.getCliente().getCodigoId());
        fE.setCustomerId(idCliente);
        fE.setCustomerTypeDoc(tipoDocumento);
        fE.setTipoComprobante(tipoComprobante);
        fE.setFecha(Inicio.fechaDia);
        fE.setFechaCae(Inicio.fechaDia);
        fE.setCaeVto(Inicio.fechaDia);
        importeTotal=importeTotal.replace("_",".");
        fE.setImporteTotal(importeTotal);
        fE.setImporteNeto(importeNeto);
        fE.setImpuestoLiquido(impuestoLiq);
        
        fE.setId(guardarC(fE));
        
        
      return fE;
    }
        private Integer guardarC(Object Fe){
            Transaccionable tra=new Conecciones();
        FacturaElectronica ffE=new FacturaElectronica();
        ffE=(FacturaElectronica)Fe;
        Integer estado=0;
        Integer id=0;
        if(ffE.getRespuesta().equals("OK"))estado=1;
        String sql="insert into facturaelectronica (cae,cae_vto,fecha_cae,afipqty,afipplastid,afipplastcbte,idfactura,idcliente,estado,customerid,customertypedoc,tipo_comprobante,importe_total,importe_neto,impto_liq) values ('"+ffE.getCae()+"','"+ffE.getCaeVto()+"','"+ffE.getFechaCae()+"','"+ffE.getAfipQty()+"','"+ffE.getAfipPlastId()+"','"+ffE.getAfipPlastCbte()+"',"+ffE.getIdFactura()+","+ffE.getIdCliente()+","+estado+",'"+ffE.getCustomerId()+"','"+ffE.getCustomerTypeDoc()+"','"+ffE.getTipoComprobante()+"','"+ffE.getImporteTotal()+"','"+ffE.getImporteNeto()+"','"+ffE.getImpuestoLiquido()+"')";
        tra.guardarRegistro(sql);
        sql="select LAST_INSERT_ID()";
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                id=rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FacturaElectronica.class.getName()).log(Level.SEVERE, null, ex);
        }
                return id;
        }
        private final char[] CONSTS_HEX = { '0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f' };
    public  String encriptaEnMD5(String stringAEncriptar)
    {
        try
        {
           MessageDigest msgd = MessageDigest.getInstance("MD5");
           byte[] bytes = msgd.digest(stringAEncriptar.getBytes());
           StringBuilder strbCadenaMD5 = new StringBuilder(2 * bytes.length);
           for (int i = 0; i < bytes.length; i++)
           {
               int bajo = (int)(bytes[i] & 0x0f);
               int alto = (int)((bytes[i] & 0xf0) >> 4);
               strbCadenaMD5.append(CONSTS_HEX[alto]);
               strbCadenaMD5.append(CONSTS_HEX[bajo]);
           }
           return strbCadenaMD5.toString();
        } catch (NoSuchAlgorithmException e) {
           return null;
        }
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import Configuracion.Propiedades;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Administrador
 */
public class Mail {
    private final Properties propiedades=new Properties();
    private String password="SUtter001";
    private Session sesion;
    private String direccionFile;
    private String detalleListado;
    private String asunto;

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    
    public void setDetalleListado(String detalleListado) {
        this.detalleListado = detalleListado;
    }
    
    
    public void setDireccionFile(String direccionFile) {
        this.direccionFile = direccionFile;
    }
    
    private void init(){
        propiedades.put("mail.smtp.host","mail.bambusoft.com.ar");
        propiedades.put("mail.smtp.starttls.enable","false");
        propiedades.put("mail.smtp.port",587);
        propiedades.put("mail.smtp.mail.sender","sistemas@bambusoft.com.ar");
        propiedades.put("mail.smtp.user","sistemas@bambusoft.com.ar");
        propiedades.put("mail.smtp.auth","true");
        sesion=Session.getDefaultInstance(propiedades);
        
    }
    public void enviarMailRepartoCargaCompleta() throws MessagingException{
        init();
        try{
            MimeMessage mensaje=new MimeMessage(sesion);
            mensaje.setFrom(new InternetAddress((String)propiedades.get("mail.smtp.mail.sender")));
            mensaje.addRecipient(Message.RecipientType.TO,new InternetAddress(Propiedades.getCORREOCIERREDECAJA()));
            mensaje.addRecipient(Message.RecipientType.CC,new InternetAddress(Propiedades.getCORREOCC()));
            mensaje.addRecipient(Message.RecipientType.BCC,new InternetAddress(Propiedades.getCORREOCCC()));
            mensaje.setSubject(asunto);
            BodyPart texto=new MimeBodyPart();
            texto.setText("INFORME GENERADO POR CIERRE DE CAJA   \n Saludos");
            BodyPart adjunto=new MimeBodyPart();
            adjunto.setDataHandler(new DataHandler(new FileDataSource(direccionFile)));
            adjunto.setFileName(detalleListado);
            MimeMultipart multiParte=new MimeMultipart();
            multiParte.addBodyPart(texto);
            multiParte.addBodyPart(adjunto);
            //mensaje.setText("El reparto del vehiculo esta cerrado para el reparto. Motivo :CAPACIDAD DE CARGA COMPLETADA");
            mensaje.setContent(multiParte);
            Transport t=sesion.getTransport("smtp");
            t.connect((String)propiedades.get("mail.smtp.user"), password);
            t.sendMessage(mensaje,mensaje.getAllRecipients());
            t.close();
        }catch(MessagingException me){
            System.err.println("EL MENSAJE NO SE PUDO ENVIAR "+me);
        }
    }
   public void enviarMailRepartoCerrado(String descripcionVehiculo,String fecha) throws MessagingException{
        init();
        try{
            MimeMessage mensaje=new MimeMessage(sesion);
            mensaje.setFrom(new InternetAddress((String)propiedades.get("mail.smtp.mail.sender")));
            mensaje.addRecipient(Message.RecipientType.TO,new InternetAddress("hernangonzalez@sidercon.com"));
            mensaje.addRecipient(Message.RecipientType.CC,new InternetAddress("rgonzalez@sidercon.com"));
            mensaje.setSubject("REPARTO CERRADO");
            mensaje.setText("El reparto del vehiculo "+descripcionVehiculo+" esta cerrado para el reparto del dia "+fecha+". Motivo :CAPACIDAD DE REPARTO COMPLETA");
            Transport t=sesion.getTransport("smtp");
            t.connect((String)propiedades.get("mail.smtp.user"), password);
            t.sendMessage(mensaje,mensaje.getAllRecipients());
            t.close();
        }catch(MessagingException me){
            System.err.println("EL MENSAJE NO SE PUDO ENVIAR "+me);
        }
    }
}

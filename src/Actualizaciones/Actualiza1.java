/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Actualizaciones;

import interfaceGraficas.Inicio;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import javax.swing.Timer;

/**
 *
 * @author mauro
 */
public class Actualiza1 extends SwingWorker{
    
        Timer timer; 

    public Actualiza1() {
        
    }
        

        @Override
    protected Object doInBackground() throws Exception {
        this.timer = new Timer(600000,new ActionListener(){ 
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Inicio.actualizable=0;
                System.err.println("COMIENZO DEL CICLO DE RELOJ *******************************");
                //ActOt at=new ActOt();
                //at.start();
                
                //Inicio.coneccionRemota=true;
                //VerificarErrores();
                
                //carga la lista remota
                //Proveedores.cargarListadoProv1();
                
                
                
                
                    //Process aplicacion;
                    //aplicacion.exec("java -jar C:/Gestor/ActualizadorPocoPrecio.jar");
                    
                    /*
                try {
                    Process aplicacion=Runtime.getRuntime().exec("cmd java -jar c:/Gestor/ActualizadorPocoPrecio.jar");
                    } catch (IOException ex) {
                    Logger.getLogger(Actualiza1.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null,"error al ejecutar actualizacion "+ex);
                }
                    */
                    
                    /*
                    InputStreamReader entrada = new InputStreamReader(aplicacion.getInputStream());
            BufferedReader stdInput = new BufferedReader(entrada);
                    String salida;

            //Si el comando tiene una salida la mostramos
            if((salida=stdInput.readLine()) != null){
            	System.out.println("Comando ejecutado Correctamente");
            	while ((salida=stdInput.readLine()) != null){
                	System.out.println(salida);
                }
            }else{
            	System.out.println("No se a producido ninguna salida");
            }
                            */
                
                
            }
           // timer.start();
            
        }); 
        timer.start();
        return null;
    }

    

    
        
        
}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjetosBackUp;

import java.util.ArrayList;

/**
 *
 * @author mauro
 */
public interface Backapear {
    public Boolean GenerarArchivosBarrios();
    public Boolean GenerarArchivosCac();
    public Boolean GenerarArchivosCiudades();
    public Boolean GenerarArchivosClientes();
    public Boolean GenerarArchivosDepartamentos();
    public Boolean GenerarArchivosEdificios();
    public Boolean GenerarArchivosEquivalencias();
    public Boolean GenerarArchivosInmobiliarias();
    public Boolean GenerarArchivosMonedas();
    public Boolean GenerarArchivosMovimientoDetallePlanes();
    public Boolean GenerarArchivosMovimientoPlanes();
    public Boolean GenerarArchivosPlanes();
    public Boolean GenerarArchivosTipos();
    public Boolean GenerarArchivosUsuarios();
    public Boolean GenerarArchivosVistas();
    public String GenerarArchivos();
    public Boolean RecuperarArchivos(String archivo,String base);
    public Boolean HacerBackUpGeneral();
    public Boolean RecuperarBackUpGeneral();
    
}

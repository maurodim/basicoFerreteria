/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import Conversores.Numeros;
import Impresiones.Impresora;
import facturacion.clientes.ClientesTango;
import interfaceGraficas.Inicio;
import interfaces.Transaccionable;
import interfacesPrograma.Facturar;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Thread.sleep;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author mauro
 */
public class Comprobantes implements Facturar{
    private int numero;
    private String descripcion;
    private int destinatarioCondicion;
    private int descargaStock;
    private ClientesTango cliente;
    private ArrayList listadoDeArticulos;
    private int tipoComprobante;
    private Date fechaEmision;
    private int tipoMovimiento;
    private Double montoTotal;
    private int usuarioGenerador;
    private int idSucursal;
    private int idDeposito;
    private Integer idCaja;
    private Double montoBruto;
    private Double montoIva;
    private Double montoRet;
    private Integer pagado;
    private static Integer numeroComprobante;
    private static Integer idComp;
    private int fiscal;
    private int tipoComprobanteFiscal;
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    

    public int getTipoComprobanteFiscal() {
        return tipoComprobanteFiscal;
    }

    public void setTipoComprobanteFiscal(int tipoComprobanteFiscal) {
        this.tipoComprobanteFiscal = tipoComprobanteFiscal;
    }

    public int getFiscal() {
        return fiscal;
    }

    public void setFiscal(int fiscal) {
        this.fiscal = fiscal;
    }
    

    public Integer getPagado() {
        return pagado;
    }

    public void setPagado(Integer pagado) {
        this.pagado = pagado;
    }
    
    

    public Integer getIdCaja() {
        return idCaja;
    }

    public void setIdCaja(int idCaja) {
        this.idCaja = idCaja;
    }

    public Double getMontoBruto() {
        return montoBruto;
    }

    public void setMontoBruto(Double montoBruto) {
        this.montoBruto = montoBruto;
    }

    public Double getMontoIva() {
        return montoIva;
    }

    public void setMontoIva(Double montoIva) {
        this.montoIva = montoIva;
    }

    public Double getMontoRet() {
        return montoRet;
    }

    public void setMontoRet(Double montoRet) {
        this.montoRet = montoRet;
    }
    

    public ClientesTango getCliente() {
        return cliente;
    }

    public void setCliente(ClientesTango cliente) {
        this.cliente = cliente;
    }

    public ArrayList getListadoDeArticulos() {
        return listadoDeArticulos;
    }

    public void setListadoDeArticulos(ArrayList listadoDeArticulos) {
        this.listadoDeArticulos = listadoDeArticulos;
    }

    public int getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(int tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public int getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(int tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public Double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(Double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public int getUsuarioGenerador() {
        return usuarioGenerador;
    }

    public void setUsuarioGenerador(int usuarioGenerador) {
        this.usuarioGenerador = usuarioGenerador;
    }

    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    public int getIdDeposito() {
        return idDeposito;
    }

    public void setIdDeposito(int idDeposito) {
        this.idDeposito = idDeposito;
    }
    

    public Comprobantes(int numero) {
            this.idCaja=0;
        this.idDeposito=0;
        this.idSucursal=0;
        this.numero=0;
        this.tipoComprobante=0;
        this.tipoMovimiento=0;
        this.usuarioGenerador=0;

        this.numero = numero;

    }

    public Comprobantes() {
        this.idCaja=0;
        this.idDeposito=0;
        this.idSucursal=0;
        this.numero=0;
        this.tipoComprobante=0;
        this.tipoMovimiento=0;
        this.usuarioGenerador=0;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getDestinatarioCondicion() {
        return destinatarioCondicion;
    }

    public void setDestinatarioCondicion(int destinatarioCondicion) {
        this.destinatarioCondicion = destinatarioCondicion;
    }

    public int getDescargaStock() {
        return descargaStock;
    }

    public void setDescargaStock(int descargaStock) {
        this.descargaStock = descargaStock;
    }
    private static void numeroActual(int tipoComprobante){
        Transaccionable tra;
        /*
        if(Inicio.coneccionRemota){
            tra=new Conecciones();
        }else{
        */ 
            tra=new ConeccionLocal();
        //}
        String tc="ticket";
        String fc="FCA A";
        String tx="";
        if(tipoComprobante==1){
            tx=tc;
        }else{
            tx=fc;
        }
        
        String sql="select * from tipocomprobantes where numero="+tipoComprobante;
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
            numeroComprobante=rs.getInt("numeroActivo");
            idComp=rs.getInt("numero");   
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Comprobantes.class.getName()).log(Level.SEVERE, null, ex);
        }catch(NullPointerException ee){
            Inicio.coneccionRemota=false;
            numeroActual(tipoComprobante);
        }
    }
    @Override
    public Boolean guardar(Object oob) {
        // GUARDA EL OBJETO COMPROBANTE COMO EL O LOS MOVIMIENTOS CORRESPONDIENTES
        // EJ: EN UNA FACTURA DE VENTA VOY A TENER MOVIMIENTO DE STOCK DESCONTANDO
        // EN EL DEPOSITO Y DE CAJA SUMANDO EN CAJA DE LA SUCURSAL
        /*
         * ACA VOY A TENER QUE DISTINGUIR EL TIPO DE MOVIMIENTO, VOY A TENER QUE HACER UN 
         * ITERATOR DE LOS ARTICULOS, PERO NO HACE FALTA PARA EL MOVIMIENTO DE CAJA PUESTO 
         * QUE GRABO EL MONTO TOTAL.
         * 
         */
        Boolean verifi=true;
        Comprobantes comp=(Comprobantes)oob;
        Iterator iComp=comp.listadoDeArticulos.listIterator();
        Articulos articulo=new Articulos();
        Boolean imprimioFiscal=false;
        if(comp.getFiscal()==1){
            
            FileWriter fichero=null;
            try {
                
                String sentencias=null;
                String cantidad;
                String precioUnitario;
                Double redondeo;
                int posicion;
                int coincidencia = -1;
                
                fichero=new FileWriter("tique.in");
                PrintWriter pw=new PrintWriter(fichero);
                
                //ACA EMPIEZO CON LOS COMPROBANTES FISCALES
                System.out.println("comprobante tipo: "+comp.getTipoComprobante());
                if(comp.getTipoComprobante()==8){
                    String cuit=comp.getCliente().getNumeroDeCuit().replace("-","");
                    sentencias="@FACTABRE|T|C|A|1|P|12|I|I|"+comp.getCliente().getRazonSocial()+"||CUIT|"+cuit+"|N|"+comp.getCliente().getDireccion()+"|SFE|_|_|_|C";
                    pw.println(sentencias);
                    Double pUnitario=0.00;
                    while(iComp.hasNext()){
                        articulo=(Articulos)iComp.next();
                        redondeo=articulo.getCantidad() * 1000;
                        cantidad=Numeros.ConvetirNumeroCuatroDigitos(redondeo).replace(".","");
                        //cantidad=String.valueOf(articulo.getCantidad()).replace(".","");
                        pUnitario=articulo.getPrecioUnitarioNeto() / 1.21;
                        //pUnitario=articulo.getPrecioUnitarioNeto() - pUnitario;
                        //pUnitario=pUnitario / articulo.getCantidad();
                        redondeo=pUnitario;
                        precioUnitario=Numeros.ConvetirNumeroDosDigitos(redondeo).replace(",", "");
                        sentencias="@FACTITEM|"+articulo.getDescripcionArticulo()+"|"+cantidad+"|"+precioUnitario+"|2100|M|1|0|0|";
                        //sentencias=sentencias.replace(".","");
                        
                        pw.println(sentencias);
                    }
                    Double subtotal=comp.getMontoTotal() * 0.21;
                    
                    sentencias="@FACTSUBTOTAL|N";
                    pw.println(sentencias);
                    sentencias="@FACTPERCEP|iva|T";
                    pw.println(sentencias);
                    sentencias="@FACTCIERRA|T|A|";
                    pw.println(sentencias);
                    comp.setTipoComprobanteFiscal(81);
                }
                if(comp.getTipoComprobante()==9|| comp.getTipoComprobante()==18){
                  
                    String cuit=comp.getCliente().getNumeroDeCuit().replace("-","");
                    sentencias="@FACTABRE|T|C|B|1|P|12|I|E|"+comp.getCliente().getRazonSocial()+"||CUIT|"+comp.getCliente().getNumeroDeCuit()+"|N|"+comp.getCliente().getDireccion()+"|SFE|_|_|_|C";
                    pw.println(sentencias);
                    Double pUnitario=0.00;
                    while(iComp.hasNext()){
                        articulo=(Articulos)iComp.next();
                        redondeo=articulo.getCantidad() * 1000;
                        cantidad=Numeros.ConvetirNumeroCuatroDigitos(redondeo).replace(".","");
                        //cantidad=String.valueOf(articulo.getCantidad()).replace(".","");
                        pUnitario=articulo.getPrecioUnitarioNeto();
                        //pUnitario=articulo.getPrecioUnitarioNeto() - pUnitario;
                        redondeo=pUnitario * 100;
                        precioUnitario=Numeros.ConvetirNumeroCuatroDigitos(redondeo).replace(".", "");
                        sentencias="@FACTITEM|"+articulo.getDescripcionArticulo()+"|"+cantidad+"|"+precioUnitario+"|210000|M|1|0|0|";
                        //sentencias=sentencias.replace(".","");
                        
                        pw.println(sentencias);
                    }
                    Double subtotal=comp.getMontoTotal() * 0.21;
                    
                    sentencias="@FACTSUBTOTAL|N";
                    pw.println(sentencias);
                    sentencias="@FACTCIERRA|T|B|";
                    pw.println(sentencias);
                    comp.setTipoComprobanteFiscal(82);
                    
                }
                if(comp.getTipoComprobante()==11){
                    sentencias="@TIQUEABRE|B";
                    
                    pw.println(sentencias);
                    while(iComp.hasNext()){
                        articulo=(Articulos)iComp.next();
                        redondeo=articulo.getCantidad() * 1000;
                        cantidad=Numeros.ConvetirNumeroCuatroDigitos(redondeo).replace(".","");
                        //cantidad=String.valueOf(articulo.getCantidad()).replace(".","");
                        redondeo=articulo.getPrecioUnitarioNeto() * 100;
                        precioUnitario=Numeros.ConvetirNumeroCuatroDigitos(redondeo).replace(".", "");
                        sentencias="@TIQUEITEM|"+articulo.getDescripcionArticulo()+"|"+cantidad+"|"+precioUnitario+"|210000|M|1|0|0|";
                        //sentencias=sentencias.replace(".","");
                        
                        pw.println(sentencias);
                    }
                    sentencias="@TIQUESUBTOTAL|P|";
                    pw.println(sentencias);
                    sentencias="@TIQUECIERRA|T|";
                    pw.println(sentencias);
                    comp.setTipoComprobanteFiscal(82);
                    
                }
                //NOTAS DE CREDITO
                
                if(comp.getTipoComprobante()==112){
                    String cuit=comp.getCliente().getNumeroDeCuit().replace("-","");
                    sentencias="@FACTABRE|M|C|A|1|P|12|I|I|"+comp.getCliente().getRazonSocial()+"||CUIT|"+cuit+"|N|"+comp.getCliente().getDireccion()+"|SFE|_|_|_|C";
                    pw.println(sentencias);
                    Double pUnitario=0.00;
                    while(iComp.hasNext()){
                        articulo=(Articulos)iComp.next();
                        redondeo=articulo.getCantidad() * 1000;
                        redondeo=redondeo * (-1);
                        cantidad=Numeros.ConvetirNumeroCuatroDigitos(redondeo).replace(".","");
                        //cantidad=String.valueOf(articulo.getCantidad()).replace(".","");
                        pUnitario=articulo.getPrecioUnitarioNeto() / 1.21;
                        //pUnitario=articulo.getPrecioUnitarioNeto() - pUnitario;
                        //pUnitario=pUnitario / articulo.getCantidad();
                        redondeo=pUnitario;
                        precioUnitario=Numeros.ConvetirNumeroDosDigitos(redondeo).replace(",", "");
                        sentencias="@FACTITEM|"+articulo.getDescripcionArticulo()+"|"+cantidad+"|"+precioUnitario+"|2100|M|1|0|0|";
                        //sentencias=sentencias.replace(".","");
                        
                        pw.println(sentencias);
                    }
                    Double subtotal=comp.getMontoTotal() * 0.21;
                    
                    sentencias="@FACTSUBTOTAL|N";
                    pw.println(sentencias);
                    sentencias="@FACTPERCEP|iva|T";
                    pw.println(sentencias);
                    sentencias="@FACTCIERRA|M|A|";
                    pw.println(sentencias);
                    comp.setTipoComprobanteFiscal(112);
                }
                if(comp.getTipoComprobante()==113){
                  
                    String cuit=comp.getCliente().getNumeroDeCuit().replace("-","");
                    sentencias="@FACTABRE|M|C|B|1|P|12|I|E|"+comp.getCliente().getRazonSocial()+"||CUIT|"+comp.getCliente().getNumeroDeCuit()+"|N|"+comp.getCliente().getDireccion()+"|SFE|_|_|_|C";
                    pw.println(sentencias);
                    Double pUnitario=0.00;
                    while(iComp.hasNext()){
                        articulo=(Articulos)iComp.next();
                        redondeo=articulo.getCantidad() * 1000;
                        cantidad=Numeros.ConvetirNumeroCuatroDigitos(redondeo).replace(".","");
                        //cantidad=String.valueOf(articulo.getCantidad()).replace(".","");
                        pUnitario=articulo.getPrecioUnitarioNeto();
                        //pUnitario=articulo.getPrecioUnitarioNeto() - pUnitario;
                        redondeo=pUnitario * 100;
                        precioUnitario=Numeros.ConvetirNumeroCuatroDigitos(redondeo).replace(".", "");
                        sentencias="@FACTITEM|"+articulo.getDescripcionArticulo()+"|"+cantidad+"|"+precioUnitario+"|210000|M|1|0|0|";
                        //sentencias=sentencias.replace(".","");
                        
                        pw.println(sentencias);
                    }
                    Double subtotal=comp.getMontoTotal() * 0.21;
                    
                    sentencias="@FACTSUBTOTAL|N";
                    pw.println(sentencias);
                    sentencias="@FACTCIERRA|M|B|";
                    pw.println(sentencias);
                    comp.setTipoComprobanteFiscal(113);
                    
                }
                
                sleep(1000);
                Runtime jpfBatch=Runtime.getRuntime();
                System.out.println(sentencias);
                jpfBatch.exec("java -jar jpfbatch.jar -I tique.in");
            
                File salida=new File("tique.out");
                FileReader fr = null;
            
                fr = new FileReader(salida);
            
                BufferedReader br=new BufferedReader(fr);
                String linea;
                
                    int numero=0;
                    while((linea=br.readLine())!=null){
                        numero++;
                        coincidencia=linea.indexOf("0500I");
                        if(comp.getTipoComprobante()==7)coincidencia=linea.indexOf("0500I");
                        //coincidencia=linea.indexOf("0500I");
                        if(comp.getTipoComprobante()==8)coincidencia=linea.indexOf("0500I");
                        
                        if(coincidencia != -1){
                            posicion=coincidencia + 10;
                            System.out.println(linea.substring(8,15));
                            numeroComprobante=Integer.parseInt(linea.substring(8,15));
                            imprimioFiscal=true;
                            
                        }
                        System.out.println(linea+" comprobante "+numeroComprobante);
                    }
                    //if(coincidencia== -1)JOptionPane.showMessageDialog(null, "Error en la impresion fiscal ");
                
                
                
            } catch (IOException ex) {
                Logger.getLogger(Comprobantes.class.getName()).log(Level.SEVERE, null, ex);
            
            } catch (InterruptedException ex) {
                Logger.getLogger(Comprobantes.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                    try {
                        fichero.close();
                        if(imprimioFiscal){
                            
                        }else{
                            return false;
                        }
                        
                    } catch (IOException ex) {
                        Logger.getLogger(Comprobantes.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            
            
        }else{
        numeroActual(comp.getTipoComprobante());
        }
        
         //numeroComprobante=0;
        
        if(numeroComprobante !=null){
        numeroComprobante++;
        comp.setNumero(numeroComprobante);
        
        Transaccionable tra=new ConeccionLocal();
        
        Articulos art;
        Boolean verif=false;
        String sql="";
        ResultSet rr;
        if(comp.getFiscal()==1){
               sql="insert into movimientoscaja (numeroUsuario,numeroSucursal,numeroComprobante,tipoComprobante,monto,tipoMovimiento,idCaja,idCliente,tipoCliente,pagado,fiscal) values ("+comp.getUsuarioGenerador()+","+comp.getIdSucursal()+","+numeroComprobante+","+comp.getTipoComprobanteFiscal()+","+comp.getMontoTotal()+","+comp.getTipoMovimiento()+","+Inicio.caja.getNumero()+","+comp.getCliente().getCodigoId()+",1,"+comp.getPagado()+",1)";
               tra.guardarRegistro(sql);
               sql="select last_insert_id()";
                rr=tra.leerConjuntoDeRegistros(sql);
            try {
                while(rr.next()){
                    comp.setId(rr.getInt(1));
                } 
            rr.close();
            } catch (SQLException ex) {
                Logger.getLogger(Comprobantes.class.getName()).log(Level.SEVERE, null, ex);
            }
               String fecha=Numeros.ConvertirFechaFiscal();
               
               String tipo=String.valueOf(comp.getTipoComprobanteFiscal());
               String numero=String.valueOf(numeroComprobante);
               comp.setMontoBruto(comp.getMontoTotal() / 1.21);
               comp.setMontoIva(comp.getMontoTotal() - comp.getMontoBruto());
               int tipoClienteId=0;
               if(comp.getTipoComprobante()==11){
                   tipoClienteId=99;
               }else{
                   tipoClienteId=80;
               }
               String razonS=comp.getCliente().getRazonSocial();
               String cuit=comp.getCliente().getNumeroDeCuit();
               if(cuit.equals("1"))cuit="0";
               sql="insert into fiscal (fecha,tipo,numero,gravado,impuesto,total,idcliente,tipoClienteId,razon,cuit) values (lpad("+fecha+",8,'0'),'"+tipo+"','"+numero+"',"+comp.getMontoBruto()+","+comp.getMontoIva()+","+comp.getMontoTotal()+","+comp.getCliente().getCodigoId()+","+tipoClienteId+",'"+razonS+"','"+cuit+"')";
               System.out.println("fiscal: "+sql);
               tra.guardarRegistro(sql);
        }else{
            sql="insert into movimientoscaja (numeroUsuario,numeroSucursal,numeroComprobante,tipoComprobante,monto,tipoMovimiento,idCaja,idCliente,tipoCliente,pagado) values ("+comp.getUsuarioGenerador()+","+comp.getIdSucursal()+","+comp.getNumero()+","+comp.getTipoComprobante()+","+comp.getMontoTotal()+","+comp.getTipoMovimiento()+","+Inicio.caja.getNumero()+","+comp.getCliente().getCodigoId()+",1,"+comp.getPagado()+")";
            tra.guardarRegistro(sql);
            sql="select last_insert_id()";
            rr=tra.leerConjuntoDeRegistros(sql);
            try {
                while(rr.next()){
                    comp.setId(rr.getInt(1));
                } 
            rr.close();
            } catch (SQLException ex) {
                Logger.getLogger(Comprobantes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        Iterator iComp1=comp.listadoDeArticulos.listIterator();
        while(iComp1.hasNext()){
            articulo=(Articulos)iComp1.next();
            Double cantidad=articulo.getCantidad() * -1;
            
            if(articulo.getIdCombo() == 1){
                Iterator itC=articulo.getCombo().listIterator();
                Double cant=0.00;
                art=new Articulos();
                while(itC.hasNext()){
                    art=(Articulos)itC.next();
                    cantidad=cantidad * art.getCantidad();
                    sql="insert into movimientosarticulos (tipoMovimiento,idArticulo,cantidad,numeroDeposito,tipoComprobante,numeroComprobante,numeroCliente,fechaComprobante,numeroUsuario,precioDeVenta,precioServicio,preciodecosto,idcaja,idcomprobante) values ("+comp.getTipoMovimiento()+","+art.getNumeroId()+","+cantidad+","+Inicio.deposito.getNumero()+","+comp.getTipoComprobante()+","+comp.getNumero()+","+comp.getCliente().getCodigoId()+",'"+comp.getFechaEmision()+"',"+comp.getUsuarioGenerador()+","+articulo.getPrecioUnitario()+","+articulo.getPrecioServicio()+","+articulo.getPrecioDeCosto()+","+Inicio.caja.getNumero()+","+comp.getId()+")";
                    verif=tra.guardarRegistro(sql);
                }
            }else{
            
            sql="insert into movimientosarticulos (tipoMovimiento,idArticulo,cantidad,numeroDeposito,tipoComprobante,numeroComprobante,numeroCliente,fechaComprobante,numeroUsuario,precioDeVenta,precioServicio,preciodecosto,idcaja,idcomprobante) values ("+comp.getTipoMovimiento()+","+articulo.getNumeroId()+","+cantidad+","+Inicio.deposito.getNumero()+","+comp.getTipoComprobante()+","+comp.getNumero()+","+comp.getCliente().getCodigoId()+",'"+comp.getFechaEmision()+"',"+comp.getUsuarioGenerador()+","+articulo.getPrecioUnitario()+","+articulo.getPrecioServicio()+","+articulo.getPrecioDeCosto()+","+Inicio.caja.getNumero()+","+comp.getId()+")";
            verif=tra.guardarRegistro(sql);
            }
        }
        
            
            sql="insert into movimientosclientes (numeroProveedor,monto,pagado,numeroComprobante,idUsuario,idCaja,idSucursal,tipoComprobante,idcomprobante) values ("+comp.getCliente().getCodigoId()+","+comp.getMontoTotal()+","+comp.getPagado()+","+numeroComprobante+","+Inicio.usuario.getNumeroId()+","+Inicio.caja.getNumero()+","+Inicio.sucursal.getNumero()+","+comp.getTipoComprobante()+","+comp.getId()+")";
            tra.guardarRegistro(sql);
        
        System.out.println("SE RECEPCIONO BARBARO");
        sql="update tipocomprobantes set numeroActivo="+numeroComprobante+" where numero="+idComp;
        /*
        if(Inicio.coneccionRemota){
            
        }else{
        * */
            tra=new ConeccionLocal();
        //}
        tra.guardarRegistro(sql);
        
        
        if(comp.getFiscal()==1){
            
        }else{
        Impresora impresion=new Impresora();
        try {
            impresion.ImprimiComprobante(comp);
        } catch (IOException ex) {
            Logger.getLogger(Comprobantes.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
        }else{
            verifi=false;
        }
        
        return verifi;
    }

    @Override
    public Boolean modificar(Object oob) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean nuevo(Object oob) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList listar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object leer(Object oob) {
        Comprobantes comprobante=(Comprobantes) oob;
        ArrayList listadoArt=new ArrayList();
        Transaccionable tra=new ConeccionLocal();
        String sql="select * from movimientoscaja where id="+comprobante.getId();
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        ClientesTango cliente;
        try {
            while(rs.next()){
                comprobante.setId(rs.getInt("id"));
                comprobante.setUsuarioGenerador(rs.getInt("numeroUsuario"));
                cliente=new ClientesTango(rs.getInt("idCliente"));
                comprobante.setCliente(cliente);
                comprobante.setIdSucursal(rs.getInt("numeroSucursal"));
                comprobante.setNumero(rs.getInt("numeroComprobante"));
                comprobante.setTipoComprobante(rs.getInt("tipoComprobante"));
                comprobante.setMontoTotal(rs.getDouble("monto"));
                comprobante.setTipoMovimiento(rs.getInt("tipoMovimiento"));
                comprobante.setIdCaja(rs.getInt("idCaja"));
                comprobante.setPagado(rs.getInt("pagado"));
                comprobante.setFiscal(rs.getInt("fiscal"));
                comprobante.setFechaEmision(rs.getDate("fecha"));
                
            }
            sql="select *,(select articulos.NOMBRE from articulos where articulos.ID=movimientosarticulos.idArticulo)as descri,(select articulos.BARRAS from articulos where articulos.ID=movimientosarticulos.idArticulo)as codA from movimientosarticulos where idcomprobante="+comprobante.getId();
            rs=tra.leerConjuntoDeRegistros(sql);
            Double cant=0.00;
            Articulos articulo;
            while(rs.next()){
                articulo=new Articulos();
                articulo.setDescripcionArticulo(rs.getString("descri"));//(Articulos) fact.cargarPorCodigoAsignado(rs.getInt("idArticulo"));
                articulo.setCodigoAsignado(rs.getString("codA"));
                cant=rs.getDouble("cantidad");
                cant=cant * -1;
                articulo.setCantidad(cant);
                articulo.setPrecioUnitario(rs.getDouble("precioDeVenta"));
                articulo.setPrecioServicio(rs.getDouble("precioServicio"));
                //total=rs.getDouble("total");
                //articulo.setCantidad(cant);
                //articulo.setPrecioUnitario(rs.getDouble("precioDeVenta"));
                //articulo.setPrecioServicio(rs.getDouble("precioServicio"));
                
                //renglon="Articulo :"+articulo.getCodigoAsignado()+" descripcion:"+articulo.getDescripcionArticulo()+" cantidad:"+cant+" precio:"+articulo.getPrecioUnitario();
                listadoArt.add(articulo);
            }
            comprobante.setListadoDeArticulos(listadoArt);
        } catch (SQLException ex) {
            Logger.getLogger(Comprobantes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return comprobante;
    }

    @Override
    public void imprimirComprobante(int tipoComprobante, Object oob) {
        Comprobantes comprobante=(Comprobantes)oob;
            Impresora impresion=new Impresora();
        
        try {
            impresion.ImprimiComprobante(comprobante);
        } catch (IOException ex) {
            Logger.getLogger(Comprobantes.class.getName()).log(Level.SEVERE, null, ex);
        }
         

    }

    @Override
    public ArrayList listadoBusqueda(String criterio) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean guardarNuevoCliente(Object cliente) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean modificarDatosDelCliente(Object cliente) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList listarClientes(String nombre) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object cargarPorCodigoDeBarra(String codigoDeBarra) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Integer leerNumeroDeComprobanteSiguiente(Integer numeroComprobante) {
        Integer numeroSiguiente=0;
        String sql="select tipocomprobantes.numeroActivo from tipocomprobantes where numero="+numeroComprobante+" and numeroSucursal="+Inicio.sucursal.getNumero();
        Transaccionable tra=new Conecciones();
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
                numeroSiguiente=rs.getInt("numeroActivo");
                numeroSiguiente++;
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Comprobantes.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return numeroSiguiente;
    }
    private Integer numeroComprobante(Integer tipoComp){
               Transaccionable tra=new Conecciones();
               Integer numeroAct=0;
        String sql="select * from tipocomprobantes where numero="+tipoComp+" and numeroSucursal="+Inicio.sucursal.getNumero();
        ResultSet rs=tra.leerConjuntoDeRegistros(sql);
        try {
            while(rs.next()){
            numeroAct=rs.getInt("numeroActivo");
                
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Comprobantes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return numeroAct++;
    }

    @Override
    public Object cargarPorCodigoAsignado(Integer id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList listadoBusquedaMayorista(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object cargarPorCodigoDeBarraMayorista(String codigoDeBarra) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

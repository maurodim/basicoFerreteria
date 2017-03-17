/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.ArrayList;

/**
 *
 * @author mauro
 */
public interface Trasladable {
    public Boolean GenerarRemitoInterno(Object ob);
    public Boolean RecibirRemitoInterno(Object ob);
    public Boolean ConfirmarRemitoInterno(Object ob);
    public ArrayList LeerRemitosInternos(Integer depositoDestino);
    public ArrayList LeerRemitosInternosEnviados(Integer depositoOrigen);
    public Object StockPorDeposito(Integer numeroDeposito,Object articulo);
    public ArrayList MovimientosPorDeposito(Integer numeroDeposito);
}

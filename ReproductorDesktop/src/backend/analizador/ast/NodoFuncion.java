
package backend.analizador.ast;

import backend.analizador.ErrorLSS;
import backend.analizador.comprobaciones.tablasimbolos.ComprobacionTipos;
import backend.analizador.objetos.ManejadorArreglos;
import backend.analizador.objetos.Posicion;
import backend.analizador.objetos.Simbolo;
import java.util.ArrayList;

public class NodoFuncion extends Nodo{
    
    /**
     * 1.Procediento
     * 2.Funcion
     **/
    
    private Simbolo simbolo;
    private Posicion pos;
    private int tipoOp, ambito;
    private Nodo nodoIz, nodoDr;
    public boolean errorS;
    public ErrorLSS errorLSS;
    private ComprobacionTipos comprobacionTipos = new ComprobacionTipos();
    //para el arreglo
    ArrayList<Integer> dimens;
    private ArrayList<Object> lista;
    ManejadorArreglos manejadorArreglos = new ManejadorArreglos();
    //para una funcion o metodo
    ArrayList<Simbolo> listaParams;
    ArrayList<Nodo> listaAcciones;

    public NodoFuncion(Simbolo simbolo, int tipoOp, int ambito, Nodo nodoIz, Nodo nodoDr, ArrayList<Simbolo> listaParams, ArrayList<Nodo> listaAcciones, Posicion pos) {
        this.simbolo = simbolo;
        this.pos = pos;
        this.tipoOp = tipoOp;
        this.ambito = ambito;
        this.nodoIz = nodoIz;
        this.nodoDr = nodoDr;
        this.listaParams = listaParams;
        this.listaAcciones = listaAcciones;
    }
    
    @Override
    public Simbolo getSimbolo() {
        return simbolo;
    }

    @Override
    public void setSimbolo(Simbolo simbolo) {
        this.simbolo = simbolo;
    }

    @Override
    public int getTipoOp() {
        return tipoOp;
    }

    @Override
    public void setTipoOp(int tipoOp) {
        this.tipoOp = tipoOp;
    }

    @Override
    public Nodo getNodoIz() {
        return nodoIz;
    }

    @Override
    public void setNodoIz(Nodo nodoIz) {
        this.nodoIz = nodoIz;
    }

    @Override
    public Nodo getNodoDr() {
        return nodoDr;
    }

    @Override
    public void setNodoDr(Nodo nodoDr) {
        this.nodoDr = nodoDr;
    }

    @Override
    public Posicion getPosicion() {
        return pos;
    }
    
    @Override
    public void setPosicion(Posicion pos) {
        this.pos = pos;
    }
    
    @Override
    public int getAmbito() {
        return ambito;
    }

    @Override
    public void setAmbito(int ambito) {
        this.ambito = ambito;
    }
    @Override
    public ArrayList<Integer> getDimens() {
        return dimens;
    }
    @Override
    public ArrayList<Object> getLista() {
        return lista;
    }
    @Override
    public void setLista(ArrayList<Object> lista) {
        this.lista = lista;
    }
    
    

    @Override
    public void realizarAccion() {
        /**NodoDr = NodoS
        *  NodoIz = el valor de asignacion
        *  Simbolo los datos de la variable
        **/
        if(nodoDr!=null && nodoIz!=null){
            realizarOperacion(getTipoOp());
        }
    }
    
    public void realizarOperacion(int tipoOp){
        errorS = false; 
        switch(tipoOp){
             case 1:
                 // declaracion variable
                 //asignarVariable();
                 break;
             case 2:
                 // declaracion arreglo
                 //asignarArreglo();
                 break;
        }
    }

    @Override
    public void setDimens(ArrayList<Nodo> dimens) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Nodo> getParams() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setParams(ArrayList<Nodo> params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

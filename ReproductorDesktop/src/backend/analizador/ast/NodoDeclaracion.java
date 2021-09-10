/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.analizador.ast;

import backend.analizador.ErrorLSS;
import backend.analizador.comprobaciones.tablasimbolos.ComprobacionTipos;
import backend.analizador.objetos.Arreglo;
import backend.analizador.objetos.ManejadorArreglos;
import backend.analizador.objetos.Posicion;
import backend.analizador.objetos.Simbolo;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class NodoDeclaracion extends Nodo{
    /**
     *  1. Nodo declaracion var
     *  2. Nodo declaracion var arreglo
     *  0. Simbolo
     **/
    
    private Simbolo simbolo;
    private Posicion pos;
    private int tipoOp, ambito;
    private Nodo nodoIz, nodoDr;
    public boolean errorS;
    public ErrorLSS errorLSS;
    private ComprobacionTipos comprobacionTipos = new ComprobacionTipos();
    //para el arreglo
    ArrayList<Nodo> dimens;
    private ArrayList<Object> lista;
    ManejadorArreglos manejadorArreglos = new ManejadorArreglos();
    
    public NodoDeclaracion(int tipoOp, int ambito, Nodo nodoIz, Nodo nodoDr, Posicion pos) {
        this.tipoOp = tipoOp;
        this.ambito = ambito;
        this.nodoIz = nodoIz;
        this.nodoDr = nodoDr;
        this.pos = pos;
    }
    
    public NodoDeclaracion(int tipoOp, int ambito, Nodo nodoIz, Nodo nodoDr, Posicion pos, ArrayList<Nodo> dimens, ArrayList<Object> lista) {
        this.tipoOp = tipoOp;
        this.ambito = ambito;
        this.nodoIz = nodoIz;
        this.nodoDr = nodoDr;
        this.pos = pos;
        this.dimens = dimens;
        this.lista = lista;
    }

    public NodoDeclaracion(Simbolo simbolo, int tipoOp, int ambito, Nodo nodoIz, Nodo nodoDr, Posicion pos) {
        this.simbolo = simbolo;
        this.tipoOp = tipoOp;
        this.ambito = ambito;
        this.nodoIz = nodoIz;
        this.nodoDr = nodoDr;
        this.pos = pos;
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
    public void setDimens(ArrayList<Nodo> dimens) {
        this.dimens = dimens;
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
        //NodoDr = apuntador al hijo
        //NodoIz = el valor de asignacion
        //Simbolo los datos de la variable
        
        
        if(nodoDr!=null && nodoIz!=null){
            realizar(getTipoOp());
        }
    }
    
    public void realizar(int tipoOp){
        errorS = false; 
        switch(tipoOp){
             case 1:
                 // declaracion variable
                 declararVariable();
                 break;
             case 2:
                 // declaracion arreglo
                 declararArreglo();
                 break;
        }
    }
    
    public void declararVariable(){
        //NodoDr = el valor de asignacion
        //NodoIz = apuntador al hijo
        //Simbolo los datos de la variable
        
        
        if(nodoDr!=null){
            if(comprobacionTipos.getTipoDato(nodoDr.getSimbolo().getTipo(),simbolo.getTipo())!=null){
                simbolo.setObjeto(comprobacionTipos.convertir(nodoDr.getSimbolo().getObjeto(),nodoDr.getSimbolo().getTipo()));
            }else{
                errorLSS = new ErrorLSS(pos.getLinea(), pos.getColumna(), simbolo.getNombre(), "Error: no es posible asignar el valor a la varible nombre \""+simbolo.getNombre()+"\", ya que no es del mismo tipo dato", "Semantico");
                errorS = true;
            }
        }else{
            errorS = true;
        }
    }
    
    public void declararArreglo(){
        //NodoDr = apuntador al hijo
        //NodoIz = el valor de asignacion
        //Simbolo los datos de la variable
        if(dimens==null && lista==null){
            
        }else if(dimens!=null && lista==null){
            
        }else if(dimens!=null && lista!=null){
            if (manejadorArreglos.verificarDimArreglo(lista)) {
//                if (manejadorArreglos.compararDims(dimens)) {
//                    Arreglo arrAux = (Arreglo) simbolo.getObjeto();
//                    arrAux.llenarArreglo(manejadorArreglos.listaObj);
//                    simbolo.setObjeto(arrAux);
//                } else {
//                    errorLSS = new ErrorLSS(pos.getLinea(), pos.getColumna(), simbolo.getNombre(), "Error: no coinciden las dimensiones del arreglo \""+simbolo.getNombre()+"\" con las dimensiones de los datos que se agregaron", "Semantico");
//                    errorS = false;
//                }
            } else {
                errorLSS = new ErrorLSS(pos.getLinea(), pos.getColumna(), simbolo.getNombre(), "Error: no coinciden las dimensiones de los valores del arreglo \""+simbolo.getNombre()+"\" con los datos de se le ingresaron", "Semantico");
                errorS = false;
            }
        }
    }

    @Override
    public ArrayList<Nodo> getParams() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setParams(ArrayList<Nodo> params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Integer> getDimens() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

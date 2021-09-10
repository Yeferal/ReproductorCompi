/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.analizador.ast;

import backend.analizador.objetos.Posicion;
import backend.analizador.objetos.Simbolo;
import java.util.ArrayList;

/**
 *
 * @author yefer
 */
public class NodoS extends Nodo{
    
    /**
     * 1. Nodo Simbolo ID
     * 2. Nodo simbolo
     * 3. Nodo llamada Funcion
     * 4. Nodo llamada Metodo
     * 5. Nodo llamada posArreglo
     **/
    
    private Simbolo simbolo;
    private Posicion pos;
    private int tipoOp, ambito;
    private Nodo nodoIz, nodoDr;
    //Para funciones y metodos
    private ArrayList<Nodo> params;
    //para el arreglo
    ArrayList<Integer> dimens;
    private ArrayList<Object> lista;

    public NodoS(Simbolo simbolo, Posicion pos, int tipoOp, int ambito) {
        this.simbolo = simbolo;
        this.pos = pos;
        this.tipoOp = tipoOp;
        this.ambito = ambito;
    }
    
    public NodoS(int tipoOp , Nodo nodoIz, Nodo nodoDr, Posicion pos) {
        this.tipoOp = tipoOp;
        this.nodoIz = nodoIz;
        this.nodoDr = nodoDr;
        this.pos = pos;
    }

    public NodoS(int tipoOp, int ambito, Nodo nodoIz, Nodo nodoDr, Posicion pos) {
        this.tipoOp = tipoOp;
        this.ambito = ambito;
        this.nodoIz = nodoIz;
        this.nodoDr = nodoDr;
        this.pos = pos;
    }
    
    
    
    public NodoS(){
        
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
    public void realizarAccion() {
        if(nodoDr!=null && nodoIz!=null){
            
        }
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
    public ArrayList<Nodo> getParams() {
        return params;
    }
    @Override
    public void setParams(ArrayList<Nodo> params) {
        this.params = params;
    }
    
    public void getS(){
        System.out.println("Hola soy un nodo simbolo");
    }

    @Override
    public ArrayList<Integer> getDimens() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setDimens(ArrayList<Nodo> dimens) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}

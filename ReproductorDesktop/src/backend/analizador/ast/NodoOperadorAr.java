/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.analizador.ast;

import backend.analizador.objetos.Posicion;
import backend.analizador.objetos.Simbolo;

/**
 *
 * @author yefer
 */
public class NodoOperadorAr extends Nodo{
    
    /**
     * Tipo de operador
     *  1. +
     *  2. -
     *  3. *
     *  4. /
     *  5. %
     *  6. ^
     *  0. Simbolo
     *  
     **/
    private Simbolo simbolo;
    private Posicion pos;
    private int tipoOp;
    private Nodo nodoIz, nodoDr;

    public NodoOperadorAr(int tipoOp, Nodo nodoIz, Nodo nodoDr) {
        this.tipoOp = tipoOp;
        this.nodoIz = nodoIz;
        this.nodoDr = nodoDr;
        
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
    public void realizarAccion() {
        if(nodoDr!=null && nodoIz!=null){
            
        }
    }
    
    public void realizarOperacion(int tipoOp){
        Simbolo s1 = getNodoIz().getSimbolo();
        Simbolo s2 = getNodoDr().getSimbolo();
        switch(tipoOp){
            case 1:
                
                break;
            case 2:
                
                break;
            case 3:
                
                break;
            case 4:
                
                break;
            case 5:
                
                break;
            case 6:
                
                break;
                default:
                    
        }
    }

    
    
    
    
    
    
}

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
    private int tipoOp, ambito;
    private Nodo nodoIz, nodoDr;

    public NodoOperadorAr(int tipoOp , Nodo nodoIz, Nodo nodoDr, Posicion pos) {
        this.tipoOp = tipoOp;
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
    public void realizarAccion() {
        if(nodoDr!=null && nodoIz!=null){
            realizarOperacion(getTipoOp());
        }
    }
    
    
    
    public void realizarOperacion(int tipoOp){
        Simbolo s1 = getNodoIz().getSimbolo();
        Simbolo s2 = getNodoDr().getSimbolo();
        switch(tipoOp){
            case 1:
                //si es +
                
                break;
            case 2:
                //si es -
                
                break;
            case 3:
                //si es *
                
                break;
            case 4:
                //si es /
                
                break;
            case 5:
                //si es %
                
                break;
            case 6:
                //si es ^
                
                break;
                default:
                    
        }
    }
    
    public void sumar(Simbolo s1, Simbolo s2){
        if((s1.getTipo().equals("CADENA") || s2.getTipo().equals("CADENA"))){
            //sr = new Simbolo("CADENA","CADENA",manejadorTablaSimbolos.ambitoFlag,(String) s1.getObjeto()+""+(String)s2.getObjeto() );
	}else{
            if(s1.getTipo().equals("CARACTER") && s2.getTipo().equals("ENTERO")){
		
                //simbolo = new Simbolo("ENTERO","ENTERO",manejadorTablaSimbolos.ambitoFlag,(((int)s1.getObjeto().toString().charAt(0))+(int)s2.getObjeto()));
            }else if(s1.getTipo().equals("ENTERO") && s2.getTipo().equals("CARACTER")){
		//simbolo = new Simbolo("ENTERO","ENTERO",manejadorTablaSimbolos.ambitoFlag, ((int)s1.getObjeto())+((int)s2.getObjeto().toString().charAt(0)));
            }else if(s1.getTipo().equals("ENTERO") && s2.getTipo().equals("ENTERO")){
                //simbolo = new Simbolo("ENTERO","ENTERO",manejadorTablaSimbolos.ambitoFlag, ((int)s1.getObjeto())+(int)s2.getObjeto());
            }else if((s1.getTipo().equals("ENTERO") && s2.getTipo().equals("DOBLE")) || (s1.getTipo().equals("DOBLE") && s2.getTipo().equals("ENTERO"))){
                //simbolo = new Simbolo("DOBLE","DOBLE",manejadorTablaSimbolos.ambitoFlag, ((double)comprobacionTipos.convertirADoble(s1.getObjeto(),"DOBLE")+(double)comprobacionTipos.convertirADoble(s2.getObjeto(),"DOBLE")));
            }else{
                //parser.agregarError("Semantico",(String) o,"Error en la operacion entre \"+\"", oright+1, oleft+1);
            }
	}
    }
    
    public void restar(Simbolo s1, Simbolo s2){
        if((!s1.getTipo().equals("CADENA") && !s2.getTipo().equals("CADENA"))){
            //parser.agregarError("Semantico",(String) o,"Error en la operacion entre \"-\", no es posible realizar esta operacion entre cadenas", oright+1, oleft+1);
        }else{
            if(s1.getTipo().equals("CARACTER") && s2.getTipo().equals("ENTERO")){
                
            }else if(s1.getTipo().equals("ENTERO") && s2.getTipo().equals("CARACTER")){
                
            }else if(s1.getTipo().equals("ENTERO") && s2.getTipo().equals("ENTERO")){
                
            }else if((s1.getTipo().equals("ENTERO") && s2.getTipo().equals("DOBLE")) || (s1.getTipo().equals("DOBLE") && s2.getTipo().equals("ENTERO"))){
                
            }else{
                //parser.agregarError("Semantico",(String) o,"Error en la operacion entre \"-\"", oright+1, oleft+1);
            }
        }
    }
    
    public void multiplicar(Simbolo s1, Simbolo s2){
        if((s1.getTipo().equals("CADENA") || s2.getTipo().equals("CADENA"))){
            
        }else{
            if(s1.getTipo().equals("CARACTER") && s2.getTipo().equals("ENTERO")){
                
            }else if(s1.getTipo().equals("ENTERO") && s2.getTipo().equals("CARACTER")){
                
            }else if(s1.getTipo().equals("ENTERO") && s2.getTipo().equals("ENTERO")){
                
            }else if((s1.getTipo().equals("ENTERO") && s2.getTipo().equals("DOBLE")) || (s1.getTipo().equals("DOBLE") && s2.getTipo().equals("ENTERO"))){
                
            }else{
                
            }
                    
        }
    }
    
    public void dividir(Simbolo s1, Simbolo s2){
        if((s1.getTipo().equals("CADENA") || s2.getTipo().equals("CADENA"))){
            
        }else{
            if(s1.getTipo().equals("CARACTER") && s2.getTipo().equals("ENTERO")){
                
            }else if(s1.getTipo().equals("ENTERO") && s2.getTipo().equals("CARACTER")){
                
            }else if(s1.getTipo().equals("ENTERO") && s2.getTipo().equals("ENTERO")){
                
            }else if((s1.getTipo().equals("ENTERO") && s2.getTipo().equals("DOBLE")) || (s1.getTipo().equals("DOBLE") && s2.getTipo().equals("ENTERO"))){
                
            }else{
                
            }
                    
        }
    }
    
    public void modular(Simbolo s1, Simbolo s2){
        if((s1.getTipo().equals("CADENA") || s2.getTipo().equals("CADENA"))){
            
        }else{
            if(s1.getTipo().equals("CARACTER") && s2.getTipo().equals("ENTERO")){
                
            }else if(s1.getTipo().equals("ENTERO") && s2.getTipo().equals("CARACTER")){
                
            }else if(s1.getTipo().equals("ENTERO") && s2.getTipo().equals("ENTERO")){
                
            }else if((s1.getTipo().equals("ENTERO") && s2.getTipo().equals("DOBLE")) || (s1.getTipo().equals("DOBLE") && s2.getTipo().equals("ENTERO"))){
                
            }else{
                
            }
                    
        }
    }
    
    public void potenciar(Simbolo s1, Simbolo s2){
        if((s1.getTipo().equals("CADENA") || s2.getTipo().equals("CADENA"))){
            
        }else{
            if(s1.getTipo().equals("CARACTER") && s2.getTipo().equals("ENTERO")){
                
            }else if(s1.getTipo().equals("ENTERO") && s2.getTipo().equals("CARACTER")){
                
            }else if(s1.getTipo().equals("ENTERO") && s2.getTipo().equals("ENTERO")){
                
            }else if((s1.getTipo().equals("ENTERO") && s2.getTipo().equals("DOBLE")) || (s1.getTipo().equals("DOBLE") && s2.getTipo().equals("ENTERO"))){
                
            }else{
                
            }
                    
        }
    }

    
    
    
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.analizador.ast;

import backend.analizador.ErrorLSS;
import backend.analizador.comprobaciones.tablasimbolos.ComprobacionTipos;
import backend.analizador.objetos.Posicion;
import backend.analizador.objetos.Simbolo;
import java.util.ArrayList;

/**
 *
 * @author yefer
 */
public class NodoOpAritmetico extends Nodo{
    
    /**
     * Tipo de operador aritmetico
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
    public boolean errorS;
    public ErrorLSS errorLSS;
    private ComprobacionTipos comprobacionTipos = new ComprobacionTipos();
    //para el arreglo
    ArrayList<Integer> dimens;
    private ArrayList<Object> lista;

    public NodoOpAritmetico(int tipoOp, int ambito, Nodo nodoIz, Nodo nodoDr, Posicion pos) {
        this.tipoOp = tipoOp;
        this.ambito = ambito;
        this.nodoIz = nodoIz;
        this.nodoDr = nodoDr;
        this.pos = pos;
    }
    
    public NodoOpAritmetico(){
        
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
    
    
    
    public void realizarOperacion(int tipoOp){
        Simbolo s1 = getNodoIz().getSimbolo();
        Simbolo s2 = getNodoDr().getSimbolo();
        errorS = false;
        switch(tipoOp){
            case 1:
                //si es +
                sumar(s1, s2);
                break;
            case 2:
                //si es -
                restar(s1, s2);
                break;
            case 3:
                //si es *
                multiplicar(s1, s2);
                break;
            case 4:
                //si es /
                dividir(s1, s2);
                break;
            case 5:
                //si es %
                modular(s1, s2);
                break;
            case 6:
                //si es ^
                potenciar(s1, s2);
                break;
                default:
                    errorS = true;
        }
    }
    
    public void sumar(Simbolo s1, Simbolo s2){
        if((s1.getTipo().equals("CADENA") || s2.getTipo().equals("CADENA")) || (s1.getTipo().equals("CADENA") && s2.getTipo().equals("CARACTER")) || (s1.getTipo().equals("CARACTER") && s2.getTipo().equals("CADENA"))){
            simbolo = new Simbolo("CADENA","CADENA",ambito,(String) s1.getObjeto()+""+(String)s2.getObjeto() );
	}else{
            if(s1.getTipo().equals("CARACTER") && s2.getTipo().equals("ENTERO")){
                simbolo = new Simbolo("ENTERO","ENTERO",ambito,(((int)s1.getObjeto().toString().charAt(0))+(int)s2.getObjeto()));
            }else if(s1.getTipo().equals("ENTERO") && s2.getTipo().equals("CARACTER")){
		simbolo = new Simbolo("ENTERO","ENTERO",ambito, ((int)s1.getObjeto())+((int)s2.getObjeto().toString().charAt(0)));
            }else if(s1.getTipo().equals("ENTERO") && s2.getTipo().equals("ENTERO")){
                simbolo = new Simbolo("ENTERO","ENTERO",ambito, ((int)s1.getObjeto())+(int)s2.getObjeto());
            }else if((s1.getTipo().equals("ENTERO") && s2.getTipo().equals("DOBLE")) || (s1.getTipo().equals("DOBLE") && s2.getTipo().equals("ENTERO"))){
                simbolo = new Simbolo("DOBLE","DOBLE",ambito, ((double)comprobacionTipos.convertirADoble(s1.getObjeto(),"DOBLE")+(double)comprobacionTipos.convertirADoble(s2.getObjeto(),"DOBLE")));
            }else{
                errorLSS = new ErrorLSS(pos.getLinea(), pos.getColumna(), "+", "Error en la operacion entre \"+\"", "Semantico");
                errorS = true;
            }
	}
    }
    
    public void restar(Simbolo s1, Simbolo s2){
        if((!s1.getTipo().equals("CADENA") && !s2.getTipo().equals("CADENA"))){
            errorLSS = new ErrorLSS(pos.getLinea(), pos.getColumna(), "-","Error en la operacion entre \"-\", no es posible realizar esta operacion entre cadenas", "Semantico");
            errorS = true;
        }else{
            if(s1.getTipo().equals("CARACTER") && s2.getTipo().equals("ENTERO")){
                simbolo = new Simbolo("ENTERO","ENTERO",ambito,(((int)s1.getObjeto().toString().charAt(0))-(int)s2.getObjeto()));
            }else if(s1.getTipo().equals("ENTERO") && s2.getTipo().equals("CARACTER")){
                simbolo = new Simbolo("ENTERO","ENTERO",ambito, ((int)s1.getObjeto())-((int)s2.getObjeto().toString().charAt(0)));
            }else if(s1.getTipo().equals("ENTERO") && s2.getTipo().equals("ENTERO")){
                simbolo = new Simbolo("ENTERO","ENTERO",ambito, ((int)s1.getObjeto())-(int)s2.getObjeto());
            }else if((s1.getTipo().equals("ENTERO") && s2.getTipo().equals("DOBLE")) || (s1.getTipo().equals("DOBLE") && s2.getTipo().equals("ENTERO"))){
                simbolo = new Simbolo("DOBLE","DOBLE",ambito, ((double)comprobacionTipos.convertirADoble(s1.getObjeto(),"DOBLE")-(double)comprobacionTipos.convertirADoble(s2.getObjeto(),"DOBLE")));
            }else{
                errorLSS = new ErrorLSS(pos.getLinea(), pos.getColumna(), "-","Error en la operacion entre \"-\"", "Semantico");
                errorS = true;
            }
        }
    }
    
    public void multiplicar(Simbolo s1, Simbolo s2){
        if((s1.getTipo().equals("CADENA") || s2.getTipo().equals("CADENA"))){
            errorLSS = new ErrorLSS(pos.getLinea(), pos.getColumna(), "*","Error en la operacion entre \"*\", no es posible realizar esta operacion entre cadenas", "Semantico");
            errorS = true;
        }else{
            if(s1.getTipo().equals("CARACTER") && s2.getTipo().equals("ENTERO")){
                simbolo = new Simbolo("DOBLE","DOBLE",ambito,(double)(((int)s1.getObjeto().toString().charAt(0))*(int)s2.getObjeto()));
            }else if(s1.getTipo().equals("ENTERO") && s2.getTipo().equals("CARACTER")){
                simbolo = new Simbolo("DOBLE","DOBLE",ambito, ((double)((int)s1.getObjeto())*((int)s2.getObjeto().toString().charAt(0))));
            }else if(s1.getTipo().equals("ENTERO") && s2.getTipo().equals("ENTERO")){
                simbolo = new Simbolo("DOBLE","DOBLE",ambito, ((double)((int)s1.getObjeto())*(int)s2.getObjeto()));
            }else if((s1.getTipo().equals("ENTERO") && s2.getTipo().equals("DOBLE")) || (s1.getTipo().equals("DOBLE") && s2.getTipo().equals("ENTERO"))){
                simbolo = new Simbolo("DOBLE","DOBLE",ambito, ((double)comprobacionTipos.convertirADoble(s1.getObjeto(),"DOBLE")*(double)comprobacionTipos.convertirADoble(s2.getObjeto(),"DOBLE")));
            }else{
                errorLSS = new ErrorLSS(pos.getLinea(), pos.getColumna(), "*","Error en la operacion entre \"*\"", "Semantico");
                errorS = true;
            }     
        }
    }
    
    public void dividir(Simbolo s1, Simbolo s2){
        if((s1.getTipo().equals("CADENA") || s2.getTipo().equals("CADENA"))){
            errorLSS = new ErrorLSS(pos.getLinea(), pos.getColumna(), "/","Error en la operacion entre \"/\", no es posible realizar esta operacion entre cadenas", "Semantico");
            errorS = true;
        }else{
            if(s1.getTipo().equals("CARACTER") && s2.getTipo().equals("ENTERO")){
                simbolo = new Simbolo("DOBLE","DOBLE",ambito,(double)(((int)s1.getObjeto().toString().charAt(0))/(int)s2.getObjeto()));
            }else if(s1.getTipo().equals("ENTERO") && s2.getTipo().equals("CARACTER")){
                simbolo = new Simbolo("DOBLE","DOBLE",ambito, ((double)((int)s1.getObjeto())/((int)s2.getObjeto().toString().charAt(0))));
            }else if(s1.getTipo().equals("ENTERO") && s2.getTipo().equals("ENTERO")){
                simbolo = new Simbolo("DOBLE","DOBLE",ambito, ((double)((int)s1.getObjeto())/(int)s2.getObjeto()));
            }else if((s1.getTipo().equals("ENTERO") && s2.getTipo().equals("DOBLE")) || (s1.getTipo().equals("DOBLE") && s2.getTipo().equals("ENTERO"))){
                simbolo = new Simbolo("DOBLE","DOBLE",ambito, ((double)comprobacionTipos.convertirADoble(s1.getObjeto(),"DOBLE")/(double)comprobacionTipos.convertirADoble(s2.getObjeto(),"DOBLE")));
            }else{
                errorLSS = new ErrorLSS(pos.getLinea(), pos.getColumna(), "/","Error en la operacion entre \"/\"", "Semantico");
                errorS = true;
            }     
        }
    }
    
    public void modular(Simbolo s1, Simbolo s2){
        if((s1.getTipo().equals("CADENA") || s2.getTipo().equals("CADENA"))){
            errorLSS = new ErrorLSS(pos.getLinea(), pos.getColumna(), "%","Error en la operacion entre \"%\", no es posible realizar esta operacion entre cadenas", "Semantico");
            errorS = true;
        }else{
            if(s1.getTipo().equals("CARACTER") && s2.getTipo().equals("ENTERO")){
                simbolo = new Simbolo("DOBLE","DOBLE",ambito,(double)(((int)s1.getObjeto().toString().charAt(0))%(int)s2.getObjeto()));
            }else if(s1.getTipo().equals("ENTERO") && s2.getTipo().equals("CARACTER")){
                simbolo = new Simbolo("DOBLE","DOBLE",ambito, ((double)((int)s1.getObjeto())%((int)s2.getObjeto().toString().charAt(0))));
            }else if(s1.getTipo().equals("ENTERO") && s2.getTipo().equals("ENTERO")){
                simbolo = new Simbolo("DOBLE","DOBLE",ambito, ((double)((int)s1.getObjeto())%(int)s2.getObjeto()));
            }else if((s1.getTipo().equals("ENTERO") && s2.getTipo().equals("DOBLE")) || (s1.getTipo().equals("DOBLE") && s2.getTipo().equals("ENTERO"))){
                simbolo = new Simbolo("DOBLE","DOBLE",ambito, ((double)comprobacionTipos.convertirADoble(s1.getObjeto(),"DOBLE")%(double)comprobacionTipos.convertirADoble(s2.getObjeto(),"DOBLE")));
            }else{
                errorLSS = new ErrorLSS(pos.getLinea(), pos.getColumna(), "%","Error en la operacion entre \"%\"", "Semantico");
                errorS = true;
            }     
        }
    }
    
    public void potenciar(Simbolo s1, Simbolo s2){
        if((s1.getTipo().equals("CADENA") || s2.getTipo().equals("CADENA"))){
            errorLSS = new ErrorLSS(pos.getLinea(), pos.getColumna(), "^","Error en la operacion entre \"^\", no es posible realizar esta operacion entre cadenas", "Semantico");
            errorS = true;
        }else{
            if(s1.getTipo().equals("CARACTER") && s2.getTipo().equals("ENTERO")){
                simbolo = new Simbolo("DOBLE","DOBLE",ambito,(double) (Math.pow((int)s1.getObjeto().toString().charAt(0),(int)s2.getObjeto())));
            }else if(s1.getTipo().equals("ENTERO") && s2.getTipo().equals("CARACTER")){
                simbolo = new Simbolo("DOBLE","DOBLE",ambito, (double) (Math.pow((int)s1.getObjeto(),(int)s2.getObjeto().toString().charAt(0))));
            }else if(s1.getTipo().equals("ENTERO") && s2.getTipo().equals("ENTERO")){
                simbolo = new Simbolo("DOBLE","DOBLE",ambito, (double) (Math.pow((int)s1.getObjeto(),(int)s2.getObjeto())));
            }else if((s1.getTipo().equals("ENTERO") && s2.getTipo().equals("DOBLE")) || (s1.getTipo().equals("DOBLE") && s2.getTipo().equals("ENTERO"))){
                simbolo = new Simbolo("DOBLE","DOBLE",ambito, (double) (Math.pow((double)comprobacionTipos.convertirADoble(s1.getObjeto(),"DOBLE"),(double)comprobacionTipos.convertirADoble(s2.getObjeto(),"DOBLE"))));
            }else{
                errorLSS = new ErrorLSS(pos.getLinea(), pos.getColumna(), "^","Error en la operacion entre \"^\"", "Semantico");
                errorS = true;
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
    public void setDimens(ArrayList<Nodo> dimens) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
    
    
    
}

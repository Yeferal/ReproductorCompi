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
 * @author Usuario
 */
public class NodoOpRacional extends Nodo{
    
    /**
     * Tipo de operador racional
     *  1. ==
     *  2. !=
     *  3. <
     *  4. >
     *  5. <=
     *  6. >=
     *  7. !!
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
    
    public NodoOpRacional(int tipoOp, int ambito, Nodo nodoIz, Nodo nodoDr, Posicion pos) {
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
        if(nodoDr!=null && nodoIz!=null){
            realizarOperacion(getTipoOp());
        }
    }
    
    public void realizarOperacion(int tipoOp){
        Simbolo s1 = getNodoIz().getSimbolo();
        Simbolo s2 = getNodoDr().getSimbolo();
        errorS = false;
        switch(tipoOp){
            case 1:
                //si es ==
                igualIgual(s1, s2);
                break;
            case 2:
                //si es !=
                diferente(s1, s2);
                break;
            case 3:
                //si es <
                menorQ(s1, s2);
                break;
            case 4:
                //si es >
                mayorQ(s1, s2);
                break;
            case 5:
                //si es <=
                menorQIgual(s1, s2);
                break;
            case 6:
                //si es >=
                mayorQIgual(s1, s2);
                break;
            case 7:
                //si es !!
                isNull(s1, s2);
                break;
                default:
                    errorS = true;
        }
    }
    
    public void igualIgual(Simbolo s1, Simbolo s2){
        if(s1.getTipo().equals(s2.getTipo())){
            simbolo = new Simbolo("BOOLEAN","BOOLEAN",ambito,(Object) comprobacionTipos.opRacionalIgualIgual(s1,s2));
        }else if (s1.getTipo().equals("CARACTER") && s1.getTipo().equals("ENTERO")){
            simbolo = new Simbolo("BOOLEAN","BOOLEAN",ambito,(Object) comprobacionTipos.opRacionalIgualIgual(s1,s2));
	}else if(s1.getTipo().equals("ENTERO") && s1.getTipo().equals("CARACTER")){
            simbolo = new Simbolo("BOOLEAN","BOOLEAN",ambito,(Object) comprobacionTipos.opRacionalIgualIgual(s1,s2));
	}else if (s1.getTipo().equals("DOBLE") && s1.getTipo().equals("ENTERO")){
            simbolo = new Simbolo("BOOLEAN","BOOLEAN",ambito,(Object) comprobacionTipos.opRacionalIgualIgual(s1,s2));
	}else if(s1.getTipo().equals("ENTERO") && s1.getTipo().equals("DOBLE")){
            simbolo = new Simbolo("BOOLEAN","BOOLEAN",ambito,(Object) comprobacionTipos.opRacionalIgualIgual(s1,s2));
	}else{
            errorLSS = new ErrorLSS(pos.getLinea(), pos.getColumna(), "==", "Error en la operacion racional entre \"==\", estos tipos de datos no puedens ser comparados", "Semantico");
            errorS = true;
	}
    }
    
    public void diferente(Simbolo s1, Simbolo s2){
        if(s1.getTipo().equals(s2.getTipo())){  
            simbolo = new Simbolo("BOOLEAN","BOOLEAN",ambito,(Object) comprobacionTipos.opRacionalDiferente(s1,s2));
	}else if (s1.getTipo().equals("CARACTER") && s1.getTipo().equals("ENTERO")){
            simbolo = new Simbolo("BOOLEAN","BOOLEAN",ambito,(Object) comprobacionTipos.opRacionalDiferente(s1,s2));
	}else if(s1.getTipo().equals("ENTERO") && s1.getTipo().equals("CARACTER")){
            simbolo = new Simbolo("BOOLEAN","BOOLEAN",ambito,(Object) comprobacionTipos.opRacionalDiferente(s1,s2));
	}else if (s1.getTipo().equals("DOBLE") && s1.getTipo().equals("ENTERO")){
            simbolo = new Simbolo("BOOLEAN","BOOLEAN",ambito,(Object) comprobacionTipos.opRacionalDiferente(s1,s2));
	}else if(s1.getTipo().equals("ENTERO") && s1.getTipo().equals("DOBLE")){
            simbolo = new Simbolo("BOOLEAN","BOOLEAN",ambito,(Object) comprobacionTipos.opRacionalDiferente(s1,s2));
	}else{
            errorLSS = new ErrorLSS(pos.getLinea(), pos.getColumna(), "!=", "Error en la operacion racional entre \"!=\", estos tipos de datos no puedens ser comparados", "Semantico");
            errorS = true;
	}
    }
    
    public void mayorQ(Simbolo s1, Simbolo s2){
        if(!s1.getTipo().equals("CADENA") && !s2.getTipo().equals("CADENA")){
            simbolo = new Simbolo("BOOLEAN","BOOLEAN",ambito,(Object) comprobacionTipos.opRacionalMayorQ(s1,s2));
	}else{
            errorLSS = new ErrorLSS(pos.getLinea(), pos.getColumna(), ">", "Error en la operacion racional entre \">\", es posible realizarlo entre cadenas", "Semantico");
            errorS = true;
	}
    }
    
    public void menorQ(Simbolo s1, Simbolo s2){
        if(!s1.getTipo().equals("CADENA") && !s2.getTipo().equals("CADENA")){
            simbolo = new Simbolo("BOOLEAN","BOOLEAN",ambito,(Object) comprobacionTipos.opRacionalMenorQ(s1,s2));
	}else{
            errorLSS = new ErrorLSS(pos.getLinea(), pos.getColumna(), "<", "Error en la operacion racional entre \"<\", es posible realizarlo entre cadenas", "Semantico");
            errorS = true;
	}
    }
    
    public void mayorQIgual(Simbolo s1, Simbolo s2){
        if(!s1.getTipo().equals("CADENA") && !s2.getTipo().equals("CADENA")){
            simbolo = new Simbolo("BOOLEAN","BOOLEAN",ambito,(Object) comprobacionTipos.opRacionalMayorQIgual(s1,s2));
	}else{
            errorLSS = new ErrorLSS(pos.getLinea(), pos.getColumna(), ">=", "Error en la operacion racional entre \">=\", es posible realizarlo entre cadenas", "Semantico");
            errorS = true;
	}
    }
    
    public void menorQIgual(Simbolo s1, Simbolo s2){
        if(!s1.getTipo().equals("CADENA") && !s2.getTipo().equals("CADENA")){
            simbolo = new Simbolo("BOOLEAN","BOOLEAN",ambito,(Object) comprobacionTipos.opRacionalMenorQIgual(s1,s2));
	}else{
            errorLSS = new ErrorLSS(pos.getLinea(), pos.getColumna(), "<=", "Error en la operacion racional entre \"<=\", es posible realizarlo entre cadenas", "Semantico");
            errorS = true;
	}
    }
    
    public void isNull(Simbolo s1, Simbolo s2){
        simbolo = new Simbolo("BOOLEAN","BOOLEAN",ambito,(Object) (s1.getObjeto()==null));
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

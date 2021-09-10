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

public class NodoFEspecial extends Nodo{
    
    /**
     * 25. -> 1. Reproducir
     * 26. -> 2. Esperar
     * 27. -> 3. Ordenar
     * 28. -> 4. Sumarizar
     * 29. -> 5. Longitud
     * 30. -> 6. Mensaje
     * 
     **/
    
    private Simbolo simbolo;
    private Posicion pos;
    private int tipoOp, ambito;
    private Nodo nodoIz, nodoDr;
    public boolean errorS;
    public ErrorLSS errorLSS;
    private ComprobacionTipos comprobacionTipos = new ComprobacionTipos();
    //para las funciones especiales
    String p1, p11;
    Nodo p2,p3,p4,p5;

    public NodoFEspecial(Posicion pos, int tipoOp, int ambito, String p1, String p11, Nodo p2, Nodo p3, Nodo p4, Nodo p5) {
        this.pos = pos;
        this.tipoOp = tipoOp;
        this.ambito = ambito;
        this.p1 = p1;
        this.p11 = p11;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
        this.p5 = p5;
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

    @Override
    public void setDimens(ArrayList<Nodo> dimens) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Object> getLista() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setLista(ArrayList<Object> lista) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void realizarAccion() {
        //NodoDr = apuntador al hijo
        //NodoIz = el valor de asignacion
        //Simbolo los datos de la variable
        
        
        if(nodoDr!=null && nodoIz!=null){
            //realizar(getTipoOp());
        }
    }

    
    
}

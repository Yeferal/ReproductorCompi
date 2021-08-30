
package backend.analizador.objetos;

import java.util.ArrayList;

public class Simbolo {
    /*
    1. ENTERO
    2. DOBLE
    3. CARACTER
    4. CADENA
    5. ARRAY_ENTERO
    6. ARRAY_DOBLE
    7. ARRAY_CARACTER
    8. ARRAY_CADENA
    9. CONST_ENTERO
    10.CONST_DOBLE
    11.CONST_CARACTER
    12.CONST_CADENA
    13.FUNCION
    14.PROCEDIMIENTO
    15.PISTA
    0. 
    */
    
    private String nombre, tipo;
    private int ambito, numParams;
    private ArrayList<String> tipoParams;
    private Object objeto;
    private boolean isKeep;
//    private Nodo raiz;

    public Simbolo(String nombre, String tipo, int ambito) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.ambito = ambito;
    }

    public Simbolo(String nombre, String tipo, int ambito, boolean isKeep) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.ambito = ambito;
        this.isKeep = isKeep;
    }

    public Simbolo(String nombre, String tipo, int ambito, Object objeto, boolean isKeep) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.ambito = ambito;
        this.objeto = objeto;
        this.isKeep = isKeep;
    }

    public Simbolo(String nombre, String tipo, int ambito, int numParams, Object objeto, boolean isKeep) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.ambito = ambito;
        this.numParams = numParams;
        this.objeto = objeto;
        this.isKeep = isKeep;
    }

    public Simbolo(String nombre, String tipo, int ambito, int numParams, ArrayList<String> tipoParams, Object objeto, boolean isKeep) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.ambito = ambito;
        this.numParams = numParams;
        this.tipoParams = tipoParams;
        this.objeto = objeto;
        this.isKeep = isKeep;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getAmbito() {
        return ambito;
    }

    public void setAmbito(int ambito) {
        this.ambito = ambito;
    }

    public Object getObjeto() {
        return objeto;
    }

    public void setObjeto(Object objeto) {
        this.objeto = objeto;
    }

    public int getNumParams() {
        return numParams;
    }

    public void setNumParams(int numParams) {
        this.numParams = numParams;
    }

    public ArrayList<String> getTipoParams() {
        return tipoParams;
    }

    public void setTipoParams(ArrayList<String> tipoParams) {
        this.tipoParams = tipoParams;
    }

    public boolean isIsKeep() {
        return isKeep;
    }

    public void setIsKeep(boolean isKeep) {
        this.isKeep = isKeep;
    }
    
    
        
    
}

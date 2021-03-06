
package backend.analizador.objetos;

import backend.analizador.ast.Nodo;
import java.util.ArrayList;

public class Simbolo {
    /*
    1. ENTERO
    2. DOBLE
    3. CARACTER
    4. CADENA
    5. BOOLEAN
    6. ARRAY_ENTERO
    7. ARRAY_DOBLE
    8. ARRAY_CARACTER
    9. ARRAY_CADENA
    10.ARRAY_BOOLEAN
    11.CONST_ENTERO
    12.CONST_DOBLE
    13.CONST_CARACTER
    14.CONST_CADENA
    15.CONST_BOOLEAN
    16.FUNCION
    17.PROCEDIMIENTO
    18.PISTA
    0. 
    */
    
    private String nombre, tipo;
    private int ambito, numParams, tipoReturn;
    private ArrayList<String> tipoParams;
    private ArrayList<Simbolo> simbParams;
    private ArrayList<Nodo> simbParamsNodo;
    private Object objeto;
    private boolean isKeep, isArreglo = false, isFuncion=false, isMetodo=false;
//    private Nodo raiz;
    
    public Simbolo(String nombre, int ambito) {
        this.nombre = nombre;
        this.ambito = ambito;
    }
    
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

    public Simbolo(String nombre, String tipo, int ambito, Object objeto, boolean isKeep, boolean isArreglo) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.ambito = ambito;
        this.objeto = objeto;
        this.isKeep = isKeep;
        this.isArreglo = isArreglo;
    }
    
    

    public Simbolo(String nombre, String tipo, int ambito, Object objeto) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.ambito = ambito;
        this.objeto = objeto;
        this.isKeep = isKeep;
    }
    
    //si es funcion o metodo
    public Simbolo(String nombre, String tipo, int ambito, int numParams, Object objeto, boolean isKeep) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.ambito = ambito;
        this.numParams = numParams;
        this.objeto = objeto;
        this.isKeep = isKeep;
    }
    //si es funcion o metodo
    public Simbolo(String nombre, String tipo, int ambito, int numParams, ArrayList<String> tipoParams, Object objeto, boolean isKeep) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.ambito = ambito;
        this.numParams = numParams;
        this.tipoParams = tipoParams;
        this.objeto = objeto;
        this.isKeep = isKeep;
    }

    public Simbolo(String nombre, String tipo, int ambito, ArrayList<Simbolo> simbParams, boolean isKeep, boolean isFuncion, boolean isMetodo) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.ambito = ambito;
        this.simbParams = simbParams;
        this.isKeep = isKeep;
        this.isFuncion = isFuncion;
        this.isMetodo = isMetodo;
    }
    
    
    public void generarParamsStrings(){
        tipoParams = new ArrayList<>();
        for (int i = 0; i < simbParams.size(); i++) {
            if(simbParams.get(i)!=null){
               tipoParams.add(simbParams.get(i).getTipo());
            }
        }
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

    public int getTipoReturn() {
        return tipoReturn;
    }

    public void setTipoReturn(int tipoReturn) {
        this.tipoReturn = tipoReturn;
    }

    public boolean isIsArreglo() {
        return isArreglo;
    }

    public void setIsArreglo(boolean isArreglo) {
        this.isArreglo = isArreglo;
    }

    public ArrayList<Simbolo> getSimbParams() {
        return simbParams;
    }

    public void setSimbParams(ArrayList<Simbolo> simbParams) {
        this.simbParams = simbParams;
    }

    public boolean isIsFuncion() {
        return isFuncion;
    }

    public void setIsFuncion(boolean isFuncion) {
        this.isFuncion = isFuncion;
    }

    public boolean isIsMetodo() {
        return isMetodo;
    }

    public void setIsMetodo(boolean isMetodo) {
        this.isMetodo = isMetodo;
    }
    
    
    
}

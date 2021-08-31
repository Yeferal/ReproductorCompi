/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.analizador.objetos;

import java.util.ArrayList;

public class Pista {
    
    private String nombre;
    private ArrayList<String> listaExtensiones;
    private TablaSimbolos tablaSimbolos;
    boolean n = true, d = Boolean.valueOf("true");

    public Pista(String nombre, ArrayList<String> listaExtensiones, TablaSimbolos tablaSimbolos) {
        this.nombre = nombre;
        this.listaExtensiones = listaExtensiones;
        this.tablaSimbolos = tablaSimbolos;
    }

    public Pista(String nombre, TablaSimbolos tablaSimbolos) {
        this.nombre = nombre;
        this.tablaSimbolos = tablaSimbolos;
    }
    
    
    
}

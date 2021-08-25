
package backend.analizador;

import java_cup.runtime.Symbol;

public class ErrorSintactico {
    private int fila, columna;
    private String token, descripcion;
    private Symbol simbol;

    public ErrorSintactico(int fila, int columna, String token, String descripcion) {
        this.fila = fila;
        this.columna = columna;
        this.token = token;
        this.descripcion = descripcion;
    }

    public ErrorSintactico(Symbol simbol) {
        this.simbol = simbol;
//        simbol.value
    }
    

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
    
}

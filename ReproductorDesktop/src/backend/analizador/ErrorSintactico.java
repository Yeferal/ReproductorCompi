
package backend.analizador;

import java_cup.runtime.Symbol;

public class ErrorSintactico {
    private int linea, columna;
    private String token, descripcion, tipo;
    private Symbol simbol;

    public ErrorSintactico(int linea, int columna, String token, String descripcion, String tipo) {
        this.linea = linea;
        this.columna = columna;
        this.token = token;
        this.descripcion = descripcion;
        this.tipo = tipo;
    }

    public ErrorSintactico(Symbol simbol) {
        this.simbol = simbol;
//        simbol.value
    }
    

    public int getLinea() {
        return linea;
    }

    public void setLinea(int linea) {
        this.linea = linea;
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

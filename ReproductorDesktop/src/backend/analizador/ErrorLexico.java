
package backend.analizador;

public class ErrorLexico {
    private int linea, columna;
    private String token, descripcion, tipo;

    public ErrorLexico(int linea, int columna, String token, String descripcion, String tipo) {
        this.linea = linea;
        this.columna = columna;
        this.token = token;
        this.descripcion = descripcion;
        this.tipo = tipo;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.analizador.objetos;

public class Extension {
    private String nombre;
    private Pista pistaPertences;

    public Extension(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Pista getPistaPertences() {
        return pistaPertences;
    }

    public void setPistaPertences(Pista pistaPertences) {
        this.pistaPertences = pistaPertences;
    }
    
    
    
}

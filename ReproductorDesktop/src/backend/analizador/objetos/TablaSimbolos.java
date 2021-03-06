
package backend.analizador.objetos;

import java.util.ArrayList;

public class TablaSimbolos {
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
    
    public int posicionSimbolo;
    private ArrayList<Simbolo> listaSimbolos = new ArrayList<>();
    
    public void agregarSimbolo(Simbolo s){
        listaSimbolos.add(s);
    }

    public int getPosicionSimbolo() {
        return posicionSimbolo;
    }

    public void setPosicionSimbolo(int posicionSimbolo) {
        this.posicionSimbolo = posicionSimbolo;
    }

    public ArrayList<Simbolo> getListaSimbolos() {
        return listaSimbolos;
    }

    public void setListaSimbolos(ArrayList<Simbolo> listaSimbolos) {
        this.listaSimbolos = listaSimbolos;
    }
    
    
    public void pintar(){
        for (int i = 0; i < listaSimbolos.size(); i++) {
            System.out.println("Nombre: "+listaSimbolos.get(i).getNombre()+", Tipo: "+listaSimbolos.get(i).getTipo()+", Valor: "+listaSimbolos.get(i).getObjeto());
        }
    }
    
}

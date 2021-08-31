
package backend.analizador.comprobaciones.tablasimbolos;

import backend.analizador.objetos.Simbolo;
import backend.analizador.objetos.TablaSimbolos;
import java.util.ArrayList;
import java.util.Stack;

public class ManejadorTablaSimbolos {
    
    TablaSimbolos tablaSimbolos;
    Stack<Integer> pilaAmbitos;
    ArrayList<Integer> caminoAmbitos;
    public int ambitoFlag = 0, ambito=0, posSimbolo = 0;

    public ManejadorTablaSimbolos() {
        tablaSimbolos = new TablaSimbolos();
        pilaAmbitos = new Stack<>();
        caminoAmbitos = new ArrayList<>();
        iniciar();
    }
    
    public ManejadorTablaSimbolos(TablaSimbolos tablaSimbolos) {
        this.tablaSimbolos = tablaSimbolos;
        pilaAmbitos = new Stack<>();
        caminoAmbitos = new ArrayList<>();
        iniciar();
    }
    
    //Inicia la pila de ambitos
    public void iniciar(){
        pilaAmbitos.push(0);
        caminoAmbitos.add(0);
    }
    //suma un ambito y lo agrega a la pila, y setena la bandera el ambito actual
    public void indent(){
        ambito++;
        ambitoFlag = ambito;
        pilaAmbitos.push(ambito);
        caminoAmbitos.add(ambito);
    }
    //sale de un ambito y obtenemos el ambito actual en el que nos encontramos
    public void dedent(){
        if (!pilaAmbitos.isEmpty()) {
            pilaAmbitos.pop();
            caminoAmbitos.remove(caminoAmbitos.size()-1);
            if (!pilaAmbitos.isEmpty())
                ambitoFlag = pilaAmbitos.peek(); 
            else
                ambitoFlag = 0;
        }
    }
    /**busca si existe el simbolo en el mismo ambito, 
    esto cuado se agrega una variable
    **/
    public boolean isExistSimbolo(Simbolo s){
        for (int i = 0; i < tablaSimbolos.getListaSimbolos().size(); i++) {
            //si encuenta un simbolo con el mismo nombre
            if (tablaSimbolos.getListaSimbolos().get(i).getNombre().equals(s.getNombre())) {
                //si encuentra el simbolo con el mismo ambito
                if (tablaSimbolos.getListaSimbolos().get(i).getAmbito()==s.getAmbito()) {
                    return false;
                }else{
                    //sino se busca que no se encuentre dentro del rango de ambitos
                    for (int j = 0; j < caminoAmbitos.size(); j++) {
                        //si el ambitoCamion es igual al ambito del simbolo de la tabla
                        if (caminoAmbitos.get(j)==tablaSimbolos.getListaSimbolos().get(i).getAmbito()) {
                            for (int k = 0; k < caminoAmbitos.size(); k++) {
                                //si el nuevo simbolo se encuentra en el mismo conjunto de ambitos
                                if (caminoAmbitos.get(k)==s.getAmbito()) {
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
        }
        //agregarSimbolo(s);
        return true;
    }
    
    public boolean buscarSimbolo(Simbolo s){
        for (int i = 0; i < tablaSimbolos.getListaSimbolos().size(); i++) {
            //si encuenta un simbolo con el mismo nombre
            if (tablaSimbolos.getListaSimbolos().get(i).getNombre().equals(s.getNombre())) {
                //si encuentra el simbolo con el mismo ambito
                if (tablaSimbolos.getListaSimbolos().get(i).getAmbito()==s.getAmbito()) {
                    posSimbolo = i;
                    return true;
                }else{
                    //sino se busca que no se encuentre dentro del rango de ambitos
                    for (int j = 0; j < caminoAmbitos.size(); j++) {
                        //si el ambitoCamion es igual al ambito del simbolo de la tabla
                        if (caminoAmbitos.get(j)==tablaSimbolos.getListaSimbolos().get(i).getAmbito()) {
                            for (int k = j; k < caminoAmbitos.size(); k++) {
                                //si el nuevo simbolo se encuentra en el mismo conjunto de ambitos
                                if (caminoAmbitos.get(k)==s.getAmbito()) {
                                    posSimbolo = i;
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    public boolean buscarSimboloFuncionMetodo(Simbolo s,String tipo){
        for (int i = 0; i < tablaSimbolos.getListaSimbolos().size(); i++) {
            //si encuenta un simbolo con el mismo nombre
            if (tablaSimbolos.getListaSimbolos().get(i).getTipo().equals(tipo) && tablaSimbolos.getListaSimbolos().get(i).getNombre().equals(s.getNombre())) {
                //si encuentra el simbolo con el mismo ambito
                if (tablaSimbolos.getListaSimbolos().get(i).getAmbito()==s.getAmbito()) {
                    posSimbolo = i;
                    return true;
                }else{
                    //sino se busca que no se encuentre dentro del rango de ambitos
                    for (int j = 0; j < caminoAmbitos.size(); j++) {
                        //si el ambitoCamion es igual al ambito del simbolo de la tabla
                        if (caminoAmbitos.get(j)==tablaSimbolos.getListaSimbolos().get(i).getAmbito()) {
                            for (int k = j; k < caminoAmbitos.size(); k++) {
                                //si el nuevo simbolo se encuentra en el mismo conjunto de ambitos
                                if (caminoAmbitos.get(k)==s.getAmbito()) {
                                    posSimbolo = i;
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    
    public void agregarSimbolo(Simbolo s){
        tablaSimbolos.agregarSimbolo(s);
    }
    
    public Simbolo getSimbolo(){
        return tablaSimbolos.getListaSimbolos().get(posSimbolo);
    }
    
    public void setSimbolo(Simbolo s){
        tablaSimbolos.getListaSimbolos().set(posSimbolo, s);
    }
}


package backend.analizador.comprobaciones.tablasimbolos;

import backend.analizador.objetos.TablaSimbolos;
import java.util.Stack;

public class ManejadorTablaSimbolos {
    
    TablaSimbolos tablaSimbolos;
    Stack<Integer> pilaAmbitos = new Stack();
    int ambitoFlag = 0, ambito=0;
    

    public ManejadorTablaSimbolos(TablaSimbolos tablaSimbolos) {
        this.tablaSimbolos = tablaSimbolos;
        iniciar();
    }
    
    //Inicia la pila de ambitos
    public void iniciar(){
        pilaAmbitos.push(0);
    }
    //suma un ambito y lo agrega a la pila, y setena la bandera el ambito actual
    public void indent(){
        ambito++;
        ambitoFlag = ambito;
        pilaAmbitos.push(ambito);
    }
    //sale de un ambito y obtenemos el ambito actual en el que nos encontramos
    public void dedent(){
        if (!pilaAmbitos.isEmpty()) {
            pilaAmbitos.pop();
            if (!pilaAmbitos.isEmpty())
                ambitoFlag = pilaAmbitos.peek();
            else
                ambitoFlag = 0;
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.analizador.ast;

import backend.analizador.objetos.Posicion;
import backend.analizador.objetos.Simbolo;
import java.util.ArrayList;

/**
 *
 * @author yefer
 */
public abstract class Nodo {
    
    /**
     *  1. Nodo simbolo
     *  2. Nodo operador aritmetico (+,-,*,/,%,^)
     *  3. Nodo operador Racional (==,!=, <, >, <=, >=, !!)
     *  4. Nodo operador Logico (&&, !&&, ||, !||, &|, !)
     *  5. Nodo declaracion var
     *  6. Nodo declaracion var arreglo
     *  7. Nodo asignacion var
     *  8. Nodo asignacion var arreglo
     *  9. Nodo operador incremento/decremento (+=, ++, --)
     * 10. Nodo Si
     * 11. Nodo Sino Si
     * 12. Nodo Sino
     * 13. Nodo Switch
     * 14. Nodo caso
     * 15. Nodo salir
     * 16. Nodo default
     * 17. Nodo para (asignacion, condicion, incremento)
     * 18. Nodo Mientras (condicion)
     * 19. Nodo Hacer mientras -> (18. mientras)
     * 20. Nodo continuar
     * 21. Nodo Funcion
     * 22. Nodo Return
     * 23. Nodo Procedimiento
     * 24. Nodo Pametros
     * 25. Reproducir
     * 26. Esperar
     * 27. Ordenar
     * 28. Sumarizar
     * 29. Longitud
     * 30. Mensaje
     * 31. Principal()
     * 32. 
     * 33. 
     * 34. 
     * 
     **/
    
    public abstract Simbolo getSimbolo();

    public abstract void setSimbolo(Simbolo simbolo);

    public abstract int getTipoOp();

    public abstract void setTipoOp(int tipoOp);

    public abstract Nodo getNodoIz();

    public abstract void setNodoIz(Nodo nodoIz);

    public abstract Nodo getNodoDr();

    public abstract void setNodoDr(Nodo nodoDr);
    
    public abstract void realizarAccion();
    
    public abstract Posicion getPosicion();
    
    public abstract void setPosicion(Posicion pos);
    
    public abstract int getAmbito();
    
    public abstract void setAmbito(int ambito);
    
    public abstract ArrayList<Integer> getDimens();
    
    public abstract void setDimens(ArrayList<Nodo> dimens);
    
    public abstract ArrayList<Object> getLista();
            
    public abstract void setLista(ArrayList<Object> lista);
    
    public abstract ArrayList<Nodo> getParams();
    
    public abstract void setParams(ArrayList<Nodo> params);
    
//    public abstract ();
    
    
}

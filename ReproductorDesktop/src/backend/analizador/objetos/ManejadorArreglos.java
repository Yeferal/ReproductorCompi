/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.analizador.objetos;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ManejadorArreglos {
    
    
    public ArrayList<Object> listaObj = null;
    public ArrayList<Integer> listaDim = null;
    
    public boolean verificarDimArreglo(ArrayList<Object> lista){
        boolean b = false;
        listaObj = new ArrayList<>();
        listaDim = new ArrayList<>();
        listaDim.add(lista.size());
        b = recorrerArray(lista);
        System.out.println("Dims: "+listaDim);
        return b;
    }
    
    public boolean recorrerArray(ArrayList<Object> lista){
        
        for (int i = 0; i < lista.size(); i++) {
            if(lista.get(i).getClass().getSimpleName().equals("ArrayList")){
                
                ArrayList<Object> listaAux = (ArrayList<Object>) lista.get(i);
                ArrayList<Object> listaAux1 = (ArrayList<Object>) lista.get(0);
                if(i==0){
                    listaDim.add(listaAux1.size());
                }
                System.out.println("Comparacion: "+listaAux.size()+"=="+listaAux1.size());
                if(listaAux.size()==listaAux1.size()){
                    recorrerArray(listaAux);
                }else{
                    return false;
                }
            }else{
                if(i==0){
                    //listaDim.add(lista.size());
                    //System.out.println("Datos: "+lista);
                }
                if(lista.size()==listaDim.get(listaDim.size()-1)){
                    //listaObj.add(lista.get(i));
                }else{
                    return false;
                }
//                ArrayList<Object> listaAux = (ArrayList<Object>) lista.get(i);
//                ArrayList<Object> listaAux1 = (ArrayList<Object>) lista.get(0);
//                if(listaAux.size()==listaAux1.size()){
                    listaObj.add(lista.get(i));
//                }else{
//                    return false;
//                }
            }
        }
        return true;
    }
    
    public boolean compararDims(ArrayList<Integer> dims){
        if(listaDim!=null){
            if(listaDim.size()==dims.size()){
                for (int i = 0; i < dims.size(); i++) {
                    if(!(dims.get(i)==listaDim.get(i))){
                        return false;
                    }
                }
            }else{
                return false;
            }
        }else{
            return false;
        }
        return true;
    }
    
}

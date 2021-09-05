
package backend.analizador.objetos;

import java.util.ArrayList;

public class Arreglo {
    /*
    5. ARRAY_ENTERO
    6. ARRAY_DOBLE
    7. ARRAY_CARACTER
    8. ARRAY_CADENA
    */
    
    private Object[] arreglo;
    private String tipoArreglo;
    private int tamanio;
    private ArrayList<Integer> dimArreglo;
    
    public Arreglo(ArrayList<Integer> dimArreglo) {
        this.dimArreglo = dimArreglo;
        tamanio = 0;
    }

    public Arreglo(String tipoArreglo) {
        this.tipoArreglo = tipoArreglo;
    }

    public Arreglo(String tipoArreglo, ArrayList<Integer> dimArreglo) {
        this.tipoArreglo = tipoArreglo;
        this.dimArreglo = dimArreglo;
        generarTmArreglo();
    }
    
    public void generarTmArreglo(){
        if(dimArreglo!=null){
            tamanio = getTotalPosiciones(dimArreglo);
            arreglo = new Object[tamanio];
        }
        
    }
    
    
    public int getTotalPosiciones(ArrayList<Integer> dimension){
        int total=0;
        for (int i = 0; i < dimension.size(); i++) {
            if(i==0){
                
                total = dimension.get(i);
                System.out.print(total);
            }else{
                total = total*dimension.get(i);
                System.out.print(","+total);
            }
        }
        return total;
    }
    
    public int getPosicion(ArrayList<Integer> dimension){
        if (dimension.size()==dimArreglo.size()) {
            return encontrarNumPosicionesArreglo(dimension, dimension.size()-1, dimArreglo.size()-1);
        }
        System.out.println("Error de tamanios");
        return 0;
    }
    
    public int encontrarNumPosicionesArreglo(ArrayList<Integer> dimension, int pos, int posDim){
        
        if(pos == 0){
            System.out.println("x ="+dimension.get(pos));
            return dimension.get(pos);
        }else if(pos == 1){
            int dimX = dimArreglo.get(0);
            int y = dimension.get(1);
            int x = dimension.get(0);
            System.out.println(y+"*"+dimX+"+"+x+" = "+(y*dimX+x));
            return y*dimX+x;
        }else{
            if(pos>1){
                int val = encontrarNumPosicionesArreglo(dimension, pos-1, posDim-1);
                int multiplo = getMultiplo(pos);
                int n = getMultiploD(dimension, pos);
                int valA = (multiplo)*(n);
                System.out.println(val+"+("+multiplo+"*"+n+") = "+ val+"+"+valA+" = "+(val+valA));
                return val+valA;
            }
        }
        
        return 0;
    }
    
    public int getMultiplo(int pos){
        int total = 0;
        for (int i = 0; i < pos; i++) {
            if(i==0){
                total = dimArreglo.get(i);
            }else{
                total = total*dimArreglo.get(i);
            }
        }
        return total;
    }
    
    public int getMultiploD(ArrayList<Integer> dimension, int pos){
        int total = 0;
        if(pos == 2){
            return dimension.get(2);
        }else if(dimension.size()>2){
//            for (int i = 3; i <= pos; i++) {
//                if(i==3){
//                    total = dimension.get(i);
//                }else{
//                    total = total*dimension.get(i);
//                }
//            }
            total = total = dimension.get(pos);
        }
        return total;
    }
    
    public boolean llenarArreglo(ArrayList<Object> listado){
        for (int i = 0; i < arreglo.length; i++) {
            Simbolo s = (Simbolo)listado.get(i);
            if(tipoArreglo.equals(s.getTipo())){
                arreglo[i] = s.getObjeto();
            }else{
                return false;
            }
        }
        return true;
    }
}

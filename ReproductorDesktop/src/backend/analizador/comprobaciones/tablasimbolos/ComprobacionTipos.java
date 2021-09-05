
package backend.analizador.comprobaciones.tablasimbolos;

import backend.analizador.objetos.Simbolo;

public class ComprobacionTipos {
        
    /*
    Jerarquia de tipos numericos
    0. doble
    1. entero
    2. caracter
    3. boolean
    */
    
    
    /*
    ENTERO = (ENTERO,BOOLEAN,DOBLE,CARACTER) -> ENTERO
    CADENA = (ENTERO,CADENA,BOOLEAN,DOBLE,CARACTER)) -> CADENA
    BOOLEAN = (BOOLEAN) -> BOOLEAN
    DOBLE = (ENTERO,DOBLE) -> DOBLE
    CARACTER = (ENTERO,CARACTER) -> CARACTER
    */
    public String getTipoDato(String var1, String var2){
        if ((var1.equals("ENTERO")) && (var2.equals("ENTERO") || var2.equals("BOOLEAN") || var2.equals("DOBLE") || var2.equals("CARACTER"))) {
            return "ENTERO";
        }else if ((var1.equals("CADENA"))){
            return "CADENA";
        }else if ((var1.equals("BOOLEAN") && var2.equals("BOOLEAN"))) {
            return "BOOLEAN";
        }else if ((var1.equals("DOBLE")) && (var2.equals("ENTERO") || var2.equals("DOBLE"))) {
            return "DOBLE";
        }else if ((var1.equals("CARACTER")) && (var2.equals("ENTERO") || var2.equals("CARACTER"))) {
            return "CARACTER";
        }
        return null;
    }
    
    public Object convertir(Object o,String tipo){
        switch(tipo){
            case "ENTERO":
                return convertirAEntero(o, tipo);
            case "CADENA":
                return convertirACadena(o, tipo);
            case "BOOLEAN":
                return convertirABoolean(o, tipo);
            case "DOBLE":
                return convertirADoble(o, tipo);
            case "CARACTER":
                return convertirACaracter(o, tipo);
        }
        return null;
    }
    
    public Object convertirAEntero(Object objeto,String tipo){
        try {
            switch(tipo){
                case "ENTERO":
                    int entero = (Integer) objeto;
                    return entero;
                case "BOOLEAN":
                    if((int)objeto==0 || !(boolean)objeto){
                        int entero1 = 0;
                        return entero1;
                    }else if((int)objeto==1 || (boolean)objeto){
                        int entero1 = 1;
                        return entero1;
                    }
                    return 0;
                case "DOBLE":
                    int entero2 = (Integer)(objeto);
                    return entero2;
                case "CARACTER":
                    int entero3 = (int)objeto.toString().charAt(0);
                    return entero3;
            }
        } catch (ClassCastException e) {
            return 0;
        }
        return 0;
    }
    
    public Object convertirACadena(Object objeto,String tipo){
        String cadena = "";
        try {
            switch(tipo){
                case "ENTERO":
                    cadena = objeto+"";
                    break;
                case "BOOLEAN":
                    if((int)objeto==0 || !(boolean)objeto){
                        cadena = false+"";
                    }else if((int)objeto==1 || (boolean)objeto){
                        cadena = true+"";
                    }
                    break;
                case "DOBLE":
                    cadena = objeto+"";
                    break;
                case "CARACTER":
                    cadena = objeto.toString().charAt(0)+"";
                    break;
                case "CADENA":
                    cadena = objeto+"";
                    break;
            }
        } catch (ClassCastException e) {
            return cadena;
        }
        return cadena;
    }
    
    public Object convertirABoolean(Object objeto,String tipo){
        boolean bolean = false;
        try {
            switch(tipo){
                case "ENTERO":
                    if((int)objeto==1){
                        bolean = true;
                    }else if((int)objeto==0){
                        bolean = false;
                    }
                    break;
                case "BOOLEAN":
                    if((int)objeto==0 || !(boolean)objeto){
                        bolean = false;
                    }else if((int)objeto==1 || (boolean)objeto){
                        bolean = true;
                    }
                    break;
            }
        } catch (ClassCastException e) {
            return bolean;
        }
        return bolean;
    }
    
    public Object convertirADoble(Object objeto,String tipo){
        try {
            switch(tipo){
                case "ENTERO":
                    double doble = (double) objeto;
                    return doble;
                case "DOBLE":
                    double doble1 = (double) objeto;
                    return doble1;
            }
        } catch (ClassCastException e) {
            return 0.0;
        }
        return 0.0;
    }
    
    public Object convertirACaracter(Object objeto,String tipo){
        String charS = "";
        try {
            switch(tipo){
                case "ENTERO":
                    char caracter = (char) objeto;
                    charS = caracter+"";
                    break;
                case "CARACTER":
                    char caracter1 = objeto.toString().charAt(0);
                    charS = caracter1+"";
            }
        } catch (ClassCastException e) {
            return charS;
        }
        return charS;
    }
    
    public boolean opRacionalIgualIgual(Simbolo s1, Simbolo s2){
        boolean resultado = false;
        if(s1.getTipo().equals(s2.getTipo())){
            return (s1.getObjeto().equals(s2.getObjeto()));
        }else if((s1.getTipo().equals("ENTERO") && s1.getTipo().equals("CARACTER")) && s1.getObjeto().equals(s2.getObjeto())){
            return s1.getObjeto().equals((int)(s2.getObjeto().toString().charAt(0)));
        }else if((s1.getTipo().equals("CARACTER") && s1.getTipo().equals("ENTERO")) && s1.getObjeto().equals(s2.getObjeto())){
            return s2.getObjeto().equals((int)(s1.getObjeto().toString().charAt(0)));
        }else if((s1.getTipo().equals("ENTERO") && s1.getTipo().equals("DOBLE")) && s1.getObjeto().equals(s2.getObjeto())){
            return s1.getObjeto().equals((int)((double)s2.getObjeto()));
        }else if((s1.getTipo().equals("DOBLE") && s1.getTipo().equals("ENTERO")) && s1.getObjeto().equals(s2.getObjeto())){
            return s2.getObjeto().equals((int)((double)s1.getObjeto()));
        }
        return resultado;
    }
    
    public boolean opRacionalDiferente(Simbolo s1, Simbolo s2){
        boolean resultado = true;
        if(s1.getTipo().equals(s2.getTipo())){
            return !(s1.getObjeto().equals(s2.getObjeto()));
        }else if((s1.getTipo().equals("ENTERO") && s1.getTipo().equals("CARACTER")) && s1.getObjeto().equals(s2.getObjeto())){
            return !(s1.getObjeto().equals((int)(s2.getObjeto().toString().charAt(0))));
        }else if((s1.getTipo().equals("CARACTER") && s1.getTipo().equals("ENTERO")) && s1.getObjeto().equals(s2.getObjeto())){
            return !(s2.getObjeto().equals((int)(s1.getObjeto().toString().charAt(0))));
        }else if((s1.getTipo().equals("ENTERO") && s1.getTipo().equals("DOBLE")) && s1.getObjeto().equals(s2.getObjeto())){
            return !(s1.getObjeto().equals((int)((double)s2.getObjeto())));
        }else if((s1.getTipo().equals("DOBLE") && s1.getTipo().equals("ENTERO")) && s1.getObjeto().equals(s2.getObjeto())){
            return !(s2.getObjeto().equals((int)((double)s1.getObjeto())));
        }
        return resultado;
    }
    
    public boolean opRacionalMenorQ(Simbolo s1, Simbolo s2){
        boolean resultado = false;
        if(!s1.getTipo().equals("CADENA") && !s2.getTipo().equals("CADENA")){
            if(s1.getTipo().equals("CARACTER") && s2.getTipo().equals("CARACTER")){
                return ((double)(s1.getObjeto().toString().charAt(0))<(double)(s2.getObjeto().toString().charAt(0)));
            }else if(s1.getTipo().equals("CARACTER")){
                return ((double)(s1.getObjeto().toString().charAt(0))<(double)(s2.getObjeto()));
            }else if(s2.getTipo().equals("CARACTER")){
                return ((double)(s1.getObjeto())<(double)(s2.getObjeto().toString().charAt(0)));
            }else{
                return ((double)(s1.getObjeto())<(double)(s1.getObjeto()));
            }
        }
        return resultado;
    }
    
    public boolean opRacionalMayorQ(Simbolo s1, Simbolo s2){
        boolean resultado = false;
        if(!s1.getTipo().equals("CADENA") && !s2.getTipo().equals("CADENA")){
            if(s1.getTipo().equals("CARACTER") && s2.getTipo().equals("CARACTER")){
                return ((double)(s1.getObjeto().toString().charAt(0))>(double)(s2.getObjeto().toString().charAt(0)));
            }else if(s1.getTipo().equals("CARACTER")){
                return ((double)(s1.getObjeto().toString().charAt(0))>(double)(s2.getObjeto()));
            }else if(s2.getTipo().equals("CARACTER")){
                return ((double)(s1.getObjeto())>(double)(s2.getObjeto().toString().charAt(0)));
            }else{
                return ((double)(s1.getObjeto())>(double)(s1.getObjeto()));
            }
        }
        return resultado;
    }
    
    public boolean opRacionalMenorQIgual(Simbolo s1, Simbolo s2){
        boolean resultado = false;
        if(!s1.getTipo().equals("CADENA") && !s2.getTipo().equals("CADENA")){
            if(s1.getTipo().equals("CARACTER") && s2.getTipo().equals("CARACTER")){
                return ((double)(s1.getObjeto().toString().charAt(0))<=(double)(s2.getObjeto().toString().charAt(0)));
            }else if(s1.getTipo().equals("CARACTER")){
                return ((double)(s1.getObjeto().toString().charAt(0))<=(double)(s2.getObjeto()));
            }else if(s2.getTipo().equals("CARACTER")){
                return ((double)(s1.getObjeto())<=(double)(s2.getObjeto().toString().charAt(0)));
            }else{
                return ((double)(s1.getObjeto())<=(double)(s1.getObjeto()));
            }
        }
        return resultado;
    }
    
    public boolean opRacionalMayorQIgual(Simbolo s1, Simbolo s2){
        boolean resultado = false;
        if(!s1.getTipo().equals("CADENA") && !s2.getTipo().equals("CADENA")){
            if(s1.getTipo().equals("CARACTER") && s2.getTipo().equals("CARACTER")){
                return ((double)(s1.getObjeto().toString().charAt(0))>=(double)(s2.getObjeto().toString().charAt(0)));
            }else if(s1.getTipo().equals("CARACTER")){
                return ((double)(s1.getObjeto().toString().charAt(0))>=(double)(s2.getObjeto()));
            }else if(s2.getTipo().equals("CARACTER")){
                return ((double)(s1.getObjeto())>=(double)(s2.getObjeto().toString().charAt(0)));
            }else{
                return ((double)(s1.getObjeto())>=(double)(s1.getObjeto()));
            }
        }
        return resultado;
    }
    
    public boolean opLogicoOR(Simbolo s1, Simbolo s2){
        if(s1.getTipo().equals("BOOLEAN") && s2.getTipo().equals("BOOLEAN")){
            return ((boolean)s1.getObjeto() || (boolean)s2.getObjeto());
        }
        return false;
    }
    public boolean opLogicoNOR(Simbolo s1, Simbolo s2){
        if(s1.getTipo().equals("BOOLEAN") && s2.getTipo().equals("BOOLEAN")){
            return !((boolean)s1.getObjeto() || (boolean)s2.getObjeto());
        }
        return !false;
    }
    public boolean opLogicoXOR(Simbolo s1, Simbolo s2){
        if(s1.getTipo().equals("BOOLEAN") && s2.getTipo().equals("BOOLEAN")){
            return (((boolean)s1.getObjeto() && !(boolean)s2.getObjeto()) || (!(boolean)s1.getObjeto() && (boolean)s2.getObjeto()));
        }
        return false;
    }
    public boolean opLogicoAND(Simbolo s1, Simbolo s2){
        if(s1.getTipo().equals("BOOLEAN") && s2.getTipo().equals("BOOLEAN")){
            return ((boolean)s1.getObjeto() && (boolean)s2.getObjeto());
        }
        return false;
    }
    public boolean opLogicoNAND(Simbolo s1, Simbolo s2){
        if(s1.getTipo().equals("BOOLEAN") && s2.getTipo().equals("BOOLEAN")){
            return !((boolean)s1.getObjeto() && (boolean)s2.getObjeto());
        }
        return !false;
    }
    public boolean opLogicoNOT(Simbolo s1){
        if(s1.getTipo().equals("BOOLEAN")){
            return !((boolean)s1.getObjeto());
        }
        return !false;
    }
//    public boolean opLogicoAND(Simbolo s1, Simbolo s2){
//        if(s1.getTipo().equals("BOOLEAN") && s2.getTipo().equals("BOOLEAN")){
//            return ((boolean)s1.getObjeto() && (boolean)s2.getObjeto());
//        }
//        return false;
//    }
    
    
}

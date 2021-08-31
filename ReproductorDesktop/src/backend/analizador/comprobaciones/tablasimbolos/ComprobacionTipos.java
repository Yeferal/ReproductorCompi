
package backend.analizador.comprobaciones.tablasimbolos;

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
    
}

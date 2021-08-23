/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.archivos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LENOVO-PC
 */
public class Archivo {
    
    public String leerArchivo(String ruta){
        String texto = "";
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        

      try {
         archivo = new File (ruta);
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);

         String linea;
         while((linea=br.readLine())!=null)
            texto+=linea+"\n";
      }
      catch(Exception e){
         e.printStackTrace();
      }finally{
         try{                    
            if( null != fr ){   
               fr.close();     
            }                  
         }catch (Exception e2){ 
            e2.printStackTrace();
         }
      } 
        //System.out.println(texto);
        return texto;
    }
    
    public void escribir(String texto, String ruta){
        
        
        File file = new File(ruta);
        
        try {
            if(!file.exists()){
                file.createNewFile();
            }
            
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(texto);
            bw.close();
        } catch (IOException ex) {
                Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    public void crearArchivo(String ruta,String texto){
        File file = new File(ruta);
        
        try {
            if(!file.exists()){
                file.createNewFile();
            }
            
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(texto);
            bw.close();
        } catch (IOException ex) {
                Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}

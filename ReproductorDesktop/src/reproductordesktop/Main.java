/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reproductordesktop;

import backend.analizador.objetos.Arreglo;
import backend.analizador.objetos.ManejadorArreglos;
import frontend.gui.VentanaPrincipal;
import frontend.gui.editor.AnalizadorLexicoCode;
import frontend.gui.editor.AnalizadorSintacticoCode;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yeferal
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        String dd = "'0'";
        String ds = dd.substring(1, dd.length()-1);
        System.out.println("ds: "+ds);
        char c = 48;
        int r = ds.charAt(0);
        System.out.println("char: "+(int)r);
        Object c1 = 48;
        Object c2 = 48.5;
        boolean bl = false;
        double df = 45.3;
        System.out.println("Resultado: "+(int)df);
        
        //ManejadorArreglos manejadorArreglos = new ManejadorArreglos();
        //System.out.println("Res: "+manejadorArreglos.verificarDim(lista));

        
//        String myStr = "\t\tHello planet earth, you are a great planet.";
//        System.out.println(myStr.lastIndexOf("\t"));
//        String myStr1 = "\t\t";
//        System.out.println(myStr1.length());
//        ArrayList<Integer> lista = new ArrayList<>();
//        lista.add(5);
//        lista.add(6);
//        lista.add(7);
//        lista.add(5);
//        lista.add(6);
//        lista.add(7);
//        lista.add(5);
//        lista.add(6);
//        lista.add(7);
//        lista.add(5);
//        lista.add(6);
//        lista.add(7);
//        Arreglo arre = new Arreglo(lista);
//        
//        ArrayList<Integer> listaPos = new ArrayList<>();
//        listaPos.add(4);
//        listaPos.add(5);
//        listaPos.add(6);
//        listaPos.add(4);
//        listaPos.add(5);
//        listaPos.add(6);
//        listaPos.add(4);
//        listaPos.add(5);
//        listaPos.add(6);
//        listaPos.add(4);
//        listaPos.add(5);
//        listaPos.add(6);
//
//        System.out.println("Tamanio: "+arre.getTotalPosiciones(lista)+"\n");
//        System.out.println("posicion: "+arre.getPosicion(listaPos));
        
        VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
        ventanaPrincipal.setVisible(true);
    }
    
}

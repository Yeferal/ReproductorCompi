/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reproductordesktop;

import backend.analizador.ast.Nodo;
import backend.analizador.ast.NodoOpAritmetico;
import backend.analizador.ast.NodoS;
import backend.analizador.comprobaciones.tablasimbolos.ComprobacionTipos;
import backend.analizador.objetos.Arreglo;
import backend.analizador.objetos.ManejadorArreglos;
import frontend.gui.VentanaPrincipal;
import frontend.gui.editor.AnalizadorLexicoCode;
import frontend.gui.editor.AnalizadorSintacticoCode;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfugue.pattern.Pattern;

import org.jfugue.player.Player;

/**
 *
 * @author yeferal
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
//        Player player = new Player();
//        Pattern p = new Pattern("C").setTempo(100);
//        player.play(p,p);
        
        VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
        ventanaPrincipal.setVisible(true);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reproductordesktop;

import frontend.gui.VentanaPrincipal;
import frontend.gui.editor.AnalizadorLexicoCode;
import frontend.gui.editor.AnalizadorSintacticoCode;
import java.io.StringReader;
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
        /*String texto = "\n\n>>comenñtario de una sola línea\n" +
"<- comentañrio de varias líneas\n" +
"Segunda línea\n" +
"Tercera línea\n" +
"...\n" +
"->";
        
        AnalizadorLexicoCode analizadorLexicoCode = new AnalizadorLexicoCode(new StringReader(texto));
        AnalizadorSintacticoCode analizadorSintacticoCode = new AnalizadorSintacticoCode(analizadorLexicoCode);
        try {
            analizadorSintacticoCode.parse();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        
        VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
        ventanaPrincipal.setVisible(true);
    }
    
}

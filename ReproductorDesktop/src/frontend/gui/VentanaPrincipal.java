/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.gui;

import backend.archivos.Archivo;
<<<<<<< HEAD
import backend.analizador.AnalizadorLexico;
import backend.analizador.AnalizadorSintactico;
import backend.analizador.ErrorLexico;
=======
>>>>>>> 6b9ab068299e58c313363f528f005409a1e41633
import frontend.gui.editor.AnalizadorLexicoCode;
import frontend.gui.editor.AnalizadorSintacticoCode;
import frontend.gui.editor.LineasText;
import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

/**
 *
 * @author yeferal
 */
public class VentanaPrincipal extends javax.swing.JFrame {

    public LineasText lines;
    private Archivo archivo;
    private int posicionCursor;
    
    public VentanaPrincipal() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setSize(1000, 500);
        lines =new LineasText();
        archivo = new Archivo();
        panelCodigo.add(lines,BorderLayout.WEST);
        panelCodigo.add(lines.scrollPane,BorderLayout.CENTER);
        
        posicionPuntero();
        
        lines.pane.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                paneCodeKeyReleased(evt);
            }
        });
        
        //lines.pane.setText(archivo.leerArchivo("./entrada_pista.txt"));
        //pintarTexto();
        
        
    }
    
    private void iniciarLIneaText(){
        
    }
    
    public void pintarTexto(){
        String texto = lines.pane.getText();
        AnalizadorLexicoCode analizadorLexicoCode = new AnalizadorLexicoCode(new StringReader(texto));
        analizadorLexicoCode.pintar.insertar(lines.pane.getText());
        lines.pane.setDocument(analizadorLexicoCode.pintar.caja2.getDocument());
        System.out.println(lines.pane.getText());
        //lines.pane.setCaretPosition(posicionCursor-1);
        //posicionPuntero();
        //lines.pane.setText(analizadorLexicoCode.pintar.caja2.getText());
        AnalizadorSintacticoCode analizadorSintacticoCode = new AnalizadorSintacticoCode(analizadorLexicoCode);
        
        try {
            analizadorSintacticoCode.parse();
        } catch (Exception ex) {
            //Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    private void posicionPuntero() {
        lines.pane.addCaretListener(new CaretListener(){
            
            @Override
            public void caretUpdate(CaretEvent e) {
                int pos = e.getDot();
		int fila = 1, columna=0;
		int ultimalinea=-1;
                posicionCursor = 1;
		String text = lines.pane.getText().replaceAll("\r","");
		              System.out.println("POS: "+pos);	
		for(int i=0;i<pos;i++){
//                    System.out.println("\""+text+"\"");
//                    System.out.println("\nTamnio: "+text.length());
//                    System.out.println("INDICE: "+i);
                    try{
                        if(text.charAt(i)==10){
                            fila++;
                            ultimalinea=i;
                        }
                        posicionCursor ++;
                    }catch(Exception ex){
                        lines.pane.repaint();
                        //ex.printStackTrace();
//                        fila++;
//                        ultimalinea=i;
//                        posicionCursor ++;
                        //lines.pane.setCaretPosition(posicionCursor-1);
                        //lines.pane.removeCaretListener(this);
                        //posicionPuntero();
                    }
                }		
		columna=pos-ultimalinea;                
                noFila.setText(fila +"");
                noCol.setText(columna+"");
                System.out.println("PosicionC: "+posicionCursor);
            }
        });
        
    }
    
    private void paneCodeKeyReleased(java.awt.event.KeyEvent evt){
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            //System.out.println("Hola");
            int fila = 0;
                //JTextArea jta = (JTextArea) textComp;
                //return jta.getLineStartOffset(lineNumber-1);
                fila = posicionCursor;
               // fila = lines.pane.get
                
            pintarTexto();
            System.out.println("Posicion cursor: "+(fila-1));
            lines.pane.setCaretPosition(fila-1);
        }
        else if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            //System.out.println("Hola");
            int fila = 0;
                //JTextArea jta = (JTextArea) textComp;
                //return jta.getLineStartOffset(lineNumber-1);
                fila = posicionCursor;
               // fila = lines.pane.get
                
            pintarTexto();
            //lines.pane.setCaretPosition(fila-1);
        }
    }

    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        panelCentral = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        panelBiblioteca = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        panelEditorCodigo = new javax.swing.JPanel();
        paneBotEditor = new javax.swing.JPanel();
        panelCentralEditor = new javax.swing.JPanel();
        labelFIla = new javax.swing.JLabel();
        noFila = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        noCol = new javax.swing.JLabel();
        panelCodigo = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuArchivo = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        menuReporte = new javax.swing.JMenu();
        menuAyuda = new javax.swing.JMenu();

        jMenu1.setText("File");
        jMenuBar2.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar2.add(jMenu2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelBiblioteca.setLayout(new java.awt.BorderLayout());

        jTextPane1.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        jTextPane1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextPane1KeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTextPane1);

        panelBiblioteca.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Biblioteca", panelBiblioteca);

        panelEditorCodigo.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout paneBotEditorLayout = new javax.swing.GroupLayout(paneBotEditor);
        paneBotEditor.setLayout(paneBotEditorLayout);
        paneBotEditorLayout.setHorizontalGroup(
            paneBotEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1137, Short.MAX_VALUE)
        );
        paneBotEditorLayout.setVerticalGroup(
            paneBotEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        panelEditorCodigo.add(paneBotEditor, java.awt.BorderLayout.PAGE_END);

        labelFIla.setText("FILA: ");

        noFila.setText("0");

        jLabel2.setText("COLUMNA:");

        noCol.setText("0");

        panelCodigo.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout panelCentralEditorLayout = new javax.swing.GroupLayout(panelCentralEditor);
        panelCentralEditor.setLayout(panelCentralEditorLayout);
        panelCentralEditorLayout.setHorizontalGroup(
            panelCentralEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCentralEditorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCentralEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelCentralEditorLayout.createSequentialGroup()
                        .addComponent(labelFIla)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(noFila, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(noCol, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 855, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelCentralEditorLayout.setVerticalGroup(
            panelCentralEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCentralEditorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelCentralEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelFIla)
                    .addComponent(noFila)
                    .addComponent(jLabel2)
                    .addComponent(noCol))
                .addContainerGap())
        );

        panelEditorCodigo.add(panelCentralEditor, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Editor de Codigo", panelEditorCodigo);

        javax.swing.GroupLayout panelCentralLayout = new javax.swing.GroupLayout(panelCentral);
        panelCentral.setLayout(panelCentralLayout);
        panelCentralLayout.setHorizontalGroup(
            panelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCentralLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        panelCentralLayout.setVerticalGroup(
            panelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCentralLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        getContentPane().add(panelCentral, java.awt.BorderLayout.CENTER);

        menuArchivo.setText("Archivo");

        jMenuItem2.setText("Nuevo Archivo");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        menuArchivo.add(jMenuItem2);

        jMenuItem1.setText("Abrir Archivo");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        menuArchivo.add(jMenuItem1);

        jMenuBar1.add(menuArchivo);

        menuReporte.setText("Reportes");
        jMenuBar1.add(menuReporte);

        menuAyuda.setText("Ayuda");
        jMenuBar1.add(menuAyuda);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextPane1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextPane1KeyReleased
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            //System.out.println("Hola");
            
        }
    }//GEN-LAST:event_jTextPane1KeyReleased

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        pintarTexto();
        
        
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        String texto = jTextPane1.getText();
        //System.out.println(texto);
        AnalizadorLexico lexico = new AnalizadorLexico(new StringReader(texto));
        lexico.iniciar();
        AnalizadorSintactico sintactico = new AnalizadorSintactico(lexico);
        try {
            sintactico.parse();
        } catch (Exception ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JLabel labelFIla;
    private javax.swing.JMenu menuArchivo;
    private javax.swing.JMenu menuAyuda;
    private javax.swing.JMenu menuReporte;
    private javax.swing.JLabel noCol;
    private javax.swing.JLabel noFila;
    private javax.swing.JPanel paneBotEditor;
    private javax.swing.JPanel panelBiblioteca;
    private javax.swing.JPanel panelCentral;
    private javax.swing.JPanel panelCentralEditor;
    private javax.swing.JPanel panelCodigo;
    private javax.swing.JPanel panelEditorCodigo;
    // End of variables declaration//GEN-END:variables
}

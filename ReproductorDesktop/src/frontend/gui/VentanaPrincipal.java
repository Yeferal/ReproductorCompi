/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.gui;

import backend.archivos.Archivo;
import backend.analizador.AnalizadorLexico;
import backend.analizador.AnalizadorSintactico;
import backend.analizador.ErrorLSS;
import backend.analizador.ast.gramatica.AnalizadorLexicoAST;
import backend.analizador.ast.gramatica.AnalizadorSintacticoAST;
import backend.analizador.comprobaciones.tablasimbolos.AnalizadorLexicoTS;
import backend.analizador.comprobaciones.tablasimbolos.AnalizadorSintacticoTS;
import frontend.gui.editor.AnalizadorLexicoCode;
import frontend.gui.editor.AnalizadorSintacticoCode;
import frontend.gui.editor.LineasText;
import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.table.DefaultTableModel;


public class VentanaPrincipal extends javax.swing.JFrame {

    public LineasText lines;
    private Archivo archivo;
    private int posicionCursor;
    DefaultTableModel model = new DefaultTableModel();
    
    public VentanaPrincipal() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setSize(1200, 700);
        lines =new LineasText();
        archivo = new Archivo();
        panelCodigo.add(lines,BorderLayout.WEST);
        panelCodigo.add(lines.scrollPane,BorderLayout.CENTER);
        //lines.pane
        jTabbedPane1.setSelectedIndex(1);
        posicionPuntero();
        
        lines.pane.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                paneCodeKeyReleased(evt);
            }
        });
        
    }
    
    private void iniciarModelo(){
        model = new DefaultTableModel();
        model.addColumn("Linea");
        model.addColumn("Columna");
        model.addColumn("Tipo");
        model.addColumn("Descripcion");
    }
    
    public void reportarErrores(ArrayList<ErrorLSS> lista){
        
        for (int i = 0; i < lista.size(); i++) {
            System.out.println("Reportado: "+lista.get(i).getLinea()+", "+lista.get(i).getColumna()+", "+lista.get(i).getTipo()+", "+lista.get(i).getDescripcion());
            model.addRow(new Object[]{lista.get(i).getLinea(),lista.get(i).getColumna(), lista.get(i).getTipo(),lista.get(i).getDescripcion()});
        }
    }
    
    public void pintarTexto(){
        String texto = lines.pane.getText();
        AnalizadorLexicoCode analizadorLexicoCode = new AnalizadorLexicoCode(new StringReader(texto));
        analizadorLexicoCode.pintar.insertar(lines.pane.getText());
        lines.pane.setDocument(analizadorLexicoCode.pintar.caja2.getDocument());
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
		//System.out.println("POS: "+pos);	
		for(int i=0;i<pos;i++){
                    try{
                        if(text.charAt(i)==10){
                            fila++;
                            ultimalinea=i;
                        }
                        posicionCursor ++;
                    }catch(Exception ex){
                        //lines.pane.repaint();
                        //ex.printStackTrace();
                    }
                }		
		columna=pos-ultimalinea;                
                noFila.setText(fila +"");
                noCol.setText(columna+"");
                //System.out.println("PosicionC: "+posicionCursor);
            }
        });
        
    }
    
    private void paneCodeKeyReleased(java.awt.event.KeyEvent evt){
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            int fila = 0;
            fila = posicionCursor;
            pintarTexto();
            lines.pane.setCaretPosition(fila-1);
        }
        else if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            int fila = 0;
            fila = posicionCursor;
            pintarTexto();
            lines.pane.setCaretPosition(fila-1);
        }
    }

    public void compilar(){
        String texto = lines.pane.getText();
        AnalizadorLexico lexico = new AnalizadorLexico(new StringReader(texto));
        AnalizadorLexicoTS lexico2 = new AnalizadorLexicoTS(new StringReader(texto));
        lexico.iniciar();
        lexico2.iniciar();
        AnalizadorSintactico sintactico = new AnalizadorSintactico(lexico);
        AnalizadorSintacticoTS sintacticoTS = new AnalizadorSintacticoTS(lexico2);
        try {
            sintactico.parse();
            sintacticoTS.parse();
            if(lexico.listaErrores.size()>0 || sintactico.listaErrores.size()>0 || sintacticoTS.listaErrores.size()>0){
                iniciarModelo();
                reportarErrores(lexico.listaErrores);
                reportarErrores(sintactico.listaErrores);
                reportarErrores(sintacticoTS.listaErrores);
                tablaErrores.setModel(model);
                JOptionPane.showMessageDialog(null, "La pista contiene errores");
            }else{
                JOptionPane.showMessageDialog(null, "No se detectaron errores en la gramatica");
                iniciarModelo();
                tablaErrores.setModel(model);
                compilarAST();
                
            }
        } catch (Exception ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }
    
    public void compilarAST(){
        String texto = lines.pane.getText();
        AnalizadorLexicoAST lexicoAST = new AnalizadorLexicoAST(new StringReader(texto));
        lexicoAST.iniciar();
        AnalizadorSintacticoAST sintacticoAST = new AnalizadorSintacticoAST(lexicoAST);
        try {
            sintacticoAST.parse();
            //JOptionPane.showMessageDialog(null, "La pista no contiene errores");
        } catch (Exception ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            
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
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listCanciones = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        bntCrear = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnReproducir = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listListasReproduccion = new javax.swing.JList<>();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        listListaReproduccion = new javax.swing.JList<>();
        jLabel4 = new javax.swing.JLabel();
        btnModificar = new javax.swing.JButton();
        btnEliminarLista = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        slider = new javax.swing.JSlider();
        labelT2 = new javax.swing.JLabel();
        labelT1 = new javax.swing.JLabel();
        panelFrecuencia = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        btnPlay = new javax.swing.JButton();
        btnPause = new javax.swing.JButton();
        btnStop = new javax.swing.JButton();
        checkBoxRepetir = new javax.swing.JCheckBox();
        panelEditorCodigo = new javax.swing.JPanel();
        paneBotEditor = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaErrores = new javax.swing.JTable();
        panelCentralEditor = new javax.swing.JPanel();
        labelFIla = new javax.swing.JLabel();
        noFila = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        noCol = new javax.swing.JLabel();
        panelCodigo = new javax.swing.JPanel();
        btnCompilar = new javax.swing.JButton();
        btnReproducirEditor = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuArchivo = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        menuReporte = new javax.swing.JMenu();
        menuAyuda = new javax.swing.JMenu();

        jMenu1.setText("File");
        jMenuBar2.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar2.add(jMenu2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelCentral.setBackground(new java.awt.Color(66, 142, 225));

        panelBiblioteca.setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        jPanel3.setLayout(new java.awt.GridLayout(1, 0));

        jPanel5.setBackground(new java.awt.Color(66, 142, 225));

        jScrollPane1.setViewportView(listCanciones);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("CANCIONES");

        bntCrear.setText("Crear");
        bntCrear.setToolTipText("Crear Pista");
        bntCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntCrearActionPerformed(evt);
            }
        });

        btnEditar.setText("Editar");

        btnReproducir.setText("Reproducir");

        btnEliminar.setText("Eliminar");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnReproducir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addComponent(bntCrear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEditar, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminar)
                        .addGap(4, 4, 4)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnReproducir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bntCrear)
                    .addComponent(btnEditar)
                    .addComponent(btnEliminar))
                .addContainerGap())
        );

        jPanel3.add(jPanel5);

        jPanel6.setBackground(new java.awt.Color(66, 142, 225));

        jScrollPane2.setViewportView(listListasReproduccion);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Listas de Reproduccion");

        jScrollPane3.setViewportView(listListaReproduccion);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Lista de Reproduccion");

        btnModificar.setText("Modificar");

        btnEliminarLista.setText("Eliminar");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                        .addComponent(btnEliminarLista, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModificar)
                    .addComponent(btnEliminarLista))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.add(jPanel6);

        jPanel1.add(jPanel3);

        jPanel4.setBackground(new java.awt.Color(66, 142, 225));

        jLabel5.setText("4000HZ");

        jLabel6.setText("0 HZ");

        slider.setBackground(new java.awt.Color(66, 142, 225));
        slider.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        labelT2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelT2.setText("0");

        labelT1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelT1.setText("0");

        javax.swing.GroupLayout panelFrecuenciaLayout = new javax.swing.GroupLayout(panelFrecuencia);
        panelFrecuencia.setLayout(panelFrecuenciaLayout);
        panelFrecuenciaLayout.setHorizontalGroup(
            panelFrecuenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelFrecuenciaLayout.setVerticalGroup(
            panelFrecuenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel9.setText("60s");

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("0s");

        jPanel8.setBackground(new java.awt.Color(66, 142, 225));

        btnPlay.setBackground(new java.awt.Color(66, 142, 225));
        btnPlay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/frontend/gui/pictures/btnPlay.png"))); // NOI18N
        btnPlay.setFocusPainted(false);
        btnPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlayActionPerformed(evt);
            }
        });

        btnPause.setBackground(new java.awt.Color(66, 142, 225));
        btnPause.setIcon(new javax.swing.ImageIcon(getClass().getResource("/frontend/gui/pictures/btnPause.png"))); // NOI18N
        btnPause.setFocusPainted(false);
        btnPause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPauseActionPerformed(evt);
            }
        });

        btnStop.setBackground(new java.awt.Color(66, 142, 225));
        btnStop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/frontend/gui/pictures/btnStop.png"))); // NOI18N
        btnStop.setFocusPainted(false);
        btnStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStopActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(btnPause, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnStop, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPause, javax.swing.GroupLayout.PREFERRED_SIZE, 103, Short.MAX_VALUE)
                    .addComponent(btnPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 103, Short.MAX_VALUE)
                    .addComponent(btnStop, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 103, Short.MAX_VALUE))
                .addContainerGap())
        );

        checkBoxRepetir.setBackground(new java.awt.Color(66, 142, 225));
        checkBoxRepetir.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        checkBoxRepetir.setText("Repetir");
        checkBoxRepetir.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        checkBoxRepetir.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(labelT1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelFrecuencia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(slider, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelT2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(checkBoxRepetir, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 378, Short.MAX_VALUE)
                        .addComponent(jLabel6))
                    .addComponent(panelFrecuencia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(slider, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(labelT1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(checkBoxRepetir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelT2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel1.add(jPanel4);

        panelBiblioteca.add(jPanel1, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Biblioteca", panelBiblioteca);

        panelEditorCodigo.setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(66, 142, 225));
        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        tablaErrores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane4.setViewportView(tablaErrores);

        jPanel2.add(jScrollPane4);

        javax.swing.GroupLayout paneBotEditorLayout = new javax.swing.GroupLayout(paneBotEditor);
        paneBotEditor.setLayout(paneBotEditorLayout);
        paneBotEditorLayout.setHorizontalGroup(
            paneBotEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1260, Short.MAX_VALUE)
        );
        paneBotEditorLayout.setVerticalGroup(
            paneBotEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );

        panelEditorCodigo.add(paneBotEditor, java.awt.BorderLayout.PAGE_END);

        panelCentralEditor.setBackground(new java.awt.Color(66, 142, 225));

        labelFIla.setText("FILA: ");

        noFila.setText("0");

        jLabel2.setText("COLUMNA:");

        noCol.setText("0");

        panelCodigo.setLayout(new java.awt.BorderLayout());

        btnCompilar.setText("Compilar");
        btnCompilar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompilarActionPerformed(evt);
            }
        });

        btnReproducirEditor.setText("Reproducir Pista");

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 725, Short.MAX_VALUE)
                        .addComponent(btnReproducirEditor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCompilar)))
                .addContainerGap())
        );
        panelCentralEditorLayout.setVerticalGroup(
            panelCentralEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCentralEditorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelCentralEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelFIla)
                    .addComponent(noFila)
                    .addComponent(jLabel2)
                    .addComponent(noCol)
                    .addComponent(btnCompilar)
                    .addComponent(btnReproducirEditor))
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

        jMenu3.setText("Pista");

        jMenuItem3.setText("Crear Pista");
        jMenu3.add(jMenuItem3);

        jMenuItem4.setText("Modificar Pista");
        jMenu3.add(jMenuItem4);

        jMenuItem5.setText("Eliminar Pista");
        jMenu3.add(jMenuItem5);

        jMenuItem6.setText("Guardar Pista");
        jMenu3.add(jMenuItem6);

        jMenuItem7.setText("Recuperar Pista");
        jMenu3.add(jMenuItem7);

        menuArchivo.add(jMenu3);

        jMenu4.setText("Lista");

        jMenuItem8.setText("Guardar Lista de Reproduccion");
        jMenu4.add(jMenuItem8);

        jMenuItem9.setText("Recuperar Listas de Reproducción");
        jMenu4.add(jMenuItem9);

        menuArchivo.add(jMenu4);

        jMenuItem2.setText("Nueva Pista");
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

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        //pintarTexto();
        
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        /*String texto = jTextPane1.getText();
        //System.out.println(texto);
        AnalizadorLexico lexico = new AnalizadorLexico(new StringReader(texto));
        lexico.iniciar();
        AnalizadorSintactico sintactico = new AnalizadorSintactico(lexico);
        try {
            sintactico.parse();
        } catch (Exception ex) {
            //Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            
        }*/
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void bntCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntCrearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bntCrearActionPerformed

    private void btnPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPlayActionPerformed

    private void btnPauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPauseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPauseActionPerformed

    private void btnStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStopActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnStopActionPerformed

    private void btnCompilarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompilarActionPerformed
        compilar();
    }//GEN-LAST:event_btnCompilarActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntCrear;
    private javax.swing.JButton btnCompilar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnEliminarLista;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnPause;
    private javax.swing.JButton btnPlay;
    private javax.swing.JButton btnReproducir;
    private javax.swing.JButton btnReproducirEditor;
    private javax.swing.JButton btnStop;
    private javax.swing.JCheckBox checkBoxRepetir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labelFIla;
    private javax.swing.JLabel labelT1;
    private javax.swing.JLabel labelT2;
    private javax.swing.JList<String> listCanciones;
    private javax.swing.JList<String> listListaReproduccion;
    private javax.swing.JList<String> listListasReproduccion;
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
    private javax.swing.JPanel panelFrecuencia;
    private javax.swing.JSlider slider;
    private javax.swing.JTable tablaErrores;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

/**
 *
 * @author yeferal
 */
public class VentanaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form VentanaPrincipal
     */
    public VentanaPrincipal() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setSize(1200, 700);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelCentral = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        panelBiblioteca = new javax.swing.JPanel();
        panelEditorCodigo = new javax.swing.JPanel();
        paneBotEditor = new javax.swing.JPanel();
        panelCentralEditor = new javax.swing.JPanel();
        scrollPaneEditor = new javax.swing.JScrollPane();
        textPaneEditor = new javax.swing.JTextPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuArchivo = new javax.swing.JMenu();
        menuReporte = new javax.swing.JMenu();
        menuAyuda = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelBiblioteca.setLayout(new java.awt.BorderLayout());
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

        textPaneEditor.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        textPaneEditor.setToolTipText("");
        scrollPaneEditor.setViewportView(textPaneEditor);

        javax.swing.GroupLayout panelCentralEditorLayout = new javax.swing.GroupLayout(panelCentralEditor);
        panelCentralEditor.setLayout(panelCentralEditorLayout);
        panelCentralEditorLayout.setHorizontalGroup(
            panelCentralEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCentralEditorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPaneEditor, javax.swing.GroupLayout.DEFAULT_SIZE, 1113, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelCentralEditorLayout.setVerticalGroup(
            panelCentralEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCentralEditorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPaneEditor, javax.swing.GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
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
        jMenuBar1.add(menuArchivo);

        menuReporte.setText("Reportes");
        jMenuBar1.add(menuReporte);

        menuAyuda.setText("Ayuda");
        jMenuBar1.add(menuAyuda);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JMenu menuArchivo;
    private javax.swing.JMenu menuAyuda;
    private javax.swing.JMenu menuReporte;
    private javax.swing.JPanel paneBotEditor;
    private javax.swing.JPanel panelBiblioteca;
    private javax.swing.JPanel panelCentral;
    private javax.swing.JPanel panelCentralEditor;
    private javax.swing.JPanel panelEditorCodigo;
    private javax.swing.JScrollPane scrollPaneEditor;
    private javax.swing.JTextPane textPaneEditor;
    // End of variables declaration//GEN-END:variables
}

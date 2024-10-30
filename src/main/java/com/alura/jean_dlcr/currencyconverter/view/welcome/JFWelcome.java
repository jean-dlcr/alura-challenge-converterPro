/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.alura.jean_dlcr.currencyconverter.view.welcome;

import com.alura.jean_dlcr.currencyconverter.util.RoundButton;
import com.alura.jean_dlcr.currencyconverter.model.lang.LanguageItem;
import java.lang.String;
import javax.swing.BorderFactory;

/**
 *
 * @author Jean
 */
public class JFWelcome extends javax.swing.JFrame {

    /**
     * Creates new form JFWelcome
     * @param title
     */
    public JFWelcome(String title) {
        this.setTitle(title);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblWelcomeTo = new javax.swing.JLabel();
        txtAreaDescrip = new javax.swing.JTextArea();
        lblBrand = new javax.swing.JLabel();
        lblLanguage = new javax.swing.JLabel();
        cboLanguages = new javax.swing.JComboBox<>();
        btnStart = new RoundButton("", 15);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lblWelcomeTo.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        lblWelcomeTo.setForeground(new java.awt.Color(0, 0, 0));
        lblWelcomeTo.setText("Bienvenido a");

        txtAreaDescrip.setEditable(false);
        txtAreaDescrip.setBackground(new java.awt.Color(255, 255, 255));
        txtAreaDescrip.setColumns(20);
        txtAreaDescrip.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        txtAreaDescrip.setForeground(new java.awt.Color(0, 0, 0));
        txtAreaDescrip.setLineWrap(true);
        txtAreaDescrip.setRows(5);
        txtAreaDescrip.setText("Tu herramienta confiable para cotizar el intercambio de divisas. Ingresa la cantidad, selecciona el par de monedas que deseas convertir, y descubre el valor en tiempo real.\n\nAntes de empezar, selecciona tu idioma.");
        txtAreaDescrip.setWrapStyleWord(true);
        txtAreaDescrip.setAutoscrolls(false);
        txtAreaDescrip.setBorder(null);
        txtAreaDescrip.setFocusable(false);
        txtAreaDescrip.setPreferredSize(new java.awt.Dimension(344, 121));
        txtAreaDescrip.setVerifyInputWhenFocusTarget(false);

        lblBrand.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        lblBrand.setForeground(new java.awt.Color(42, 122, 228));
        lblBrand.setText("ConvertPro");

        lblLanguage.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        lblLanguage.setForeground(new java.awt.Color(0, 0, 0));
        lblLanguage.setText("Languaje:");

        cboLanguages.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        cboLanguages.setOpaque(true);
        cboLanguages.setPreferredSize(new java.awt.Dimension(101, 22));

        btnStart.setBackground(new java.awt.Color(42, 122, 228));
        btnStart.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        btnStart.setForeground(new java.awt.Color(255, 255, 255));
        btnStart.setText("jButton1");
        btnStart.setBorder(null);
        btnStart.setBorderPainted(false);
        btnStart.setContentAreaFilled(false);
        btnStart.setFocusPainted(false);
        btnStart.setFocusable(false);
        btnStart.setMargin(new java.awt.Insets(5, 5, 5, 5));
        btnStart.setMaximumSize(new java.awt.Dimension(100, 100));
        btnStart.setMinimumSize(new java.awt.Dimension(0, 0));
        btnStart.setPreferredSize(new java.awt.Dimension(88, 30));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtAreaDescrip, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblWelcomeTo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblBrand))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(159, 159, 159)
                        .addComponent(btnStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(lblLanguage)
                        .addGap(18, 18, 18)
                        .addComponent(cboLanguages, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblWelcomeTo)
                    .addComponent(lblBrand))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtAreaDescrip, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cboLanguages, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLanguage, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(btnStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFWelcome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFWelcome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFWelcome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFWelcome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFWelcome(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnStart;
    public javax.swing.JComboBox<LanguageItem> cboLanguages;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JLabel lblBrand;
    public javax.swing.JLabel lblLanguage;
    public javax.swing.JLabel lblWelcomeTo;
    public javax.swing.JTextArea txtAreaDescrip;
    // End of variables declaration//GEN-END:variables
}

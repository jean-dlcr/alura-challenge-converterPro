package com.alura.jean_dlcr.currencyconverter.controller.main;

import com.alura.jean_dlcr.currencyconverter.controller.api.ControllerApi;
import com.alura.jean_dlcr.currencyconverter.controller.main.converter.ControllerConverter;
import com.alura.jean_dlcr.currencyconverter.controller.main.record.ControllerRecord;
import com.alura.jean_dlcr.currencyconverter.util.LanguageYMLLoader;
import com.alura.jean_dlcr.currencyconverter.util.StringVariables;
import com.alura.jean_dlcr.currencyconverter.view.main.JFMain;
import com.alura.jean_dlcr.currencyconverter.view.main.converter.JPConverter;
import com.alura.jean_dlcr.currencyconverter.view.main.record.JPRecord;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Jean
 */
public class ControllerMain implements ActionListener, MouseListener {

    private ControllerApi controllerApi;
    private JFMain mainViewTemplate;
    private LanguageYMLLoader languageLoader;

    private ArrayList<JButton> buttons;

    public ControllerMain(LanguageYMLLoader languageLoader, ControllerApi controllerApi) {
        this.controllerApi = controllerApi;
        this.mainViewTemplate = new JFMain();
        this.languageLoader = languageLoader;
        this.buttons = new ArrayList<>();
        init();
        events();
        buttons.get(0).doClick();
    }

    public void show() {
        this.mainViewTemplate.setVisible(true);
    }

    private void init() {
        this.mainViewTemplate.setTitle(StringVariables.BRAND);
        this.mainViewTemplate.setLocationRelativeTo(null);
        createMenuButtons();
        loadMenuButtons();
    }

    private void loadMenuButtons() {
        this.mainViewTemplate.pnlMenuOptions.removeAll();
        for (JButton btn : buttons) {
            this.mainViewTemplate.pnlMenuOptions.add(btn);
        }
    }

    private void createMenuButtons() {
        this.buttons.add(createJButton(languageLoader.getValue("MENUBUTTONS.btnConverter")));
        this.buttons.add(createJButton(languageLoader.getValue("MENUBUTTONS.btnRecord")));
        this.buttons.add(createJButton(languageLoader.getValue("MENUBUTTONS.btnHelp")));
        this.buttons.add(createJButton(languageLoader.getValue("MENUBUTTONS.btnAbout")));
    }

    private JButton createJButton(String label) {
        JButton jButton1 = new javax.swing.JButton();
        jButton1.setName(label.toLowerCase());
        jButton1.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jButton1.setBackground(new java.awt.Color(84, 89, 92));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText(label);
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setFocusPainted(false);
        jButton1.setOpaque(false);
        jButton1.setMargin(new java.awt.Insets(2, 5, 2, 5));
        jButton1.setMaximumSize(new java.awt.Dimension(100, 50));
        jButton1.setMinimumSize(new java.awt.Dimension(0, 0));
        jButton1.setPreferredSize(new java.awt.Dimension(65, 24));
        return jButton1;
    }

    private void events() {
        for (JButton btn : buttons) {
            btn.addActionListener(this);
            btn.addMouseListener(this);
        }
    }

    private void changePanel(JPanel pnl){
    this.mainViewTemplate.pnlMain.removeAll();
        this.mainViewTemplate.pnlMain.add(pnl);
        this.mainViewTemplate.revalidate();
        this.mainViewTemplate.repaint();
    }
    
    private void OnClickConverter(String label) {
        //[400, 270]
        JPConverter pnlConverter = new JPConverter();
        ControllerConverter controllerConverter = new ControllerConverter(mainViewTemplate, languageLoader, controllerApi, pnlConverter, label);
        changePanel(pnlConverter);
    }
    
    

    private void OnClickRecord(String label) {
        JPRecord pnlRecord = new JPRecord();
        ControllerRecord controllerRecord = new ControllerRecord(mainViewTemplate, languageLoader, pnlRecord, label);
        changePanel(pnlRecord);
    }
    
    /*private void OnClickHelp() {
        JPHelp pnlHelp = new JPHelp();
        ControllerHelp controllerHelp = new ControllerHelp(mainViewTemplate, languageLoader, pnlHelp, label);
        changePanel(pnlHelp);
    }
    
    private void OnClickAbout() {
        JPAbout pnlHelp = new JPAbout();
        ControllerAbout controllerAbout = new ControllerAbout(mainViewTemplate, languageLoader, pnlHelp, label);
        changePanel(pnlAbout);
    }*/

    @Override
    public void actionPerformed(ActionEvent evt) {
        Object e = evt.getSource();
        if (isButton(e, languageLoader.getValue("MENUBUTTONS.btnConverter"))) {
            // Llamar al método clickMenu1()
            OnClickConverter(languageLoader.getValue("MENUBUTTONS.btnConverter"));
        } else if (isButton(e, languageLoader.getValue("MENUBUTTONS.btnRecord"))) {
            OnClickRecord(languageLoader.getValue("MENUBUTTONS.btnRecord"));
        }
    }

    private Boolean isButton(Object e, String label) {
        return e instanceof JButton && label.equalsIgnoreCase(((JButton) e).getName());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent evt) {
        Object e = evt.getSource();
        if (e instanceof JButton && this.mainViewTemplate.pnlMenuOptions.isAncestorOf((JButton) e)) {
            ((JButton) e).setOpaque(true);
        }
    }

    @Override
    public void mouseExited(MouseEvent evt) {
        Object e = evt.getSource();
        if (e instanceof JButton && this.mainViewTemplate.pnlMenuOptions.isAncestorOf((JButton) e)) {
            ((JButton) e).setOpaque(false);
        }
    }

}

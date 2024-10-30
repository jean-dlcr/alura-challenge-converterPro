/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alura.jean_dlcr.currencyconverter.controller.main.about;

import com.alura.jean_dlcr.currencyconverter.util.Helper;
import com.alura.jean_dlcr.currencyconverter.util.LanguageYMLLoader;
import com.alura.jean_dlcr.currencyconverter.util.StringVariables;
import com.alura.jean_dlcr.currencyconverter.view.main.JFMain;
import com.alura.jean_dlcr.currencyconverter.view.main.about.JPAbout;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.ImageIcon;

/**
 *
 * @author Jean
 */
public class ControllerAbout implements ActionListener {
    private final JFMain mainView;
    private final JPAbout pnlAbout;
    private final LanguageYMLLoader languageLoader;
    private final String labelButton;

    public ControllerAbout(JFMain mainView, LanguageYMLLoader languageLoader, JPAbout pnlAbout, String labelButton) {
        this.mainView = mainView;
        this.languageLoader = languageLoader;
        this.pnlAbout = pnlAbout;
        this.labelButton = labelButton;
        init();
        events();
    }

    private void init() {
        this.mainView.setTitle(String.format("%s - %s", StringVariables.BRAND, labelButton));
        this.pnlAbout.btnGithub.setText(StringVariables.GITHUB.toUpperCase());
        fillTextPane();
    }
    
    private void fillTextPane(){
        String text = "<html><body style='background-color:transparent;font-family:Verdana;font-size:9px;'>"
                + "<p>"
                + String.format("%s %s", StringVariables.BRAND, StringVariables.VERSION) + "<br><br>"
                + "Esta aplicación fue creada para practicar el consumo de APIs y la integración con servicios externos. "
                + "El objetivo no es brindar información financiera precisa, ya que usa datos de una API gratuita de "
                + "<a href='https://www.exchangerate-api.com/'>Exchange Rate API</a>, que no es en tiempo real.<br><br>"
                + "Para más detalles y el código fuente, visita la documentación.<br><br>Jean De La Cruz R.<br><br>"
                + "</p></body></html>";
        this.pnlAbout.txtPnl.setText(text);

    }

    private void events() {
        this.pnlAbout.btnGithub.addActionListener(this);
    }
    
    private void onClickGitHubBtn(){
        try {
          String url = StringVariables.GITHUB_LINK;
           Desktop.getDesktop().browse(new URI(url));
        }catch(IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        Object e = evt.getSource();
        if(e == this.pnlAbout.btnGithub){
            onClickGitHubBtn();
        }
    }
}

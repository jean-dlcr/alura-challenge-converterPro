package com.alura.jean_dlcr.currencyconverter.controller.welcome;

import com.alura.jean_dlcr.currencyconverter.controller.ControllerApiKey_Frame;
import com.alura.jean_dlcr.currencyconverter.controller.api.ControllerApi;
import com.alura.jean_dlcr.currencyconverter.controller.main.converter.ControllerConverter;
import com.alura.jean_dlcr.currencyconverter.controller.config.ControllerConfig;
import com.alura.jean_dlcr.currencyconverter.controller.main.ControllerMain;
import com.alura.jean_dlcr.currencyconverter.model.lang.LanguageItem;
import com.alura.jean_dlcr.currencyconverter.util.Helper;
import com.alura.jean_dlcr.currencyconverter.util.LanguageItemRenderer;
import com.alura.jean_dlcr.currencyconverter.util.LanguageYMLLoader;
import com.alura.jean_dlcr.currencyconverter.util.StringVariables;
import com.alura.jean_dlcr.currencyconverter.view.welcome.JFWelcome;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;

/**
 * Controlador para la vista de bienvenida
 *
 * @author Jean
 */
public class ControllerWelcome implements ActionListener {

    private ControllerConfig controllerConfig;
    private ControllerApi controllerApi;

    private JFWelcome welcomeView;
    private LanguageYMLLoader languageYMLLoader;
    //private final String CURRENT_PATH = StringVariables.CURRENT_PATH;
    private ArrayList<LanguageItem> languagesAvailables;

    public ControllerWelcome(ControllerConfig controllerConfig, LanguageYMLLoader languageYML, ArrayList<LanguageItem> languages, ControllerApi controllerApi) {
        this.controllerConfig = controllerConfig;
        this.controllerApi = controllerApi;
        this.welcomeView = new JFWelcome(StringVariables.BRAND);
        this.welcomeView.setLocationRelativeTo(null);
        this.languageYMLLoader = languageYML;
        this.languagesAvailables = languages;
        init();
        events();
    }

    public void show() {
        
        String aux = controllerConfig.getLanguageProperty();
        if(!Helper.isNullOrEmpty(aux)){
            System.out.println("ya hay idioma: " + aux);
            this.welcomeView.btnStart.doClick();
        }else{
            this.welcomeView.setVisible(true);
        }
        
    }

    private void events() {
        this.welcomeView.btnStart.addActionListener(this);
    }

    private void init() {

        welcomeView.lblWelcomeTo.setText(languageYMLLoader.getValue("WELCOME.welcomeTo"));
        welcomeView.lblBrand.setText(StringVariables.BRAND);
        welcomeView.txtAreaDescrip.setText(languageYMLLoader.getValue("WELCOME.description"));
        welcomeView.btnStart.setText(languageYMLLoader.getValue("WELCOME.btnStart"));
        loadCboLanguages();
    }

    private void loadCboLanguages() {
        welcomeView.cboLanguages.setRenderer(new LanguageItemRenderer());
        DefaultComboBoxModel<LanguageItem> aModel = new DefaultComboBoxModel<>(languagesAvailables.toArray(new LanguageItem[0]));
        welcomeView.cboLanguages.setModel(aModel);
    }

    private void OnClick_btnStart() {
        controllerConfig.setLanguage(this.welcomeView.cboLanguages.getSelectedItem().toString());
        LanguageYMLLoader languageLoader = controllerConfig.loadLanguage();

        ControllerMain controllerMainFrame = new ControllerMain(languageLoader, controllerApi);
        controllerMainFrame.show();

        this.dispose();
    }

    private void dispose() {
        this.welcomeView.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        Object e = evt.getSource();
        if (e == welcomeView.btnStart) {
            OnClick_btnStart();
        }
    }

}

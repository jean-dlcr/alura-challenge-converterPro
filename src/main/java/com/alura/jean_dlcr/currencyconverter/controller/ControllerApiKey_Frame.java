/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alura.jean_dlcr.currencyconverter.controller;

import com.alura.jean_dlcr.currencyconverter.controller.config.ControllerConfig;
import com.alura.jean_dlcr.currencyconverter.util.Helper;
import com.alura.jean_dlcr.currencyconverter.util.LanguageYMLLoader;
import com.alura.jean_dlcr.currencyconverter.util.StringVariables;
import com.alura.jean_dlcr.currencyconverter.view.api.JDApikey;

import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 *
 * @author Jean
 */
public class ControllerApiKey_Frame implements ActionListener {

    private ListenerApiKeyDialog dialogListener;
    
    private JDApikey apikeyView;
    private ControllerConfig controllerConfig;
    private JFrame parent;
    private LanguageYMLLoader languageLoader;

    public ControllerApiKey_Frame(ListenerApiKeyDialog listenerDialog, JFrame parent, ControllerConfig controllerConfig, LanguageYMLLoader languageLoader) {
        this.dialogListener = listenerDialog;
        this.controllerConfig = controllerConfig;
        this.languageLoader = languageLoader;
        this.parent = parent;
        this.apikeyView = new JDApikey(parent, true);
        init();
        events();
    }

    public void show() {
        //this.apikeyView.setModal(true);
        this.apikeyView.setLocationRelativeTo(null);
        this.apikeyView.setVisible(true);
    }
    
    public void dispose(){
        this.apikeyView.dispose();
    }

    private void init() {
        this.apikeyView.setTitle(languageLoader.getValue("CONFIG_API.dialogTitle"));
        this.apikeyView.jTextPane2.setText(String.format(languageLoader.getValue("CONFIG_API.configInstructions"), StringVariables.API_HOME, StringVariables.API_HOME));
        this.apikeyView.btnCancel.setText(languageLoader.getValue("CONFIG_API.btnSave"));
        this.apikeyView.btnCancel.setText(languageLoader.getValue("CONFIG_API.btnCancel"));
        
    }

    private void events() {
        this.apikeyView.btnSaveApiKey.addActionListener(this);
        this.apikeyView.btnCancel.addActionListener(this);
    }

    public void clear(){
        Helper.setApikey(null);
        this.apikeyView.txtInputApikey.setText("");
    }
    
    private void OnClickSave(){
        //Obtengo el string del input
        String api = this.apikeyView.txtInputApikey.getText();
        //pruebo el api original, ya que el apikey es parte del url, si da "result": "success", entonces está bien
        
        dialogListener.onDialogComplete(this, true, api);
        //this.apikeyView.dispose();
        //Helper.getJSON_FromAPI_ENDPOINT(StringVariables.API_ENDPOINT_SUPPORTED_CODES);
        //la respuesta es así:
        /*
        {
    "result": "success",
    "documentation": "https://www.exchangerate-api.com/docs",
    "terms_of_use": "https://www.exchangerate-api.com/terms",
    "supported_codes": [
        [
            "AED",
            "UAE Dirham"
        ],
        [..continúa
        */
        
        //si la apikey está mal da error:
        /*
        {
    "result": "error",
    "documentation": "https://www.exchangerate-api.com/docs",
    "terms-of-use": "https://www.exchangerate-api.com/terms",
    "error-type": "invalid-key"
}
        */
        //Helper.setApikey(this.apikeyView.txtInputApikey.getText());
        
    }
    
    private void OnClickCancel(){
        //terminar el JDIALOG this.apikeyView
        //Helper.setApikey(StringVariables.CANCEL_CODE);
        dialogListener.onDialogComplete(null, false, null);
    this.apikeyView.dispose(); 
    }
    
    @Override
    public void actionPerformed(ActionEvent evt) {
        Object e = evt.getSource();
        if (e == this.apikeyView.btnSaveApiKey) {
            OnClickSave();
        } else if (e == this.apikeyView.btnCancel) {
            OnClickCancel();
        }
    }

}

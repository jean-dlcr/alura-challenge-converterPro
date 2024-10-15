package com.alura.jean_dlcr.currencyconverter.model.config;

/**
 *
 * @author Jean
 */
public class ConfigModel {
    private String selectedLanguage;
    
    public ConfigModel(){
        loadConfig();
        this.selectedLanguage = null;
    }
    
    public void loadConfig(){
    }
    
    public String getSelectedLanguage(){
        return selectedLanguage;
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alura.jean_dlcr.currencyconverter.model.lang;

/**
 *
 * @author Jean
 */
public class LanguageItem {
    private String language;
    private String imgFlagPath;
    private String imgFlagURL;

    public LanguageItem(String language, String imgFlagURL) {
        this.language = language;
        this.imgFlagPath = null;
        this.imgFlagURL = imgFlagURL;
    }
    
    public String getLanguage() {
        return language;
    }
    
    public String getImgFlagPath(){
        return imgFlagPath;
    }
    
    public String getImgFlagURL(){
        return imgFlagURL;
    }
    
    public void setImgFlagPath(String path){
        this.imgFlagPath = path;
    }
    
    @Override
    public String toString() {
       return this.language;
    }
}


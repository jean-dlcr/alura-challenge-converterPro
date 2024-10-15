/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alura.jean_dlcr.currencyconverter.model.currency;

/**
 *
 * @author Jean
 */
public class CurrencyItem {
    private String currencyCode;
    private String flagPath;
    
    public CurrencyItem(String flag, String currency){
        
        this.currencyCode = currency;
        this.flagPath = flag;
    }
    
    public void setFlagPath(String path){
        this.flagPath = path;
    }

    
    public String getCurrencyCode() {
        return currencyCode;
    }

    public String getFlagPath() {
        return flagPath;
    }
    
    @Override
    public String toString(){
        return this.currencyCode;
    }
    
}

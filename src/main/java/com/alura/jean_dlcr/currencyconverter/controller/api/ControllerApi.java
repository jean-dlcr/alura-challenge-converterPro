/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alura.jean_dlcr.currencyconverter.controller.api;

import com.alura.jean_dlcr.currencyconverter.model.currency.CurrencyItem;
import com.alura.jean_dlcr.currencyconverter.util.Helper;
import com.alura.jean_dlcr.currencyconverter.util.StringVariables;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Jean
 */
public class ControllerApi {

    private JSONArray supportedCodes;
    private ArrayList<CurrencyItem> finalCurrencies;
    private ArrayList<String> excluded;
    
    public ControllerApi(){
        this.excluded = new ArrayList<>();
        this.finalCurrencies = new ArrayList<>();
        this.supportedCodes = new JSONArray();
        //checkAvailableCurrencies();
        //updateIconFlag();        
    }
    
    public void checkAvailableCurrencies(){
        // Obtener el array de "supported_codes"
        System.out.println("apikey: " + Helper.getApikey());
                     System.out.println(String.format(StringVariables.API_ENDPOINT_SUPPORTED_CODES,Helper.getApikey()));
        JSONObject jsonResponse = (JSONObject)Helper.getJSON_FromAPI_ENDPOINT(String.format(StringVariables.API_ENDPOINT_SUPPORTED_CODES,Helper.getApikey()));
             supportedCodes = jsonResponse.getJSONArray("supported_codes");
             System.out.println(String.format(StringVariables.API_ENDPOINT_SUPPORTED_CODES,Helper.getApikey()));
             System.out.println("size: " + supportedCodes.length());
    }
    
    public int getSizeJSONArray(){
        return supportedCodes.length();
    }
    
    public JSONArray getSupportedCodes(){
        return supportedCodes;
    }
    
    public void setFinalCurrencies(ArrayList<CurrencyItem> items){
        this.finalCurrencies = items;
    }
    
    public ArrayList<CurrencyItem> getFinalCurrencies(){
        return this.finalCurrencies;
    }
}

    

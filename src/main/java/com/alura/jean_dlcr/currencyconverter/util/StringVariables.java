/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alura.jean_dlcr.currencyconverter.util;

/**
 *
 * @author Jean
 */
public class StringVariables {
    public final static String BRAND = "ConverterPro";
    public final static String GITHUB = "ConverterPro";
    
    public final static String MSG_LOADING_CONFIG = "Cargando archivos de configuración...";
    public final static String MSG_LOADING_LANGUAGE = "Cargando archivos de idioma...";
    public final static String MSG_LOADING_LANGUAGES = "Idiomas disponibles...";
    public final static String MSG_READING_APIKEY = "Leyendo API_KEY...";
    public final static String MSG_LOADING_CURRENCIES = "Cargando monedas disponibles...";
    
    
    public final static String CURRENT_PATH = System.getProperty("user.dir");
    public final static String API_HOME = "https://www.exchangerate-api.com/";
    private final static String API = "https://v6.exchangerate-api.com/v6/%s";
    
    //ENDPOINTS
    public final static String API_ENDPOINT_SUPPORTED_CODES = API + "/codes";
    public final static String API_ENDPOINT_PAIR_CONVERSION = API + "/pair/%s/%s";
    
    public final static String API_GET_DATA_FROM_CURRENCY = "https://restcountries.com/v3.1/currency/%s";
    public final static String API_GET_FLAG_WAVE_FROM_COUNTRY = "https://flagcdn.com/96x72/%s.png";
}


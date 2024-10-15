package com.alura.jean_dlcr.currencyconverter.util;

import java.util.Map;

public class LanguageYMLLoader {
    
    private Map<String, Object> languageYMLObject;

    // Constructor que acepta un mapa como parámetro
    public LanguageYMLLoader(Map<String, Object> languageYMLObject) {
        this.languageYMLObject = languageYMLObject;
    }

    public String getValue(String path) {
        String[] keys = path.split("\\.");
        Object value = languageYMLObject;

        for (String key : keys) {
            if (value instanceof Map) {
                value = ((Map<String, Object>) value).get(key);
            } else {
                return null;
            }
        }
        
        return value != null ? value.toString() : null; 
    }
}

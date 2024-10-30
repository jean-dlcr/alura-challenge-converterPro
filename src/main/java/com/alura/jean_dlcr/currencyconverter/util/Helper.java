/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alura.jean_dlcr.currencyconverter.util;

import java.awt.Desktop;
import java.awt.Panel;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.event.HyperlinkEvent;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Jean
 */
public class Helper {


    private static String API_KEY;
    
    public static String getApikey(){
        return API_KEY;
    }
    
    public static void setApikey(String apikey){
        
        API_KEY = apikey;
    }
    
    public static Object getJSON_FromAPI_ENDPOINT(String apiEndpoint) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiEndpoint))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Intentar determinar si es un JSONArray o un JSONObject
            String responseBody = response.body().trim();
            if (responseBody.startsWith("[")) {
                // Es un JSONArray
                return new JSONArray(responseBody);
            } else if (responseBody.startsWith("{")) {
                // Es un JSONObject
                return new JSONObject(responseBody);
            } else {
                // No es un JSON válido
                Logger.getLogger(Helper.class.getName()).log(Level.WARNING, "Respuesta no es JSON válido.");
                return null;
            }
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static String downloadFlag(String urlString, String fileName, String destinationPath) {
        try {
            // Crear la URL y la conexión
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);

            // Crear el directorio si no existe
            File directory = new File(destinationPath);
            if (!directory.exists()) {
                directory.mkdirs(); // Crear el directorio si no existe
            }
            // Crear la ruta completa del archivo
            String fullPath = destinationPath + File.separator + fileName;

            // Descargar la imagen de la bandera y guardarla
            try (var inputStream = connection.getInputStream()) {
                Files.copy(inputStream, Paths.get(fullPath));
            }

            return fullPath; // Retornar la ruta completa del archivo descargado
        } catch (IOException e) {
            System.err.println("Error al descargar la bandera: " + e.getMessage());
            return null; // Retornar null si hay un error
        }
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    public static String roundedDecimals(double value, int decimalPlaces) {
        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(decimalPlaces, RoundingMode.HALF_UP);
        return bd.toPlainString();
    }

    public static void openURL_onBrowser(String url) {
        try {
            Desktop desktop = Desktop.getDesktop();
            if (desktop.isSupported(Desktop.Action.BROWSE)) {
                desktop.browse(new URI(url));
            }
        } catch (IOException | URISyntaxException ex) {
            Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "No se pudo abrir el navegador. Verifique la URL o la configuración de su sistema.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    public static void addHiperLink(JTextPane p){
        p.addHyperlinkListener(e -> {
                if (HyperlinkEvent.EventType.ACTIVATED.equals(e.getEventType())) {
                    try {
                        Desktop.getDesktop().browse(e.getURL().toURI());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
    }

}

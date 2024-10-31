/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alura.jean_dlcr.currencyconverter.controller.config;

import com.alura.jean_dlcr.currencyconverter.model.lang.LanguageItem;
import com.alura.jean_dlcr.currencyconverter.util.Helper;
import com.alura.jean_dlcr.currencyconverter.util.LanguageYMLLoader;
import com.alura.jean_dlcr.currencyconverter.util.StringVariables;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.yaml.snakeyaml.Yaml;

/**
 *
 * @author Jean
 */
public class ControllerConfig {

    private static final String CURRENT_PATH = StringVariables.CURRENT_PATH;
    private static final String CONFIG_PATH = StringVariables.CURRENT_PATH + "/config/config.ini";

    private ArrayList<LanguageItem> languagesFilesAvailable = new ArrayList<>();

    private static final String languageEmergency = "Spanish";

    // Método para crear la carpeta config
    public void createConfig() {
        File configDir = new File(CURRENT_PATH + File.separator + "config");

        // Si la carpeta 'config' no existe, crearla
        if (!configDir.exists()) {
            if (configDir.mkdir()) {
                System.out.println("Carpeta 'config' creada exitosamente en: " + configDir.getPath());

                try {
                    copyResourceDirectory("config", configDir);
                    System.out.println("Archivos copiados exitosamente a: " + configDir.getPath());
                } catch (Exception e) {
                    System.out.println("Error al copiar archivos: " + e.getMessage());
                }
            } else {
                System.out.println("No se pudo crear la carpeta 'config'.");
            }
        } else {
            System.out.println("La carpeta 'config' ya existe en: " + configDir.getPath());
        }
    }

    // Método para copiar el directorio desde resources en IDE usar resourcePath como /config
    /*private void copyResourceDirectory(String resourcePath, File destinationDir) throws IOException {
        URL resourceURL = getClass().getResource(resourcePath);
        if (resourceURL == null) {
            throw new IOException("No se pudo encontrar el recurso: " + resourcePath);
        }
        File sourceDirectory = new File(resourceURL.getFile());
        FileUtils.copyDirectory(sourceDirectory, destinationDir);
    }*/
    private void copyResourceDirectory(String resourcePath, File destinationDir) throws IOException {
        final File jarFile = new File(getClass().getProtectionDomain().getCodeSource().getLocation().getPath());

        if (jarFile.isFile()) { // Ejecutando desde un JAR
            try (JarFile jar = new JarFile(jarFile)) {
                final Enumeration<JarEntry> entries = jar.entries();

                while (entries.hasMoreElements()) {
                    final JarEntry entry = entries.nextElement();
                    final String entryName = entry.getName();

                    // Filtrar los archivos que comienzan con el path deseado
                    if (entryName.startsWith(resourcePath + "/") && !entry.isDirectory()) {
                        // Extraer el archivo y copiarlo al directorio de destino
                        File destFile = new File(destinationDir, entryName.substring(resourcePath.length() + 1));
                        if (!destFile.getParentFile().exists()) {
                            destFile.getParentFile().mkdirs();
                        }

                        // Copiar el contenido del archivo desde el JAR
                        try (InputStream inputStream = jar.getInputStream(entry)) {
                            FileUtils.copyInputStreamToFile(inputStream, destFile);
                        }
                    }
                }
            }
        } else {
            throw new IOException("No se está ejecutando desde un archivo JAR");
        }
    }

    public void overrideEmergency() {
        File configDir = new File(CURRENT_PATH + File.separator + "config");

        // Verificar si la carpeta existe
        if (configDir.exists()) {
            try {
                FileUtils.deleteDirectory(configDir);
                System.out.println("Carpeta 'config' eliminada exitosamente.");
            } catch (IOException e) {
                System.out.println("Error al eliminar la carpeta 'config': " + e.getMessage());
            }
        } else {
            System.out.println("La carpeta 'config' no existe.");
        }
        createConfig();
    }

    private Properties loadProperties() {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(CONFIG_PATH)) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            overrideEmergency();
        }
        return properties;
    }

    // Método auxiliar para guardar propiedades en el archivo .ini
    private void saveProperties(Properties properties) {
        try (FileOutputStream output = new FileOutputStream(CONFIG_PATH)) {
            properties.store(output, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public LanguageYMLLoader loadLanguage() {
        Properties properties = loadProperties();
        LanguageYMLLoader languageYMLObject = null;

        // Obtener el valor de 'language'
        String language = properties.getProperty("language");
        if (Helper.isNullOrEmpty(language)) {
            language = languageEmergency;
        }
        String ymlFilePath = StringVariables.CURRENT_PATH + "/config/languages/" + language + ".yml";
        Path path = Path.of(ymlFilePath);

        if (Files.exists(path)) {
            Yaml yaml = new Yaml();
            try (FileInputStream ymlInput = new FileInputStream(path.toFile())) {
                Map<String, Object> loadedYMLObject = yaml.load(ymlInput);
                languageYMLObject = new LanguageYMLLoader(loadedYMLObject);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("El archivo de idioma no existe: " + ymlFilePath);
            overrideEmergency();
            return secureLoad();
        }
        return languageYMLObject;
    }

    
    private LanguageYMLLoader secureLoad() {
        String ymlFilePath = StringVariables.CURRENT_PATH + "/config/languages/Spanish.yml";
        Path path = Path.of(ymlFilePath);

        if (Files.exists(path)) {
            Yaml yaml = new Yaml();
            try (FileInputStream ymlInput = new FileInputStream(path.toFile())) {
                Map<String, Object> loadedYMLObject = yaml.load(ymlInput);
                return new LanguageYMLLoader(loadedYMLObject);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    
    private void writeValue(String key, String value) {
        Properties properties = loadProperties();
        properties.setProperty(key, value);
        saveProperties(properties);
    }

    // Método para actualizar la propiedad 'language' en el archivo .ini
    public void setLanguage(String newLanguage) {
        writeValue("language", newLanguage);
    }

    public void setApiKey(String apikey) {
        writeValue("api_key", apikey);
    }

    public String getApikey() {
        Properties properties = loadProperties();

        return (String) properties.getProperty("api_key");
    }

    public String getLanguageProperty() {
        Properties properties = loadProperties();
        return (String) properties.getProperty("language");
    }

}

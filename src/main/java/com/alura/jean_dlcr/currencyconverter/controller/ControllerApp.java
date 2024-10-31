/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alura.jean_dlcr.currencyconverter.controller;

import com.alura.jean_dlcr.currencyconverter.model.lang.LanguageItem;
import com.alura.jean_dlcr.currencyconverter.util.Helper;
import com.alura.jean_dlcr.currencyconverter.util.StringVariables;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.yaml.snakeyaml.Yaml;
import java.net.URL;

/**
 *
 * @author Jean
 */
public class ControllerApp {

    private final static String CURRENT_PATH = StringVariables.CURRENT_PATH;
    private ArrayList<LanguageItem> languageItems = new ArrayList<>();

    public void prepareLanguageItems() {
        File directory = new File(CURRENT_PATH + "/config/languages");
        LanguageItem languageItem = null;
        String imgFlagPath = null;
        String baseName = null;
        String imgFlagURL = null;
        if (directory.exists() && directory.isDirectory()) {
            try {
                for (File file : FileUtils.listFiles(directory, new String[]{"yml"}, false)) {
                    // Leer el archivo YML
                    Yaml yaml = new Yaml();
                    Map<String, Object> loadedYMLObject;

                    try (FileInputStream fis = new FileInputStream(file)) {
                        loadedYMLObject = yaml.load(fis);

                        if (loadedYMLObject != null && loadedYMLObject.containsKey("flagURL")) {
                            baseName = FilenameUtils.getBaseName(file.getName());
                            imgFlagURL = (String) loadedYMLObject.get("flagURL");
                            languageItem = new LanguageItem(baseName, imgFlagURL);
                            imgFlagPath = downloadFlag(imgFlagURL, baseName);
                            if (imgFlagPath != null) {
                                languageItem.setImgFlagPath(imgFlagPath);
                                languageItems.add(languageItem);
                            }
                            
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String downloadFlag(String url, String basename) {
        String savedPath = alreadyDownloaded(basename);

        if (savedPath != null) {
            
            return savedPath;
        }
        
        savedPath = Helper.downloadFlag(url, basename+".png",CURRENT_PATH + "/resources/lang/" );
        return savedPath;
    }

    private String alreadyDownloaded(String basename) {
        String filePath = CURRENT_PATH + "/resources/lang/" + basename + ".png";
        File file = new File(filePath);
        if (file.exists()) {
            return filePath;
        } else {
            return null;
        }
    }


    public ArrayList<LanguageItem> getLanguagesFilesAvailables() {
        return this.languageItems;
    }
}

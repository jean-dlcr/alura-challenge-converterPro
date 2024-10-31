/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alura.jean_dlcr.currencyconverter.controller;

import com.alura.jean_dlcr.currencyconverter.controller.api.ControllerApi;
import com.alura.jean_dlcr.currencyconverter.controller.welcome.ControllerWelcome;
import com.alura.jean_dlcr.currencyconverter.controller.config.ControllerConfig;
import com.alura.jean_dlcr.currencyconverter.model.currency.CurrencyItem;
import com.alura.jean_dlcr.currencyconverter.util.Helper;
import com.alura.jean_dlcr.currencyconverter.util.LanguageYMLLoader;
import com.alura.jean_dlcr.currencyconverter.util.StringVariables;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Jean
 */
public class InitializationTask extends SwingWorker<Void, Integer> implements ListenerApiKeyDialog<ControllerApiKey_Frame> {

    private final static String FLAG_PATH = StringVariables.CURRENT_PATH + "/resources/icons/flag/";

    private ControllerSplashScreen controllerSplashScreen;

    private ControllerConfig controllerConfig;
    private LanguageYMLLoader languageYML;

    private ControllerApp controllerMain;

    private ControllerApi controllerApi;

    private ArrayList<CurrencyItem> finalCurrencies;

    private volatile boolean apiKeyReady = false;

    //private ControllerApiKey_Frame apiKeyFrame;
    public InitializationTask(ControllerSplashScreen controllerSplashScreen) {
        this.controllerSplashScreen = controllerSplashScreen;
        this.controllerConfig = new ControllerConfig();
        this.controllerMain = new ControllerApp();
        this.controllerApi = new ControllerApi();
        this.finalCurrencies = new ArrayList<>();
    }

    @Override
    protected Void doInBackground() throws Exception {
        int cantFlag = 0;
        int totalSteps = 4 + cantFlag;
        float progressStep = 100f / totalSteps;
        float currentProgress = 0f;

        controllerSplashScreen.setStatus(StringVariables.MSG_LOADING_CONFIG);
        controllerConfig.createConfig();
        currentProgress += progressStep;
        publish((int) currentProgress);

        controllerSplashScreen.setStatus(StringVariables.MSG_LOADING_LANGUAGE);
        languageYML = controllerConfig.loadLanguage();
        currentProgress += progressStep;
        publish((int) currentProgress);

        controllerSplashScreen.setStatus(StringVariables.MSG_LOADING_LANGUAGES);
        controllerMain.prepareLanguageItems();
        currentProgress += progressStep;
        publish((int) currentProgress);

        controllerSplashScreen.setStatus(StringVariables.MSG_READING_APIKEY);
        String apikey = controllerConfig.getApikey();
        if (Helper.isNullOrEmpty(apikey)) {
            ControllerApiKey_Frame apiKeyFrame = new ControllerApiKey_Frame(this, controllerSplashScreen.getJFrame(), controllerConfig, languageYML);
            apiKeyFrame.show();
            currentProgress += progressStep;
            publish((int) currentProgress);
        }else{
            Helper.setApikey(apikey);
        }

        controllerSplashScreen.setStatus(StringVariables.MSG_LOADING_CURRENCIES);
        controllerApi.checkAvailableCurrencies();
        cantFlag = controllerApi.getSizeJSONArray();
        progressStep = 100f / (4 + cantFlag);
        currentProgress = 4 * progressStep;
        updateIconFlag(controllerApi.getSupportedCodes(), currentProgress, progressStep);

        return null;
    }

    private void updateIconFlag(JSONArray supportedCodes, float current, float progress) {
        JSONArray jsonResponse = null;
        String code = "";
        String countryCode = null;
        Object response = null;

        File flagDirectory = new File(FLAG_PATH);
        if (!flagDirectory.exists()) {
            flagDirectory.mkdirs();
        }

        Set<String> existingFlags = new HashSet<>();
        for (File file : flagDirectory.listFiles()) {
            if (file.isFile()) {
                existingFlags.add(file.getName());
            }
        }

        for (int i = 0; i < supportedCodes.length(); i++) {
            JSONArray codePair = supportedCodes.getJSONArray(i);
            code = codePair.getString(0);
            String flagFileName = code + ".png";

            // Verificar si la bandera ya está en la lista de archivos existentes
            if (!existingFlags.contains(flagFileName)) {
                response = Helper.getJSON_FromAPI_ENDPOINT(String.format(StringVariables.API_GET_DATA_FROM_CURRENCY, code));

                if (response instanceof JSONArray) {
                    jsonResponse = (JSONArray) response;
                    countryCode = jsonResponse.getJSONObject(0).getString("cca2").toLowerCase();
                    if (code.equalsIgnoreCase("USD")) {
                        countryCode = "US".toLowerCase();
                    }

                    String flagPath = FLAG_PATH + flagFileName;

                    // Descargar la bandera si no existe
                    String flagUrl = String.format(StringVariables.API_GET_FLAG_WAVE_FROM_COUNTRY, countryCode);
                    Helper.downloadFlag(flagUrl, flagFileName, FLAG_PATH);

                    // Después de procesar, agregar la bandera a la lista para evitar revisarla de nuevo
                    existingFlags.add(flagFileName);
                }
            }

            String flagPath = FLAG_PATH + flagFileName;
            finalCurrencies.add(new CurrencyItem(flagPath, code));
            current += progress;
            publish((int) current);
        }
    }

    @Override
    protected void process(java.util.List<Integer> chunks
    ) {
        int lastProgress = chunks.get(chunks.size() - 1);
        controllerSplashScreen.setProgress(lastProgress);
    }

    @Override
    protected void done() {
        controllerSplashScreen.dispose();
        controllerApi.setFinalCurrencies(finalCurrencies);

        new ControllerWelcome(controllerConfig, languageYML, controllerMain.getLanguagesFilesAvailables(), controllerApi).show();
    }

    private boolean checkApiWorks(String apikey) {
        if (Helper.isNullOrEmpty(apikey)) {
            return false;
        }

        // Probar la API key
        Helper.setApikey(apikey);
        JSONObject response = (JSONObject) Helper.getJSON_FromAPI_ENDPOINT(String.format(StringVariables.API_ENDPOINT_SUPPORTED_CODES, Helper.getApikey()));
        if (response.has("result") && response.getString("result").equals("success")) {
            return true;
        } else {
            JOptionPane.showMessageDialog(controllerSplashScreen.getJFrame(),
                    languageYML.getValue("CONFIG_API.wrongAPIkey"),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;

        }
    }

    @Override
    public void onDialogComplete(ControllerApiKey_Frame refController, boolean ready, String apikey) {
        boolean res = false;
        if (!ready) {
            System.exit(0);
        } else {
            res = checkApiWorks(apikey.trim());
            refController.clear();
            if (res) {
                controllerConfig.setApiKey(apikey);
                Helper.setApikey(apikey);
                refController.dispose();
            }
        }
    }

}

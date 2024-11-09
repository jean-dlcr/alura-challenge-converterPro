/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alura.jean_dlcr.currencyconverter.controller.main.converter;

import com.alura.jean_dlcr.currencyconverter.controller.api.ControllerApi;
import com.alura.jean_dlcr.currencyconverter.model.currency.CurrencyItem;
import com.alura.jean_dlcr.currencyconverter.model.lang.LanguageItem;
import com.alura.jean_dlcr.currencyconverter.util.CurrencyItemRenderer;
import com.alura.jean_dlcr.currencyconverter.util.Helper;
import com.alura.jean_dlcr.currencyconverter.util.LanguageYMLLoader;
import com.alura.jean_dlcr.currencyconverter.util.StringVariables;
import com.alura.jean_dlcr.currencyconverter.view.main.JFMain;
import com.alura.jean_dlcr.currencyconverter.view.main.converter.JPConverter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Jean
 */
public class ControllerConverter implements ActionListener {

    private final LanguageYMLLoader languageLoader;
    private final JFMain mainView;
    private final JPConverter pnlConverter;
    private final ControllerApi controllerApi;
    private final ArrayList<CurrencyItem> currencies;
    private final String labelButton;

    public ControllerConverter(JFMain mainView, LanguageYMLLoader languageLoader, ControllerApi controllerApi, JPConverter pnlConverter, String labelButton) {
        this.mainView = mainView;
        this.languageLoader = languageLoader;
        this.controllerApi = controllerApi;
        this.currencies = controllerApi.getFinalCurrencies();
        this.pnlConverter = pnlConverter;
        this.labelButton = labelButton;
        init();
        events();
    }

    private void init() {
        this.mainView.setTitle(String.format("%s - %s", StringVariables.BRAND, labelButton));
        this.pnlConverter.btnConverter.setText(languageLoader.getValue("CONVERTER.btnConverter"));
        loadCboLanguages();
        setSelectedCurrency(this.pnlConverter.cboCurrency1, "PEN");
        setSelectedCurrency(this.pnlConverter.cboCurrency2, "USD");
    }

    private void events() {
        this.pnlConverter.btnChangeOrder.addActionListener(this);
        this.pnlConverter.btnConverter.addActionListener(this);

        this.pnlConverter.cboCurrency1.addActionListener(this);
        this.pnlConverter.cboCurrency2.addActionListener(this);
    }

    private void loadCboLanguages() {
        pnlConverter.cboCurrency1.setRenderer(new CurrencyItemRenderer());
        pnlConverter.cboCurrency2.setRenderer(new CurrencyItemRenderer());
        DefaultComboBoxModel<CurrencyItem> aModel = new DefaultComboBoxModel<>(currencies.toArray(new CurrencyItem[0]));
        DefaultComboBoxModel<CurrencyItem> aModel2 = new DefaultComboBoxModel<>(currencies.toArray(new CurrencyItem[0]));
        pnlConverter.cboCurrency1.setModel(aModel);
        pnlConverter.cboCurrency2.setModel(aModel2);
    }

    private void setSelectedCurrency(JComboBox<CurrencyItem> comboBox, String currencyCode) {
        DefaultComboBoxModel<CurrencyItem> model = (DefaultComboBoxModel<CurrencyItem>) comboBox.getModel();
        for (int i = 0; i < model.getSize(); i++) {
            CurrencyItem item = model.getElementAt(i);
            if (item.getCurrencyCode().equals(currencyCode)) {
                comboBox.setSelectedItem(item);
                break;
            }
        }
    }

    private void OnClickSwapCBO() {
        this.pnlConverter.rotateButtonIcon(this.pnlConverter.btnChangeOrder, 180);
        SwapCBO();
    }

    private void SwapCBO() {
        short indexCBOFrom_original = (short) this.pnlConverter.cboCurrency1.getSelectedIndex();
        short indexCBOTo_original = (short) this.pnlConverter.cboCurrency2.getSelectedIndex();

        String resultOriginal = this.pnlConverter.txtResultConverted.getText();

        this.pnlConverter.txtInputFrom.setText(resultOriginal);
        this.pnlConverter.txtResultConverted.setText("");
        this.pnlConverter.cboCurrency1.setSelectedIndex(indexCBOTo_original);
        this.pnlConverter.cboCurrency2.setSelectedIndex(indexCBOFrom_original);
    }

    private void OnClickConverter() {
        final String inputMountText = this.pnlConverter.txtInputFrom.getText();
        double inputMount = textToMount(inputMountText);
        if (inputMount > 0) {
            String codeFrom, codeTo;
            codeFrom = ((CurrencyItem) this.pnlConverter.cboCurrency1.getSelectedItem()).getCurrencyCode();
            codeTo = ((CurrencyItem) this.pnlConverter.cboCurrency2.getSelectedItem()).getCurrencyCode();

            double result = -1;
            double conversionRate = getConversionRate(codeFrom, codeTo);
            if (conversionRate > 0) {
                result = inputMount * conversionRate;
                this.pnlConverter.txtResultConverted.setText(Helper.roundedDecimals(result, 2));
                saveConversionToJson(codeFrom, inputMount, codeTo, Double.parseDouble(Helper.roundedDecimals(result, 2)));
            }
        }
    }
    
    private double textToMount(String text){
        double result = -1;
        try{
            result = Double.parseDouble(text.trim());
        }catch(NumberFormatException ex){
            result = 1;
            JOptionPane.showMessageDialog(this.pnlConverter, languageLoader.getValue("CONVERTER.errorInvalidInput"), languageLoader.getValue("CONVERTER.errorInvalidInputTitle"), JOptionPane.ERROR_MESSAGE);
        }            
        return result;
    }

    private void saveConversionToJson(String codeFrom, double inputAmount, String codeTo, double result) {
        // Obtener la fecha y hora actual (Local)
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = now.format(formatter);

        // Crear el objeto JSON con los datos
        JSONObject conversionData = new JSONObject();
        conversionData.put("date", formattedDate);
        conversionData.put("from_currency", codeFrom);
        conversionData.put("input_amount", inputAmount);
        conversionData.put("to_currency", codeTo);
        conversionData.put("result_amount", result);

        // Ruta para guardar el archivo JSON
        String currentPath = StringVariables.CURRENT_PATH + "/resources/data/";
        String filePath = Paths.get(currentPath, "conversion_history.json").toString();

        // Crear el directorio si no existe
        File directory = new File(currentPath);
        if (!directory.exists()) {
            directory.mkdirs(); // Crea el directorio y todos los directorios intermedios
        }

        // Leer el archivo existente y agregar la nueva conversión
        JSONArray conversionHistory = new JSONArray();
        File file = new File(filePath);
        if (file.exists()) {
            try {
                String content = new String(Files.readAllBytes(file.toPath()));
                conversionHistory = new JSONArray(content);
            } catch (IOException e) {
                Logger.getLogger(ControllerConverter.class.getName()).log(Level.SEVERE, "Failed to read existing JSON file", e);
            }
        }

        // Agregar la nueva conversión al arreglo
        conversionHistory.put(conversionData);

        // Escribir el arreglo JSON en el archivo
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(conversionHistory.toString(4)); // El segundo parámetro es para la indentación
        } catch (IOException e) {
            Logger.getLogger(ControllerConverter.class.getName()).log(Level.SEVERE, "Failed to save conversion to JSON file", e);
        }
    }

    private double getConversionRate(String codeFrom, String codeTo) {
        if(codeFrom.equalsIgnoreCase(codeTo)){
            return 1.0;
        }
        
        double conversionRate = -1;        
        Object res = Helper.getJSON_FromAPI_ENDPOINT(String.format(StringVariables.API_ENDPOINT_PAIR_CONVERSION, Helper.getApikey(), codeFrom, codeTo));
        
        if (res instanceof JSONObject) {
            JSONObject jsonResponse = (JSONObject) res;

            if (jsonResponse.has("result") && jsonResponse.getString("result").equals("success")) {
                conversionRate = jsonResponse.getDouble("conversion_rate");
            } else {
                System.out.println("Error al obtener respuesta.");
            }
        } else {
            System.out.println("Respuesta JSON inválida.");
        }
        return conversionRate;
    }

    private void OnChangeSelectedCBO(JComboBox e) {
        this.pnlConverter.txtInputFrom.setText("");
        this.pnlConverter.txtResultConverted.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        Object e = evt.getSource();
        if (e == this.pnlConverter.btnChangeOrder) {
            OnClickSwapCBO();
        } else if (e == this.pnlConverter.btnConverter) {
            OnClickConverter();
        } else if (e == this.pnlConverter.cboCurrency1 || e == this.pnlConverter.cboCurrency1) {
            OnChangeSelectedCBO((JComboBox) e);
        }
    }

}

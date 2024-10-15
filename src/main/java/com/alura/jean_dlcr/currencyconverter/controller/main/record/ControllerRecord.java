/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alura.jean_dlcr.currencyconverter.controller.main.record;

import com.alura.jean_dlcr.currencyconverter.util.LanguageYMLLoader;
import com.alura.jean_dlcr.currencyconverter.util.StringVariables;
import com.alura.jean_dlcr.currencyconverter.view.main.JFMain;
import com.alura.jean_dlcr.currencyconverter.view.main.record.JPRecord;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.table.DefaultTableModel;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Jean
 */
public class ControllerRecord {

    private LanguageYMLLoader languageLoader;
    private JFMain mainView;

    private String labelButton;
    private JSONArray jsonArray;
    private JPRecord pnlRecord;

    private DefaultTableModel model;

    public ControllerRecord(JFMain mainView, LanguageYMLLoader languageLoader, JPRecord pnlRecord, String labelButton) {
        this.mainView = mainView;
        this.languageLoader = languageLoader;
        this.pnlRecord = pnlRecord;
        this.labelButton = labelButton;
        init();
        events();
    }

    private void init() {
        this.mainView.setTitle(String.format("%s - %s", this.mainView.getTitle(), labelButton));
        model = (DefaultTableModel) this.pnlRecord.tblRecord.getModel();
        model.setRowCount(0);
        readJsonFile();
    }

    private void events() {

    }

    private void readJsonFile() {
        String filePath = StringVariables.CURRENT_PATH + "/resources/data/conversion_history.json";

       try {
            // Leer el archivo JSON
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
       
            JSONArray conversionHistory = new JSONArray(content);
     
            for (int i = 0; i < conversionHistory.length(); i++) {
                JSONObject conversionData = conversionHistory.getJSONObject(i);
            
                Object[]rowData = {
                    conversionData.getString("date"),
                    conversionData.getString("from_currency"),
                    conversionData.getDouble("input_amount"),
                    conversionData.getString("to_currency"),
                    conversionData.getDouble("result_amount")
                };
                model.addRow(rowData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

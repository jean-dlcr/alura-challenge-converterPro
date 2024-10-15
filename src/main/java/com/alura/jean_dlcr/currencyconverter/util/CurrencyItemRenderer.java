/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alura.jean_dlcr.currencyconverter.util;

import com.alura.jean_dlcr.currencyconverter.model.currency.CurrencyItem;
import com.alura.jean_dlcr.currencyconverter.view.welcome.JPCustomFlagItemCbo;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

/**
 *
 * @author Jean
 */
public class CurrencyItemRenderer extends JPanel implements ListCellRenderer<CurrencyItem> {

    private JPCustomFlagItemCbo panel;

    public CurrencyItemRenderer() {
        panel = new JPCustomFlagItemCbo();
        panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        panel.langLabel.setFont(new java.awt.Font("Verdana", 1, 12));
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends CurrencyItem> list, CurrencyItem value, int index, boolean isSelected, boolean cellHasFocus) {
        
        panel.setData(value.getFlagPath(), value.getCurrencyCode());
        if (isSelected) {
            if (!panel.getBackground().equals(list.getSelectionBackground())) {
                panel.setBackground(list.getSelectionBackground());
            }
        } else {
            if (!panel.getBackground().equals(Color.WHITE)) {
                panel.setBackground(Color.WHITE);
            }
        }
        return panel;
    }
}
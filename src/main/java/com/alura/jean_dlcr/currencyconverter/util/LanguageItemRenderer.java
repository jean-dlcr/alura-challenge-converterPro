/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alura.jean_dlcr.currencyconverter.util;

import com.alura.jean_dlcr.currencyconverter.model.lang.LanguageItem;
import com.alura.jean_dlcr.currencyconverter.view.welcome.JPCustomFlagItemCbo;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

/**
 *
 * @author Jean
 */
public class LanguageItemRenderer extends JPanel implements ListCellRenderer<LanguageItem> {

    private JPCustomFlagItemCbo panel;

    public LanguageItemRenderer() {
        panel = new JPCustomFlagItemCbo();
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends LanguageItem> list, LanguageItem value, int index, boolean isSelected, boolean cellHasFocus) {
        panel.setData(value.getImgFlagPath(), value.getLanguage());
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

/*
if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
*/

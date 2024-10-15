/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.alura.jean_dlcr.currencyconverter.controller;

import javax.swing.JDialog;

public interface ListenerApiKeyDialog<T> {

    void onDialogComplete(T refController, boolean ready, String apikey);
    
}

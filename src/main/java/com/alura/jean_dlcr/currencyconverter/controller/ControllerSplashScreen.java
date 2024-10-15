/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alura.jean_dlcr.currencyconverter.controller;

import com.alura.jean_dlcr.currencyconverter.util.StringVariables;
import com.alura.jean_dlcr.currencyconverter.view.loading.JFSplashScreen;
import javax.swing.JFrame;


/**
 *
 * @author Jean
 */
public class ControllerSplashScreen {
    private JFSplashScreen splashScreenView;
    
    public ControllerSplashScreen(){
        this.splashScreenView = new JFSplashScreen();
        this.splashScreenView.setTitle(StringVariables.BRAND);
        this.splashScreenView.lblBrand.setText(StringVariables.BRAND);        
    }
    
    public void dispose(){
        this.splashScreenView.dispose();
    }
    
    public void show(){
        this.splashScreenView.setLocationRelativeTo(null);
        this.splashScreenView.setVisible(true);
    }
    
    public void setProgress(int progress) {
        this.splashScreenView.prgBar.setValue(progress);
    }
    
     public void setStatus(String status) {
       this.splashScreenView.lblStatus.setText(status);
    }
     
    public JFrame getJFrame(){
        return this.splashScreenView;
    }
           
}

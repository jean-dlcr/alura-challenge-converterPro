
package com.alura.jean_dlcr.currencyconverter;

import com.alura.jean_dlcr.currencyconverter.controller.config.ControllerConfig;
import com.alura.jean_dlcr.currencyconverter.controller.ControllerApp;
import com.alura.jean_dlcr.currencyconverter.controller.ControllerSplashScreen;
import com.alura.jean_dlcr.currencyconverter.controller.welcome.ControllerWelcome;
import com.alura.jean_dlcr.currencyconverter.controller.InitializationTask;
import com.alura.jean_dlcr.currencyconverter.model.lang.LanguageItem;
import com.alura.jean_dlcr.currencyconverter.util.LanguageYMLLoader;
import java.util.ArrayList;
import javax.swing.UIManager;

public class main {

    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        //SplashScreen
        ControllerSplashScreen splashScreenController = new ControllerSplashScreen();
        splashScreenController.show();
        
        InitializationTask initTask = new InitializationTask(splashScreenController);
        initTask.execute();
           
        
        
    }
}

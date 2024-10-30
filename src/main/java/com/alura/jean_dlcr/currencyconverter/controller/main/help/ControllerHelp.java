package com.alura.jean_dlcr.currencyconverter.controller.main.help;

import com.alura.jean_dlcr.currencyconverter.controller.api.ControllerApi;
import com.alura.jean_dlcr.currencyconverter.util.LanguageYMLLoader;
import com.alura.jean_dlcr.currencyconverter.util.StringVariables;
import com.alura.jean_dlcr.currencyconverter.view.main.JFMain;
import com.alura.jean_dlcr.currencyconverter.view.main.converter.JPConverter;
import com.alura.jean_dlcr.currencyconverter.view.main.help.JPHelp;
import java.awt.Point;
import java.awt.event.ActionListener;

/**
 *
 * @author Jean
 */
public class ControllerHelp {

    private final JFMain mainView;
    private final JPHelp pnlHelp;
    private final LanguageYMLLoader languageLoader;
    private final String labelButton;

    public ControllerHelp(JFMain mainView, LanguageYMLLoader languageLoader, JPHelp pnlHelp, String labelButton) {
        this.mainView = mainView;
        this.languageLoader = languageLoader;
        this.pnlHelp = pnlHelp;
        this.labelButton = labelButton;
        init();
        events();
    }

    private void init() {
        this.mainView.setTitle(String.format("%s - %s", StringVariables.BRAND, labelButton));
        this.pnlHelp.lblTitle.setText(languageLoader.getValue("HELP.title"));
        fillTextPane();
        this.pnlHelp.scrollPane.getVerticalScrollBar().setValue(0);
    }

    private void events() {

    }

    private void fillTextPane() {
    StringBuilder htmlContent = new StringBuilder("<html><body style='background-color:rgba(255,255,255,0);"
                                                                + "font-family:Verdana;font-size:9px;''>"
                                                    + "<p style='margin:0;padding:0;'><br>");
    String stepText = "";
    String text="";
    int stepIndex = 1;
    while (true) {
        stepText = languageLoader.getValue(String.format("HELP.step%s", stepIndex));

        if (stepIndex == 4) {
            stepText = String.format(stepText, '"' + languageLoader.getValue("CONVERTER.btnConverter") + '"');
        }
        if (stepText == null || stepText.isEmpty()) {
            break;
        }
        text = String.format("%s. %s",stepIndex,stepText);
        System.out.println(text);
        htmlContent.append(text).append("<br><br>");
        stepIndex++;
    }

    String iconPath = getClass().getResource("/assets/icons/bidirectional.png").toString();
    String iconLi = String.format(
        "</p><table style=''>" +
            "<tr>" +
            "<td><img src='%s' width='25' height='25'/></td>" +
            "<td style='vertical-align: middle;'><span style=''>%s</span></td>" +
            "</tr>" +
            "</table>",
        iconPath,
        languageLoader.getValue("HELP.note1")
    );

    htmlContent.append(iconLi);
    htmlContent.append("</body></html>");
    this.pnlHelp.txtPnl.setContentType("text/html");
    this.pnlHelp.txtPnl.setText(htmlContent.toString());
        System.out.println(this.pnlHelp.txtPnl.getText());
}


}

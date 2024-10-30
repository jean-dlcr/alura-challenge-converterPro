/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alura.jean_dlcr.currencyconverter.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author Jean
 */

public class RoundButton extends JButton {
    private int radius;
    private Icon icon;
    private int marginLeft;

    public RoundButton(String text, int radius, Icon icon, int marginLeft) {
        //super(text);
        this.radius = radius;
        this.marginLeft = marginLeft;
        this.icon = icon;
        setContentAreaFilled(false);
        setOpaque(false);
    }
    
    public RoundButton(String text, int radius) {
        super(text);
        this.radius = radius;
        this.icon = null;
        setContentAreaFilled(false);
        setOpaque(false);
    }
    

 
    
    /*protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
        super.paintComponent(g);
        g2.dispose();
    }*/
       @Override
    protected void paintComponent(Graphics g) {
    Graphics2D g2 = (Graphics2D) g.create();
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2.setColor(getBackground());
    g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

    //Left Icon, margin 5px
    if (icon != null) {
        
        //Center text
        FontMetrics fm = g2.getFontMetrics();
    int textX = (getWidth() - fm.stringWidth(getText())) / 2;
    int textY = (getHeight() + fm.getAscent()) / 2 - 2;
    icon.paintIcon(this, g2, (textX-icon.getIconWidth()-marginLeft), (getHeight() - icon.getIconHeight()) / 2);
    g2.setColor(getForeground());
    g2.drawString(getText(), textX, textY);
    }

    
    

    g2.dispose();
    super.paintComponent(g);
}
    
    

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getForeground());
        g2.drawRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
        g2.dispose();
    }
}


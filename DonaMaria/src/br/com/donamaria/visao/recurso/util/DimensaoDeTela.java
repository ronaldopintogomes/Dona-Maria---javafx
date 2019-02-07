/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.donamaria.visao.recurso.util;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 *
 * @author Ronaldo
 */
public class DimensaoDeTela {
 
    public static double getWidth() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        return dimension.getWidth();
    }
    
    public static double getHeight() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        return dimension.getHeight()-80;
    }

}
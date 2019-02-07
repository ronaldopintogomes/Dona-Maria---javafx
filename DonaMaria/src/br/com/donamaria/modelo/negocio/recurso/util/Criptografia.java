/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.donamaria.modelo.negocio.recurso.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Ronaldo
 */
public class Criptografia {
    
    public static String encriptar(String senha) {
        String senhaCriptografada = null;
        try {
            MessageDigest algoritmo = MessageDigest.getInstance("MD5");
            byte messageDigest[] = algoritmo.digest(senha.getBytes("UTF-8"));
            StringBuilder stringBuilder = new StringBuilder();
            for(byte b : messageDigest) {
                stringBuilder.append(String.format("%02X", 0xFF & b));
            }
            senhaCriptografada = stringBuilder.toString();
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
        
        return senhaCriptografada;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unrarpass.crypto;

/**
 *
 * @author Bruno Reinicke
 */
public class Crypto {
    
    public void decrypt(String pass, String crypto, int pos) {
        if (crypto.equals("fg")) {
            System.out.println(crypto);
            System.exit(0);
        }
        String aux = "";
        for (int i = 0; i < crypto.length(); i++)
            if (!aux.contains(crypto.charAt(i) + ""))
                aux = aux + crypto.charAt(i);
                    
        String caract = " 0123456789" 
                      + "abcdefghijklmnopqrstuvwxyz"
                      + "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                      + "'!@#$%¨&*()-_=+´`[{~^]}|,<.>;:/\\\"?";
        caract = caract.charAt(pos) + "";
        crypto = crypto.replaceAll(aux, caract);
        pos++;
        decrypt(pass, crypto, pos);
    }
}
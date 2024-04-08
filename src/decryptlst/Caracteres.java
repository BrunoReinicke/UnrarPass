/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package decryptlst;

import java.util.ArrayList;

/**
 *
 * @author Bruno Reinicke
 */
public class Caracteres {
    
    private final String caract;
    private final int pos;
    private final int aux;
    private final String senha;
    private final int z;
    private final ArrayList<String> lista;
    
    public Caracteres() {
        this.caract = " 0123456789" 
                    + "abcdefghijklmnopqrstuvwxyz"
                    + "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                    + "'!@#$%¨&*()-_=+´`[{~^]}|,<.>;:/?";
        this.senha = "";
        this.pos = 0;
        this.z  = 0;
        this.aux = -1;
        this.lista = new ArrayList<String>();
    }
    
    public void povoaLista() {
        this.lista.add(this.caract);
    }
    
    public void percorrer(String senha, int x, boolean found, String pass) { 
        if (!found) {
            for (int i = x; i < this.lista.size(); i++) {
                x++;
                if (found)
                    break;
                for (int y = 0; y < this.lista.get(i).length(); y++) {
                    if ((senha + this.lista.get(i).charAt(y)).equals("Xlly")) {
                        System.out.println(senha + this.lista.get(i).charAt(y));
                        System.exit(0);
                    }
                    pass = senha + this.lista.get(i).charAt(y);
                    percorrer(senha + this.lista.get(i).charAt(y), x, found, pass);
                }   
                if (found) 
                    break;
            }
        } else {
            System.out.println(pass);
            System.exit(0);
        }
        String aux = "";
        for (String lista1 : this.lista) 
            aux = aux + "?";
        if (pass.equals(aux)) {
            this.lista.add(this.caract);
            percorrer("", 0, false, "");
        }
    }
}
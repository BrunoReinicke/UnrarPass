/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unrarpass.crypto;

/**
 *
 * @author Bruno Reinicke
 */
public class Caracteres {
    
    private String caract;
    private Caracteres next;
    private Caracteres ant;
    private String word;
    private String senha;
    private int count;
    
    public Caracteres() {
        this.caract = " 0123456789" 
                    + "abcdefghijklmnopqrstuvwxyz"
                    + "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                    + "'!@#$%¨&*()-_=+´`[{~^]}|,<.>;:/\\\"?";
        this.senha = "";
    }

    public void setCaract(String caract) {
        this.caract = caract;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
    public String getCaract() {
        return this.caract;
    }
    
    public void setNext(Caracteres next) {
        this.next = next;
    }
    
    public void setAnt(Caracteres ant) {
        this.ant = ant;
    }
    
    public Caracteres getNext() {
        return this.next;
    }
    
    public Caracteres getAnt() {
        return this.ant;
    }
    
    public void setWord(String word) {
        this.word = word;
    }
    
    public String getWord() {
        return this.word;
    }
    
    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public void percorrer(String pass, String passAux) {
        for (int i = 0; i < this.caract.length(); i++) {
            if (this.next != null) {
                this.next.setSenha(this.senha + "" + this.caract.charAt(i));
                this.next.percorrer(pass, passAux);
            } else {
                String clear  = pass;    
                
                for (int j = 0; j < (this.senha + this.caract.charAt(i)).length(); j++) 
                    while (clear.contains(passAux.charAt(j) + "")) 
                        clear = clear.replace(passAux.charAt(j) + "", 
                                              (this.senha + this.caract.charAt(i)).charAt(j) + "");
                
                if (clear.equals("4yllx")) {
                    System.out.println(pass + " = " + clear);
                    System.exit(0);
                }
            }
        }
    }
}
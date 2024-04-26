/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unrarpass.unrar;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.IOException;

/**
 *
 * @author Bruno Reinicke
 */
public class Caracteres {
    
    private String caract;
    private int pos;
    private Caracteres next;
    private Caracteres ant;
    private String word;
    private String senha;
    private int count;
    private int line;
    private long match;
    private boolean stop;
    private boolean manual;
    
    public Caracteres() {
        this.caract = " 0123456789" 
                    + "abcdefghijklmnopqrstuvwxyz"
                    + "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                    + "'!@#$%¨&*()-_=+´`[{~^]}|,<.>;:/\\\"?";
        this.senha = "";
        this.pos = -1;
        this.line = 0;
        this.match = 0;
        this.manual = false;
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
    
    public int getPos() {
        return this.pos;
    }
    
    public void setPos(int pos) {
        this.pos = pos;
    }
    
    public void setLine(int line) {
        this.line = line;
    }
    
    public void setManual(boolean manual) {
        this.manual = manual;
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

    public void setStop(boolean stop) {
        this.stop = stop;
    }
    
    public void percorrer() {
        int x = this.pos;
        if (x == -1)
            x = 0;
            
        for (int i = x; i < this.caract.length(); i++) {
            if (this.next != null) {
                this.next.setSenha(this.senha + "" + this.caract.charAt(i));
                this.next.percorrer();
            } else {
                if (this.match == 1000000000) {
                    new Decrypter().SaveToFile(this.senha + this.caract.charAt(i));
                    this.match = 0;
                } 
                this.extractRarFilePass(this.senha + this.caract.charAt(i));
                //this.md5HashGenerator(this.senha + this.caract.charAt(i));
                this.match++;
            }
            this.pos = i;
            if (this.pos == this.caract.length()-1)
                this.pos = 0;
        } 
        if ((this.ant == null) && (this.pos == 0)) {
            if (this.next != null) {
                Caracteres aux = this.next;
                if (aux != null) {
                    while ((aux != null) && (aux.getNext() != null))
                        aux = aux.getNext();
                    Caracteres aux2 = new Caracteres();
                    aux.setNext(aux2);
                    aux2.setAnt(aux);
                }
            } else {
                Caracteres aux = new Caracteres();
                aux.setAnt(this);
                aux.setCount(this.count);
                this.next = aux;             
            }
            this.percorrer();
        }  
    }
    
    public void extractRarFile(String rarFilePath, String destinationPath, String password) {
        try {
            password = password.replaceAll("\"", "\"\"");
            ProcessBuilder processBuilder = new ProcessBuilder("unrar", "x", "-p"+password, rarFilePath, destinationPath);
            processBuilder.inheritIO();
            Process process = processBuilder.start();
            process.waitFor();
            
            if (process.exitValue() == 0) {
                System.out.println(password);
                System.exit(0); 
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }      
    }

    public void extractRarFilePass(String password) {
        String rarFilePath = "C:/Users/Bruno Reinicke/Documents/TESTE/Teste.rar";
        String destinationPath = "C:/Users/Bruno Reinicke/Documents/TESTE/Extracao";
        extractRarFile(rarFilePath, destinationPath, password);
    }
    
    public void md5HashGenerator(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = password.getBytes();
            md.update(bytes);
            byte[] digest = md.digest();

            StringBuilder hexString = new StringBuilder();
            for (byte b : digest) {
                hexString.append(String.format("%02x", b & 0xff));
            }
            if (hexString.toString().equals("3071da78e08fd27916f96631dc4af3e7")) {
                System.out.println(password);
                System.exit(0);
            }
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Algoritmo de hash MD5 não encontrado.");
            e.printStackTrace();
        }
    }
}
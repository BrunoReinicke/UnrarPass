/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unrarpass.unrar;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author Bruno Reinicke
 */
public class Decrypter {

    private final int pos;
    private int count;
    private Caracteres cara;
    private final String pass;
    
    public Decrypter () {
        this.count = 0;
        this.pos = 0;
        this.pass = this.getMatch();
    }
    
    public Caracteres povoaLista(Caracteres aux) {
        Caracteres aux2 = new Caracteres();
  
        while (this.count < (this.pass.length() - 1)) {
            while (aux.getNext() != null)
                aux = aux.getNext();
            Caracteres cara2  = aux;
            Caracteres cara3 = new Caracteres();
            cara2.setNext(cara3);
            cara3.setAnt(cara2); 
            cara3.setPos(aux.getCaract().indexOf(this.pass.charAt(this.count + 1)));
            aux2 = cara2;
            this.count++;
            povoaLista(aux2);
        }
        return aux2;
    }
    
    public void decrypt() {
        Caracteres aux = new Caracteres();
        this.cara  = this.povoaLista(aux);
        this.cara.setPos(aux.getCaract().indexOf(this.pass.charAt(0)));
        this.cara.percorrer();
    }
    
    public void readFile() {
        String fileName = "C:/Users/Bruno Reinicke/Documents/TESTE/SENHAS.txt";
        List<String> list = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            list = stream.map(String::toUpperCase).collect(Collectors.toList());
            System.out.println("OK!");
        } catch (IOException e) {
        }
    }
    
    public String getMatch() {
        FileReader fr;
        BufferedReader reader;
        String line  = "";
        String match = "";
        
        try {
            fr = new FileReader("C:/Users/Bruno Reinicke/Documents/Hacking/SENHAS/Password.txt");
            reader = new BufferedReader(fr);
            try {
                while (line != null) {
                    line = reader.readLine();
                    if (line != null)
                        match = line;
                }
            } catch (IOException ex) {
            }
        } catch (FileNotFoundException ex) { 
        }
        return match;
    }
    
    public void SaveToFile(String line) {
        FileWriter fw;
        BufferedWriter writer;
        try {
            fw = new FileWriter("C:/Users/Bruno Reinicke/Documents/Hacking/SENHAS/Password.txt", true);
            writer = new BufferedWriter(fw);
            writer.write(line);
            writer.newLine();
            writer.close();
        } catch (IOException ex) {
            //
        }      
    }
}
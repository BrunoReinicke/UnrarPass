/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unrarpass.crypto;

/**
 *
 * @author Bruno Reinicke
 */
public class Decrypter {

    private int count;
    private Caracteres cara;
    private String passAux;
    
    public Decrypter () {
        this.count = 0; 
        this.passAux = "";
    }
    
    public Caracteres povoaLista(Caracteres aux) {
        Caracteres aux2 = new Caracteres();

        while (this.count < (this.passAux.length() - 1)) {
            while (aux.getNext() != null)
                aux = aux.getNext();
            Caracteres cara2  = aux;
            Caracteres cara3 = new Caracteres();
            cara2.setNext(cara3);
            cara3.setAnt(cara2); 
            aux2 = cara2;
            this.count++;
            povoaLista(aux2);
        }
        return aux2;
    }
    
    public void decrypt() {
        String pass = "âãääå";
        
        for (int i = 0; i < pass.length(); i++)
            if (!this.passAux.contains(pass.charAt(i) + ""))
                this.passAux = this.passAux + pass.charAt(i); 
        
        Caracteres aux = new Caracteres();
        this.cara  = this.povoaLista(aux);
        this.cara.percorrer(pass, this.passAux);
    }
}
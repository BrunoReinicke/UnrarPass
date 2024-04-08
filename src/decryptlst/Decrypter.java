/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package decryptlst;

/**
 *
 * @author Bruno Reinicke
 */
public class Decrypter {
    
    public void decrypt() {
        Caracteres car = new Caracteres();
        car.povoaLista();
        car.percorrer("", 0, false, "");
    }
}
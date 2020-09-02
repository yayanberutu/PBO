/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package no1_kuis2_pbo;

/**
 *
 * @author Sarah Christine (11318058)
 */
public class DriverThread {
    public static void main(String[] args) {    
    new Thread(new ClassA("Objek A")).start();
    new Thread(new ClassB("Objek B")).start();
        
    }
}

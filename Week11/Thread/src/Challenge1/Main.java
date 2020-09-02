/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Challenge1;

/**
 *
 * @author ASUS
 */
public class Main {
    public static void main(String[] args) {
        new Thread(new ClassA("Ini dari class A")).start();
        new Thread(new ClassB("Ini dari class B")).start();
    }
    
}

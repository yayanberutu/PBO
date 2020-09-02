/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multiprocessesdiffclasses;

/**
 *
 * @author ASUS
 */
public class MultiProcessesDiffClasses {
 
    public static void main(String[] args) {
        ClassA objA = new ClassA("Objek A");
        ClassB objB = new ClassB("Objek B");
        
        objA.start(); //loop 0-4
        objB.start(); //loop 0-9
    }
}

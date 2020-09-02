/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rabbitholeapp;

/**
 *
 * @author ASUS
 */
public class RabbitHoleApp {

    public static void main(String[] args) {
        Hole objHole = new Hole();
        Human objHuman = new Human(objHole);
        Rabbit objRabbit = new Rabbit(objHole);
        
        objHuman.start();
        objRabbit.start();
    }
}

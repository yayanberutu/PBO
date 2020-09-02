/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rabbitholeapp;

import java.util.Random;

/**
 *
 * @author ASUS
 */
public class Rabbit extends Thread{
    private Hole hole;
    
    public Rabbit(Hole hole){
        this.hole = hole;
    }
    
    public void run(){
        while(true){
            
            try {
                int randNum = getRandomInRange(0,9);
                if(randNum == this.hole.getValue()) System.out.println("Got it! " + "Your rabit is in your bag now.");
                else System.err.println(String.format("Sorry, you missed the rabbit " + "The rabbit needs number %d. Please try again!", randNum));
            }
            catch(Exception ie){
                ie.printStackTrace();
            }
        }
    }
    public static int getRandomInRange(int min, int max){
        if(min >= max) throw new IllegalArgumentException("Max must be greater than Min");
        Random r = new Random();
        
        return r.nextInt((max-min + 1) + min);
    }
}

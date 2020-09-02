/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Challenge2;

import rabbitholeapp.*;
import java.util.Scanner;

/**
 *
 * @author ASUS
 */
public class Human implements Runnable {
    private volatile boolean exit = false;
    private Thread currThread = null;
    private Hole hole;
    
    public Human(Hole hole){
        this.hole = hole;
    }
    
    @Override
    public void run() {
         while(!exit){
             try{
                 Scanner _scanner = new Scanner(System.in);
                 System.out.println("Enter any number ranging from 0-9 to get yout lovely rabbit ");
                   
                 int num = Integer.valueOf(_scanner.nextLine());
                 
                 if(num < 0) this.stop();
                 else hole.putValue(num);
             }
             catch(Exception ex){
                 System.out.println(ex.getMessage());
             }
         }
    }
    
    public void stop(){
        exit = true;
    }
    
    void start(){
        if(currThread == null){
            currThread = new Thread(this);
            currThread.start();
        }
    }
}

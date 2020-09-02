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
public class Hole {
    private int value;
    private boolean isAvailable;
    
    public synchronized int getValue(){
        while(!isAvailable){
            try {
                this.wait();
              
            }
            catch(InterruptedException ie){
                ie.printStackTrace();
            }
        }
        this.isAvailable = false;
        this.notifyAll();
        return value;
        
    }
    
    public synchronized void putValue(int value){
        while(isAvailable){
            try{
                this.wait();
            }
            catch(InterruptedException ie){
                ie.printStackTrace();
            }
        }
        
        this.isAvailable = true;
        this.value = value;
        this.notifyAll();
    }
}

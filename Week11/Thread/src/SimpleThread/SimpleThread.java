/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SimpleThread;

import java.util.Random;

/**
 *
 * @author ASUS
 */
public class SimpleThread extends Thread{
   public SimpleThread(String nama){
       super(nama);
   }
   
   public void run(){
       for(int i=0; i<5; i++){
           try {
               long time = getRandomNumberInRange(1,10) * 1000;
               System.out.println(String.format("[%s] ini proses ke-%d membutuhkan waktu %d detik", this.getName(), i, time));
               Thread.sleep(time);
           }
           catch(Exception e){
               System.out.println("Error " + e.getMessage());
           }
       }
   }
   
    public static void main(String[] args) {
     
        new SimpleThread("Thread dari Objek 1").start();
        new SimpleThread("Thread dari Objek 2").start();
    }
    
    private static int getRandomNumberInRange(int min, int max){
        if(min >= max) throw new IllegalArgumentException("max must be greater ttan min");
        Random r = new Random();
        return r.nextInt((max - min) + min);
    }
}

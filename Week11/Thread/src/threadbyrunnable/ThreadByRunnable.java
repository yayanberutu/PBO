/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadbyrunnable;

import java.util.Random;
import static multiprocessesdiffclasses.ClassA.getRandomNumberInRange;

/**
 *
 * @author ASUS
 */
public class ThreadByRunnable implements Runnable {
    private String nama;
    
    public ThreadByRunnable(String nama){
        this.nama = nama;
    }
    
    @Override
    public void run() { 
        for(int i=0; i<5; i++){
            try {
                long time = getRandomNumberInRange(1,10) * 1000;
                System.out.println(String.format("[%s] ini proses ke-%d membutuhkan waktu %d detik", this.nama, i, time));
                Thread.sleep(time);
            }
            catch(Exception e){
                System.out.println("Error " + e.getMessage());
            }
        }
    }
    
    public static void main(String[] args) {
        new Thread(new ThreadByRunnable("Thread dari objek 1")).start();
        new Thread(new ThreadByRunnable("Thread dari objek 2")).start();
    }
    
    public static int getRandomNumberInRange(int min, int max){
        if(min >= max) throw new IllegalArgumentException("max must be greater than min");
        Random r = new Random();
        return r.nextInt((max-min) + 1) + min;
    }
   
}

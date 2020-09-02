/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multiprocessesdiffclasses;

import java.util.Random;

/**
 *
 * @author ASUS
 */
public class ClassB extends Thread {
    public ClassB(String nama){
        super(nama);
    }
    
    public void run(){
        System.out.println("Proses lain dari kelas B dijalankan..");
        for(int i=0; i<10; i++){
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
    public static int getRandomNumberInRange(int min, int max){
        if(min >= max) throw new IllegalArgumentException("max must be greater than min");
        Random r = new Random();
        return r.nextInt((max-min) + 1) + min;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package no1_kuis2_pbo;

import java.util.Random;
/**
 *
 * @author Sarah Christine (11318058)
 */
public class ClassA implements Runnable{
    private String nama;
    
    public ClassA(String nama){
        this.nama = nama;
    }
    
    public void run(){
        System.out.println("Proses dari kelas A dijalankan..");
        for(int i=0; i<3; i++){
            try{
                long time = getRandomNumberInRange(1, 10)*1000;
                System.out.println(String.format("[%s] ini proses ke-%d membutuhkan waktu %d detik", this.nama, i, time));
                Thread.sleep(time);
            }
            catch(Exception e){
                System.out.println("Error "+e.getMessage());
            }
        }
    }
    
    private static int getRandomNumberInRange(int min, int max){
        if(min >= max) throw new IllegalArgumentException("max must be greater than min");
        Random r = new Random();
        return r.nextInt((max-min)+1) + min;
    }
}

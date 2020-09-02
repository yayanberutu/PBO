/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package with_multi_thread;

import java.util.Random;

/**
 *
 * @author Yosepri Berutu
 */
public class ClassB implements Runnable {
    private String nama;
    private int n;
    
    public ClassB(String nama, int n){
        this.nama = nama;
        this.n = n;
    }
    
    @Override
    public void run() {
        for(int i=0; i<5; i++){
            try{
                System.out.println(String.format("Halo! Nama kamu adalah %s. Ini berasal dari Class B", this.nama));
//                Thread.sleep(2);
            }
            catch(Exception e){
                System.out.println("Error " + e.getMessage());
            }
            
        }
    }

}

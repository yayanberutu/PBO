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
public class ClassA implements Runnable{
    private String nama;
    private int n;
    public ClassA(String nama, int n){
        this.nama = nama;
        this.n = n;
    }
    
    @Override
    public void run() {
        for(int i=0; i<(n/2); i++){
            try{
                System.out.println(String.format("Halo! Nama kamu adalah %s. Ini berasal dari Class A", this.nama));
//                Thread.sleep(1);
            }
            catch(Exception e){
                System.out.println("Error " + e.getMessage());
            }
            
        }
    }
    
}

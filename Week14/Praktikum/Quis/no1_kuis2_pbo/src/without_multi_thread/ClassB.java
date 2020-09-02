/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package without_multi_thread;

/**
 *
 * @author Yosepri Berutu
 */
public class ClassB {
    private String nama;
    private int n;
    public ClassB(String nama, int n) throws InterruptedException{
        this.nama = nama;
        this.n = n;
        this.run();
    }
    
    public void run() throws InterruptedException{
        for(int i=0; i<(n/2); i++){
            System.out.println(String.format("Halo! Nama kamu adalah %s. Ini berasal dari Class B", this.nama));
//            Thread.sleep(2);
        }
    }
        
}

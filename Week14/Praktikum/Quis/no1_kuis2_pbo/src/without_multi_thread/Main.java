/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package without_multi_thread;

import java.util.Scanner;

/**
 *
 * @author Yosepri Berutu
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
       System.out.println("Selamat datang di Aplikasi CETAK NAMA sebanyak n kali");
       System.out.print("Masukkan nama: ");
       String name = new Scanner(System.in).nextLine();
       System.out.print("Masukkan banyak pencetakan: ");
       int n = new Scanner(System.in).nextInt();
       new ClassA(name, n/2);
       new ClassB(name, n/2);
    }
}

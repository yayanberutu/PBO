/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package del.ac.id.tugas.Tugas1;

import java.util.Scanner;

/**
 *
 * @author ASUS
 */
public class Main {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String nama, alamat, notel;
        System.out.print("Masukkan Nama Anda: ");
        nama = scn.nextLine();
        System.out.print("Masukkan Alamat Anda: ");
        alamat = scn.nextLine();
        System.out.print("Masukkan Nomor Telepon: ");
        notel = scn.nextLine();
        String output = "Hallo " + nama + ", alamatmu di " + alamat + "\nNomor teleponmu adalah 08211345678";
        System.out.println(output);
    }
    
}

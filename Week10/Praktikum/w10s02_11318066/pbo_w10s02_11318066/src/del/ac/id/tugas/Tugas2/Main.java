/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package del.ac.id.tugas.Tugas2;

import java.util.Scanner;

/**
 *
 * @author ASUS
 */
public class Main {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String nama;
        int mk;
        System.out.print("Masukkan nama: ");
        nama = scn.nextLine();
        System.out.print("Jumlah mata kuliah: ");
        mk = scn.nextInt();
        int tempnilai=0;
        int nilai;
        for(int i=0; i<mk; i++){
            System.out.print("MK" + (i+1) + ":");
            nilai = scn.nextInt();
            tempnilai += nilai;
        }
        float avg = (float) nilai / mk;
        String hasil = "Putri Kahyangan, nilai rata-rata dari"+ mk + " mata kuliah yang kamu masukkan adalah " + avg;
        System.out.println(hasil);
    }
}

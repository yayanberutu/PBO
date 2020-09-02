/*
Nama : Yosepri Disyandro Berutu
NIM : 11318066
Tanggal : 13 September 2019
 */
import java.util.Scanner;
public class Latihan04 {
    public static void main(String[] args){
        String nama;
        int umur;

        Scanner sc = new Scanner(System.in);

        System.out.println("Masukkan nama Anda: ");
        nama = sc.next();
        System.out.println("Masukkan umur Anda: ");
        umur = sc.nextInt();

        System.out.println("Hello " +nama + " Umur Anda " + umur);
    }
}

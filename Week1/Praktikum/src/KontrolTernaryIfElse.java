/*
Nama : Yosepri Disyandro Berutu
NIM : 11318066
Tanggal : 13 September 2019
 */
import javax.swing.*;

public class KontrolTernaryIfElse    {
    public static void main(String[] args){
        String MasukinNIlai = JOptionPane.showInputDialog("Masukkan Nilai Anda: ");
        int NIlaiku = Integer.parseInt(MasukinNIlai);
        String ket = (NIlaiku >=55 ? "Lulus" : "Gagal");
        System.out.println("Nilai = " + NIlaiku);
        System.out.println("Anda " + ket);
        System.exit(0);
    }
}

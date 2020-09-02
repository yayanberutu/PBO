/*
Nama : Yosepri Disyandro Berutu
NIM : 11318066
Tanggal : 13 September 2019
 */
import javax.swing.*;
public class KontrolIfElseIf {
    public static void main(String[] args){
        boolean lulus;
        String kriteria;
        String MasukinNilai = JOptionPane.showInputDialog("Nilai UTS?");
        int nilai = Integer.parseInt(MasukinNilai);
        if((nilai >=50) && (nilai <= 65)){
            lulus = true;
            kriteria = "Luluc 'C'";
        }
        else if ((nilai >=66) && (nilai <= 80)) {
            lulus = true;
            kriteria = "Lulus 'B'";
        }
        else if((nilai >=81) && (nilai <= 100)){
            lulus = true;
            kriteria = "Lulus 'A'";
        }
        else {
            lulus = false;
            kriteria = "Gagal";
        }

    }
}

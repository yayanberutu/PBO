/*
Nama : Yosepri Disyandro Berutu
NIM : 11318066
Tanggal : 13 September 2019
 */
public class KontrolIf {
    int nilaiUTS = 55;
    boolean lulus;
    String ket;

    public static void main(String[] args){
        KontrolIf Nilaiku = new KontrolIf();
        //Objek baru

        int nilaiutsaku = 65;
        if(nilaiutsaku >= Nilaiku.nilaiUTS){
            Nilaiku.lulus = true;
            Nilaiku.ket = "C";
        }
        System.out.println("Nilai UTS ku = " + nilaiutsaku);
        System.out.println("Status Kelulusan= " + Nilaiku.lulus);
        System.out.println("Keterangan Kelulusan= " + Nilaiku.ket);
    }

}

/*
Nama : Yosepri Disyandro Berutu
NIM : 11318066
Tanggal : 13 September 2019
 */
public class KontrolIfElse {
    int nilaiJava = 55;
    boolean lulus;
    String ket;
    public static void main(String[] args){
        KontrolIfElse HasilUjian = new KontrolIfElse();
       int nilaiJavaAku = 45;
       if(nilaiJavaAku >= HasilUjian.nilaiJava){
           HasilUjian.lulus = true;
           HasilUjian.ket = "Lulus";
       }
       else {
           HasilUjian.lulus = false;
           HasilUjian.ket = "Gagal";
       }
        System.out.println("Nilai Aku = " + nilaiJavaAku);
        System.out.println("Status Kelulusan= " +HasilUjian.lulus);
        System.out.println("Keterangan Kelulusan= " + HasilUjian.ket);

    }
}

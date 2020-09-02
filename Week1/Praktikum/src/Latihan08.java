/*
Nama : Yosepri Disyandro Berutu
NIM : 11318066
Tanggal : 13 September 2019
 */
public class Latihan08 {
    public static void main(String[] args){
        short a = 5, b;
        b = (short) (a+1); //warning
        //karena (a+1) dipromote jadi int
        // dan tidak dapat disimpan pada b (short)
        //fix menjadi b = (short) (a+1)
        int c = 3;
        long d = 5;
        d = d+c; // d+c otomatis di promote jadi long
        System.out.println(d);
    }
}

package introtooop;

import java.util.Scanner;
public class IntroToOOP {

    public static void main(String[] args){
        String _nama, _alamat;
        int _usia;

        System.out.println("Masukkan nama: ");
        _nama = new Scanner(System.in).nextLine();
        System.out.println("Masukkan usia: ");
        _usia = new Scanner(System.in).nextInt();
        System.out.println("Alamat" );
        _alamat = new Scanner(System.in).nextLine();

        System.out.println("========================================\n");

        //instansiasi obejek dari kelas manusia
        Manusia objManusia = new Manusia();
        objManusia.setNama(_nama);
        objManusia.setUsia(_usia);
        objManusia.setAlamat(_alamat);

        objManusia.cetakInformasi(objManusia.getNama(), objManusia.getAlamat(), objManusia.getUsia());
    }

}

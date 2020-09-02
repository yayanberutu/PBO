package del.ac.id.yosepri;

import java.util.Scanner;

import static del.ac.id.yosepri.Toko.*;

public class Driver {

    public static void tambahBarang(){
        Scanner scn = new Scanner(System.in);
        System.out.print("Masukkan ID Barang: ");
        String id = scn.next();
        System.out.print("Masukkan nama barang: ");
        String nama = scn.next();
        System.out.print("Masukkan jumlah barang: ");
        int stok = scn.nextInt();
        System.out.print("Masukkan harga barang: ");
        double harga = scn.nextDouble();
        Barang brg = new Barang(id,stok,harga, nama);

        /*try{
            String id = scn.next();
        }
        catch (idDuplicated idD){

        }
         */

    }

    public static void main(String[] args) {
        boolean stat = true;
        Barang brgs = new Barang("b1", 20, 200000, "Makanan");
        Toko.barang.add(brgs);
        while(true){
            System.out.println("SELAMAT DATANG DI TOKO YDB");
            System.out.println("1. Masuk sebagai Pemilik Toko");
            System.out.println("2. Masuk sebagai pembeli");
            System.out.println("0. Keluar Aplikasi");
            System.out.print("Masukkan pilihan: ");
            Scanner scn = new Scanner(System.in);
            int pilih = scn.nextInt();
            if(pilih == 0){
                System.exit(1);
            }
            if(pilih == 1){
                System.out.print("Masukkan Password: ");
                String ps = scn.next();
                while(! ps.equals("berutu")){
                    System.out.print("Masukkan Password: ");
                    ps = scn.next();
                }
                while(true){
                    System.out.println("1. Tambah Daftar Barang");
//                    System.out.println("2. Ubah detail Barang");
                    System.out.println("3. Cetak Daftar Barang");
                    System.out.println("0. Keluar mode Pembeli");
                    int pilih1 = scn.nextInt();
                    if(pilih1 == 0){

                        break;
                    }
                    if(pilih1 == 1){
                        tambahBarang();
                    }
                    if(pilih1 == 3){
                        Toko.cetakInformasiBarang();
                    }
                }

            }
            else if(pilih == 2){
                double saldo = 2000000;
                System.out.println("1. Cari Barang berdasarkan ID");
                System.out.println("2. Beli Barang");
                System.out.println("0. Keluar dari mode Pembeli");
                System.out.print("Masukkan pilihan: ");
                int pilih1 = scn.nextInt();
                if(pilih1 == 0) break;
                if(pilih1 == 1){
                    System.out.print("Masukkan ID: ");
                    try {
                        String id = scn.next();
                        Barang brg1 = new Barang(id);
                        Toko.cariBarang(brg1);
                        System.out.println("Barang ditemukan!");
                    }
                    catch (IdNotFoundEksepsi infe){
                        System.out.println("Barang tidak ditemukan");
                    }
                }
                if(pilih1 == 2){
                    Toko.cetakInformasiBarang();
                    System.out.print("ID Barang yang ingin di beli: ");
                    String id = scn.next();
                    Barang brg1 = new Barang(id);
                    try {
                        Toko.cariBarang(brg1);
                        System.out.println("Barang ditemukan!");
                        System.out.print("Masukkan jumlah barang yang ingin dibeli: ");
                        int jlh = scn.nextInt();
                        brg1.setStok(jlh);
                        try {
                            beliBarang(brg1, saldo);
                        }
                        catch (StokEksepsi se){
                            System.out.println("Stok Barang tidak cukup!");
                        }
                        catch (UangKurangEksepsi uke){
                            System.out.println("Uang tidak mencukupi");
                        }
                    }
                    catch (IdNotFoundEksepsi infe){
                        System.out.println("Barang tidak ditemukan");
                    }

                }
            }



        }
    }
}

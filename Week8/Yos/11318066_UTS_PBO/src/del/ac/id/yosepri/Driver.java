/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package del.ac.id.yosepri;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author ITD
 */
public class Driver {
   public static KelompokTA TambahKel(){
       Scanner s = new Scanner(System.in);
       System.out.print("ID: ");
       String id = s.next();
       System.out.print("Judul TA: ");
       s.nextLine();
       String Judul = s.nextLine();
       System.out.print("Jumlah Halaman TA: ");
       int jl = s.nextInt();
       KelompokTA kTA = new KelompokTA(id, Judul, jl);
       return kTA;
   }
    
    public static void main(String[] args) {
        ArrayList<KelompokTA> kel = new ArrayList<>();
        ArrayList<Dosen> dosen = new ArrayList<>();
        ArrayList<Mahasiswa> mahasiswa = new ArrayList<>();
        while(true){
            System.out.println("1. Tambah Data Baru (Mahasiswa/DOsen)");
            System.out.println("2. Buat Kelompok TA");
            System.out.println("0. Exit");
            System.out.print("Masukkan pilihan: ");
            Scanner scn = new Scanner(System.in);
            int pilih = scn.nextInt();
            if(pilih == 0) System.exit(0);
            if(pilih == 2) kel.add(TambahKel());
            if(pilih == 1){
                System.out.print("Nama : ");
                scn.nextLine();
                String nama = scn.nextLine();
                System.out.print("Usia : ");
                float usia = scn.nextFloat();
                System.out.print("Jenis_Kelamin(L/P): ");
                String jk = scn.next();
                System.out.print("Pekerjaan(Mahasiswa/Dosen): ");
                scn.nextLine();
                String job = scn.nextLine();
                if(job.toLowerCase().equals("mahasiswa")){
                    System.out.print("NIM: ");
                    String nim = scn.next();
                    Mahasiswa mhs = new Mahasiswa(nama, jk, usia, nim); 
                    if(kel.size() == 0){
                        System.out.println("Kelompok TA masih kosong! Harus ditambah!");
                        kel.add(TambahKel());
                    }
                    else {
                        for(int i=0; i<kel.size(); i++){
                            System.out.println(String.format("ID: %s", kel.get(i).getId()));
                        }
                        System.out.println("Masukkan pilihan: ");
                        String idp = scn.next();
                    }

                }
                else if(job.toLowerCase().equals("dosen")){
                    System.out.print("NIDN :");
                    String nidn = scn.next();
                    System.out.print("Ruangan: ");
                    scn.nextLine();
                    String ruangan = scn.nextLine();
                    System.out.print("Gaji: ");
                    double gaji = scn.nextDouble();
                    System.out.println("Status(Permanent/Kontrak):");
                    String stat = scn.next();
                    if(stat.toLowerCase().equals("tetap")){

                    }
                    else if(stat.toLowerCase().equals("permanent")){

                    }
                    else {
                        System.out.println("Tidak Terdaftar!");
                    }
                }
            }
            
            
        }
        
    }
}

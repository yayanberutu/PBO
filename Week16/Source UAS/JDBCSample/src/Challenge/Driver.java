/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Challenge;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import static jdbcsample.JDBCSample.convertToDateFromString;

/**
 *
 * @author Yosepri Disyandro Berutu
 */
public class Driver {
    
    public static void AddMhs(Connection con, TMahasiswa objMhs, String _sql, PreparedStatement ps) throws SQLException, ParseException{
        objMhs = new TMahasiswa();
        System.out.print("Masukkan NIM: "); objMhs.setNim(new Scanner(System.in).nextLine());
        System.out.print("Masukkan nama mahasiswa: "); objMhs.setNama(new Scanner(System.in).nextLine());       
        System.out.print("Masukkan DOB: ");
        objMhs.setDob(convertToDateFromString(new Scanner(System.in).nextLine(), "dd/MM/yyyy"));
        System.out.print("Masukkan E-mail: "); objMhs.setEmail(new Scanner(System.in).nextLine());
        _sql = "INSERT INTO TMahasiswa(NIM, NAMA, DOB, EMAIL) VALUES(?,?,?,?)";  
        
        ps = con.prepareStatement(_sql);
        ps.setString(1, objMhs.getNim());
        ps.setString(2, objMhs.getNama());
        ps.setDate(3, (java.sql.Date) objMhs.getDob());
        ps.setString(4, objMhs.getEmail());
        
        int response = ps.executeUpdate();
        if(response > 0) System.out.println("Success save data");
        else System.out.println("Unable to save the data");
        //con.close();
    }
    
    public static void AddMatkul(Connection con, TMataKuliah objMatkul, String _sql, PreparedStatement ps) throws SQLException{
        objMatkul = new TMataKuliah();
        System.out.print("Masukkan ID Matkul: "); objMatkul.setID(new Scanner(System.in).nextLine());
        System.out.print("Masukkan nama Mata Kuliah: "); objMatkul.setNAMA_MATKUL(new Scanner(System.in).nextLine());       
        System.out.print("Masukkan SKS: "); objMatkul.setSKS(new Scanner(System.in).nextInt());
        
         _sql = "INSERT INTO TMataKuliah(ID_MATKUL, NAMA_MATKUL, SKS) VALUES(?, ?, ?)";
        ps = con.prepareStatement(_sql);
        ps.setString(1, objMatkul.getID());
        ps.setString(2, objMatkul.getNAMA_MATKUL());
        ps.setInt(3, objMatkul.getSKS());
//        System.out.println("TEST 1");
        int response = ps.executeUpdate();
//        System.out.println("TEST 2");
        if(response > 0) System.out.println("Success save data");
        else System.out.println("Unable to save the data");
        //con.close();
    }
    
    public static void isiKRS(Connection con, String _sql, ResultSet rs, PreparedStatement ps) throws SQLException{
        TMahasiswa tmhs;
        
        Scanner scn = new Scanner(System.in);
        System.out.print("Masukkan NIM yang ingin di isi KRS: ");
        String nim = scn.nextLine();
        
        _sql = "SELECT * FROM TMahasiswa";
        ps = con.prepareStatement(_sql);
        rs = ps.executeQuery();
        List<TMahasiswa> lstMhs = new ArrayList<TMahasiswa>();
        while(rs.next()){
            tmhs = new TMahasiswa();
            tmhs.setNim(rs.getString("NIM"));
            tmhs.setNama(rs.getString("NAMA"));
            tmhs.setDob(rs.getDate("DOB"));
            tmhs.setEmail(rs.getString("EMAIL"));
            lstMhs.add(tmhs);
        }
        if(findMahasiswa(nim, lstMhs) == null){
            System.out.println(String.format("Maaf! Mahasiswa dengan NIM %s tidak dapat ditemukan", nim));
        }
        else {
            boolean loop = true;
            do{
                System.out.print("Masukkan kode mata kuliah: ");
                String idMk = scn.nextLine();
                System.out.println("Test A");
                _sql = "SELECT * FROM TMatakuliah";
                ps = con.prepareStatement(_sql);
                rs = ps.executeQuery();
                System.out.println("Test B");
                List<TMataKuliah> lstMatkul = new ArrayList<TMataKuliah>();
                TMataKuliah objMatkul;
                while(rs.next()){
                    objMatkul = new TMataKuliah();
                    objMatkul.setID(rs.getString("ID_MATKUL"));
                    objMatkul.setNAMA_MATKUL(rs.getString("NAMA_MATKUL"));
                    objMatkul.setSKS(rs.getInt("SKS"));
                    lstMatkul.add(objMatkul);
                }
                System.out.println("Test C");
                if(findMatakuliah(idMk, lstMatkul) == null){
                    System.out.println("Maaf! Kode Mata Kuliah tidak tersedia!");
                }
                else {
                    _sql = "INSERT INTO TMkMhs(ID, NIM) VALUES(?, ?)";
                    System.out.println("D");
                    ps = con.prepareStatement(_sql);
                    System.out.println("Test E");
//                    System.out.println(idMk);
//                    System.out.println("NIM: " + nim);
//                    System.out.println("idmk: " + idMk);
                    TmhsMatkul objMhsMk = new TmhsMatkul();
                    objMhsMk.setID(idMk);
                    objMhsMk.setNIM(nim);
                    
                    ps.setString(1, objMhsMk.getID());
                    ps.setString(2, objMhsMk.getNIM());
                    System.out.println("Test F");
                    int stat;
                    stat = ps.executeUpdate();
                    System.out.println("Test G");
                    if(stat > 0) System.out.println("Data tersimpan");
                    else System.out.println("Maaf data tidak tersimpan");
                    
                    System.out.print("Ingin menambah mata kuliah?(Y=Yes|N=No): ");
                    String again = scn.next();
                    if(again.toLowerCase().equals("n")) {
                        loop = false;
                    }
                }
            }
            while(loop);
        }
    }
    
    public static void cariMahasiswa(Connection koneksi, PreparedStatement ps) throws SQLException{
        Scanner scn = new Scanner(System.in);
        System.out.print("Masukkan NIM Mahasiswa: ");
        String nim = scn.nextLine();
        String _sql = "SELECT G.NIM AS 'NIM', MHS.NAMA AS 'NAMA', MK.NAMA_MATKUL AS 'Matkul', G.NILAI \n AS 'NILAI'"  +
                        "FROM TMkMhs G\n" +
                        "INNER JOIN tmahasiswa MHS\n" +
                        "ON G.NIM = MHS.NIM\n" +
                        "INNER JOIN tmatakuliah MK\n" +
                        "ON G.ID = MK.ID_MATKUL\n" +
                        "WHERE G.NIM = ?";
        ps = koneksi.prepareStatement(_sql);
        ps.setString(1, nim);
        if(ps.executeUpdate() == 0){
            System.out.println("Maaf! Data tidak dapat ditemukan!");
        }
        else {
            ResultSet rs = ps.executeQuery();
            System.out.println(String.format("Mahasiswa dengan NIM %s memiliki data nilai: ", nim));
            while(rs.next()){
                System.out.println("Mata Kuliah: " + rs.getString("Matkul"));
                System.out.println("Nilai : " + rs.getFloat("Nilai"));
            }
        }
        
    }
    
    public static void isiDataMhs(Connection koneksi, PreparedStatement ps, ResultSet rs) throws SQLException, SQLException{
        System.out.print("Masukkan NIM yang ingin ditambah data nilainya: ");
        String nim = new Scanner(System.in).nextLine();
        String _sql = "SELECT MHS.NIM AS 'NIM', MHS.NAMA AS 'NAMA', MK.ID_MATKUL AS 'ID', MK.NAMA_MATKUL AS 'MATKUL', G.NILAI AS 'NILAI'\n" +
                        "FROM TMKMHS G \n" +
                        "INNER JOIN TMAHASISWA MHS\n" +
                        "ON G.NIM = MHS.NIM\n" +
                        "INNER JOIN TMATAKULIAH MK\n" +
                        "ON MK.ID_MATKUL = G.ID\n" +
                        "WHERE MHS.NIM = ?";
        ps = koneksi.prepareStatement(_sql);
        ps.setString(1, nim);
        if(ps.executeUpdate() == 0){
            System.out.println(String.format("Maaf! Data mahasiswa dengan NIM %s tidak dapat ditemukan!", nim));
        }
        else {
            rs = ps.executeQuery();
            System.out.println(String.format("Mata kuliah yang sudah diambil mahasiswa bernim %s adalah: "));
            List<TmhsMatkul> lstMhsMk = new ArrayList<TmhsMatkul>();
            TmhsMatkul tempMhsMk;
            while(rs.next()){
                tempMhsMk = new TmhsMatkul();
                tempMhsMk.setID(rs.getString("ID"));
                tempMhsMk.setNIM("NIM");
                System.out.println(String.format("[ID Matkul][Nama Matkul]: [%s] [%s]", rs.getString("ID"), rs.getString("MATKUL")));
            }
            boolean loop = true;
            do {
                System.out.print("Masukkan ID Mata Kuliah: ");
                String idMk = new Scanner(System.in).nextLine();
                boolean find = false;
                for(int i=0; i<lstMhsMk.size(); i++){
                    if(lstMhsMk.get(i).getID() == idMk){
                        tempMhsMk = new TmhsMatkul();
                        tempMhsMk.setID(lstMhsMk.get(i).getID());
                        tempMhsMk.setNIM(lstMhsMk.get(i).getNIM());
                        System.out.print("Masukkan nilai: ");
                        float nilai = new Scanner(System.in).nextFloat();
                        tempMhsMk.setNilai(nilai);
                        find = true;
                        break;
                    }
                }
                if(find == false){
                    System.out.println("Maaf! data yang diminta tidak ditemukan.");
                }
                System.out.print("Ingin menambah nilai lagi?[Y|N]: ");
                String pilih = new Scanner(System.in).nextLine();
                if(pilih.toLowerCase().equals("N")) loop = false;
            }
            
            while(loop);
        }
        
    }
    
    public static void main(String[] args) {
        try {
            String className = "com.mysql.cj.jdbc.Driver";
            Class.forName(className);
            System.out.println("Driver loaded successfully");
            String url = "jdbc:mysql://localhost/PROFIL";
            String user = "root";
            String password = "";
            PreparedStatement ps = null; ResultSet rs = null;
            
            Connection con = DriverManager.getConnection(url, user, password);
            if(con.isClosed()) System.out.println("Connection is closed");
            else {
                while(true){
                    System.out.println("1. Tambah data Mahasiswa");
                    System.out.println("2. Tambah data Mata Kuliah");
                    System.out.println("3. Isi KRS Mahasiswa");
                    System.out.println("4. Isi data Mahasiswa");
                    System.out.println("5. Cetak Data Nilai Mahasiswa");
                    System.out.println("0. Exit");
                    System.out.print("Masukkan pilihan: ");
                    Scanner scn = new Scanner(System.in);
                    int pilih = scn.nextInt();
                    TMahasiswa objMhs = null;
                    TMataKuliah objMatkul = null;
                    String _sql = null;
                    TmhsMatkul objmhsMatkul;
                    if(pilih == 0) {
                        con.close();
                        System.exit(0);
                    } 
                    if(pilih == 1){
                        AddMhs(con, objMhs, _sql, ps);
                    }
                    if(pilih == 2){
                        AddMatkul(con, objMatkul, _sql, ps);
                    }
                    if(pilih == 3){
                        isiKRS(con, _sql, rs, ps);
                    }
                    if(pilih == 4){
                        isiDataMhs(con, ps, rs);
                    }
                    if(pilih == 5){
                        cariMahasiswa(con, ps);
                    }
                }
                
            }
            
        } catch(Exception ex){
            
        }
    }

    public static java.sql.Date convertToDateFromString(String date, String format) throws ParseException{
        SimpleDateFormat sdf1 = new SimpleDateFormat(format);
        Date dt = sdf1.parse(date);
        return new java.sql.Date(dt.getTime());  
    }
    
    public static TMahasiswa findMahasiswa(String nim, List<TMahasiswa> listMahasiswa) {
        Iterator<TMahasiswa> iterator = listMahasiswa.iterator();
        while (iterator.hasNext()) {
            TMahasiswa mhs = iterator.next();
            if (mhs.getNim().equals(nim)) return mhs;
        }
        return null;
    }
    
    public static TMataKuliah findMatakuliah(String kodeMatkul, List<TMataKuliah> listMatakuliah) {
        Iterator<TMataKuliah> iterator = listMatakuliah.iterator();
        while (iterator.hasNext()) {
            TMataKuliah mkl = iterator.next();
            if (mkl.getID().equals(kodeMatkul)) return mkl;
        }
        return null;
    }

}

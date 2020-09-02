/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbcsample;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner; 
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;  
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author teamsar
 */
public class JDBCSample {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, ParseException {
        try {
            // TODO code application logic here
            String className = "com.mysql.cj.jdbc.Driver";
            Class.forName(className);
            System.out.println("Driver loaded successfully!");
            
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/PROFIL", 
                    "root", "");
            
            if(con.isClosed()) System.out.println("Connection is closed!");
            else {
                while(true){
                    int command;
                    System.out.println("1. Entri data mahasiswa\n2. Entri data kuliah\n3. Isi KRS\n4. Keluar\n");
                    System.out.print("Pilihan anda: "); command = Integer.valueOf(new Scanner(System.in).nextLine()); 
                    
                    if(command >= 4 || command < 1) break;
                    else{
                        TMahasiswa objMhs;
                        TMatakuliah objMatkul;
                        TMkMhs objMkMhs;
                        PreparedStatement ps; ResultSet rs;
                        String _sql = null; int response;
                        
                        switch(command){
                            case 1:
                                objMhs = new TMahasiswa();
                                System.out.print("Masukan NIM: "); objMhs.setNim(new Scanner(System.in).nextLine());
                                System.out.print("Masukan nama mahasiswa: "); objMhs.setNama(new Scanner(System.in).nextLine());
                                System.out.print("Masukan DOB: "); 
                                objMhs.setDob(convertToDateFromString(new Scanner(System.in).nextLine(), "dd/MM/yyyy"));
                                System.out.print("Masukan email: "); objMhs.setEmail(new Scanner(System.in).nextLine());
                                
                                _sql = "INSERT INTO TMahasiswa(NIM, NAMA, DOB, EMAIL) VALUES(?, ?, ?, ?)";
                                ps = con.prepareStatement(_sql);
                                ps.setString(1, objMhs.getNim());
                                ps.setString(2, objMhs.getNama());
                                ps.setDate(3, objMhs.getDob());
                                ps.setString(4, objMhs.getEmail());
                                
                                response = ps.executeUpdate();
                                if(response > 0) System.out.println("Success save data");
                                else System.out.println("Unable to save data");
                                break;
                            case 2:
                                objMatkul = new TMatakuliah();
                                System.out.print("Masukan kode mata kuliah: "); objMatkul.setIDMatkul(new Scanner(System.in).nextLine());
                                System.out.print("Masukan nama mata kuliah: "); objMatkul.setNamaMatkul(new Scanner(System.in).nextLine());
                                System.out.print("Masukan jumlah SKS: "); objMatkul.setSks(Integer.valueOf(new Scanner(System.in).nextLine()));
                                
                                _sql = "INSERT INTO TMatakuliah(ID_MATKUL, NAMA_MATKUL, SKS) VALUES(?, ?, ?)";
                                ps = con.prepareStatement(_sql);
                                ps.setString(1, objMatkul.getIdMatkul());
                                ps.setString(2, objMatkul.getNamaMatkul());
                                ps.setInt(3, objMatkul.getSks());
                                
                                response = ps.executeUpdate();
                                if(response > 0) System.out.println("Success save data");
                                else System.out.println("Unable to save data");
                                break;
                            case 3:
                                // collect data mahasiswa
                                _sql = "SELECT * FROM TMahasiswa";
                                ps = con.prepareStatement(_sql);

                                List<TMahasiswa> lstMahasiswa = new ArrayList<TMahasiswa>();
                                rs = ps.executeQuery();
                                while(rs.next()){
                                    objMhs = new TMahasiswa();
                                    objMhs.setNim(rs.getString("NIM"));
                                    objMhs.setNama(rs.getString("NAMA"));
                                    objMhs.setDob(rs.getDate("DOB"));
                                    objMhs.setEmail(rs.getString("EMAIL"));
                                    
                                    lstMahasiswa.add(objMhs); // record to list
                                }
                                
                                // collect data mata kuliah
                                _sql = "SELECT * FROM TMatakuliah";
                                ps = con.prepareStatement(_sql);

                                List<TMatakuliah> lstMatkul = new ArrayList<TMatakuliah>();
                                rs = ps.executeQuery();
                                while(rs.next()){
                                    objMatkul = new TMatakuliah();
                                    objMatkul.setIDMatkul(rs.getString("ID_MATKUL"));
                                    objMatkul.setNamaMatkul((rs.getString("NAMA_MATKUL")));
                                    objMatkul.setSks(rs.getInt("SKS"));
                                    
                                    lstMatkul.add(objMatkul); // record to list
                                }
                                
                                // collect data mk mhs
                                _sql = "SELECT * FROM tmhsmatkul";
                                ps = con.prepareStatement(_sql);
                                
                                List<TMkMhs> lstMkMhs = new ArrayList<TMkMhs>();
                                rs = ps.executeQuery();
                                while(rs.next()){
                                    objMkMhs = new TMkMhs();
                                    objMkMhs.setIdMatkul(rs.getString("ID_MATKUL"));
                                    objMkMhs.setNim((rs.getString("NIM")));
                                    
                                    lstMkMhs.add(objMkMhs); // record to list
                                }
                                
                                String tempNim, tempIdMatkul = null;
                                System.out.print("Masukan NIM: "); tempNim = new Scanner(System.in).nextLine();
                                if(findMahasiswa(tempNim, lstMahasiswa) == null){
                                    System.out.println(String.format("Maaf mahasiswa dengan nim "
                                            + "%s tidak terdaftar", tempNim));
                                    break;
                                }else{
                                    System.out.print("Masukan kode mata kuliah: "); 
                                    tempIdMatkul = new Scanner(System.in).nextLine();
                                    if(findMatakuliah(tempIdMatkul, lstMatkul) == null){
                                        System.out.println(String.format("Maaf mata kuliah dengan kode "
                                                + "%s tidak terdaftar", tempIdMatkul));
                                        break;
                                    }else{
                                        if(checkKRSExist(tempIdMatkul, tempNim, lstMkMhs) != null){
                                            System.out.println(String.format("Mahasiswa %s sudah mengambil "
                                                    + "mata kuliah %s", tempNim, tempIdMatkul));
                                            break;
                                        }else{
                                            objMkMhs = new TMkMhs();
                                            _sql = "INSERT INTO TMkMhs(NIM, ID_MATKUL) VALUES(?, ?)";
                                            ps = con.prepareStatement(_sql);
                                            ps.setString(1, tempNim);
                                            ps.setString(2, tempIdMatkul);
                                            
                                            response = ps.executeUpdate();
                                            if(response > 0) System.out.println("Success save data");
                                            else System.out.println("Unable to save data");
                                        }
                                    }
                                } 
                                break;  
                        }
                    }
                }
                
                con.close();
            }
        } catch (ClassNotFoundException ex) {
            System.err.println(ex.getException());
            Logger.getLogger(JDBCSample.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public static TMatakuliah findMatakuliah(String kodeMatkul, List<TMatakuliah> listMatakuliah) {
        Iterator<TMatakuliah> iterator = listMatakuliah.iterator();
        while (iterator.hasNext()) {
            TMatakuliah mkl = iterator.next();
            if (mkl.getIdMatkul().equals(kodeMatkul)) return mkl;
        }
        return null;
    }
    
    public static TMkMhs checkKRSExist(String kodeMatkul, String nim, List<TMkMhs> listMkMhs) {
        Iterator<TMkMhs> iterator = listMkMhs.iterator();
        while (iterator.hasNext()) {
            TMkMhs mkmhs = iterator.next();
            if (mkmhs.getIdMatkul().equals(kodeMatkul) && mkmhs.getNim().equals(nim)) return mkmhs;
        }
        return null;
    }
}

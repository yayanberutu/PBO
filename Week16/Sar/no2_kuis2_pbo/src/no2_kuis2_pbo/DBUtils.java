/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package no2_kuis2_pbo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sarah Christine (11318058)
 */
public class DBUtils {
    public static Connection getConnection() throws SQLException, ClassNotFoundException{
        String className = "com.mysql.cj.jdbc.Driver";
            Class.forName(className);
            System.out.println("Driver loaded successfully!");
            
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/PROFIL", 
                    "root", "");
            
        if(con.isClosed()) return null;
        else return con;
    }
    
    public static void closeConnection(Connection con) throws SQLException{
        con.close();
    }
    
    public static int saveMatakuliah(TMatakuliah obj) throws SQLException, ClassNotFoundException{
        Connection con = getConnection();
        String _sql = "INSERT INTO TMatakuliah(ID_MATKUL, NAMA_MATKUL, SKS)VALUES(?,?,?)";
        PreparedStatement ps = con.prepareStatement(_sql);
        ps.setString(1, obj.getIdMatkul());
        ps.setString(2, obj.getNamaMatkul());
        ps.setInt(3, obj.getSks());
        
        int response = ps.executeUpdate();
        ps.close();
        con.close();
        
        return response;
    }
    
    public static int saveMahasiswa(TMahasiswa obj) throws SQLException, ClassNotFoundException{
        Connection con = getConnection();
        String _sql = "INSERT INTO TMahasiswa(NIM, NAMA, DOB, EMAIL) VALUES (?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(_sql);
        ps.setString(1, obj.getNim());
        ps.setString(2, obj.getNama());
        ps.setDate(3, obj.getDob());
        ps.setString(4, obj.getEmail());
        
        int response = ps.executeUpdate();
        ps.close();
        con.close();
        
        return response;
    }
    
    public static int saveKrs(TMkMhs obj) throws SQLException, ClassNotFoundException{
        Connection con = getConnection();
        String _sql = "INSERT INTO TMkMhs(NIM, ID_MATKUL, NILAI) VALUES (?,?,?)";
        PreparedStatement ps = con.prepareStatement(_sql);
        ps.setString(1, obj.getNim());
        ps.setString(2, obj.getIdMatkul());
        ps.setString(3, obj.getNilai());
      
        
        int response = ps.executeUpdate();
        ps.close();
        con.close();
        
        return response;
    }
    
    public static List<TMahasiswa> getAllMahasiswa() throws SQLException, ClassNotFoundException{
        Connection con = getConnection();
        String _sql = "SELECT * FROM TMahasiswa";
        PreparedStatement ps = con.prepareStatement(_sql);
        
        List<TMahasiswa> lstMahasiswa = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        
        TMahasiswa objMhs;
        while(rs.next()){
            objMhs = new TMahasiswa();
            objMhs.setNim(rs.getString("NIM"));
            objMhs.setNama(rs.getString("NAMA"));
            objMhs.setDob(rs.getDate("DOB"));
            objMhs.setEmail(rs.getString("EMAIL"));
            
            lstMahasiswa.add(objMhs);
        }
        return lstMahasiswa;
    }
    
    public static List<TMatakuliah> getAllMataKuliah() throws SQLException, ClassNotFoundException{
        Connection con = getConnection();
        String _sql = "SELECT * FROM TMatakuliah";
        PreparedStatement ps = con.prepareStatement(_sql);
        
        List<TMatakuliah> lstMatkul = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        
        TMatakuliah objMatkul;
        while(rs.next()){
            objMatkul = new TMatakuliah();
            objMatkul.setIDMatkul(rs.getString("ID_MATKUL"));
            objMatkul.setNamaMatkul(rs.getString("NAMA_MATKUL"));
            objMatkul.setSks(rs.getInt("SKS"));
            lstMatkul.add(objMatkul);
        }
        return lstMatkul;
    }
   
    public static List<TMkMhs> getAllKrs() throws SQLException, ClassNotFoundException{
        Connection con = getConnection();
        String _sql = "SELECT * FROM TMkMhs";
        PreparedStatement ps = con.prepareStatement(_sql);
        
        List<TMkMhs> lstMkMhs = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        
        TMkMhs objMkMhs;
        while(rs.next()){
            objMkMhs = new TMkMhs();
            objMkMhs.setIdMatkul(rs.getString("ID_MATKUL"));
            objMkMhs.setNim(rs.getString("NIM"));
            lstMkMhs.add(objMkMhs);
        }
        return lstMkMhs;
    }
    
   public static void getKrs(String nim) throws SQLException, ClassNotFoundException{
        Connection con = getConnection();
        String _sql = "SELECT TMatakuliah.ID_MATKUL, NAMA_MATKUL, NILAI FROM TMkMhs"
                + "INNER JOIN TMatakuliah"
                + "ON TMatakuliah.ID_MATKUL = TMkMhs.ID_MATKUL WHERE NIM=?";
        PreparedStatement ps = con.prepareStatement(_sql);
        ps.setString(1, nim);
        
        ResultSet rs = ps.executeQuery();
        
        
        TMkMhs objMkMhs;
        TMatakuliah objMatkul;
        while(rs.next()){
            objMkMhs = new TMkMhs();
            objMatkul = new TMatakuliah();
            objMkMhs.setIdMatkul(rs.getString("ID_MATKUL"));
            objMatkul.setNamaMatkul(rs.getString("NAMA_MATKUL"));
            objMkMhs.setNilai(rs.getString("NILAI"));
            System.out.print(String.format("%s %s %s", objMkMhs.getIdMatkul(), objMatkul.getNamaMatkul(), objMkMhs.getNilai()));
        }
    }
}

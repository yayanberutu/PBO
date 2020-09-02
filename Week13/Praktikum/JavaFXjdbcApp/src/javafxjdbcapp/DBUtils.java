/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxjdbcapp;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class DBUtils {
    
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        String className = "com.mysql.cj.jdbc.Driver";
        Class.forName(className);
        System.out.println("Driver loaded successfully!");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/PROFIL", "root", "");
        
        if(con.isClosed()) return null;
        else return con;
    }
    
    public static void closeConnection(Connection con) throws SQLException{
        con.close();
    }
    
    public static int saveMataKuliah(TMataKuliah obj) throws ClassNotFoundException, SQLException{
        Connection con = getConnection();
        String _sql = "INSERT INTO TMatakuliah(ID_MATKUL, NAMA_MATKUL, SKS) VALUES"
                + "(?,?,?)";
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
        String _sql = "INSERT INTO TMahasiswa(NIM, NAMA, DOB, EMAIL) "
                + "VALUES(?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(_sql);
        ps.setString(1, obj.getNim());
        ps.setString(2,obj.getNama());
        ps.setDate(3, (Date) obj.getDob());
        ps.setString(4, obj.getEmail());
        
        int response = ps.executeUpdate();
        ps.close();
        con.close();
        
        return response;
    }
    
    public static int saveKrs(TMkMhs obj) throws ClassNotFoundException, SQLException{
        Connection con = getConnection();
        String _sql = "INSERT INTO TMkMhs(NIM, ID_MATKUL) VALUES(?, ?)";
        PreparedStatement ps = con.prepareStatement(_sql);
        ps.setString(1, obj.getNim());
        ps.setString(2, obj.getId_matkul());
        
        int response = ps.executeUpdate();
        ps.close();
        con.close();
        
        return response;
    }
    
    public static List<TMahasiswa> getAllMahasiswa() throws ClassNotFoundException, SQLException{
        Connection con = getConnection();
        String _sql = "SELECT * FROM MAHASISWA";
        PreparedStatement ps = con.prepareStatement(_sql);
        
        List<TMahasiswa> lstMahasiswa = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        
        TMahasiswa objMhs;
        while(rs.next()){
            objMhs = new TMahasiswa();
            objMhs.setNim(rs.getString("NIM"));
            objMhs.setEmail(rs.getString("EMAIL"));
            objMhs.setDob(rs.getDate("DOB"));
            objMhs.setNama(rs.getString("NAMA"));
            lstMahasiswa.add(objMhs);
        }
        
        return lstMahasiswa;
    }
    
    public static List<TMataKuliah> getAllMatakuliah() throws ClassNotFoundException, SQLException{
        Connection con = getConnection();
        String _sql = "SELECT * FROM TMatakuliah";
        PreparedStatement ps = con.prepareStatement(_sql);
        
        List<TMataKuliah> lstMatkul = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        
        TMataKuliah objMatkul;
        while(rs.next()){
            objMatkul = new TMataKuliah();
            objMatkul.setIdMatkul(rs.getString("ID_MATKUL"));
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
        
        List<TMkMhs> lstMkMhs = new ArrayList<TMkMhs>();
        ResultSet rs = ps.executeQuery();
        
        TMkMhs objMkMhs;
        while(rs.next()){
            objMkMhs = new TMkMhs();
            objMkMhs.setId_matkul(rs.getString("ID_MATKUL"));
            objMkMhs.setNim(rs.getString("NIM"));
            
            lstMkMhs.add(objMkMhs);
        }
        
        return lstMkMhs;
    }
}

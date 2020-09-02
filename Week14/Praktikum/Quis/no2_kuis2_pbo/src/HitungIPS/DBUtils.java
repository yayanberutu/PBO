package HitungIPS;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Yosepri Berutu
 */
public class DBUtils {
    public static Connection getConnection() throws SQLException,
        ClassNotFoundException{
            String className = "com.mysql.cj.jdbc.Driver";
            Class.forName(className);
            System.out.println("Driver loaded Successfully !");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/PROFIL?useTimezone=true&serverTimezone=UTC","root", "");
            if(con.isClosed()) return null;
            else return con;
        }
    public static void closeConnection(Connection con) throws SQLException{
        con.close();
    }
    
    public static int saveMatakuliah(TMataKuliah obj) throws SQLException,ClassNotFoundException{
        Connection con = getConnection();
        String sql = "INSERT INTO TMatakuliah(ID_MATKUL,NAMA_MATKUL,SKS) "+"VALUES(?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,obj.getIdMatkul());
        ps.setString(2, obj.getNamaMatkul());
        ps.setInt(3, obj.getSks());
        
        int response = ps.executeUpdate();
        ps.close();
        con.close();
        
        return response;
    }
    
    public static int saveMahasiswa(TMahasiswa obj) throws SQLException,ClassNotFoundException{
        Connection con = getConnection();
        String sql = "INSERT INTO TMahasiswa(NIM,NAMA,DOB,EMAIL) "+"VALUES(?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,obj.getNim());
        ps.setString(2, obj.getNama());
        ps.setDate(3, obj.getDob());
        ps.setString(4, obj.getEmail());
        
        int response = ps.executeUpdate();
        ps.close();
        con.close();
        
        return response;
    }
    
    public static int saveKrs(TMkMhs obj) throws SQLException,ClassNotFoundException{
        Connection con = getConnection();
        String sql = "INSERT INTO TMkMhs(NIM,ID_MATKUL) "+"VALUES(?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,obj.getNim());
        ps.setString(2, obj.getId_matkul());
        
        int response = ps.executeUpdate();
        ps.close();
        con.close();
        
        return response;
    }
    
    public static List<TMahasiswa> getAllMahasiswa() throws SQLException,ClassNotFoundException{
        Connection con = getConnection();
        String sql = "SELECT * FROM TMahasiswa";
        PreparedStatement ps = con.prepareStatement(sql);
        
         List<TMahasiswa> listMahasiswa  = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        
        TMahasiswa objMhs;
        while(rs.next()){
            objMhs = new TMahasiswa();
            objMhs.setNim(rs.getString("NIM"));
            objMhs.setNama(rs.getString("NAMA"));
            objMhs.setDob(rs.getDate("DOB"));s
            objMhs.setEmail(rs.getString("Email"));
            
            listMahasiswa.add(objMhs);
        }
        
        return listMahasiswa;
    }
     
    public static List<TMataKuliah> getAllMataKuliah() throws SQLException,ClassNotFoundException{
        Connection con = getConnection();
        String sql = "SELECT * FROM TMataKuliah";
        PreparedStatement ps = con.prepareStatement(sql);
        
        List<TMataKuliah> listMataKuliah  = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        
        TMataKuliah objMatkul;
        while(rs.next()){
            objMatkul = new TMataKuliah();
            objMatkul.setIDMatkul(rs.getString("ID_MATKUL"));
            objMatkul.setNamaMatkul(rs.getString("NAMA_MATKUL"));
            objMatkul.setSks(rs.getInt("SKS"));
               
            listMataKuliah.add(objMatkul);
        }
        
        return listMataKuliah;
    }
    
    public static List<TMkMhs> getAllKrs() throws SQLException,ClassNotFoundException{
        Connection con = getConnection();
        String sql = "SELECT * FROM TMkMhs";
        PreparedStatement ps = con.prepareStatement(sql);
        
        List<TMkMhs> listMkMhs  = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        
        TMkMhs objMkMhs;
        while(rs.next()){
            objMkMhs = new TMkMhs();
            objMkMhs.setId_matkul(rs.getString("ID_MATKUL"));
            objMkMhs.setNim(rs.getString("NIM"));
            objMkMhs.setNilai(rs.getInt("NILAI"));
            listMkMhs.add(objMkMhs);
        }
        
        return listMkMhs;
    }
    
    public static TMataKuliah getMatkulById(String id) throws SQLException, ClassNotFoundException{
        Connection con = getConnection();
        String sql = "SELECT * FROM tmatakuliah WHERE ID_MATKUL=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, id);
        
        ResultSet rs = ps.executeQuery();
        TMataKuliah objMatkul = new TMataKuliah();
        objMatkul.setIDMatkul(rs.getString("ID_MATKUL"));
        objMatkul.setNamaMatkul(rs.getString("NAMA_MATKUL"));
        objMatkul.setSks(rs.getInt("SKS"));
                
        return objMatkul;
    }
}

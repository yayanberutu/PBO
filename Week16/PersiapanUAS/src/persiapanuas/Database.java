/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persiapanuas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class Database {
    
    public static Connection getKoneksi() throws ClassNotFoundException, SQLException{
        String namaClass = "com.mysql.cj.jdbc.Driver";
        Class.forName(namaClass);
        System.out.println("Driver load successfully!");
        Connection con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/belajar_laravel?useTimezone=true&serverTimezone=UTC","root", "");
        return con;
    }
    
    public static ArrayList<Pengguna> getAllPengguna() throws ClassNotFoundException, SQLException{
        Connection con = getKoneksi();
        String sql = "SELECT * FROM pengguna";
        PreparedStatement ps = con.prepareStatement(sql);
        
        ResultSet rs =  ps.executeQuery();
        ArrayList<Pengguna> listPengguna = new ArrayList<>();
        Pengguna objPengguna;
        while(rs.next()){
           objPengguna = new Pengguna(rs.getInt("id"), rs.getString("nama"));
           listPengguna.add(objPengguna);
        }
        return listPengguna;
    }
    
    
    
}

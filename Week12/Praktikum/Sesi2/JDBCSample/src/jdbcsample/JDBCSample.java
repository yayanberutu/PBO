/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbcsample;

import com.sun.istack.internal.logging.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;

/**
 *
 * @author ASUS
 */
public class JDBCSample {
    
    
    public static void main(String[] args) throws SQLException, ParseException {
        
        try {
            String className = "com.mysql.cj.jdbc.Driver";
            Class.forName(className);
            System.out.println("Driver loaded successfully");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/PROFIL", "root", "");
            
            if(con.isClosed()) System.out.println("Connection is closed");
            else {
                TMahasiswa objMhs = new TMahasiswa();
                System.out.print("Masukkan NIM: "); objMhs.setNim(new Scanner(System.in).nextLine());
                System.out.print("Masukkan nama mahasiswa: "); objMhs.setNama(new Scanner(System.in).nextLine());
                System.out.print("Masukkan DOB: "); 
                objMhs.setDob(convertToDateFromString(new Scanner(System.in).nextLine(), "dd/MM/yyyy"));;
                
                String _sql = "INSERT INTO TMahasiswa(NIM, NAMA< DOB, ENAIL) VALUES(?,?,?,?)";
                PreparedStatement pStmnt = con.prepareStatement(_sql);
                
                pStmnt.setString(1, objMhs.getNim());
                pStmnt.setString(2, objMhs.getNama());
                pStmnt.setDate(3, (java.sql.Date) objMhs.getDob());
                pStmnt.setString(4, objMhs.getEmail());
                
                int response = pStmnt.executeUpdate();
                if(response > 0) System.out.println("Success save data");
                else System.out.println("Unable to save the data");
                
                con.close();
            }
        }
        catch(ClassNotFoundException ex){
            System.err.println(ex.getException());
//            Logger.getLogger(JDBCSample.class.getName()).log(level.SEVERE,null, ex);
        }
    }
    
    public static java.sql.Date convertToDateFromString(String date, String format) throws ParseException{
        SimpleDateFormat sdfl = new SimpleDateFormat(format);
        Date dt = sdfl.parse(date);
        
        return new java.sql.Date(dt.getTime());
    }
}

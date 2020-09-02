/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx_praktikum.javafxjdbcapp;

import java.sql.Date;

/**
 *
 * @author STU
 */
public class TMahasiswa {
    private String nim,nama,email;
    private Date dob;
    
    public void setNim(String nim){
        this.nim = nim;
    }
    public void setNama(String nama){
        this.nama = nama;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setDob(Date dob){
        this.dob = dob;
    }
    
    public String getNim(){ return this.nim; }
    public String getNama() { return this.nama; }
    public String getEmail() {return this.email; }
    public Date getDob() {return this.dob; }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbcsample;

import java.sql.Date;



/**
 *
 * @author teamsar
 */
public class TMahasiswa {
    private String _nim, _nama, _email;
    private Date _dob;
    
    public void setNim(String nim){
        _nim = nim;
    }
    
    public void setNama(String nama){
        _nama = nama;
    }
    
    public void setEmail(String email){
        _email = email;
    }
    
    public void setDob(Date dob){
        _dob = dob;
    }
    
    public String getNim(){ return _nim; }
    public String getNama(){ return _nama; }
    public String getEmail(){ return _email; }
    public Date getDob(){ return _dob; }
}

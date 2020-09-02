/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxjdbcapp;

import java.util.Date;

/**
 *
 * @author ASUS
 */
public class TMahasiswa {
    private String _nim, _nama, _email;
    private Date _dob;

    public String getNim() {
        return _nim;
    }

    public void setNim(String _nim) {
        this._nim = _nim;
    }

    public String getNama() {
        return _nama;
    }

    public void setNama(String _nama) {
        this._nama = _nama;
    }

    public String getEmail() {
        return _email;
    }

    public void setEmail(String _email) {
        this._email = _email;
    }

    public Date getDob() {
        return _dob;
    }

    public void setDob(Date _dob) {
        this._dob = _dob;
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package del.ac.id.yosepri;

/**
 *
 * @author ITD
 */
public class Mahasiswa extends Manusia {
    private String NIM;
    private KelompokTA kelTA; 

    public Mahasiswa(String nama, String jk, float usia, String nim) {
        this.nama = nama;
        this.jenis_kelamin = jk;
        this.usia = usia;
        this.NIM = nim;
    }
    
    
    public String getNIM() {
        return NIM;
    }

    public void setNIM(String NIM) {
        this.NIM = NIM;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public float getUsia() {
        return usia;
    }

    public void setUsia(float usia) {
        this.usia = usia;
    }

  
    
    public KelompokTA getKelTA() {
        return kelTA;
    }

    public void setKelTA(KelompokTA kelTA) {
        this.kelTA = kelTA;
    }

}

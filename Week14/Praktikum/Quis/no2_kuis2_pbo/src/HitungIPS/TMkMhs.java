/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HitungIPS;

/**
 *
 * @author Yosepri Berutu
 */
public class TMkMhs {
    private String nim, id_matkul;
    private float nilai;

    public TMkMhs(String nim, String id_matkul, float nilai) {
        this.nim = nim;
        this.id_matkul = id_matkul;
        this.nilai = nilai;
    }

    public TMkMhs() {
        
    }

    
    public float getNilai() {
        return nilai;
    }
    

    public void setNilai(float nilai) {
        this.nilai = nilai;
    }
    
    
    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getId_matkul() {
        return id_matkul;
    }

    public void setId_matkul(String id_matkul) {
        this.id_matkul = id_matkul;
    }
}
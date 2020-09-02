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
public class TMataKuliah {
    private String idMatkul, namaMatkul;
    private int sks;

    public TMataKuliah(String idMatkul, String namaMatkul, int sks) {
        this.idMatkul = idMatkul;
        this.namaMatkul = namaMatkul;
        this.sks = sks;
    }

    public TMataKuliah() {
        
    }
    
    public void setIDMatkul(String idMatkul){ this.idMatkul = idMatkul; }
    public void setNamaMatkul(String namaMatkul){ this.namaMatkul = namaMatkul; }
    public void setSks(int sks){ this.sks = sks; }
    
    public String getIdMatkul(){ return idMatkul; }
    public String getNamaMatkul(){ return namaMatkul; }
    public int getSks(){ return sks; }
}

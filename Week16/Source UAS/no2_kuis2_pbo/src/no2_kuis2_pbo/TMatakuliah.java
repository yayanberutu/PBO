/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package no2_kuis2_pbo;

/**
 *
 * @author Sarah Christine (11318058)
 */
public class TMatakuliah {
    private String idMatkul, namaMatkul;
    private int sks;
    
    public void setIDMatkul(String idMatkul){ this.idMatkul = idMatkul; }
    public void setNamaMatkul(String namaMatkul){ this.namaMatkul = namaMatkul; }
    public void setSks(int sks){ this.sks = sks; }
    
    public String getIdMatkul(){ return idMatkul; }
    public String getNamaMatkul(){ return namaMatkul; }
    public int getSks(){ return sks; }
}

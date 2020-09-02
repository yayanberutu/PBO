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
public class TMkMhs {
    private String nim, id_matkul, grade;
    
    public void setNim(String nim){ this.nim = nim; }
    public void setIdMatkul(String id_matkul){ this.id_matkul = id_matkul; }
    public void setNilai(String nilai){this.grade = nilai;}
    
    public String getNim(){ return nim; }
    public String getIdMatkul(){ return id_matkul; }
    public String getNilai(){ return grade;}
}

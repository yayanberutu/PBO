/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package del.ac.id.yosepri;

import java.util.ArrayList;

/**
 *
 * @author ITD
 */
public class KelompokTA {
    private String id, judulTA;
    private int jlhHlmTA;
    private ArrayList<Mahasiswa> anggota;

    public KelompokTA(String id, String judulTA, int jlhHlmTA) {
        this.id = id;
        this.judulTA = judulTA;
        this.jlhHlmTA = jlhHlmTA;
    }

    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJudulTA() {
        return judulTA;
    }

    public void setJudulTA(String judulTA) {
        this.judulTA = judulTA;
    }

    public int getJlhHlmTA() {
        return jlhHlmTA;
    }

    public void setJlhHlmTA(int jlhHlmTA) {
        this.jlhHlmTA = jlhHlmTA;
    }

    public ArrayList<Mahasiswa> getAnggota() {
        return anggota;
    }

    public void setAnggota(ArrayList<Mahasiswa> anggota) {
        this.anggota = anggota;
    }
    
    
}

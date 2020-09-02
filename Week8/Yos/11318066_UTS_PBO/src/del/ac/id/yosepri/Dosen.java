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
public abstract class Dosen extends Manusia{
    protected String NIDN, ruangan;
    protected double gaji, tunjangan;
    protected ArrayList<KelompokTA> kelTA;
    
    public void mengajar(){
        System.out.println("Melakukan pengajaran");
    }
    
    public void membuatModulKuliah(){
        System.out.println("membuat modul perkuliahan");
        
    }
    
    public void membuatModulPraktikum(){
        System.out.println("Membuat modul praktikum");
    }
    
    public abstract void setTunjangan();
    
    
}

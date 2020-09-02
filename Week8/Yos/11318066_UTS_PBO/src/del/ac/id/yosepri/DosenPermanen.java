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
public class DosenPermanen extends Dosen {

    @Override
    public void setTunjangan() {
        tunjangan = (kelTA.size()/2) * gaji;
    }
    
}

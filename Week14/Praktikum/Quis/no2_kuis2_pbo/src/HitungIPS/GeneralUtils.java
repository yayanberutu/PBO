/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HitungIPS;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Yosepri Berutu
 */
public class GeneralUtils {
    
    public static java.sql.Date convertToDateFromString(String date,String format) throws ParseException{
        SimpleDateFormat sdf1 = new SimpleDateFormat(format);
        Date dt = sdf1.parse(date);
        return new java.sql.Date(dt.getTime());
    }
    public static TMahasiswa findMahasiswa(String nim,List<TMahasiswa> listMahasiswa){
        Iterator<TMahasiswa> iterator = listMahasiswa.iterator();
        while(iterator.hasNext()){
            TMahasiswa mhs = iterator.next();
            if(mhs.getNim().toLowerCase().equals(nim)) return mhs;
        }
        return null;
    }
    
    public static TMataKuliah findMataKuliah(String kodeMatkul,List<TMataKuliah> listMatkul){
        Iterator<TMataKuliah> iterator = listMatkul.iterator();
        while(iterator.hasNext()){
            TMataKuliah mkl = iterator.next();
            if(mkl.getIdMatkul().toLowerCase().equals(kodeMatkul)) return mkl;
        }
        return null;
    }
    
    public static TMkMhs checkKrsExist(String kodeMatkul,String nim,List<TMkMhs> listMkMhs){
        Iterator<TMkMhs> iterator = listMkMhs.iterator();
        while(iterator.hasNext()){
            TMkMhs mkmhs = iterator.next();
            if(mkmhs.getId_matkul().toLowerCase().equals(kodeMatkul) &&
                    mkmhs.getNim().toLowerCase().equals(nim)) return mkmhs;
        }
        return null;
    }
}

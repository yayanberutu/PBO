/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package no2_kuis2_pbo;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Sarah Christine (11318058)
 */
public class GeneralUtils {
    public static java.sql.Date convertToDateFromString(String date, String format) throws ParseException{
        SimpleDateFormat sdf1 = new SimpleDateFormat(format);
        Date dt = sdf1.parse(date);
        return new java.sql.Date(dt.getTime());
    }
    
    public static TMahasiswa findMahasiswa(String nim, List<TMahasiswa> listMahasiswa){
        Iterator<TMahasiswa> iterator = listMahasiswa.iterator();
        while(iterator.hasNext()){
            TMahasiswa mhs = iterator.next();
            if(mhs.getNim().toLowerCase().equals(nim)) return mhs;
        }
        return null;
    }
    
    public static TMatakuliah findMatakuliah(String kodeMatkul, List<TMatakuliah> listMatakuliah){
        Iterator<TMatakuliah> iterator = listMatakuliah.iterator();
        while(iterator.hasNext()){
            TMatakuliah mkl = iterator.next();
            if(mkl.getIdMatkul().equals(kodeMatkul)) return mkl;
        }
        return null;
    }
    
    public static TMkMhs checkKRSExist(String kodeMatkul, String nim, List<TMkMhs> listMkMhs){
        Iterator<TMkMhs> iterator = listMkMhs.iterator();
        while(iterator.hasNext()){
            TMkMhs mkmhs = iterator.next();
            if(mkmhs.getIdMatkul().toLowerCase().equals(kodeMatkul) && mkmhs.getNim().toLowerCase().equals(nim)) return mkmhs;
        }
        return null;
    }
    
    public static TMkMhs findKRS(String nim, List<TMkMhs> listMkMhs){
        Iterator<TMkMhs> iterator = listMkMhs.iterator();
        while(iterator.hasNext()){
            TMkMhs mkhs = iterator.next();
            if(mkhs.getNim().equals(nim)) return mkhs;
        }
        return null;
    }
}

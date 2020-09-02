package introtooop;

import java.util.ArrayList;
import java.util.List;
public class Mahasiswa extends Manusia {
    private String nim;
    private float ipk;
    List<Float> listIps = new ArrayList<>();

    public Mahasiswa(){};

    public void setNim(String value){
        nim = value;
    }

    public String getNim(){
        return nim;
    }

    public float getIpk(){
        return ipk;
    }

    protected void rekamIpsSaya(List<Float> pListIps){
        for(float ips : pListIps){
            ipk += ips;
        }
        ipk /=pListIps.size();
    }

    //override prosedur cetakInformasi
    protected void cetakInformasi(){
        System.out.println("Nama:\t" + getNama());
        System.out.println("NIM:\t" + getNim());
        System.out.println("Usia:\t" + getUsia());
        System.out.println("Alamat:\t" + getAlamat());
        System.out.println("IPK:\t" + getIpk());

    }
}

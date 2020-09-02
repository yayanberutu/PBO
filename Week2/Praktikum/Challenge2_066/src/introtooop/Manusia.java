package introtooop;

public class Manusia {
    private String nama, alamat;
    private int usia;

    public Manusia(){};

    public void setNama(String value){
        nama = value;
    }

    public void setAlamat(String value){
        alamat = value;
    }

    public void setUsia(int value){
        usia = value;
    }

    public String  getNama(){
        return nama;
    }

    public String getAlamat(){
        return alamat;
    }

    public int getUsia(){
        return usia;
    }

    protected void cetakInformasi(String _nama, String _alamat, int _usia){
        System.out.println("Nama:\t" + _nama);
        System.out.println("Usia:\t" + _usia);
        System.out.println("Alamat:\t" + _alamat);

    }
}

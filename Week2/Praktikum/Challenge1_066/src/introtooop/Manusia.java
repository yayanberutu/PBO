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

    protected void cetakInformasi(){
        System.out.println("Nama:\t" + nama);
        System.out.println("Usia:\t" +usia);
        System.out.println("Alamat:\t" + alamat);

    }
}

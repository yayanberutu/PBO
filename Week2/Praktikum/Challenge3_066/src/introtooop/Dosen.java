package introtooop;

public class Dosen extends Manusia{
    private String NIDN;
    private boolean adaJabfung;
    private int jlhPenelitian;

    public void setNIDN(String value){
        NIDN = value;
    }
    public void setAdaJabfung(boolean value){
        adaJabfung = value;
    }

    public void setJlhPenelitian(int value){
        jlhPenelitian = value;
    }

    protected float hitungPerformance(){
        if(adaJabfung == true) {
            if(jlhPenelitian < 5){
                return (3 * 2);
            }
            else if(jlhPenelitian >= 5){
                return (7 * 2);
            }
        }

        return 0;
    }

    protected void cetakInformasi(){
        System.out.println("Nama Dosen:\t"+ getNama());
        System.out.println("NIDN:\t" + NIDN);
        System.out.println("Alamat:\t" + getAlamat());
        System.out.println("Performansi:\t"+ hitungPerformance());
    }
}

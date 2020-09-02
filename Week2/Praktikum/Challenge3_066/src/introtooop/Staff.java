package introtooop;

public class Staff extends Manusia{
    private String NIP, lokasiRuangan;

    public void setNip(String value){
        NIP = value;
    }
    public void setLokasiRuangan(String value){
        lokasiRuangan = value;
    }

    protected String getRuangan(){
        return lokasiRuangan;
    }

}

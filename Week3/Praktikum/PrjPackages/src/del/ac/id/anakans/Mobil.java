package del.ac.id.anakans;
import del.ac.id.induks.Kendaraan;

public class Mobil extends Kendaraan{
    private int jlhTorsi, faktorPembagi;
    private float panjangGagang, rasioGigi;

    public Mobil(int roda, int dimensikend, int spion,  String warna){
        setJlhRoda(roda);
        setWarnaKendaraan(warna);
        setJlhSpion(spion);
        setDimensiKendaraan(dimensikend);
    }

    public void setJlhTorsi(int value) {
        jlhTorsi= value;
    }

    public void setPanjangGagang(float value) {
        panjangGagang = value;
    }

    public void setRasioGigi(float value){
        rasioGigi = value;
    }

    public void setFaktorPembagi(int value){
        faktorPembagi = value;
    }

    @Override
    public float kalkulasiPercepatan(int jlhTorsi, float panjangGagang, float rasioGigi) {
        return (jlhTorsi * panjangGagang)/(faktorPembagi * rasioGigi);
    }

    @Override
    public void cetakInformasi(Object o) {
        System.out.println("========================================================================");
        System.out.println(String.format("Mobil saya bewarna %s.", ((Mobil)o).getWarnaKendaraan()));
        System.out.println(String.format("Dimensi mobil tersebut kurang lebih %d meter persegi. ", ((Mobil)o).getDimensiKendaraan()));
        System.out.println(String.format("dan dengan percepatan sekitar %.2f km/jam. ", kalkulasiPercepatan(jlhTorsi, panjangGagang, rasioGigi)));
        System.out.println(String.format("Mobil tersebut memiliki %d kaca spion dan %d roda", ((Mobil)o).getJlhSpion(), ((Mobil)o).getJlhRoda()));
        System.out.println("========================================================================");
    }
}

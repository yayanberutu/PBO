package del.ac.id.anakans;

import del.ac.id.induks.*;

public class Motor extends Kendaraan{
    private int jlhTorsi, faktorPembagi;
    private float panjangGagang, rasioGigi;

    public Motor(int roda, int dimensikend, int spion,  String warna){
        setJlhRoda(roda);
        setWarnaKendaraan(warna);
        setJlhSpion(spion);
        setDimensiKendaraan(dimensikend);
    }

    public void setJlhTorsi(int value) {
        jlhTorsi = jlhTorsi;
    }
    public void setPanjangGagang(float value){
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
        return super.kalkulasiPercepatan(jlhTorsi, panjangGagang, rasioGigi)*faktorPembagi;
    }

    @Override
    public void cetakInformasi(Object o) {
        System.out.println("========================================================================");
        System.out.println(String.format("Motor saya bewarna %s.", ((Motor)o).getWarnaKendaraan()));
        System.out.println(String.format("Dimensi motor tersebut kurang lebih %d meter persegi. ", ((Motor)o).getDimensiKendaraan()));
        System.out.println(String.format("dan dengan percepatan sekitar %.2f km/jam. ", kalkulasiPercepatan(jlhTorsi, panjangGagang, rasioGigi)));
        System.out.println(String.format("Motor tersebut memiliki %d kaca spion dan %d roda", ((Motor)o).getJlhSpion(), ((Motor)o).getJlhRoda()));
        System.out.println("========================================================================");
    }
}

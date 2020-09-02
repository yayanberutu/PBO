package del.ac.id.anakans;
import del.ac.id.induks.Kendaraan;

public class Sepeda extends Kendaraan {
    public Sepeda(int roda, int dimensikend, int spion,  String warna){
        setJlhRoda(roda);
        setWarnaKendaraan(warna);
        setJlhSpion(spion);
        setDimensiKendaraan(dimensikend);
    }

    @Override
    public float kalkulasiPercepatan(int jlhTorsi, float panjangGagang, float rasioGigi) {
//        return super.kalkulasiPercepatan(jlhTorsi, panjangGagang, rasioGigi);
        //Karena sepeda tidak memiliki jumlah toris, panjang gagang dan juga rasio gigi,
        // disini akan diberi nilai default, yaitu 10km/jam
        return (jlhTorsi == 0 || panjangGagang == 0 || rasioGigi ==0)? (float)10.0 : (jlhTorsi * panjangGagang * rasioGigi);
    }

    @Override
    public void cetakInformasi(Object o) {
        System.out.println("========================================================================");
        System.out.println(String.format("Sepeda saya bewarna %s.", ((Sepeda)o).getWarnaKendaraan()));
        System.out.println(String.format("Dimensi sepeda tersebut kurang lebih %d meter persegi. ", ((Sepeda)o).getDimensiKendaraan()));
        System.out.println(String.format("dan dengan percepatan sekitar %.2f km/jam. ", kalkulasiPercepatan(0, 0, 0)));
        System.out.println(String.format("Sepeda tersebut memiliki %d kaca spion dan %d roda", ((Sepeda)o).getJlhSpion(), ((Sepeda)o).getJlhRoda()));
        System.out.println("========================================================================");
    }
}



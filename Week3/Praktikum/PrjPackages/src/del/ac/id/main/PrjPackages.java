package del.ac.id.main;
import del.ac.id.anakans.* ;

public class PrjPackages {
    public static void main(String[] args){

        Motor objMotor = new Motor(2, 274, 2, "merah");
//        objMotor.setDimensiKendaraan(274);
//        objMotor.setJlhRoda(2);
//        objMotor.setJlhSpion(2);
//        objMotor.setWarnaKendaraan("merah");
        objMotor.setJlhTorsi(1);
        objMotor.setPanjangGagang((float) 15.5);
        objMotor.setRasioGigi((float) 4.2);
        objMotor.setFaktorPembagi(2);

        Mobil objMobil = new Mobil(4, 632, 2, "hitam");
//        objMobil.setDimensiKendaraan(632);
//        objMobil.setJlhRoda(4);
//        objMobil.setJlhRoda(4);
//        objMobil.setJlhSpion(2);
        objMobil.setWarnaKendaraan("hitam");
        objMobil.setJlhTorsi(1);
        objMobil.setPanjangGagang((float) 20.5);
        objMobil.setRasioGigi((float) 50.4);
        objMobil.setFaktorPembagi(3);

        Sepeda objSepeda = new Sepeda(2, 632, 0, "pinky");
//        objSepeda.setDimensiKendaraan(632);
//        objSepeda.setJlhRoda(2);
//        objSepeda.setJlhSpion(0);
//        objSepeda.setWarnaKendaraan("pinky");

        //invoking method cetak Informasi
        objMotor.cetakInformasi(objMotor);
        objMobil.cetakInformasi(objMobil);
        objSepeda.cetakInformasi(objSepeda);
    }

}

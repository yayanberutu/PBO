package del.ac.id.yosepri;

import java.util.ArrayList;

public class Toko {
    static ArrayList<Barang> barang = new ArrayList<>();

    public Barang cariBarangById(String id){

        for(int i=0; i<barang.size(); i++){
            if(id.equals(barang.get(i).getId())){
                return barang.get(i);
            }
        }

        return null;
    }

    public void beliBarang(String id, int jumlah, double uang){
        Barang barangbeli = null;
        barangbeli = cariBarangById(id);
        barangbeli.kurangiStok(jumlah);
    }

    public static void  tambahbrg(Barang brg){
        barang.add(brg);
    }

    public static void cetakInformasiBarang(){
        for(int i=0; i<barang.size(); i++){
            System.out.println(String.format("(%s).(%s)", barang.get(i).getId(), barang.get(i).getNama()));
        }
    }

    public static Barang cariBarang(Barang brg) throws IdNotFoundEksepsi{
        for(int i= 0; i < barang.size(); i++){
            if(barang.get(i).getId().equals(brg.getId())){
                return barang.get(i);
            }
        }
        throw new IdNotFoundEksepsi();
    }

    public static void beliBarang(Barang brg, double uang) throws StokEksepsi, UangKurangEksepsi{
        try {
            cekStock(brg);
            try {
                hitungsisaUang(brg, uang);
            }
            catch (UangKurangEksepsi uke){
                System.out.println("Uang anda tidak mencukupi");
            }
        }
        catch (StokEksepsi se){
            System.out.println("Stok barang yang tersedia tidak cukup!");
        } catch (IdNotFoundEksepsi idNotFoundEksepsi) {
            idNotFoundEksepsi.printStackTrace();
        }

    }

    public static boolean cekStock(Barang brg) throws StokEksepsi, IdNotFoundEksepsi {
        if(cariBarang(brg).getStok() >= brg.getStok()){
            cariBarang(brg).setStok(cariBarang(brg).getStok() - brg.getStok());
        }
        throw new StokEksepsi();
    }

    public static double hitungsisaUang(Barang brg, double uang) throws UangKurangEksepsi {
        if(brg.getHarga()*brg.getStok() < uang){
            uang -= brg.getHarga();
            return uang;
        }
        throw new UangKurangEksepsi(uang);
    }

}

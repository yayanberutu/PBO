package introtooop;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IntroToOOP {
    public static void main(String[] args){
        String _nama, _alamat;
        int _usia;
        List<Float> _myIps = new ArrayList<>();

        Scanner scn = new Scanner(System.in);

        System.out.print("Masukkan nama: ");
        _nama = scn.nextLine();

        System.out.print("Masukkan usia: ");
        _usia = scn.nextInt();
//        System.out.print("Masukkan alamat: ");
//        _alamat = scn.nextLine();
        System.out.print("Pekerjaan:\n1.Mahasiswa\n2.Staff\n3.Dosen\n");
        int pilih;
        pilih = scn.nextInt();
        if(pilih == 1){
            System.out.print("Masukkan NIM: ");
            String _nim;
            _nim = scn.next();

            for(int i=0; i<5; i++){
                System.out.print(String.format("Masukkan IPS ke-%d:", i+1));
                _myIps.add(scn.nextFloat());
            }
            System.out.println("==================================================\n");

            //Instansiasi Kelas Mahasiswa
            Mahasiswa objMahasiswa = new Mahasiswa();

            objMahasiswa.setNama(_nama);

            objMahasiswa.setNim(_nim);
            objMahasiswa.setUsia(_usia);

            //invoke procedure rekamIpsSaya
            objMahasiswa.rekamIpsSaya(_myIps);

            objMahasiswa.cetakInformasi();
        }
        if(pilih == 2){
//            Scanner scn = new Scanner();
            String _nip, _lokasiRuangan ;
            System.out.print("NIP: ");
            _nip = scn.next();
            System.out.print("Lokasi Ruangan: ");
            _lokasiRuangan = scn.next();
            Staff stf = new Staff();
            stf.setNip(_nip);
            stf.setLokasiRuangan(_lokasiRuangan);
        }
        if(pilih == 3){
//            Scanner scn = new Scanner();
            String _nidn;
            boolean adaJabfung;
            int jlhPenelian;

            System.out.print("NIDN: ");
            _nidn = scn.next();

            System.out.print("adaJabfung(0/1): ");
            adaJabfung = scn.nextBoolean();

            System.out.print("Jumlah Penelitian: ");
            jlhPenelian = scn.nextInt();



        }



    }


}

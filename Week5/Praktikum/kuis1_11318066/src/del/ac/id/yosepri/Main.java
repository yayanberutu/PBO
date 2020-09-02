package del.ac.id.yosepri;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        System.out.print("Masukkan jenis kendaraan(1:Truck|2:SportCar|3:FamilyCar): ");
        int pilih = scn.nextInt();
        if(pilih == 1){
            Truck truck = new Truck();
            System.out.print("Warna Truck: ");
            scn.nextLine();
            truck.setColor(scn.nextLine());
            System.out.print("Jumlah roda: ");
            truck.setNumOfWheels(scn.nextInt());
            System.out.print("Jumlah gigi: ");
            truck.setNumOfGears(scn.nextInt());
            truck.changeOilEngine();
            truck.loadPassenger();
            System.out.println(String.format("Truck memiliki %d roda, berwarna %s, dan bergigi %d", truck.getNumOfWheels(),truck.getColor(), truck.getNumOfGears()));
        }
        else if(pilih == 2){
            SportCar sp = new SportCar();
            System.out.print("Warna Mobil Sport: ");
            scn.nextLine();
            sp.setColor(scn.nextLine());
            System.out.print("Jumlah roda: ");
            sp.setNumOfWheels(scn.nextInt());
            System.out.print("Jumlah gigi: ");
            sp.setNumOfGears(scn.nextInt());
            sp.changeOilEngine();
            sp.loadPassenger();
            System.out.println(String.format("Mobil Sport memiliki %d roda, berwarna %s, dan bergigi %d", sp.getNumOfWheels(),sp.getColor(), sp.getNumOfGears()));
        }
        else if(pilih == 3){
            FamilyCar fm = new FamilyCar();
            System.out.print("Masukkan mode FamilyCar(Front||Rear||All): ");
            String mode = scn.next();
            fm.setModeCar(mode);
            System.out.println(String.format("Mobil anda berwarna %s, berroda %d, dan bergigi %d", fm.getColor(), fm.getNumOfWheels(),fm.getNumOfWheels()));
        }
    }
}

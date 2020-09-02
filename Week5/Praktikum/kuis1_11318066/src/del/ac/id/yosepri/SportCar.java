package del.ac.id.yosepri;

public class SportCar extends Vehicle {
    @Override
    public void changeOilEngine() {
        if (getNumOfGears() >=6 ){
            System.out.println("Oli mobil sport adalah chevron dan harus diganti ketika jarak tempuh sudah mencapai 1000Km");
        }
        else if(getNumOfGears()<6 && getNumOfGears() >= 4){
            System.out.println("Oli mobil anda harus diganti ketika harak mencapai 400 Km");
        }
        else {
            System.out.println("Tidak ada mobil yang punya gear dibawah 4");
        }

    }

    @Override
    public void loadPassenger() {
        if(getNumOfWheels() >=6){
            System.out.println("Penumpang mobil sport maksimal 4 orang");
        }
        else if(getNumOfWheels() < 6 && getNumOfWheels() >=4 ){
            System.out.println("Penumpang mobil sport maksimal 3 orang");
        }
    }
}

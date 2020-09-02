package del.ac.id.yosepri;

public class Truck extends  Vehicle{

    @Override
    public void changeOilEngine() {
        if (getNumOfGears() >=12 ){
            System.out.println("Oli Truck adalah Top One dan harus diganti ketika jarak tempuh sudah mencapai 3000Km");
        }
        else if(getNumOfGears()<12 && getNumOfGears() >= 6){
            System.out.println("Oli Truck anda harus diganti ketika harak mencapai 1500 Km");
        }
        else {
            System.out.println("Tambahi jumlah gear anda agar truck bisa berjalan");
        }
    }

    @Override
    public void loadPassenger() {
        if(getNumOfWheels() >=12){
            System.out.println("Truk bisa menampung beban seberat 3000 Kg");
        }
        else if(getNumOfWheels() < 12 && getNumOfWheels() >=8 ){
            System.out.println("Truk bisa menampung beban seberat 1500 Kg");
        }
        else if(getNumOfWheels() < 8 && getNumOfWheels() >=6 ){
            System.out.println("Truk hanya bisa menampung beban seberat 900 Kg");
        }
    }

}

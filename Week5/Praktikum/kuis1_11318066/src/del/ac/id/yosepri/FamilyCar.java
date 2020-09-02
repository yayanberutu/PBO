package del.ac.id.yosepri;

public class FamilyCar extends Vehicle implements IFamilyCar {
    private String mode;
    @Override
    public void setModeCar(String mode) {
        this.mode = mode;
        if(mode.toLowerCase().equals("front")){
            System.out.println("sumber gerak mobil dari depan");
        }
        else if (mode.toLowerCase().equals("rear")) {
            System.out.println("sumber gerak mobil dari belakang");
        }
        else if (mode.toLowerCase().equals("all")){
            System.out.println("sumber gerak dari depan dan belakang");
        }
        designCarBasedOnMode();
    }

    @Override
    public void designCarBasedOnMode() {
        if(mode.toLowerCase().equals("front")){
            setColor("Pink");
            setNumOfGears(5);
            setNumOfWheels(6);
        }
        else if(mode.toLowerCase().equals("rear")){
            setColor("Black");
            setNumOfGears(6);
            setNumOfWheels(4);
        }
        else if (mode.toLowerCase().equals("all")){
            setColor("Yellow");
            setNumOfGears(4);
            setNumOfWheels(5);
        }
    }

    @Override
    public void changeOilEngine() {
        if (getNumOfGears() > 5){
            System.out.println("Oli mesin mobil ini harus diganti ketika jarak tempuh mobil sekitar 600 Km");
        }
        else {
            System.out.println("Oli mesin mobil ini harus diganti ketika jarak tempuh mobil sekitar 400 Km");
        }
    }

    @Override
    public void loadPassenger() {
        if (getNumOfWheels() == 4){
            System.out.println("Muatan mobil ini cukup untuk 1 keluarga yaitu 6 orang");
        }
        else if(getNumOfWheels() > 4){
            System.out.println("Muatan mobil ini bisa menampung 10 orang");
        }
        else {
            System.out.println("Tidak ada mobil yang memiliki ban kurang dari 4");
        }
    }
}

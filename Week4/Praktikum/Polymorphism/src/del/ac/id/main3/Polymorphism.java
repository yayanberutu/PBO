package del.ac.id.main3;

import del.ac.id.main1.Car;

public class Polymorphism {
    public static void main(String[] args) {
        Car objCar = new Car((float)150);
        System.out.println(String.format("Kurangi kecepatan dengan lepas gas: %.2f km/h", objCar.decreaseSpeed()));
        System.out.println(String.format("Kurangi kecepatan dengan engine brake: %.2f km/h", objCar.decreaseSpeed("engine")));
        System.out.println(String.format("Kurangi kecepatan dengan friction brake: %.2f km/h", objCar.decreaseSpeed("friction")));

    }
}

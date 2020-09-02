package del.ac.id.main2;

public class Polymorphism {
    public static void main(String[] args) {

        Car objTruck = new Truck ((float) 150);
        System.out.println(String.format("Kurangi kecepatan dengan lepas gas: %.2f km/h", objTruck.decreaseSpeed()));
        System.out.println(String.format("Kurangi kecepatan dengan engine brake: %.2f km/h", objTruck.decreaseSpeed("engine")));
        System.out.println(String.format("Kurangi kecepatan dengan friction brake: %.2f km/h", objTruck.decreaseSpeed("friction")));


    }
}

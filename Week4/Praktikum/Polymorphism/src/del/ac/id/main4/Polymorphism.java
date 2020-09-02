package del.ac.id.main4;


public class Polymorphism {
    public static void main(String[] args) {
        Car objCar = new Car((float)120);
        System.out.println(String.format("Kurangi kecepatan dengan lepas gas: %.2f km/h", objCar.decreaseSpeed()));
        System.out.println(String.format("Kurangi kecepatan dengan engine brake: %.2f km/h", objCar.decreaseSpeed("engine")));
        System.out.println(String.format("Kurangi kecepatan dengan friction brake: %.2f km/h", objCar.decreaseSpeed("friction")));

        //invoking caraMerakitObjekBergerak dari instance objCar
        objCar.caraMerakitObjekBergerak();

        //invoking method getGayaPergerakan dari instance objCar
        objCar.setBeratKendaraan(500);
        objCar.setPercepatan(120);
        System.out.println(String.format("Gaya mobil anda adalah %.2f Newton", objCar.getGayaPergerakan()));

        MovingObject objMotorcycle = new Motorcycle();

        //Invoking cara merakitObjekBergerak dari instance objMotorCycle
        objMotorcycle.caraMerakitObjekBergerak();

        //Invoking method getGayaPergerakan dari instance objMotorcycle
        objMotorcycle.setBeratKendaraan(100);
        objMotorcycle.setPercepatan(80);
        System.out.println(String.format("\nGaya mo tor anda adalah %.2f newton", objMotorcycle.getGayaPergerakan()));

    }
}

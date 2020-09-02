package del.ac.id.main2;

public class Truck extends Car{
    private float speed;

    //constructor di kelas truck ini
    // akan menerima sebuah parameter yang nantinya
    //akan dilempar nilainya ke kelas indukan
    //melalui konstraktor
    public Truck(float currentSpeed) {
        super(currentSpeed);
    }

    @Override
    public float decreaseSpeed() {
        speed = getCurrentSpeed() - (float) 13.5;
        return speed;
    }

    @Override
    public float decreaseSpeed(String brakeMechanism) {
        speed = getCurrentSpeed();
        if (brakeMechanism.toLowerCase().equals("engine")){
            return  speed -= 20;
        }
        else if(brakeMechanism.toLowerCase().equals("friction")){
            return speed -= 10;
        }
        else {
            return decreaseSpeed();
        }

        //        switch (brakeMechanism.toLowerCase()){
//            case "engine":
//                return this.currentSpeed -= BY_ENGINE_BRAKE;
//            case "friction" :
//                return this.currentSpeed -= BY_FRICTION_BRAKE;
//            default:
//                return decreaseSpeed();
//        }
    }
}

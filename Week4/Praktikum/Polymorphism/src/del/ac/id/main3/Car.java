package del.ac.id.main3;

public class Car implements IVehicle{
    private static final float BY_ENGINE_BRAKE = 10; //dengan turun kopling
    private static final float BY_FRICTION_BRAKE = 50; // dengan rem biasa
    private float currentSpeed;

    public Car(float currentSpeed){
        this.currentSpeed = currentSpeed;
    }

    //jika seseorang memperlambat mobil
    // by default (lepas gas mobil)
    public float decreaseSpeed(){
        return this.currentSpeed -= 5;
    }

    public float getCurrentSpeed() {
        return currentSpeed;
    }

    public float decreaseSpeed(String brakeMechanism){
        switch (brakeMechanism.toLowerCase()){
            case "engine":
                return this.currentSpeed -= BY_ENGINE_BRAKE;
            case "friction" :
                return this.currentSpeed -= BY_FRICTION_BRAKE;
            default:
                return decreaseSpeed();
        }
    }

}


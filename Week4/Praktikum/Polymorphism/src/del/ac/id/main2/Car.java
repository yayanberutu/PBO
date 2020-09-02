package del.ac.id.main2;

public class Car {
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
        if(brakeMechanism.toLowerCase().equals("engine")){
            return this.currentSpeed -= BY_ENGINE_BRAKE;
        }
        else if(brakeMechanism.toLowerCase().equals("friction")){
            return this.currentSpeed -= BY_FRICTION_BRAKE;
        }
        else {
            return decreaseSpeed();
        }
    }

}


package del.ac.id.main4;


public class Car extends MovingObject implements IVehicle{
    private static final float BY_ENGINE_BRAKE = 10; //dengan turun kopling
    private static final float BY_FRICTION_BRAKE = 50; // dengan rem biasa
    private float currentSpeed;


    public Car(float currentSpeed){
        this.currentSpeed = currentSpeed;
    }


    public float getCurrentSpeed() {
        return currentSpeed;
    }

    //jika seseorang memperlambat mobil
    // by default (lepas gas mobil)
    @Override
    public float decreaseSpeed(){
        return this.currentSpeed -= 5;
    }

    @Override
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

    @Override
    void caraMerakitObjekBergerak() {
        System.out.print("Cara merakit mobil adalah tambahkan 4 roda. \n");
        System.out.print("Selain itu tambahkan mesin 2000cc. \n");
    }
}


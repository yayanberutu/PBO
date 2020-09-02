package del.ac.id.yosepri;

public abstract class Vehicle {
    private int numOfWheels;
    private String color;
    private int numOfGears;

    public void setNumOfWheels(int numOfWheels)
    {
        this.numOfWheels = numOfWheels;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setNumOfGears(int numOfGears) {
        this.numOfGears = numOfGears;
    }

    public int getNumOfWheels() {
        return numOfWheels;
    }

    public String getColor() {

        return color;
    }

    public int getNumOfGears() {
        return numOfGears;
    }

    public abstract void changeOilEngine();
    public abstract void loadPassenger();

}

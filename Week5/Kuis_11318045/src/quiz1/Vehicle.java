/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quiz1;

/**
 *
 * @author asus
 */
public abstract  class Vehicle {
    private int numOfWheels;
    private String color;
    private int numOfGears;
    
    public void setNumOfWheels(int numOfWheels){
        this.numOfWheels = numOfWheels;
    }
    public void setColor(String color){
        this.color = color;
    }
    public void setNumOfGears(int numOfGears){
        this.numOfGears = numOfGears;
    }
    public int getnumOfWheels(){
        return numOfWheels;
    }
    public String getColor(){
        return color;
    }
    public int getnumOfGears(){
        return numOfGears;
    }
    

    private abstract void changeOilEngine();
    private abstract void loadPassenger();
}
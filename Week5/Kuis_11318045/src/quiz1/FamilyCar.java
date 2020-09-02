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
public class FamilyCar extends AbstractVehicle implements IFamilyCar{

    @Oveerride
    public void changeOilEngine(){
      System.out.println("Cair");

    }

    @Oveerride
    public void loadPassenger(){
        System.out.println("Bapak");

    }

    @Oveerride
     public void setModeCar(){
         System.out.println("Pajero sport");

    }

    @Override
     public void designCarBasedOnMode(){
        System.out.println("Bagus");
     }

}

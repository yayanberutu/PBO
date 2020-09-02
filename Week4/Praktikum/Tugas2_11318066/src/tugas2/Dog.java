package tugas2;

public class Dog extends Pet implements IMammal {

    public Dog(String name, int age){

    }

    public void eat(){
        System.out.println("Anjing memakan tulang");
    }

    public void speak(){
        System.out.println("Menggonggong");

    }


    @Override
    public void shedFun() {

    }
}

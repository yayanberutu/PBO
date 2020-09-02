package tugas2;

public class Cat extends Pet implements IMammal{

    public Cat(String name, int age){

    }
    public void speak(){
        System.out.println("Miau");
    }

    public void eat(){
        System.out.println("Kucing makan ikan");
    }
    @Override
    public void shedFun() {
        System.out.println("Auauau");
    }
}

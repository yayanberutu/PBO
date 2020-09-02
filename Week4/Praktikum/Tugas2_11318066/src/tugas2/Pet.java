package tugas2;

public class Pet implements IAnimal{
    private String name;
    private int age;


    public Pet(String name, int age){

    }

    public Pet() {
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public void eat() {
        System.out.println("Hewan bisa memakan tumbuhan, daging, dan segalanya");
    }

    @Override
    public void speak() {
        System.out.println("Hohoho");
    }
}

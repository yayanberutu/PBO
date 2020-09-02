package del.ac.id.tugas1;

public class Contractor extends Employee{
    public int workingHours;

    @Override
    public int calculateSalary() {
        return workingHours * getPaymentPerHour();
    }
}

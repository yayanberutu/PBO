package del.ac.id.tugas1;

public class FullTimeEmployee extends Employee {
    public int workingHours;
    public int calculateSalary(){
        return workingHours * getPaymentPerHour();
    }
}

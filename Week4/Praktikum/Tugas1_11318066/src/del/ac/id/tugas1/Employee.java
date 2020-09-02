package del.ac.id.tugas1;

public abstract class Employee {
    private String name;
    private int paymentPerHour;

    public abstract int calculateSalary();

    public void setName(String name) {
        this.name = name;
    }

    public void setPaymentPerHour(int paymentPerHour) {
        this.paymentPerHour = paymentPerHour;
    }

    public int getPaymentPerHour() {
        return paymentPerHour;
    }

    public String getName() {
        return name;
    }
}

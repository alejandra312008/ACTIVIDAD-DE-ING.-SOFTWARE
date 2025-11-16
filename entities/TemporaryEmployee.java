package xyz.cereshost.entities;

/**
 * Representa a un empleado temporal.
 * Extiende ContractEmployee y calcula su salario mensual fijo.
 */
public class TemporaryEmployee extends ContractEmployee {

    private double monthlySalary;

    public TemporaryEmployee(String id, String fullName, int contractDurationMonths, double monthlySalary) {
        super(id, fullName, contractDurationMonths);
        this.monthlySalary = monthlySalary;
    }

    public double getMonthlySalary() {
        return monthlySalary;
    }

    @Override
    public double calculateSalary() {
        return monthlySalary;
    }

    @Override
    public String toString() {
        return "TemporaryEmployee { " +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", contractDurationMonths=" + contractDurationMonths +
                ", monthlySalary=" + monthlySalary +
                " }";
    }
}

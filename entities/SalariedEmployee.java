package actividadingenieriasoftware.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SalariedEmployee extends Employee {
    private final Money monthlySalary;

    public SalariedEmployee(String id, String name, LocalDate hireDate, Money monthlySalary) {
        super(id, name, hireDate);
        this.monthlySalary = monthlySalary;
    }

    @Override
    public Money calculateGrossSalary() {
        return monthlySalary;
    }

    @Override
    public Money calculateBenefits() {
        Money benefits = FOOD_BONUS;

        // Bono por antigüedad (10% si tiene más de 5 años)
        if (getYearsOfService() > 5) {
            Money seniorityBonus = monthlySalary.multiply(new BigDecimal("0.10"));
            benefits = benefits.add(seniorityBonus);
        }

        return benefits;
    }

}

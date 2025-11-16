package co.edu.unicartagena.actvidadingenieria.entities;

import co.edu.unicartagena.actvidadingenieria.valuesobjects.Money;

import java.time.LocalDate;

public class TemporaryEmployee extends Employee {
    private final Money monthlySalary;
    private final LocalDate contractEndDate;

    public TemporaryEmployee(String id, String name, LocalDate hireDate,
                             Money monthlySalary, LocalDate contractEndDate) {
        super(id, name, hireDate);
        this.monthlySalary = monthlySalary;
        this.contractEndDate = contractEndDate;
    }

    @Override
    public Money calculateGrossSalary() {
        return monthlySalary;
    }

    @Override
    public Money calculateBenefits() {
        return Money.ZERO(); // No tiene beneficios
    }

}

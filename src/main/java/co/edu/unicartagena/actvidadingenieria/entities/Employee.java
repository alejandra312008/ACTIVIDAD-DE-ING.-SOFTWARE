package co.edu.unicartagena.actvidadingenieria.entities;

import co.edu.unicartagena.actvidadingenieria.valuesobjects.Money;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@Getter
public abstract class Employee {
    private final String id;
    private final String name;
    private final LocalDate hireDate;

    protected static final Money FOOD_BONUS = new Money("1000000");
    protected static final BigDecimal SOCIAL_SECURITY_RATE = new BigDecimal("0.04");
    protected static final BigDecimal ARL_RATE = new BigDecimal("0.005");

    public Employee(String id, String name, LocalDate hireDate) {
        this.id = id;
        this.name = name;
        this.hireDate = hireDate;
    }

    public int getYearsOfService() {
        return Period.between(hireDate, LocalDate.now()).getYears();
    }

    public abstract Money calculateGrossSalary();

    public Money calculateDeductions() {
        Money grossSalary = calculateGrossSalary();
        Money socialSecurity = grossSalary.multiply(SOCIAL_SECURITY_RATE);
        Money arl = grossSalary.multiply(ARL_RATE);
        return socialSecurity.add(arl);
    }

    public abstract Money calculateBenefits();

    public Money calculateNetSalary() {
        Money grossSalary = calculateGrossSalary();
        Money deductions = calculateDeductions();
        Money benefits = calculateBenefits();
        return grossSalary.add(benefits).subtract(deductions);
    }
}
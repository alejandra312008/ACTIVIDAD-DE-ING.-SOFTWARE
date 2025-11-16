package co.edu.unicartagena.actvidadingenieria.entities;

import co.edu.unicartagena.actvidadingenieria.exceptions.NegativeHoursException;
import co.edu.unicartagena.actvidadingenieria.valuesobjects.Money;

import java.math.BigDecimal;
import java.time.LocalDate;

public class HourlyEmployee extends Employee {
    private final Money hourlyRate;
    private BigDecimal hoursWorked;
    private final boolean acceptSavingsFund;

    public HourlyEmployee(String id, String name, LocalDate hireDate,
                          Money hourlyRate, BigDecimal hoursWorked,
                          boolean acceptSavingsFund) {
        super(id, name, hireDate);
        this.hourlyRate = hourlyRate;
        setHoursWorked(hoursWorked);
        this.acceptSavingsFund = acceptSavingsFund;
    }

    public void setHoursWorked(BigDecimal hoursWorked) {
        if (hoursWorked.compareTo(BigDecimal.ZERO) < 0) {
            throw new NegativeHoursException("Las horas trabajadas no pueden ser negativas");
        }
        this.hoursWorked = hoursWorked;
    }

    @Override
    public Money calculateGrossSalary() {
        BigDecimal regularHours = hoursWorked.min(new BigDecimal("40"));
        BigDecimal overtimeHours = hoursWorked.subtract(new BigDecimal("40")).max(BigDecimal.ZERO);

        Money regularPay = hourlyRate.multiply(regularHours);
        Money overtimePay = hourlyRate.multiply(new BigDecimal("1.5")).multiply(overtimeHours);

        return regularPay.add(overtimePay);
    }

    @Override
    public Money calculateBenefits() {
        Money benefits = Money.ZERO();

        // Fondo de ahorro para empleados con más de 1 año
        if (getYearsOfService() > 1 && acceptSavingsFund) {
            Money grossSalary = calculateGrossSalary();
            Money savingsFund = grossSalary.multiply(new BigDecimal("0.02"));
            benefits = benefits.add(savingsFund);
        }

        return benefits;
    }
}

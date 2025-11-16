package co.edu.unicartagena.actvidadingenieria.entities;

import co.edu.unicartagena.actvidadingenieria.exceptions.NegativeSalesException;
import co.edu.unicartagena.actvidadingenieria.valuesobjects.Money;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CommissionEmployee extends Employee{
    private final Money baseSalary;
    private BigDecimal salesAmount;
    private final BigDecimal commissionRate;

    private static final BigDecimal SALES_BONUS_THRESHOLD = new BigDecimal("20000000");
    private static final BigDecimal BONUS_RATE = new BigDecimal("0.03");

    public CommissionEmployee(String id, String name, LocalDate hireDate,
                              Money baseSalary, BigDecimal salesAmount,
                              BigDecimal commissionRate) {
        super(id, name, hireDate);
        this.baseSalary = baseSalary;
        setSalesAmount(salesAmount);
        this.commissionRate = commissionRate;
    }

    public void setSalesAmount(BigDecimal salesAmount) {
        if (salesAmount.compareTo(BigDecimal.ZERO) < 0) {
            throw new NegativeSalesException("Las ventas no pueden ser negativas");
        }
        this.salesAmount = salesAmount;
    }

    @Override
    public Money calculateGrossSalary() {
        Money commission = new Money(salesAmount).multiply(commissionRate);

        // Bono por ventas altas
        if (salesAmount.compareTo(SALES_BONUS_THRESHOLD) > 0) {
            Money salesBonus = new Money(salesAmount).multiply(BONUS_RATE);
            commission = commission.add(salesBonus);
        }

        return baseSalary.add(commission);
    }

    @Override
    public Money calculateBenefits() {
        return FOOD_BONUS; // Bono alimentación
    }
}

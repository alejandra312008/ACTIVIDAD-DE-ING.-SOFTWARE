package co.edu.unicartagena.actvidadingenieria.valuesobjects;

import co.edu.unicartagena.actvidadingenieria.exceptions.NegativeSalaryException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Money {
    private final BigDecimal amount;

    public static Money ZERO() {
        return new Money(BigDecimal.ZERO);
    }

    public Money(BigDecimal amount) {
        // Forzamos 2 decimales SIEMPRE
        this.amount = amount.setScale(2, RoundingMode.HALF_UP);

        if (this.amount.compareTo(BigDecimal.ZERO) < 0) {
             throw new IllegalArgumentException("El monto no puede ser negativo");
        }
    }

    public Money(String amount) {
        this(new BigDecimal(amount));
    }

    public Money(double amount) {
        this(BigDecimal.valueOf(amount));
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Money add(Money other) {
        return new Money(this.amount.add(other.amount));
    }

    public Money subtract(Money other) {
        BigDecimal result = this.amount.subtract(other.amount);
        
        if (result.compareTo(BigDecimal.ZERO) < 0) {
            throw new NegativeSalaryException("El salario neto no puede ser negativo");
        }
        return new Money(result);
    }

    public Money multiply(BigDecimal multiplicand) {
        return new Money(this.amount.multiply(multiplicand));
    }

    public Money multiply(double multiplicand) {
        return multiply(BigDecimal.valueOf(multiplicand));
    }

   @Override
public String toString() {
    return "$" + String.format("%,.2f", amount);
}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return amount.compareTo(money.amount) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
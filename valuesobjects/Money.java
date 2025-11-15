package actividadingenieriasoftware.valuesobjects;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Money {
    private final BigDecimal amount;

    private static final BigDecimal ZERO = BigDecimal.ZERO;
    private static final int SCALE = 2;
    private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;

    public Money(BigDecimal amount) {
        if (amount.compareTo(ZERO) < 0) {
            throw new IllegalArgumentException("El monto no puede ser negativo");
        }
        this.amount = amount.setScale(SCALE, ROUNDING_MODE);
    }

    public Money(String amount) {
        this(new BigDecimal(amount));
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Money add(Money other) {
        return new Money(this.amount.add(other.amount));
    }

    public Money subtract(Money other) {
        BigDecimal result = this.amount.subtract(other.amount);
//        if (result.compareTo(ZERO) < 0) {
//            throw new NegativeSalaryException("El salario neto no puede ser negativo");
//        }
        return new Money(result);
    }

    public Money multiply(BigDecimal multiplier) {
        return new Money(this.amount.multiply(multiplier));
    }

    public Money multiply(double multiplier) {
        return multiply(BigDecimal.valueOf(multiplier));
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

    @Override
    public String toString() {
        return String.format("$%,.2f", amount);
    }

    public static Money ZERO() {
        return new Money(ZERO);
    }
}

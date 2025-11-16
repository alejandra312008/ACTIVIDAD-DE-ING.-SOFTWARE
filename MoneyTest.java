package actividadingenieriasoftware;

import actividadingenieriasoftware.valuesobjects.Money;

public class MoneyTest {

        @Test
        void testCreateMoneyWithValidAmount() {
            Money money = new Money(new BigDecimal("1000.50"));
            assertEquals(new BigDecimal("1000.50"), money.getAmount());
        }

        @Test
        void testCreateMoneyWithString() {
            Money money = new Money("2000.75");
            assertEquals(new BigDecimal("2000.75"), money.getAmount());
        }

        @Test
        void testCreateMoneyWithNegativeAmountThrowsException() {
            IllegalArgumentException exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> new Money(new BigDecimal("-100"))
            );
            assertEquals("El monto no puede ser negativo", exception.getMessage());
        }

        @ParameterizedTest
        @ValueSource(strings = {"-1", "-0.01", "-1000000"})
        void testCreateMoneyWithVariousNegativeAmountsThrowsException(String negativeValue) {
            assertThrows(IllegalArgumentException.class, () -> new Money(negativeValue));
        }

        @Test
        void testAddMoney() {
            Money money1 = new Money("1000");
            Money money2 = new Money("500");
            Money result = money1.add(money2);

            assertEquals(new BigDecimal("1500.00"), result.getAmount());
        }

        @Test
        void testSubtractMoney() {
            Money money1 = new Money("1000");
            Money money2 = new Money("300");
            Money result = money1.subtract(money2);

            assertEquals(new BigDecimal("700.00"), result.getAmount());
        }

        @Test
        void testSubtractMoneyResultingInNegativeThrowsException() {
            Money money1 = new Money("100");
            Money money2 = new Money("200");

            NegativeSalaryException exception = assertThrows(
                    NegativeSalaryException.class,
                    () -> money1.subtract(money2)
            );
            assertEquals("El salario neto no puede ser negativo", exception.getMessage());
        }

        @Test
        void testMultiplyWithBigDecimal() {
            Money money = new Money("1000");
            Money result = money.multiply(new BigDecimal("1.5"));

            assertEquals(new BigDecimal("1500.00"), result.getAmount());
        }

        @Test
        void testMultiplyWithDouble() {
            Money money = new Money("1000");
            Money result = money.multiply(2.0);

            assertEquals(new BigDecimal("2000.00"), result.getAmount());
        }

        @Test
        void testZeroMoney() {
            Money zero = Money.ZERO();
            assertEquals(BigDecimal.ZERO.setScale(2), zero.getAmount());
        }

        @Test
        void testEqualsAndHashCode() {
            Money money1 = new Money("1000");
            Money money2 = new Money("1000");
            Money money3 = new Money("2000");

            assertEquals(money1, money2);
            assertNotEquals(money1, money3);
            assertEquals(money1.hashCode(), money2.hashCode());
        }

        @Test
        void testToString() {
            Money money = new Money("1234567.89");
            String result = money.toString();

            assertTrue(result.contains("$1,234,567.89"));
        }
    }
}

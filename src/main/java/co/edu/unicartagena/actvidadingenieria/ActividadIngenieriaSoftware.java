package co.edu.unicartagena.actvidadingenieria;

import co.edu.unicartagena.actvidadingenieria.entities.*;
import co.edu.unicartagena.actvidadingenieria.servicios.PayrollCalculator;
import co.edu.unicartagena.actvidadingenieria.servicios.PayrollReportGenerator;
import co.edu.unicartagena.actvidadingenieria.valuesobjects.Money;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class ActividadIngenieriaSoftware {

    public static void main(String[] args) {
        // Crear ejemplos
        List<Employee> employees = Arrays.asList(
                new SalariedEmployee(
                        "001",
                        "Juan Pérez",
                        LocalDate.of(2018, 1, 15),
                        new Money("5000000")
                ),
                new HourlyEmployee(
                        "002",
                        "María García",
                        LocalDate.of(2022, 6, 1),
                        new Money("25000"),
                        new BigDecimal("45"),
                        true
                ),
                new CommissionEmployee(
                        "003",
                        "Carlos López",
                        LocalDate.of(2020, 3, 10),
                        new Money("3000000"),
                        new BigDecimal("25000000"),
                        new BigDecimal("0.06")
                ),
                new TemporaryEmployee(
                        "004",
                        "Ana Martínez",
                        LocalDate.of(2023, 1, 1),
                        new Money("3500000"),
                        LocalDate.of(2023, 12, 31)
                )
        );

        // Calcular nómina
        PayrollCalculator calculator = new PayrollCalculator();
        var payrollData = calculator.calculatePayroll(employees);
        PayrollReportGenerator reportGenerator = new PayrollReportGenerator();
        reportGenerator.printReport(payrollData);
        // Ejemplo de validaciones
        demonstrateValidations();
    }

    private static void demonstrateValidations() {
        // ejemplo de un empleado erróneo
        try {
            new HourlyEmployee(
                    "005",
                    "Test Validation",
                    LocalDate.now(),
                    new Money("10000"),
                    new BigDecimal("-10"),
                    false
            );
        } catch (Exception e) {
            System.out.println("error al crear el empleado: " + e.getMessage());
        }

        try {
            new CommissionEmployee(
                    "006",
                    "Test Validation",
                    LocalDate.now(),
                    new Money("1000000"),
                    new BigDecimal("-10000"),
                    new BigDecimal("0.05")
            );
        } catch (Exception e) {
            System.out.println("error al crear el empleado: " + e.getMessage());
        }
    }
}

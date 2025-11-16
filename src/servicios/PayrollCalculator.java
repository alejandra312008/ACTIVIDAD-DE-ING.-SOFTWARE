package xyz.cereshost.services;

import lombok.Getter;
import xyz.cereshost.entities.Employee;
import xyz.cereshost.valueobjects.Money;

import java.util.ArrayList;
import java.util.List;

public class PayrollCalculator {
    public List<PayrollResult> calculatePayroll(List<Employee> employees) {
        List<PayrollResult> results = new ArrayList<>();

        for (Employee employee : employees) {
            try {
                Money grossSalary = employee.calculateGrossSalary();
                Money deductions = employee.calculateDeductions();
                Money benefits = employee.calculateBenefits();
                Money netSalary = employee.calculateNetSalary();

                PayrollResult result = new PayrollResult(
                        employee.getId(),
                        employee.getName(),
                        employee.getClass().getSimpleName(),
                        grossSalary,
                        deductions,
                        benefits,
                        netSalary
                );
                results.add(result);
            } catch (Exception e) {
                System.err.printf("Error calculando nómina para %s: %s%n",
                        employee.getName(), e.getMessage());
            }
        }

        return results;
    }

    @Getter
    public static class PayrollResult {
        private final String employeeId;
        private final String employeeName;
        private final String employeeType;
        private final Money grossSalary;
        private final Money deductions;
        private final Money benefits;
        private final Money netSalary;

        public PayrollResult(String employeeId, String employeeName, String employeeType,
                             Money grossSalary, Money deductions, Money benefits, Money netSalary) {
            this.employeeId = employeeId;
            this.employeeName = employeeName;
            this.employeeType = employeeType;
            this.grossSalary = grossSalary;
            this.deductions = deductions;
            this.benefits = benefits;
            this.netSalary = netSalary;
        }
    }
}

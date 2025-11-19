package co.edu.unicartagena.actvidadingenieria.servicios;
import co.edu.unicartagena.actvidadingenieria.entities.Employee;
import co.edu.unicartagena.actvidadingenieria.valuesobjects.Money;
// import lombok.Getter; // ELIMINADO PARA EVITAR ERRORES DE COMPILACIÓN

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

                // Aquí usamos employee.getId() y getName(). 
        
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

    // @Getters
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

        // GETTERS 

        public String getEmployeeId() {
            return employeeId;
        }

        public String getEmployeeName() {
            return employeeName;
        }

        public String getEmployeeType() {
            return employeeType;
        }

        public Money getGrossSalary() {
            return grossSalary;
        }

        public Money getDeductions() {
            return deductions;
        }

        public Money getBenefits() {
            return benefits;
        }

        public Money getNetSalary() {
            return netSalary;
        }
    }
}
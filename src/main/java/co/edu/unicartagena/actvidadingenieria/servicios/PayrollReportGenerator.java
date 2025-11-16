package co.edu.unicartagena.actvidadingenieria.servicios;

import java.math.BigDecimal;
import java.util.List;

public class PayrollReportGenerator {

    public String generateReport(List<PayrollCalculator.PayrollResult> payrollData) {
        StringBuilder report = new StringBuilder();

        report.append("=".repeat(80)).append("\n");
        report.append("REPORTE DE NÓMINA").append("\n");
        report.append("=".repeat(80)).append("\n");

        BigDecimal totalGross = BigDecimal.ZERO;
        BigDecimal totalDeductions = BigDecimal.ZERO;
        BigDecimal totalBenefits = BigDecimal.ZERO;
        BigDecimal totalNet = BigDecimal.ZERO;

        for (PayrollCalculator.PayrollResult data : payrollData) {
            report.append(String.format("%nEmpleado: %s (%s)%n",
                    data.getEmployeeName(), data.getEmployeeId()));
            report.append(String.format("Tipo: %s%n", data.getEmployeeType()));
            report.append(String.format("Salario Bruto: %s%n", data.getGrossSalary()));
            report.append(String.format("Deducciones: %s%n", data.getDeductions()));
            report.append(String.format("Beneficios: %s%n", data.getBenefits()));
            report.append(String.format("Salario Neto: %s%n", data.getNetSalary()));
            report.append("-".repeat(40)).append("\n");

            totalGross = totalGross.add(data.getGrossSalary().getAmount());
            totalDeductions = totalDeductions.add(data.getDeductions().getAmount());
            totalBenefits = totalBenefits.add(data.getBenefits().getAmount());
            totalNet = totalNet.add(data.getNetSalary().getAmount());
        }

        report.append("\n").append("=".repeat(80)).append("\n");
        report.append("TOTALES:").append("\n");
        report.append(String.format("Salario Bruto Total: $%,.2f%n", totalGross));
        report.append(String.format("Deducciones Totales: $%,.2f%n", totalDeductions));
        report.append(String.format("Beneficios Totales: $%,.2f%n", totalBenefits));
        report.append(String.format("Salario Neto Total: $%,.2f%n", totalNet));
        report.append("=".repeat(80)).append("\n");

        return report.toString();
    }

    public void printReport(List<PayrollCalculator.PayrollResult> payrollData) {
        System.out.println(generateReport(payrollData));
    }
}
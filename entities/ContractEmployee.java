package xyz.cereshost.entities;

/**
 * Clase abstracta para empleados que trabajan bajo contrato.
 * Puede representar empleados temporales, por proyecto, etc.
 */
public abstract class ContractEmployee extends Employee {

    protected int contractDurationMonths;

    public ContractEmployee(String id, String fullName, int contractDurationMonths) {
        super(id, fullName);
        this.contractDurationMonths = contractDurationMonths;
    }

    public int getContractDurationMonths() {
        return contractDurationMonths;
    }

    @Override
    public String toString() {
        return "ContractEmployee { " +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", contractDurationMonths=" + contractDurationMonths +
                " }";
    }
}

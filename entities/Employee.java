package xyz.cereshost.entities;

/**
 * Clase abstracta que representa a un empleado genérico.
 * Todas las clases de tipos de empleados deben extender esta clase.
 */
public abstract class Employee {

    protected String id;
    protected String fullName;

    public Employee(String id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    /**
     * Calcula el salario del empleado según su tipo.
     *
     * @return salario total en formato double.
     */
    public abstract double calculateSalary();

    @Override
    public String toString() {
        return "Employee { " +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                " }";
    }
}

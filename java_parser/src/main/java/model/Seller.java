package model;

public class Seller {
    private String cpf;
    private String name;
    private Double salary;

    public Seller(String cpf, String name, Double salary){
        this.cpf = cpf;
        this.name = name;
        this.salary = salary;
    }

    public String getCpf() {
        return cpf;
    }

    public String getName() {
        return name;
    }

    public Double getSalary() {
        return salary;
    }

}

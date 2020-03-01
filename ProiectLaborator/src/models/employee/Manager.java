package models.employee;

import models.company.Department;
import models.project.Project;

import java.util.Calendar;

import static java.util.Calendar.*;

public class Manager extends Employee {
    private Double bonus;

    public Manager() {
    }

    public Manager(Employee employee){
        super(employee);
    }

    public Double getBonus() {
        return bonus;
    }

    public void setBonus(Double bonus) {
        this.bonus = bonus;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "id=" + this.getId() +
                ", name='" + this.getName() + '\'' +
                ", date_of_birth=" + this.getDate_of_birth().get(DATE) + '-' + this.getDate_of_birth().get(MONTH) + '-' + this.getDate_of_birth().get(YEAR) +
                ", email='" + this.getEmail() + '\'' +
                ", phone_number='" + this.getPhone_number() + '\'' +
                ", hire_date=" + this.getHire_date().get(DATE) + '-' + this.getHire_date().get(MONTH) + '-' + this.getHire_date().get(YEAR) +
                ", job=" + this.getJob() +
                ", department=" + this.getDepartment() +
                ", salary=" + computeSalary() +
                '}';
    }

    @Override
    public Double computeSalary(){
        Double originalSalary = super.computeSalary();
        return originalSalary;// + bonus;
    }
}

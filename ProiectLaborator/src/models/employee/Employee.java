package models.employee;

import models.company.Company;
import models.company.Department;
import services.model_services.services_using_db.DepartmentEmployeeService;

import java.util.Calendar;
import static java.util.Calendar.*;

public class Employee extends Person implements Hired, Comparable<Employee> {
    private Integer id;
    private Calendar hire_date;
    private Job job;
    private Department department;

    {
        this.hire_date = Calendar.getInstance();

        Integer newEmpId = 0;
        for(Department dep : Company.getInstance().getDepartments()){
            for(Employee emp : dep.getEmployees()){
                if(newEmpId < emp.getId()){
                    newEmpId = emp.getId();
                }
            }
        }
        this.id = newEmpId + 1;

    }

    public Employee() {
    }

    public Employee(Employee employee) {
        this.id = employee.id;
        this.setName(employee.getName());
        this.setPhone_number(employee.getPhone_number());
        this.setEmail(employee.getEmail());
        this.setDate_of_birth(employee.getDate_of_birth().get(YEAR),employee.getDate_of_birth().get(MONTH),employee.getDate_of_birth().get(DATE));
        this.hire_date = employee.hire_date;
        this.job = employee.job;
        this.department = employee.department;
    }

    public Employee(String name, int year, int month, int day, String email, String phone_number, Department department) {
        this.setName(name);
        this.setDate_of_birth(year,month,day);
        this.setEmail(email);
        this.setPhone_number(phone_number);
        this.department = department;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Calendar getHire_date() {
        return hire_date;
    }

    public void setHire_date(int year, int month, int day) {
        this.hire_date.set(year, month-1, day);
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department, boolean logFlag) {
        this.department = department;
        DepartmentEmployeeService.getInstance().addEmployee(department,this, logFlag);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + this.getName() + '\'' +
                ", date_of_birth=" + this.getDate_of_birth().get(DATE) + '-' + (this.getDate_of_birth().get(MONTH)+1) + '-' + this.getDate_of_birth().get(YEAR) +
                ", email='" + this.getEmail() + '\'' +
                ", phone_number='" + this.getPhone_number() + '\'' +
                ", hire_date=" + hire_date.get(DATE) + '-' + (hire_date.get(MONTH)+1) + '-' + hire_date.get(YEAR) +
                ", job=" + job +
                ", department=" + department +
                ", salary=" + computeSalary() +
                '}';
    }

    public Integer yearsWorked() {
        Calendar currentDay = Calendar.getInstance();
        if(hire_date.get(MONTH)<currentDay.get(MONTH))
            return yearsBetween(hire_date, currentDay);
        if(hire_date.get(MONTH)>currentDay.get(MONTH))
            return yearsBetween(hire_date, currentDay)-1;
        if(hire_date.get(DATE)<=currentDay.get(DATE))
            return yearsBetween(hire_date, currentDay);
        return yearsBetween(hire_date, currentDay)-1;
    }
    
    public Double computeSalary() {
//        Double modifierPercentage = (double)this.yearsWorked()/25D;
//        return Math.min(this.job.getMin_salary() + this.job.getMin_salary()*modifierPercentage, this.job.getMax_salary());
    return 2000.0;
    }

    public Manager getManager() {
        return this.department.getManager();
    }

    @Override
    public int compareTo(Employee o) {
        return this.id.compareTo(o.getId());
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Employee){
            return this.id.equals(((Employee) o).getId());
        }
        return false;
    }
}

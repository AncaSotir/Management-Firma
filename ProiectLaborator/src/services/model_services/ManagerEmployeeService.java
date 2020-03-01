package services.model_services;

import models.employee.Employee;
import models.employee.Job;
import models.employee.Manager;
import models.employee.Person;
import services.model_services.services_using_db.DepartmentEmployeeService;

import java.util.Calendar;

import static java.util.Calendar.*;

public class ManagerEmployeeService {
    private static ManagerEmployeeService ourInstance = new ManagerEmployeeService();

    public static ManagerEmployeeService getInstance() {
        return ourInstance;
    }

    private ManagerEmployeeService() {
    }


    public void hire (Manager manager, Person person, Job job, boolean logFlag) {
        Calendar currentDate = Calendar.getInstance();

        Employee newEmployee = new Employee();
        newEmployee.setName(person.getName());
        newEmployee.setDate_of_birth(person.getDate_of_birth().get(YEAR), person.getDate_of_birth().get(MONTH), person.getDate_of_birth().get(DATE));
        newEmployee.setEmail(person.getEmail());
        newEmployee.setPhone_number(person.getPhone_number());
        newEmployee.setHire_date(currentDate.get(YEAR), currentDate.get(MONTH), currentDate.get(DATE));
        newEmployee.setJob(job);
        newEmployee.setDepartment(manager.getDepartment(),true);

        DepartmentEmployeeService.getInstance().addEmployee(manager.getDepartment(), newEmployee, logFlag);
        CompanyApplicantService.getInstance().removeApplicant(person, logFlag);
    }

    public void fire(Manager manager, Employee employee, boolean logFlag) {
        DepartmentEmployeeService.getInstance().removeEmployee(manager.getDepartment(), employee, logFlag);
    }

}

package services.model_services.services_using_db;

import loggers.CompanyLogger;
import models.company.Company;
import models.company.Department;
import models.employee.Employee;
import services.database_services.utils.DBUtils;

public class DepartmentEmployeeService {
    private static DepartmentEmployeeService ourInstance = new DepartmentEmployeeService();

    public static DepartmentEmployeeService getInstance() {
        return ourInstance;
    }

    private DepartmentEmployeeService() {
    }


    public void addEmployee (Department department, Employee employee, boolean logFlag) {
        if(logFlag){
            DBUtils.getInstance().insertEmployee(Company.getInstance().getConnection(),employee);
            String logMessage = "In department ";
            logMessage = logMessage.concat(department.getId().toString());
            logMessage = logMessage.concat(": Added new employee: ");
            logMessage = logMessage.concat(employee.toString());
            CompanyLogger.getInstance().logAction(logMessage);
        }
        if(department != null){
            department.getEmployees().add(employee);
        }
    }

    public void removeEmployee (Department department, Employee employee, boolean logFlag) {
        if(logFlag){
            DBUtils.getInstance().deleteEmployee(Company.getInstance().getConnection(), employee.getId());
            String logMessage = "In department ";
            logMessage = logMessage.concat(department.getId().toString());
            logMessage = logMessage.concat(": Removed employee: ");
            logMessage = logMessage.concat(employee.toString());
            CompanyLogger.getInstance().logAction(logMessage);
        }

        department.getEmployees().remove(employee);
    }

    public void listEmployees (Department department, boolean logFlag) {
        for(Employee emp : department.getEmployees()) {
            System.out.println(emp);
        }
        if(logFlag){
            String logMessage = "In department ";
            logMessage = logMessage.concat(department.getId().toString());
            logMessage = logMessage.concat(": Listed employees");
            CompanyLogger.getInstance().logAction(logMessage);
        }
    }

    public Employee getEmployeeById (Department department, Integer id, boolean logFlag){
        if(logFlag){
            String logMessage = "In department ";
            logMessage = logMessage.concat(department.getId().toString());
            logMessage = logMessage.concat(": Searched employee by id: ");
            logMessage = logMessage.concat(id.toString());
            CompanyLogger.getInstance().logAction(logMessage);
        }

        for(Employee emp : department.getEmployees()){
            if(emp.getId().equals(id))
                return emp;
        }
        return null;
    }
}

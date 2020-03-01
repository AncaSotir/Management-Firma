package services.model_services.services_using_db;

import loggers.CompanyLogger;
import models.company.Company;
import models.company.Department;
import services.database_services.utils.DBUtils;

public class CompanyDepartmentService {
    private static CompanyDepartmentService ourInstance = new CompanyDepartmentService();

    public static CompanyDepartmentService getInstance() {
        return ourInstance;
    }

    private CompanyDepartmentService() {
    }


    public void addDepartment (Department department, boolean logFlag) {
        if(logFlag){
            DBUtils.getInstance().insertDepartment(Company.getInstance().getConnection(), department);
            String logMessage = "Added new department: ";
            logMessage = logMessage.concat(department.toString());
            CompanyLogger.getInstance().logAction(logMessage);
        }

        Company.getInstance().getDepartments().add(department);
    }

    public void removeDepartment (Department department, boolean logFlag) {
        if(logFlag){
            DBUtils.getInstance().deleteDepartment(Company.getInstance().getConnection(),department.getId());
            String logMessage = "Removed department: ";
            logMessage = logMessage.concat(department.toString());
            CompanyLogger.getInstance().logAction(logMessage);
        }

        Company.getInstance().getDepartments().remove(department);
    }

    public void listDepartments (boolean logFlag) {
        for(Department dep : Company.getInstance().getDepartments()) {
            System.out.println(dep);
        }

        if(logFlag){
            String logMessage = "Listed departments";
            CompanyLogger.getInstance().logAction(logMessage);
        }
    }

    public Department getDepartmentById (Integer id, boolean logFlag){
        if(logFlag){
            String logMessage = "Searched department by id: ";
            logMessage = logMessage.concat(id.toString());
            CompanyLogger.getInstance().logAction(logMessage);
        }

        for(Department dep : Company.getInstance().getDepartments()){
            if(dep.getId().equals(id))
                return dep;
        }
        return null;
    }

    public Department getDepartmentByManagerId (Integer id, boolean logFlag){
        if(logFlag){
            String logMessage = "Searched department by manager id: ";
            logMessage = logMessage.concat(id.toString());
            CompanyLogger.getInstance().logAction(logMessage);
        }

        for(Department dep : Company.getInstance().getDepartments()){
            if(dep.getManager() == null || dep.getManager().getId() == null)
                continue;
            if(dep.getManager().getId().equals(id))
                return dep;
        }
        return null;
    }

}

package services.read_write_services.utils;

import loggers.CompanyLogger;
import models.company.Company;
import models.company.Department;
import models.employee.Employee;
import models.project.Project;
import models.project.Task;

import java.util.Vector;

import static java.util.Calendar.*;
import static java.util.Calendar.YEAR;

public class ExportData {
    private static ExportData ourInstance = new ExportData();

    public static ExportData getInstance() {
        return ourInstance;
    }

    private ExportData() {
    }


    private void exportDepartmentData(){
        Vector<String[]> data = new Vector<>();
        //depId, depName, managerId

        for(Department currentDep : Company.getInstance().getDepartments()){
            String[] itemData = new String[3];
            itemData[0] = currentDep.getId().toString();
            itemData[1] = currentDep.getName();
            if(currentDep.getManager() != null && currentDep.getManager().getId() != null){
                itemData[2] = currentDep.getManager().getId().toString();
            }
            else
                itemData[2] = null;
            data.add(itemData);

        }

        WriteService.getInstance().writeDataFile("src/services/read_write_services/data_files/departmentData.csv", data);
    }

    private void exportEmployeeData(){
        Vector<String[]> data = new Vector<>();
        //id, name, date-of-birth, email, phone, hire-date, job-id, dep-id

        for(Department currentDep : Company.getInstance().getDepartments()){
            for(Employee currentEmp : currentDep.getEmployees()){
                String[] itemData = new String[8];
                itemData[0] = currentEmp.getId().toString();
                itemData[1] = currentEmp.getName();

                String[] dateOfBirth = new String[3];
                dateOfBirth[0] = Integer.toString(currentEmp.getDate_of_birth().get(DATE));
                dateOfBirth[1] = Integer.toString(currentEmp.getDate_of_birth().get(MONTH));
                dateOfBirth[2] = Integer.toString(currentEmp.getDate_of_birth().get(YEAR));

                itemData[2] = String.join("-", dateOfBirth);
                itemData[3] = currentEmp.getEmail();
                itemData[4] = currentEmp.getPhone_number();

                String[] hireDate = new String[3];
                hireDate[0] = Integer.toString(currentEmp.getHire_date().get(DATE));
                hireDate[1] = Integer.toString(currentEmp.getHire_date().get(MONTH));
                hireDate[2] = Integer.toString(currentEmp.getHire_date().get(YEAR));

                itemData[5] = String.join("-", hireDate);

                if(currentEmp.getJob() != null && currentEmp.getJob().getId() != null){
                    itemData[6] = currentEmp.getJob().getId().toString();
                }
                itemData[7] = currentEmp.getDepartment().getId().toString();

                data.add(itemData);
            }
        }

        WriteService.getInstance().writeDataFile("src/services/read_write_services/data_files/employeeData.csv", data);
    }

    private void exportProjectData(){
        Vector<String[]> data = new Vector<>();
        //id, name, personsRequired, depId

        for(Department currentDep : Company.getInstance().getDepartments()){
            for(Project currentProject : currentDep.getProjects()){
                String[] itemData = new String[4];
                itemData[0] = currentProject.getId().toString();
                itemData[1] = currentProject.getName();
                itemData[2] = currentProject.getPersonsRequired().toString();
                itemData[3] = currentDep.getId().toString();
                data.add(itemData);
            }
        }

        WriteService.getInstance().writeDataFile("src/services/read_write_services/data_files/projectData.csv", data);
    }

    private void exportTaskData() {
        Vector<String[]> data = new Vector<>();
        //taskId, taskDesc, projId

        for (Department currentDep : Company.getInstance().getDepartments()) {
            for (Project currentProject : currentDep.getProjects()) {
                for (Task currentTask : currentProject.getTasks()) {
                    String[] itemData = new String[3];
                    itemData[0] = currentTask.getId().toString();
                    itemData[1] = currentTask.getDescription();
                    itemData[2] = currentProject.getId().toString();
                    data.add(itemData);
                }
            }
        }

        WriteService.getInstance().writeDataFile("src/services/read_write_services/data_files/taskData.csv", data);
    }

    public void exportData(){
        String logMessage = "EXPORTING DATA...";
        CompanyLogger.getInstance().logAction(logMessage);
        exportDepartmentData();
        exportEmployeeData();
        exportProjectData();
        exportTaskData();
        logMessage = "EXPORTING DATA DONE.";
        CompanyLogger.getInstance().logAction(logMessage);
    }

}

package services.read_write_services.utils;

import loggers.CompanyLogger;
import models.company.Company;
import models.company.Department;
import models.employee.Employee;
import models.employee.Job;
import models.employee.Manager;
import models.project.Project;
import models.project.Task;
import services.database_services.utils.ConnectionUtils;
import services.database_services.utils.DBUtils;
import services.model_services.*;
import services.model_services.services_using_db.CompanyDepartmentService;
import services.model_services.services_using_db.CompanyProjectService;
import services.model_services.services_using_db.DepartmentEmployeeService;
import services.model_services.services_using_db.ProjectTaskService;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Vector;

public class ImportData {
    private static ImportData ourInstance = new ImportData();

    public static ImportData getInstance() {
        return ourInstance;
    }

    private ImportData() {
    }


    private void importDepartmentData(){
        Vector<String[]> departmentData = ReadService.getInstance().readDataFile("src/services/read_write_services/data_files/departmentData.csv");
        for(int i=1; i<departmentData.size(); ++i){
            Department newDepartment = new Department();

            newDepartment.setId(Integer.parseInt(departmentData.elementAt(i)[0]));
            newDepartment.setName(departmentData.elementAt(i)[1]);
            Manager temp = new Manager();
            if(!Objects.equals(departmentData.elementAt(i)[2], "null")){
                temp.setId(Integer.valueOf(departmentData.elementAt(i)[2]));
            }
            newDepartment.setManager(temp);

            CompanyDepartmentService.getInstance().addDepartment(newDepartment, false);
        }
    }

    private void importEmployeeData(){
        Vector<String[]> employeeData = ReadService.getInstance().readDataFile("src/services/read_write_services/data_files/employeeData.csv");
        for(int i=1; i<employeeData.size(); ++i){
            Department tempDep;
            Employee newEmployee;
            if((tempDep = CompanyDepartmentService.getInstance().getDepartmentByManagerId(Integer.valueOf(employeeData.elementAt(i)[0]),false)) != null){
                newEmployee = new Manager();
            }
            else{
                newEmployee = new Employee();
            }

            newEmployee.setId(Integer.valueOf(employeeData.elementAt(i)[0]));
            newEmployee.setName(employeeData.elementAt(i)[1]);

            String[] dateOfBirth = employeeData.elementAt(i)[2].split("-");
            newEmployee.setDate_of_birth(Integer.parseInt(dateOfBirth[2]),Integer.parseInt(dateOfBirth[1]),Integer.parseInt(dateOfBirth[0]));

            newEmployee.setEmail(employeeData.elementAt(i)[3]);
            newEmployee.setPhone_number(employeeData.elementAt(i)[4]);

            String[] hireDate = employeeData.elementAt(i)[5].split("-");
            newEmployee.setHire_date(Integer.parseInt(hireDate[2]),Integer.parseInt(hireDate[1]),Integer.parseInt(hireDate[0]));

            Job tempJob = new Job();
            if(!Objects.equals(employeeData.elementAt(i)[6], "null")){
                tempJob.setId(Integer.valueOf(employeeData.elementAt(i)[6]));
            }
            newEmployee.setJob(tempJob);

            Department dep = CompanyDepartmentService.getInstance().getDepartmentById(Integer.valueOf(employeeData.elementAt(i)[7]), false);

            newEmployee.setDepartment(dep,false);

            if(newEmployee instanceof Manager){
                tempDep.setManager((Manager)newEmployee);
            }
        }
    }

    private void importProjectData(){
        Vector<String[]> projectData = ReadService.getInstance().readDataFile("src/services/read_write_services/data_files/projectData.csv");
        for(int i=1; i<projectData.size(); ++i){
            Project newProject = new Project();

            newProject.setId(Integer.valueOf(projectData.elementAt(i)[0]));
            newProject.setName(projectData.elementAt(i)[1]);
            newProject.setPersonsRequired(Integer.valueOf(projectData.elementAt(i)[2]));

            DepartmentProjectService.getInstance().addProject(CompanyDepartmentService.getInstance().getDepartmentById(Integer.valueOf(projectData.elementAt(i)[3]), false), newProject, false);
        }
    }

    private void importTaskData(){
        Vector<String[]> taskData = ReadService.getInstance().readDataFile("src/services/read_write_services/data_files/taskData.csv");
        for(int i=1; i<taskData.size(); ++i){
            Task newTask = new Task();
            newTask.setId(Integer.valueOf(taskData.elementAt(i)[0]));
            newTask.setDescription(taskData.elementAt(i)[1]);
            for(Department currentDep : Company.getInstance().getDepartments()){
                Project project;
                if((project = DepartmentProjectService.getInstance().getProjectById(currentDep, Integer.valueOf(taskData.elementAt(i)[2]), false)) != null){
                    ProjectTaskService.getInstance().addTask(project, newTask, false);
                    break;
                }
            }
        }
    }

    public void importData(){
        //CompanyLogger.getInstance().wipeLogger();
        String logMessage = "IMPORTING DATA...";
        CompanyLogger.getInstance().logAction(logMessage);
        importDepartmentData();
        importEmployeeData();
        importProjectData();
        importTaskData();
        logMessage = "IMPORTING DATA DONE.";
        CompanyLogger.getInstance().logAction(logMessage);
    }


    // DATABASE IMPORT

    public void importDataDB(Connection connection) {
        String logMessage = "IMPORTING DATA...";
        CompanyLogger.getInstance().logAction(logMessage);
        Company.getInstance().setConnection(connection);

        Thread department_thr = new Thread(() -> importDepartmentDataDB(connection));
        Thread employee_thr = new Thread(() -> importEmployeeDataDB(connection));
        Thread project_thr = new Thread(() -> importProjectDataDB(connection));

        department_thr.start();

        project_thr.start();
        try{
            department_thr.join();
        }
        catch(InterruptedException exception) {
            exception.printStackTrace();
        }

        employee_thr.start();

        try{
            project_thr.join();
        }
        catch(InterruptedException exception) {
            exception.printStackTrace();
        }

        try{
            employee_thr.join();
        }
        catch(InterruptedException exception) {
            exception.printStackTrace();
        }


        logMessage = "IMPORTING DATA DONE.";
        CompanyLogger.getInstance().logAction(logMessage);
    }

    public void importDepartmentDataDB(Connection connection) {
        ArrayList<Department> departments = DBUtils.getInstance().selectAllDepartments(connection);

        for(Department department : departments){
            CompanyDepartmentService.getInstance().addDepartment(department, false);
        }
    }

    public void importEmployeeDataDB(Connection connection){
        ArrayList<Employee> employees = DBUtils.getInstance().selectAllEmployees(connection);

        for(Employee employee : employees){
            DepartmentEmployeeService.getInstance().addEmployee(CompanyDepartmentService.getInstance().getDepartmentById(employee.getDepartment().getId(),false),employee,false);
        }
    }

    public void importProjectDataDB(Connection connection){
        ArrayList<Project> projects = DBUtils.getInstance().selectAllProjects(connection);

        for(Project project : projects){
            CompanyProjectService.getInstance().addProject(project,false);
        }
    }

    public void importTaskDataDB(Connection connection){
        ArrayList<Task> tasks = DBUtils.getInstance().selectAllTasks(connection);

        for(Task task : tasks){
            ProjectTaskService.getInstance().addTask(DBUtils.getInstance().selectProject(connection,task.getProject().getId()),task,false);
        }
    }

    public void exportDataDB(Connection connection){
        ConnectionUtils.getInstance().closeDBConnection(connection);
    }
}

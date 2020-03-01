package services.model_services;

import loggers.CompanyLogger;
import models.company.Department;
import models.project.Project;

public class DepartmentProjectService {
    private static DepartmentProjectService ourInstance = new DepartmentProjectService();

    public static DepartmentProjectService getInstance() {
        return ourInstance;
    }

    private DepartmentProjectService() {
    }


    public void addProject (Department department, Project project, boolean logFlag) {
        department.getProjects().add(project);

        if(logFlag){
            String logMessage = "In department ";
            logMessage = logMessage.concat(department.getId().toString());
            logMessage = logMessage.concat(": Added new project: ");
            logMessage = logMessage.concat(project.toString());
            CompanyLogger.getInstance().logAction(logMessage);
        }
    }

    public void removeProject (Department department, Project project, boolean logFlag) {
        department.getProjects().remove(project);

        if(logFlag){
            String logMessage = "In department ";
            logMessage = logMessage.concat(department.getId().toString());
            logMessage = logMessage.concat(": Removed project: ");
            logMessage = logMessage.concat(project.toString());
            CompanyLogger.getInstance().logAction(logMessage);
        }
    }

    public void listProjects (Department department, boolean logFlag) {
        for(int i=0; i<department.getProjects().size(); ++i) {
            System.out.println(department.getProjects().elementAt(i));
        }

        if(logFlag){
            String logMessage = "In department ";
            logMessage = logMessage.concat(department.getId().toString());
            logMessage = logMessage.concat(": Listed projects");
            CompanyLogger.getInstance().logAction(logMessage);
        }
    }

    public Project getProjectById (Department department, Integer id, boolean logFlag){
        if(logFlag){
            String logMessage = "In department ";
            logMessage = logMessage.concat(department.getId().toString());
            logMessage = logMessage.concat(": Searched project by id: ");
            logMessage = logMessage.concat(id.toString());
            CompanyLogger.getInstance().logAction(logMessage);
        }

        for(int i=0; i<department.getProjects().size(); ++i) {
            if(department.getProjects().elementAt(i).getId().equals(id))
                return department.getProjects().elementAt(i);
        }
        return null;
    }
}

package services.model_services;

import models.employee.Manager;
import models.project.Project;

public class ManagerProjectService {
    private static ManagerProjectService ourInstance = new ManagerProjectService();

    public static ManagerProjectService getInstance() {
        return ourInstance;
    }

    private ManagerProjectService() {
    }


    public void startProject(Manager manager, Project project, boolean logFlag) {
        DepartmentProjectService.getInstance().addProject(manager.getDepartment(), project, logFlag);
    }

    public void endProject(Manager manager, Project project, boolean logFlag) {
        DepartmentProjectService.getInstance().removeProject(manager.getDepartment(), project, logFlag);
    }

}

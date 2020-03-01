package services.model_services;

import loggers.CompanyLogger;
import models.employee.Employee;
import models.project.Project;

public class ProjectTeamService {
    private static ProjectTeamService ourInstance = new ProjectTeamService();

    public static ProjectTeamService getInstance() {
        return ourInstance;
    }

    private ProjectTeamService() {
    }


    public void listTeam (Project project, boolean logFlag) {
        for(int i=0; i<project.getTeam().size(); ++i) {
            System.out.println(project.getTeam().elementAt(i));
        }

        if(logFlag){
            String logMessage = "In project ";
            logMessage = logMessage.concat(project.getId().toString());
            logMessage = logMessage.concat(": Listed team members");
            CompanyLogger.getInstance().logAction(logMessage);
        }
    }

    public void addPerson (Project project, Employee employee, boolean logFlag) {
        if(project.getTeam().size()<=project.getPersonsRequired())
            project.getTeam().add(employee);

        if(logFlag){
            String logMessage = "In project ";
            logMessage = logMessage.concat(project.getId().toString());
            logMessage = logMessage.concat(": Added new team member: ");
            logMessage = logMessage.concat(employee.toString());
            CompanyLogger.getInstance().logAction(logMessage);
        }
    }

    public void removePerson (Project project, Employee employee, boolean logFlag) {
        project.getTeam().remove(employee);

        if(logFlag){
            String logMessage = "In project ";
            logMessage = logMessage.concat(project.getId().toString());
            logMessage = logMessage.concat(": Removed team member: ");
            logMessage = logMessage.concat(employee.toString());
            CompanyLogger.getInstance().logAction(logMessage);
        }
    }

}

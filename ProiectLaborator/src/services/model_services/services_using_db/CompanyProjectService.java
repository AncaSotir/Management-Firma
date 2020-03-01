package services.model_services.services_using_db;

import loggers.CompanyLogger;
import models.company.Company;
import models.project.Project;
import services.database_services.utils.DBUtils;

public class CompanyProjectService {
    private static CompanyProjectService ourInstance = new CompanyProjectService();

    public static CompanyProjectService getInstance() {
        return ourInstance;
    }

    private CompanyProjectService() {
    }


    public void addProject (Project project, boolean logFlag) {
        if(logFlag){
            DBUtils.getInstance().insertProject(Company.getInstance().getConnection(), project);
            String logMessage = "";
            logMessage = logMessage.concat("Added new project: ");
            logMessage = logMessage.concat(project.toString());
            CompanyLogger.getInstance().logAction(logMessage);
        }

        Company.getInstance().getProjects().add(project);
    }

    public void removeProject (Project project, boolean logFlag) {
        if(logFlag){
            DBUtils.getInstance().deleteProject(Company.getInstance().getConnection(), project.getId());
            String logMessage = "";
            logMessage = logMessage.concat("Removed project: ");
            logMessage = logMessage.concat(project.toString());
            CompanyLogger.getInstance().logAction(logMessage);
        }

        Company.getInstance().getProjects().remove(project);
    }

    public void listProjects (boolean logFlag) {
        for(int i=0; i<Company.getInstance().getProjects().size(); ++i) {
            System.out.println(Company.getInstance().getProjects().elementAt(i));
        }

        if(logFlag){
            String logMessage = "";
            logMessage = logMessage.concat("Listed projects");
            CompanyLogger.getInstance().logAction(logMessage);
        }
    }

    public Project getProjectById (Integer id, boolean logFlag){
        if(logFlag){
            String logMessage = "";
            logMessage = logMessage.concat("Searched project by id: ");
            logMessage = logMessage.concat(id.toString());
            CompanyLogger.getInstance().logAction(logMessage);
        }

        for(int i=0; i<Company.getInstance().getProjects().size(); ++i) {
            if(Company.getInstance().getProjects().elementAt(i).getId().equals(id))
                return Company.getInstance().getProjects().elementAt(i);
        }
        return null;
    }
}

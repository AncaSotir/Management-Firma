package services.model_services.services_using_db;

import loggers.CompanyLogger;
import models.company.Company;
import models.project.Project;
import models.project.Task;
import services.database_services.utils.DBUtils;

public class ProjectTaskService {
    private static ProjectTaskService ourInstance = new ProjectTaskService();

    public static ProjectTaskService getInstance() {
        return ourInstance;
    }

    private ProjectTaskService() {
    }

    public void listTasks (Project project, boolean logFlag) {
        for(int i=0; i<project.getTasks().size(); ++i) {
            System.out.println(project.getTasks().elementAt(i));
        }

        if(logFlag){
            String logMessage = "In project ";
            logMessage = logMessage.concat(project.getId().toString());
            logMessage = logMessage.concat(": Listed tasks");
            CompanyLogger.getInstance().logAction(logMessage);
        }
    }

    public void addTask (Project project, Task task, boolean logFlag) {
        project.getTasks().add(task);

        if(logFlag){
            DBUtils.getInstance().insertTask(Company.getInstance().getConnection(), task);
            String logMessage = "In project ";
            logMessage = logMessage.concat(project.getId().toString());
            logMessage = logMessage.concat(": Added new task: ");
            logMessage = logMessage.concat(task.toString());
            CompanyLogger.getInstance().logAction(logMessage);
        }
    }

    public void removeTask(Project project, Task task, boolean logFlag) {
        project.getTasks().remove(task);

        if(logFlag){
            DBUtils.getInstance().deleteTask(Company.getInstance().getConnection(), task.getId());
            String logMessage = "In project ";
            logMessage = logMessage.concat(project.getId().toString());
            logMessage = logMessage.concat(": Removed task: ");
            logMessage = logMessage.concat(task.toString());
            CompanyLogger.getInstance().logAction(logMessage);
        }
    }

    public Task getTaskById(Project project, Integer id, boolean logFlag){
        if(logFlag){
            String logMessage = "In project ";
            logMessage = logMessage.concat(project.getId().toString());
            logMessage = logMessage.concat(": Searched task by id: ");
            logMessage = logMessage.concat(id.toString());
            CompanyLogger.getInstance().logAction(logMessage);
        }

        for(Task task : project.getTasks()){
            if(task.getId().equals(id)){
                return task;
            }
        }
        return null;
    }

}

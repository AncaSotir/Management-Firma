package models.project;

import models.company.Company;
import models.company.Department;

public class Task {
    private Integer id;
    private String description;
    private Project project;

    {
        Integer newTaskId = 0;
        for(Department dep : Company.getInstance().getDepartments()){
            for(Project project : dep.getProjects()){
                for(Task task : project.getTasks()){
                    if(newTaskId < task.getId()){
                        newTaskId = task.getId();
                    }
                }
            }
        }
        this.id = newTaskId + 1;
    }

    public Task() {
    }

    public Task(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }

}

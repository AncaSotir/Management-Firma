package models.project;

import models.company.Company;
import models.company.Department;
import models.employee.Employee;

import java.util.Vector;

public class Project {
    private Integer id;
    private String name;
    private Vector <Task> tasks;
    private Integer personsRequired;
    private Vector <Employee> team;

    {
        tasks = new Vector <Task> ();
        team = new Vector <Employee> ();

        Integer newProjectId = 0;
        for(Department dep : Company.getInstance().getDepartments()){
            for(Project project : dep.getProjects()){
                if(newProjectId < project.getId()){
                    newProjectId = project.getId();
                }
            }
        }
        this.id = newProjectId + 1;
    }

    public Project() {
    }

    public Project(String name, Integer personsRequired) {
        this.name = name;
        this.personsRequired = personsRequired;
    }

    public Project(Project project) {
        this.id = project.getId();
        this.name = project.getName();
        this.personsRequired = project.getPersonsRequired();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPersonsRequired() {
        return personsRequired;
    }

    public void setPersonsRequired(Integer personsRequired) {
        this.personsRequired = personsRequired;
    }

    public Vector<Task> getTasks() {
        return tasks;
    }

    public Vector<Employee> getTeam() {
        return team;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", personsRequired=" + personsRequired +
                '}';
    }

}

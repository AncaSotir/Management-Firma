package models.company;

import models.employee.Employee;
import models.employee.Manager;
import models.project.Project;

import java.util.TreeSet;
import java.util.Vector;

public class Department implements Comparable<Department> {
    private Integer id;
    private String name;
    private Manager manager;
    private TreeSet<Employee> employees;
    private Vector <Project> projects;

    {
        employees = new TreeSet <> ();
        projects = new Vector <> ();

//        Integer newDepId = 0;
//        for(Department dep : Company.getInstance().getDepartments()){
//            if(newDepId < dep.getId()){
//                newDepId = dep.getId();
//            }
//        }
//        this.id = newDepId + 1;
    }

    public Department() {
    }

    public Department(String name) {
        this.name = name;
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

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public TreeSet<Employee> getEmployees() {
        return employees;
    }

    public Vector<Project> getProjects() {
        return projects;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int compareTo(Department o) {
        return this.getId().compareTo(o.getId());
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Department){
            return this.getId().equals(((Department)o).getId());
        }
        return false;
    }
}

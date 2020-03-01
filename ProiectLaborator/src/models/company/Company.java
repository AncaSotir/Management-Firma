package models.company;

import models.employee.Manager;
import models.employee.Person;
import models.project.Project;

import java.sql.Connection;
import java.util.TreeSet;
import java.util.Vector;

public class Company {

    private static Company instance = new Company();

    private String name;
    private Vector<Department> departments;
    private Vector<Project> projects;
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Vector<Project> getProjects() {
        return projects;
    }

    public void setProjects(Vector<Project> projects) {
        this.projects = projects;
    }

    private Vector<Person> applicants;
    private Manager manager;
    private Location location;

    {
        departments = new Vector<> ();
        applicants = new Vector<>();
        projects = new Vector<>();
    }

    private Company(){
    }

    public static Company getInstance() {
        return instance;
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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Vector<Department> getDepartments() {
        return departments;
    }

    public Vector<Person> getApplicants() {
        return applicants;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", manager=" + manager +
                ", location=" + location +
                '}';
    }
}

package services.database_services.utils;

import models.company.Department;
import models.employee.Employee;
import models.employee.Job;
import models.employee.Manager;
import models.project.Project;
import models.project.Task;
import services.model_services.services_using_db.CompanyDepartmentService;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

import static java.util.Calendar.*;

public class DBUtils {
    private static DBUtils ourInstance = new DBUtils();

    public static DBUtils getInstance() {
        return ourInstance;
    }

    private DBUtils() {
    }


    public void insertEmployee(Connection connection, Employee employee) {
        String sql = "INSERT INTO employees(name, date_of_birth, email, phone_number, hire_date, department_id) VALUES (?, ?, ?, ?, ?, ?);";

        try {
            PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, employee.getName());
            pstmt.setDate(2, new Date(employee.getDate_of_birth().getTimeInMillis()));
            pstmt.setString(3, employee.getEmail());
            pstmt.setString(4, employee.getPhone_number());
            pstmt.setDate(5, new Date(employee.getHire_date().getTimeInMillis()));
            pstmt.setInt(6, employee.getDepartment().getId());
            //pstmt.setInt(7, employee.getJob().getId());

            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            rs.next();
            employee.setId(rs.getInt(1));

            System.out.println("Inserted: " + employee);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertEmployeeTable(Connection connection, Employee employee) {
        String sql = "INSERT INTO employees(name, date_of_birth, email, phone_number, hire_date) VALUES (?, ?, ?, ?, ?);";

        try {
            PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, employee.getName());
            pstmt.setDate(2, new Date(employee.getDate_of_birth().getTimeInMillis()));
            pstmt.setString(3, employee.getEmail());
            pstmt.setString(4, employee.getPhone_number());
            pstmt.setDate(5, new Date(employee.getHire_date().getTimeInMillis()));
            //pstmt.setInt(6, employee.getDepartment().getId());
            //pstmt.setInt(7, employee.getJob().getId());

            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            rs.next();
            employee.setId(rs.getInt(1));

            System.out.println("Inserted: " + employee);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateEmployee(Connection connection, int index, String attribute, String newValue) {
        String sql;
        switch(attribute){
            case "NAME":
                sql = "UPDATE EMPLOYEES SET NAME = ? WHERE employee_id = ?";
                try {
                    PreparedStatement pstmt = connection.prepareStatement(sql);
                    pstmt.setInt(2, index);
                    pstmt.setString(1, newValue);
                    pstmt.executeUpdate();
                    System.out.println("Updated: " + "id = " + index + ", attribute = " + attribute + ", new value = " + newValue);

                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "EMAIL":
                sql = "UPDATE EMPLOYEES SET EMAIL = ? WHERE employee_id = ?";
                try {
                    PreparedStatement pstmt = connection.prepareStatement(sql);
                    pstmt.setInt(2, index);
                    pstmt.setString(1, newValue);
                    pstmt.executeUpdate();
                    System.out.println("Updated: " + "id = " + index + ", attribute = " + attribute + ", new value = " + newValue);

                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "PHONE_NUMBER":
                sql = "UPDATE EMPLOYEES SET PHONE_NUMBER = ? WHERE employee_id = ?";
                try {
                    PreparedStatement pstmt = connection.prepareStatement(sql);
                    pstmt.setInt(2, index);
                    pstmt.setString(1, newValue);
                    pstmt.executeUpdate();
                    System.out.println("Updated: " + "id = " + index + ", attribute = " + attribute + ", new value = " + newValue);

                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "DATE_OF_BIRTH":
                sql = "UPDATE EMPLOYEES SET DATE_OF_BIRTH = ? WHERE employee_id = ?";
                try {
                    PreparedStatement pstmt = connection.prepareStatement(sql);
                    pstmt.setInt(2, index);
                    String[] splitDate = newValue.split("-");
                    Calendar date = Calendar.getInstance();
                    date.set(Integer.parseInt(splitDate[2]), Integer.parseInt(splitDate[1]), Integer.parseInt(splitDate[0]));
                    Date dateOfBirth = new Date(date.getTimeInMillis());
                    pstmt.setDate(1, dateOfBirth);
                    pstmt.executeUpdate();
                    System.out.println("Updated: " + "id = " + index + ", attribute = " + attribute + ", new value = " + newValue);

                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }

        }

    }
    public Employee selectEmployee(Connection connection, int index) {
        String sql = "SELECT * FROM employees WHERE employee_id = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, index);
            ResultSet rs = pstmt.executeQuery();
            Employee employee = new Employee();
            while (rs.next()) {
                employee.setId(rs.getInt("id"));
                employee.setName(rs.getString("name"));

                Calendar birthDate = Calendar.getInstance();
                birthDate.setTime(rs.getDate("date_of_birth"));
                employee.setDate_of_birth(birthDate.get(YEAR), birthDate.get(MONTH)+1, birthDate.get(DATE));

                employee.setEmail(rs.getString("email"));
                employee.setPhone_number(rs.getString("phone_number"));

                Calendar hireDate = Calendar.getInstance();
                hireDate.setTime(rs.getDate("hire_date"));
                employee.setHire_date(hireDate.get(YEAR), hireDate.get(MONTH)+1, hireDate.get(DATE));

                System.out.println("Selected: " + employee);
                return employee;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
    public void deleteEmployee(Connection connection, int index) {
        String sql = "DELETE FROM employees WHERE employee_id = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, index);
            pstmt.executeUpdate();
            System.out.println("Deleted: id = " + index);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public ArrayList<Employee> selectAllEmployees(Connection connection){
        String sql = "SELECT * FROM employees";
        ArrayList<Employee> employees = new ArrayList<>();
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Employee employee = new Employee();

                employee.setId(rs.getInt("employee_id"));
                employee.setName(rs.getString("name"));

                Calendar birthDate = Calendar.getInstance();
                birthDate.setTime(rs.getDate("date_of_birth"));
                employee.setDate_of_birth(birthDate.get(YEAR), birthDate.get(MONTH)+1, birthDate.get(DATE));

                employee.setEmail(rs.getString("email"));
                employee.setPhone_number(rs.getString("phone_number"));

                Calendar hireDate = Calendar.getInstance();
                hireDate.setTime(rs.getDate("hire_date"));
                employee.setHire_date(hireDate.get(YEAR), hireDate.get(MONTH)+1, hireDate.get(DATE));

                employee.setDepartment(DBUtils.getInstance().selectDepartment(connection,rs.getInt("department_id")),false);

                System.out.println("Selected: " + employee);
                employees.add(employee);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return employees;
    }


    public void insertDepartment(Connection connection, Department department) {
        String sql = "INSERT INTO departments(name) VALUES (?);";

        try {
            PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, department.getName());

            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            rs.next();
            department.setId(rs.getInt(1));

            System.out.println("Inserted: " + department);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void updateDepartment(Connection connection, int index, String newValue) {
        String sql = "UPDATE departments SET NAME = ? WHERE department_id = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(2, index);
            pstmt.setString(1, newValue);
            pstmt.executeUpdate();
            System.out.println("Updated: " + "id = " + index + ", new name value = " + newValue);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public Department selectDepartment(Connection connection, int index) {
        String sql = "SELECT * FROM departments WHERE department_id = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, index);
            ResultSet rs = pstmt.executeQuery();
            Department department = new Department();
            while (rs.next()) {
                department.setId(rs.getInt("department_id"));
                department.setName(rs.getString("name"));
                //department.setManager(new Manager(DBUtils.getInstance().selectEmployee(connection,rs.getInt("manager_id"))));

                System.out.println("Selected: " + department);
                return department;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    public void deleteDepartment(Connection connection, int index) {
        String sql = "DELETE FROM departments WHERE department_id = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, index);
            pstmt.executeUpdate();
            System.out.println("Deleted: id = " + index);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public ArrayList<Department> selectAllDepartments(Connection connection){
        String sql = "SELECT * FROM departments";
        ArrayList<Department> departments = new ArrayList<>();
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Department department = new Department();

                department.setId(rs.getInt("department_id"));
                department.setName(rs.getString("name"));
                //department.setManager(new Manager(DBUtils.getInstance().selectEmployee(connection,rs.getInt("manager_id"))));

                System.out.println("Selected: " + department);
                departments.add(department);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return departments;
    }


    public void insertJob(Connection connection, Job job) {
        String sql = "INSERT INTO jobs(title, min_salary, max_salary) VALUES (?,?,?);";

        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, job.getTitle());
            pstmt.setInt(2, job.getMin_salary());
            pstmt.setInt(3, job.getMax_salary());

            pstmt.executeUpdate();
            System.out.println("Inserted: " + job);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void updateJob(Connection connection, int index, String attribute, String newValue) {
        String sql;
        switch(attribute){
            case "TITLE":
                sql = "UPDATE JOBS SET TITLE = ? WHERE id = ?";
                try {
                    PreparedStatement pstmt = connection.prepareStatement(sql);
                    pstmt.setInt(2, index);
                    pstmt.setString(1, newValue);
                    pstmt.executeUpdate();
                    System.out.println("Updated: " + "id = " + index + ", attribute = " + attribute + ", new value = " + newValue);

                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "MIN_SALARY":
                sql = "UPDATE JOBS SET MIN_SALARY = ? WHERE id = ?";
                try {
                    PreparedStatement pstmt = connection.prepareStatement(sql);
                    pstmt.setInt(2, index);
                    pstmt.setInt(1, Integer.parseInt(newValue));
                    pstmt.executeUpdate();
                    System.out.println("Updated: " + "id = " + index + ", attribute = " + attribute + ", new value = " + newValue);

                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "MAX_SALARY":
                sql = "UPDATE JOBS SET MAX_SALARY = ? WHERE id = ?";
                try {
                    PreparedStatement pstmt = connection.prepareStatement(sql);
                    pstmt.setInt(2, index);
                    pstmt.setInt(1, Integer.parseInt(newValue));
                    pstmt.executeUpdate();
                    System.out.println("Updated: " + "id = " + index + ", attribute = " + attribute + ", new value = " + newValue);

                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
        }
    }
    public Job selectJob(Connection connection, int index) {
        String sql = "SELECT * FROM JOBS WHERE id = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, index);
            ResultSet rs = pstmt.executeQuery();
            Job job = new Job();
            while (rs.next()) {
                job.setId(rs.getInt("job_id"));
                job.setTitle(rs.getString("title"));
                job.setMin_salary(rs.getInt("min_salary"));
                job.setMax_salary(rs.getInt("max_salary"));
                System.out.println("Selected: " + job);
                return job;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    public void deleteJob(Connection connection, int index) {
        String sql = "DELETE FROM jobs WHERE id = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, index);
            pstmt.executeUpdate();
            System.out.println("Deleted: id = " + index);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void insertProject(Connection connection, Project project) {
        String sql = "INSERT INTO projects(name, persons_required) VALUES (?,?);";

        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, project.getName());
            pstmt.setInt(2, project.getPersonsRequired());

            pstmt.executeUpdate();
            System.out.println("Inserted: " + project);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void updateProject(Connection connection, int index, String attribute, String newValue) {
        String sql;
        switch(attribute){
            case "NAME":
                sql = "UPDATE PROJECTS SET NAME = ? WHERE project_id = ?";
                try {
                    PreparedStatement pstmt = connection.prepareStatement(sql);
                    pstmt.setInt(2, index);
                    pstmt.setString(1, newValue);
                    pstmt.executeUpdate();
                    System.out.println("Updated: " + "id = " + index + ", attribute = " + attribute + ", new value = " + newValue);

                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "PERSONS_REQUIRED":
                sql = "UPDATE PROJECTS SET PERSONS_REQUIRED = ? WHERE project_id = ?";
                try {
                    PreparedStatement pstmt = connection.prepareStatement(sql);
                    pstmt.setInt(2, index);
                    pstmt.setInt(1, Integer.parseInt(newValue));
                    pstmt.executeUpdate();
                    System.out.println("Updated: " + "id = " + index + ", attribute = " + attribute + ", new value = " + newValue);

                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
        }
    }
    public Project selectProject(Connection connection, int index) {
        String sql = "SELECT * FROM projects WHERE project_id = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, index);
            ResultSet rs = pstmt.executeQuery();
            Project project = new Project();
            while (rs.next()) {
                project.setId(rs.getInt("project_id"));
                project.setName(rs.getString("name"));
                project.setPersonsRequired(rs.getInt("persons_required"));
                System.out.println("Selected: " + project);
                return project;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    public void deleteProject(Connection connection, int index) {
        String sql = "DELETE FROM projects WHERE project_id = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, index);
            pstmt.executeUpdate();
            System.out.println("Deleted: id = " + index);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public ArrayList<Project> selectAllProjects(Connection connection){
        String sql = "SELECT * FROM projects";
        ArrayList<Project> projects = new ArrayList<>();
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Project project = new Project();
                project.setId(rs.getInt("project_id"));
                project.setName(rs.getString("name"));
                project.setPersonsRequired(rs.getInt("persons_required"));
                System.out.println("Selected: " + project);
                projects.add(project);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return projects;
    }


    public void insertTask(Connection connection, Task task) {
        String sql = "INSERT INTO tasks(description, project_id) VALUES (?,?);";

        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, task.getDescription());
            pstmt.setInt(2, task.getProject().getId());

            pstmt.executeUpdate();
            System.out.println("Inserted: " + task);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void updateTask(Connection connection, int index, String newValue) {
        String sql = "UPDATE TASKS SET DESCRIPTION = ? WHERE id = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(2, index);
            pstmt.setString(1, newValue);
            pstmt.executeUpdate();
            System.out.println("Updated: " + "id = " + index + ", new description value = " + newValue);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public Task selectTask(Connection connection, int index) {
        String sql = "SELECT * FROM tasks WHERE id = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, index);
            ResultSet rs = pstmt.executeQuery();
            Task task = new Task();
            while (rs.next()) {
                task.setId(rs.getInt("task_id"));
                task.setDescription(rs.getString("description"));
                task.setProject(new Project(DBUtils.getInstance().selectProject(connection, rs.getInt("project_id"))));
                System.out.println("Selected: " + task);
                return task;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    public void deleteTask(Connection connection, int index) {
        String sql = "DELETE FROM tasks WHERE id = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, index);
            pstmt.executeUpdate();
            System.out.println("Deleted: id = " + index);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public ArrayList<Task> selectAllTasks(Connection connection) {
        String sql = "SELECT * FROM tasks";
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Task task = new Task();
                task.setId(rs.getInt("task_id"));
                task.setDescription(rs.getString("description"));
                task.setProject(new Project(DBUtils.getInstance().selectProject(connection, rs.getInt("project_id"))));
                System.out.println("Selected: " + task);
                tasks.add(task);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return tasks;
    }


}

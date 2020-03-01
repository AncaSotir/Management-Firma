package demos.db_demos;

import models.company.Company;
import models.company.Department;
import models.employee.Employee;
import models.employee.Job;
import models.project.Project;
import services.database_services.utils.ConnectionUtils;
import services.database_services.utils.DBUtils;
import services.read_write_services.utils.ImportData;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;

public class DataBaseInsertDemo {
    public static void main(String[] args){
        Connection conn = ConnectionUtils.getInstance().getDBConnection();
        System.out.println(conn);

//
//        Department department1 = new Department("First Department");
//        Department department2 = new Department("Second Department");
//        Department department3 = new Department("Third Department");
////        DBUtils.getInstance().insertDepartment(conn, department1);
////        DBUtils.getInstance().insertDepartment(conn, department2);
////        DBUtils.getInstance().insertDepartment(conn, department3);
//

        ImportData.getInstance().importDepartmentDataDB(conn);

        ArrayList<Department> departments = new ArrayList<>();
        Iterator<Department> it = Company.getInstance().getDepartments().iterator();
        while(it.hasNext()){
            departments.add(it.next());
        }

        Employee employee1 = new Employee("First Employee",1980,1,1,"first@myco.com","1234567890", departments.get(0));
        Employee employee2 = new Employee("Second Employee",1980,1,1,"second@myco.com","1234567890", departments.get(0));
        Employee employee3 = new Employee("Third Employee",1980,1,1,"third@myco.com","1234567890", departments.get(1));
        Employee employee4 = new Employee("Fourth Employee",1980,1,1,"fourth@myco.com","1234567890", departments.get(1));
        Employee employee5 = new Employee("Fifth Employee",1980,1,1,"fifth@myco.com","1234567890", departments.get(2));
        Employee employee6 = new Employee("Sixth Employee",1980,1,1,"sixth@myco.com","1234567890", departments.get(2));
        DBUtils.getInstance().insertEmployee(conn, employee1);
        DBUtils.getInstance().insertEmployee(conn, employee2);
        DBUtils.getInstance().insertEmployee(conn, employee3);
        DBUtils.getInstance().insertEmployee(conn, employee4);
        DBUtils.getInstance().insertEmployee(conn, employee5);
        DBUtils.getInstance().insertEmployee(conn, employee6);


        Project project1 = new Project("Project1", 2);
        Project project2 = new Project("Project2", 2);
        Project project3 = new Project("Project3", 2);
        DBUtils.getInstance().insertProject(conn, project1);
        DBUtils.getInstance().insertProject(conn, project2);
        DBUtils.getInstance().insertProject(conn, project3);


        ConnectionUtils.getInstance().closeDBConnection(conn);
    }
}

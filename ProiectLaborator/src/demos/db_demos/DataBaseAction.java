package demos.db_demos;

import models.company.Company;
import models.company.Department;
import models.employee.Employee;
import models.project.Project;
import models.project.Task;
import services.database_services.utils.ConnectionUtils;
import services.model_services.DepartmentProjectService;
import services.model_services.services_using_db.CompanyDepartmentService;
import services.model_services.services_using_db.CompanyProjectService;
import services.model_services.services_using_db.DepartmentEmployeeService;
import services.model_services.services_using_db.ProjectTaskService;
import services.read_write_services.utils.ImportData;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Scanner;

import static java.util.Calendar.*;

public class DataBaseAction {
    public static void main(String[] args){

        ImportData.getInstance().importDataDB(ConnectionUtils.getInstance().getDBConnection());

        System.out.println();
        for(Department department : Company.getInstance().getDepartments()){
            System.out.println(department.getEmployees());
        }
        System.out.println();

        boolean continueFlag = true;

        while(continueFlag){
            System.out.println();
            System.out.println("Alegeti numarul unei actiuni:");
            System.out.println(" 1. Listati departamentele companiei.");
            System.out.println(" 2. Adaugati un nou departament.");
            System.out.println(" 3. Stergeti un departament existent.");
            System.out.println(" 4. Listati angajatii unui departament.");
            System.out.println(" 5. Adaugati un nou angajat intr-un departament existent.");
            System.out.println(" 6. Stergeti un angajat existent dintr-un departament.");
            System.out.println(" 7. Listati proiectele companiei.");
            System.out.println(" 8. Adaugati un nou proiect.");
            System.out.println(" 9. Stergeti un proiect existent.");
            System.out.println("10. Opriti demo-ul.");


            Scanner scanner = new Scanner(System.in);
            Integer selectedAction = scanner.nextInt();


            switch (selectedAction){
                case  1: {
                    CompanyDepartmentService.getInstance().listDepartments(true);
                    break;
                } //Listati departamentele companiei.
                case  2: {
                    Department newDep = new Department();
                    System.out.println();
                    System.out.print("Nume departament: ");
                    newDep.setName(scanner.next());
                    CompanyDepartmentService.getInstance().addDepartment(newDep, true);
                    break;
                } //Adaugati un nou departament.
                case  3: {
                    System.out.println();
                    System.out.println("Departamentele existente:");
                    CompanyDepartmentService.getInstance().listDepartments(true);
                    System.out.println("\nIntroduceti id-ul departamentului pe care doriti sa il stergeti.");
                    CompanyDepartmentService.getInstance().removeDepartment(CompanyDepartmentService.getInstance().getDepartmentById(scanner.nextInt(), true),true);
                    break;
                } //Stergeti un departament existent.
                case  4: {
                    System.out.println();
                    System.out.println("Departamentele existente:");
                    CompanyDepartmentService.getInstance().listDepartments(true);
                    System.out.println("\nIntroduceti id-ul departamentului pentru care doriti sa listati angajatii.");
                    DepartmentEmployeeService.getInstance().listEmployees(CompanyDepartmentService.getInstance().getDepartmentById(scanner.nextInt(), true), true);
                    break;
                } //Listati angajatii unui departament.
                case  5: {
                    System.out.println();
                    System.out.println("Departamentele existente:");
                    CompanyDepartmentService.getInstance().listDepartments(true);
                    System.out.println("\nIntroduceti id-ul departamentului in care doriti sa adaugati un nou angajat.");
                    Department depSelected = CompanyDepartmentService.getInstance().getDepartmentById(scanner.nextInt(), true);
                    Employee newEmp = new Employee();
                    System.out.println("Nume angajat:");
                    newEmp.setName(scanner.next());
                    System.out.println("Data nasterii (ZZ-LL-AAAA):");
                    String[] dateOfBirth = scanner.next().split("-");
                    newEmp.setDate_of_birth(Integer.parseInt(dateOfBirth[2]),Integer.parseInt(dateOfBirth[1]),Integer.parseInt(dateOfBirth[0]));
                    System.out.println("Email:");
                    newEmp.setEmail(scanner.next());
                    System.out.println("Numar de telefon:");
                    newEmp.setPhone_number(scanner.next());
                    newEmp.setHire_date(getInstance().get(YEAR), getInstance().get(MONTH), getInstance().get(DATE));
                    newEmp.setDepartment(depSelected, true);
                    DepartmentEmployeeService.getInstance().addEmployee(depSelected,newEmp, true);
                    break;
                } //Adaugati un nou angajat intr-un departament existent.
                case  6: {
                    System.out.println();
                    System.out.println("Departamentele existente:");
                    CompanyDepartmentService.getInstance().listDepartments(true);
                    System.out.println("\nIntroduceti id-ul departamentului din care doriti sa stergeti un angajat.");
                    Department depSelected = CompanyDepartmentService.getInstance().getDepartmentById(scanner.nextInt(), true);
                    System.out.println("Angajatii existenti:");
                    DepartmentEmployeeService.getInstance().listEmployees(depSelected, true);
                    System.out.println("\nIntroduceti id-ul angajatului pe care doriti sa il stergeti.");
                    DepartmentEmployeeService.getInstance().removeEmployee(depSelected, DepartmentEmployeeService.getInstance().getEmployeeById(depSelected, scanner.nextInt(), true), true);
                    break;
                } //Stergeti un angajat existent dintr-un departament.
                case 10:
                    continueFlag = false;
            }
        }

        ImportData.getInstance().exportDataDB(Company.getInstance().getConnection());
    }
}

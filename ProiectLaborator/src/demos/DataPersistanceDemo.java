package demos;

import models.company.Department;
import services.model_services.services_using_db.CompanyDepartmentService;
import services.read_write_services.utils.ExportData;
import services.read_write_services.utils.ImportData;

import java.util.Scanner;

public class DataPersistanceDemo {
    public static void main(String[] args){

        //CompanyLogger.getInstance().wipeLogger();
        ImportData.getInstance().importData();

        System.out.println();
        System.out.println("Departamentele deja existente in baza de date:");
        CompanyDepartmentService.getInstance().listDepartments(true);
        System.out.println();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduceti datele pentru un nou departament:");

        System.out.print("Department name: ");
        String newDepName = scanner.next();
        System.out.println();
        System.out.println("Adaugam noul departament in baza de date...");

        Department newDep = new Department();
        newDep.setName(newDepName);

        CompanyDepartmentService.getInstance().addDepartment(newDep, true);

        System.out.println();
        System.out.println("Departamentele companiei:");
        CompanyDepartmentService.getInstance().listDepartments(true);

        System.out.println();
        System.out.println("Introduceti id-ul unui departament pe care sa il stergem din baza de date:");
        System.out.print("Department id: ");
        Integer depId = scanner.nextInt();

        System.out.println();
        System.out.println("Stergem departamentul ales din baza de date...");
        CompanyDepartmentService.getInstance().removeDepartment(CompanyDepartmentService.getInstance().getDepartmentById(depId, true), true);

        System.out.println();
        System.out.println("Departamentele companiei:");
        CompanyDepartmentService.getInstance().listDepartments(true);

        ExportData.getInstance().exportData();

    }
}

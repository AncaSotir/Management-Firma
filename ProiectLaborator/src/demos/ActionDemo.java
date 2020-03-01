//package demos;
//
//import loggers.CompanyLogger;
//import models.company.Department;
//import models.employee.Employee;
//import models.project.Project;
//import models.project.Task;
//import services.model_services.services_using_db.CompanyDepartmentService;
//import services.model_services.services_using_db.DepartmentEmployeeService;
//import services.model_services.DepartmentProjectService;
//import services.model_services.services_using_db.ProjectTaskService;
//import services.read_write_services.GUI_services.ExportData;
//import services.read_write_services.GUI_services.ImportData;
//
//import java.util.Calendar;
//import java.util.Scanner;
//
//import static java.util.Calendar.*;
//
//public class ActionDemo {
//    public static void main(String[] args){
//
//        //CompanyLogger.getInstance().wipeLogger();
//
//        ImportData.getInstance().importData();
//
//        boolean continueAction = false;
//
//        do{
//
//            System.out.println("Alegeti numarul unei actiuni:");
//            System.out.println(" 1. Listati departamentele companiei.");
//            System.out.println(" 2. Adaugati un nou departament.");
//            System.out.println(" 3. Stergeti un departament existent.");
//            System.out.println(" 4. Listati angajatii unui departament.");
//            System.out.println(" 5. Adaugati un nou angajat intr-un departament existent.");
//            System.out.println(" 6. Stergeti un angajat existent dintr-un departament.");
//            System.out.println(" 7. Listati proiectele unui departament.");
//            System.out.println(" 8. Adaugati un nou proiect intr-un departament existent.");
//            System.out.println(" 9. Stergeti un proiect existent dintr-un departament.");
//            System.out.println("10. Listati task-urile unui proiect dintr-un departament.");
//            System.out.println("11. Adaugati un nou task unui proiect dintr-un departament.");
//            System.out.println("12. Stergeti un task dintr-un proiect al unui departament.");
//
//            Scanner scanner = new Scanner(System.in);
//            Integer selectedAction = scanner.nextInt();
//
//            switch (selectedAction){
//                case  1: {
//                    CompanyDepartmentService.getInstance().listDepartments(true);
//                    break;
//                } //Listati departamentele companiei.
//                case  2: {
//                    Department newDep = new Department();
//                    System.out.println();
//                    System.out.print("Nume departament: ");
//                    newDep.setName(scanner.next());
//                    CompanyDepartmentService.getInstance().addDepartment(newDep, true);
//                    break;
//                } //Adaugati un nou departament.
//                case  3: {
//                    System.out.println();
//                    System.out.println("Departamentele existente:");
//                    CompanyDepartmentService.getInstance().listDepartments(true);
//                    System.out.println("\nIntroduceti id-ul departamentului pe care doriti sa il stergeti.");
//                    CompanyDepartmentService.getInstance().removeDepartment(CompanyDepartmentService.getInstance().getDepartmentById(scanner.nextInt(), true),true);
//                    break;
//                } //Stergeti un departament existent.
//                case  4: {
//                    System.out.println();
//                    System.out.println("Departamentele existente:");
//                    CompanyDepartmentService.getInstance().listDepartments(true);
//                    System.out.println("\nIntroduceti id-ul departamentului pentru care doriti sa listati angajatii.");
//                    DepartmentEmployeeService.getInstance().listEmployees(CompanyDepartmentService.getInstance().getDepartmentById(scanner.nextInt(), true), true);
//                    break;
//                } //Listati angajatii unui departament.
//                case  5: {
//                    System.out.println();
//                    System.out.println("Departamentele existente:");
//                    CompanyDepartmentService.getInstance().listDepartments(true);
//                    System.out.println("\nIntroduceti id-ul departamentului in care doriti sa adaugati un nou angajat.");
//                    Department depSelected = CompanyDepartmentService.getInstance().getDepartmentById(scanner.nextInt(), true);
//                    Employee newEmp = new Employee();
//                    System.out.println("Nume angajat:");
//                    newEmp.setName(scanner.next());
//                    System.out.println("Data nasterii (ZZ-LL-AAAA):");
//                    String[] dateOfBirth = scanner.next().split("-");
//                    newEmp.setDate_of_birth(Integer.parseInt(dateOfBirth[2]),Integer.parseInt(dateOfBirth[1]),Integer.parseInt(dateOfBirth[0]));
//                    System.out.println("Email:");
//                    newEmp.setEmail(scanner.next());
//                    System.out.println("Numar de telefon:");
//                    newEmp.setPhone_number(scanner.next());
//                    newEmp.setHire_date(Calendar.getInstance().get(YEAR),Calendar.getInstance().get(MONTH),Calendar.getInstance().get(DATE));
//                    newEmp.setDepartment(depSelected, true);
//                    DepartmentEmployeeService.getInstance().addEmployee(depSelected,newEmp, true);
//                    break;
//                } //Adaugati un nou angajat intr-un departament existent.
//                case  6: {
//                    System.out.println();
//                    System.out.println("Departamentele existente:");
//                    CompanyDepartmentService.getInstance().listDepartments(true);
//                    System.out.println("\nIntroduceti id-ul departamentului din care doriti sa stergeti un angajat.");
//                    Department depSelected = CompanyDepartmentService.getInstance().getDepartmentById(scanner.nextInt(), true);
//                    System.out.println("Angajatii existenti:");
//                    DepartmentEmployeeService.getInstance().listEmployees(depSelected, true);
//                    System.out.println("\nIntroduceti id-ul angajatului pe care doriti sa il stergeti.");
//                    DepartmentEmployeeService.getInstance().removeEmployee(depSelected, DepartmentEmployeeService.getInstance().getEmployeeById(depSelected, scanner.nextInt(), true), true);
//                    break;
//                } //Stergeti un angajat existent dintr-un departament.
//                case  7: {
//                    System.out.println();
//                    System.out.println("Departamentele existente:");
//                    CompanyDepartmentService.getInstance().listDepartments(true);
//                    System.out.println("\nIntroduceti id-ul departamentului pentru care doriti sa listati proiectele.");
//                    DepartmentProjectService.getInstance().listProjects(CompanyDepartmentService.getInstance().getDepartmentById(scanner.nextInt(),true), true);
//                    break;
//                } //Listati proiectele unui departament.
//                case  8: {
//                    System.out.println();
//                    System.out.println("Departamentele existente:");
//                    CompanyDepartmentService.getInstance().listDepartments(true);
//                    System.out.println("\nIntroduceti id-ul departamentului pentru care doriti sa adaugati un nou proiect.");
//                    Department selectedDep = CompanyDepartmentService.getInstance().getDepartmentById(scanner.nextInt(),true);
//                    Project newProject = new Project();
//                    System.out.println("Nume proiect:");
//                    newProject.setName(scanner.next());
//                    System.out.println("Numar de persoane necesare:");
//                    newProject.setPersonsRequired(scanner.nextInt());
//                    DepartmentProjectService.getInstance().addProject(selectedDep, newProject,true);
//                    break;
//                } //Adaugati un nou proiect intr-un departament existent.
//                case  9: {
//                    System.out.println();
//                    System.out.println("Departamentele existente:");
//                    CompanyDepartmentService.getInstance().listDepartments(true);
//                    System.out.println("\nIntroduceti id-ul departamentului in care doriti sa stergeti un proiect.");
//                    Department selectedDep = CompanyDepartmentService.getInstance().getDepartmentById(scanner.nextInt(),true);
//                    System.out.println();
//                    System.out.println("Proiecte existente:");
//                    DepartmentProjectService.getInstance().listProjects(selectedDep,true);
//                    System.out.println("\nIntroduceti id-ul proiectului pe care doriti sa il stergeti.");
//                    DepartmentProjectService.getInstance().removeProject(selectedDep, DepartmentProjectService.getInstance().getProjectById(selectedDep, scanner.nextInt(),true),true);
//                    break;
//                } //Stergeti un proiect existent dintr-un departament.
//                case 10: {
//                    System.out.println();
//                    System.out.println("Departamentele existente:");
//                    CompanyDepartmentService.getInstance().listDepartments(true);
//                    System.out.println("\nIntroduceti id-ul departamentului.");
//                    Department selectedDep = CompanyDepartmentService.getInstance().getDepartmentById(scanner.nextInt(),true);
//                    System.out.println();
//                    System.out.println("Proiecte existente:");
//                    DepartmentProjectService.getInstance().listProjects(selectedDep,true);
//                    System.out.println("\nIntroduceti id-ul proiectului.");
//                    Project selectedProject = DepartmentProjectService.getInstance().getProjectById(selectedDep,scanner.nextInt(),true);
//                    System.out.println("\nTask-urile existente:");
//                    ProjectTaskService.getInstance().listTasks(selectedProject,true);
//                    break;
//                } //Listati task-urile unui proiect dintr-un departament.
//                case 11: {
//                    System.out.println();
//                    System.out.println("Departamentele existente:");
//                    CompanyDepartmentService.getInstance().listDepartments(true);
//                    System.out.println("\nIntroduceti id-ul departamentului.");
//                    Department selectedDep = CompanyDepartmentService.getInstance().getDepartmentById(scanner.nextInt(),true);
//                    System.out.println();
//                    System.out.println("Proiecte existente:");
//                    DepartmentProjectService.getInstance().listProjects(selectedDep,true);
//                    System.out.println("\nIntroduceti id-ul proiectului.");
//                    Project selectedProject = DepartmentProjectService.getInstance().getProjectById(selectedDep,scanner.nextInt(),true);
//                    Task newTask = new Task();
//                    System.out.println("\nDescrierea task-ului:");
//                    newTask.setDescription(scanner.next());
//                    ProjectTaskService.getInstance().addTask(selectedProject, newTask,true);
//                    break;
//                } //Adaugati un nou task unui proiect dintr-un departament.
//                case 12: {
//                    System.out.println();
//                    System.out.println("Departamentele existente:");
//                    CompanyDepartmentService.getInstance().listDepartments(true);
//                    System.out.println("\nIntroduceti id-ul departamentului.");
//                    Department selectedDep = CompanyDepartmentService.getInstance().getDepartmentById(scanner.nextInt(), true);
//                    System.out.println();
//                    System.out.println("Proiecte existente:");
//                    DepartmentProjectService.getInstance().listProjects(selectedDep, true);
//                    System.out.println("\nIntroduceti id-ul proiectului.");
//                    Project selectedProject = DepartmentProjectService.getInstance().getProjectById(selectedDep,scanner.nextInt(), true);
//                    System.out.println("\nTask-urile existente:");
//                    ProjectTaskService.getInstance().listTasks(selectedProject, true);
//                    System.out.println("\nIntroduceti id-ul task-ului pe care doriti sa il stergeti.");
//                    ProjectTaskService.getInstance().removeTask(selectedProject, ProjectTaskService.getInstance().getTaskById(selectedProject, scanner.nextInt(), true), true);
//                    break;
//                } //Stergeti un task dintr-un proiect al unui departament.
//            }
//
//            System.out.println();
//            String answer;
//
//            do{
//                System.out.println("Doriti sa continuati? (d/n)");
//                answer = scanner.next();
//            }
//            while(!answer.equals("d") && !answer.equals("n"));
//
//            continueAction = answer.equals("d");
//
//
//        }while(continueAction);
//
//        ExportData.getInstance().exportData();
//
//    }
//}

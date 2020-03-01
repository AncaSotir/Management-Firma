package services.model_services;

import loggers.CompanyLogger;
import models.company.Company;
import models.employee.Person;

public class CompanyApplicantService {
    private static CompanyApplicantService ourInstance = new CompanyApplicantService();

    public static CompanyApplicantService getInstance() {
        return ourInstance;
    }

    private CompanyApplicantService() {
    }


    public void addApplicant (Person applicant, boolean logFlag) {
        Company.getInstance().getApplicants().add(applicant);

        if(logFlag){
            String logMessage = "Added new applicant: ";
            logMessage = logMessage.concat(applicant.toString());
            CompanyLogger.getInstance().logAction(logMessage);
        }
    }

    public void removeApplicant (Person applicant, boolean logFlag) {
        Company.getInstance().getApplicants().remove(applicant);

        if(logFlag){
            String logMessage = "Removed applicant: ";
            logMessage = logMessage.concat(applicant.toString());
            CompanyLogger.getInstance().logAction(logMessage);
        }
    }

    public void listApplicants (boolean logFlag) {
        if(logFlag){
            String logMessage = "Listed applicants";
            CompanyLogger.getInstance().logAction(logMessage);
        }

        for(int i=0; i<Company.getInstance().getApplicants().size(); ++i) {
            System.out.println(Company.getInstance().getApplicants().elementAt(i));
        }
    }
}

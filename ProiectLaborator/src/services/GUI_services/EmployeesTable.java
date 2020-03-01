package services.GUI_services;

import models.company.Company;
import models.employee.Employee;
import services.database_services.utils.DBUtils;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Vector;

import static java.util.Calendar.*;

public class EmployeesTable extends AbstractTableModel {

    private ArrayList<Employee> employees;

    public EmployeesTable(Connection connection){
        employees = DBUtils.getInstance().selectAllEmployees(Company.getInstance().getConnection());
    }

    @Override
    public int getRowCount() {
        return employees.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex){
            case 0:
                return employees.get(rowIndex).getId();
            case 1:
                return employees.get(rowIndex).getName();
            case 2:
                String dateOfBirth = "";
                dateOfBirth += Integer.toString(employees.get(rowIndex).getDate_of_birth().get(DATE));
                dateOfBirth += "-";
                dateOfBirth += Integer.toString(employees.get(rowIndex).getDate_of_birth().get(MONTH)+1);
                dateOfBirth += "-";
                dateOfBirth += Integer.toString(employees.get(rowIndex).getDate_of_birth().get(YEAR));
                return dateOfBirth;
            case 3:
                return employees.get(rowIndex).getEmail();
            case 4:
                return employees.get(rowIndex).getPhone_number();
            case 5:
                String hireDate = "";
                hireDate += Integer.toString(employees.get(rowIndex).getHire_date().get(DATE));
                hireDate += "-";
                hireDate += Integer.toString(employees.get(rowIndex).getHire_date().get(MONTH)+1);
                hireDate += "-";
                hireDate += Integer.toString(employees.get(rowIndex).getHire_date().get(YEAR));
                return hireDate;
            case 6:
                JButton deleteButton = new JButton("Delete Employee");

                EmployeesTable myTable = this;
                deleteButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        DBUtils.getInstance().deleteEmployee(Company.getInstance().getConnection(), myTable.employees.get(rowIndex).getId());
                        myTable.employees.remove(rowIndex);
                        myTable.fireTableRowsDeleted(rowIndex, rowIndex);
                    }
                });
                return deleteButton;
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        switch(column){
            case 0:
                return "ID";
            case 1:
                return "NAME";
            case 2:
                return "DATE OF BIRTH";
            case 3:
                return "EMAIL";
            case 4:
                return "PHONE NUMBER";
            case 5:
                return "HIRE DATE";
            case 6:
                return "DELETE";
        }
        return null;
    }

    @Override
    public int findColumn(String columnName) {
        switch(columnName){
            case "ID":
                return 0;
            case "NAME":
                return 1;
            case "DATE OF BIRTH":
                return 2;
            case "EMAIL":
                return 3;
            case "PHONE NUMBER":
                return 4;
            case "HIRE DATE":
                return 5;
            case "DELETE":
                return 6;
        }
        return -1;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex){
            case 0:
                return Integer.class;
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                return String.class;
            case 6:
                return JButton.class;
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch (columnIndex){
            case 0:
            case 5:
            case 6:
                return false;
            case 1:
            case 2:
            case 3:
            case 4:
                return true;
        }
        return false;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        switch (columnIndex){
            case 1:
                employees.get(rowIndex).setName(aValue.toString());
                break;
            case 2:
                String[] temp = aValue.toString().split("-");
                if(temp.length == 3){
                    employees.get(rowIndex).setDate_of_birth(Integer.parseInt(temp[2]), Integer.parseInt(temp[1])-1, Integer.parseInt(temp[0]));
                }
                break;
            case 3:
                employees.get(rowIndex).setEmail(aValue.toString());
                break;
            case 4:
                employees.get(rowIndex).setPhone_number(aValue.toString());
                break;
        }
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }
}

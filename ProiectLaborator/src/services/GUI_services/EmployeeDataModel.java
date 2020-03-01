package services.GUI_services;



import models.company.Company;
import models.employee.Employee;
import services.database_services.utils.ConnectionUtils;
import services.database_services.utils.DBUtils;
import services.read_write_services.utils.ImportData;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;

public class EmployeeDataModel {

    private static class MyMouseAdapter extends MouseAdapter {
        private final JTable table;

        public MyMouseAdapter(JTable table) {
            this.table = table;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            int column = table.getColumnModel().getColumnIndexAtX(e.getX());
            int row = e.getY()/table.getRowHeight();
            if(row<table.getRowCount() && row>=0 && column<table.getColumnCount() && column>=0){
                Object target = table.getValueAt(row, column);
                if(target instanceof JButton){
                    ((JButton) target).doClick();
                }
            }
        }
    }

    public static void main(String[] args){

        ImportData.getInstance().importDataDB(ConnectionUtils.getInstance().getDBConnection());

        JFrame jFrame = new JFrame("Employee DataModel @MyCompany");

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTable table = new JTable(new EmployeesTable(Company.getInstance().getConnection()));

        TableCellRenderer renderer = (table1, value, isSelected, hasFocus, row, column) -> (JButton)value;
        table.getColumn("DELETE").setCellRenderer(renderer);
        table.addMouseListener(new MyMouseAdapter(table));

        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));

        JPanel newEmployeeForm = new JPanel();
        newEmployeeForm.setLayout(new GridLayout(4,2));

        newEmployeeForm.add(new JLabel("Name"));
        JTextField nameTextField = new JTextField();
        newEmployeeForm.add(nameTextField);

        newEmployeeForm.add(new JLabel("Date Of Birth"));
        JTextField birthTextField = new JTextField();
        newEmployeeForm.add(birthTextField);

        newEmployeeForm.add(new JLabel("Email"));
        JTextField emailTextField = new JTextField();
        newEmployeeForm.add(emailTextField);

        newEmployeeForm.add(new JLabel("Phone Number"));
        JTextField phoneTextField = new JTextField();
        newEmployeeForm.add(phoneTextField);

        form.add(newEmployeeForm);

        JButton addEmployeeButton = new JButton("Add New Employee");
        addEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Employee newEmployee = new Employee();
                newEmployee.setName(nameTextField.getText());
                newEmployee.setEmail(emailTextField.getText());
                newEmployee.setPhone_number(phoneTextField.getText());
                String[] temp = birthTextField.getText().split("-");
                if(temp.length == 3){
                    newEmployee.setDate_of_birth(Integer.parseInt(temp[2]), Integer.parseInt(temp[1])-1, Integer.parseInt(temp[0]));
                }
                TableModel myTable = table.getModel();
                if(myTable instanceof EmployeesTable){
                    EmployeesTable myEmployeesTable = (EmployeesTable)myTable;
                    myEmployeesTable.getEmployees().add(newEmployee);
                    DBUtils.getInstance().insertEmployeeTable(Company.getInstance().getConnection(), newEmployee);
                    myEmployeesTable.fireTableRowsInserted(myEmployeesTable.getEmployees().size(), myEmployeesTable.getEmployees().size());

                    nameTextField.setText("");
                    birthTextField.setText("");
                    emailTextField.setText("");
                    phoneTextField.setText("");
                }

            }
        });
        addEmployeeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        form.add(addEmployeeButton);

        JButton resetFormTextFields = new JButton("Reset Text Fields");
        resetFormTextFields.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameTextField.setText("");
                birthTextField.setText("");
                emailTextField.setText("");
                phoneTextField.setText("");
            }
        });
        resetFormTextFields.setAlignmentX(Component.CENTER_ALIGNMENT);
        form.add(resetFormTextFields);


        JScrollPane TableScrollPane = new JScrollPane(table);
        JScrollPane EmployeeFormScrollPane = new JScrollPane(form);

        EmployeeFormScrollPane.setSize(640,100);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, TableScrollPane, EmployeeFormScrollPane);
        splitPane.setOneTouchExpandable(true);

        splitPane.setDividerLocation(240);


        jFrame.add(splitPane);
        jFrame.setSize(960, 480);

        jFrame.setVisible(true);
        jFrame.setResizable(false);
    }
}

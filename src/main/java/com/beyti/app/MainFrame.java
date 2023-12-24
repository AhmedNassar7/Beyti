package com.beyti.app;

import com.beyti.models.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MainFrame extends JFrame {
    private JPanel mainFramePanel;
    private JTabbedPane tabbedPane1;
    private JTabbedPane tabbedPane2;
    private JTable propertiesTable;
    private JTextField propertyAddressTextField;
    private JTextField propertyCityTextField;
    private JButton propertyAddButton;
    private JButton empRegisterButton;
    private JTextField empOfficeIDTextField;
    private JTextField empFirstNameTextField;
    private JTextField empLastNameTextField;
    private JTextField empUsernameTextField;
    private JTextField empPasswordTextField;
    private JTable empTable;
    private JButton addOfficeBtn;
    private JTextField officeLocationTextField;
    private JTable salesOfficeTable;
    private JButton officeUpdateButton;
    private JButton officeDeleteButton;
    private JButton empUpdateButton;
    private JButton empDeleteButton;
    private JButton ownershipUpdateButton;
    private JButton ownershipDeleteButton;
    private JButton OwnerUpdateButton;
    private JButton OwnerDeleteButton;
    private JButton OwnerRegisterButton;
    private JTextField ownershipPropertyIdTextField;
    private JTextField ownershipOwnerIdTextField;
    private JButton OwnershipRegisterButton;
    private JTextField ownedPercentTextField;
    private JTextField propertyStateTextField;
    private JTextField propertyPostalCodeTextField;
    private JTextField ownerFirstNameTextField;
    private JTextField ownerLastNameTextField;
    private JTable ownersTable;
    private JTable ownershipsTable;
    private JButton propertyDeleteButton;
    private JButton propertyUpdateButton;
    private JTextField propertySalesOfficeNumberTextField;
    private JRadioButton yesRadioButton;
    private JScrollPane scrollPane1;

    public MainFrame(String title) {
        super(title);
        setContentPane(mainFramePanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(1000, 720));

        empTable.setFillsViewportHeight(true);
        salesOfficeTable.setFillsViewportHeight(true);
        populateEmpTable();
        populateSalesOfficeTable();
        populatePropertiesTable();
        populateOwnersTable();
        populateOwnershipsTable();
        pack();
        setVisible(true);
        empRegisterButton.addActionListener(actionEvent ->  {
            try{
                int officeID = Integer.parseInt(empOfficeIDTextField.getText());
                String firstName = empFirstNameTextField.getText();
                String lastName = empLastNameTextField.getText();
                String username = empUsernameTextField.getText();
                String password = empPasswordTextField.getText();
                Employee employee = Employee.create(officeID, firstName, lastName, username, password);
                DefaultTableModel model = (DefaultTableModel) empTable.getModel();
                assert employee != null;
                model.addRow(new Object[]{employee.id, employee.officeNumber, employee.firstName,
                        employee.lastName, employee.username});
                JOptionPane.showMessageDialog(null, "Employee registered successfully!");
                } catch (RuntimeException e) {
                    JOptionPane.showMessageDialog(null, "Error while registering employee!" + e.getMessage());
                }


        });
        addOfficeBtn.addActionListener(actionEvent -> {
            try{
                String location = officeLocationTextField.getText();
                SalesOffice salesOffice = SalesOffice.create(location);
                DefaultTableModel model = (DefaultTableModel) salesOfficeTable.getModel();
                assert salesOffice != null;
                model.addRow(new Object[]{salesOffice.number, salesOffice.location, salesOffice.managerId});
                JOptionPane.showMessageDialog(null, "Office added successfully!");
            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(null, "Error while adding office!\n" + e.getMessage());
            }
        });
        officeUpdateButton.addActionListener(actionEvent -> {
            int row = salesOfficeTable.getSelectedRow();
            int managerId = Integer.parseInt(salesOfficeTable.getModel().getValueAt(row, 2).toString());
            if (managerId == 0) {
                JOptionPane.showMessageDialog(null, "You can't update an office without a manager!");
            }
            try{
                int number = Integer.parseInt(salesOfficeTable.getModel().getValueAt(row, 0).toString());
                String location = salesOfficeTable.getModel().getValueAt(row, 1).toString();
                SalesOffice.update(number, location, managerId);
                JOptionPane.showMessageDialog(null, "Office updated successfully!");
            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(null, "Error while updating office!\n" + e.getMessage());
            }
        });
        officeDeleteButton.addActionListener(actionEvent -> {
            try{
                int row = salesOfficeTable.getSelectedRow();
                int number = Integer.parseInt(salesOfficeTable.getModel().getValueAt(row, 0).toString());
                SalesOffice.delete(number);
                DefaultTableModel model = (DefaultTableModel) salesOfficeTable.getModel();
                model.removeRow(row);
                JOptionPane.showMessageDialog(null, "Office deleted successfully!");
            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(null, "Error while deleting office!\n" + e.getMessage());
            }
        });
        empUpdateButton.addActionListener(actionEvent -> {
            try{
                int row = empTable.getSelectedRow();
                int id = Integer.parseInt(empTable.getModel().getValueAt(row, 0).toString());
                int officeNumber = Integer.parseInt(empTable.getModel().getValueAt(row, 1).toString());
                String firstName = empTable.getModel().getValueAt(row, 2).toString();
                String lastName = empTable.getModel().getValueAt(row, 3).toString();
                String username = empTable.getModel().getValueAt(row, 4).toString();
                Employee.update(id, officeNumber, firstName, lastName, username);
                JOptionPane.showMessageDialog(null, "Employee updated successfully!");
            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(null, "Error while updating employee!\n" + e.getMessage());
            }
        });
        empDeleteButton.addActionListener(actionEvent -> {
            try{
                int row = empTable.getSelectedRow();
                int id = Integer.parseInt(empTable.getModel().getValueAt(row, 0).toString());
                Employee.delete(id);
                DefaultTableModel model = (DefaultTableModel) empTable.getModel();
                model.removeRow(row);
                JOptionPane.showMessageDialog(null, "Employee deleted successfully!");
            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(null, "Error while deleting employee!\n" + e.getMessage());
            }
        });

        OwnerRegisterButton.addActionListener(actionEvent -> {
            try{
                String firstName = ownerFirstNameTextField.getText();
                String lastName = ownerLastNameTextField.getText();
                Owner owner = Owner.create(firstName, lastName);
                DefaultTableModel model = (DefaultTableModel) ownersTable.getModel();
                assert owner != null;
                model.addRow(new Object[]{owner.id, owner.firstName, owner.lastName});
                JOptionPane.showMessageDialog(null, "Owner registered successfully!");
            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(null, "Error while registering owner!\n" + e.getMessage());
            }
        });
        OwnerUpdateButton.addActionListener(actionEvent -> {
            int row = ownersTable.getSelectedRow();
            int id = Integer.parseInt(ownersTable.getModel().getValueAt(row, 0).toString());
            String firstName = ownersTable.getModel().getValueAt(row, 1).toString();
            String lastName = ownersTable.getModel().getValueAt(row, 2).toString();
            try{
                Owner.update(id, firstName, lastName);
                JOptionPane.showMessageDialog(null, "Owner updated successfully!");
            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(null, "Error while updating owner!\n" + e.getMessage());
            }
        });
        OwnerDeleteButton.addActionListener(actionEvent -> {
            try{
                int row = ownersTable.getSelectedRow();
                int id = Integer.parseInt(ownersTable.getModel().getValueAt(row, 0).toString());
                Owner.delete(id);
                DefaultTableModel model = (DefaultTableModel) ownersTable.getModel();
                model.removeRow(row);
                JOptionPane.showMessageDialog(null, "Owner deleted successfully!");
            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(null, "Error while deleting owner!\n" + e.getMessage());
            }
        });
        OwnershipRegisterButton.addActionListener(actionEvent -> {
            try{
                int ownerId = Integer.parseInt(ownershipOwnerIdTextField.getText());
                int propertyId = Integer.parseInt(ownershipPropertyIdTextField.getText());
                float ownedPercent = Float.parseFloat(ownedPercentTextField.getText());
                OwnerShip ownership = OwnerShip.create(ownerId, propertyId, ownedPercent);
                DefaultTableModel model = (DefaultTableModel) ownershipsTable.getModel();
                model.addRow(new Object[]{ownership.ownerId, ownership.propertyId, ownership.percent});
                JOptionPane.showMessageDialog(null, "Ownership registered successfully!");
            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(null, "Error while registering ownership!\n" + e.getMessage());
            }
        });
        ownershipUpdateButton.addActionListener(actionEvent -> {
            try{
                int row = ownershipsTable.getSelectedRow();
                int ownerId = Integer.parseInt(ownershipsTable.getModel().getValueAt(row, 0).toString());
                int propertyId = Integer.parseInt(ownershipsTable.getModel().getValueAt(row, 1).toString());
                float ownedPercent = Float.parseFloat(ownershipsTable.getModel().getValueAt(row, 2).toString());
                OwnerShip.update(ownerId, propertyId, ownedPercent);
                JOptionPane.showMessageDialog(null, "Ownership updated successfully!");
            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(null, "Error while updating ownership!\n" + e.getMessage());
            }
        });
        ownershipDeleteButton.addActionListener(actionEvent -> {
            try{
                int row = ownershipsTable.getSelectedRow();
                int ownerId = Integer.parseInt(ownershipsTable.getModel().getValueAt(row, 0).toString());
                int propertyId = Integer.parseInt(ownershipsTable.getModel().getValueAt(row, 1).toString());
                OwnerShip.delete(ownerId, propertyId);
                DefaultTableModel model = (DefaultTableModel) ownershipsTable.getModel();
                model.removeRow(row);
                JOptionPane.showMessageDialog(null, "Ownership deleted successfully!");
            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(null, "Error while deleting ownership!\n" + e.getMessage());
            }
        });
        propertyAddButton.addActionListener(actionEvent -> {

            try{
                String propertyAddress = propertyAddressTextField.getText();
                String propertyCity = propertyCityTextField.getText();
                String propertyState = propertyStateTextField.getText();
                String propertyPostalCode = propertyPostalCodeTextField.getText();
                int salesOfficeNumber = Integer.parseInt(propertySalesOfficeNumberTextField.getText());
                Property property = Property.create(propertyAddress, propertyCity, propertyState,
                        propertyPostalCode, salesOfficeNumber);
                DefaultTableModel model = (DefaultTableModel) propertiesTable.getModel();
                assert property != null;
                model.addRow(new Object[]{property.id, property.address, property.city, property.state,
                        property.postCode, property.salesOfficeNumber});
                JOptionPane.showMessageDialog(null, "Property added successfully!");
            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(null, "Error while adding property!\n" + e.getMessage());
            }
        });
        propertyUpdateButton.addActionListener(actionEvent -> {
            try{
                int row = propertiesTable.getSelectedRow();
                int id = Integer.parseInt(propertiesTable.getModel().getValueAt(row, 0).toString());
                String propertyAddress = propertiesTable.getModel().getValueAt(row, 1).toString();
                String propertyCity = propertiesTable.getModel().getValueAt(row, 2).toString();
                String propertyState = propertiesTable.getModel().getValueAt(row, 3).toString();
                String propertyPostalCode = propertiesTable.getModel().getValueAt(row, 4).toString();
                int salesOfficeNumber = Integer.parseInt(propertiesTable.getModel().getValueAt(row, 5).toString());
                Property.update(id, propertyAddress, propertyCity, propertyState, propertyPostalCode, salesOfficeNumber);
                JOptionPane.showMessageDialog(null, "Property updated successfully!");
            } catch (RuntimeException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error while updating property!\n" + e.getMessage());
            }
        });
        propertyDeleteButton.addActionListener(actionEvent -> {
            try{
                int row = propertiesTable.getSelectedRow();
                int id = Integer.parseInt(propertiesTable.getModel().getValueAt(row, 0).toString());
                Property.delete(id);
                DefaultTableModel model = (DefaultTableModel) propertiesTable.getModel();
                model.removeRow(row);
                JOptionPane.showMessageDialog(null, "Property deleted successfully!");
            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(null, "Error while deleting property!\n" + e.getMessage());
            }
        });
    }
    private void populateEmpTable(){
        empTable.setModel(new DefaultTableModel(Employee.getColumns(), 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0;
            }
        });
        Employee.get().forEach(employee -> {
            DefaultTableModel model = (DefaultTableModel) empTable.getModel();
            model.addRow(new Object[]{employee.id, employee.officeNumber, employee.firstName,
                    employee.lastName, employee.username});
        });

    }
    private void populateSalesOfficeTable(){
        salesOfficeTable.setModel(new DefaultTableModel(SalesOffice.getColumns(),0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0;
            }
        });
        SalesOffice.get().forEach(salesOffice -> {
            DefaultTableModel model = (DefaultTableModel) salesOfficeTable.getModel();
            model.addRow(new Object[]{salesOffice.number, salesOffice.location, salesOffice.managerId});
        });

    }
    private void populateOwnersTable(){
        ownersTable.setModel(new DefaultTableModel(Owner.getColumns(), 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0;
            }
        });
        Owner.get().forEach(owner -> {
            DefaultTableModel model = (DefaultTableModel) ownersTable.getModel();
            model.addRow(new Object[]{owner.id, owner.firstName, owner.lastName});
        });
    }
    private void populateOwnershipsTable(){
        ownershipsTable.setModel(new DefaultTableModel(OwnerShip.getColumns(), 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 2;
            }
        });
        OwnerShip.get().forEach(ownership -> {
            DefaultTableModel model = (DefaultTableModel) ownershipsTable.getModel();
            model.addRow(new Object[]{ownership.ownerId, ownership.propertyId, ownership.percent});
        });
    }
    private void populatePropertiesTable(){
        propertiesTable.setModel(new DefaultTableModel(Property.getColumns(), 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0;
            }
        });
        Property.get().forEach(property -> {
            DefaultTableModel model = (DefaultTableModel) propertiesTable.getModel();
            model.addRow(new Object[]{property.id, property.address, property.city, property.state,
                    property.postCode, property.salesOfficeNumber});
        });
    }
}

package com.debca.example.ocp;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class EmployeeTerminatorDialog implements EmployeeTerminatorView {

    private JFrame frame;
    private JList listBox;
    private JButton terminateButton;
    private EmployeeTerminatorController controller;
    private Vector employees;

    public static final String EMPLOYEE_LIST_NAME = "Employee List";
    public static final String TERMINATE_BUTTON_NAME = "Terminate";

    public void initialize(EmployeeTerminatorController controller){
        this.controller = controller;
        initializeEmployeeListBox();
        initializeTerminateButton();
        initializeContentPane();
    }

    private void initializeContentPane() {
        frame = new JFrame("Employee List");
        frame.getContentPane().setLayout(new FlowLayout());
        frame.getContentPane().add(listBox);
        frame.getContentPane().add(terminateButton);
        frame.getContentPane().setSize(300, 600);
        frame.pack();
    }

    private void initializeTerminateButton() {
        terminateButton = new JButton(TERMINATE_BUTTON_NAME);
        terminateButton.disable();
        terminateButton.setName(TERMINATE_BUTTON_NAME);
        terminateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                controller.terminate();
            }
        });
    }

    private void initializeEmployeeListBox() {
        listBox = new JList();
        listBox.setName(EMPLOYEE_LIST_NAME);
        listBox.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                if (!listSelectionEvent.getValueIsAdjusting()){
                    controller.selectionChanged((String) listBox.getSelectedValue());
                }
            }
        });
    }

    public Container getContentPane() {
        return frame.getContentPane();
    }

    public JFrame getFrame() {
        return frame;
    }

    @Override
    public void enableTerminate(boolean enable) {
        terminateButton.setEnabled(enable);
    }

    @Override
    public void setEmployeeList(Vector employees) {
        this.employees = employees;
        listBox.setListData(employees);
        frame.pack();
    }

    @Override
    public void clearSelection() {
        listBox.clearSelection();
    }
}

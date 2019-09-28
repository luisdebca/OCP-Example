package com.debca.example.ocp;

import junit.framework.TestCase;
import junit.textui.TestRunner;

import java.util.Vector;

public class TestEmployeeTerminatorModel extends TestCase implements EmployeeTerminatorView {

    private boolean terminateEnable = true;
    private String selectedEmployee;
    private Vector noEmployees = new Vector();
    private Vector threeEmployees = new Vector();
    private Vector employees = null;
    private EmployeeTerminatorModel model;

    public static void main(String[] args) {
        TestRunner.main(new String[] {"TestEmployeeTerminatorMOdel"});
    }

    public TestEmployeeTerminatorModel(String name) {
        super(name);
    }

    public void setUp() throws Exception {
        model = new EmployeeTerminatorModel();
        threeEmployees.add("Bob");
        threeEmployees.add("Bill");
        threeEmployees.add("Robert");
    }

    public void tearDown() throws Exception {

    }

    public void testNoEmployees() throws Exception {
        model.initialize(noEmployees, this);
        assertEquals(0, employees.size());
        assertFalse(terminateEnable);
        assertNull(selectedEmployee);
    }

    public void testThreeEmployees() throws Exception {
        model.initialize(threeEmployees, this);
        assertEquals(3, employees.size());
        assertFalse(terminateEnable);
        assertNull(selectedEmployee);
    }

    public void testSelection() throws Exception {
        model.initialize(threeEmployees, this);
        model.selectionChanged("Bob");
        assertTrue(terminateEnable);
        model.selectionChanged(null);
        assertFalse(terminateEnable);
    }

    public void testTerminate() throws Exception {
        model.initialize(threeEmployees, this);
        assertEquals(3, employees.size());
        selectedEmployee = "Bob";
        model.selectionChanged("Bob");
        model.terminate();
        assertEquals(2, employees.size());
        assertNull(selectedEmployee);
        assertFalse(terminateEnable);
        assertTrue(employees.contains("Bill"));
        assertTrue(employees.contains("Robert"));
        assertFalse(employees.contains("Bob"));
    }

    @Override
    public void enableTerminate(boolean enable) {
        terminateEnable = enable;
    }

    @Override
    public void setEmployeeList(Vector employees) {
        this.employees = (Vector) employees.clone();
    }

    @Override
    public void clearSelection() {
        selectedEmployee = null;
    }
}

package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        // Create new Application
        App a = new App();

        // Connect to database
        a.connect();
        // Get Employee
        Employee emp = a.getEmployee(255530);
        // Display results
        a.displayEmployee(emp);
        ArrayList<Employee> allSalaries = a.getAllSalaries();
        a.printSalaries(allSalaries);
        Department department = a.getDepartment("Sales");
        ArrayList<Employee> salaryByDepartment = a.getSalariesByDepartment(department);
        a.printSalaries(salaryByDepartment);
        // Disconnect from database
        a.disconnect();

    }

    // Connection to MySQL database.
    private Connection con = null;

    public void connect() {
        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/employees?useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }


    // Disconnect from the MySQL database.

    public void disconnect() {
        if (con != null) {
            try {
                // Close connection
                con.close();
                System.out.println("Database connection closed.");
            } catch (Exception e) {
                System.out.println("Error closing connection to database");
            }
        }
    }


    public Employee getEmployee(int ID) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "Select employees.emp_no, employees.first_name, employees.last_name, salaries.salary, titles.title,  departments.dept_name, em.last_name "
                    + " FROM employees"
                    + " join salaries on employees.emp_no= salaries.emp_no "
                    + "join titles on employees.emp_no = titles.emp_no "
                    + "join dept_emp on employees.emp_no = dept_emp.emp_no "
                    + "join departments on dept_emp.dept_no = departments.dept_no "
                    + "join dept_manager on departments .dept_no = dept_manager.dept_no "
                    + "join employees em on dept_manager.emp_no = em.emp_no "
                    + "where employees.emp_no = " + ID
                    + " limit 1";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            if (rset.next()) {
                Employee emp = new Employee();
                emp.emp_no = rset.getInt("employees.emp_no");
                emp.first_name = rset.getString("employees.first_name");
                emp.last_name = rset.getString("employees.last_name");
                emp.salary = rset.getInt("salaries.salary");
                emp.title = rset.getString("titles.title");
                emp.dept_name = rset.getString("departments.dept_name");
                //emp.manager = rset.getString("em.last_name");
                return emp;
            } else {
                System.out.println("Employee not found.");
                return null;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get employee details");
            return null;
        }
    }

    public ArrayList<Employee> getAllSalaries() {
        try {
            Statement stmt = con.createStatement();
            String strSelect = "SELECT employees.emp_no, employees.first_name, employees.last_name, salaries.salary " +
                    " FROM employees, salaries " +
                    " WHERE employees.emp_no = salaries.emp_no AND salaries.to_date = '9999-01-01' " +
                    " ORDER BY employees.emp_no ASC ";


            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract employee information
            ArrayList<Employee> employees = new ArrayList<Employee>();
            while (rset.next()) {
                Employee emp = new Employee();
                emp.emp_no = rset.getInt("employees.emp_no");
                emp.first_name = rset.getString("employees.first_name");
                emp.last_name = rset.getString("employees.last_name");
                emp.salary = rset.getInt("salaries.salary");
                employees.add(emp);
            }
            return employees;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("Failed to get salary details");
            return null;
        }


    }

    public void displayEmployee(Employee emp) {
        if (emp != null) {
            System.out.println(
                    emp.emp_no + " "
                            + emp.first_name + " "
                            + emp.last_name + "\n"
                            + emp.title + "\n"
                            + "Salary:" + emp.salary + "\n"
                            + emp.dept_name + "\n"
                            + "Manager: " + emp.manager + "\n");
        } else {
            System.out.println("Nothing to display.");
        }
    }


    public void printSalaries(ArrayList<Employee> employees) {
        // Check employees is not null
        if (employees == null) {
            System.out.println("No employees");
            return;
        }
        // Print header
        System.out.println(String.format("%-10s %-15s %-20s %-8s", "Emp No", "First Name", "Last Name", "Salary"));
        // Loop over all employees in the list
        for (Employee emp : employees) {
            if (emp == null)
                continue;
            String emp_string =
                    String.format("%-10s %-15s %-20s %-8s",
                            emp.emp_no, emp.first_name, emp.last_name, emp.salary);
            System.out.println(emp_string);
        }
    }

    public Department getDepartment(String dept_name) {

        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "Select departments.dept_name, departments.dept_no "
                    + " FROM departments "
                    + " WHERE departments.dept_name =  dept_name" ;

            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            if (rset.next()) {
                Department department = new Department();
                department.dept_name=rset.getString("departments.dept_name");
                department.dept_no= rset.getString("departments.dept_no");
                System.out.println("Department name is " + department.dept_name + "department no is "+ department.dept_no);
                return department;
            } else {
                System.out.println("Department not found.");
                return null;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get department details");
            return null;
        }
    }


    public ArrayList<Employee> getSalariesByDepartment(Department dept){
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT employees.emp_no, employees.first_name, employees.last_name, salaries.salary "
                    + " FROM employees, salaries, dept_emp, departments "
                      + " WHERE employees.emp_no = salaries.emp_no "
                      + " AND employees.emp_no = dept_emp.emp_no "
                      + " AND dept_emp.dept_no = departments.dept_no "
                      + " AND salaries.to_date = '9999-01-01' "
                      + " AND departments.dept_no = '<dept_no>' "
                      + " ORDER BY employees.emp_no ASC ";

            ResultSet rset = stmt.executeQuery(strSelect);
            ArrayList<Employee> employees = new ArrayList<Employee>();
            while (rset.next()) {
                Employee emp = new Employee();
                emp.emp_no = rset.getInt("employees.emp_no");
                emp.first_name = rset.getString("employees.first_name");
                emp.last_name = rset.getString("employees.last_name");
                emp.salary = rset.getInt("salaries.salary");
                System.out.println("Employee name is " + emp.first_name);
                employees.add(emp);
            }
            return employees;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("Failed to get salary by department details");
            return null;
        }

    }




}
package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;

public class App
{
    public static void main(String[] args) {
        // Create new Application
        App a = new App();

        // Connect to database
        a.connect();
        // Get Employee
        Employee emp = a.getEmployee(255530);
        // Display results
        a.displayEmployee(emp);

        // Disconnect from database
        a.disconnect();

    }

        // Connection to MySQL database.
        private Connection con = null;
        public void connect()
        {
            try
            {
                // Load Database driver
                Class.forName("com.mysql.cj.jdbc.Driver");
            }
            catch (ClassNotFoundException e)
            {
                System.out.println("Could not load SQL driver");
                System.exit(-1);
            }

            int retries = 10;
            for (int i = 0; i < retries; ++i)
            {
                System.out.println("Connecting to database...");
                try
                {
                    // Wait a bit for db to start
                    Thread.sleep(30000);
                    // Connect to database
                   con = DriverManager.getConnection("jdbc:mysql://db:3306/employees?useSSL=false", "root", "example");
                    System.out.println("Successfully connected");
                    break;
                }
                catch (SQLException sqle)
                {
                    System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                    System.out.println(sqle.getMessage());
                }
                catch (InterruptedException ie)
                {
                    System.out.println("Thread interrupted? Should not happen.");
                }
            }
        }


         // Disconnect from the MySQL database.

        public void disconnect()
        {
            if (con != null)
            {
                try
                {
                    // Close connection
                    con.close();
                    System.out.println("Database connection closed.");
                }
                catch (Exception e)
                {
                    System.out.println("Error closing connection to database");
                }
            }
        }


    public Employee getEmployee(int ID)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =  "Select e.emp_no, e.first_name, e.last_name, s.salary, t.title,  d.dept_name, em.last_name as manager "
            +" FROM employees e"
           +" join salaries s on e.emp_no= s.emp_no "
           + "join titles t on e.emp_no = t.emp_no "
           + "join dept_emp de on e.emp_no = de.emp_no "
           + "join departments d on de.dept_no = d.dept_no "
           + "join dept_manager dm on d.dept_no = dm.dept_no "
           + "join employees em on dm.emp_no = em.emp_no "
            + "where e.emp_no = " + ID
            +" limit 1";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            if (rset.next())
            {
                Employee emp = new Employee();
                emp.emp_no = rset.getInt("e.emp_no");
                emp.first_name = rset.getString("e.first_name");
                emp.last_name = rset.getString("e.last_name");
                emp.salary =rset.getInt("s.salary");
                emp.title = rset.getString("t.title");
                emp.dept_name = rset.getString("d.dept_name");
               // emp.manager = rset.getString("manager");
                return emp;
            }
            else {
                System.out.println("Employee not found.");
                return null;
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get employee details");
            return null;
        }
    }

    public void displaySalariesByRoleFeature()
    {


        String strSelect =  "SELECT employees.emp_no, employees.first_name, employees.last_name, salaries.salary "
                + " FROM employees, salaries, titles "
                + " WHERE employees.emp_no = salaries.emp_no "
                + " AND employees.emp_no = titles.emp_no"
                + " AND salaries.to_date = '9999-01-01'"
                + " AND titles.to_date = '9999-01-01'"
                + " AND titles.title = '<title>'"
                + " ORDER BY employees.emp_no ASCstatement";

    }

    public void displayEmployee(Employee emp)
    {
        if (emp != null)
        {
            System.out.println(
                    emp.emp_no + " "
                            + emp.first_name + " "
                            + emp.last_name + "\n"
                            + emp.title + "\n"
                            + "Salary:" + emp.salary + "\n"
                            + emp.dept_name + "\n"
                            + "Manager: " + emp.manager + "\n");
        }
        else {
            System.out.println("Nothing to display.");
        }
    }

    public Department getDepartment(int dept_no) {
        return null;
    }

    public ArrayList<Employee> getSalariesByDepartment(Department dept){
            return  null;
    }
}
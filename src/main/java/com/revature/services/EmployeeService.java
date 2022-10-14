package com.revature.services;


import com.revature.models.Employee;

import com.revature.models.Ticket;
import com.revature.repos.EmployeeRepo;



import java.util.List;



public class EmployeeService {



    private EmployeeRepo employeeRepo;



    // Constructors

    public EmployeeService() {

        employeeRepo = new EmployeeRepo();

    }



    public EmployeeService(EmployeeRepo employeeRepo) {

        this.employeeRepo = employeeRepo;

    }



    // Methods

    // Create a user

    public int createEmployee(Employee employee){

        return employeeRepo.create(employee);

    }



    // Read All employee's Tickets

    public List<Ticket> getAllTickets(int id){

        return employeeRepo.getAll(id);

    }



    // Read a user by ID

   // public Employee getEmployeeById(int id){

 //       return employeeRepo.getById(id);

    //}



    // Login an employee

    public Employee loginEmployee(Employee employee){

        return employeeRepo.login(employee);

    }



    // Delete a user

   // public boolean deleteEmployee(Employee employee){

    //    return employeeRepo.delete(employee);

   // }



   // public boolean deleteEmployeeById(int id){

   //     return employeeRepo.deleteById(id);

    }


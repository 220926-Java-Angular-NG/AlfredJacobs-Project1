package com.revature.controllers;

import com.revature.models.Employee;
import com.revature.models.Ticket;
import com.revature.services.EmployeeService;

import io.javalin.http.Handler;

import java.util.ArrayList;
import java.util.List;

public class EmployeeController {

     EmployeeService service;

    public EmployeeController(){
        service = new EmployeeService();
    }
    public EmployeeController(EmployeeService employeeService){
        this.service = employeeService;
    }

    //create handlers

    public Handler createNewEmployee = context -> {

        // grab the object from the request body (postman)
        // send the incoming user to our service, so it can
        // reach out to our repo layer and execute the request

        Employee employee = context.bodyAsClass(Employee.class); //change the jason from postman to an object
        int id = service.createEmployee(employee);

        if (id > 0) {
            //valid number ( it represents the primary key )
            employee.setId(id);
            context.json(employee).status(200);
        } else {
            //something went wrong
            context.result("Employee not created").status(400);
        }


    };
    //read

    //all
    public Handler getAllTicketsById = context -> {

        String param = context.pathParam("creator_id");
        //we are going to wrap this logic in a try catch

        try {
            int id = Integer.parseInt(param);
             Employee employee = (Employee) service.getAllTickets(id);
                  //  service.getAllTickets(id);
            if (employee != null) {
                context.json(employee).status(202);
            } else {
                context.result("Ticket not found").status(400);
            }


        } catch (NumberFormatException nFMException) {
            System.out.println(nFMException.getMessage());
        }
    };

    //by id

//    public Handler getUserById = context -> {
//
//        String param = context.pathParam("id");
//        //we are going to wrap this logic in a try catch
//
//        try{
//            int id = Integer.parseInt(param);
//            User user = service.getUserById(id);
//
//            if(user != null){
//                context.json(user).status(202);
//            }else {
//                context.result("User not found").status(400);
//            }
//        }catch(NumberFormatException nFMException){
//            System.out.println(nFMException.getMessage());
//        }
//    };
    //update

    public Handler employeeLogin = context -> {
        Employee employee = context.bodyAsClass(Employee.class); //model of what is on database
        employee = service.loginEmployee(employee);

        if(employee != null){
            context.json(employee).status(202);
        }else{
            context.result("Could not login user").status(400);
        }
    };

    //delete
//    public Handler deleteUser = context -> {
//        User user = context.bodyAsClass(User.class);
//        if(user != null){
//            context.status(200).json(service.deleteUser(user));
//        }else{
//            context.status(400).result("Could not delete user");
//        }
//    };
//
//    public Handler deleteUserById = context -> {
//        String param = context.pathParam("id");
//
//        try{
//            //this is the id that we are getting from our url
//            int id = Integer.parseInt(param);
//
//            context.json(service.deleteUserById(id)).status(202);
//        }catch (NumberFormatException nFMException){
//            System.out.println(nFMException.getMessage());
//        }
//    };
//}

}

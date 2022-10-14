package com.revature.controllers;

import com.revature.models.Ticket;
import com.revature.models.Manager;

import com.revature.services.ManagerService;
import io.javalin.http.Handler;


public class ManagerController {

    ManagerService service;

    public ManagerController(){
        service = new ManagerService();
    }
    public ManagerController(ManagerService managerService){
        this.service = managerService;
    }

    //create handlers

    public final Handler createNewManager = context -> {

        // grab the object from the request body (postman)
        // send the incoming user to our service, so it can
        // reach out to our repo layer and execute the request

        Manager manager = context.bodyAsClass(Manager.class); //change the jason from postman to an object
        int id = service.createManager(manager);

        if (id > 0) {
            //valid number ( it represents the primary key )
            manager.setId(id);
            context.json(manager).status(200);
        } else {
            //something went wrong
            context.result("Username Already in Use").status(400);
        }


    };
    //read

    //all
    public Handler getAllTickets = context -> {
        context.json(service.getAllTickets());
    };
    //alll144
    public Handler getAllTicketsPending = context -> {

        String param = context.pathParam("creator_id");
        //we are going to wrap this logic in a try catch

        try {
            int id = Integer.parseInt(param);
            Manager manager = (Manager) service.getAllTickets();
            //  service.getAllTickets(id);
            if (manager != null) {
                context.json(manager).status(202);
            } else {
                context.result("Not Authorized").status(400);
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

    public Handler managerLogin = context -> {
        Manager manager = context.bodyAsClass(Manager.class); //model of what is on database
        manager = service.loginManager(manager);

        if(manager != null){
            context.json(manager).status(202);
        }else{
            context.result("Could not login user").status(400);
        }
    };
    public Handler approveTicket = context -> {

            Ticket ticket = context.bodyAsClass(Ticket.class); //model of what is on database
            ticket = service.approveTickets(ticket);
            if(ticket != null){
                context.json(ticket).status(202);
            }else{
                context.result("Could not update user").status(400);
            }

    };
    public Handler denyTicket = context -> {

        Ticket ticket = context.bodyAsClass(Ticket.class); //model of what is on database
        ticket = service.denyTickets(ticket);
        if(ticket != null){
            context.json(ticket).status(202);
        }else{
            context.result("Could not update user").status(400);
        }

    };

}

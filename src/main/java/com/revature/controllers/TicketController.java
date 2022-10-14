package com.revature.controllers;

import com.revature.models.Ticket;
import com.revature.services.TicketService;
import io.javalin.http.Handler;

public class TicketController {

    TicketService service;

    public TicketController(){
        service = new TicketService();
    }
    public TicketController(TicketService ticketService){
        this.service = ticketService;
    }

    //create handlers

    public Handler createNewTickets = context -> {

        // grab the object from the request body (postman)
        // send the incoming user to our service, so it can
        // reach out to our repo layer and execute the request

        Ticket ticket = context.bodyAsClass(Ticket.class); //change the jason from postman to an object
        int id = service.createTicket(ticket);

        if(id>0){
            //valid number ( it represents the primary key )
            ticket.setId(id);
            context.json(ticket).status(200);
        }else{
            //something went wrong
            context.result("Ticket not created").status(400);
        }


    };
}

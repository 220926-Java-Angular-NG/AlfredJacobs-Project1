package com.revature.services;

import com.revature.models.Employee;
import com.revature.models.Ticket;
import com.revature.repos.EmployeeRepo;
import com.revature.repos.TicketRepo;

public class TicketService {

    private TicketRepo ticketRepo;



    // Constructors

    public TicketService() {

        ticketRepo = new TicketRepo();

    }



    public TicketService(TicketRepo ticketRepo) {

        this.ticketRepo = ticketRepo;

    }



    // Methods

    // Create a ticket

    public int createTicket(Ticket ticket){

        return ticketRepo.create(ticket);

    }
}

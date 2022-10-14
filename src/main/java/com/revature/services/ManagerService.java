package com.revature.services;

import com.revature.models.Employee;
import com.revature.models.Manager;
import com.revature.models.Ticket;
import com.revature.repos.ManagerRepo;

import java.util.List;

public class ManagerService {
    private ManagerRepo managerRepo;



    // Constructors

    public ManagerService() {

        managerRepo = new ManagerRepo();

    }



    public ManagerService(ManagerRepo managerRepo) {

        this.managerRepo = managerRepo;

    }



    // Methods

    // Create a user

    public int createManager(Manager manager){

        return managerRepo.create(manager);

    }
    //Login Manager
    public Manager loginManager(Manager manager){

        return managerRepo.login(manager);

    }


    // Read All tickets

    public List<Ticket> getAllTickets(){

        return managerRepo.getAll();

    }

    //Read All pending tickets

    public List<Ticket> getAllPendingTickets(){

        return managerRepo.getAllPending();

    }
    //Approve Tickets
    public Ticket approveTickets(Ticket ticket){

        return managerRepo.approveTicket(ticket);
    }

    //Deny Tickets
    public Ticket denyTickets(Ticket ticket){

        return managerRepo.denyTicket(ticket);
    }
}

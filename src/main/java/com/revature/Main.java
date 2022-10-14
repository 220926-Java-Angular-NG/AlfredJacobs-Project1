package com.revature;
import com.revature.controllers.ManagerController;
import com.revature.controllers.TicketController;
import com.revature.controllers.EmployeeController;
import com.revature.models.Employee;
import com.revature.services.ManagerService;
import com.revature.services.TicketService;
import com.revature.services.EmployeeService;
import io.javalin.Javalin;

public class Main {
    public static void main(String[] args){

        Javalin app = Javalin.create().start(8080);
        EmployeeService employeeService = new EmployeeService();
        EmployeeController employeeController = new EmployeeController(employeeService);

        app.post("/employees", employeeController.createNewEmployee);
        app.get("/er-tickets/{id}", employeeController.getAllTicketsById);
        app.put("/employees",employeeController.employeeLogin);


        TicketService ticketService = new TicketService();
        TicketController ticketController = new TicketController(ticketService);


        app.post("/tickets", ticketController.createNewTickets);


        ManagerService managerService = new ManagerService();
        ManagerController managerController = new ManagerController(managerService);

        app.post("/manager", managerController.createNewManager);
        app.get("/tickets/all", managerController.getAllTickets);
        app.get("/tickets/pending", managerController.getAllTicketsPending);
        app.put("/tickets/approve/{ticket_id}", managerController.approveTicket);
        app.put("/tickets/deny/{ticket_id}", managerController.denyTicket);
        app.put("/employees/manager",managerController.managerLogin);
    }
}
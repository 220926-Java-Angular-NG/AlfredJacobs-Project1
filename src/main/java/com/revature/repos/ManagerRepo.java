package com.revature.repos;

import com.revature.models.Employee;
import com.revature.models.Manager;
import com.revature.models.Ticket;
import com.revature.utils.CRUDDaoInterface;
import com.revature.utils.ConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManagerRepo implements CRUDDaoInterface<Manager> {

    public Connection conn;

    private static final Logger LOGGER = LoggerFactory.getLogger(Manager.class);


    public ManagerRepo() {
//        String url = "jdbc:postgresql://localhost:5432/postgres?currentSchema=flash-card-demo";
//        String username = "postgres";
//        String password = "ABC132:";

        try {
            conn = ConnectionManager.getConnection();

        } catch (SQLException sqlException) {

            //System.out.println(sqlException.getMessage());
            LOGGER.error(sqlException.getMessage());
        }

    }
    @Override
    public int create(Manager manager) {


        try {
            String sql = "INSERT INTO employees (id, first_name, last_name, user_name, pass_word, job_title, status) VALUES (default,?,?,?,?,?,default)";

            //feeds it to the database management system
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(2, manager.getFirstname());
            pstmt.setString(3, manager.getLastname());
            pstmt.setString(4, manager.getUsername());
            pstmt.setString(5, manager.getPassword());
            pstmt.setString(6, manager.getJob_title());
            // this executes the sql statement above
            pstmt.executeUpdate();

            // this gives us a result set of the row that was just created
            ResultSet rs = pstmt.getGeneratedKeys();

            // the cursor is right in front of the first (or only) element in our results set.
            // calling rs.next() iterates us into the first row.
            rs.next();

            return rs.getInt("id");

        }catch(SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return 0;
    }

    @Override
    public List<Ticket> getAll(int id) {
        return null;
    }

    public List<Ticket> getAll() {
        List<Ticket> tickets = new ArrayList<>();

        try {

            String sql = "SELECT * FROM tickets";

            PreparedStatement pstmt = conn.prepareStatement(sql);


            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Ticket ticket = new Ticket();
                ticket.setId(rs.getInt("ticket_id"));
                ticket.setAmount(rs.getDouble("amount"));
                ticket.setDescription(rs.getString("description"));
                ticket.setStatus(rs.getString("status"));

                tickets.add(ticket);

            }
            return tickets;

        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }

        return null;
    }

    @Override
    public List<Ticket> getAllPending() {

        List<Ticket> tickets = new ArrayList<>();

        try {
            String sql = "SELECT * FROM tickets WHERE status = 'Pending'";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {

                Ticket ticket = new Ticket();

                ticket.setId(rs.getInt("ticket_id"));
                ticket.setAmount(rs.getDouble("amount"));
                ticket.setDescription(rs.getString("description"));
                ticket.setStatus(rs.getString("status"));
                ticket.setCreatorId(rs.getInt("creator_id"));

                tickets.add(ticket);
            }

            return tickets;

        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return null;
    }

    @Override
    public Manager getById(int id) {
        return null;
    }

    @Override
    public Manager login(Manager manager) {


        try {
            // we are updating a row at a specific id
            String sql = "UPDATE employees SET status = ? WHERE user_name = ? and pass_word = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, "Logged In");
            pstmt.setString(2, manager.getUsername());
            pstmt.setString(3, manager.getPassword());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();

            while (rs.next()) {
                manager.setStatus(rs.getString("status"));
            }
            return manager;

        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());

            return null;
        }
    }

    @Override
    public boolean delete(Manager manager) {
        return false;
    }

    public Ticket approveTicket(Ticket ticket) {
        try {
            String sql = "UPDATE tickets SET status = ? WHERE ticket_id = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(2, ticket.getId());
            pstmt.setString(1, "Approved");
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();

            while (rs.next()) {
                ticket.setStatus(rs.getString("status"));
            }

            return ticket;

        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());


        }
        return null;
    }

    public Ticket denyTicket(Ticket ticket) {
        try {
            String sql = "UPDATE tickets SET status = ? WHERE ticket_id = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(2, ticket.getId());
            pstmt.setString(1, "Denied");
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();

            while (rs.next()) {
                ticket.setStatus(rs.getString("status"));
            }

            return ticket;

        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());


        }
        return null;
    }
}
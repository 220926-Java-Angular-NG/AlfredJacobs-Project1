package com.revature.repos;

import com.revature.models.Ticket;
import com.revature.utils.CRUDDaoInterface;
import com.revature.utils.ConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketRepo implements CRUDDaoInterface<Ticket> {

    public Connection conn;

    private static final Logger LOGGER = LoggerFactory.getLogger(TicketRepo.class);


    public TicketRepo() {


        try {
            conn = ConnectionManager.getConnection();
            System.out.println(conn.getSchema());
        } catch (SQLException sqlException) {

            //System.out.println(sqlException.getMessage());
            LOGGER.error(sqlException.getMessage());
        }

    }

    @Override
    public int create(Ticket ticket) {
        String sql = "INSERT INTO tickets (ticket_id, amount, description, status, creator_id) VALUES(default,?,?,default,?); ";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setDouble(2, ticket.getAmount());
            pstmt.setString(3, ticket.getDescription());
            pstmt.setInt(5, ticket.getCreatorId());


            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            rs.next();

            return rs.getInt("ticket_id");

        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return 0;
    }

    @Override
    public List<Ticket> getAll(int id) {
        return null;
    }

    @Override
    public List<Ticket> getAll() {

        List<Ticket> tickets = new ArrayList<>();
        try {
            String sql = "SELECT * FROM tickets ";
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
    public List<Ticket> getAllPending() {
        return null;
    }

    @Override
    public Ticket getById(int id) {
        return null;
    }

    @Override
    public Ticket login(Ticket ticket) {
        return null;
    }


    public Ticket update(Ticket ticket) {
        return null;
    }

    @Override
    public boolean delete(Ticket ticket) {
        return false;
    }

}

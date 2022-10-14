package com.revature.repos;
import com.revature.models.Employee;
import com.revature.models.Manager;
import com.revature.models.Ticket;
import com.revature.utils.CRUDDaoInterface;
import com.revature.utils.ConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepo implements CRUDDaoInterface<Employee> {

    public Connection conn;

    //need a logger for each class
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeRepo.class);

    public EmployeeRepo() {
//
//            String url = "jdbc:postgresql://localhost:5432/postgres?currentSchema=flash-card-demo";
//            String username = "postgres";
//            String password = "ABC132:";


        // Note:certain methods throw errors because there are different instances of what can go wrong.
        // in order to handle those errors in a way that saves the application from crashing
        // we wrap those methods or blocks of code in a "try{}catch(){}" block

        try {
            // this is the code that can throw an error.
            conn = ConnectionManager.getConnection();

            //System.out.println(conn.getSchema());
        } catch (SQLException sqlException) {
            //System.out.println(sqlException.getMessage());
            LOGGER.error(sqlException.getMessage());

        }

    }


    @Override
    public int create(Employee employee) {

        try {
            String sql = "INSERT INTO users (id, first_name, last_name, user_name, pass_word, job_title, status) VALUES (default,?,?,?,?,default,default)";

            //feeds it to the database management system
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(2, employee.getFirstname());
            pstmt.setString(3, employee.getLastname());
            pstmt.setString(4, employee.getUsername());
            pstmt.setString(5, employee.getPassword());

            // this executes the sql statement above
            pstmt.executeUpdate();

            // this gives us a result set of the row that was just created
            ResultSet rs = pstmt.getGeneratedKeys();

            // the cursor is right in front of the first (or only) element in our results set.
            // calling rs.next() iterates us into the first row.
            rs.next();

            return rs.getInt("id");

        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return 0;
    }


    @Override
    public List<Ticket> getAll(int id) {
        List<Ticket> tickets = new ArrayList<>();

        try {

            String sql = "SELECT * FROM tickets WHERE creator_id = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);

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
    public List<Ticket> getAll() {
        return null;
    }

    @Override
    public Employee getById(int id) {
        try {

            String sql = "SELECT * FROM employees WHERE id = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();

            // we are returning a user therefore we have to create a new instance of a user from the database
            Employee employee = new Employee();

            while (rs.next()) {
                employee.setId(rs.getInt("id"));
                employee.setFirstname(rs.getString("first_name"));
                employee.setLastname(rs.getString("last_name"));
                employee.setUsername(rs.getString("user_name"));
                employee.setPassword(rs.getString("pass_word"));
                employee.setJob_title(rs.getString("job_title"));
                employee.setStatus(rs.getString("status"));
            }

            return employee;

        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }

        return null;
    }

    @Override
    public Employee login(Employee employee) {

        try {
            // we are updating a row at a specific id
            String sql = "UPDATE employees SET status = ? WHERE user_name = ? and pass_word = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, "Logged In");
            pstmt.setString(2, employee.getUsername());
            pstmt.setString(3, employee.getPassword());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();

            while (rs.next()) {
                employee.setStatus(rs.getString("status"));
            }
            return employee;

        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());

            return null;
        }
    }

    @Override
    public boolean delete(Employee employee) {
        //Delete from tablename where id = ?
        try {

            String sql = "DELETE FROM employees WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, employee.getId());

            //pstmt.execute() returns a boolean
            //it returns false if the executed statement returns void

            return pstmt.execute();
        } catch (SQLException sqlException) {

            System.out.println(sqlException.getMessage());
        }

        return false;
    }

    public boolean deleteEmployeeById(int id) {
        return delete(getById(id));
    }

    @Override
    public List<Ticket> getAllPending() {

    return null;
    }
}
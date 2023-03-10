package DAO;

import Models.Event;
import Models.Ticket;
import Models.User;
import Util.ConnectionSingleton;
import org.eclipse.jetty.util.DateCache;
import org.h2.command.Prepared;
import org.h2.util.NetworkConnectionInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CantusDAO {

    /*
     *
     * Class Methods
     *
     */

    public User registerUser(String email, String password) {

        Connection connection = ConnectionSingleton.getConnection();

        try {

            String sql = "INSERT INTO users (email, password) VALUES (?, ?);";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, email);
            statement.setString(2, password);
            statement.executeUpdate();

            ResultSet pkeyResultSet = statement.getGeneratedKeys();
            if(pkeyResultSet.next()){
                int id = pkeyResultSet.getInt("id");
                return new User(id, email, password);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;

    }

    public User attemptLogin(String username, String password) {

        Connection connection = ConnectionSingleton.getConnection();

        try {

            String sql = "SELECT * FROM users WHERE email = ? AND password = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                User account = new User(rs.getInt("id"),
                        rs.getString("email"),
                        rs.getString("password"));
                return account;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;

    }

    public boolean deactivateUser(User user) {

        Connection connection = ConnectionSingleton.getConnection();

        try {

            String sql = "DELETE FROM users WHERE id = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, user.getID());

            int i = statement.executeUpdate();
            if (i == 1) return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;

    }

    public List<Event> getAllEvents() {

        Connection connection = ConnectionSingleton.getConnection();

        try {

            List<Event> events = new ArrayList<>();
            String sql = "SELECT * FROM events;";
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Event event = new Event(rs.getInt("id"), rs.getString("title"), rs.getString("eventType"),
                        rs.getInt("standardSeating"), rs.getFloat("standardPrice"));
                events.add(event);
            }
            return events;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;

    }

    public Event getEventByID(int id) {

        Connection connection = ConnectionSingleton.getConnection();

        try {

            String sql = "SELECT * FROM events WHERE id = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                Event event = new Event(rs.getInt("id"), rs.getString("title"), rs.getString("eventType"),
                        rs.getInt("standardSeating"), rs.getFloat("standardPrice"));
                return event;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;

    }

    public Event addEvent(Event event) {

        Connection connection = ConnectionSingleton.getConnection();

        try {

            String sql = "INSERT INTO events (title, eventType, standardSeating, standardPrice) VALUES (?, ?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, event.getTitle());
            statement.setString(2, event.getEventType());
            statement.setInt(3, event.getStandardSeating());
            statement.setDouble(4, event.getStandardPrice());
            statement.executeUpdate();

            ResultSet pkeyResultSet = statement.getGeneratedKeys();
            if(pkeyResultSet.next()) {
                int id = pkeyResultSet.getInt("id");
                return new Event(id, event.getTitle(), event.getEventType(), event.getStandardSeating(), event.getStandardPrice());
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;

    }

    public Event updateEvent(Event event) {

        Connection connection = ConnectionSingleton.getConnection();

        try {

            String sql = "UPDATE events SET title = ? WHERE id = ?;";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, event.getTitle());
            statement.setInt(2, event.getID());
            int i = statement.executeUpdate();

            if (i == 1) {
                return event;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;

    }

    public List<Ticket> getUserTickets(int userID) {

        Connection connection = ConnectionSingleton.getConnection();

        try {

            List<Ticket> tickets = new ArrayList<>();
            String sql = "SELECT * FROM tickets WHERE user_id = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userID);

            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                Ticket ticket = new Ticket(rs.getInt("ticket_id"), rs.getInt("event_id"), rs.getInt("user_id"));
                tickets.add(ticket);
            }
            return tickets;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;

    }

    public Ticket buyTicket(Ticket ticket) {

        Connection connection = ConnectionSingleton.getConnection();

        try {

            String sql = "INSERT INTO tickets (event_id, user_id) VALUES (?, ?);";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, ticket.getEventID());
            statement.setInt(2, ticket.getUserID());
            statement.executeUpdate();

            ResultSet pkeyResultSet = statement.getGeneratedKeys();
            if(pkeyResultSet.next()){
                int id = pkeyResultSet.getInt("ticket_id");
                return new Ticket(id, ticket.getEventID(), ticket.getUserID());
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;

    }

}

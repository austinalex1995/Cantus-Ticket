package Service;

import DAO.CantusDAO;
import Models.Event;
import Models.Ticket;
import Models.User;

import java.util.List;

public class CantusService {

    /*
     *
     * Member Variables
     *
     */

    private CantusDAO mDAO;



    /*
     *
     * Constructor
     *
     */

    public CantusService() {
        mDAO = new CantusDAO();
    }

    public CantusService(CantusDAO dao) {
        mDAO = dao;
    }



    /*
     *
     * Class Methods
     *
     */

    public User register(User user) {

        if (user.getEmail().length() == 0 || user.getPassword().length() < 0) return null;

        return mDAO.registerUser(user.getEmail(), user.getPassword());

    }

    public User attemptLogin(User user) {

        if (user.getEmail().length() == 0 || user.getPassword().length() == 0) return null;

        return mDAO.attemptLogin(user.getEmail(), user.getPassword());

    }

    public boolean deactivateAccount(User user) {

        if (user.getEmail().length() == 0 || user.getPassword().length() == 0 || user.getID() == -1) return false;

        return mDAO.deactivateUser(user);

    }

    public List<Event> getAllEvents() {

        return mDAO.getAllEvents();

    }

    public Event getEventByID(int id) {

        if (id == -1) return null;

        return mDAO.getEventByID(id);

    }

    public Event addEvent(Event event) {

        if (event.getTitle().length() == 0 || event.getEventType().length() == 0
                || event.getStandardSeating() < 1 || event.getStandardPrice() < 0.0) {
            return null;
        }

        return mDAO.addEvent(event);

    }

    public Event updateEvent(Event event) {

        if (event.getTitle().length() == 0 || event.getEventType().length() == 0
                || event.getStandardSeating() < 1 || event.getStandardPrice() < 0.0) {
            return null;
        }

        return mDAO.updateEvent(event);

    }

    public List<Ticket> getUserTickets(int userID) {

        if (userID == -1) return null;

        return mDAO.getUserTickets(userID);

    }

    public Ticket buyTicket(Ticket ticket) {

        if (ticket.getEventID() == -1 || ticket.getUserID() == -1) return null;

        return mDAO.buyTicket(ticket);

    }

}

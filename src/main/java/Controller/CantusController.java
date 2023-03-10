package Controller;

import Models.Event;
import Models.Ticket;
import Models.User;
import Service.CantusService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

public class CantusController {

    /*
     *
     * Member Variables
     *
     */

    CantusService mService;



    /*
     *
     * Constructor
     *
     */

    public CantusController() {

        mService = new CantusService();

    }



    /*
     *
     * Class Methods
     *
     */

    public Javalin startAPI() {

        Javalin app = Javalin.create();
        app.post("/register", this::register);
        app.get("/login", this::attemptLogin);
        app.delete("/deactivation", this::deactivateAccount);
        app.get("/events", this::getAllEvents);
        app.get("/events/<eventId>", this::getEventById);
        app.post("/events", this::postEvent);
        app.patch("/events", this::updateEvent);
        app.post("/tickets", this::buyTicket);
        app.get("/tickets/<user_id>", this::getUserTickets);
        return app;

    }

    private void register(Context context) throws JsonProcessingException {

        String body = context.body();
        ObjectMapper om = new ObjectMapper();
        User account = om.readValue(body, User.class);
        User registeredUser = mService.register(account);

        if (registeredUser == null) {
            context.status(400);
        } else {
            context.json(om.writeValueAsString(registeredUser));
            context.status(200);
        }

    }

    private void attemptLogin(Context context) throws JsonProcessingException {

        String body = context.body();
        ObjectMapper om = new ObjectMapper();
        User account = om.readValue(body, User.class);
        User returnedAccount = mService.attemptLogin(account);
        if (returnedAccount == null) {
            context.status(401);
        } else {
            context.json(om.writeValueAsString(returnedAccount));
            context.status(200);
        }

    }

    private void deactivateAccount(Context context) throws JsonProcessingException {

        String body = context.body();
        ObjectMapper om = new ObjectMapper();
        User account = om.readValue(body, User.class);
        boolean didDeactivate = mService.deactivateAccount(account);
        if (!didDeactivate) {
            context.status(400);
        } else {
            context.status(200);
        }

    }

    private void getAllEvents(Context context) throws JsonProcessingException {

        ObjectMapper om = new ObjectMapper();
        List<Event> returnedMessages = mService.getAllEvents();
        if (returnedMessages == null) {
            context.status(400);
        } else {
            context.json(om.writeValueAsString(returnedMessages));
            context.status(200);
        }

    }

    private void getEventById(Context context) throws JsonProcessingException {

        ObjectMapper om = new ObjectMapper();
        String idAsString = context.pathParam("eventId");
        int id = (int) Integer.valueOf(idAsString);

        Event returnedEvent = mService.getEventByID(id);
        if (returnedEvent == null) {
            context.status(400);
        } else {
            context.json(om.writeValueAsString(returnedEvent));
            context.status(200);
        }

    }

    private void postEvent(Context context) throws JsonProcessingException {

        String body = context.body();
        ObjectMapper om = new ObjectMapper();
        Event event = om.readValue(body, Event.class);
        Event returnedEvent = mService.addEvent(event);
        if (returnedEvent == null) {
            context.status(400);
        } else {
            context.json(om.writeValueAsString(returnedEvent));
            context.status(200);
        }

    }

    private void updateEvent(Context context) throws JsonProcessingException {

        String body = context.body();
        ObjectMapper om = new ObjectMapper();
        Event event = om.readValue(body, Event.class);
        Event updatedEvent = mService.updateEvent(event);
        if (updatedEvent == null) {
            context.status(400);
        } else {
            context.json(om.writeValueAsString(updatedEvent));
            context.status(200);
        }

    }

    private void buyTicket(Context context) throws JsonProcessingException {

        String body = context.body();
        ObjectMapper om = new ObjectMapper();
        Ticket ticket = om.readValue(body, Ticket.class);
        Ticket returnedTicket = mService.buyTicket(ticket);
        if (returnedTicket == null) {
            context.status(400);
        } else {
            context.json(om.writeValueAsString(returnedTicket));
            context.status(200);
        }

    }

    private void getUserTickets(Context context) throws JsonProcessingException {

        ObjectMapper om = new ObjectMapper();
        String idAsString = context.pathParam("user_id");
        int id = (int) Integer.valueOf(idAsString);
        List<Ticket> returnedTickets = mService.getUserTickets(id);
        if (returnedTickets == null) {
            context.status(400);
        } else {
            context.json(om.writeValueAsString(returnedTickets));
            context.status(200);
        }

    }

}

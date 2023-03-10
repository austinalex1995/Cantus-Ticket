package Models;

public class Ticket {

    /*
     *
     * Member Variables
     *
     */

    private int mID;
    private int mEventID;
    private int mUserID;



    /*
     *
     * Constructor
     *
     */

    public Ticket() {

        mID = -1;
        mEventID = -1;
        mUserID = -1;

    }

    public Ticket(int eventID, int userID) {

        mID = -1;
        mEventID = eventID;
        mUserID = userID;

    }

    public Ticket(int id, int eventID, int userID) {

        mID = id;
        mEventID = eventID;
        mUserID = userID;

    }



    /*
     *
     * Class Methods
     *
     */

    public int getID() {

        return mID;

    }

    public int getEventID() {

        return mEventID;

    }

    public void setEventID(int eventID) {

        mEventID = eventID;

    }

    public int getUserID() {

        return mUserID;

    }

    public void setUserID(int userID) {

        mUserID = userID;

    }

    @Override
    public String toString() {
        return "Ticket{id = " + mID + ", eventID = " + mEventID + ", userID = " + mUserID + "}";
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Ticket t = (Ticket) obj;
        return (t.getID() == mID && t.getEventID() == mEventID && t.getUserID() == mUserID);

    }

}

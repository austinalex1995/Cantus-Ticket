package Models;

public class Event {

    /*
     *
     * Member Variables
     *
     */

    private int mID;
    private String mTitle;
    private String mEventType;
    private int mStandardSeating;
    private double mStandardPrice;



    /*
     *
     * Constructor
     *
     */

    public Event() {

        mID = -1;
        mTitle = "";
        mEventType = "";
        mStandardSeating = -1;
        mStandardPrice = 0.0;

    }

    public Event(String title, String eventType, int standardSeating, double standardPrice) {

        mID = -1;
        mTitle = title;
        mEventType = eventType;
        mStandardSeating = standardSeating;
        mStandardPrice = standardPrice;

    }

    public Event(int id, String title, String eventType, int standardSeating, double standardPrice) {

        mID = id;
        mTitle = title;
        mEventType = eventType;
        mStandardSeating = standardSeating;
        mStandardPrice = standardPrice;

    }



    /*
     *
     * Class Methods
     *
     */

    public int getID() {

        return mID;

    }

    public void setID(int id) {

        mID = id;

    }

    public String getTitle() {

        return mTitle;

    }

    public void setTitle(String title) {

        mTitle = title;

    }

    public String getEventType() {

        return mEventType;

    }

    public void setEventType(String event_type) {

        mEventType = event_type;

    }

    public int getStandardSeating() {

        return mStandardSeating;

    }

    public void setStandardSeating(int standard_seating) {

        mStandardSeating = standard_seating;

    }

    public double getStandardPrice() {

        return mStandardPrice;

    }

    public void setStandardPrice(float newPrice) {

        mStandardPrice = newPrice;

    }

    @Override
    public String toString() {

        return "Event{id = " + mID + ", title = " + mTitle + ", event_type = " + mEventType
                + ", standard_seating = " + mStandardSeating + ", standard_price = " + mStandardPrice + "}";

    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Event e = (Event) obj;
        return (e.getID() == mID && e.getTitle().equals(mTitle) && e.getEventType().equals(mEventType)
                && e.getStandardSeating() == mStandardSeating && e.getStandardPrice() == mStandardPrice);

    }

}

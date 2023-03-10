package Models;

public class User {

    /*
     *
     * Member Variables
     *
     */

    private int mID;
    private String mEmail;
    private String mPassword;



    /*
     *
     * Constructor
     *
     */

    public User() {

        mID = -1;
        mEmail = "";
        mPassword = "";

    }

    public User(int id, String email, String password) {

        mID = id;
        mEmail = email;
        mPassword = password;

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

    public void setEmail(String email) {

        mEmail = email;

    }

    public String getEmail() {

        return mEmail;

    }

    public String getPassword() {

        return mPassword;

    }

    public void setPassword(String password) {

        mPassword = password;

    }

    @Override
    public String toString() {
        return "User{id = " + mID + ", email = " + mEmail + ", password = " + mPassword + "}";
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        User u = (User) obj;
        return (u.getID() == mID && u.getEmail().equals(mEmail) && u.getPassword().equals(mPassword));

    }

}

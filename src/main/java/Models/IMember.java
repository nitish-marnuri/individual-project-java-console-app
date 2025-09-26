package Models;

/*
This is a generic interface for representing a member in the system.
It has a defined structure for any type member (e.g., student, staff, etc.),
Keeping in mind that each member will have basic properties such as ID, name, email, and phone.

This class follows the SOLID design principles
defining only essential properties and methods for a member and
focusing solely on member-specific methods keeping it lightweight and focused.
*/

public interface IMember {

    //region Abstract implementation of getters to fetch the value of each member variable.
    int getId(); // Gets the unique identifier for the member.

    String getName();// Gets the name for the member.

    String getEmail();// Gets the email address for the member.

    String getPhone();// Gets the phone number for the member.
    //endregion


    //region Abstract implementation of setters to set the value of each member variable.
    void setId(int id);// Sets the unique identifier for the member.

    void setName(String name); //Sets the name of the member.

    void setEmail(String email); //Sets the email address of the member.

    void setPhone(String phone);//Sets the phone number of the member.
    //endregion
}

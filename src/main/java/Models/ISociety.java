package Models;

/*
This is a generic interface for representing a society in the system.
It has a defined structure for any type society (e.g., student, staff, etc.),
Keeping in mind that each society will have basic properties
such as ID, society name, society description, and a functionality to view its members.

NOTE: We have not provided the member list here as each society may have society specific members.

This class follows the SOLID design principles
defining only essential properties and methods for a society and
focusing solely on society-specific methods keeping it lightweight and focused.
*/
public interface ISociety {

    // Method to view or list all members of the society.
    // Implementations will specify how societies are displayed.
    void viewMembers();


    //region Abstract implementation of getters to fetch the value of each member variable.
    int getId(); // Gets the unique identifier of the society

    String getName();// Gets the name of the society.

    String getDescription();// Gets the description of the society.
    //endregion


    //region Abstract implementation of setters to set the value of each member variable.
    void setId(int id); // Sets the unique identifier for the society.

    void setName(String name); // Sets the name for the society.

    void setDescription(String description); // Sets the description for the society.
    //endregion
}

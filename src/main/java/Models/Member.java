package Models;

/*
The Member class is an abstract base implementation of the IMember interface
serving as a base class for different types of members
so that any specific member class can be extended from it ensuring extensibility and maintainability.
Examples for extensible classes: Society Member, Guest Member, Organizing Member.

This class follows the SOLID design principles
allowing the high-level modules focus on IMember interface providing flexibility
and open to extension by specific member types without modifying this class directly.
*/

public abstract class Member implements IMember {
    // Protected member variables for common member properties.
    protected int id;           // Unique identifier for the member

    protected String name;      // Name of the member

    protected String email;     // Email address of the member

    protected String phone;     // Phone number of the member

    //Complete implementation of getters to fetch the member variables.
    public int getId() { return id; } // Gets the unique identifier of the member.

    public String getName() { return name; } // Gets the name of the member.

    public String getEmail() { return email; } // Gets the email address of the member.

    public String getPhone() { return phone; } // Gets the phone number of the member.


    //Complete implementation of getters to fetch the member variables.
    public void setId(int id) { this.id = id; } // Sets the unique identifier of the member.

    public void setName(String name) { this.name = name; } // Sets the name of the member.

    public void setEmail(String email) { this.email = email; } // Sets the email address of the member.

    public void setPhone(String phone) { this.phone = phone; } // Sets the phone number of the member.
}

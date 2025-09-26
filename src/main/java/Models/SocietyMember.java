package Models;

/*
 The SocietyMember class extends the Member class and implements the ISocietyMember interface,
 representing a member with a specific role in a society.

 This class also shows multi-level and multiple inheritance by extending from abstract Member class
 and providing implementation for ISocietyMember interface.

 It follows the SOLID principles by
 handling only the responsibility for society-specific member details, particularly the role,
 makes sure SocietyMember can be extended with more properties if needed without modifying existing code and
 making the Higher-level classes only depend on the ISocietyMember interface rather than  this specific implementation.
*/

public class SocietyMember extends Member implements ISocietyMember{
    // Private member property for members specific to a society group.
    private Enum<UserRoles> role; //to store the role of the society member from enum (i.e, ADMIN, MEMBER)

    //Constructor to create an instance of the Society Member with the provided
    // member id, full name and role.
    public SocietyMember(int id, String name, Enum<UserRoles> role){
        this.id=id;
        this.name=name;
        this.role=role;
    }

    //Constructor to create an instance of the Society Member with the provided
    //member id, full name, role and email address.
    public SocietyMember(int id, String name,String email, Enum<UserRoles> role){
        this.id=id;
        this.name=name;
        this.role=role;
        this.email=email;
    }

    // Gets the role of the society member.
    public Enum<UserRoles> getRole(){
        return role;
    }

    // Sets the role of the society member.
    public void setRole(Enum<UserRoles> role){
        this.role=role;
    }
}

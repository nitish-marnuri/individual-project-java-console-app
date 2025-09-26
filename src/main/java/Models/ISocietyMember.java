package Models;

/*
This interface is used for members within a society who have specific roles
by extending IMember interface.

This follows the SOLID design principles keeping each interface lightweight
by ensuring each interface has a single, focused purpose.
*/

public interface ISocietyMember extends IMember{

    //Abstract implementation to get the role of a member (i.e, ADMIN, MEMBER).
    Enum<UserRoles> getRole();

    //Abstract implementation to set the role of a member (i.e, ADMIN, MEMBER).
    void setRole(Enum<UserRoles> role);
}

package Models;

import java.util.List;

/*
The IUniversitySociety interface extends ISociety by adding functionality specific to university society.
Adding methods for managing members within a society, including adding, removing, retrieving,
and setting the list of society members.

This also introduces a society specific field for storing the list of members of type ISocietyMember.

This follows SOLID design principles
as it defines only the essential member-management methods using ISocietyMember
specific to university societies.
*/

public interface IUniversitySociety extends ISociety {

    void addMember(ISocietyMember member); // Adds a member to the university society.

    void removeMember(ISocietyMember member); // Removes a member from the university society.

    List<ISocietyMember> getMemberList(); // Gets the list of all members in the university society.

    void setMemberList(List<ISocietyMember> memberList); // Sets the list of members in the university society.

    ISocietyMember getMemberById(int id); // Finds and returns a member by their ID.
}

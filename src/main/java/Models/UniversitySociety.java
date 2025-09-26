package Models;

import java.security.InvalidParameterException;
import java.util.List;

/*
 The SocietyMember class extends the Society class and implements the IUniversitySociety interface,
 representing a society with a specific role in a society.

 This class also shows multi-level and multiple inheritance by extending from abstract Society class
 and providing implementation for IUniversitySociety interface.

 It follows the SOLID design principles by
 focusing solely on managing members of the university society,
 makes sure UniversitySociety can be extended with more properties if needed without modifying existing code and
 making the Higher-level classes only depend on the IUniversitySociety interface rather than this specific implementation.
*/

public class UniversitySociety extends Society implements IUniversitySociety{

    // List to store the members of the society of type ISocietyMember
    private List<ISocietyMember> memberList;


    //Constructor to initialize all the member variables of the UniversitySociety and Society classes
    //and this also makes sure that at least one member should be added while initializing.
    public UniversitySociety(int id, String name, String description, List<ISocietyMember> memberList){
        // Ensures that the society has at least one member
        if(memberList.isEmpty() || memberList==null){
            throw new InvalidParameterException("At least one member should be present.");
        }
        this.id=id;
        this.name=name;
        this.description=description;
        this.memberList=memberList;
    }


    // Displays the list of society members, showing their ID and name.
    public void viewMembers() {
        for(ISocietyMember member : memberList){
            System.out.println(member.getId() +": "+ member.getName());
        }
    }


    // Adds a new member to the society.
    public void addMember(ISocietyMember member) {
        memberList.add(member);
    }


    // Removes a member from the society.
    // Ensures that the society always has at least one member.
    public void removeMember(ISocietyMember member) {
        // Check to prevent removing the last member of the society
        if(memberList.size()>1){
            memberList.remove(member);
            System.out.println("Member "+ member.getName()+ " removed successfully.");
        }
        else{
            System.out.println("Cannot remove the last member. Society should at least have one member.");
        }

    }


    // Retrieves the list of all members in the society.
    public List<ISocietyMember> getMemberList() {
        return memberList;
    }


    // Sets a new list of members for the society.
    public void setMemberList(List<ISocietyMember> memberList) {
        this.memberList=memberList;
    }


    // Get member details by their ID.
    // Returns null if the member is not found
    public ISocietyMember getMemberById(int id){
        // Uses orElse to return null if member is not found.
        ISocietyMember member = memberList.stream().filter(m->m.getId()==id)
                .findFirst().orElse(null);
        return member;
    }

}

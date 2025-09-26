package Services;

import Common.Utility;
import Models.*;

import java.util.*;

/*
The SocietyService class manages the operations related to societies and their members.
It provides methods to add, remove, view societies, and manage members within a society.

The class uses dependency injection to inject lists of ISocietyMember and IUniversitySociety interfaces
via the constructor or default constructor.
This allows for flexible configuration of member and society data, making the class
easier to test and maintain.

The class depends on abstractions (interfaces) like ISocietyMember and IUniversitySociety,
rather than concrete implementations.
*/

public class SocietyService {

    private List<ISocietyMember> memberList; // List to store members across all societies.
    private List<IUniversitySociety> societylist; // List to store university societies.
    Utility utility = new Utility(); // Utility class instance to help with utility operations

    // Default constructor with dependency injection for members and societies with mock data
    public SocietyService(){
        memberList=new ArrayList<ISocietyMember>();
        societylist=new ArrayList<IUniversitySociety>();

        //Creating separate lists to store members for each society.
        List<ISocietyMember> memberList1 = new ArrayList<ISocietyMember>();
        List<ISocietyMember> memberList2 = new ArrayList<ISocietyMember>();
        List<ISocietyMember> memberList3 = new ArrayList<ISocietyMember>();

        //Adding mock data for members to global member list
        memberList.add(new SocietyMember(100,"Nitish Marnuri","nitish.marnuri@cardiff.ac.uk", UserRoles.ADMIN));
        memberList.add(new SocietyMember(101,"Jim Patterson", "jim.patterson@cardiff.ac.uk",UserRoles.MEMBER));
        memberList.add(new SocietyMember(102,"Amarnath Karnati","amarnath.karnati@cardiff.ac.uk", UserRoles.MEMBER));
        memberList.add(new SocietyMember(103,"Mike Long", "mike.long@cardiff.ac.uk",UserRoles.MEMBER));
        memberList.add(new SocietyMember(104,"Keith Nicholson","keith.nicholson@cardiff.ac.uk", UserRoles.ADMIN));
        memberList.add(new SocietyMember(105,"Azgher Mohammad","azgher.mohammad@cardiff.ac.uk", UserRoles.ADMIN));

        //Adding mock data for members to society specific member lists
        memberList1.add(memberList.get(0));
        memberList1.add(memberList.get(1));
        memberList2.add(memberList.get(2));
        memberList2.add(memberList.get(3));
        memberList3.add(memberList.get(4));
        memberList3.add(memberList.get(5));

        // Creating sample mock societies and passing the member specific lists as an argument.
        societylist.add(new UniversitySociety(10, "COMSC",
                "Computer Science Society", memberList1));
        societylist.add(new UniversitySociety(11, "International Students",
                "International Students Society", memberList2));
        societylist.add(new UniversitySociety(12, "Arts",
                "Arts Society", memberList3));
    }

    // Retrieves the list of all members across all University societies.
    public List<ISocietyMember> getMemberList() {
        return memberList;
    }

    // Method to add a new society to the societyList
    public Boolean addSociety(IUniversitySociety society){

        // flag to check if adding society was successful
        Boolean addSuccess = false;

        //Check if society already exists
        Optional<IUniversitySociety> societyPresent=societylist
                .stream()
                .filter(s->s.getName().toLowerCase().equals(society.getName().toLowerCase()))
                .findFirst();

        // Adds the new society to the list as the society does not exist already.
        if(societyPresent.isEmpty()){

            // Ensure society has at least one member before adding
            if(society.getMemberList().size()>0){
                //Set the new id of the society by incrementing the last ID of the society in the list.
                society.setId(societylist.get(societylist.size() -1).getId()+1);

                // Adds the new society if society name is not empty or has whitespaces.
                if(!utility.stringIsEmptyOrWhitespace(society.getName())) //Check for empty society name
                {
                    societylist.add(society); // Adds the society
                    System.out.println("Society added successfully.");
                    addSuccess=true; // Updates the flag as society was added successfully
                }

                // Execute if society name is empty or has whitespaces.
                else{
                    // Fails to add with a below warning to the console.
                    System.out.println("Society name cannot be empty.");
                }
            }

            // If the society does not have at least one member.
            else{
                // Fails to add with a below warning to the console.
                System.out.println("Society should have at least one member.");
            }
        }

        // Does not add the society as the society already exists
        else{
            System.out.println("Society already exists.");
        }

        // Returns the final status about the successful addition of society to the list.
        return addSuccess;
    }

    // Method to remove selected society from the societyList
    public void removeSociety(IUniversitySociety society){

        // Checks if selected society does not exist in the societyList.
        if(!societylist.contains(society)){
            System.out.println("Society does not exist."); // Prints warning to console
        }

        // Checks if selected society exists in the societyList, and it has at least one society.
        else if(societylist.contains(society) && societylist.size()>1){
            societylist.remove(society); // removes the selected society from the list
        }

        // If the societyList has only one society, cannot delete as at least one should be present in the list
        else{
            // Prints warning to console
            System.out.println("At least one society should be present. Cannot delete.");
        }
    }

    // Method to display the id, name and description of the societies as a list to the console
    public void viewAllSociety(){
        // For each to iterate through each society and print its id, name and description to console.
        societylist.forEach(s->
                System.out.println(s.getId()+": "+s.getName() + " - "+s.getDescription()));
    }

    // Method to display the name of the societies as a list to the console
    public void viewAllSocietyByName(){
        // For each to iterate through each society and print its name to console.
        societylist.forEach(s-> System.out.println(s.getName()));
    }

    // Method for viewing all the info of a specific society by providing its society id.
    public IUniversitySociety viewSocietyById(int id){

        //Find the society in the list by the provided id. Returns null if not found.
        IUniversitySociety society = societylist.stream().filter(s->s.getId()==id).findFirst()
                .orElse(null);

        // If society is not null, then print all the society info to the console.
        if(society != null){
            System.out.println("Society Id: "+society.getId());
            System.out.println("Society Name: "+society.getName());
            System.out.println("Society Description: "+society.getDescription());
            System.out.println("Members:");
            society.getMemberList().forEach(m -> System.out.println("Student Id :"+m.getId()+"  Name: "+m.getName()+"  Email: "+m.getEmail() +"  Role: "+m.getRole()));
        }

        // If society is null, then ask the user to enter a valid society id from the list.
        else{
            System.out.println("Please enter a valid society id.");
        }

        // Returns found society or null if not found.
        return society;
    }

    // Method to get the society from the society list by providing it society id.
    public IUniversitySociety getSocietyById(int id){

        //Find the society in the list by the provided id. Returns null if not found.
        IUniversitySociety society = societylist.stream().filter(s->s.getId()==id).findFirst()
                .orElse(null);

        // If society is null, then ask the user to enter a valid society id from the list.
        if(society == null){
            System.out.println("Please enter a valid society id.");
        }

        // Returns found society or null if not found.
        return society;
    }

    // Method to add a new member to the selected university society
    public Boolean addMemberToSociety(IUniversitySociety society, ISocietyMember member){

        // Flag to check if member does not exist
        Boolean memberNotExists=true;

        // Find and fetch the new member in main member list
        Optional<ISocietyMember> memberPresent=memberList
                .stream()
                .filter(m->m.getName().equals(member.getName()))
                .findFirst();

        // Find and fetch the new member in selected society member list
        Optional<ISocietyMember> societyMemberPresent = society.getMemberList()
                .stream()
                .filter(m->m.getName().equals(member.getName()))
                .findFirst();

        // Increments id of the last member from main member list to accommodate new member.
        member.setId(memberList.get(memberList.size()-1).getId()+1);

        // Joins the name with '.' and create new email for new member.
        member.setEmail(member.getName().replace(" ",".")+"@cardiff.ac.uk");

        // If the new member is not present in both the main and society's member list
        // then add the member to both the lists.
        if(memberPresent.isEmpty() && societyMemberPresent.isEmpty()){
            memberList.add(member);
            society.addMember(member);
        }

        // If the new member is present in the main member list and not present in society's member list
        // then add the member to society's member list.
        else if(!memberPresent.isEmpty() && societyMemberPresent.isEmpty()){
            society.addMember(member);
        }

        // If member exists both places, then do not add the member.
        else{
            System.out.println("Member already exists.");
            memberNotExists=false; // Update member not exist to false as it exists.
        }
        return memberNotExists;
    }

    // Method to remove a selected member from selected society
    public void removeMemberFromSociety(IUniversitySociety society, ISocietyMember member){

        // Checks if member exists in the selected society
        Optional<ISocietyMember> societyMemberPresent = society.getMemberList()
                .stream()
                .filter(m->m.getName().equals(member.getName()))
                .findFirst();

        // Cannot remove as member not present in the society
        if(societyMemberPresent.isEmpty()){
            System.out.println("Member not part of the society. Cannot remove.");
        }

        // Removes the member from the society
        else{
            society.removeMember(member);
        }
    }
}

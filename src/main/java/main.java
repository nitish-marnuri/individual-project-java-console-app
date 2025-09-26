import Common.Utility;
import Models.*;
import Services.SocietyService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Entry point of the application
public class main {

    // Initialize the SocietyService, Scanner for input, Utility for common operations
    private static SocietyService societyService = new SocietyService();
    private static Scanner input = new Scanner(System.in);
    private static Utility utility =new Utility();

    private static int userInput=0; // Input from user to start the program

    // Main method to run the console application
    public static void main(String[] args){
        // Displays welcome home page for University society application
        homePatternDisplay();

        // loop until the user decides to exit
        while(userInput!=4){
            // Display instructions for the main screen.
            homePatternInstructions();

            // Get user input for the operation to perform by parsing the value to int
            userInput =utility.integerValidation(input.nextLine(),
                    "Please select the operation you want to perform:");


            switch (userInput){
                case 1:
                    // Console logging and logic for viewing a society and managing it members.
                    viewSociety();
                    break;
                case 2:
                    // Console logging and logic for adding a new society.
                    addSociety();
                    break;
                case 3:
                    // Console logging and logic for removing a society.
                    removeSociety();
                    break;
                case 4:
                    // Exit operation
                    System.out.println("Exiting you from the system...");
                    break;
                default:
                    // Executes if all the above input does not match.
                    System.out.println("Please enter from given options.");
                    break;
            }
        }
        System.out.println("\nThank you for using Cardiff University society application." +
                "\nEnjoy your day!");
    }

    // Method to display the welcome home page
    public static void homePatternDisplay(){
        System.out.println("\n");
        System.out.println("        **********************************************");
        System.out.println("        *                                            *");
        System.out.println("        *       WELCOME TO THE CARDIFF UNIVERSITY    *");
        System.out.println("        *                SOCIETY APP                 *");
        System.out.println("        *                                            *");
        System.out.println("        **********************************************");
        System.out.println();
    }

    // Method to display the instructions for the available operations
    public static void homePatternInstructions(){
        System.out.println("We have different type of societies for different individuals " +
                "with varied interests.\n");
        System.out.println("You can explore the below listed societies: ");
        societyService.viewAllSocietyByName();
        System.out.println("\nYou can perform the below set of operations.");
        System.out.println("1. View Society  2. Add Society  3. Remove Society 4. Exit");
        System.out.println("\nPlease select the operation you want to perform:");
        System.out.println("\"Hint: Type 1 to View Society\"");
    }

    // Method to handle viewing societies and managing members
    public static void viewSociety(){
        int userInputViewById=1; // to store user input for sub operations under view society
        //region View Society logic
        System.out.println("You selected 1: View Society\n");

        // loop until the user decides to exit
        while (userInputViewById!=9){
            System.out.println("Here are the list of societies you can view from:");
            societyService.viewAllSociety(); // View all society
            IUniversitySociety selectedSociety; // To store the selected society object by given id.
            do{
                System.out.println("\nPlease enter the id of the society you would like to view or type '9' to go back to main.");
                // Checks if user input is integer and sets the value. Asks again if incorrect input provided.
                userInputViewById = utility.integerValidation(input.nextLine(),
                        "Please enter the id of the society you would like to view.");
                System.out.println();

                // Return null if user wants to exit else return the selected society
                selectedSociety = (userInputViewById!=9) ?
                        societyService.viewSocietyById(userInputViewById) : null;

            }while (selectedSociety==null && userInputViewById!=9);
            // Continue if wrong society id provided else exit if users wants to exit.

            if(userInputViewById!=9){
                //Console logging and logic for managing member operations in selected society.
                manageMembers(userInputViewById,selectedSociety);
            }
        }
        //endregion
    }

    // Method to handle adding and removing members from a society.
    public static void manageMembers(int userInputViewById, IUniversitySociety society){
        int userInputOperation=0; // To store user input for performing add/remove member operations.

        // Loop until the user decides to exit
        while(userInputOperation!=3){
            if(society != null){
                System.out.println("\nYou can perform the below set of operations.");
                System.out.println("1. Add Member  2. Remove Member 3. Back");

                // Checks if user input is integer and sets the value. Asks again if incorrect input provided.
                userInputOperation = utility.integerValidation(input.nextLine(),
                        "Please select the operation you would like to perform.");

                // Execute only if user does not want to exit.
                if(userInputOperation!=3){
                    switch (userInputOperation){
                        // Adds new member to society
                        case 1:

                            // Add new member to the society by checking if member already exists
                            Boolean memberNotExists = societyService.addMemberToSociety(society,
                                    new SocietyMember(1,
                                            utility.emptyStringValidation("Enter the name of new member.",
                                                    "Member name cannot be blank."),
                                            UserRoles.MEMBER));

                            // Add the member if it is not already existing
                            if(memberNotExists){
                                System.out.println("New member added successfully.");
                            }

                            societyService.viewSocietyById(userInputViewById);
                            System.out.println();
                            break;

                        // Removes selected member from society
                        case 2:
                            System.out.println("List of available members.");
                            society.viewMembers();
                            Boolean invalidEntryFlag=true; // Flag to check correct user input
                            while(invalidEntryFlag){
                                // Checks if member id user input is integer. Asks again if incorrect input provided.
                                int userInputForMemberToRemove = utility.integerValidation(input.nextLine(),
                                        "Enter the id of the member to be removed.");

                                ISocietyMember societyMember = society.getMemberById(userInputForMemberToRemove);

                                // Removes the member if it exists
                                if(societyMember!=null){
                                    societyService.removeMemberFromSociety(society,societyMember);
                                    invalidEntryFlag=false;
                                }
                                else{
                                    System.out.println("Please enter a valid member id to be removed.");
                                    invalidEntryFlag=true;
                                }
                            }
                            break;
                        default:
                            System.out.println("Enter the correct number from the list of options.");
                            break;
                    }
                }
            }
        }
    }

    // Method to handle adding of society to the society list.
    public static void addSociety(){
        //region Add Society logic

        // Value fields for creating new university society.
        String newSocietyName;
        String newSocietyDescription;
        String newMemberName;

        // Flag to check if society added successfully
        Boolean isSocietyAdded=true;
        System.out.println("You selected 1: Add Society\n");
        do{
            // Get society name from user input. Need to input again for invalid input.
            newSocietyName = utility.emptyStringValidation(
                    "Enter a name for the new society",
                    "Society name cannot be blank.");

            System.out.println("Give some description about the society and some more insights.");
            newSocietyDescription = input.nextLine();
            System.out.println("Add members to the society.");
            System.out.println("NOTE: At least one member should be present in a society.");

            // Get member name from user input. Need to input again for invalid input.
            newMemberName = utility.emptyStringValidation(
                    "Enter the name of the member to be added.",
                    "Member name cannot be blank");
            List<ISocietyMember> newMemberList = new ArrayList<ISocietyMember>();

            // Creating new member list from input to be added to the new society
            newMemberList.add(
                    new SocietyMember(
                            societyService.getMemberList().get(societyService.getMemberList().size() -1).getId() +1
                            ,newMemberName
                            ,UserRoles.ADMIN));

            // Adds new society to the society list
            IUniversitySociety newSociety = new UniversitySociety(1,newSocietyName,
                    newSocietyDescription, newMemberList);
            isSocietyAdded = societyService.addSociety(newSociety);


            if(!isSocietyAdded)
                System.out.println("Try again please...");
            System.out.println();
        }while (!isSocietyAdded); // Loops until correct society details are provided to be added.
        //endregion Add Society logic
    }

    // Method to handle removing of society from the society list.
    public static void removeSociety(){
        //region Remove Society logic
        Boolean isDeleted=false; // Flag to check if delete is successful
        System.out.println("You selected 1: Remove Society\n");
        System.out.println("Here are the list of available societies:");
        societyService.viewAllSociety();
        do{
           System.out.println("\nPlease enter the id of the society you would like to delete.");
           // Get society id to be deleted as user input
           int societyId = utility.integerValidation(input.nextLine(),
                    "Please enter the id of the society you would like to delete.");
           System.out.println("Are you sure you want to delete? Type Yes(Y) or No(N) to confirm.");

           // Get confirmation to delete society as a user input
           String confirmation = input.nextLine();

           // Checks if confirmation input has correct input
           Boolean checkConfirmationInput=true;

           // If confirmation input is correct then remove society else ask user again
           while(checkConfirmationInput){
               if(!confirmation.toLowerCase().equals("yes") && !confirmation.toLowerCase().equals("y")
                       && !confirmation.toLowerCase().equals("no") && !confirmation.toLowerCase().equals("n")){
                   System.out.println("You entered an invalid value. Please try again.");
                   confirmation=input.nextLine();
                   checkConfirmationInput=true;
               }

               // Remove society if confirmation input is Yes
               else if(confirmation.toLowerCase().equals("yes") || confirmation.toLowerCase().equals("y")){
                   IUniversitySociety society = societyService.getSocietyById(societyId);
                   if(society!=null){
                       societyService.removeSociety(society);
                       System.out.println(society.getName() + " removed successfully.\n");
                       isDeleted=true;
                   }
                   else{}
                   checkConfirmationInput=false;
               }

               // Ignores and runs out of the loop if user input is No
               else if(confirmation.toLowerCase().equals("no") || confirmation.toLowerCase().equals("n")){
                   System.out.println("You selected 'NO' so skipping the deletion.\n");
                   checkConfirmationInput=false;
                   isDeleted=true;
               }
           }
        }while (!isDeleted);
    }
}

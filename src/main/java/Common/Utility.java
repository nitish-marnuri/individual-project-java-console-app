package Common;

import java.util.Scanner;

public class Utility {
    Scanner input = new Scanner(System.in);

    // This method tries to parse string to number. Throws exception is parsing fails.
    private int tryParseInteger(String number){
        try{
            return Integer.parseInt(number);
        }
        catch(Exception ex){
            return 0;
        }
    }

    // Validates if the provided input is integer. If not, asks the user to try again
    public int integerValidation(String number, String message){
        int parsedInt = tryParseInteger(number);

        // Loops until a valid integer input is provided.
        while(parsedInt<=0){
            System.out.println("You should enter a numeric value.\n");
            System.out.println(message);
            parsedInt = tryParseInteger(input.nextLine());
        }
        return parsedInt;
    }

    // Method to check given string is empty or has only whitespace
    public Boolean stringIsEmptyOrWhitespace(String str){
        Boolean flag=str.trim().length()>0?false:true;
        return flag;
    }

    // Method to restrict the user from providing empty string and give a valid input
    public String emptyStringValidation(String message, String errorMessage){
        String parameter;

        // loops until a valid string is provided
        do{
            System.out.println(message);
            parameter = input.nextLine();
            if(stringIsEmptyOrWhitespace(parameter)){
                System.out.println(errorMessage);
            }
        }while(stringIsEmptyOrWhitespace(parameter));

        return parameter;
    }

}

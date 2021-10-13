package animalgame;

import java.util.List;
import java.util.Scanner;

public class ProgramUtils {
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    public static final String PURPLE = "\u001B[35m";
    public static final String YELLOW = "\u001B[33m";
    public static final String RED = "\u001B[31m";


    /**
     * Read a file from input path
     * @param path name of the file
     */
    public static void readFile(String path){
    }


    /**
     * Write to file from input path
     * @param path name of the file
     */
    public static void writeToFile(String path){

    }

    /**
     * Method to create a menu out of input parameters and return it as a string
     * @param menuName name of the desired menu
     * @param options each menu choise
     * @return return output string
     */
    public static int menuBuilder(String menuName, String ...options){

        int menuCounter = 1;
        System.out.println(menuName);
        for(String menuOption : options){
            System.out.print("\n" + menuCounter + ".\t" + menuOption);
            menuCounter += 1;
        }
        System.out.print("\nUser input: ");
        return Integer.parseInt(userInput());
    }

    /**
     * Takes a user input value and return it as a string
     *
     * @return Return user input
     */
    public static String userInput(){

        Scanner console = new Scanner(System.in);
        String userInput;

        //try{
            userInput = console.nextLine();
            return userInput;

        //}catch(Exception e){
        //    e.printStackTrace();
        //    return null;
        //}

    }

}

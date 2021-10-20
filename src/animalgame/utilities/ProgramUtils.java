package animalgame.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Scanner;

public class ProgramUtils {
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    public static final String PURPLE = "\u001B[35m";
    public static final String YELLOW = "\u001B[33m";
    public static final String RED = "\u001B[31m";

    /**
     * Load object from file and return that object
     * @return object from file
     */
    public static Object readFile(){
        ObjectInputStream o = null;
        Object object = null;
        try{
            FileInputStream f = new FileInputStream("src/animalgame/savegame");
            o = new ObjectInputStream(f);
            object = o.readObject();
            o.close();

        }catch(Exception e){
            e.printStackTrace();
        }
        return object;
    }

    /**
     * Save input object to file
     * @param object input object to save
     */
    public static void writeToFile(Object object){
        ObjectOutputStream o = null;
        FileOutputStream f = null;

        try{
            f = new FileOutputStream("src/animalgame/savegame");
            o = new ObjectOutputStream(f);
            o.writeObject(object);
            o.close();

        }catch(Exception e){
            e.printStackTrace();
        }
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
        return tryCatch(userInput());
    }

    /**
     * Takes a user input value and return it as a string
     *
     * @return Return user input
     */
    public static String userInput(){

        Scanner console = new Scanner(System.in);
        String userInput;
        userInput = console.nextLine();
        return userInput;
    }

    public static int tryCatch(String numberInput){
        int newInput;
            try{
                newInput = Integer.parseInt(numberInput);
            }catch(Exception e){
                //e.printStackTrace();
                System.out.println("Write a number you goof!");
                newInput = tryCatch(userInput());
            }

        return newInput;
    }

}

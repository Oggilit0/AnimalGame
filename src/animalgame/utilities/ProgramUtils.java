package animalgame.utilities;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * ProgramUtils ...
 * @author Sebastian Banfi, Oskar Herdenberg, Mathilda Nilsson, Hanna Petersson
 */
public class ProgramUtils {
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    public static final String PURPLE = "\u001B[35m";
    public static final String YELLOW = "\u001B[33m";
    public static final String RED = "\u001B[31m";

    /**
     * Method to create a menu out of input parameters and return it as a string
     * @param menuName name of the desired menu
     * @param options each menu choice
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
        return tryCatch();
    }

    /**
     * Takes a user input value and return it as a string
     * @return Return user input
     */
    public static String userInput(){

        Scanner console = new Scanner(System.in);
        String userInput;
        userInput = console.nextLine();
        return userInput;
    }

    /**
     * check if user input is correctly a number, returns number.
     * If user inputs a wrong number, or other symbol, the function loops
     * @return user input as an int
     */
    public static int tryCatch(){
        int newInput = -1;
        do {
            try{
                newInput = Integer.parseInt(userInput());

            }catch(Exception e){
                System.out.println("Invalid input");
            }
        }while(newInput == -1);
        return newInput;
    }

    /**
     * Overloaded method to input min and max value of a menu to create
     * conditions for the loop
     * @param minMenuValue the menus lowest int value
     * @param maxMenuValue the menus highest int value
     * @return user input as an int
     */
    public static int tryCatch(int minMenuValue, int maxMenuValue){
        int newInput = -1;
        do{
            try{
                newInput = Integer.parseInt(userInput());
                if(maxMenuValue < newInput || newInput < minMenuValue){
                    System.out.println("Invalid input");
                }

            }catch(Exception e){
                System.out.println("Invalid input");
            }
        }while(maxMenuValue < newInput || newInput < minMenuValue);
        return newInput;
    }

    /**
     * Reads all the lines in storedSaveName.txt and put it in a list
     * @return List of lines read from file storedSaveName.txt
     */
    public static List<String> readAllLines() {
        List<String> lines = null;
        try {
            lines = new ArrayList<>();
            lines = Files.readAllLines(Paths.get("src/animalgame/programfiles/storedSaveName.txt"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lines;
    }

    /**
     * Reads the line from file storedSaveName.txt and store it in a list
     * replace the lines on the current row with the new lines taken from parameter
     * @param saveName name of the save file
     * @param index index of which it's stored
     * @return the new list
     */
    public static ArrayList<String> saveFileHandler(String saveName, int index){
        ArrayList<String> oldSave = (ArrayList<String>) readAllLines();
        ArrayList<String> temp = new ArrayList<>();

        switch(index){
            case 1:
                if(oldSave.size() > 0){
                    oldSave.remove(0);
                }

                temp.add(saveName);

                if (oldSave.size() > 0){
                    temp.add(oldSave.get(0));
                }

                if (oldSave.size() > 1){
                    temp.add(oldSave.get(1));
                }

                oldSave = temp;
                    return oldSave;

            case 2:
                if(oldSave.size() > 0){
                    temp.add(oldSave.get(0));
                }

                temp.add(saveName);

                if (oldSave.size() > 2){
                    temp.add(oldSave.get(2));
                }

                oldSave = temp;
                return oldSave;

            case 3:
                if(oldSave.size() == 3){
                    oldSave.remove(2);
                    oldSave.add(saveName);
                }else{
                    oldSave.add(saveName);
                }
                return oldSave;
        }

        return null;
    }

    /**
     * Load object from file and return that object
     * @return object from file
     */
    public static Object readFile(String fileName){
        ObjectInputStream o;
        Object object;
        try{
            FileInputStream f = new FileInputStream("src/animalgame/programfiles/"+fileName+".txt");
            o = new ObjectInputStream(f);
            object = o.readObject();
            System.out.println("Sucess!");
            o.close();

        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return object;
    }

    /**
     * Save input object to file
     * @param object input object to save
     */
    public static void writeToFile(Object object,String fileName){
        ObjectOutputStream o;
        FileOutputStream f;

        try{
            f = new FileOutputStream("src/animalgame/programfiles/"+fileName+".txt",false);
            o = new ObjectOutputStream(f);
            o.writeObject(object);
            o.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Write saved file names to store in a .txt file for display purpose
     * @param saveName Save file name as string
     * @param oldSaveIndex index of where the old save was stored as int
     */
    public static void writeToSaveFile(String saveName, int oldSaveIndex){

        ArrayList<String> oldSave = saveFileHandler(saveName, oldSaveIndex);

        try {
            Path path = Paths.get("src/animalgame/programfiles/storedSaveName.txt");
            Files.write(path, oldSave, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete file corresponding to the incoming parameter
     * @param fileName name of file which will be deleted
     */
    public static void DeleteFile(String fileName) {
            File myObj = new File("src/animalgame/programfiles/"+fileName+".txt");
            if (myObj.delete()) {
                System.out.println("Deleted the file: " + myObj.getName());
            } else {
                System.out.println("Failed to delete the file.");
            }
    }
}

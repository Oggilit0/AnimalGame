package animalgame.utilities;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
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
    public static Object readFile(String fileName){
        ObjectInputStream o = null;
        Object object = null;
        try{
            FileInputStream f = new FileInputStream("src/animalgame/programfiles/"+fileName+".txt");
            o = new ObjectInputStream(f);
            object = o.readObject();
            System.out.println("Sucess!");
            o.close();


        }catch(Exception e){
            //e.printStackTrace();
            return null;
        }
        return object;
    }

    /**
     * Save input object to file
     * @param object input object to save
     */
    public static void writeToFile(Object object,String fileName){
        ObjectOutputStream o = null;
        FileOutputStream f = null;

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

    public static void writeFromSaveFile(String saveName, int oldSaveIndex){

        ArrayList<String> oldSave = saveFileHandler(saveName, oldSaveIndex);

        try {
            Path path = Paths.get("src/animalgame/programfiles/storedSaveName.txt");
            Files.write(path, oldSave, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void DeleteFile(String fileName) {
            File myObj = new File("src/animalgame/programfiles/"+fileName+".txt");
            if (myObj.delete()) {
                System.out.println("Deleted the file: " + myObj.getName());
            } else {
                System.out.println("Failed to delete the file.");
            }
    }
}

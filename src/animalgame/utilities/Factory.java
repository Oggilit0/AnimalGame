package animalgame.utilities;

import animalgame.Player;
import animalgame.animals.*;
import animalgame.animals.abstractmodels.Animal;
import animalgame.enums.Gender;

import java.awt.datatransfer.ClipboardOwner;
import java.util.ArrayList;

public class Factory {

    /**
     * @param animalType
     * @param gender
     */
    public static  Animal createAnimal(String animalType, Gender gender) {
        Animal animal = null;
        System.out.println("Congratulations to your new "+ gender.toString().toLowerCase()+ " animal, Name your new animal : ");

        switch(animalType){
            case "Ferret":
                animal = new Ferret(ProgramUtils.userInput(), gender);
                break;
            case "MexicanAlligatorLizard":
                animal = new Mexican_Alligator_Lizard(ProgramUtils.userInput(), gender);
                break;
            case "Giraffe":
                animal = new Giraffe(ProgramUtils.userInput(),gender);
                break;
            case "PolarBear":
                animal = new PolarBear(ProgramUtils.userInput(), gender);
                break;
            case "Troll":
                animal = new Troll(ProgramUtils.userInput(),gender);
                break;
            default:
        }

        return animal;
    }

    /**
     * Takes two animals and checks if they are the same class and different gender.
     * If comparison is successful a random number will be given to how many new animals
     * the player will get.
     * @param animal1
     * @param animal2
     */
    public static boolean tryMating(Animal animal1, Animal animal2, Player currentPlayer) {
        if (animal1.getClass().equals(animal2.getClass())) {
            if (animal1.getGender() != animal2.getGender()) {
                int startMating = (int) (Math.random() * 2); //random if mating is successful
                if (startMating == 0) {
                    for (int i = 0; i < animal1.howManyBabies(animal1.getClass().toString().substring(25)); i++) {
                        int getGender = (int) (Math.random() * 2); //random gender of baby
                        if (getGender == 0) {
                            currentPlayer.setPlayerAnimal(createAnimal(animal1.getClass().toString().substring(25), Gender.FEMALE));

                        } else {
                            currentPlayer.setPlayerAnimal(createAnimal(animal1.getClass().toString().substring(25), Gender.MALE));
                        }
                    }return true;

                } else {
                    System.out.println("No babies this time!");
                }return false;

            } else {
                System.out.println("Animals needs to be different gender!");
            }return false;

        } else {
            System.out.println("Animals needs to be the same species!");
        }return false;
    }
}

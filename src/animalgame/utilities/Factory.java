package animalgame.utilities;

import animalgame.Player;
import animalgame.animals.*;
import animalgame.animals.abstractmodels.Animal;
import animalgame.enums.Gender;

/**
 * This is the factory class where we create and mate our animals.
 * @author Sebastian Banfi, Oskar Herdenberg, Mathilda Nilsson, Hanna Petersson
 */
public class Factory {

    /**
     * Takes an animal type as a string and its gender as an enum and checks which type of animal
     * and gender to create, the player can name their new animal with an already assigned user input.
     * @param animalType animal as a String.
     * @param gender gender as enum.
     */
    public static  Animal createAnimal(String animalType, Gender gender) {
        Animal animal = null;
        System.out.println("Congratulations to your new "+ gender.toString().toLowerCase()+ " animal, Name your new animal : ");
        switch(animalType){
            case "Ferret":
                animal = new Ferret(ProgramUtils.userInput(), gender);
                break;
            case "Dragon":
                animal = new Dragon(ProgramUtils.userInput(), gender);
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
     * the player will get and then random if the baby is a male or female. A new animal will
     * be created and added to the players animal list.
     * @param animal1 as an animal object.
     * @param animal2 as an animal object.
     * @param currentPlayer as a player object.
     */
    public static boolean tryMating(Animal animal1, Animal animal2, Player currentPlayer) {
        if (animal1.getClass().equals(animal2.getClass())) {
            if (animal1.getGender() != animal2.getGender()) {
                int startMating = (int) (Math.random() * 2); //random if mating is successful
                if (startMating == 0) {
                    for (int i = 0; i < animal1.howManyBabies(animal1.getClass().getSimpleName()); i++) {
                        int getGender = (int) (Math.random() * 2); //random gender of baby
                        Gender gender;
                        if (getGender == 0) {
                            gender = Gender.FEMALE;
                        } else {
                            gender = Gender.MALE;
                        }
                        currentPlayer.addPlayerAnimal(createAnimal(animal1.getClass().getSimpleName(), gender));
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

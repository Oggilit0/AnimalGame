package animalgame.utilities;

import animalgame.Player;
import animalgame.animals.*;
import animalgame.animals.abstractmodels.Animal;
import animalgame.enums.Gender;

public class Factory {


    public Factory(){

    }

    /**
     * @param animalType
     * @param gender
     */
    public static  Animal createAnimal(String animalType, Gender gender) {
        System.out.println("Congratulations to your new "+ gender.toString().toLowerCase()+ " animal, Name your new animal : ");
        switch(animalType){
            case "Ferret":
                Ferret ferret;
                if(gender == Gender.FEMALE) {
                    ferret = new Ferret(ProgramUtils.userInput(), 500, 9, Gender.FEMALE);
                } else {
                    ferret = new Ferret(ProgramUtils.userInput(), 500, 9, Gender.MALE);
                }
                return ferret;
            case "MexicanAlligatorLizard":
                MexicanAlligatorLizard mexicanAlligatorLizard;
                if (gender == Gender.FEMALE) {
                    mexicanAlligatorLizard = new MexicanAlligatorLizard(ProgramUtils.userInput(), 500, 10, Gender.FEMALE);
                } else {
                    mexicanAlligatorLizard = new MexicanAlligatorLizard(ProgramUtils.userInput(), 500, 10, Gender.MALE);
                }
                return mexicanAlligatorLizard;
            case "Giraffe":
                Giraffe giraffe;
                if (gender == Gender.FEMALE) {
                    giraffe = new Giraffe(ProgramUtils.userInput(), 500, 15, Gender.FEMALE);
                } else {
                    giraffe = new Giraffe(ProgramUtils.userInput(), 500, 15, Gender.MALE);
                }
                return giraffe;
            case "PolarBear":
                PolarBear polarBear;
                if (gender == Gender.FEMALE) {
                    polarBear = new PolarBear(ProgramUtils.userInput(), 500, 20, Gender.FEMALE);
                } else {
                    polarBear = new PolarBear(ProgramUtils.userInput(), 500, 20, Gender.MALE);
                }
                return polarBear;
            case "Troll":
                Troll troll;
                if (gender == Gender.FEMALE) {
                    troll = new Troll(ProgramUtils.userInput(), 500, 7, Gender.FEMALE);
                } else {
                    troll = new Troll(ProgramUtils.userInput(), 500, 7, Gender.FEMALE);
                }
                return troll;
            default:
        }return null;
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
                    int makeBabies = (int) (Math.random() * 3) + 1; //random how many babies
                    for (int i = 0; i < makeBabies; i++) {
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

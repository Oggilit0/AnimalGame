package animalgame.utilities;

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
    public Animal createAnimal(String animalType, Gender gender) {
        // "animalgame.animals.Cat";
        //animalType.substring(11);
        System.out.println("Congratulations to your new animal you got a: " + gender + ". Name your new animal : ");
        switch (animalType) {
            case "Cat":
                Cat cat;
                if(gender == Gender.FEMALE) {
                    cat = new Cat(ProgramUtils.userInput(), 500, 9, Gender.FEMALE);
                } else {
                    cat = new Cat(ProgramUtils.userInput(), 500, 9, Gender.MALE);
                }
                return cat;
            case "Cow":
                Cow cow;
                if (gender == Gender.FEMALE) {
                    cow = new Cow(ProgramUtils.userInput(), 500, 10, Gender.FEMALE);
                } else {
                    cow = new Cow(ProgramUtils.userInput(), 500, 10, Gender.MALE);
                }
                return cow;
            case "Dog":
                Dog dog;
                if (gender == Gender.FEMALE) {
                    dog = new Dog(ProgramUtils.userInput(), 500, 15, Gender.FEMALE);
                } else {
                    dog = new Dog(ProgramUtils.userInput(), 500, 15, Gender.MALE);
                }
                return dog;
            case "Horse":
                Horse horse;
                if (gender == Gender.FEMALE) {
                    horse = new Horse(ProgramUtils.userInput(), 500, 20, Gender.FEMALE);
                } else {
                    horse = new Horse(ProgramUtils.userInput(), 500, 20, Gender.MALE);
                }
                return horse;
            case "Snake":
                Snake snake;
                if (gender == Gender.FEMALE) {
                    snake = new Snake(ProgramUtils.userInput(), 500, 7, Gender.FEMALE);
                } else {
                    snake = new Snake(ProgramUtils.userInput(), 500, 7, Gender.FEMALE);
                }
                return snake;
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
    public boolean tryMating(Animal animal1, Animal animal2) {
        if (animal1.getClass().equals(animal2.getClass())) {
            if (animal1.getGender() != animal2.getGender()) {
                int startMating = (int) (Math.random() * 2); //random if mating is successful
                if (startMating == 0) {
                    int makeBabies = (int) (Math.random() * 3) + 1; //random how many babies
                    for (int i = 0; i < makeBabies; i++) {
                        int getGender = (int) (Math.random() * 2); //random gender of baby
                        if (getGender == 0) {
                            createAnimal(animal1.getClass().getName(), Gender.FEMALE);
                        } else {
                            createAnimal(animal1.getClass().getName(), Gender.MALE);
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

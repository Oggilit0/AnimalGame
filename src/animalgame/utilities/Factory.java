package animalgame.utilities;

import animalgame.animals.*;
import animalgame.animals.abstractmodels.Animal;
import animalgame.enums.Gender;

public class Factory {


    public Factory(){

    }

    /**
     *
     * @param animalType
     * @param gender
     */
    public void createAnimal(String animalType, Gender gender) {
        // "animalgame.animals.Cat"
        //animalType.getClass().getName().substring(11);
        switch(animalType){
            case "Cat":
                if(gender == Gender.FEMALE){
                    Cat cat = new Cat("Martina", 500, 9, Gender.FEMALE, currentPlayer);
                    System.out.println("Hej katten!");
                    this.currentPlayer.setPlayerAnimal(cat);
                }else{
                    Cat cat = new Cat("Martin",500,9, Gender.MALE, currentPlayer);
                    System.out.println("Hej katt!");
                    this.currentPlayer.setPlayerAnimal(cat);
                }
                break;
            case "Cow":
                if(gender == Gender.FEMALE){
                    Cow cow = new Cow(ProgramUtils.userInput(), 500, 10, Gender.FEMALE, currentPlayer);
                    System.out.println("Hej ko!");
                    this.currentPlayer.setPlayerAnimal(cow);
                }else{
                    Cow cow = new Cow(ProgramUtils.userInput(), 500, 10, Gender.MALE, currentPlayer);
                    this.currentPlayer.setPlayerAnimal(cow);
                }
                break;
            case "Dog":
                if(gender == Gender.FEMALE){
                    Dog dog = new Dog(ProgramUtils.userInput(),500, 15, Gender.FEMALE, currentPlayer);
                    this.currentPlayer.setPlayerAnimal(dog);
                }else{
                    Dog dog = new Dog(ProgramUtils.userInput(), 500,15, Gender.MALE, currentPlayer);
                    this.currentPlayer.setPlayerAnimal(dog);
                }
            case "Horse":
                if(gender == Gender.FEMALE){
                    Horse horse = new Horse(ProgramUtils.userInput(),500,20, Gender.FEMALE, currentPlayer);
                    this.currentPlayer.setPlayerAnimal(horse);

                }else{
                    Horse horse = new Horse(ProgramUtils.userInput(),500,20, Gender.MALE, currentPlayer);
                    this.currentPlayer.setPlayerAnimal(horse);
                }
            case "Snake":
                if(gender == Gender.FEMALE){
                    Snake snake = new Snake(ProgramUtils.userInput(),500, 7, Gender.FEMALE, currentPlayer);
                    this.currentPlayer.setPlayerAnimal(snake);
                }else{
                    Snake snake = new Snake(ProgramUtils.userInput(),500, 7, Gender.FEMALE, currentPlayer);
                    this.currentPlayer.setPlayerAnimal(snake);
                }
            default:
                // if (animalType.getClass().equals(Cat.class))
                //100% hälsa
                //köpa djur: döpa och välja kön
        }
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

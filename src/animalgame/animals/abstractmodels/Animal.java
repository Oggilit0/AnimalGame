package animalgame.animals.abstractmodels;

import animalgame.Player;
import animalgame.enums.Gender;
import animalgame.food.abstractmodels.Food;

import java.io.Serializable;

public abstract class Animal implements Serializable {
    private String name;
    private int health;
    private int maxAge;
    private int currentAge;
    private int animalPrice;
    private final Gender gender;
    private boolean aliveStatus;

    public Animal(String name, Gender gender){
        this.name = name;
        this.health = 100;
        this.gender = gender;
        this.aliveStatus = true;
        //this.owner = owner;

    }

    /**
     * If foodToEat instance of a food the animal doesn't eat returns false and an output to the console.
     * If true the animal eats the food object it is given.
     * @param foodToEat as food object
     * @return if the animal eats the food or not
     */
    public abstract boolean eat(Food foodToEat);


    /**
     * Kills the animal and removes it from owners list of animals
     */
    public void death(){
        this.aliveStatus = false;
    }

    /**
     * Remove 10-30% of maximum value of health each time method is running
     * checks if health is over 0, if not the animal dies
     */
    public void healthOverTime(){
        int randNr = (int) (Math.random()*21)+10;
        if (randNr < health){
            this.health = this.health-randNr;
        }else{
            this.health = 0;
            death();
        }
    }

    /**
     * Takes an animal as a String and checks the art of the animal.
     * The method will randomize the amount of babies the animal will have
     * depending on the art.
     * @param animal as a string
     * @return the amount of babies the animals will have
     */
    public int howManyBabies(String animal){
        switch(animal){
            case "Ferret":
                int makeFerretBabies = (int) (Math.random() * 6) + 1;
                return makeFerretBabies;
            case "Giraffe":
                int makeGiraffeBabies = (int) (Math.random() * 3) + 1;
                return makeGiraffeBabies;
            case "Mexican_Alligator_Lizard":
                int makeLizardBabies = (int) (Math.random() * 8) + 1;
                return makeLizardBabies;
            case "PolarBear":
                int makePolarBearBabies = (int) (Math.random() * 4) + 1;
                return makePolarBearBabies;
            case "Troll":
                int makeTrollBabies = (int) (Math.random() * 2) + 1;
                return makeTrollBabies;
        }return 0;
    }

    public String getName(){
        return this.name;
    }

    public Gender getGender(){
        return this.gender;
    }

    public int getMaxAge(){
        return this.maxAge;
    }

    public void setMaxAge(int maxAge){
        this.maxAge = maxAge;
    }

    public void setCurrentAge(int currentAge) {
        this.currentAge += currentAge;
    }

    public void setHealth(int health){
        this.health = health;
    }

    public int getCurrentAge(){
        return this. currentAge;}

    public int getHealth(){
        return this.health;
    }

    public int getAnimalPrice(){
        return this.animalPrice;
    }

    public void setAnimalPrice(int animalPrice) {
        this.animalPrice = animalPrice;
    }

    public int animalSellPrice(){
        return animalPrice * ((health-currentAge))/100;
    }

    public boolean getAliveStatus() {
        return aliveStatus;
    }
}

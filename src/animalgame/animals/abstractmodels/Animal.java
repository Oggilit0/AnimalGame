package animalgame.animals.abstractmodels;

import animalgame.Player;
import animalgame.enums.Gender;

public abstract class Animal {
    private String name;
    private int health;
    private final int maxAge;
    private int currentAge;
    private int animalPrice;
    private Player owner;
    private final Gender gender;
    private Boolean aliveStatus = true;

    public Animal(String name, int animalPrice, int maxAge, Gender gender, Player owner){
        this.name = name;
        this.health = 100;
        this.currentAge = 0;
        this.animalPrice = animalPrice;
        this.maxAge = maxAge;
        this.gender = gender;
        this.owner = owner;

    }

    /**
     * Kills the animal and removes it from owners list of animals
     */
    public void death(){
        this.owner.removePlayerAnimal(this);
        this.aliveStatus = false;
    }

    /**
     * Remove 10-30% of maximum value of health each time method is running
     * checks if health is over 0, if not the animal dies
     */
    public void healthOverTime(){
        if (this.health > 0){
            int randNr = (int) (Math.random()*21)+10;
            this.health = this.health-randNr;
        }else {
            death();
        }

    }

    public String getName(){
        return this.name;
    }

    public Gender getGender(){
        return this.gender;
    }

    public int getMaxAge(){
        return this.currentAge;
    }

    public void setCurrentAge(int currentAge) {
        this.currentAge += currentAge;
    }

    public int getCurrentAge(){
        return this. currentAge;}

    public int getHealth(){
        return this.health;
    }

    public int getAnimalPrice(){
        return this.animalPrice;
    }

    public void setOwner(Player owner){
        this.owner = owner;
    }


}

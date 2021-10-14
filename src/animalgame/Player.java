package animalgame;

import java.util.ArrayList;

/**
 * @author
 */
public class Player {
    private String name;
    private int money;
    private ArrayList<Animal> playerAnimal;
    private ArrayList<Food> foods;

    public Player(String name) {
        this.name = name;
        this.money = 5000;
        this.playerAnimal = new ArrayList<>();
        this.foods = new ArrayList<>();
    }


    public void feedAnimal(Animal animal) {
        //remove food from foodsList
        //remove money from player
        //add health to animal
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
                            //createAnimal(animal1, FEMALE); *female*
                        } else {
                            //createAnimal(animal1, MALE); *male*
                        }
                    }return true;

                } else {
                    // no babies, mating not successful
                }return false;

            }else{
                // animal same gender : can't get babies.
            }return false;

        }return false;
    }


    public String getName() {
        return name;
    }

    public void setPlayerAnimal(Animal animal) {
        this.playerAnimal.add(animal);
    }

    public ArrayList<Animal> getPlayerAnimal() {
        return this.playerAnimal;
    }

    public void removePlayerAnimal(Animal animal){
        this.playerAnimal.remove(animal);
    }

    public int getMoney() {
        return money;
    }
}

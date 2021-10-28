package animalgame;

import animalgame.animals.abstractmodels.Animal;
import animalgame.food.abstractmodels.Food;
import animalgame.utilities.ProgramUtils;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author
 */
public class Player implements Serializable {
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

    /**
     * Checks if the animal eats the food it is given.
     * Then checks if the player has the amount of food that they try to give the animal.
     * Add 10% health per kilo food and remove food from players
     * foodslist if the animal eats the food.
     * @param animal as an animal object.
     * @param food as an food object.
     * @param weight as an int.
     */
    public void feedAnimal(Animal animal, Food food, int weight) {
        if(animal.eat(food)){
            if(weight <= food.getWeight()){
                int f = weight*10;
                animal.setHealth(animal.getHealth()+f);
                if(animal.getHealth() >= 100){
                    animal.setHealth(100);
                }
                if(weight == food.getWeight()){
                    this.foods.remove(food);
                }else{
                    food.removeWeight(weight);
                }
            }else{
                System.out.println("You don't have " +weight + "kg " +food.getName().toLowerCase() + " to give " +animal.getName());
            }

        }
    }


    public boolean gameOverCheck(){
        if (this.money == 0 && (this.playerAnimal.size() == 0)) {
            //this.allPlayers.remove(this);
            System.out.println("\n".repeat(10));
            try {
                System.out.println(ProgramUtils.RED + "GAME OVER " + this.name + "!" + ProgramUtils.RESET);
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
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

    public void removeMoney(int money){
        this.money -= money;
    }

    public void addMoney(int money){
        this.money += money;
    }

    public void setMoney(int money){
        this.money = money;
    }

    public void setFoods(Food food){
        this.foods.add(food);
    }

    public ArrayList<Food> getFoods(){
        return this.foods;
    }


}

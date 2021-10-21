package animalgame;

import animalgame.animals.abstractmodels.Animal;
import animalgame.food.abstractmodels.Food;

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


    public void feedAnimal(Animal animal) {
        //remove food from foodsList
        //remove money from player
        //add health to animal
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

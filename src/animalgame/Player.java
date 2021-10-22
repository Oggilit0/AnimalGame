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


    public void feedAnimal(Animal animal, Food food) {
        //if(animal äter det den blir serverad){
        int f = (int)(animal.getHealth()*(10.0f/100.0f));
        animal.setHealth(f);
        this.foods.remove(food);
        //beroende på KG
        //set health
        //getPlayerAnimal(animal.getHealth() + );
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

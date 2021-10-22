package animalgame;

import animalgame.animals.Cat;
import animalgame.animals.Cow;
import animalgame.animals.abstractmodels.Animal;
import animalgame.food.Fish;
import animalgame.food.Grass;
import animalgame.food.Meat;
import animalgame.food.abstractmodels.Food;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

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
        if(animal.eat(food)){
            this.foods.remove(food);
        }else{

        }
        //int f = (int)(getPlayerAnimal(animal.getHealth())  +   food.getWeight()*(10.0f/100.0f));
        }
            //int f = (int)(getPlayerAnimal() + food.getWeight()*(10.0f/100.0f));
            //animal.setHealth(f);
        //beroende på KG
        //getPlayerAnimal(animal.getHealth() + );



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

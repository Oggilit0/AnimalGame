package animalgame;

import animalgame.animals.abstractmodels.Animal;
import animalgame.enums.Gender;
import animalgame.food.Taco;
import animalgame.food.abstractmodels.Food;
import animalgame.food.Waffles;
import animalgame.food.Sausage;
import animalgame.utilities.Factory;
import animalgame.utilities.ProgramUtils;

/**
 * This is the Store Class where we have a customer who can buy food and also buy and sell animals.
 * @author Sebastian Banfi, Oskar Herdenberg, Mathilda Nilsson, Hanna Petersson
 */
public class Store {
    private Player customer;

   public void setCustomer(Player customer){
       this.customer = customer;
   }

    public Store(){
    }

    public boolean animalToBuy(String animal, Gender gender, int price){
       if(this.customer.getMoney() < price){
           System.out.println("Not enough money! The animal costs "+price+" And you have "+customer.getMoney());
           return false;
       }else{
            customer.removeMoney(price);
            customer.addPlayerAnimal(Factory.createAnimal(animal,gender));
            return true;
       }
    }

    public void foodToBuy(String food, int foodPrice){
        System.out.println("How many kg do you want to buy?");
        int amountToBuy = ProgramUtils.tryCatch();
        int sum = foodPrice * amountToBuy;
        if(this.customer.getMoney() >= sum){
            boolean containFoodType = false;
            for(Food foodType : this.customer.getFoods()){
                if (foodType.getName().equals(food)){
                    foodType.addWeight(amountToBuy);
                    containFoodType = true;
                }
            }
            Food newFood = null;
            if (!containFoodType){
                switch(food){
                    case "Sausage":
                        newFood = new Sausage("Sausage", amountToBuy);
                        break;
                    case "Taco":
                        newFood = new Taco("Taco", amountToBuy);
                        break;
                    case "Waffles":
                        newFood = new Waffles("Waffles", amountToBuy);
                        break;
                        default:
                }
                this.customer.setFoods(newFood);
                this.customer.removeMoney(sum);
            }
        }else{
            System.out.println("Can't afford $" + sum + " with your $"+this.customer.getMoney());
        }
    }

    public void animalToSell(Animal animal){
        int animalPrice = animal.animalSellPrice();
        this.customer.addMoney(animalPrice);
        this.customer.getPlayerAnimal().remove(animal);
   }
}


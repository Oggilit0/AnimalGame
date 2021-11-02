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

    /**
     * animalsToBuy checks if the player can buy tha animal they chose and then calls on {@link Factory#createAnimal}
     * if they can, else they will get a message and skip their round.
     * @param animal gets the Animal they chose
     * @param gender gets the enum gender to createAnimal.
     * @param price gets an int value of price
     * @return boolean depending on if they cna buy the animal or not.
     */
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

    /**
     * sells the animal that the player has chosen from the menu.
     * @param animal is the animal chosen from menu.
     */
    public void animalToSell(Animal animal){
        int animalPrice = animal.animalSellPrice();
        this.customer.addMoney(animalPrice);
        this.customer.getPlayerAnimal().remove(animal);
    }

    /**
     * setter for customer, to get currentPlayer.
     * @param customer gets Player customer/currentPlayer
     */
    public void setCustomer(Player customer){
        this.customer = customer;
    }

    /**
     * foodToBuy gets food and price from menu and then asks how much they want, but if they don't have enough money
     * for that amount of food they get a message.
     * If they can buy the food, it's added to players/customers inventory.
     * @param food gets string value of food depending on what food in on the menu.
     * @param foodPrice gets the int value of the food type they chose.
     */
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
}


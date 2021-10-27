package animalgame;

import animalgame.animals.abstractmodels.Animal;
import animalgame.enums.Gender;
import animalgame.food.Taco;
import animalgame.food.abstractmodels.Food;
import animalgame.food.Waffles;
import animalgame.food.Sausage;
import animalgame.utilities.Factory;
import animalgame.utilities.ProgramUtils;

public class Store {
    // private AnimalFactory factory;
    private Player customer;

   public void setCustomer(Player customer){
       this.customer = customer;
   }

    public Store(){
        //this.player= player;

    }

    public boolean animalToBuy(String animal, Gender gender, int price){
       if(this.customer.getMoney() < price){
           System.out.println("Not enough money! The animal costs "+price+" And you have "+customer.getMoney());
           return false;
       }else{
            customer.removeMoney(price);
            customer.setPlayerAnimal(Factory.createAnimal(animal,gender));
            return true;
       }
    }

    public void foodToBuy(String food, int foodPrice){
        System.out.println("How many kg do you want to buy?");
        int amountToBuy = ProgramUtils.tryCatch(ProgramUtils.userInput());
        int sum = foodPrice * amountToBuy;

        if(this.customer.getMoney() >= sum){
            boolean containFoodType = false;
            for(Food foodType : this.customer.getFoods()){
                if (foodType.getName().equals(food)){
                    foodType.addWeight(amountToBuy);
                    containFoodType = true;
                }
            }
            if (!containFoodType && food.equals("Sausage")){
                Food sausage = new Sausage("Sausage", amountToBuy);
                this.customer.setFoods(sausage);
            }else if (!containFoodType && food.equals("Taco")){
                Food taco = new Taco("Taco", amountToBuy);
                this.customer.setFoods(taco);
            }else if (!containFoodType && food.equals("Waffles")){
                Food waffles = new Waffles("Waffles", amountToBuy);
                this.customer.setFoods(waffles);
            }
            this.customer.removeMoney(sum);
        }else{
            System.out.println("Can't afford $" + sum + " with your $"+this.customer.getMoney());
        }

        //Debug
        for(Food fooder : this.customer.getFoods()){

            System.out.println(fooder.getName() + " : "+fooder.getWeight());
        }
        //Debug
    }


    public void animalToSell(Animal animal){
       int animalPrice = animal.getAnimalPrice()* (animal.getHealth()/100);
       this.customer.addMoney(animalPrice);
       this.customer.getPlayerAnimal().remove(animal);
   }

   //välja vilket djur man vill sälja, vilket djur han sålt och hur mycket pengar.
}       //häst,ta djuret och ta bort från spellistan
        //djur remove från player list
        //spelarens pengar ska plussas på djurets värde

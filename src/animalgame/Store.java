package animalgame;

import animalgame.animals.abstractmodels.Animal;
import animalgame.food.Fish;
import animalgame.food.abstractmodels.Food;
import animalgame.food.Grass;
import animalgame.food.Meat;
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

    public void animalToBuy(){


        // customer.deliverAnimals( animalsList )
    }

    public void foodToBuy(String food, int foodPrice){
        System.out.println("How many do you want to buy?");
        int amountToBuy = ProgramUtils.tryCatch(ProgramUtils.userInput());
        int sum = foodPrice * amountToBuy;

        if(this.customer.getMoney() >= sum || this.customer.getMoney() < 0){
            boolean containFoodType = false;
            for(Food foodType : this.customer.getFoods()){
                if (foodType.getName().equals(food)){
                    foodType.addWeight(amountToBuy);
                    containFoodType = true;
                }
            }
            if (!containFoodType && food.equals("Meat")){
                Food meat = new Meat("Meat", amountToBuy);
                this.customer.setFoods(meat);
            }else if (!containFoodType && food.equals("Fish")){
                Food fish = new Fish("Fish", amountToBuy);
                this.customer.setFoods(fish);
            }else if (!containFoodType && food.equals("Grass")){
                Food grass = new Grass("Grass", amountToBuy);
                this.customer.setFoods(grass);
            }

        }else{
            System.out.println("Can't afford $" + sum + " with your $"+this.customer.getMoney());
        }
        this.customer.removeMoney(sum);
        //Debug
        for(Food fooder : this.customer.getFoods()){

            System.out.println(fooder.getName() + " : "+fooder.getWeight());
        }
        //Debug
    }


    public void animalToSell(Animal animal){
       int animalPrice = animal.getAnimalPrice()* (animal.getHealth()/100);

       this.customer.getPlayerAnimal().remove(animal);
    }
} // häst , ta djuret och ta bort från spellistan
//djur remove från player list
//spelarens pengar ska plussas på djurets värde
//
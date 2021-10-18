package animalgame;

public class Store {

    private Player customer;

   public void setCustomer(Player customer){
       this.customer = customer;
   }

    public Store(){
        //this.player= player;

    }

    public void animalToBuy(){


    }

    public void foodToBuy(String food, int foodPrice){
        System.out.println("How many do you want to buy?");
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

        }
        //Debug
        for(Food fooder : this.customer.getFoods()){

            System.out.println(fooder.getName() + " : "+fooder.getWeight());
        }
        //Debug
    }


    public void animalToSell(){}
}

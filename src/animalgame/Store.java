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

    public void shopFood(String food){
        System.out.println("How many do you want to buy?");
        int amountToBuy = ProgramUtils.tryCatch(ProgramUtils.userInput());
        int sum = 50 * amountToBuy;

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

    public void foodToBuy(){
        // Vi måste kolla spelarens pengar
        // Vi måste fråga hur mycket han vill köpa
        // vi skall lägga till allt han köpt


       switch( ProgramUtils.menuBuilder("Avaible food","meat", "fish","grass")){

            case 1:
                shopFood("Meat");
                break;

            case 2:
                shopFood("Fish");
                break;

            case 3:
                shopFood("Grass");
                break;


        }

        // input parameter Food
        // create food object (Meat, Fish, Grass)
        // put food object in player inventory

        // check Game class method createAnimal for inspiration :)

    }
    public void animalToSell(){}
}

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
    public void foodToBuy(){

       switch( ProgramUtils.menuBuilder("Avaible food","meat", "fish","grass")){

            case 1:
                Food meat = new Meat("Meat", 1);
                this.customer.setFoods(meat);
                break;

            case 2:
                Food fish = new Fish("Fish", 1);
                this.customer.setFoods(fish);
                break;

            case 3:
                Food grass = new Grass("Grass", 1);
                this.customer.setFoods(grass);
                break;


        }

        // input parameter Food
        // create food object (Meat, Fish, Grass)
        // put food object in player inventory

        // check Game class method createAnimal for inspiration :)

    }
    public void animalToSell(){}
}

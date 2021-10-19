package animalgame.utilities;

import animalgame.Game;
import animalgame.animals.abstractmodels.Animal;
import animalgame.utilities.ProgramUtils;

public class Menu {
    private Game currentGame;

    public Menu(Game currentGame){
        this.currentGame = currentGame;
    }

    public void playerAnimalsAsMenu(){

        int i = 1;
        for(Animal animal : this.currentGame.getCurrentPlayer().getPlayerAnimal()) {
            System.out.println(i + ".\t" + animal.getClass().getName().substring(11) + " :  namn:" + animal.getName());
            i++;
        }
    }



    public void roundMenu(){

        switch(ProgramUtils.menuBuilder("Choose one","Shop","Feed animals","Mate animals")){
            case 1:
                shopMenu();
                break;
            case 2:
                feedAnimalsMenu();
                playerAnimalsAsMenu();
                break;
            case 3:
                mateAnimalsMenu();
                playerAnimalsAsMenu();
                break;
            default:
                roundMenu();
        }
    }

    public void shopMenu(){

        switch(ProgramUtils.menuBuilder("Shop","Buy animals","Buy Food","Sell animals")){
            case 1:
                animalChoice();
                break;
               // this.currentGame.getStore().animalToBuy();
            case 2:
                this.currentGame.getStore().setCustomer(this.currentGame.getCurrentPlayer());
                shopFoodMenu();

                break;

            case 3:
               // this.currentGame.getStore().animalToSell();
                break;
            default:
        }
    }
    public void shopFoodMenu(){

        switch( ProgramUtils.menuBuilder("Avaible food","meat", "fish","grass")){

            case 1:
                this.currentGame.getStore().foodToBuy("Meat",50);
                break;

            case 2:
                this.currentGame.getStore().foodToBuy("Fish",100);
                break;

            case 3:
                this.currentGame.getStore().foodToBuy("Grass",20);
                break;


        }
    }

    public void animalChoice(){

        switch(ProgramUtils.menuBuilder("AnimalChoice","cat","Dog","Cow","Horse","Snake")){
            case 1:
                String cat = "Cat";

        }
        this.currentGame.getStore().animalToBuy();
    }

    public void feedAnimalsMenu(){

        System.out.println("Choose which animals to feed");

    }

    public void mateAnimalsMenu(){

        System.out.println("choose which animal to mate etc");
    }


}

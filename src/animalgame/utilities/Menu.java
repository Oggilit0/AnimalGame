package animalgame.utilities;

import animalgame.Game;
import animalgame.Player;
import animalgame.animals.Cat;
import animalgame.animals.abstractmodels.Animal;
import animalgame.enums.Gender;
import animalgame.food.abstractmodels.Food;
import animalgame.utilities.ProgramUtils;

import java.util.ArrayList;

public class Menu {
    private Game currentGame;

    public Menu(Game currentGame){
        this.currentGame = currentGame;
    }

    public void playerAnimalsAsMenu(){

        int i = 1;
        for(Animal animal : this.currentGame.getCurrentPlayer().getPlayerAnimal()) {
            System.out.println(i + ".\t" + animal.getClass().getName().substring(19) + " :  namn: " + animal.getName()+ " Gender: "+animal.getGender().toString().toLowerCase());
            i++;
        }
    }

    public void playerFoodAsMenu(){
        int i = 1;
        for(Food food : this.currentGame.getCurrentPlayer().getFoods()){
            System.out.println(i + ".\t Amount of weight: " + food.getWeight() + "KG  :  Type of food: " + food.getName());
            i++;
        }
    }



    public void roundMenu(){
        switch(ProgramUtils.menuBuilder("\nChoose one","Shop","Feed animals","Mate animals", "Save game", "print data")){
            case 1:
                this.currentGame.getStore().setCustomer(this.currentGame.getCurrentPlayer());
                shopMenu();
                break;
            case 2:
                feedAnimalsMenu();
                playerAnimalsAsMenu();
                break;
            case 3:
                mateAnimalsMenu();
                break;
            case 4:
                this.currentGame.saveGame();
            case 5:

                for(Animal animal : this.currentGame.getCurrentPlayer().getPlayerAnimal()){
                    System.out.println("Animal: " + animal.getClass().getName().substring(19) + ". Name:"+animal.getName());
                }

                for(Food food : this.currentGame.getCurrentPlayer().getFoods()){
                    System.out.println("Food: " + food.getClass().getName().substring(16) + ". Amount:"+food.getWeight() + " kg");
                }
                roundMenu();
            default:
                roundMenu();
        }
    }

    public void newGameMenu(){
        switch (ProgramUtils.menuBuilder("\nStart menu","New game","Load game")){
            case 1:
                this.currentGame.startGame();
                break;
            case 2:
                this.currentGame.loadGame();
                break;
            default:
                newGameMenu();
        }
    }

    public void shopMenu(){

        switch(ProgramUtils.menuBuilder("\nShop","Buy animals","Buy Food","Sell animals")){
            case 1:
                animalChoice();
                break;
               // this.currentGame.getStore().animalToBuy();
            case 2:
                shopFoodMenu();
                break;

            case 3:
               // this.currentGame.getStore().animalToSell();
                break;
            default:
                shopMenu();
        }
    }
    public void shopFoodMenu(){

        switch( ProgramUtils.menuBuilder("\nAvailable food","meat", "fish","grass")){

            case 1:
                this.currentGame.getStore().foodToBuy("Meat",50);
                break;

            case 2:
                this.currentGame.getStore().foodToBuy("Fish",100);
                break;

            case 3:
                this.currentGame.getStore().foodToBuy("Grass",20);
                break;
            default:
                shopFoodMenu();
        }
    }

    public void animalChoice(){
        switch(ProgramUtils.menuBuilder("\nBuyAnimal","cat"+": 1000 Gold","Dog"+": 1000 Gold","Cow"+": 1000 Gold","Horse"+": 1000 Gold","Snake"+": 1000 Gold")){
            case 1:
                switch (ProgramUtils.menuBuilder("\nGenderChoice", "MALE","FEMALE")){
                    case 1:
                        if(!currentGame.getStore().animalToBuy("Cat",Gender.MALE,1000)){
                            animalChoice();
                    }
                        break;
                    case 2:
                        if(!currentGame.getStore().animalToBuy("Cat",Gender.FEMALE,1000)){
                            animalChoice();
                        }
                        break;
                }
                break;
            case 2:
                switch (ProgramUtils.menuBuilder("\nGenderChoice", "MALE","FEMALE")){
                    case 1:
                        if(!currentGame.getStore().animalToBuy("Dog",Gender.MALE,1000)){
                            animalChoice();
                        }
                        break;
                    case 2:
                        if(!currentGame.getStore().animalToBuy("Dog",Gender.FEMALE,1000)){
                            animalChoice();
                        }
                        break;
                }
                break;
            case 3:
                switch (ProgramUtils.menuBuilder("\nGenderChoice", "MALE","FEMALE")){
                    case 1:
                        if(!currentGame.getStore().animalToBuy("Cow",Gender.MALE,1000)){
                            animalChoice();
                        }
                        break;
                    case 2:
                        if(!currentGame.getStore().animalToBuy("Cow",Gender.FEMALE,1000)){
                            animalChoice();
                        }
                        break;
                }
                break;
            case 4:
                switch (ProgramUtils.menuBuilder("\nGenderChoice", "MALE","FEMALE")){
                    case 1:
                        if(!currentGame.getStore().animalToBuy("Horse",Gender.MALE,1000)){
                            animalChoice();
                        }
                        break;
                    case 2:
                        if(!currentGame.getStore().animalToBuy("Horse",Gender.FEMALE,1000)){
                            animalChoice();
                        }
                        break;
                }
                break;
            case 5:
                switch (ProgramUtils.menuBuilder("\nGenderChoice", "MALE","FEMALE")){
                    case 1:
                        if(!currentGame.getStore().animalToBuy("Snake",Gender.MALE,1000)){
                            animalChoice();
                        }
                        break;
                    case 2:
                        if(!currentGame.getStore().animalToBuy("Snake",Gender.FEMALE,1000)){
                            animalChoice();
                        }
                        break;
                }
            default:
                animalChoice();
        }
    }

    public void feedAnimalsMenu(){
        System.out.println("Choose which animals to feed: ");
        playerAnimalsAsMenu();
        System.out.println("Choose which food to feed your animal with: ");
        playerFoodAsMenu();
        System.out.println("How many kg do you want to feed your animal: ");
        //currentGame.getCurrentPlayer().feedAnimal();

    }

    public void mateAnimalsMenu(){
        if (currentGame.getCurrentPlayer().getPlayerAnimal().size() < 2) {
            System.out.println("\nyou need 2 or more animals!");
            System.out.println(currentGame.getCurrentPlayer().getPlayerAnimal().size());
            roundMenu();
        }else{
            playerAnimalsAsMenu();
            int menuChoice = 0;
            do {
                menuChoice = ProgramUtils.tryCatch(ProgramUtils.userInput());
                System.out.print("Write a number: ");

            }while (menuChoice < 1 || menuChoice > currentGame.getCurrentPlayer().getPlayerAnimal().size());
            System.out.println("success");

        }
    }
}

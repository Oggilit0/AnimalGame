package animalgame.utilities;

import animalgame.Game;
import animalgame.animals.abstractmodels.Animal;
import animalgame.enums.Gender;
import animalgame.food.abstractmodels.Food;

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

    /**
     * Prints out the players amount and type of food they have bought.
     */
    public void playerFoodAsMenu(){
        int i = 1;
        for(Food food : this.currentGame.getCurrentPlayer().getFoods()){
            System.out.println(i + ".\t Amount of weight: " + food.getWeight() + "KG  :  Type of food: " + food.getName());
            i++;
        }
    }



    public void roundMenu(){
        switch(ProgramUtils.menuBuilder("\nChoose one","Shop","Feed animals","Mate animals", "Save game","Debug")){
            case 1:
                this.currentGame.getStore().setCustomer(this.currentGame.getCurrentPlayer());
                shopMenu();
                break;
            case 2:
                feedAnimalsMenu();
                break;
            case 3:
                mateAnimalsMenu();
                break;
            case 4:
                this.currentGame.saveGame();
            case 5:

                for(Animal animal : this.currentGame.getCurrentPlayer().getPlayerAnimal()){
                    System.out.println("Animal: " + animal.getClass().getName().substring(19) + ". Name:"+animal.getName() + " " + animal.getHealth());
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

        switch( ProgramUtils.menuBuilder("\nAvailable food","Sausage", "Taco","Waffles")){

            case 1:
                this.currentGame.getStore().foodToBuy("Sausage",50);
                break;

            case 2:
                this.currentGame.getStore().foodToBuy("Taco",100);
                break;

            case 3:
                this.currentGame.getStore().foodToBuy("Waffles",20);
                break;
            default:
                shopFoodMenu();
        }
    }

    public void animalChoice(){
        switch(ProgramUtils.menuBuilder("\nBuyAnimal","Ferret"+": 1000 Gold","Giraffe"+": 1000 Gold","Mexican Alligator Lizard"+": 1000 Gold","Polar Bear"+": 1000 Gold","Troll"+": 1000 Gold")){
            case 1:
                switch (ProgramUtils.menuBuilder("\nGenderChoice", "MALE","FEMALE")){
                    case 1:
                        if(!currentGame.getStore().animalToBuy("Ferret",Gender.MALE,1000)){
                            animalChoice();
                        }
                        break;
                    case 2:
                        if(!currentGame.getStore().animalToBuy("Ferret",Gender.FEMALE,1000)){
                            animalChoice();
                        }
                        break;
                }
                break;
            case 2:
                switch (ProgramUtils.menuBuilder("\nGenderChoice", "MALE","FEMALE")){
                    case 1:
                        if(!currentGame.getStore().animalToBuy("Giraffe",Gender.MALE,1000)){
                            animalChoice();
                        }
                        break;
                    case 2:
                        if(!currentGame.getStore().animalToBuy("Giraffe",Gender.FEMALE,1000)){
                            animalChoice();
                        }
                        break;
                }
                break;
            case 3:
                switch (ProgramUtils.menuBuilder("\nGenderChoice", "MALE","FEMALE")){
                    case 1:
                        if(!currentGame.getStore().animalToBuy("MexicanAlligatorLizard",Gender.MALE,1000)){
                            animalChoice();
                        }
                        break;
                    case 2:
                        if(!currentGame.getStore().animalToBuy("MexicanAlligatorLizard",Gender.FEMALE,1000)){
                            animalChoice();
                        }
                        break;
                }
                break;
            case 4:
                switch (ProgramUtils.menuBuilder("\nGenderChoice", "MALE","FEMALE")){
                    case 1:
                        if(!currentGame.getStore().animalToBuy("PolarBear",Gender.MALE,1000)){
                            animalChoice();
                        }
                        break;
                    case 2:
                        if(!currentGame.getStore().animalToBuy("PolarBear",Gender.FEMALE,1000)){
                            animalChoice();
                        }
                        break;
                }
                break;
            case 5:
                switch (ProgramUtils.menuBuilder("\nGenderChoice", "MALE","FEMALE")){
                    case 1:
                        if(!currentGame.getStore().animalToBuy("Troll",Gender.MALE,1000)){
                            animalChoice();
                        }
                        break;
                    case 2:
                        if(!currentGame.getStore().animalToBuy("Troll",Gender.FEMALE,1000)){
                            animalChoice();
                        }
                        break;
                }
            default:
                animalChoice();
        }
    }

    public void feedAnimalsMenu() {
        if (currentGame.getCurrentPlayer().getPlayerAnimal().size() == 0) {
            System.out.println("You must have one animal to feed!");

        } else if (currentGame.getCurrentPlayer().getFoods().size() == 0) {
            System.out.println("You must buy food to feed your animal with!");

        } else {
            System.out.println("Choose which animals to feed: ");
            playerAnimalsAsMenu();
            int animalChoice = ProgramUtils.tryCatch(ProgramUtils.userInput());
            System.out.println("Choose which food to feed your animal with: ");
            playerFoodAsMenu();
            int foodChoice = ProgramUtils.tryCatch(ProgramUtils.userInput());
            System.out.println("How many kg do you want to feed your animal: ");
            int kgChoice = ProgramUtils.tryCatch(ProgramUtils.userInput());
            currentGame.getCurrentPlayer().feedAnimal(currentGame.getCurrentPlayer().getPlayerAnimal().get(animalChoice-1),currentGame.getCurrentPlayer().getFoods().get(foodChoice-1), kgChoice);
        }
    }

    public void mateAnimalsMenu(){
        if (currentGame.getCurrentPlayer().getPlayerAnimal().size() < 2) {
            System.out.println("you currently have "+currentGame.getCurrentPlayer().getPlayerAnimal().size()+" animals");
            System.out.println("\nyou need 2 or more animals!");
            roundMenu();
        }else{
            playerAnimalsAsMenu();
            int menuChoice = 0;
            int otherChoice = 0;
            do {
                ArrayList<Animal> newAnimalList = new ArrayList<>();
                System.out.print("First animal to breed: ");
                menuChoice = ProgramUtils.tryCatch(ProgramUtils.userInput());
                newAnimalList.add(currentGame.getCurrentPlayer().getPlayerAnimal().get(menuChoice-1));
                System.out.print("Write the second animal to breed: ");
                otherChoice = ProgramUtils.tryCatch(ProgramUtils.userInput());
                if(otherChoice == menuChoice){
                    System.out.println("\nCan't choose the same animal!\n");
                }else{
                    newAnimalList.add(currentGame.getCurrentPlayer().getPlayerAnimal().get(otherChoice-1));
                    Factory.tryMating(newAnimalList.get(0),newAnimalList.get(1),currentGame.getCurrentPlayer());
                }
            }while (menuChoice < 1 || menuChoice > currentGame.getCurrentPlayer().getPlayerAnimal().size() || menuChoice == otherChoice);
        }
    }
}

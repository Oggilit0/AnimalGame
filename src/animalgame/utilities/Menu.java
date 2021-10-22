package animalgame.utilities;

import animalgame.Game;
import animalgame.Player;
import animalgame.animals.Cat;
import animalgame.animals.abstractmodels.Animal;
import animalgame.enums.Gender;
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



    public void roundMenu(){
        switch(ProgramUtils.menuBuilder("\nChoose one","Shop","Feed animals","Mate animals")){
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
            default:
                roundMenu();
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
        }
    }

    public void feedAnimalsMenu(){

        System.out.println("Choose which animals to feed");

    }

    public void mateAnimalsMenu(){
        ArrayList<Animal> newAnimalList = new ArrayList<>();
        if (currentGame.getCurrentPlayer().getPlayerAnimal().size() < 2) {
            System.out.println("\nyou need 2 or more animals!");
            System.out.println(currentGame.getCurrentPlayer().getPlayerAnimal().size());
            roundMenu();
        }else{
            playerAnimalsAsMenu();
            int menuChoice = 0;
            int otherChoice = 0;
            System.out.print("First animal to breed: ");
            do {
                menuChoice = ProgramUtils.tryCatch(ProgramUtils.userInput());
                    newAnimalList.add(currentGame.getCurrentPlayer().getPlayerAnimal().get(menuChoice-1));
                    System.out.print("Write the second animal to breed: ");
                    otherChoice = ProgramUtils.tryCatch(ProgramUtils.userInput());
                    if(otherChoice == menuChoice){
                        System.out.println("Can't choose the same animal!\n");
                        System.out.print("First animal to breed: ");
                    }else{
                        newAnimalList.add(currentGame.getCurrentPlayer().getPlayerAnimal().get(otherChoice-1));
                        Factory.tryMating(newAnimalList.get(0),newAnimalList.get(1),currentGame.getCurrentPlayer());
                    }
            }while (menuChoice < 1 || menuChoice > currentGame.getCurrentPlayer().getPlayerAnimal().size() || menuChoice == otherChoice);
        }
    }

}

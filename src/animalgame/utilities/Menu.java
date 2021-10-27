package animalgame.utilities;

import animalgame.Game;
import animalgame.animals.Ferret;
import animalgame.animals.abstractmodels.Animal;
import animalgame.enums.Gender;
import animalgame.food.Sausage;
import animalgame.food.Taco;
import animalgame.food.abstractmodels.Food;

import javax.swing.text.TabableView;
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

    /**
     * Takes an animal as a string and checks what kind of food that animal eats.
     * @param animal
     * @return what animal eats as a String
     */
    public String whatAnimalEats(String animal){
        switch (animal){
            case "Ferret":
                return "Taco and Sausage";
            case "Giraffe":
                return "Waffles";
            case "Mexican_Alligator_Lizard":
                return "Taco";
            case "PolarBear":
                return "Sausage";
            case "Troll":
                return "Waffles and Taco";
            default:
                return"";
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
                saveGameMenu();
            case 5:

                for(Animal animal : this.currentGame.getCurrentPlayer().getPlayerAnimal()){
                    System.out.println("Animal: " + animal.getClass().getName().substring(19) + " Name:"+animal.getName() + " " + animal.getHealth());
                }

                for(Food food : this.currentGame.getCurrentPlayer().getFoods()){
                    System.out.println("Food: " + food.getClass().getName().substring(16) + " Amount:"+food.getWeight() + " kg");
                }
                roundMenu();
            default:
                roundMenu();
        }
    }

    public void saveGameMenuHelper(String menuOptionName, int menuIndex){

        if(!menuOptionName.equals("")){
            System.out.println("Are you sure you want to delete your saved game? y/n");
            String yesOrNo = ProgramUtils.userInput();
            if(yesOrNo.equalsIgnoreCase("y") ){
                ProgramUtils.DeleteFile(ProgramUtils.readAllLines().get(menuIndex-1));
            }else{
                saveGameMenu();
            }
        }
        System.out.println("Write the name of your save file");
        String fileName = ProgramUtils.userInput();
        this.currentGame.saveGame(fileName);


        ProgramUtils.writeFromSaveFile(fileName,menuIndex);
    }


    public void saveGameMenu(){
        String[] menuArray = {"","",""};
        for(int i = 0; i < ProgramUtils.readAllLines().size() ; i++){
            menuArray[i] = ProgramUtils.readAllLines().get(i);
        }

        switch (ProgramUtils.menuBuilder("\nSave game)",menuArray[0],menuArray[1],menuArray[2], "Back")){
            case 1:
                saveGameMenuHelper(menuArray[0],1);
                break;
            case 2:
                saveGameMenuHelper(menuArray[1],2);
                break;
            case 3:
                saveGameMenuHelper(menuArray[2],3);
                break;
            case 4:
                roundMenu();
                break;
            default:
                break;
        }
    }

    public void newGameMenu(){
        switch (ProgramUtils.menuBuilder("\nStart menu","New game","Load game")){
            case 1:
                this.currentGame.startGame();
                break;
            case 2:
                loadGameMenu();
                break;
            default:
                newGameMenu();
        }
    }

    public boolean continueMenu(String type, String buySell){

        switch(ProgramUtils.menuBuilder("\nContinue to "+buySell+ " " + type +" ?","Yes","No")){
            case 1:
                return true;
            case 2:
                return false;
            default:
        }
        return true;
    }


    public void shopMenu(){
        switch(ProgramUtils.menuBuilder("\nShop","Buy animals","Buy Food","Sell animals")){
            case 1:
                do{
                    animalChoice();

                }while(continueMenu("animal","buy"));

                break;
            case 2:
                do{
                    shopFoodMenu();

                }while(continueMenu("food","buy"));

                break;
            case 3:

                do{
                    // this.currentGame.getStore().animalToSell();

                }while(continueMenu("animal","sell"));

                break;
            default:
                shopMenu();
        }
    }
    public void shopFoodMenu(){

        String food ="";
        int price = 0;

        switch( ProgramUtils.menuBuilder("\nAvailable food","Sausage" + ": 20 Gold/kg", "Waffles" + ": 50 Gold/kg","Taco" + ":    100 Gold/kg")){

            case 1:
                food = "Sausage";
                price = 20;
                break;

            case 2:
                food = "Waffles";
                price = 50;
                break;

            case 3:
                food = "Taco";
                price = 100;
                break;
            default:
                shopFoodMenu();
        }

        this.currentGame.getStore().foodToBuy(food,price);

    }

    public Gender genderSelectionMenu(){

        switch (ProgramUtils.menuBuilder("\nGenderChoice", "MALE","FEMALE")){
            case 1:
                return Gender.MALE;
            case 2:
                return Gender.FEMALE;
            default:
                return null;
        }
    }

    public void animalChoice(){

        String animal ="";
        Gender gender = null;
        int price = 0;

        switch(ProgramUtils.menuBuilder("\nBuyAnimal","Troll"+":   800 Gold","Giraffe"+": 1000 Gold","Polar bear"+": 1500 Gold","Ferret"+":     2250 Gold","Mexican Alligator Lizard"+": 4000 Gold")){

            case 1:
                animal = "Troll";
                price = 800;
                gender = genderSelectionMenu();
                break;
            case 2:
                animal = "Giraffe";
                price = 1000;
                gender = genderSelectionMenu();
                break;
            case 3:
                animal = "PolarBear";
                price = 1500;
                gender = genderSelectionMenu();
                break;
            case 4:
                animal = "Ferret";
                price = 2250;
                gender = genderSelectionMenu();
                break;
            case 5:
                animal = "Mexican_Alligator_Lizard";
                price = 4000;
                gender = genderSelectionMenu();
                break;
            default:
                animalChoice();
        }

        if(!currentGame.getStore().animalToBuy(animal,gender,price)){
            animalChoice();
        }

    }

    public void loadGameMenu(){

        String[] menuArray = {"","",""};
        for(int i = 0; i < ProgramUtils.readAllLines().size() ; i++){
            menuArray[i] = ProgramUtils.readAllLines().get(i);
        }

        switch(ProgramUtils.menuBuilder("\nLoad game",menuArray[0],menuArray[1],menuArray[2], "Back")){
            case 1:
                this.currentGame.loadGame(ProgramUtils.readAllLines().get(0));
            case 2:
                this.currentGame.loadGame(ProgramUtils.readAllLines().get(1));
            case 3:
                this.currentGame.loadGame(ProgramUtils.readAllLines().get(2));
            case 4:
                newGameMenu();
                break;
            default:
                loadGameMenu();
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
            System.out.println(currentGame.getCurrentPlayer().getPlayerAnimal().get(animalChoice-1).getName() + " likes to eat: " + whatAnimalEats(currentGame.getCurrentPlayer().getPlayerAnimal().get(animalChoice-1).getClass().toString().substring(25)));
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

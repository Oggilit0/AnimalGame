package animalgame.utilities;

import animalgame.Game;
import animalgame.animals.abstractmodels.Animal;
import animalgame.enums.Gender;
import animalgame.food.abstractmodels.Food;
import java.util.ArrayList;

/**
 * Menu Class
 * @author Sebastian Banfi, Oskar Herdenberg, Mathilda Nilsson, Hanna Petersson
 */
public class Menu {
    private Game currentGame;

    public Menu(Game currentGame){
        this.currentGame = currentGame;
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

    public void loadGameMenu(){
        if(ProgramUtils.readAllLines().size() == 0){
            System.out.println("No files saved, starting game...");
            currentGame.startGame();
        }
        for(String files : ProgramUtils.readAllLines()){
            switch(ProgramUtils.menuBuilder("\nLoad game",files, "Back")){
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
    }

    public void roundMenu(){
        switch(ProgramUtils.menuBuilder("\nChoose one","Shop","Feed animals","Mate animals", "Save game")){
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
            default:
                roundMenu();
        }
    }

    private boolean continueMenu(String type, String buySell){
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
                    if(this.currentGame.getCurrentPlayer().getMoney() <= 0){
                        break;
                    }
                    animalChoice();
                }while(continueMenu("animal","buy"));
                break;
            case 2:
                do{
                    if(this.currentGame.getCurrentPlayer().getMoney() <= 0){
                        break;
                    }
                    shopFoodMenu();
                }while(continueMenu("food","buy"));
                break;
            case 3:
                boolean test;
                if(this.currentGame.getCurrentPlayer().getPlayerAnimal().size() == 0){
                    System.out.println("No animal to sell!");
                    shopMenu();
                }else{
                    do{
                        boolean test2 = animalSellMenu();
                        if(test2){
                            test = continueMenu("animal", "sell");
                        }else{
                            test = false;
                        }
                    }while(test);
                }
                break;
            default:
                shopMenu();
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

    /**
     * Prints out a menu to show the player what food they can buy and the price. The player
     * chooses what food to buy and the food will be added in to the player food list and remove
     * money from the player.
     */
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

    /**
     * Checks that the player has at least one animal to sell if not returns false and an error output to console.
     * If one or more animals exists the player can choose which animal to sell.
     * @return false if no animal exists in players animal list, true if player has animals to sell
     */
    public boolean animalSellMenu() {
        if(this.currentGame.getCurrentPlayer().getPlayerAnimal().size() == 0){
            System.out.println("No animal to sell!");
            return false;
        }else{
            ArrayList<Animal>animalList = this.currentGame.getCurrentPlayer().getPlayerAnimal();
            System.out.println("Choose which animal to sell");
            playerAnimalsAsMenu();
            int menuChoice = ProgramUtils.tryCatch(1,animalList.size());
            this.currentGame.getStore().animalToSell(animalList.get(menuChoice-1));
            return true;
        }
    }

    /**
     * Takes an animal as a string and checks what kind of food that animal eats.
     * @param animal as a String
     * @return what animal eats as a String
     */
    private String whatAnimalEats(String animal){
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

    /**
     * Checks so the player at least have one animal and one item of food to feed the animal with.
     * Then asks the player to choose which of their animal to feed, what food and how many kilos
     * of that food to feed the animal with. Calls the feedAnimal method...
     */
    public void feedAnimalsMenu() {
        if (currentGame.getCurrentPlayer().getPlayerAnimal().size() == 0) {
            System.out.println("You must have one animal to feed!");
        } else if (currentGame.getCurrentPlayer().getFoods().size() == 0) {
            System.out.println("You must buy food to feed your animal with!");
        } else {
            System.out.println("Choose which animals to feed: ");
            playerAnimalsAsMenu();
            int animalChoice = ProgramUtils.tryCatch();
            System.out.println(currentGame.getCurrentPlayer().getPlayerAnimal().get(animalChoice-1).getName() + " likes to eat: " + whatAnimalEats(currentGame.getCurrentPlayer().getPlayerAnimal().get(animalChoice-1).getClass().getSimpleName()));
            System.out.println("Choose which food to feed your animal with: ");
            playerFoodAsMenu();
            int foodChoice = ProgramUtils.tryCatch(1,this.currentGame.getCurrentPlayer().getFoods().size());
            System.out.println("How many kg do you want to feed your animal: ");
            int kgChoice = ProgramUtils.tryCatch();
            currentGame.getCurrentPlayer().feedAnimal(currentGame.getCurrentPlayer().getPlayerAnimal().get(animalChoice-1),currentGame.getCurrentPlayer().getFoods().get(foodChoice-1), kgChoice);
        }
    }

    public void mateAnimalsMenu(){
        if (currentGame.getCurrentPlayer().getPlayerAnimal().size() < 2) {
            System.out.println(ProgramUtils.RED+"You currently have "+currentGame.getCurrentPlayer().getPlayerAnimal().size()+" animal, you need 2 or more animals!"+ProgramUtils.RESET);
            System.out.println(" ");
            roundMenu();
        }else{
            playerAnimalsAsMenu();
            int menuChoice = 0;
            int otherChoice = 0;
            do {
                ArrayList<Animal> newAnimalList = new ArrayList<>();
                System.out.print("First animal to breed: ");
                menuChoice = ProgramUtils.tryCatch();
                newAnimalList.add(currentGame.getCurrentPlayer().getPlayerAnimal().get(menuChoice-1));
                System.out.print("Write the second animal to breed: ");
                otherChoice = ProgramUtils.tryCatch();
                if(otherChoice == menuChoice){
                    System.out.println("\nCan't choose the same animal!\n");
                }else{
                    newAnimalList.add(currentGame.getCurrentPlayer().getPlayerAnimal().get(otherChoice-1));
                    Factory.tryMating(newAnimalList.get(0),newAnimalList.get(1),currentGame.getCurrentPlayer());
                }
            }while (menuChoice < 1 || menuChoice > currentGame.getCurrentPlayer().getPlayerAnimal().size() || menuChoice == otherChoice);
        }
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

    /**
     * Prints out a summary of the current players animals to the console.
     */
    public void playerAnimalsAsMenu(){
        int i = 1;
        for(Animal animal : this.currentGame.getCurrentPlayer().getPlayerAnimal()) {
            System.out.println(i + ".\t" + animal.getClass().getName().substring(19) + " :     Name: " + ProgramUtils.PURPLE+animal.getName()+ProgramUtils.RESET+ "     Gender: "+animal.getGender().toString().toLowerCase());
            i++;
        }
    }

    /**
     * Prints out the current players amount and type of food they have bought to the console.
     */
    public void playerFoodAsMenu(){
        int i = 1;
        for(Food food : this.currentGame.getCurrentPlayer().getFoods()){
            System.out.println(i + ".\t Amount of weight: " + food.getWeight() + "KG  :  Type of food: " + food.getName());
            i++;
        }
    }

    private void saveGameMenuHelper(String menuOptionName, int menuIndex){
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

}

package animalgame;

import animalgame.animals.abstractmodels.Animal;
import animalgame.food.abstractmodels.Food;
import animalgame.utilities.Menu;
import animalgame.utilities.ProgramUtils;

import java.util.ArrayList;

public class Game {
    private ArrayList<Player> allPlayers;
    private int maxRound;
    private int currentRound;
    private int playerAmount;
    private Player currentPlayer;
    private Menu gameMenu;
    private Store store;

    public Game() {
        this.store = new Store();
        this.allPlayers = new ArrayList<>();
        this.gameMenu = new Menu(this);
        this.currentRound = 0;
        //Animal testKo = new Cow("TestKo",1000,10,Gender.FEMALE, currentPlayer);
        //Animal testKatt = new Horse( "Katten", 500, 5, Gender.MALE);
       //Animal testKatten = new Horse( "Katt", 55,5,  Gender.FEMALE);
       // Factory.tryMating(testKatt, testKatten, currentPlayer);
        //Factory.createAnimal("Cat", Gender.MALE);
        this.gameMenu.newGameMenu();
        //this is A COMMENT
        //hello
    }

    public void startGame() {
        choosePlayers();
        chooseRounds();
        newRound();
    }

    /**
     * User choose how many players he or she wants to play with
     * aslong as its between 2 and 4.
     * Calls on method to create each player if condition is true.
     * loops if input is wrong
     */
    public void choosePlayers(){
        boolean checkPlayerInputAmount;
        do {
            System.out.print("Write in how many players (Min 2 Max 4): ");
            this.playerAmount = ProgramUtils.tryCatch(ProgramUtils.userInput());

            if (playerAmount >= 1 && playerAmount <= 4) {
                for (int i = 0; i < playerAmount; i++) {
                    System.out.print("Write player " + (i + 1) + ": ");
                    createPlayer(ProgramUtils.userInput());
                }
                checkPlayerInputAmount = true;
            } else {
                System.out.println("Min 2 Max 4");
                checkPlayerInputAmount = false;
            }

        }while(!checkPlayerInputAmount);
        this.currentPlayer = this.allPlayers.get(0);
    }

    /**
     * User choose how many rounds he or she wants to play
     * aslong as its between 5 and 30.
     */
    public void chooseRounds(){
        boolean checkRoundInputAmount;
        do {
            System.out.print("\nWrite in how many rounds (Min 5 Max 30): ");
            this.maxRound = ProgramUtils.tryCatch(ProgramUtils.userInput());

            if (!(this.maxRound >= 5 && this.maxRound <= 30)) {
                System.out.println("Min 5 rounds and Max 30 rounds");
                checkRoundInputAmount = false;
            }else{
                checkRoundInputAmount = true;
            }
        }while(!checkRoundInputAmount);

        System.out.println("\n".repeat(30));
    }

    /**
     * Load saved game from file and set variables to 
     */
    public void loadGame(){
        SavedGame loadedGameObj = (SavedGame) ProgramUtils.readFile();
        this.currentPlayer = loadedGameObj.getSavedCurrentPlayer();
        this.maxRound = loadedGameObj.getSavedMaxRounds();
        this.allPlayers = loadedGameObj.getSavedPlayerList();
        this.currentRound = loadedGameObj.getSavedCurrentRound();
        this.playerAmount = loadedGameObj.getSavedPlayerList().size();
        newRound();
    }

    public void saveGame(){
        SavedGame saveGame = new SavedGame(this.allPlayers,this.currentPlayer,this.currentRound,this.maxRound);
        ProgramUtils.writeToFile(saveGame);
    }

    public void newRound() {
        //this.allPlayers.get(2).setMoney(0); a test to check that the player gets kicked when broke
        for (int r = currentRound; r <= this.maxRound; r++) {
            this.currentRound = r;
            if (currentRound != maxRound) {
                System.out.println(ProgramUtils.RED + "\nRound " + (r + 1) + ProgramUtils.RESET);
                ageAnimal();
                newRoundGetPlayer();
            } else {
                endGame();
            }
        }
    }





    public void gameOver() {
        for (Player winner : allPlayers) {
            System.out.println(ProgramUtils.GREEN + "The winner is " + winner.getName() + "!" + ProgramUtils.RESET + "\uD83D\uDC51");
            System.exit(1);
        }
    }

    public void endGame() {
        ArrayList<Integer> money = new ArrayList<>();
        ArrayList<Player> player = new ArrayList<>();
        int max = 0;
        for(Player players : allPlayers){
            for(Animal animal : players.getPlayerAnimal()){
             //will sell animal here later!
                money.add(players.getMoney());
                player.add(players);
                max = money.get(0);
            }
        }
        int n = money.size();
        for(int i = 1; i < n; i++){
            if(money.get(i) > max){
                max = money.get(i);
                System.out.println("\nThe winner is "+ player.get(i).getName()+" with "+max+ProgramUtils.YELLOW+"Gold"+ProgramUtils.YELLOW);
            }
        }
        System.out.println(ProgramUtils.RED + "\nGood Game!" + ProgramUtils.RESET);
    }

    public void newRoundGetPlayer() {
        for (int i = this.allPlayers.indexOf(this.currentPlayer); i < playerAmount; i++) {
            //TEST
            //createAnimal("Cat", Animal.Gender.MALE);
            //createAnimal("Cat", Animal.Gender.FEMALE);
            //TEST
            if (this.currentPlayer.getMoney() == 0 && (this.currentPlayer.getPlayerAnimal().size() == 0)) {
                this.allPlayers.remove(currentPlayer);
                System.out.println("\n".repeat(10));
                try {
                    System.out.println(ProgramUtils.RED + "GAME OVER " + currentPlayer.getName() + "!" + ProgramUtils.RESET);
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (this.allPlayers.size() != 1) {
                    break;
                } else {
                    gameOver();
                }
            }
            System.out.println("\n" + ProgramUtils.GREEN + currentPlayer.getName() + "'s Turn" + ProgramUtils.RESET + "\n");
            System.out.println(currentPlayer.getMoney() + ProgramUtils.YELLOW + " Gold" + ProgramUtils.RESET);
            for(Food food : currentPlayer.getFoods()){
                System.out.println(food.getName()+": "+food.getWeight()+" kg");
            }
            for (Animal animal : currentPlayer.getPlayerAnimal()) {
                animal.healthOverTime();
                System.out.println(animal.getClass().getName().substring(19)+": "+animal.getName()+", Health: "+ProgramUtils.RED+animal.getHealth()+ProgramUtils.RESET+", Age: "+ProgramUtils.PURPLE+animal.getCurrentAge()+ProgramUtils.RESET);
            }
            gameMenu.roundMenu();

            if (i != playerAmount - 1) {
                this.currentPlayer = allPlayers.get(1 + i);
            }else{
                this.currentPlayer = this.allPlayers.get(0);
            }

        }

    }

    public void ageAnimal() {
        for (Animal animal : currentPlayer.getPlayerAnimal()) {
            if (!(animal.getCurrentAge() == animal.getMaxAge())) {
                animal.setCurrentAge(1);
                System.out.println("\nEvery animal you have aged with 1 year!");
            } else {
                animal.death();
            }
        }
    }


    public void createPlayer(String newPlayer){
        Player player = new Player(newPlayer);
        this.allPlayers.add(player);
    }

    public Player getCurrentPlayer(){
        return currentPlayer;
    }

    public Store getStore(){
        return store;
    }
}


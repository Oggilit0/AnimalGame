package animalgame;

import animalgame.animals.abstractmodels.Animal;
import animalgame.food.abstractmodels.Food;
import animalgame.utilities.Menu;
import animalgame.utilities.ProgramUtils;
import java.util.ArrayList;
import java.util.*;

/**
 * This is the Game class where we store our game logic.
 * @author Sebastian Banfi, Oskar Herdenberg, Mathilda Nilsson, Hanna Petersson
 */
public class Game {
    private ArrayList<Player> allPlayers;
    private int maxRound;
    private int currentRound;
    private int playerAmount;
    private Player currentPlayer;
    private Menu gameMenu;
    private Store store;

    /**
     *
     */
    public Game() {
        this.store = new Store();
        this.allPlayers = new ArrayList<>();
        this.gameMenu = new Menu(this);
        this.currentRound = 0;
        this.gameMenu.newGameMenu();
    }

    /**
     * Starts the whole game where the player chooses how many players and rounds.
     * Starts the first round.
     */
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
    private void choosePlayers(){
        System.out.print("\nWrite in how many players (Min 2 Max 4): ");
        this.playerAmount = ProgramUtils.tryCatch(1,4);
        for (int i = 0; i < playerAmount; i++) {
            System.out.print("Write player " + (i + 1) + ": ");
            createPlayer(ProgramUtils.userInput());
        }
        this.currentPlayer = this.allPlayers.get(0);
    }

    /**
     * User choose how many rounds he or she wants to play
     * aslong as its between 5 and 30.
     */
    private void chooseRounds(){
        System.out.print("\nWrite in how many rounds (Min 5 Max 30): ");
        this.maxRound = ProgramUtils.tryCatch(5,30);
        System.out.println("\n".repeat(30));
    }

    /**
     * Load saved game from file and set variables to 
     */
    public void loadGame(String fileName){
        SavedGame loadedGameObj = (SavedGame) ProgramUtils.readFile(fileName);
        this.currentPlayer = loadedGameObj.getSavedCurrentPlayer();
        this.maxRound = loadedGameObj.getSavedMaxRounds();
        this.allPlayers = loadedGameObj.getSavedPlayerList();
        this.currentRound = loadedGameObj.getSavedCurrentRound();
        this.playerAmount = loadedGameObj.getSavedPlayerList().size();
        newRound();
    }

    /**
     *
     * @param fileName
     */
    public void saveGame(String fileName){
        SavedGame saveGame = new SavedGame(this.allPlayers,this.currentPlayer,this.currentRound,this.maxRound);
        ProgramUtils.writeToFile(saveGame,fileName);
    }

    /**
     *
     */
    private void newRound() {
        //this.allPlayers.get(2).setMoney(0); a test to check that the player gets kicked when broke
        for (int r = currentRound; r <= this.maxRound; r++) {
            this.currentRound = r;
            if (currentRound != maxRound) {
                System.out.println(ProgramUtils.RED + "\nRound " + (r + 1) + ProgramUtils.RESET);
                ageAnimal();
                removeDeadAnimals();
                newRoundGetPlayer();
            } else {
                endGame();
            }
        }
    }

    /**
     * gameOver prints a winner screen to the player who won the game by not getting removed,
     * this only happens if all player besides the winner gets eliminated before the last round.
     */
    private void gameOver() {
        for (Player winner : allPlayers) {
            System.out.println(ProgramUtils.GREEN + "The winner is " + winner.getName() + "!" + ProgramUtils.RESET + "\uD83D\uDC51");
            System.exit(1);
        }
    }

    /**
     * endGame takes every player and sells their animal, then checks that money to see which player wins.
     * Hashmap is used to set player and money to key/value and later use that value to check id that player has the most amount of money.
     * Map.entry is used to return a collection-view of the map (used it to check for the "maxValue") and then put the player with the most value to another arraylist,
     * that then checks if its 1 player who won or if more than it becomes a tie.
     */
    private void endGame() {
        Map<String,Integer> list = new HashMap<>();
        for(Player player : allPlayers){
            currentPlayer = player;
            getStore().setCustomer(currentPlayer);
            for(int i =0; i <= currentPlayer.getPlayerAnimal().size()+1; i++){
                if(currentPlayer.getPlayerAnimal().size() == 0){
                    break;
                }
                getStore().animalToSell(currentPlayer.getPlayerAnimal().get(0));
            }
            list.put(player.getName(), player.getMoney());
        }
        List<String> resultList = new ArrayList<>();
        int currentMaxValue = Integer.MIN_VALUE;
        for (Map.Entry<String, Integer> entry : list.entrySet()){
            if (entry.getValue() > currentMaxValue){
                resultList.clear();
                resultList.add(entry.getKey());
                currentMaxValue = entry.getValue();
            } else if (entry.getValue() == currentMaxValue){
                resultList.add(entry.getKey());
            }
        }
        if(resultList.size() == 1) {
            System.out.println(ProgramUtils.GREEN + "\nThe winner is " + resultList.get(0) + ProgramUtils.RESET + " \uD83D\uDC51");
        }else{
            System.out.println(ProgramUtils.YELLOW+"\nIts a tie Between "+ProgramUtils.RESET);
            for(String winner : resultList)
            System.out.print(winner+" \uD83D\uDC51 ");
        }
        System.out.println("\n\n"+ProgramUtils.RED + "Good Game!" + ProgramUtils.RESET);
    }

    /**
     *
     */
    private void newRoundGetPlayer() {
        for (int i = this.allPlayers.indexOf(this.currentPlayer); i < playerAmount; i++) {
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
                System.out.println(food.getName()+": "+food.getWeight()+" kg ");
            }
            for (Animal animal : currentPlayer.getPlayerAnimal()) {
                System.out.println(animal.getClass().getSimpleName()+": "+animal.getName()+", Health: "+ProgramUtils.RED+animal.getHealth()+ProgramUtils.RESET+", Age: "+ProgramUtils.PURPLE+animal.getCurrentAge()+ProgramUtils.RESET);
            }
            if(currentPlayer.getDeceasedAnimals() != null && currentPlayer.getDeceasedAnimals().size() != 0){
                System.out.println("\nDeceased animals since last turn: ");
                for(Animal animal : currentPlayer.getDeceasedAnimals()){
                    System.out.println(animal.getClass().getSimpleName()+": "+animal.getName());
                }
            }

            gameMenu.roundMenu();
            if (i != playerAmount - 1) {
                this.currentPlayer = allPlayers.get(1 + i);
            }else{
                this.currentPlayer = this.allPlayers.get(0);
            }
        }
    }

    /**
     *
     */
    private void removeDeadAnimals(){
        for(Player player : allPlayers){
            ArrayList<Animal> tempAnimals= new ArrayList<>();
            player.setDeceasedAnimalList(new ArrayList<>());
            for(Animal animal : player.getPlayerAnimal()){
                animal.healthOverTime();
                if(animal.getAliveStatus()){
                    tempAnimals.add(animal);
                }else{
                    player.setDeceasedAnimals(animal);
                }
            }
            player.setPlayerAnimal(tempAnimals);
        }
    }

    /** ages the animal
     * ageAnimal takes all the players and players animals and sets the value age higher
     * after every round, then prints it so the player knows that their animals aged.
     */
    private void ageAnimal() {
        for(Player player : allPlayers){
            for (Animal animal : player.getPlayerAnimal()) {
                if (!(animal.getCurrentAge() == animal.getMaxAge())) {
                    animal.setCurrentAge(1);
                } else {
                    animal.death();
                }
            }
        }

        if(currentPlayer.getPlayerAnimal().size() != 0){
            System.out.println("\nEvery animal you have aged with 1 year!");
        }
    }

    /** creates players
     * createPlayer makes a player and adds them into the player list,
     * if they got no name then they will have to write player name again.
     * @param newPlayer is the input name they chose for their player.
     */
    private void createPlayer(String newPlayer){
        if(newPlayer.equals("")){
            System.out.println("\nYou need a name!\n");
            startGame();
        }
        Player player = new Player(newPlayer);
        this.allPlayers.add(player);
    }

    /**
     *
     * @return
     */
    public Player getCurrentPlayer(){
        return currentPlayer;
    }

    /**
     *
     * @return
     */
    public Store getStore(){
        return store;
    }
}


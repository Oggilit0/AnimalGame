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
     * Constructor for the Game class
     * Initialize a store for access from menus.
     * Initialize allPlayers as a new ArrayList
     * initialize a menu accessed from most classes
     * initialize currentRound as int 0 because it rises.
     * Initialize {@link Menu#newGameMenu}
     */
    public Game() {
        this.store = new Store();
        this.allPlayers = new ArrayList<>();
        this.gameMenu = new Menu(this);
        this.currentRound = 0;
        this.gameMenu.newGameMenu();
    }

    /**
     * StartGame starts the program in the Game.
     * it starts {@link #choosePlayers}, {@link #chooseRounds} and {@link #newRound}.
     */
    public void startGame() {
        choosePlayers();
        chooseRounds();
        newRound();
    }

    /**
     * User choose how many players he or she wants to play with
     * as long as it's between 2 and 4.
     * Calls on {@link #createPlayer} if condition is true.
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
     * as long as it's between 5 and 30.
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
     * Saves the game using files and can later be accessed from {@link #loadGame}
     * @param fileName is the String name for the file to be saved as.
     */
    public void saveGame(String fileName){
        SavedGame saveGame = new SavedGame(this.allPlayers,this.currentPlayer,this.currentRound,this.maxRound);
        ProgramUtils.writeToFile(saveGame,fileName);
    }

    /** Gives round depending on how many rounds they selected before in choseRounds.
     * Loops trough rounds after {@link #playerTurn}, {@link #ageAnimal} and {@link #removeDeadAnimals}.
     * currentRound is set to the round that the player plays on, if the currentRound has reached the last
     * round it then goes to {@link #endGame} to check the winner.
     */
    private void newRound() {
        for (int r = currentRound; r <= this.maxRound; r++) {
            this.currentRound = r;
            if (currentRound != maxRound) {
                System.out.println(ProgramUtils.RED + "\nRound " + (r + 1) + ProgramUtils.RESET);
                ageAnimal();
                removeDeadAnimals();
                playerTurn();
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
     * Hashmap is used to set player and money to key/value and later use that value to check if that player has the most amount of money.
     * Map.entry is used to return a collection-view of the map (used it to check for the "maxValue") and then put the player with the most value to another arraylist,
     * that then checks if its 1 player who won or if more than one it becomes a tie.
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

    /** Loops trough every player inputted in chosePlayer.
     * playerTurn makes/sets every player and current player, so the amount of players has turns inside rounds.
     * Within every player turn they get information about their player like money, animals, animals health, animal age and players bought food.
     * playerTurn sets the first player in the turn to currentPlayer and then when their turn is over it sets the next player to currentPlayer.
     * If currentPlayer has no animals or money they get {@link #gameOver}, and also prints out all the dead animals they got after turns.
     */
    private void playerTurn() {
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
     * Calls on the method {@link Animal#healthOverTime}.
     * removeDeadAnimals checks if the animal is dead, if the animal is dead it gets
     * put in {@link Player#setDeceasedAnimalList} else if the animal is still alive
     * it gets put in a tempAnimal ArrayList.
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
     * If the animal aged to maxAge we call on {@link Animal#death}
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
     * @param newPlayer is the inputted name they chose for their player.
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
     * Gets the current player playing the game.
     * @return currentPlayer as currentPlayer
     */
    public Player getCurrentPlayer(){
        return currentPlayer;
    }

    /**
     * Gets the Store class made from Game
     * @return store as class store
     */
    public Store getStore(){
        return store;
    }
}


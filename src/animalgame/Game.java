package animalgame;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Objects;

public class Game {
    private ArrayList<Player> allPlayers;
    private int maxRound;
    private int round;
    private int playerAmount;
    private Player currentPlayer;
    private Menu gameMenu;
    private Store store;

    public Game(){
        this.store = new Store();
        this.allPlayers = new ArrayList<>();
        this.gameMenu = new Menu(this);
        Animal testKo = new Cow("TestKo",1000,10, Animal.Gender.FEMALE);
        Animal testKatt = new Cat("TestKatt",500,5, Animal.Gender.MALE);

        gameStartPlayer();
        //this is A COMMENT

    }
    public void gameStartPlayer() {
        System.out.print("Write in how many players (Min 2 Max 4): ");
        this.playerAmount = ProgramUtils.tryCatch(ProgramUtils.userInput());
        if (playerAmount >= 2 && playerAmount <= 4) {
            for (int i = 0; i < playerAmount; i++) {
                System.out.print("Write player " + (i + 1) + ": ");
                createPlayer(ProgramUtils.userInput());

            }
        } else {
            System.out.println("Min 2 Max 4");
            gameStartPlayer();
        }
        gameStartRounds();
    }
    public void gameStartRounds(){
        System.out.print("\nWrite in how many rounds (Min 5 Max 30): ");
        this.maxRound = ProgramUtils.tryCatch(ProgramUtils.userInput());
        if (!(maxRound >= 5 && maxRound <= 30)) {
            System.out.println("Min 5 rounds and Max 30 rounds");
            gameStartRounds();
        }
        System.out.println("\n".repeat(30));
        newRound();
    }

    public void newRound(){
        //this.allPlayers.get(2).setMoney(0); a test to check that the player gets kicked when broke
        for(int r = 0; r <= this.maxRound; r++) {
            this.round = r;
            if(round != maxRound){
                System.out.println(ProgramUtils.RED+"Round " + (r + 1)+ProgramUtils.RESET);
                newRoundGetPlayer();
                ageAnimal();
                System.out.println("\n".repeat(10));
            }else{
                endGame();
            }
        }
    }
    public void gameOver(){
        for (Player winner : allPlayers){
            System.out.println(ProgramUtils.GREEN+"The winner is "+winner.getName()+"!"+ProgramUtils.RESET+"\uD83D\uDC51");
            System.exit(1);
        }
    }

    public void endGame(){
        System.out.println(ProgramUtils.RED+"Good Game!"+ProgramUtils.RESET);
    }

    public void newRoundGetPlayer(){
        this.currentPlayer = allPlayers.get(0);
        for(int i = 0; i < playerAmount; i++){
            if(this.currentPlayer.getMoney() == 0 && (this.currentPlayer.getPlayerAnimal().size() == 0)){
                this.allPlayers.remove(currentPlayer);
                System.out.println("\n".repeat(10));
                try {
                    System.out.println(ProgramUtils.RED+"GAME OVER "+currentPlayer.getName()+"!"+ProgramUtils.RESET);
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(this.allPlayers.size() != 1){
                    break;
                }else{
                    gameOver();
                }
            }
            System.out.println("\n"+ProgramUtils.GREEN+currentPlayer.getName()+ "'s Turn"+ProgramUtils.RESET+"\n");
            gameMenu.roundMenu();

            if(i != playerAmount -1){
                this.currentPlayer = allPlayers.get(1+i);
            }
        }
    }
    public void ageAnimal(){
        for(Animal animal : currentPlayer.getPlayerAnimal()){
            if(!(animal.getCurrentAge() == animal.getMaxAge())){
                animal.setCurrentAge(1);
                System.out.println("Every animal you have aged with 1 year!");
            }else{
                animal.death();
            }
        }
    }


    /**
     *
     * @param animalType
     * @param gender
     */
    public void createAnimal(Animal animalType, Animal.Gender gender) {
            switch(animalType.getClass().getName()){
                case "animalgame.Cat":
                    if(animalType.getGender() == Animal.Gender.FEMALE){
                        Cat cat = new Cat(ProgramUtils.userInput(), 500, 9, Animal.Gender.FEMALE);
                        this.currentPlayer.setPlayerAnimal(cat);
                    }else{
                        Cat cat = new Cat(ProgramUtils.userInput(),500,9, Animal.Gender.MALE);
                        this.currentPlayer.setPlayerAnimal(cat);
                    }
                    break;
                case "animalgame.Cow":
                    if(gender == Animal.Gender.FEMALE){
                        Cow cow = new Cow(ProgramUtils.userInput(), 500, 10, Animal.Gender.FEMALE);
                        this.currentPlayer.setPlayerAnimal(cow);
                    }else{
                        Cow cow = new Cow(ProgramUtils.userInput(), 500, 10, Animal.Gender.MALE);
                        this.currentPlayer.setPlayerAnimal(cow);
                        }
                        break;
                case "animalgame.Dog":
                    if(gender == Animal.Gender.FEMALE){
                        Dog dog = new Dog(ProgramUtils.userInput(),500, 15, Animal.Gender.FEMALE);
                        this.currentPlayer.setPlayerAnimal(dog);
                    }else{
                        Dog dog = new Dog(ProgramUtils.userInput(), 500,15, Animal.Gender.MALE);
                        this.currentPlayer.setPlayerAnimal(dog);
                    }
                case "animalgame.Horse":
                    if(gender == Animal.Gender.FEMALE){

                    }else{

                    }
                case "animalgame.Snake":
                    if(gender == Animal.Gender.FEMALE){

                    }else{

                    }
                default:
                    // if (animalType.getClass().equals(Cat.class))
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


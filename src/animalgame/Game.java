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
    private int age;

    public Game(){
        this.store = new Store();
        this.allPlayers = new ArrayList<>();
        this.gameMenu = new Menu(this);
        Animal testKo = new Cow("TestKo",1000,10, Animal.Gender.FEMALE);
        Animal testKatt = new Cat("TestKatt",500,5, Animal.Gender.MALE);

        gameStart();

    }
    public void gameStart(){
        System.out.print("Write in how many players (Min 2 Max 4): ");
        this.playerAmount = ProgramUtils.tryCatch(ProgramUtils.userInput());
        if (playerAmount >= 2 && playerAmount <= 4) {
            for(int i = 0; i < playerAmount; i++){
                System.out.print("Write player " + (i+1) +": ");
                createPlayer(ProgramUtils.userInput());
            }

        }else {
            System.out.println("Min 2 Max 4");
            System.exit(0);
        }

        System.out.print("\nWrite in how many rounds (Min 5 Max 30): ");
        this.maxRound = ProgramUtils.tryCatch(ProgramUtils.userInput());
        if (!(maxRound >= 5 && maxRound <= 30)) {
            System.out.println("Min 5 rounds and Max 30 rounds");
        }
        System.out.println("\n".repeat(30));
        newRound();
    }

    public void newRound(){
        for(int r = 0; r <= this.maxRound; r++) {
            this.round = r;
            if(round != maxRound){
                System.out.println(ProgramUtils.RED+"Round " + (r + 1)+ProgramUtils.RESET);
                newRoundGetPlayer();
                ageAnimal();
                System.out.println("\n".repeat(30));
            }else{
                endGame();

            }

        }
    }

    public void endGame(){
        System.out.println(ProgramUtils.RED+"Good Game!"+ProgramUtils.RESET);
    }

    public void newRoundGetPlayer(){
        this.currentPlayer = allPlayers.get(0);
        for(int i = 0; i < playerAmount; i++){
            this.allPlayers.get(1).setMoney(0);
            if(this.currentPlayer.getMoney() == 0 && (this.currentPlayer.getPlayerAnimal().size() == 0)){
                this.allPlayers.remove(currentPlayer);
                System.out.println(ProgramUtils.RED+"Game OVER "+currentPlayer.getName()+"!"+ProgramUtils.RESET);
                continue;
            }

            System.out.println(ProgramUtils.GREEN+currentPlayer.getName()+ "'s Turn:"+ProgramUtils.RESET+"\n");
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


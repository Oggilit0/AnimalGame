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
        //this is a test 2


        //Oskar testing
        Player adam = new Player("adam");
        adam.setPlayerAnimal(testKo);
        adam.setPlayerAnimal(testKatt);
        adam.setPlayerAnimal(testKo);
        adam.setPlayerAnimal(testKatt);
        currentPlayer = adam;
        System.out.println(currentPlayer);
        Menu newMenu = new Menu(this);
        newMenu.roundMenu();

        //End oskar testing

        // Debug
       // this.allPlayers.get(0).tryMating(testKo,testKatt);
      //this.allPlayers.get(0).setPlayerAnimal(testKo);
       // createAnimal(testKatt);
      //  createAnimal(testKo);

        // Debug
        gameStart();

    }
    public void gameStart(){
        System.out.print("Write in how many players (Min 2 Max 4): ");
        this.playerAmount = Integer.parseInt(ProgramUtils.userInput());
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
        this.maxRound = Integer.parseInt(ProgramUtils.userInput());
        if (!(maxRound >= 5 && maxRound <= 30)) {
            System.out.println("Min 5 rounds and Max 30 rounds");
        }
        newRound();
    }

    public void newRound(){
        for(int r = 0; r <= this.maxRound; r++) {
            this.round = r;
            if(round != maxRound){
                System.out.println("Round " + (r + 1));
                newRoundGetPlayer();
            }else{
                endGame();

            }

        }
    }
    public void endGame(){
        if(this.currentPlayer.getMoney() == 0 || (this.round > maxRound)){
            System.out.println(ProgramUtils.RED+"GAME OVER");
        }
    }
    public void newRoundGetPlayer(){
        this.currentPlayer = allPlayers.get(0);
        for(int i = 0; i < playerAmount; i++){
            System.out.println(currentPlayer.getName());
            if(i != playerAmount -1){
                this.currentPlayer = allPlayers.get(1+i);
            }
        }
    }
    public void ageAnimal(){
        //This is a comment

    }
    public void createAnimal(Animal animalType) {
       // if (animalType.getClass().equals(Cat.class)) {
            switch(animalType.getClass().getName()){
                case "animalgame.Cat":
                    //Cat cat = new Cat(ProgramUtils.userInput(),);
                case "animalgame.Cow":
                    //Cow cow = new Cow();
                case "animalgame.Dog":
                    //Dog dog = new Dog();
                case "animalgame.Horse":
                    //Horse horse = new Horse();
                case "animalgame.Snake":
                    //Snake snake = new snake();
                default:
                    //alternativ att få specifikt kön beroende på val från Store
                    //Animal.Gender gender
                    //kön
                    //djurtyp
                    //slumpa antal i player tryMating
                    //slumpa kön

            }
        }


    public void createPlayer(String newPlayer){
        Player player = new Player(newPlayer);
        this.allPlayers.add(player);
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Store getStore(){return store;}
}


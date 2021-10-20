package animalgame;

import java.io.Serializable;
import java.util.ArrayList;

public class SavedGame implements Serializable {
    private ArrayList<Player> savedPlayerList;
    private Player savedCurrentPlayer;
    private int savedCurrentRound;
    private int savedMaxRounds;


    public SavedGame(ArrayList<Player> playerList, Player currentPlayer, int currentRound, int maxRounds){
        this.savedPlayerList = playerList;
        this.savedCurrentPlayer = currentPlayer;
        this.savedCurrentRound = currentRound;
        this.savedMaxRounds = maxRounds;
    }

}
package animalgame;

import java.io.Serializable;
import java.util.ArrayList;

public class SavedGame implements Serializable {
    private final ArrayList<Player> savedPlayerList;
    private final Player savedCurrentPlayer;
    private final int savedCurrentRound;
    private final int savedMaxRounds;

    public SavedGame(ArrayList<Player> playerList, Player currentPlayer, int currentRound, int maxRounds){
        this.savedPlayerList = playerList;
        this.savedCurrentPlayer = currentPlayer;
        this.savedCurrentRound = currentRound;
        this.savedMaxRounds = maxRounds;
    }

    public Player getSavedCurrentPlayer() {
        return savedCurrentPlayer;
    }

    public int getSavedCurrentRound() {
        return savedCurrentRound;
    }

    public int getSavedMaxRounds() {
        return savedMaxRounds;
    }

    public ArrayList<Player> getSavedPlayerList() {
        return savedPlayerList;
    }
}

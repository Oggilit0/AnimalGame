package animalgame;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 */
public class SavedGame implements Serializable {
    private final ArrayList<Player> savedPlayerList;
    private final Player savedCurrentPlayer;
    private final int savedCurrentRound;
    private final int savedMaxRounds;

    /**
     *
     * @param playerList
     * @param currentPlayer
     * @param currentRound
     * @param maxRounds
     */
    public SavedGame(ArrayList<Player> playerList, Player currentPlayer, int currentRound, int maxRounds){
        this.savedPlayerList = playerList;
        this.savedCurrentPlayer = currentPlayer;
        this.savedCurrentRound = currentRound;
        this.savedMaxRounds = maxRounds;
    }

    /**
     *
     * @return
     */
    public Player getSavedCurrentPlayer() {
        return savedCurrentPlayer;
    }

    /**
     *
     * @return
     */
    public int getSavedCurrentRound() {
        return savedCurrentRound;
    }

    /**
     *
     * @return
     */
    public int getSavedMaxRounds() {
        return savedMaxRounds;
    }

    /**
     *
     * @return
     */
    public ArrayList<Player> getSavedPlayerList() {
        return savedPlayerList;
    }
}

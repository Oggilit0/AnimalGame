package animalgame;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This Class is where all the variables, that is needed to continue a game, is stored
 * @author Sebastian Banfi, Oskar Herdenberg, Mathilda Nilsson, Hanna Petersson
 */
public class SavedGame implements Serializable {
    private final ArrayList<Player> savedPlayerList;
    private final Player savedCurrentPlayer;
    private final int savedCurrentRound;
    private final int savedMaxRounds;

    /**
     * Constructor of the SavedGame class.
     * Initialize variables to be stored
     * @param playerList ArrayList of all active players
     * @param currentPlayer The current player who is playing
     * @param currentRound The current round which is played
     * @param maxRounds The maximal amount of rounds decided in the beginning
     */
    public SavedGame(ArrayList<Player> playerList, Player currentPlayer, int currentRound, int maxRounds){
        this.savedPlayerList = playerList;
        this.savedCurrentPlayer = currentPlayer;
        this.savedCurrentRound = currentRound;
        this.savedMaxRounds = maxRounds;
    }

    /**
     * returns the stored Player
     * @return savedCurrentPlayer as a Player
     */
    public Player getSavedCurrentPlayer() {
        return savedCurrentPlayer;
    }

    /**
     * returns the stored currentRound
     * @return savedCurrentRound as an int
     */
    public int getSavedCurrentRound() {
        return savedCurrentRound;
    }

    /**
     * returns the stored maxRounds
     * @return savedMaxRounds as an int
     */
    public int getSavedMaxRounds() {
        return savedMaxRounds;
    }

    /**
     * returns The stores list of Players
     * @return SavePlayerList as an Arraylist
     */
    public ArrayList<Player> getSavedPlayerList() {
        return savedPlayerList;
    }
}
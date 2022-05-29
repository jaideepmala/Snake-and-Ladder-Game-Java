import java.util.*;

public class Game {
    String gameId;
    Integer boardSize;
    Map<Integer, Integer> snakes;
    Map<Integer, Integer> ladders;
    List<Integer> playerIds;
    Map<Integer, Player> playerIdMap;
    Boolean isEnded;
    Integer[] numPlayersAtPos;

    public Game(String gameId, int boardSize, Map<Integer, Integer> snakes, Map<Integer, Integer> ladders, List<Integer> playerIds, Map<Integer, Player> playerIdMap, Boolean isEnded,Integer[] numPlayersAtPos) {
        this.gameId = gameId;
        this.boardSize = boardSize;
        this.snakes = snakes;
        this.ladders = ladders;
        this.playerIds = playerIds;
        this.playerIdMap = playerIdMap;
        this.isEnded = isEnded;
        this.numPlayersAtPos = numPlayersAtPos;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public Integer getBoardSize() {
        return boardSize;
    }

    public void setBoardSize(Integer boardSize) {
        this.boardSize = boardSize;
    }

    public Map<Integer, Integer> getSnakes() {
        return snakes;
    }

    public void setSnakes(Map<Integer, Integer> snakes) {
        this.snakes = snakes;
    }

    public Map<Integer, Integer> getLadders() {
        return ladders;
    }

    public void setLadders(Map<Integer, Integer> ladders) {
        this.ladders = ladders;
    }

    public List<Integer> getPlayerIds() {
        return playerIds;
    }

    public void setPlayerIds(List<Integer> playerIds) {
        this.playerIds = playerIds;
    }

    public Map<Integer, Player> getPlayerIdMap() {
        return playerIdMap;
    }

    public void setPlayerIdMap(Map<Integer, Player> playerIdMap) {
        this.playerIdMap = playerIdMap;
    }

    public Boolean getEnded() {
        return isEnded;
    }

    public void setEnded(Boolean ended) {
        isEnded = ended;
    }

    public Integer[] getNumPlayersAtPos() {
        return numPlayersAtPos;
    }

    public void setNumPlayersAtPos(Integer[] numPlayersAtPos) {
        this.numPlayersAtPos = numPlayersAtPos;
    }
}
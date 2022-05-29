import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class GameApplicationImpl implements GameApplication {
    private static Map<String, Game> createdGames = new ConcurrentHashMap<>();
    private static Map<String, Player> holdingDice = new ConcurrentHashMap<>();

    public String createGame(int boardSize, Map<Integer, Integer> snakes, Map<Integer, Integer> ladders, List<Integer> playerIds) {
        String gameId = UUID.randomUUID().toString();
        Map<Integer, Player> playerMap = new HashMap<>();
        for (Integer i : playerIds) {
            Player player = new Player(i, gameId, 1);
            playerMap.put(i, player);
        }
        Integer [] arr = new Integer[boardSize*boardSize+1];
        Arrays.fill(arr,1);
        Game game = new Game(gameId, boardSize, snakes, ladders, playerIds, playerMap, false,arr);
        while (createdGames.containsKey(gameId)) {
            gameId = UUID.randomUUID().toString();
        }
        synchronized (this) {
            createdGames.put(gameId, game);
        }
        return gameId;
    }

    public Boolean holdDice(String gameId, int playerId) {
        Game game = createdGames.get(gameId);
        if (game == null || game.isEnded) {
            return false;
        }
        Boolean isPlayerExists = false;
        for (Integer i : game.getPlayerIds()) {
            if (i == playerId) {
                isPlayerExists = true;
            }
        }
        if (!isPlayerExists) {
            return false;
        }
        if (holdingDice.containsKey(gameId)) {
            return false;
        } else {
            synchronized (this) {
                Player p = game.getPlayerIdMap().get(playerId);
                holdingDice.put(gameId, p);
            }
        }
        return true;

    }

    public Boolean rollDiceAndMove(String gameId, int playerId) {
        Game game = createdGames.get(gameId);
        if (game == null || game.isEnded) {
            return false;
        }
        if (!holdingDice.containsKey(gameId)) {
            return false;
        }
        if (holdingDice.get(gameId).getPlayerId() != playerId) {
            return false;
        }
        int diceMove = generateDiceMove();
        Player player = holdingDice.get(gameId);
        int oldPos = player.getPosition();
        int endGame = game.getBoardSize() * game.getBoardSize();
        int newPos = getNewPostion(game,oldPos+diceMove,oldPos,endGame);
        Map<Integer, Player> players = game.getPlayerIdMap();
        for (Map.Entry<Integer, Player> e : players.entrySet()) {
            Player p = e.getValue();
            if (p.getPosition() == newPos && game.getNumPlayersAtPos()[newPos] == 1) {
                return false;
            }
        }
        synchronized (this) {
            if (newPos == endGame) createdGames.remove(gameId);
            holdingDice.remove(gameId);
            player.setPosition(newPos);
        }
        return true;
    }

    int generateDiceMove(){
        Random rand = new Random();
        return rand.nextInt(6);
    }
    int getNewPostion(Game game, int newPos,int oldPos,int endGame){
        if (game.getSnakes().containsKey(newPos)) {
            newPos = game.getSnakes().get(newPos);
        } else if (game.getLadders().containsKey(newPos)) {
            newPos = game.getLadders().get(newPos);
        }
        if (newPos > endGame) {
            newPos = oldPos;
        }
        return newPos;
    }

    private int count;

    @Override
    public void run() {
        for(int i=1; i < 5; i++){
            processSomething(i);
            count++;
        }
    }

    public int getCount() {
        return this.count;
    }

    private void processSomething(int i) {
        // processing some job
        try {
            Thread.sleep(i*100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

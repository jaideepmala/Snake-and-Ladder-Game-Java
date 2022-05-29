import java.util.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        GameApplication gameApplication = new GameApplicationImpl();
//        Thread t1 = new Thread(gameApplication,"t1");
//        t1.start();
//        Thread t2 = new Thread(gameApplication, "t2");
//        t2.start();
//        //wait for threads to finish processing
//        t1.join();
//        t2.join();
        //System.out.println("Processing count="+gameApplication);


        int boardSize = 3;
        Map<Integer, Integer> snakes= new HashMap<>();
        Map<Integer, Integer> ladders = new HashMap<>();
        List<Integer> playerIds = new ArrayList<>();
        playerIds.add(1);
        playerIds.add(2);
        String gameId = gameApplication.createGame( boardSize, snakes,  ladders,  playerIds);
        if(gameApplication.holdDice(gameId,1)){
            while(gameApplication.rollDiceAndMove(gameId,1)) {
                gameApplication.rollDiceAndMove(gameId, 1);
            }
        }

    }
}

package tools;

import entities.powerups.PowerUp;
import entities.roadobjects.Barrier;
import entities.roadobjects.Obstacle;
import java.util.ArrayList;
import java.util.Random;

public class EntityListManager {

    // boundaries

    private static final double leftBarrierBound = 4;
    private static final double rightBarrierBound = 96;
    private static final double rightLaneBound = 51.5;
    private static final double leftLaneBound = 48.5;
    private static final Random r = new Random();


    public static void spawnBarriers(ArrayList<Barrier> barriers){
        Barrier lastBarrier = barriers.get(barriers.size() - 1);
        if (lastBarrier.getTopEdge() < 100){
            barriers.add(new Barrier(lastBarrier.getTopEdge() + lastBarrier.getHalfHeight()));
        }

    }

    public static void spawnPowerUps(ArrayList<PowerUp> powerUps){
        double xCoord = r.nextDouble() * 100;
        if (xCoord - 2 < leftBarrierBound){xCoord = leftBarrierBound + 2;} // minimum distance from left side is barrier width + powerup width
        if (xCoord + 2 > rightBarrierBound){xCoord = rightBarrierBound - 2;} // minimum distance from right side is 100 - barrier width - powerup width


        PowerUp powerUp = PowerUp.spawnPowerUp(xCoord);
        if (powerUp != null){
            powerUps.add(powerUp);
        }
    }

    public static void spawnObstacle(ArrayList<Obstacle> obstacles){
        double shouldSpawn = r.nextInt(3 * 60);
        if (shouldSpawn == 0){
            double xCoord = r.nextDouble() * 100;
            if (xCoord < leftBarrierBound + 5){xCoord = leftBarrierBound + 5;}
            if (xCoord > rightBarrierBound - 5){xCoord = rightBarrierBound - 5;}
            if (xCoord + 5 > leftLaneBound && xCoord + 5 < rightLaneBound){xCoord = leftLaneBound - 5;}
            if (xCoord - 5 < rightLaneBound && xCoord - 5 > leftLaneBound){xCoord = rightLaneBound + 5;}


            obstacles.add(new Obstacle(xCoord));
        }
    }

    public static void removeInvalidBarrier(ArrayList<Barrier> barriers){
        for (int i = 0; i < barriers.size(); i++){
            if (barriers.get(i).getTopEdge() < 0){
                barriers.remove(barriers.get(i));
            }
        }
    }

    public static void removeInvalidPowerUps(ArrayList<PowerUp> powerUps){
        for (int i = 0; i < powerUps.size(); i++){
            PowerUp current = powerUps.get(i);
            if (current.getY() + current.getHalfHeight() < 0){powerUps.remove(current);}
        }
    }

    public static void removeInvalidObstacles(ArrayList<Obstacle> obstacles){
        for (int i = 0; i < obstacles.size(); i++){
            Obstacle current = obstacles.get(i);
            if (current.getTopEdge() < 0){
                obstacles.remove(current);
            }
        }
    }





}

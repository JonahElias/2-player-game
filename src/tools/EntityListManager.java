package tools;

import entities.powerups.PowerUp;
import entities.roadobjects.Barrier;
import entities.roadobjects.Obstacle;
import java.util.ArrayList;
import java.util.Random;

public class EntityListManager {


    public static void spawnBarriers(ArrayList<Barrier> barriers){
        Barrier lastBarrier = barriers.get(barriers.size() - 1);
        if (lastBarrier.getTopEdge() < 100){
            barriers.add(new Barrier(lastBarrier.getTopEdge() + lastBarrier.getHalfHeight()));
        }

    }

    public static void spawnPowerUps(ArrayList<PowerUp> powerUps){
        PowerUp powerUp = PowerUp.spawnPowerUp();
        if (powerUp != null){
            powerUps.add(powerUp);
        }
    }

    public static void spawnObstacle(ArrayList<Obstacle> obstacles){
        Random r = new Random();
        double shouldSpawn = r.nextInt(6 * 60);
        if (shouldSpawn == 0){
            double xCoord = r.nextDouble() * 100;
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

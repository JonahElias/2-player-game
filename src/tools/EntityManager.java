package tools;

import entities.Player;
import entities.powerups.PowerUp;
import entities.roadobjects.Barrier;
import entities.roadobjects.Obstacle;
import entities.weapons.Gun;

import java.awt.*;
import java.util.ArrayList;


public class EntityManager {


    private static Player playerOne;
    private static Player playerTwo;
    private static ArrayList<PowerUp> powerUps = new ArrayList<>();
    private static ArrayList<Barrier> barriers = new ArrayList<>();
    private static ArrayList<Obstacle> obstacles = new ArrayList<>();


    public static void init(Gun gunOne, Gun gunTwo) {
        for (int i = 10; i < 100; i += 20) {
            barriers.add(new Barrier(i));
        }



        playerOne = new Player(true, gunOne);
        playerTwo = new Player(false, gunTwo);
        playerOne.setMiddleBoundary(49);
        playerOne.setSideBoundary(barriers.get(0).getHalfWidth() * 2);
        playerTwo.setMiddleBoundary(51);
        playerTwo.setSideBoundary(100 - barriers.get(0).getHalfWidth() * 2);
    }


    public static void drawEntities() {
        drawLanes();
        for (PowerUp p : powerUps) {
            p.draw();
        }
        for (Barrier b : barriers) {
            b.draw();
        }
        for (Obstacle o : obstacles) {
            o.draw();
        }
        playerOne.draw();
        playerTwo.draw();


    }

    public static void updateEntities() {
        playerOne.update();
        playerTwo.update();
        updatePowerUps();
        updateBarriers();
        updateObstacles();
        /* System.out.println("powerup size: " + powerUps.size());
        System.out.println("barrier size: " + barriers.size());
        System.out.println("obstacle size: " + obstacles.size());*/
    }


    public static void spawnEntities() {
        EntityListManager.spawnBarriers(barriers);
        EntityListManager.spawnPowerUps(powerUps);
        EntityListManager.spawnObstacle(obstacles);
    }

    public static void removeInvalidEntities() {
        EntityListManager.removeInvalidBarrier(barriers);
        EntityListManager.removeInvalidPowerUps(powerUps);
        EntityListManager.removeInvalidObstacles(obstacles);
    }


    private static void drawLanes() {
        double halfDistance = 1;
        double width = 0.5;
        double height = 50;
        StdDraw.setPenColor(new Color(255, 244, 75));


        StdDraw.filledRectangle(50 - halfDistance, 50, width, height);
        StdDraw.filledRectangle(50 + halfDistance, 50, width, height);
    }

    private static void updateBarriers() {
        for (Barrier b : barriers) {
            b.update();
        }
    }


    private static void updatePowerUps() {
        for (int i = 0; i < powerUps.size(); i++) {
            PowerUp powerUp = powerUps.get(i);
            powerUp.update();

            double topEdge = powerUp.getY() + powerUp.getHalfHeight();
            double bottomEdge = powerUp.getY() - powerUp.getHalfHeight();
            double leftEdge = powerUp.getX() - powerUp.getHalfWidth();
            double rightEdge = powerUp.getX() + powerUp.getHalfWidth();


            if (playerCollision(playerOne, topEdge, bottomEdge, leftEdge, rightEdge)) {
                powerUp.useEffect(true);
                powerUps.remove(powerUp);
            } else if (playerCollision(playerTwo, topEdge, bottomEdge, leftEdge, rightEdge)) {
                powerUp.useEffect(false);
                powerUps.remove(powerUp);
            }
        }
    }


    private static void updateObstacles() {
        for (Obstacle o : obstacles) {
            o.update();
            if (playerCollision(playerOne, o.getTopEdge(), o.getBottomEdge(), o.getLeftEdge(), o.getRightEdge()) && !o.isHit()) {
                o.setHit(true);
                o.setRotation(getObstacleAngle(playerOne, o));
                updatePlayerHealth(true, -12.5);
                playerOne.multiplyFspeed(0.1);
                playerOne.addToBspeed(0.35);
            }

            if (playerCollision(playerTwo, o.getTopEdge(), o.getBottomEdge(), o.getLeftEdge(), o.getRightEdge()) && !o.isHit()) {
                o.setHit(true);
                o.setRotation(getObstacleAngle(playerTwo, o));
                updatePlayerHealth(false, -12.5);
                playerTwo.multiplyFspeed(0.1);
                playerTwo.addToBspeed(0.35);
            }


        }
    }


    public static void bulletCollision(boolean isPlayerOne) {
        double damage = getDamageFactor(isPlayerOne);


        if (isPlayerOne) {
            playerTwo.setHealth(playerTwo.getHealth() - damage);
        } else {
            playerOne.setHealth(playerOne.getHealth() - damage);
        }
    }

    private static double getDamageFactor(boolean isPlayerOne) {

        double playerOneY = playerOne.getY();
        double playerOneHalfHeight = playerOne.getHalfHeight();
        double playerOneHeadshot = playerOne.getY() + 3;
        double playerOneBodyshot = playerOne.getY() - 4;

        double playerTwoY = playerTwo.getY();
        double playerTwoHalfHeight = playerTwo.getHalfHeight();
        double playerTwoHeadshot = playerTwo.getY() + 3;
        double playerTwoBodyshot = playerTwo.getY() - 4;


        if (isPlayerOne) {
            if (playerOneY > playerTwoY - playerTwoHalfHeight && playerOneY < playerTwoY + playerTwoHalfHeight) {

                if (playerOneY > playerTwoY && playerOneY < playerTwoHeadshot) {
                    return playerOne.getGunDamage() * 2;
                }
                if (playerOneY < playerTwoY && playerOneY > playerTwoBodyshot) {
                    return playerOne.getGunDamage();
                } else {
                    return playerOne.getGunDamage() / 2;
                }

            }

        } else {
            if (playerTwoY > playerOneY - playerOneHalfHeight && playerTwoY < playerOneY + playerOneHalfHeight) {
                if (playerTwoY > playerOneY && playerTwoY < playerOneHeadshot) {
                    return playerTwo.getGunDamage() * 2;
                }
                if (playerTwoY < playerOneY && playerTwoY > playerOneBodyshot) {
                    return playerTwo.getGunDamage();
                } else {
                    return playerTwo.getGunDamage() / 2;
                }
            }
        }

        return 0;
    }


    private static boolean playerCollision(Player player, double obj_top, double obj_bottom, double obj_left, double obj_right) {
        double p_top = player.getY() + player.getHalfHeight();
        double p_bottom = player.getY() - player.getHalfHeight();
        double p_left = player.getX() - player.getHalfWidth();
        double p_right = player.getX() + player.getHalfWidth();

        return p_top >= obj_bottom && p_left <= obj_right && p_right >= obj_left && p_bottom <= obj_top;
    }

    private static double getObstacleAngle(Player player, Obstacle obstacle){
        if (player.getX() + player.getHalfWidth() < obstacle.getX()){return 340;}
        return 20;
    }


    public static void updatePlayerHealth(boolean isPlayerOne, double changeFactor) {
        if (isPlayerOne) {
            playerOne.setHealth(playerOne.getHealth() + changeFactor);
            if (playerOne.getHealth() > 100) {
                playerOne.setHealth(100);
            }
        } else {
            playerTwo.setHealth(playerTwo.getHealth() + changeFactor);
            if (playerTwo.getHealth() > 100) {
                playerTwo.setHealth(100);
            }
        }
    }

    public static void updatePlayerAmmo(boolean isPlayerOne){
        if (isPlayerOne){
            playerOne.getGun().addAmmo(playerOne.getGunClipSize());
        }else{
            playerTwo.getGun().addAmmo(playerTwo.getGunClipSize());
        }
    }


}

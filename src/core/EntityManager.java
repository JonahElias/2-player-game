package core;

import entities.Player;

import java.awt.*;

public class EntityManager {

    private static final Player playerOne = new Player(true);
    private static final Player playerTwo = new Player(false);


    public static void drawEntities() {
        playerOne.draw();
        playerTwo.draw();
    }

    public static void updateEntities() {
        playerOne.update();
        playerTwo.update();
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

    public static double getPlayerOneHealth(){return playerOne.getHealth();}
    public static double getPlayerTwoHealth(){return playerTwo.getHealth();}


}

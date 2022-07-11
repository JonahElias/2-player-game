package entities.powerups;

import tools.StdDraw;
import java.awt.*;
import java.util.Random;

public abstract class PowerUp {

    protected double x;
    protected double y = 125;
    protected double halfWidth = 2;
    protected double halfHeight = 2;
    protected double speed = 1.25;
    protected double rarity;
    protected String image_path;


    public abstract void useEffect(boolean isPlayerOne);

    public void draw() {
        StdDraw.picture(x, y, image_path, halfWidth * 2, halfHeight * 2);
        StdDraw.setPenRadius(0.002);
        StdDraw.setPenColor(Color.black);
        StdDraw.rectangle(x, y, halfWidth, halfHeight);
    }

    public static PowerUp spawnPowerUp() {
        Random r = new Random();

        double xCoord = r.nextDouble() * 100;
        if (xCoord < 5){xCoord = 5;} // minimum distance from left side is barrier width + powerup width
        if (xCoord > 95){xCoord = 95;} // minimum distance from right side is 100 - barrier width - powerup width



        PowerUp[] powerUps = new PowerUp[1];
        powerUps[0] = new TiltDrink(xCoord);


        PowerUp powerUp = powerUps[r.nextInt(powerUps.length)];
        if (r.nextInt((int) (powerUp.rarity * 60)) == 0) {
            return powerUp;

        }
        return null;
    }

    public void update(){y -= speed;}


    public double getX() {return x;}
    public double getY() {return y;}
    public double getHalfWidth() {return halfWidth;}
    public double getHalfHeight() {return halfHeight;}

}

package entities;

import entities.weapons.MachinePistol;
import tools.StdDraw;
import entities.weapons.Gun;
import entities.weapons.Rifle;

public class Player {

    // basic player attributes

    private double x;
    private double y;
    private final double halfWidth;
    private final double halfHeight;
    private double health;
    private final boolean isPlayerOne;
    private final Gun gun;

    // driving mechanics variables

    private final double speedFactor; // a standard speed factor to be modified
    private final double vertAccel; // speed factor for vertical acceleration
    private final double vertDeccel; // speed factor for vertical deceleration
    private double fspeed; // forward velocity of car
    private double bspeed; // backward velocity of car
    private double rspeed; // velocity of car to the right
    private double lspeed; // velocity of car to the left
    private double middleBoundary;
    private double sideBoundary;

    // numbers for key inputs

    private final int UP;
    private final int DOWN;
    private final int LEFT;
    private final int RIGHT;
    private final int SHOOT;

    public Player(boolean playerOne, Gun gun) {
        halfWidth = 3.5;
        halfHeight = halfWidth * 1.875;
        health = 100;
        isPlayerOne = playerOne;
        this.gun = gun;

        speedFactor = 0.0125;
        vertAccel = speedFactor * 1.25;
        vertDeccel = speedFactor / 1.125;


        if (isPlayerOne) {
            UP = 87; // w
            DOWN = 83; // s
            LEFT = 65; // a
            RIGHT = 68; // d
            SHOOT = 67; // c
            x = 25;
            y = 50;
        } else {
            UP = 38; // up arrow
            DOWN = 40; // down arrow
            LEFT = 37; // left arrow
            RIGHT = 39; // right arrow
            SHOOT = 77; // m
            x = 75;
            y = 50;
        }


    }

    public void draw() {
        gun.draw(isPlayerOne, x, y, halfWidth); // draw gun
        if (isPlayerOne) {
            StdDraw.picture(x, y, "images/cars/car1.png", halfWidth * 2, halfHeight * 2); // draw car
            HealthBar.drawHealthBar(true, 15, 95, health);
        } else {
            StdDraw.picture(x, y, "images/cars/car2.png", halfWidth * 2, halfHeight * 2); // draw car
            HealthBar.drawHealthBar(false, 85, 95, health);
        }

    }

    public void update() {


        // shooting mechanics


        if (StdDraw.isKeyPressed(SHOOT)) {
            gun.shoot(isPlayerOne);
        }
        gun.update();


        // driving mechanics

        if (StdDraw.isKeyPressed(UP)) {
            fspeed += vertAccel;
            bspeed -= vertAccel * 1.5;
        } else {
            fspeed -= vertDeccel;
            if (fspeed < 0) {
                fspeed = 0;
            }
        }


        if (StdDraw.isKeyPressed(DOWN)) {
            bspeed += vertAccel;
            fspeed -= vertAccel * 1.5;
        } else {
            bspeed -= vertDeccel;
            if (bspeed < 0) {
                bspeed = 0;
            }
        }

        if (StdDraw.isKeyPressed(LEFT)) {
            lspeed += speedFactor;
            rspeed -= speedFactor;
        } else {
            lspeed -= speedFactor;
            if (lspeed < 0) {
                lspeed = 0;
            }
        }

        if (StdDraw.isKeyPressed(RIGHT)) {
            rspeed += speedFactor;
            lspeed -= speedFactor;
        } else {
            rspeed -= speedFactor;
            if (rspeed < 0) {
                rspeed = 0;
            }
        }

        y += fspeed;
        y -= bspeed;
        x += rspeed;
        x -= lspeed;

        // car vertical boundaries

        if (y + halfHeight > 100) {
            y = 100 - halfHeight;
            fspeed = 0;
        }
        if (y - halfHeight < 0) {
            y = halfHeight;
            bspeed = 0;
        }

        // car horizontal boundaries

        if (isPlayerOne && x + halfWidth > middleBoundary) {
            x = middleBoundary - halfWidth;
            rspeed = 0;
        }
        if (!isPlayerOne && x - halfWidth < middleBoundary) {
            x = middleBoundary + halfWidth;
            lspeed = 0;
        }
        if (isPlayerOne && x - halfWidth < sideBoundary) {
            x = sideBoundary + halfWidth;
            health -= lspeed * 5;
            lspeed = 0;
        }
        if (!isPlayerOne && x + halfWidth > sideBoundary) {
            x = sideBoundary - halfWidth;
            health -= rspeed * 5;
            rspeed = 0;
        }


    }

    public double getY() {
        return y;
    }

    public double getHalfHeight() {
        return halfHeight;
    }

    public double getX() {
        return x;
    }

    public double getHalfWidth() {
        return halfWidth;
    }

    public double getHealth() {
        return health;
    }

    public double getGunDamage() {
        return gun.getDamage();
    }


    public void setHealth(double h) {
        health = h;
    }

    public void setMiddleBoundary(double middleBoundary) {
        this.middleBoundary = middleBoundary;
    }

    public void setSideBoundary(double sideBoundary) {
        this.sideBoundary = sideBoundary;
    }

    public Gun getGun(){return gun;}

    public int getGunClipSize(){
        return gun.getClipSize();
    }

    public void multiplyFspeed(double amount){fspeed *= amount;}

    public void addToBspeed(double amount){bspeed += amount;}

}

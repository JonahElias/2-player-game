package entities;

import core.StdDraw;
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

    // numbers for key inputs

    private final int UP;
    private final int DOWN;
    private final int LEFT;
    private final int RIGHT;
    private final int SHOOT;

    public Player(boolean playerOne) {
        x = 50.0;
        y = 50.0;
        halfWidth = 3.5;
        halfHeight = halfWidth * 1.875;
        health = 100;
        isPlayerOne = playerOne;
        gun = new Rifle();

        speedFactor = 0.0125;
        vertAccel = speedFactor * 1.25;
        vertDeccel = speedFactor / 1.125;


        if (isPlayerOne) {
            UP = 87; // w
            DOWN = 83; // s
            LEFT = 65; // a
            RIGHT = 68; // d
            SHOOT = 67; // c
        } else {
            UP = 38; // up arrow
            DOWN = 40; // down arrow
            LEFT = 37; // left arrow
            RIGHT = 39; // right arrow
            SHOOT = 77; // m
        }


    }

    public void draw() {
        gun.draw(isPlayerOne, x, y, halfWidth); // draw gun
        if (isPlayerOne) {
            StdDraw.picture(x, y, "images/cars/car1.png", halfWidth * 2, halfHeight * 2); // draw car
            HealthBar.drawHealthBar(true, 15, 90, health);
        } else {
            StdDraw.picture(x, y, "images/cars/car2.png", halfWidth * 2, halfHeight * 2); // draw car
            HealthBar.drawHealthBar(false, 85, 90, health);
        }

    }

    public void update() {


        // shooting mechanics


        if (StdDraw.isKeyPressed(SHOOT)){
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
            lspeed += speedFactor / 2;
            rspeed -= speedFactor;
        } else {
            lspeed -= speedFactor;
            if (lspeed < 0) {
                lspeed = 0;
            }
        }

        if (StdDraw.isKeyPressed(RIGHT)) {
            rspeed += speedFactor / 2;
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

        // car boundaries

        if (y + halfHeight > 100){
            y = 100 - halfHeight;
            fspeed = 0;
        }
        if (y - halfHeight < 0){
            y = halfHeight;
            bspeed = 0;
        }
        if (x + halfWidth > 100){
            x = 100 - halfWidth;
            rspeed = 0;
        }
        if (x - halfWidth < 0){
            x = halfWidth;
            lspeed = 0;

        }


    }

    public double getY() {return y;}
    public double getHalfHeight(){return halfHeight;}

    public double getHealth(){return health;}
    public double getGunDamage(){return gun.getDamage();}


    public void setHealth(double h){health = h;}



}

package gameobjects;

import engine.StdAudio;
import engine.StdDraw;

public class Player {

    // basic player attributes

    private double x;
    private double y;
    private final double halfWidth;
    private final double halfHeight;
    private int health;
    private final boolean isPlayerOne;

    // driving mechanics variables

    private final double speedFactor;
    private double fspeed;
    private double bspeed;
    private double rspeed;
    private double lspeed;

    // shooting mechanics variables

    private int frames_per_shot = 5;


    // numbers for key inputs

    private final int UP;
    private final int DOWN;
    private final int LEFT;
    private final int RIGHT;
    private final int SHOOT;

    public Player(boolean playerOne) {
        x = 50.0;
        y = 50.0;
        halfWidth = 5.0;
        halfHeight = halfWidth * 1.875;
        isPlayerOne = playerOne;
        speedFactor = 0.0125;

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
        if (isPlayerOne) {
            StdDraw.picture(x, y, "images/car1.png", halfWidth * 2, halfHeight * 2); // draw car
            StdDraw.picture(x + halfWidth, y, "images/gun.png", 3, 3 / 7.5); // draw gun
        } else {
            StdDraw.picture(x, y, "images/car2.png", halfWidth * 2, halfHeight * 2); // draw car
            StdDraw.picture(x - halfWidth, y, "images/gun.png", 3, 3 / 7.5, 180); // draw gun
        }
    }

    public void update(int count) {

        // driving mechanics

        if (StdDraw.isKeyPressed(UP)) {
            fspeed += speedFactor;
            bspeed -= speedFactor * 2.25;
        } else {
            fspeed -= speedFactor * 1.25;
            if (fspeed < 0) {
                fspeed = 0;
            }
        }


        if (StdDraw.isKeyPressed(DOWN)) {
            bspeed += speedFactor;
            fspeed -= speedFactor * 2.25;
        } else {
            bspeed -= speedFactor * 1.25;
            if (bspeed < 0) {
                bspeed = 0;
            }
        }

        if (StdDraw.isKeyPressed(LEFT)) {
            lspeed += speedFactor / 1.5;
            rspeed -= speedFactor;
        } else {
            lspeed -= speedFactor / 1.5;
            if (lspeed < 0) {
                lspeed = 0;
            }
        }

        if (StdDraw.isKeyPressed(RIGHT)) {
            rspeed += speedFactor / 1.5;
            lspeed -= speedFactor;
        } else {
            rspeed -= speedFactor / 1.5;
            if (rspeed < 0) {
                rspeed = 0;
            }
        }

        y += fspeed;
        y -= bspeed;
        x += rspeed;
        x -= lspeed;

        // shooting mechanics

        if (StdDraw.isKeyPressed(SHOOT) && count % frames_per_shot == 0){
            System.out.println("shot");
            StdAudio.playInBackground("audio/akdualshot.wav");
        }

    }

}

package entities.weapons;

import tools.EntityManager;
import tools.StdAudio;
import tools.StdDraw;

import java.awt.*;
import java.util.Random;

public class Gun {

    double width; // full width of gun
    double height; // full height of gun
    double fireSize; // size of gunfire drawn
    double damage; // how much damage the gun deals
    int framesPerShot; // how many frames between each shot (speed)
    int shotCount; // how many frames has passed since the last shot
    int framesPerAudio; // how many frames between each gunfire audio
    int audioCount; // how many frames has passed since last audio playing
    int roundsInStorage;
    int clipSize;
    int roundsInClip;
    boolean shouldDrawFire; // if gunfire should be drawn
    String audio_path; // path for gunfire audio
    String image_path; // path for gun image
    Font displayAmmo = new Font("SansSerif", Font.BOLD, 17);


    public void shoot(boolean isPlayerOne) {
        if (framesPerShot - shotCount == 0 && roundsInClip != 0) {
            EntityManager.bulletCollision(isPlayerOne);
            roundsInClip -= 1;
            shotCount = 0;
        }

        shouldDrawFire = true;

        if (roundsInClip != 0){playAudio();}


    }

    // draws gun and gunfire

    public void draw(boolean isPlayerOne, double playerX, double playerY, double playerHalfWidth) {
        if (isPlayerOne) {
            StdDraw.picture(playerX + playerHalfWidth, playerY, image_path, width, height); // draw gun
            drawAmmo(10);
        } else {
            StdDraw.picture(playerX - playerHalfWidth, playerY, image_path, width, height, 180); // draw gun
            drawAmmo(90);
        }

        if (shouldDrawFire) {
            drawFire(isPlayerOne, playerX, playerY, playerHalfWidth);
            shouldDrawFire = false;
        }
    }

    // updates shotCount and audioCount

    public void update() {
        if (shotCount != framesPerShot) {
            shotCount++;
        }
        if (audioCount != framesPerAudio) {
            audioCount++;
        }
        checkReload(); // checks if gun needs to be reloaded
    }


    // draws gunfire image at the end of the gun

    private void drawFire(boolean isPlayerOne, double playerX, double playerY, double playerHalfWidth) {
        // gets random gunfire image

        String[] fire_images = new String[4];
        fire_images[0] = "images/fire/fire1.png";
        fire_images[1] = "images/fire/fire2.png";
        fire_images[2] = "images/fire/fire3.png";
        fire_images[3] = "images/fire/fire3.png";
        Random random = new Random();
        String image = fire_images[random.nextInt(4)];


        if (shotCount <= framesPerShot / 1.5) {
            if (isPlayerOne) {
                StdDraw.picture(playerX + playerHalfWidth + width / 2 + 0.5, playerY, image, fireSize, fireSize);
            } else {
                StdDraw.picture(playerX - playerHalfWidth - width / 2 - 0.5, playerY, image, fireSize, fireSize);
            }
        }
    }

    private void drawAmmo(double xCoord){
        StdDraw.setFont(displayAmmo);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(xCoord, 90, Integer.toString(roundsInStorage));
        StdDraw.text(xCoord, 87.5, Integer.toString(roundsInClip));
    }

    private void checkReload(){
        if (roundsInClip == 0){
            if (roundsInStorage > clipSize){
                roundsInClip += clipSize;
                roundsInStorage -= clipSize;
            }else{
                roundsInClip = roundsInStorage;
                roundsInStorage = 0;

            }

        }
    }


    // plays audio according to set timing

    private void playAudio() {
        if (framesPerAudio - audioCount == 0) {
            StdAudio.playInBackground(audio_path);
            audioCount = 0;
        }
    }

    public double getDamage(){return damage;}

    public int getRoundsInStorage(){
        return roundsInStorage;
    }

    public int getRoundsInClip(){return roundsInClip;}

    public int getClipSize(){return clipSize;}

    public void addAmmo(int ammo){roundsInStorage += ammo;}

}

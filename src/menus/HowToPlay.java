package menus;

import tools.StdDraw;

import java.awt.*;

public class HowToPlay {


    static Color defaultColor = new Color(76, 82, 87);
    static Color defaultHoverColor = new Color(45, 52, 54, 255);
    static Font defaultFont = new Font("SansSerif", Font.BOLD, 14);

    static Font titleFont = new Font("SansSerif", Font.PLAIN, 30);
    static Font header = new Font("SansSerif", Font.PLAIN, 20);

    static Color controlsPanel = new Color(7, 48, 108);
    static Color controlTextColor = new Color(194, 16, 16);

    static Color powerupsPanel = new Color(22, 0, 40);

    static Color obstaclePanel = new Color(64, 0, 0);
    static Color gameplayPanel = new Color(0, 45, 36);


    public static void run(){
        Button back = new Button(7, 3, 6, 2, "back");
        back.setBackgroundColor(defaultColor);
        back.setHoverColor(defaultHoverColor);
        back.setFont(defaultFont);

        while (!back.clicked()){
            StdDraw.clear();

            drawControls();
            drawPowerUps();
            drawGamplay();
            back.draw();

            StdDraw.show(1000 / 60);
        }

    }

    private static void drawControls(){

        StdDraw.setPenColor(controlsPanel);
        StdDraw.filledRectangle(15, 75, 17.5, 25);

        StdDraw.setPenColor(Color.WHITE);
        StdDraw.setFont(titleFont);
        StdDraw.text(15, 90, "Controls");

        StdDraw.setPenColor(controlTextColor);
        StdDraw.setFont(header);
        StdDraw.text(15, 80, "Player One:");
        StdDraw.text(15, 65, "Player Two:");

        StdDraw.setPenColor(Color.WHITE);
        StdDraw.setFont(defaultFont);
        StdDraw.text(15, 75, "Movement: WASD");
        StdDraw.text(15, 72, "Shooting: C");
        StdDraw.text(15, 60, "Movement: Arrow Keys");
        StdDraw.text(15, 57, "Shooting: M");
    }

    private static void drawPowerUps(){
        StdDraw.setPenColor(powerupsPanel);
        StdDraw.filledRectangle(67, 75, 67 - 32.5, 25);



        StdDraw.setPenColor(Color.WHITE);
        StdDraw.setFont(titleFont);
        StdDraw.text(67, 90, "Powerups");

        StdDraw.picture(50, 72.5, "images/powerups/tilt.png", 15, 15);
        StdDraw.picture(82.5, 72.5, "images/powerups/ammo.png", 15, 15);


        StdDraw.setFont(header);
        StdDraw.text(50, 60, "Tilt");
        StdDraw.text(82.5, 60, "Ammo");

        StdDraw.setFont(defaultFont);
        StdDraw.text(50, 55, "Restores Health");
        StdDraw.text(82.5, 55, "Restores Ammo");

    }

    private static void drawGamplay(){
        StdDraw.setPenColor(obstaclePanel);
        StdDraw.filledRectangle(67, 25, 67 - 32.5, 25);

        StdDraw.setPenColor(gameplayPanel);
        StdDraw.filledRectangle(15, 25, 17.5, 25);


        StdDraw.setPenColor(Color.WHITE);
        StdDraw.setFont(titleFont);
        StdDraw.text(67, 40, "Obstacles");
        StdDraw.text(15, 45, "Gameplay");

        StdDraw.picture(67, 25, "images/roadimages/obstacle.png", 15, 15);

        StdDraw.setFont(defaultFont);
        StdDraw.text(67, 13, "Crashing into obstacles lowers health");
        StdDraw.text(67, 10, "and slows you down");
        StdDraw.text(15, 40, "- Shoot the other player");
        StdDraw.text(15, 37, "- Avoid obstacles");
        StdDraw.text(15, 34, "- Collect powerups");


    }










}

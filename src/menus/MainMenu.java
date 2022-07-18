package menus;

import tools.StdDraw;
import java.awt.*;


public class MainMenu {


    static Color startGameColor = new Color(99, 110, 114);
    static Color startGamehoverColor = new Color(45, 52, 54, 255);
    static Font startGameFont = new Font("SansSerif", Font.BOLD, 14);

    static Font titleFont = new Font("SansSerif", Font.ITALIC, 70);
    static Font cc = new Font("SansSerif", Font.BOLD, 10);



    public static String run(){
        Button startGame = new Button(50, 45, 6.5, 3, "Play");
        startGame.setBackgroundColor(startGameColor);
        startGame.setHoverColor(startGamehoverColor);
        startGame.setFont(startGameFont);

        Button howToPlay = new Button(50,35, 6.75, 3, "How To Play");
        howToPlay.setBackgroundColor(startGameColor);
        howToPlay.setHoverColor(startGamehoverColor);
        howToPlay.setFont(startGameFont);

        while (!startGame.clicked()){
            StdDraw.clear();



            StdDraw.setFont(titleFont);
            StdDraw.text(50, 60, "Drive-By");

            StdDraw.setFont(cc);
            StdDraw.text(50, 2, "© 2022 JElias. All rights reserved.");
            drawCars();


            startGame.draw();
            howToPlay.draw();


            if (howToPlay.clicked()){
                HowToPlay.run();
            }



            StdDraw.show(1000 / 60);
        }
        return WeaponSelect.run();
    }


    private static void drawCars(){
        double width = 15;
        double height = width * 1.41;
        StdDraw.picture(20, 70, "images/cars/titlecar1.png", width, height);
        StdDraw.picture(82.5, 75, "images/cars/titlecar2.png", width - 1, height);
    }



}

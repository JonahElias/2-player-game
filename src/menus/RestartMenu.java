package menus;

import tools.StdDraw;

import java.awt.*;

public class RestartMenu {

    static Color buttonColor = new Color(81, 84, 90);
    static Color buttonHoverColor = new Color(55, 62, 64, 255);
    static Font buttonFont = new Font("SansSerif", Font.BOLD, 14);
    static Font title = new Font("SansSerif", Font.BOLD, 45);


    public static String run(String message){

        StdDraw.setFont(title);
        StdDraw.setPenColor(Color.white);
        StdDraw.text(50, 70, message);


        Button mainMenu = new Button(50, 50, 6, 2.5, "Main Menu");
        mainMenu.setBackgroundColor(buttonColor);
        mainMenu.setHoverColor(buttonHoverColor);
        mainMenu.setFont(buttonFont);

        Button restart = new Button(50, 40, 6, 2.5, "Restart");
        restart.setBackgroundColor(buttonColor);
        restart.setHoverColor(buttonHoverColor);
        restart.setFont(buttonFont);

        while (!mainMenu.clicked()){

            // StdDraw.clear();
            restart.draw();
            mainMenu.draw();
            StdDraw.show(1000 / 60);

            if (restart.clicked()){
                return WeaponSelect.run();
            }

        }

        return MainMenu.run();




    }












}

package menus;

import tools.StdDraw;

import java.awt.*;

public class WeaponSelect {

    static Color buttonColor = new Color(99, 110, 114);
    static Color buttonHoverColor = new Color(45, 52, 54, 255);
    static Font buttonFont = new Font("SansSerif", Font.BOLD, 14);



    public static String run(){
        Button back = new Button(7, 3, 6, 2, "back");

        Button rifle = new Button(40, 50, 6, 6, "");
        rifle.setBackgroundColor(buttonColor);
        rifle.setHoverColor(buttonHoverColor);

        Button machinepistol = new Button(60, 50, 6, 6, "");
        machinepistol.setBackgroundColor(buttonColor);
        machinepistol.setHoverColor(buttonHoverColor);

        back.setBackgroundColor(buttonColor);
        back.setHoverColor(buttonHoverColor);
        back.setFont(buttonFont);

        String weapon = "";

        while (!rifle.clicked() && !machinepistol.clicked()){

            StdDraw.clear();
            StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 35));
            StdDraw.text(50, 65, "Choose Weapon");


            back.draw();

            rifle.draw();
            rifle.drawPicture("images/weapons/riflesideview.png", 10, 10 / 3.78);

            machinepistol.draw();
            machinepistol.drawPicture("images/weapons/machinepistolsideview.png", 8, 8 / 2.28);


            StdDraw.show(1000 / 60);

            if (back.clicked()){
                MainMenu.run();
            }

            if (rifle.clicked()){weapon = "rifle";}
            else {weapon = "machinepistol";}




        }
        return weapon;

    }

}

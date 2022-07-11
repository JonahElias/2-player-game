package entities;

import tools.StdDraw;
import java.awt.Color;

public class HealthBar {

    private static final double height = 1;
    private static final Color healthColor = new Color(98, 222, 43);
    private static final Color backgroundColor = new Color(120, 30, 30);


    public static void drawHealthBar(boolean isPlayerOne, double x, double y, double health){
        double width = (double) health / 12.5; // width of health bar
        double margin = (double) (100 - health) / 12.5; // margin from sides

        if (width < 0){width = 0;}


        // draw background
        StdDraw.setPenColor(backgroundColor);
        StdDraw.filledRectangle(x, y, 100 / 12.5, height);

        // draw health bar
        StdDraw.setPenColor(healthColor);
        if(isPlayerOne){StdDraw.filledRectangle(x + margin, y, width, height);}
        else {StdDraw.filledRectangle(x - margin, y, width, height);}


        // draw border
        StdDraw.setPenColor(Color.black);
        StdDraw.setPenRadius(0.003);
        StdDraw.rectangle(x, y, 100 / 12.5, height);

    }

}

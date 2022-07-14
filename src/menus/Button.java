package menus;

import tools.StdDraw;
import java.awt.*;


public class Button {

    double x;
    double y;
    double width;
    double height;
    String text;
    Font font = new Font("SansSerif", Font.PLAIN, 16);
    Color background = Color.BLACK;
    Color textColor = Color.white;
    Color hoverColor = new Color(108, 108, 108);


    public Button(double x, double y, double width, double height, String text){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
        StdDraw.setFont(font);

    }

    public void draw(){
        double mousex = StdDraw.mouseX();
        double mousey = StdDraw.mouseY();
        if (mousex > x - width && mousex < x + width && mousey > y - height && mousey < y + height){
            StdDraw.setPenColor(hoverColor);
        }else{
            StdDraw.setPenColor(background);
        }
        StdDraw.filledRectangle(x, y, width, height);

        StdDraw.setFont(font);
        StdDraw.setPenColor(textColor);
        StdDraw.text(x, y, text);
    }

    public boolean clicked(){
        boolean ispressed = StdDraw.isMousePressed();
        double mousex = StdDraw.mouseX();
        double mousey = StdDraw.mouseY();
        if (mousex > x - width && mousex < x + width && mousey > y - height && mousey < y + height && ispressed){
            return true;
        }else{
            return false;
        }
    }

    public void setFont(Font f){font = f;}

    public void setBackgroundColor(Color c){background = c;}

    public void setTextColor(Color c){textColor = c;}

    public void setHoverColor(Color c){hoverColor = c;}


}
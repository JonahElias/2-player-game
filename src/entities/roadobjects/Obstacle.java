package entities.roadobjects;

import tools.StdDraw;

public class Obstacle {


    private double x;
    private double y;
    private double halfWidth;
    private double halfHeight;
    private double speed;
    private double rotation;
    private boolean hit;


    public Obstacle(double x){
        this.x = x;
        y = 125;
        halfWidth = 5;
        halfHeight = halfWidth / 1.16;
        speed = 1.15;
        rotation = 0;
        hit = false;

    }

    public void update(){y -= speed;}

    public void draw(){
        StdDraw.picture(x, y, "images/roadimages/obstacle.png", halfWidth * 2, halfHeight * 2, rotation);
    }


    public double getLeftEdge() {
        return x - halfWidth;
    }

    public double getRightEdge(){
        return x + halfWidth;
    }

    public double getTopEdge(){
        return y + halfHeight;
    }

    public double getBottomEdge(){
        return y - halfHeight;
    }
    public double getX(){return x;}

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit){this.hit = hit;}
    public void setRotation(double rotation){this.rotation = rotation;}
}

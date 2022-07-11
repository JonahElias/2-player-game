package entities.roadobjects;

import tools.StdDraw;

public class Barrier {

    private double x;
    private double y;
    private double halfWidth;
    private double halfHeight;


    public Barrier(double y){
        halfWidth = 2;
        halfHeight = 10;
        x = halfWidth;
        this.y = y;


    }


    public void draw(){
        StdDraw.picture(x, y, "images/roadimages/barrier.png", halfWidth * 2, halfHeight * 2);
        StdDraw.picture(100 - x, y, "images/roadimages/barrier.png", halfWidth * 2, halfHeight * 2);
    }


    public void update(){
        y -= 2;
    }


    public double getTopEdge(){
        return y + halfHeight;
    }

    public double getHalfHeight(){return halfHeight;}
    public double getHalfWidth(){return halfWidth;}





}

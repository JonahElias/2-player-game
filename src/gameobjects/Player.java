package gameobjects;

import engine.StdDraw;

public class Player{

   // basic player attributes

   private double x;
   private double y;
   private final double halfWidth;
   private final double halfHeight;
   private final double increment;

   private double forward_momentum;
   private double backward_momentum;
   private double left_momentum;
   private double right_momentum;
   private double speedFactor;


   private int health;

   // numbers for key inputs
   private final int UP;
   private final int DOWN;
   private final int LEFT;
   private final int RIGHT;
   private final int SHOOT;

   private boolean isPlayerOne;

   public Player(boolean playerOne){
       x = 50.0;
       y = 50.0;
       halfWidth = 5.0;
       halfHeight = halfWidth * 1.875;
       isPlayerOne = playerOne;
       increment = 0.5;
       speedFactor = 0.0125;

       if (isPlayerOne){
          UP = 87; // w
          DOWN = 83; // s
          LEFT = 65; // a
          RIGHT = 68; // d
          SHOOT = 67; // c
       }else {
          UP = 38; // up arrow
          DOWN = 40; // down arrow
          LEFT = 37; // left arrow
          RIGHT = 39; // right arrow
          SHOOT = 77; // m
       }


   }

   public void draw(){
      if (isPlayerOne){
         StdDraw.picture(x, y, "images/car1.png", halfWidth * 2, halfHeight * 2); // draw car
         StdDraw.picture(x + halfWidth, y, "images/gun.png", 3, 3 / 7.5); // draw gun
      }else {
         StdDraw.picture(x, y, "images/car2.png", halfWidth * 2, halfHeight * 2); // draw car
         StdDraw.picture(x - halfWidth, y, "images/gun.png", 3, 3 / 7.5, 180); // draw gun
      }
   }

   public void update(){

      // updates car position based on keyboard inputs
      // keeps track of momentum so car movement is more realistic


      if (StdDraw.isKeyPressed(UP)){
         y += increment;
         forward_momentum += speedFactor;
      }else{
         forward_momentum -= speedFactor;
         if (forward_momentum < 0){
            forward_momentum = 0;
         }
      }


      if (StdDraw.isKeyPressed(DOWN)){
         y -= increment;
         backward_momentum += speedFactor;

      }else {
         backward_momentum -= speedFactor;
         if (backward_momentum < 0){
            backward_momentum = 0;
         }
      }

      if (StdDraw.isKeyPressed(LEFT)){
         x -= increment;
         left_momentum += speedFactor;
      }else {
         left_momentum -= speedFactor;
         if (left_momentum < 0){
            left_momentum = 0;
         }
      }



      if (StdDraw.isKeyPressed(RIGHT)){
         x += increment;
         right_momentum += speedFactor;
      }else {
         right_momentum -= speedFactor;
         if (right_momentum < 0){
            right_momentum = 0;
         }
      }

      // check if two keys are pressed, prevents unwanted speed gain when moving diagonally

      if (StdDraw.isKeyPressed(UP) && StdDraw.isKeyPressed(RIGHT)){
         y -= increment / 2;
         x -= increment / 2;
      }

      if (StdDraw.isKeyPressed(UP) && StdDraw.isKeyPressed(LEFT)){
         y -= increment / 2;
         x += increment / 2;
      }

      if (StdDraw.isKeyPressed(DOWN) && StdDraw.isKeyPressed(RIGHT)){
         y += increment / 2;
         x -= increment / 2;
      }


      if (StdDraw.isKeyPressed(DOWN) && StdDraw.isKeyPressed(LEFT)){
         y += increment / 2;
         x += increment / 2;
      }


      y += forward_momentum;
      y -= backward_momentum;
      x -= left_momentum;
      x += right_momentum;



   }

}

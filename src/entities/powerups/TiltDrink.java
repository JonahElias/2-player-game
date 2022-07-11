package entities.powerups;
import core.EntityManager;

import java.util.Random;

public class TiltDrink extends PowerUp{


    public TiltDrink(double x){
        Random r = new Random();
      this.x = x;
      rarity = 2;
      image_path = "images/powerups/tilt.png";


    }

    @Override
    public void useEffect(boolean isPlayerOne) {
        EntityManager.givePlayerHealthBoost(isPlayerOne, 10);
    }
}

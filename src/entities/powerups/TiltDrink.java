package entities.powerups;
import tools.EntityManager;

public class TiltDrink extends PowerUp{


    public TiltDrink(double x){
      this.x = x;
      rarity = 7;
      image_path = "images/powerups/tilt.png";
    }

    @Override
    public void useEffect(boolean isPlayerOne) {
        EntityManager.updatePlayerHealth(isPlayerOne, 10);
    }
}

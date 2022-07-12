package entities.powerups;

import tools.EntityManager;

public class Ammo extends PowerUp{

    public Ammo(double x){
        this.x = x;
        rarity = 3;
        image_path = "images/powerups/ammo.png";
    }


    @Override
    public void useEffect(boolean isPlayerOne) {
        EntityManager.updatePlayerAmmo(isPlayerOne);
    }
}

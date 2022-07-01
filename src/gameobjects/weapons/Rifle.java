package gameobjects.weapons;

public class Rifle extends Gun {

    public Rifle() {
        width = 3;
        height = width / 7.5;
        fireSize = 1.375;
        damage = 0;
        framesPerShot = 5;
        shotCount = 0;
        framesPerAudio = 6;
        audioCount = 0;
        shouldDrawFire = false;
        audio_path = "audio/ak.wav";
        image_path = "images/weapons/rifle.png";


    }
}
package entities.weapons;

public class Rifle extends Gun {

    public Rifle() {
        width = 2.1;
        height = width / 6;
        fireSize = 1.125;
        damage = 2;
        framesPerShot = 5;
        shotCount = 0;
        framesPerAudio = 7;
        audioCount = 0;
        roundsInStorage = 70;
        clipSize = 25;
        roundsInClip = clipSize;
        framesPerReload = 90;
        reloadCount = 0;
        shouldDrawFire = false;
        audio_path = "audio/ak.wav";
        image_path = "images/weapons/rifle.png";


    }
}
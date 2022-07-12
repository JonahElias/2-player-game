package entities.weapons;

public class MachinePistol extends Gun {

    public MachinePistol() {
        width = 1.125;
        height = width / 4;
        fireSize = 1;
        damage = 1;
        framesPerShot = 10;
        shotCount = 0;
        framesPerAudio = 8;
        audioCount = 0;
        roundsInStorage = 30;
        clipSize = 12;
        roundsInClip = clipSize;
        shouldDrawFire = false;
        audio_path = "audio/pistol.wav";
        image_path = "images/weapons/machinepistol.png";


    }
}
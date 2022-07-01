package gameobjects.weapons;

public class MachinePistol extends Gun {

    public MachinePistol() {
        width = 1.5;
        height = width / 5.9;
        fireSize = 1.25;
        damage = 0;
        framesPerShot = 10;
        shotCount = 0;
        framesPerAudio = 8;
        audioCount = 0;
        shouldDrawFire = false;
        audio_path = "audio/pistol.wav";
        image_path = "images/weapons/machinepistol.png";


    }
}
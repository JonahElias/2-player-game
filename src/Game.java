import entities.weapons.Gun;
import entities.weapons.MachinePistol;
import entities.weapons.Rifle;
import menus.MainMenu;
import tools.EntityManager;
import tools.StdDraw;

public class Game {

    /*
    TODO:
        add sound for car
        change sound for machine pistol
        finalize menus
        make distance affect damage
     */

    private final int FPS_CAP = 60; // max FPS


    public static void main(String[] args) {
        setup();
        String weapon = MainMenu.run();
        if (weapon.equals("rifle")){new Game(new Rifle(), new Rifle());}
        else{new Game(new MachinePistol(), new MachinePistol());}
    }


    public Game(Gun gunOne, Gun gunTwo) {
        EntityManager.init(gunOne, gunTwo);
        mainGameLoop();
    }

    public static void setup() {
        // setup StdDraw
        StdDraw.setCanvasSize(700, 700);
        StdDraw.setScale(0, 100);


    }


    public void mainGameLoop() {

        while (true) {
            long start = System.currentTimeMillis();

            StdDraw.clear();
            EntityManager.drawEntities();
            EntityManager.updateEntities();
            EntityManager.spawnEntities();
            EntityManager.removeInvalidEntities();



            long end = System.currentTimeMillis();

            // frame timing checks
            long cpu_time = end - start;
            int cpu_time_int = (1000 / FPS_CAP) - (int) cpu_time;
            if (cpu_time_int < 0) {
                cpu_time_int = 1000 / FPS_CAP;
            }
            StdDraw.show(cpu_time_int);
        }
    }


}

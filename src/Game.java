import com.sun.tools.javac.Main;
import menus.MainMenu;
import tools.EntityManager;
import tools.StdDraw;

public class Game {

    /*
    TODO:
        add sound for car
        change sound for machine pistol
        add menus
        make distance affect damage
     */

    private final int FPS_CAP = 60; // max FPS


    public static void main(String[] args) {
        setup();
        MainMenu.run();
        new Game();
    }


    public Game() {
        mainGameLoop();
    }

    public static void setup() {
        // setup StdDraw
        StdDraw.setCanvasSize(700, 700);
        StdDraw.setScale(0, 100);
        EntityManager.init();

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

import tools.EntityManager;
import tools.StdDraw;

public class Game {

    private final int FPS_CAP = 60; // max FPS
    private int count; // keeps track of gameloop


    public static void main(String[] args) {
        new Game();
    }


    public Game() {
        setup();
        mainGameLoop();
    }

    public void setup() {
        // setup StdDraw
        StdDraw.setCanvasSize(700, 700);
        StdDraw.setScale(0, 100);

        // initialize game variables
        EntityManager.init();
        count = 0;

    }


    public void mainGameLoop() {

        while (true) {
            long start = System.currentTimeMillis();

            StdDraw.clear();
            EntityManager.drawEntities();
            EntityManager.updateEntities();
            EntityManager.spawnEntities();
            EntityManager.removeInvalidEntities();
            count++;
            if (count >= FPS_CAP) {
                count = 0;
            }


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

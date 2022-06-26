import engine.StdDraw;
import gameobjects.Player;

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
        count = 0;

    }


    public void mainGameLoop() {

        Player p1 = new Player(false);
        while (true) {
            long start = System.currentTimeMillis();

            StdDraw.clear();
            p1.draw();
            p1.update(count);

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
            //System.out.println("cpu time: " + cpu_time);
        }
    }


}

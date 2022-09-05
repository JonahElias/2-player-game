import entities.weapons.Gun;
import entities.weapons.MachinePistol;
import entities.weapons.Rifle;
import menus.MainMenu;
import menus.RestartMenu;
import tools.EntityManager;
import tools.StdDraw;

public class Game {

    /*
    TODO:
        add sound for car
        change sound for machine pistol
     */

    private final int FPS_CAP = 60; // max FPS


    public static void main(String[] args) {
        setup();
        String weapon = MainMenu.run();
        startGame(weapon);
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

    public static void startGame(String weapon){
        if (weapon.equals("rifle")){new Game(new Rifle(), new Rifle());}
        else{new Game(new MachinePistol(), new MachinePistol());}
    }


    public void mainGameLoop() {

        while (EntityManager.getPlayerHealth(true) > 0 && EntityManager.getPlayerHealth(false) > 0) {
            long start = System.currentTimeMillis();

            StdDraw.clear();

            EntityManager.updateEntities();
            EntityManager.spawnEntities();
            EntityManager.drawEntities();
            EntityManager.removeInvalidEntities();



            long end = System.currentTimeMillis();

            // frame timing checks
            long cpu_time = end - start;
            int cpu_time_int = (1000 / FPS_CAP) - (int) cpu_time;
            if (cpu_time_int < 0) {
                cpu_time_int = 1000 / FPS_CAP;
            }
            StdDraw.show(cpu_time_int);
            System.out.println(cpu_time_int);
        }
        String message;
        if (EntityManager.getPlayerHealth(true) <= 0){
           message = "Blue Wins!";
        }else{
            message = "Red Wins!";
        }
        String weapon = RestartMenu.run(message);
        startGame(weapon);


    }




}

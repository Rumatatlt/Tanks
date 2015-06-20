package vyki.game.tanks.objects.Environment;

import vyki.game.tanks.GlobalVariables;
import vyki.game.tanks.Sprite;
import vyki.game.tanks.objects.Shots.EnemyShot;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;



public class EnemyTank extends AbstractTank{
    private static ArrayList<EnemyTank> enemyTanks = GlobalVariables.enemyTanks;
    private Sprite sprite;
    private int speed = 3;
    private String lastCourse="up";
    private int numCourse=1;
    private int X = 0;
    private int Y = 0;
    private int respownX;
    private int respownY;
    private static int collSize = 1;
    public boolean alive = true;
    private static int count = 0;
    //private static Path path = Paths.get(".\\build\\classes\\tankUP.png");
    //private static Image background = Toolkit.getDefaultToolkit().getImage(String.valueOf(path));

    public EnemyTank() {
        this.sprite = getSprite("tankDown.png", respownX, respownY);
        this.respownX = GlobalVariables.homeLocation_X;
        this.respownY = GlobalVariables.homeLocation_Y;
        this.X = this.respownX;
        this.Y = this.respownY;
        getSprite().x=this.respownX;
        getSprite().y=this.respownY;
        collSize++;
        System.out.println("new EnemyTank, total= " + collSize);

    }


    public static void enemyTankAI(){
        Random ran = new Random();

        if (count%100==0){
            for (EnemyTank tank : enemyTanks){
                tank.numCourse = ran.nextInt(4)+1;
            }
        }
        for (EnemyTank tank : enemyTanks){
            if (!tank.alive){tank.enemyTankDestroyed();} else {
                //tank.moveRight();
                //tank.shoot(GlobalVariables.shots);
            if (findTargets()){
                tank.shoot();
            }
                switch (tank.numCourse) {
                    case 1: tank.moveRight();
                        break;
                    case 2:  tank.moveLeft();
                        break;
                    case 3:  tank.moveDown();
                        break;
                    case 4:  tank.moveUp();
                        break;
                }
            }
        }
        count++;
    }



    public static void enemyTankConstructor(){
        if (!createCheck(enemyTanks, 0, 0)){
            enemyTanks.add(new EnemyTank());
            enemyTanks.add(new EnemyTank());
        }
    }
    public static void patrul(){

    }

    public static boolean findTargets(){
        int TankX,TankY, radius;
        boolean result;
        ListIterator it = enemyTanks.listIterator();
        int coord[];
        radius = 150;

        while(it.hasNext()){
            EnemyTank pr = (EnemyTank) it.next();
            coord = pr.getSprite().getCoordinates();
            TankX=coord[0];
            TankY=coord[1];
            result = Math.sqrt(Math.pow((TankX-GlobalVariables.player1_X),2) +  Math.pow((TankY-GlobalVariables.player1_Y),2)) < radius;
            if (result){
                System.out.println("target finded");
                return true;
            }
        }
        return false;
    }

    public void enemyTankDestroyed(){
        this.X -=0;
        getSprite().x=this.X;
        if (!"Destroyed".equals(lastCourse)){
            getSprite().setImage(getImage("boom.png"));
            lastCourse = "Destroyed";
        }
    }

    public void moveLeft(){
        this.X -=speed;
        getSprite().x=this.X;
        if (!"left".equals(lastCourse)){
            getSprite().setImage(getImage("tankLeft.png"));
            lastCourse = "left";
        }
    }
    public void moveRight(){
        this.X +=speed;
        getSprite().x=this.X;
        if (!"right".equals(lastCourse)){
            getSprite().setImage(getImage("tankRight.png"));
            lastCourse = "right";
        }
    }
    public void moveDown(){
        this.Y +=speed;
        getSprite().y=this.Y;
        if (!"down".equals(lastCourse)){
            getSprite().setImage(getImage("tankDown.png"));
            lastCourse = "down";
        }
    }
    public void moveUp(){
        this.Y -=speed;
        getSprite().y=this.Y;
        if (!"up".equals(lastCourse)){
            getSprite().setImage(getImage("tankUp.png"));
            lastCourse = "up";
        }
    }
    public void shoot(){
        GlobalVariables.enemyShots.add(new EnemyShot(lastCourse, X, Y));
    }

    private static boolean createCheck(ArrayList<EnemyTank> enemyTanks, int X, int Y){
        boolean val = false;
        for ( EnemyTank tank : enemyTanks){
            if (tank.respownX == (X + GlobalVariables.homeLocation_X) && tank.respownY == (Y + GlobalVariables.homeLocation_Y)){
                val = true;
                break;
            } else {
                val = false;
            }
        }
        return val;
    }
    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public Sprite getSprite() {
        return sprite;
    }
    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
/*
    public Sprite getSprite(String path) {
        sprite = new Sprite(getImage(path), respownX, respownY);
        return sprite;
    }
    public Image getImage(String path) {
        BufferedImage sourceImage = null;
        try {
            URL url = this.getClass().getClassLoader().getResource(path);
            sourceImage = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image image = Toolkit.getDefaultToolkit().createImage(sourceImage.getSource());
        return image;
    }
    */
}

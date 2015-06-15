package vyki.game.tanks.objects.Environment;

import vyki.game.tanks.GlobalVariables;
import vyki.game.tanks.Sprite;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static java.lang.Thread.sleep;


public class EnemyTank{
    private Sprite sprite;
    public int speed = 1;
    private String lastCourse="up";
    public int X = 0;
    public int Y = 0;
    public int respownX = 0;
    public int respownY = 0;
    private static int collSize = 1;
    public boolean alive = true;
    //private static Path path = Paths.get(".\\build\\classes\\tankUP.png");
    //private static Image background = Toolkit.getDefaultToolkit().getImage(String.valueOf(path));

    public EnemyTank() {
        this.sprite = getSprite("tankDown.png");
        this.respownX = GlobalVariables.homeLocation_X + 0;
        this.respownY = GlobalVariables.homeLocation_Y + 0;
        getSprite().x=this.respownX;
        getSprite().y=this.respownY;
        collSize++;
        System.out.println("new EnemyTank, total= " + collSize);
    }


    public static void enemyTankAI(ArrayList<EnemyTank> enemyTanks){
        for (EnemyTank tank : enemyTanks){
            if (!tank.alive){tank.enemyTankDestroyed();} else {
                tank.moveLeft();
            }
        }
    }

    public void enemyTankDestroyed(){
        this.X -=0;
        //GlobalVariables.mapX=GlobalVariables.mapX+speed;
        getSprite().x=this.X;
        if (!"Destroyed".equals(lastCourse)){
            getSprite().setImage(getImage("boom.png"));
            lastCourse = "Destroyed";
        }
    }

    public void moveLeft(){
        this.X -=speed;
        //GlobalVariables.mapX=GlobalVariables.mapX+speed;
        getSprite().x=this.X;
        getSprite().setImage(getImage("tankLeft.png"));
        if (!"left".equals(lastCourse)){
            getSprite().setImage(getImage("tankLeft.png"));
            lastCourse = "left";
        }

        //lastCourse = "left";
    }


    public static void enemyTankConstructor(ArrayList<EnemyTank> enemyTanks){
        if (!findShard(enemyTanks,0,0)){
            enemyTanks.add(new EnemyTank());
        }

    }

    private static boolean findShard(ArrayList<EnemyTank> enemyTanks, int X, int Y){
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

    public Sprite getSprite() {
        return sprite;
    }
    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public Sprite getSprite(String path) {
        sprite = new Sprite(getImage(path), X, Y);
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

}

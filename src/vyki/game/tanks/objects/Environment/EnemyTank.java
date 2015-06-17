package vyki.game.tanks.objects.Environment;

import vyki.game.tanks.Game;
import vyki.game.tanks.GlobalVariables;
import vyki.game.tanks.Sprite;
import vyki.game.tanks.objects.Shots.PlayerShot;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

import static java.lang.Thread.sleep;


public class EnemyTank{
    private static ArrayList<EnemyTank> enemyTanks = GlobalVariables.enemyTanks;
    private Sprite sprite;
    public int speed = 3;
    private String lastCourse="up";
    private static int numCourse=1;
    public int X = 0;
    public int Y = 0;
    public int respownX;
    public int respownY;
    private static int collSize = 1;
    public boolean alive = true;
    private static int count = 0;
    //private static Path path = Paths.get(".\\build\\classes\\tankUP.png");
    //private static Image background = Toolkit.getDefaultToolkit().getImage(String.valueOf(path));

    public EnemyTank() {
        this.sprite = getSprite("tankDown.png");
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
        count++;
        Random ran = new Random();
        if (count%100==0){
            numCourse = ran.nextInt(4)+1;
            System.out.println("rand= " + numCourse);
        }
            for (EnemyTank tank : enemyTanks){
                if (!tank.alive){tank.enemyTankDestroyed();} else {
                    //tank.moveRight();
                    //tank.shoot(GlobalVariables.shots);

                    switch (numCourse) {
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



    }



    public static void enemyTankConstructor(){
        if (!findShard(enemyTanks,0,0)){
            enemyTanks.add(new EnemyTank());
        }
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
    public void shoot(ArrayList<PlayerShot> shots){
        shots.add(new PlayerShot(lastCourse,GlobalVariables.player1_X,GlobalVariables.player1_Y));
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

}

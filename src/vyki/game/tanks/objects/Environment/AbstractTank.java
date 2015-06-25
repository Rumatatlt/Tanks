package vyki.game.tanks.objects.Environment;

import vyki.game.tanks.GlobalVariables;
import vyki.game.tanks.Sprite;
import vyki.game.tanks.objects.Shots.EnemyShot;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * Created by Rumata on 20.06.2015.
 */
public abstract class AbstractTank {
    private Sprite sprite;
    private int X = 0;
    private int Y = 0;
    private int speed = 3;
    private String lastCourse="up";
    private boolean alive = true;

    public void Test(){
      System.out.println("Test");
    }

/*    public void moveLeft(){
        setX(getX() - speed);
        getSprite().x= getX();
        if (!"left".equals(getLastCourse())){
            getSprite().setImage(getImage("tankLeft.png"));
            setLastCourse("left");
        }
    }
    public void moveRight(){
        setX(getX() + speed);
        getSprite().x= getX();
        if (!"right".equals(getLastCourse())){
            getSprite().setImage(getImage("tankRight.png"));
            setLastCourse("right");
        }
    }
    public void moveDown(){
        setY(getY() + speed);
        getSprite().y= getY();
        if (!"down".equals(getLastCourse())){
            getSprite().setImage(getImage("tankDown.png"));
            setLastCourse("down");
        }
    }
    public void moveUp(){
        setY(getY() - speed);
        getSprite().y= getY();
        if (!"up".equals(getLastCourse())){
            getSprite().setImage(getImage("tankUp.png"));
            setLastCourse("up");
        }
    }
    public void shoot(){
        GlobalVariables.enemyShots.add(new EnemyShot(getLastCourse(), getX(), getY()));
    }*/

    public Sprite getSprite() {
        return sprite;
    }
    public Sprite getSprite(String path,int X, int Y) {
        Sprite sprite = new Sprite(getImage(path), X, Y);
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

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    public String getLastCourse() {
        return lastCourse;
    }

    public void setLastCourse(String lastCourse) {
        this.lastCourse = lastCourse;
    }
}

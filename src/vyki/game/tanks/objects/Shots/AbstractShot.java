package vyki.game.tanks.objects.Shots;

import vyki.game.tanks.Sprite;
import vyki.game.tanks.objects.Enums.LastCourse;
import vyki.game.tanks.objects.Environment.AbstractTank;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Created by Rumata on 20.06.2015.
 */
public abstract class AbstractShot {
    private Sprite sprite;
    private int speed = 9;
    private LastCourse course;
    private int X;
    private int Y;

    public boolean Hit(ArrayList<AbstractTank> players){
        boolean strike = false;
        if (inHitbox(getSprite().x - 10, getSprite().y - 12, players)) {strike = true;}
        if (LastCourse.left == getCourse()) {getSprite().x-= getSpeed();}
        else if (LastCourse.right == getCourse()){getSprite().x+= getSpeed();}
        else if (LastCourse.down == getCourse()){getSprite().y+= getSpeed();}
        else if (LastCourse.up == getCourse()){getSprite().y-= getSpeed();}
        /*switch (this.getCourse()){
            case "left":  getSprite().x-= getSpeed(); break;
            case "right": getSprite().x+= getSpeed(); break;
            case "down":  getSprite().y+= getSpeed(); break;
            case "up":    getSprite().y-= getSpeed(); break;
        }*/
        return strike;
    }

    public static boolean inHitbox(int snX, int snY, ArrayList<AbstractTank> players){
        int TankX,TankY, radius;
        boolean result = false;
        ListIterator it = players.listIterator();
        int coord[] = new int[2];
        radius = 15;

        while(it.hasNext()){
            AbstractTank pr = (AbstractTank) it.next();
            coord = pr.getSprite().getCoordinates();
            TankX=coord[0];
            TankY=coord[1];
            result = Math.sqrt(Math.pow((TankX-snX),2) +  Math.pow((TankY-snY),2)) < radius;
            if (result){
                System.out.println("result");
                pr.setAlive(false);
            }
        }
        return result;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public LastCourse getCourse() {
        return course;
    }

    public void setCourse(LastCourse course) {
        this.course = course;
    }

    public Sprite getSpriteShot(String path) {
        Sprite sprite = new Sprite(getImage(path), getX(), getY());
        if (LastCourse.left == getCourse()) {sprite = new Sprite(getImage(path), getX() -11, getY() +11);}
        else if (LastCourse.right == getCourse()){sprite = new Sprite(getImage(path), getX() +28, getY() +11);}
        else if (LastCourse.down == getCourse()){sprite = new Sprite(getImage(path), getX() +11, getY() +28);}
        else if (LastCourse.up == getCourse()){sprite = new Sprite(getImage(path), getX() +11, getY() -11);}

        /*switch (getCourse()){
            case LastCourse.left: sprite = new Sprite(getImage(path), getX() -11, getY() +11);  break;
            case "right": sprite = new Sprite(getImage(path), getX() +28, getY() +11); break;
            case "down": sprite = new Sprite(getImage(path), getX() +11, getY() +28);  break;
            case "up": sprite = new Sprite(getImage(path), getX() +11, getY() -11);    break;
        }*/

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
}

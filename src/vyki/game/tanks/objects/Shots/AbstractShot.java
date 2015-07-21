package vyki.game.tanks.objects.Shots;

import vyki.game.tanks.GlobalVariables;
import vyki.game.tanks.Sprite;
import vyki.game.tanks.objects.Enums.LastCourse;
import vyki.game.tanks.objects.Enums.LifeStatus;
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
    private float stepX;
    private float stepY;

    public  AbstractShot(){
        int x1= GlobalVariables.player1_X;
        int y1= GlobalVariables.player1_Y;
        int x2= GlobalVariables.player1_X + GlobalVariables.cursore_X;
        int y2= GlobalVariables.player1_Y + GlobalVariables.cursore_Y;
        float leagth = (float) Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
        float Lx = x2 - x1;
        float Ly = y2 - y1;
        stepX = (float)  Lx / leagth;
        stepY = (float)  Ly / leagth;
    }

    public boolean Hit(ArrayList<AbstractTank> players){
        boolean strike = false;
        if (inHitbox(getSprite().x - 10, getSprite().y - 12, players)) {strike = true;}


        getSprite().x-= -1*Math.round(getSpeed() * stepX);
        getSprite().y+= -1*Math.round(getSpeed() * stepY);
        setX(getSprite().x);
        setY(getSprite().y);
      /*  for (int i=0; i<in; i++){
            System.out.println("step "+i+": "+"x: "+ Math.round(i*stepX) + " y: " + Math.round(i*stepY));
        }*/

        /*if (LastCourse.left == getCourse()) {getSprite().x-= getSpeed();}
        else if (LastCourse.right == getCourse()){getSprite().x+= getSpeed();}
        else if (LastCourse.down == getCourse()){getSprite().y+= getSpeed();}
        else if (LastCourse.up == getCourse()){getSprite().y-= getSpeed();}*/
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
                System.out.println("target hitted");
                pr.setLifeStatus(LifeStatus.HITTED);
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
        int k = 10;
        Sprite sprite = new Sprite(getImage(path), getX(), getY());
        if (LastCourse.left == getCourse()) {sprite = new Sprite(getImage(path), getX()+k , getY()+k );}
        else if (LastCourse.right == getCourse()){sprite = new Sprite(getImage(path), getX()+k , getY()+k );}
        else if (LastCourse.down == getCourse()){sprite = new Sprite(getImage(path), getX()+k , getY()+k );}
        else if (LastCourse.up == getCourse()){sprite = new Sprite(getImage(path), getX()+k , getY()+k );}
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

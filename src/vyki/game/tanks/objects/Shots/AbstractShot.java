package vyki.game.tanks.objects.Shots;

import vyki.game.tanks.Sprite;
import vyki.game.tanks.objects.Environment.AbstractTank;

import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Created by Rumata on 20.06.2015.
 */
public abstract class AbstractShot {
    private Sprite sprite;
    private int speed = 9;
    private String course;


    public boolean Hit(ArrayList<AbstractTank> players){
        boolean strike = false;
        if (inHitbox(getSprite().x - 10, getSprite().y - 12, players)) {strike = true;}
        switch (this.getCourse()){
            case "left":  getSprite().x-= getSpeed(); break;
            case "right": getSprite().x+= getSpeed(); break;
            case "down":  getSprite().y+= getSpeed(); break;
            case "up":    getSprite().y-= getSpeed(); break;
        }
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

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}

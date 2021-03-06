package vyki.game.tanks.objects.Environment;


import vyki.game.tanks.Sprite;
import vyki.game.tanks.objects.Enums.LastCourse;
import vyki.game.tanks.objects.Enums.LifeStatus;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * Created by Rumata on 20.06.2015.
 */
public abstract class AbstractTank {
    private Sprite sprite;
    private int X = 0;
    private int Y = 0;
    private int speed = 1;
    private LastCourse lastCourse;
    private LifeStatus lifeStatus = LifeStatus.ALIVE;
    private boolean alive = true;

    public void Test(){
      System.out.println("Test");
    }

   public void moveLeft(){
        setX(getX() - getSpeed());
        getSprite().x= getX();
        if (!"left".equals(getLastCourse())){
            getSprite().setImage(getImage("carcass/tankCarcassLeft.png"));
            setLastCourse(LastCourse.left);
        }
    }
    public void moveRight(){
        setX(getX() + getSpeed());
        getSprite().x= getX();
        if (!"right".equals(getLastCourse())){
            getSprite().setImage(getImage("carcass/tankCarcassRight.png"));
            setLastCourse(LastCourse.right);
        }
    }
    public void moveDown(){
        setY(getY() + getSpeed());
        getSprite().y= getY();
        if (!"down".equals(getLastCourse())){
            getSprite().setImage(getImage("carcass/tankCarcassDown.png"));
            setLastCourse(LastCourse.down);
        }
    }

    public void moveUp(){
        setY(getY() - getSpeed());
        getSprite().y= getY();
        if (!"up".equals(getLastCourse())){
            getSprite().setImage(getImage("carcass/tankCarcassUp.png"));
            setLastCourse(LastCourse.up);
        }
    }


    public Sprite getSprite() {
        return sprite;
    }
    public Sprite getSprite(String path,int X, int Y) {
        Sprite sprite = new Sprite(getImage(path), X, Y);
        return sprite;
    }
    public Image getImage(String path) {
        URL url = this.getClass().getClassLoader().getResource(path);
        ImageIcon icon = new ImageIcon(url);
        Image image = icon.getImage();
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

    public LastCourse getLastCourse() {
        return lastCourse;
    }

    public void setLastCourse(LastCourse lastCourse) {
        this.lastCourse = lastCourse;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public LifeStatus getLifeStatus() {
        return lifeStatus;
    }

    public void setLifeStatus(LifeStatus lifeStatus) {
        this.lifeStatus = lifeStatus;
    }
}

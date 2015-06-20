/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vyki.game.tanks.objects.Environment;

import java.util.ArrayList;
import java.util.ListIterator;
import vyki.game.tanks.GlobalVariables;
import vyki.game.tanks.Sprite;
import vyki.game.tanks.objects.Shots.PlayerShot;



public class Tank  extends AbstractTank{

    private Sprite sprite;
    public int HP = 100;
    public int speed = 10;
    public int damage = 20;
    private String lastCourse="up";
    public boolean alive = true;

    public Tank() {
        this.sprite = getSprite("tankUP.png", GlobalVariables.player1_X, GlobalVariables.player1_Y);
    }


    public void moveLeft(){
        GlobalVariables.player1_X -=speed;
        getSprite().x=GlobalVariables.player1_X;
        if (!"left".equals(lastCourse)){
            getSprite().setImage(getImage("tankLeft.png"));
            lastCourse = "left";
        }
    }
    public void moveRight(){
        GlobalVariables.player1_X +=speed;
        getSprite().x=GlobalVariables.player1_X;
        if (!"right".equals(lastCourse)){
            getSprite().setImage(getImage("tankRight.png"));
            lastCourse = "right";
        }
    }
    public void moveDown(){
        GlobalVariables.player1_Y +=speed;
        getSprite().y=GlobalVariables.player1_Y;
        if (!"down".equals(lastCourse)){
            getSprite().setImage(getImage("tankDown.png"));
            lastCourse = "down";
        }

    }
    public void moveUp(){
        GlobalVariables.player1_Y -=speed;
        getSprite().y=GlobalVariables.player1_Y;
        if (!"up".equals(lastCourse)){
            getSprite().setImage(getImage("tankUp.png"));
            lastCourse = "up";
        }

    }
    public void shoot(ArrayList<PlayerShot> shots){
        shots.add(new PlayerShot(lastCourse,GlobalVariables.player1_X,GlobalVariables.player1_Y));
        System.out.println("player1 coord: " + GlobalVariables.player1_X + " " + GlobalVariables.player1_Y);
        System.out.println("Map coord: "+GlobalVariables.mapX +" "+GlobalVariables.mapY);
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

}
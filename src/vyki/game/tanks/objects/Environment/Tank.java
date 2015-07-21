/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vyki.game.tanks.objects.Environment;

import java.util.ArrayList;
import java.util.ListIterator;
import vyki.game.tanks.GlobalVariables;
import vyki.game.tanks.Sprite;
import vyki.game.tanks.objects.Enums.LastCourse;
import vyki.game.tanks.objects.Shots.PlayerShot;



public class Tank  extends AbstractTank{

    private Sprite sprite;
    public int HP = 100;
    public int speed = 5;
    public int damage = 20;
    private LastCourse lastCourse = LastCourse.up;
    public boolean alive = true;

    public Tank() {
        this.sprite = getSprite("tankCarcass.png", GlobalVariables.player1_X, GlobalVariables.player1_Y);
    }


    public void shoot(ArrayList<PlayerShot> shots){
        shots.add(new PlayerShot(getLastCourse(),GlobalVariables.player1_X,GlobalVariables.player1_Y));
    }

    @Override
    public int getX() {
        return GlobalVariables.player1_X;
    }
    @Override
    public int getY() {
        return GlobalVariables.player1_Y;
    }
    @Override
    public void setX(int x) {
        GlobalVariables.player1_X = x;
    }
    @Override
    public void setY(int y) {
        GlobalVariables.player1_Y = y;
    }
    @Override
    public boolean isAlive() {
        return alive;
    }
    @Override
    public void setAlive(boolean alive) {
        this.alive = alive;
    }
    @Override
    public Sprite getSprite() {
        return sprite;
    }
    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
    @Override
    public int getSpeed() {
        return speed;
    }

}

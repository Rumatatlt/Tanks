/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vyki.game.tanks.objects.Shots;

import vyki.game.tanks.Sprite;
import vyki.game.tanks.objects.Drawable;
import vyki.game.tanks.objects.Enums.LastCourse;

public class PlayerShot extends AbstractShot implements Drawable {
    
    private Sprite sprite;
    public int speed = 9;
    public int direction;
    private LastCourse course;
    private int X;
    private int Y;

    public PlayerShot (LastCourse lastCourse, int X, int Y) {
        this.course = lastCourse;
        this.setX(X);
        this.setY(Y);
        this.sprite = getSpriteShot("shotUP.png");
    }

    public LastCourse getCourse() {
        return course;
    }

    public void setCourse(LastCourse course) {
        this.course = course;
    }
    
    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
    public void moveUp(){

    }

    @Override
    public int getX() {
        return X;
    }

    @Override
    public void setX(int x) {
        X = x;
    }

    @Override
    public int getY() {
        return Y;
    }

    @Override
    public void setY(int y) {
        Y = y;
    }
}

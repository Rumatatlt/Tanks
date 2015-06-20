package vyki.game.tanks.objects.Shots;

import vyki.game.tanks.Sprite;
import vyki.game.tanks.objects.Drawable;

public class EnemyShot extends AbstractShot implements Drawable {


    private Sprite sprite;
    public int speed = 9;
    public int direction;
    private String course;
    private int X;
    private int Y;

    public EnemyShot(String lastCourse, int X, int Y) {
        this.course = lastCourse;
        this.setX(X);
        this.setY(Y);
        this.sprite = getSpriteShot("shotUP.png");
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
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

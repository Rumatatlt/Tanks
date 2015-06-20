package vyki.game.tanks.objects.Shots;

import vyki.game.tanks.Sprite;
import vyki.game.tanks.objects.Drawable;
import vyki.game.tanks.objects.Environment.AbstractTank;
import vyki.game.tanks.objects.Environment.Tank;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ListIterator;


public class EnemyShot extends AbstractShot implements Drawable {


    private Sprite sprite;
    public int speed = 9;
    public int direction;
    private String course;
    private int X;
    private int Y;

    public EnemyShot(String lastCourse, int X, int Y) {
        this.course = lastCourse;
        this.X=X;
        this.Y=Y;
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


    public Sprite getSpriteShot(String path) {
        Sprite sprite = new Sprite(getImage(path), X, Y);
        switch (course){
            case "left": sprite = new Sprite(getImage(path), X-11, Y+11);  break;
            case "right": sprite = new Sprite(getImage(path), X+28, Y+11); break;
            case "down": sprite = new Sprite(getImage(path), X+11, Y+28);  break;
            case "up": sprite = new Sprite(getImage(path), X+11, Y-11);    break;
        }
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
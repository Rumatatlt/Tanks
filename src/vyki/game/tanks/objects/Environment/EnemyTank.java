package vyki.game.tanks.objects.Environment;

import vyki.game.tanks.GlobalVariables;
import vyki.game.tanks.Sprite;
import vyki.game.tanks.TankInterface;
import vyki.game.tanks.objects.Tank;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;


public class EnemyTank{
    private Sprite sprite;
    public int HP = 100;
    public int speed = 20;
    public int damage = 20;
    private String lastCourse="up";
    public int X = 0;
    public int Y = 0;

    public EnemyTank() {
        this.sprite = getSprite("tankUP.png");
    }

    public void moveLeft(){
        this.X -=speed;
        //GlobalVariables.mapX=GlobalVariables.mapX+speed;
        getSprite().x=this.X;
        getSprite().setImage(getImage("tankLeft.png"));
        lastCourse = "left";
    }

    public void enemyTankAI(){

    }

    public void enemyTankConstructor(ArrayList<EnemyTank> enemyTanks){

    }

    public Sprite getSprite() {
        return sprite;
    }
    public Sprite getSprite(String path) {
        Sprite sprite = new Sprite(getImage(path), GlobalVariables.respawn_X, GlobalVariables.prespawn_Y);
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

package vyki.game.tanks.objects.Environment;

import vyki.game.tanks.GlobalVariables;
import vyki.game.tanks.Sprite;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


public class EnemyTank{
    private Sprite sprite;
    public int speed = 20;
    private String lastCourse="up";
    public int X = 0;
    public int Y = 0;
    //private static Path path = Paths.get(".\\build\\classes\\tankUP.png");
    //private static Image background = Toolkit.getDefaultToolkit().getImage(String.valueOf(path));

    public EnemyTank() {
        this.sprite = getSprite("tankDown.png");
        this.X = GlobalVariables.homeLocation_X + 400;
        this.Y = GlobalVariables.homeLocation_Y + 400;
    }

    public void moveLeft(){
        this.X -=speed;
        //GlobalVariables.mapX=GlobalVariables.mapX+speed;
        getSprite().x=this.X;
        //getSprite().setImage(getImage("D:\\Game2D\\Tanks\\build\\classes\\tankLeft.png"));
        lastCourse = "left";
    }

    public void enemyTankAI(){

    }

    public static void enemyTankConstructor(ArrayList<EnemyTank> enemyTanks){
        //enemyTanks.add(new EnemyTank());
    }

    public Sprite getSprite() {
        return sprite;
    }
    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
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

package vyki.game.tanks.objects.Environment;

import vyki.game.tanks.GlobalVariables;
import vyki.game.tanks.Sprite;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * Created by Rumata on 20.06.2015.
 */
public abstract class AbstractTank {
    private Sprite sprite;
    private int X = 0;
    private int Y = 0;
    private int speed = 3;
    private String lastCourse="up";

    public void Test(){
      System.out.println("Test");
    }


    public Sprite getSprite(String path,int X, int Y) {
        Sprite sprite = new Sprite(getImage(path), X, Y);
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

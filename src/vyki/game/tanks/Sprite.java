package vyki.game.tanks;

import javax.swing.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.net.URL;

public class Sprite {

    private Image image;
    public int x;
    public int y;
    
    public Sprite(Image image, int x, int y) {
        this.x = x;
        this.y = y;
        this.image = image;
        
    }

    Image blastImage = new ImageIcon(this.getClass().getClassLoader().getResource("blast.gif")).getImage();

    
    public int getWidth() {
        return image.getWidth(null);
    }

    public int getHeight() {
        return image.getHeight(null);
    }

    public void draw(Graphics g) {
        //Graphics2D g2d = (Graphics2D) g; 
        //g2d.rotate(Math.toRadians(90));
        if (image.equals(blastImage)){
            g.drawImage(image, x-150, y-300, null);
            return;
        }
        g.drawImage(image, x-15, y-15, null);
        
    }
    
    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
    
    public void setCoordinates(int x, int y) {
        this.x = x;
        this.y = y;

    }
    
    public int[] getCoordinates() {
        int coord[] = new int[2];
        coord[0]=x;
        coord[1]=y;
        return coord;
    }

    
    
}
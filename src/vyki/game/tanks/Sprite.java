package vyki.game.tanks;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

public class Sprite {

    private Image image;
    public int x;
    public int y;
    
    public Sprite(Image image, int x, int y) {
        this.x = x;
        this.y = y;
        this.image = image;
        
    }
    
    
    public int getWidth() {
        return image.getWidth(null);
    }

    public int getHeight() {
        return image.getHeight(null);
    }

    public void draw(Graphics g) {
        //Graphics2D g2d = (Graphics2D) g; 
        //g2d.rotate(Math.toRadians(90));
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
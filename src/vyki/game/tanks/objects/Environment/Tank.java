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

    public static boolean inHitbox(int snX, int snY, ArrayList<EnemyTank> players){
        int TankX,TankY, radius;
        boolean result = false;
        ListIterator it = players.listIterator();
        int coord[] = new int[2];
        radius = 15;

        while(it.hasNext()){
           EnemyTank pr = (EnemyTank) it.next();
           coord = pr.getSprite().getCoordinates();
           TankX=coord[0];
           TankY=coord[1];
           result = Math.sqrt(Math.pow((TankX-snX),2) +  Math.pow((TankY-snY),2)) < radius;   
           if (result){
               pr.alive = false;
               //pr.enemyTankAI();
           //it.remove();

           }
        }
    return result;
    }


    public Sprite getSprite() {
        return sprite;
    }
    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
/*
    public Sprite getSprite(String path) {
        Sprite sprite = new Sprite(getImage(path), GlobalVariables.player1_X, GlobalVariables.player1_Y);
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
    }*/


}

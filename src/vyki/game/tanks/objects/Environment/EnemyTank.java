package vyki.game.tanks.objects.Environment;

import vyki.game.tanks.GlobalVariables;
import vyki.game.tanks.Sprite;
import vyki.game.tanks.objects.Enums.LastCourse;
import vyki.game.tanks.objects.Shots.EnemyShot;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;



public class EnemyTank extends AbstractTank{
    private static ArrayList<EnemyTank> enemyTanks = GlobalVariables.enemyTanks;
    private Sprite sprite;
    private int speed = 1;
    private LastCourse lastCourse;
    private int numCourse=1;
    private int X = 0;
    private int Y = 0;
    private int respownX;
    private int respownY;
    private static int collSize = 1;
    private static int tankLimit = 2;
    public boolean alive = true;
    public boolean display = true;
    private static int count = 0;

    public EnemyTank() {
        this.respownX = GlobalVariables.homeLocation_X;
        this.respownY = GlobalVariables.homeLocation_Y;
        this.sprite = getSprite("tankDown.png", respownX, respownY);
        this.setX(this.respownX);
        this.setY(this.respownY);
        getSprite().x=this.getX();
        getSprite().y=this.getY();
        collSize++;
        //System.out.println("new EnemyTank, total= " + collSize);
    }


    public static void enemyTankAI(){
        Random ran = new Random();

        if (count%100==0){
            for (EnemyTank tank : enemyTanks){
                tank.numCourse = ran.nextInt(4)+1;
            }
        }
        for (EnemyTank tank : enemyTanks){
            if (!tank.alive){tank.enemyTankDestroyed();} else {
                    //tank.moveRight();
                    //tank.shoot(GlobalVariables.shots);
                if (findTargets()){
                    tracking(tank);
                    //tank.shoot();
                } else {
                        switch (tank.numCourse) {
                            case 1: tank.moveRight();
                                break;
                            case 2:  tank.moveLeft();
                                break;
                            case 3:  tank.moveDown();
                                break;
                            case 4:  tank.moveUp();
                                break;
                        }
                }
            }
        }
        count++;
    }



    public static void enemyTankConstructor(){
        if (!createCheck(enemyTanks, 0, 0)){
            enemyTanks.add(new EnemyTank());
            enemyTanks.add(new EnemyTank());
        }
        if (enemyTanks.size()>tankLimit){
            int distanceX;
            int distanceY;
            ListIterator iterator = enemyTanks.listIterator();
            while (iterator.hasNext()){
                EnemyTank tank = (EnemyTank) iterator.next();
                distanceX = Math.abs(GlobalVariables.player1_X) - Math.abs(tank.getX());
                distanceY = Math.abs(GlobalVariables.player1_Y) - Math.abs(tank.getY());
                if (distanceX>500 || distanceY>500){
                    iterator.remove();
                    //tank.display = false;
                }
            }
        }
    }
    public static void patrul(){

    }

    public void shoot(){
        GlobalVariables.enemyShots.add(new EnemyShot(getLastCourse(), getX(), getY()));
    }

    private static void tracking(EnemyTank tank){


        if (Math.abs(tank.X)-Math.abs(GlobalVariables.player1_X)>Math.abs(tank.Y)-Math.abs(GlobalVariables.player1_Y)){
            if (tank.X!=GlobalVariables.player1_X){
                if (tank.X>GlobalVariables.player1_X){tank.moveLeft();} else {tank.moveRight();}
            } else if (tank.Y!=GlobalVariables.player1_Y){
                if (tank.Y>GlobalVariables.player1_Y){tank.moveUp();} else {tank.moveDown();}
            }
        } else {
            if (tank.Y!=GlobalVariables.player1_Y){
                if (tank.Y>GlobalVariables.player1_Y){tank.moveUp();} else {tank.moveDown();}
            } else if (tank.X!=GlobalVariables.player1_X){
                if (tank.X>GlobalVariables.player1_X){tank.moveLeft();} else {tank.moveRight();}
            }
        }



    }

    public static boolean findTargets(){
        int TankX,TankY, radius;
        boolean result;
        ListIterator it = enemyTanks.listIterator();
        int coord[];
        radius = 300;

        while(it.hasNext()){
            EnemyTank pr = (EnemyTank) it.next();
            coord = pr.getSprite().getCoordinates();
            TankX=coord[0];
            TankY=coord[1];
            result = Math.sqrt(Math.pow((TankX-GlobalVariables.player1_X),2) +  Math.pow((TankY-GlobalVariables.player1_Y),2)) < radius;
            if (result){
                //System.out.println("target finded");
                return true;
            }
        }
        return false;
    }

    public void enemyTankDestroyed(){
        this.X -=0;
        getSprite().x=getX();
        if (!LastCourse.Destroyed.equals(lastCourse)){
            getSprite().setImage(getImage("boom.png"));
            lastCourse = LastCourse.Destroyed;
        }
    }

    private static boolean createCheck(ArrayList<EnemyTank> enemyTanks, int X, int Y){
        boolean val = false;
        for ( EnemyTank tank : enemyTanks){
            if (tank.respownX == (X + GlobalVariables.homeLocation_X) && tank.respownY == (Y + GlobalVariables.homeLocation_Y)){
                val = true;
                break;
            } else {
                val = false;
            }
        }
        return val;
    }
    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public Sprite getSprite() {
        return sprite;
    }
    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
    @Override
    public LastCourse getLastCourse() {
        return lastCourse;
    }
    @Override
    public void setLastCourse(LastCourse lastCourse) {
        this.lastCourse = lastCourse;
    }
    @Override
    public int getSpeed() {
        return speed;
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

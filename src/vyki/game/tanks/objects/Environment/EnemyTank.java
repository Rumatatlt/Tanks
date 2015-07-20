package vyki.game.tanks.objects.Environment;

import vyki.game.tanks.GlobalVariables;
import vyki.game.tanks.Sprite;
import vyki.game.tanks.objects.Enums.LastCourse;
import vyki.game.tanks.objects.Enums.LifeStatus;
import vyki.game.tanks.objects.Shots.EnemyShot;

import java.net.URL;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;



public class EnemyTank extends AbstractTank{
    private static ArrayList<EnemyTank> enemyTanks = GlobalVariables.enemyTanks;
    private Sprite sprite;
    private int speed = 1;
    private LastCourse lastCourse;
    private LifeStatus lifeStatus;
    private int numCourse=1;
    private int X = 0;
    private int Y = 0;
    private int respownX;
    private int respownY;
    private static int collSize = 1;
    private static int tankLimit = 2;

    public boolean display = true;
    private static int count = 0;
    public int deathAnimationTime = 800;
    public BlastContainer blastContainer = new BlastContainer();
    public URL url;

    public EnemyTank() {
        this.respownX = GlobalVariables.homeLocation_X;
        this.respownY = GlobalVariables.homeLocation_Y;
        this.sprite = getSprite("tankDown.png", respownX, respownY);
        this.setX(this.respownX);
        this.setY(this.respownY);
        getSprite().x=this.getX();
        getSprite().y=this.getY();
        collSize++;
    }


    public static void enemyTankAI(){
        Random ran = new Random();

        for (EnemyTank tank : enemyTanks){
            if (tank.getLifeStatus().equals(LifeStatus.HITTED) || tank.getLifeStatus().equals(LifeStatus.Destroyed)){
                tank.enemyTankDestroyed();}
            else {
                if (findTargets()){
                    tracking(tank);
                    //tank.shoot();
                } else {
                    if (count%100==0) {
                        tank.numCourse = ran.nextInt(4) + 1;
                    }
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
            //enemyTanks.add(new EnemyTank());
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
                }
            }
        }
    }



    public void shoot(){
        int shootTact = 500;
        if (GlobalVariables.lifeCycleTacts % shootTact==0){
            GlobalVariables.enemyShots.add(new EnemyShot(getLastCourse(), getX(), getY()));
        }
    }

    private static void tracking(EnemyTank tank){
        int distanceByX= Math.abs(tank.X-GlobalVariables.player1_X);
        int distanceByY= Math.abs(tank.Y-GlobalVariables.player1_Y);

        if ((tank.X-GlobalVariables.player1_X) > (tank.Y-GlobalVariables.player1_Y) || Math.abs(tank.X)-Math.abs(GlobalVariables.player1_X)>Math.abs(tank.Y)-Math.abs(GlobalVariables.player1_Y)){
            if (tank.X!=GlobalVariables.player1_X){
                if (tank.X>GlobalVariables.player1_X && tank.X!=GlobalVariables.player1_X){tank.moveLeft();} else {tank.moveRight();}
            } else/* if (tank.Y!=GlobalVariables.player1_Y)*/{
                //tank.shoot();
                if (tank.Y>GlobalVariables.player1_Y && tank.Y!=GlobalVariables.player1_Y){tank.moveUp();} else {tank.moveDown();}
            }
        } else if (distanceByX==distanceByY){
            if (tank.lastCourse==LastCourse.up){tank.moveUp();}
            if (tank.lastCourse==LastCourse.down){tank.moveDown();}
            if (tank.lastCourse==LastCourse.right){tank.moveRight();}
            if (tank.lastCourse==LastCourse.left){tank.moveLeft();}
            }
        else {
            if (tank.Y!=GlobalVariables.player1_Y){
                if (tank.Y>GlobalVariables.player1_Y && tank.Y!=GlobalVariables.player1_Y){tank.moveUp();} else {tank.moveDown();}
            } else /*if (tank.X!=GlobalVariables.player1_X)*/{
                //tank.shoot();
                if (tank.X>GlobalVariables.player1_X && tank.X!=GlobalVariables.player1_X){tank.moveLeft();} else {tank.moveRight();}
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
        if (LastCourse.up.equals(lastCourse)){getSprite().setImage(getImage("tankDestroyed/destroyedTankUp.png"));}
        if (LastCourse.down.equals(lastCourse)){getSprite().setImage(getImage("tankDestroyed/destroyedTankDown.png"));}
        if (LastCourse.left.equals(lastCourse)){getSprite().setImage(getImage("tankDestroyed/destroyedTankLeft.png"));}
        if (LastCourse.right.equals(lastCourse)){getSprite().setImage(getImage("tankDestroyed/destroyedTankRight.png"));}

        if (this.deathAnimationTime==800) {
            Tank Player_1 = GlobalVariables.tanks.get(0);
            if (LastCourse.up.equals(Player_1.getLastCourse())) {
                blastContainer.setBlastUrl(this.getClass().getClassLoader().getResource("blastUp.gif"));
                blastContainer.setCorX(-160);
                blastContainer.setCorY(-330);
            } else if (LastCourse.down.equals(Player_1.getLastCourse())) {
                blastContainer.setBlastUrl(this.getClass().getClassLoader().getResource("blastDown.gif"));
                blastContainer.setCorX(-150);
                blastContainer.setCorY(0);
            } else if (LastCourse.right.equals(Player_1.getLastCourse())) {
                blastContainer.setBlastUrl(this.getClass().getClassLoader().getResource("blastRight.gif"));
                blastContainer.setCorX(0);
                blastContainer.setCorY(-150);
            } else if (LastCourse.left.equals(Player_1.getLastCourse())) {
                blastContainer.setBlastUrl(this.getClass().getClassLoader().getResource("blastLeft.gif"));
                blastContainer.setCorX(-350);
                blastContainer.setCorY(-150);
            }
            this.setLifeStatus(LifeStatus.Destroyed);
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

    public  int getDeathAnimationTime() {
        return this.deathAnimationTime;
    }

    public void setDeathAnimationTime(int deathAnimationTime) {
        this.deathAnimationTime = deathAnimationTime;
    }
}

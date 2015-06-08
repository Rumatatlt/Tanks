package vyki.game.tanks.objects;


import vyki.game.tanks.GlobalVariables;
import vyki.game.tanks.objects.Shots.PlayerShot;

import java.awt.*;
import java.util.*;

/**
 * Created by selo0215 on 15.05.2015.
 */
public class MapShard{
    private String course;
    private Image background;
    private int pix = 50;
    private int X = 0;
    private int Y = 0;
    public int trueX;
    public int trueY;
    private static boolean shardsCreated_1 = false;
    private static boolean shardsCreated_2 = false;
    private static boolean shardsCreated_3 = false;
    private static boolean shardsCreated_4 = false;
    private static int playerLastZone;

    public MapShard(int X, int Y, Image background){
        this.background = background;
        this.X= this.X+X;
        this.Y= this.Y+Y;
        trueX = GlobalVariables.mapX + X;
        trueY = GlobalVariables.mapY + Y;
       // System.out.println("trueX= "+trueX+" trueY= "+trueY);

    }

    public void draw(Graphics g) {
        //Graphics2D g2d = (Graphics2D) g;
        //g2d.rotate(Math.toRadians(90));
        g.setColor(Color.red);
        //g.fillRect( X-GlobalVariables.player1_X-100,  Y-GlobalVariables.player1_Y-100, 1000, 1000);
        g.drawImage(background, X-GlobalVariables.player1_X-100,Y-GlobalVariables.player1_Y-100, null);
    }

    public static void mapConstructor(ArrayList<MapShard> shards,Image background){
        int max = 0;
        int min = 0;
        int mcX=GlobalVariables.player1_X;
        int mcY=GlobalVariables.player1_Y;
        int divX=GlobalVariables.player1_X/100;
        int divY=GlobalVariables.player1_Y/100;
        //shards.add(new MapShard(0, -1000 , background));
        //int mcY=GlobalVariables.player1_Y % GlobalVariables.mapSize;
        //Нижний правый
        if (mcX>max && mcY>max) {
            //System.out.println("1");
            if (!shardsCreated_1){
                if (!findShard(shards,1000 * divX, 0)) { shards.add(new MapShard(1000 * divX, 0, background));}
                if (!findShard(shards,1000 * divX, 1000 * divY)) { shards.add(new MapShard(1000 * divX, 1000 * divY, background));}
                if (!findShard(shards,0 , 1000 * divY)) { shards.add(new MapShard(0, 1000 * divY, background));}
                //shardsCreated_1 = true;
                playerLastZone = 1; // Zone
            }
        }

        //Верхний правый
        if (mcX>max && mcY<max) {
            if (!shardsCreated_2){
                if (!findShard(shards,0 , -1000 * divY)) { shards.add(new MapShard(0, -1000 * divY, background));}
                if (!findShard(shards,1000*divX , -1000 * -divY)) { shards.add(new MapShard(1000*divX, -1000 * -divY, background));}
                if (!findShard(shards,1000*divX , 0)) { shards.add(new MapShard(1000*divX, 0, background));}
            }
                //shardsCreated_2 = true;
                playerLastZone = 2; // Zone 2
        }

        //Верхний левый
        if (mcX<max && mcY<max) {
            //System.out.println("3");
            if (!shardsCreated_3){
                shards.add(new MapShard(-1000, -1000, background));
                shards.add(new MapShard(0, -1000, background));
                shards.add(new MapShard(-1000, 0, background));
                //shardsCreated_3 = true;
                playerLastZone = 3; // Zone 3
            }
        }
        //Нижний левый
        if (mcX<max && mcY>max) {
            //System.out.println("4");
            if (!shardsCreated_4){
                shards.add(new MapShard(0, 1000, background));
                shards.add(new MapShard(-1000, 1000, background));
                shards.add(new MapShard(-1000, 0, background));
                //shardsCreated_4 = true;
                playerLastZone = 4; // Zone 4
            }
        }
        /*
        switch (playerLastZone){
            case 1:{
                if (shardsCreated_2){
                    shards.remove();
                    shards.remove("2");
                    shardsCreated_2=false;
                }
                if (shardsCreated_3){
                    shards.remove("0");
                    shards.remove("1");
                    shards.remove("7");
                    shardsCreated_3=false;
                }
                if (shardsCreated_4){
                    shards.remove("6");
                    shards.remove("7");
                    shardsCreated_4=false;
                }
                break;
            }
            case 2:{
                if (shardsCreated_1){
                    shards.remove("4");
                    shards.remove("5");
                    shardsCreated_1=false;
                }
                if (shardsCreated_3){
                    shards.remove("0");
                    shards.remove("7");
                    shardsCreated_3=false;
                }
                if (shardsCreated_4){
                    shards.remove("5");
                    shards.remove("6");
                    shards.remove("7");
                    shardsCreated_4=false;
                }
                break;
            }
            case 3:{
                if (shardsCreated_1){
                    shards.remove("3");
                    shards.remove("4");
                    shards.remove("5");
                    shardsCreated_1=false;
                }
                if (shardsCreated_2){
                    shards.remove("2");
                    shards.remove("3");
                    shardsCreated_2=false;
                }
                if (shardsCreated_4){
                    shards.remove("5");
                    shards.remove("6");
                    shardsCreated_4=false;
                }
                break;
            }
            case 4:{
                if (shardsCreated_1){
                    shards.remove("3");
                    shards.remove("4");
                    shardsCreated_1=false;
                }
                if (shardsCreated_2){
                    shards.remove("1");
                    shards.remove("2");
                    shards.remove("3");
                    shardsCreated_2=false;
                }
                if (shardsCreated_3){
                    shards.remove("0");
                    shards.remove("1");
                    shardsCreated_3=false;
                }
                break;
            }
        }
        */
    }

    public static boolean findShard(ArrayList<MapShard> shards, int X, int Y){
        boolean val = false;
        for ( MapShard shard : shards){
            //System.out.println("shard.trueX= "+shard.trueX + " X= "+ X);
            //System.out.println("shard.trueY= "+shard.trueY+ " Y= "+ Y);
            if (shard.X == X && shard.Y == Y){
                val = true;
                break;
            } else {
                //System.out.println("shard not found");
                val = false;
            }

        }
        return val;
    }
}

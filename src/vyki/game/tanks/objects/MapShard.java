package vyki.game.tanks.objects;


import vyki.game.tanks.GlobalVariables;
import vyki.game.tanks.objects.Shots.PlayerShot;

import java.awt.*;
import java.util.*;


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
        this.X= this.X+X + GlobalVariables.homeLocation_X;
        this.Y= this.Y+Y + GlobalVariables.homeLocation_Y;
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
        int charX;
        int charY;
        int mcX=GlobalVariables.player1_X;
        int mcY=GlobalVariables.player1_Y;
        //int divX=GlobalVariables.player1_X/100;
        //int divY=GlobalVariables.player1_Y/100;
        if (GlobalVariables.player1_X>=0){charX = 1;} else {charX = -1;}
        if (GlobalVariables.player1_Y>=0){charY = 1;} else {charY = -1;}
        GlobalVariables.homeLocation_X = ((GlobalVariables.player1_X+500*charX)/1000)*1000;
        GlobalVariables.homeLocation_Y = ((GlobalVariables.player1_Y+500*charY)/1000)*1000;

        //shards.add(new MapShard(0, -1000 , background));
        //int mcY=GlobalVariables.player1_Y % GlobalVariables.mapSize;
        //Нижний правый
        //if (mcX>=max && mcY>=max) {
            //System.out.println("1");
            if (!shardsCreated_1){
                //if (!findShard(shards,1000 * divX, 0)) { shards.add(new MapShard(1000 * divX, 0, background));}
                 shards.add(new MapShard(1000, 0, background));
                //if (!findShard(shards,1000 * divX, 1000 * divY)) { shards.add(new MapShard(1000 * divX, 1000 * divY, background));}
                 shards.add(new MapShard(1000, 1000, background));
                //if (!findShard(shards,0 , 1000 * divY)) { shards.add(new MapShard(0, 1000 * divY, background));}
                 shards.add(new MapShard(0, 1000, background));
                //shardsCreated_1 = true;
                //playerLastZone = 1; // Zone
            }
       // }

        //Верхний правый
       // if (mcX>=max && mcY<=max) {
            if (!shardsCreated_2){
               shards.add(new MapShard(0, -1000, background));
                shards.add(new MapShard(1000, -1000, background));
                shards.add(new MapShard(1000, 0, background));
            }

        //}

        //Верхний левый
//        if (mcX<=max && mcY<=max) {
            //System.out.println("3");
            if (!shardsCreated_3){
                shards.add(new MapShard(-1000, -1000, background));
                shards.add(new MapShard(0, -1000, background));
                shards.add(new MapShard(-1000, 0, background));

            }
  //      }
        //Нижний левый
    //    if (mcX<=max && mcY>=max) {
            //System.out.println("4");
            if (!shardsCreated_4){
                shards.add(new MapShard(0, 1000, background));
                shards.add(new MapShard(-1000, 1000, background));
                shards.add(new MapShard(-1000, 0, background));

            }
      //  }
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

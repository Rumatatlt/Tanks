package vyki.game.tanks.objects;


import vyki.game.tanks.GlobalVariables;
import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class MapShard{
    private int X = 0;
    private int Y = 0;
    private static int collSize = 1;
    private static Path path = Paths.get(".\\src\\vyki\\game\\image\\Background0.png");
    private static Image background = Toolkit.getDefaultToolkit().getImage(String.valueOf(path));
    private static ArrayList <MapShard> shards = GlobalVariables.shards;

    public MapShard(int X, int Y){
        this.X= this.X+X + GlobalVariables.homeLocation_X;
        this.Y= this.Y+Y + GlobalVariables.homeLocation_Y;
        collSize++;
        System.out.println("new shard, total= " + collSize);
    }

    public void draw(Graphics g) {
        g.setColor(Color.red);
        g.drawImage(background, X-GlobalVariables.player1_X-100,Y-GlobalVariables.player1_Y-100, null);
    }


    public static void mapConstructor(){
        int charX;
        int charY;
        if (GlobalVariables.player1_X>=0){charX = 1;} else {charX = -1;}
        if (GlobalVariables.player1_Y>=0){charY = 1;} else {charY = -1;}
        GlobalVariables.homeLocation_X = ((GlobalVariables.player1_X+500*charX)/1000)*1000;
        GlobalVariables.homeLocation_Y = ((GlobalVariables.player1_Y+500*charY)/1000)*1000;

        if (!findShard(shards,1000, 0)) {shards.add(new MapShard(1000,0));}
        if (!findShard(shards,1000, 1000)) {shards.add(new MapShard(1000,1000));}
        if (!findShard(shards,0 , 1000)) { shards.add(new MapShard(0,1000));}
        if (!findShard(shards,0,-1000)) {shards.add(new MapShard(0,-1000));}
        if (!findShard(shards,1000,-1000)) {shards.add(new MapShard(1000,-1000));}
        if (!findShard(shards,-1000,-1000)) {shards.add(new MapShard(-1000, -1000));}
        if (!findShard(shards,-1000,0)) {shards.add(new MapShard(-1000, 0));}
        if (!findShard(shards,-1000,1000)) {shards.add(new MapShard(-1000, 1000));}
}


    private static boolean findShard(ArrayList<MapShard> shards, int X, int Y){
        boolean val = false;
        for ( MapShard shard : shards){
            if (shard.X == (X + GlobalVariables.homeLocation_X) && shard.Y == (Y + GlobalVariables.homeLocation_Y)){
                val = true;
                break;
            } else {
                val = false;
            }
        }
        return val;
    }
}

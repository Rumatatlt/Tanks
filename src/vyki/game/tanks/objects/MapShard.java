package vyki.game.tanks.objects;


import vyki.game.tanks.GlobalVariables;
import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class MapShard{
    private int X = 0;
    private int Y = 0;
    private static int shardsLimit = 8;
    private int id;
    private static Path path = Paths.get(".\\src\\vyki\\game\\image\\Background0.png");
    private static Image background = Toolkit.getDefaultToolkit().getImage(String.valueOf(path));
    private static ArrayList <MapShard> shards = GlobalVariables.shards;

    public MapShard(int X, int Y){

        this.setX(this.getX() +X + GlobalVariables.homeLocation_X);
        this.setY(this.getY() +Y + GlobalVariables.homeLocation_Y);
        this.id = shards.size() + 1;
        System.out.println("new shard, total= " + shards.size());
        System.out.println("shardId= " + id);
/*        if (id>10){
            ListIterator itSH = shards.listIterator();
            while (itSH.hasNext()){
                MapShard shard = (MapShard) itSH.next();
                if (shard.id < 10) {
                    System.out.println("Shard deleted");
                    itSH.remove();
                }
            }
        }*/
    }

    public void draw(Graphics g) {
        g.setColor(Color.red);
        g.drawImage(background, getX() -GlobalVariables.player1_X-100, getY() -GlobalVariables.player1_Y-100, null);
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
        if (shards.size()>shardsLimit){
            int distanceX;
            int distanceY;
            ListIterator iterator = shards.listIterator();
            while (iterator.hasNext()){
                MapShard shard = (MapShard) iterator.next();
                distanceX = Math.abs(GlobalVariables.player1_X) - Math.abs(shard.getX());
                distanceY = Math.abs(GlobalVariables.player1_Y) - Math.abs(shard.getY());
                if (distanceX>1000 || distanceY>1000){
                    iterator.remove();
                }
            }
        }

}

    private static boolean findShard(ArrayList<MapShard> shards, int X, int Y){
        boolean val = false;
        for ( MapShard shard : shards){
            if (shard.getX() == (X + GlobalVariables.homeLocation_X) && shard.getY() == (Y + GlobalVariables.homeLocation_Y)){
                val = true;
                break;
            } else {
                val = false;
            }
        }
        return val;
    }

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }
}

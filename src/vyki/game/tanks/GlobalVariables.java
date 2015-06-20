/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vyki.game.tanks;

import vyki.game.tanks.objects.Environment.AbstractTank;
import vyki.game.tanks.objects.Environment.EnemyTank;
import vyki.game.tanks.objects.MapShard;
import vyki.game.tanks.objects.Shots.EnemyShot;
import vyki.game.tanks.objects.Environment.Tank;
import vyki.game.tanks.objects.Shots.PlayerShot;

import java.util.ArrayList;

/**
 *
 * @author vyki0513
 */
public class GlobalVariables {
    
    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;
    public static final int mapSize = 1000;


    //Player 1
    public static boolean leftPressed = false;
    public static boolean rightPressed = false;
    public static boolean downPressed = false;
    public static boolean upPressed = false;
    public static boolean spacePressed = false;
    public static boolean spaceReleased = false;
    //Player 2
    public static boolean APressed = false;
    public static boolean DPressed = false;
    public static boolean SPressed = false;
    public static boolean WPressed = false;
    public static boolean OPressed = false;
    public static boolean OReleased = false;

    public static Tank hero;
    
    public static PlayerShot shot;
    public static int player1_X = 0;
    public static int player1_Y = 0;
    public static int respawn_X = 0;
    public static int respawn_Y = 0;
    public static int divX;
    public static int divY;
    public static int homeLocation_X = 0;
    public static int homeLocation_Y = 0;
    public static int mapX = -100;
    public static int mapY = -100;
    public static ArrayList<Tank> tanks = new ArrayList<>();
    public static ArrayList<PlayerShot> playerShots = new ArrayList<>();
    public static ArrayList<EnemyShot> enemyShots = new ArrayList<>();
    public static ArrayList<MapShard> shards = new ArrayList<>();
    public static ArrayList<EnemyTank> enemyTanks = new ArrayList<>();



}

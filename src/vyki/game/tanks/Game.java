package vyki.game.tanks;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

import vyki.game.tanks.objects.MapShard;
import vyki.game.tanks.objects.Tank;
import vyki.game.tanks.objects.Shots.PlayerShot;


public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	private boolean running= true;
	public static String NAME = "TANKS";
        private ArrayList<Tank> tanks;
        private ArrayList<PlayerShot> shots;
        private ArrayList<MapShard> shards;
        //private Map<String, MapShard> shards;
        //public static int mapX=0;
        //public static int mapY=0;
        public Tank Player_1;

        public static void main(String[] args) {
		Game game = new Game();
		//game.setPreferredSize(new Dimension(GlobalVariables.WIDTH, GlobalVariables.HEIGHT));
		JFrame frame = new JFrame(Game.NAME);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
        //frame.setContentPane(new JLabel(new ImageIcon("D:\\other\\Game2D\\build\\classes\\Background.png")));
        frame.setPreferredSize(new Dimension(GlobalVariables.WIDTH, GlobalVariables.HEIGHT));
		frame.add(game, BorderLayout.CENTER);
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
		game.start();
	}


    public void start() {
        running = true;
        new Thread(this).start();
    }
    public void run() {
        init();

        while (running) {
            try {
                synchronized (this) {
                    wait(20);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
            update();
            render();
        }
    }

	public void init() {
		addKeyListener(new KeyInputHandler(this));
                shots = new ArrayList<>();
                tanks = new ArrayList<>();
                shards = new ArrayList<>();
                    tanks.add(new Tank());
                    //tanks.add(new Tank());
                    Player_1 = tanks.get(0);

	}

    public void render() {
       BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(2);
            requestFocus();
            return;
        }
        Graphics g = bs.getDrawGraphics();
        Path path = Paths.get(".\\src\\vyki\\game\\image\\Background1.png");
        Image background = Toolkit.getDefaultToolkit().getImage(String.valueOf(path));

        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());


        //map
        g.drawImage(background, GlobalVariables.mapX-GlobalVariables.player1_X, GlobalVariables.mapY-GlobalVariables.player1_Y, null);
        MapShard.mapConstructor(shards, background);
         for (MapShard shard : shards){
            shard.draw(g);
        }

        g.setFont(new Font("default", Font.BOLD, 16));
        g.setColor(Color.gray);
        g.drawString("player_X="+GlobalVariables.player1_X+" player_Y= "+ GlobalVariables.player1_Y,50,720);
        g.drawString("homeLocation_X= "+GlobalVariables.homeLocation_X+" homeLocation_Y= "+ GlobalVariables.homeLocation_Y,50,700);

        g.translate(-GlobalVariables.player1_X+GlobalVariables.WIDTH/2,-GlobalVariables.player1_Y+GlobalVariables.HEIGHT/2);

        //shots
        ListIterator itSH = shots.listIterator();
        while (itSH.hasNext()){
            PlayerShot shot = (PlayerShot) itSH.next();
            shot.getSprite().draw(g);
            if (shot.Hit((ArrayList) tanks)) {
                System.out.println("Попал!");
                itSH.remove();
            }
        }
        //Tanks
        for (Tank tank : tanks) {
            tank.getSprite().draw(g);
        }
        //System.out.println("size shards= "+shards.size());
        g.dispose();
        bs.show();
    }

	public void update() {
		if (GlobalVariables.APressed) {
                Player_1.moveLeft();
		} else if (GlobalVariables.DPressed) {
                Player_1.moveRight();
		} else if (GlobalVariables.SPressed) {
                Player_1.moveDown();
        } else if (GlobalVariables.WPressed) {
                Player_1.moveUp();
        }
        if (GlobalVariables.spaceReleased) {
                GlobalVariables.spaceReleased = false;
                Player_1.shoot((ArrayList) shots);

            //System.out.println("MapCoord: "+GlobalVariables.mapX+" "+GlobalVariables.mapY);
        }
       /* //Player 2
        if (GlobalVariables.APressed == true) {
            tanks.get(1).getSprite().x-=Player_1.speed;
            tanks.get(1).getSprite().setImage(game.getImage("tankLeft.png"));
            lastCourse = "left";
        } else if (GlobalVariables.DPressed == true) {
            tanks.get(1).getSprite().x+=Player_1.speed;
            tanks.get(1).getSprite().setImage(game.getImage("tankRight.png"));
            lastCourse = "right";
        } else if (GlobalVariables.SPressed == true) {
            tanks.get(1).getSprite().y+=Player_1.speed;
            tanks.get(1).getSprite().setImage(game.getImage("tankDown.png"));
            lastCourse = "down";
        } else if (GlobalVariables.WPressed == true) {
            tanks.get(1).getSprite().y-=Player_1.speed;
            tanks.get(1).getSprite().setImage(game.getImage("tankUp.png"));
            lastCourse = "up";
        }
        if (GlobalVariables.OReleased == true) {
            GlobalVariables.OReleased = false;
            shots.add(new PlayerShot(getSpriteShot("shotUP.png"), lastCourse));
        }
        */
	}

}

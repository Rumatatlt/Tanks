package vyki.game.tanks;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import vyki.game.tanks.objects.Enums.LifeStatus;
import vyki.game.tanks.objects.Environment.EnemyTank;
import vyki.game.tanks.objects.MapShard;
import vyki.game.tanks.objects.Shots.EnemyShot;
import vyki.game.tanks.objects.Environment.Tank;
import vyki.game.tanks.objects.Shots.PlayerShot;



public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	private boolean running= true;
	public static String NAME = "TANKS";
        private ArrayList<Tank> tanks = GlobalVariables.tanks;
        private ArrayList<PlayerShot> shots = GlobalVariables.playerShots;
        private ArrayList<EnemyShot> enemyShots = GlobalVariables.enemyShots;
        private ArrayList <MapShard> shards = GlobalVariables.shards;
        private ArrayList<EnemyTank> enemyTanks = GlobalVariables.enemyTanks;
        public Tank Player_1;

        public static void main(String[] args) {
		Game game = new Game();
		JFrame frame = new JFrame(Game.NAME);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
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
                    GlobalVariables.lifeCycleTacts += 10;
                    wait(10);
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
        tanks.add(new Tank());
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
        Path path = Paths.get(".\\src\\vyki\\game\\image\\Background0.png");
        Image background = Toolkit.getDefaultToolkit().getImage(String.valueOf(path));

        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());


        //map
        g.drawImage(background, GlobalVariables.mapX-GlobalVariables.player1_X, GlobalVariables.mapY-GlobalVariables.player1_Y, null);
        MapShard.mapConstructor();
        EnemyTank.enemyTankConstructor();
        EnemyTank.enemyTankAI();

         for (MapShard shard : shards){
           /* if (shard.getX()==GlobalVariables.homeLocation_X && shard.getY()==GlobalVariables.homeLocation_Y){
                shard.draw(g);
            }*/
            shard.draw(g);
        }

        g.setFont(new Font("default", Font.BOLD, 16));
        g.setColor(Color.gray);
        g.drawString("player_X=" + GlobalVariables.player1_X + " player_Y= " + GlobalVariables.player1_Y, 50, 720);
        g.drawString("homeLocation_X= "+GlobalVariables.homeLocation_X+" homeLocation_Y= "+ GlobalVariables.homeLocation_Y,50,700);
        g.drawString("Map shards= "+shards.size(),600,700);
        g.drawString("Enemy units= "+enemyTanks.size(),600,720);
        g.translate(-GlobalVariables.player1_X + GlobalVariables.WIDTH / 2, -GlobalVariables.player1_Y + GlobalVariables.HEIGHT / 2);

        //playerShots
        ListIterator itSH = shots.listIterator();
        while (itSH.hasNext()){
            PlayerShot shot = (PlayerShot) itSH.next();
            shot.getSprite().draw(g);
            if (shot.Hit((ArrayList) enemyTanks)) {
                //System.out.println("Попал!");
                itSH.remove();
            }
        }
        //enemyShots
        ListIterator iteSH = enemyShots.listIterator();
        while (iteSH.hasNext()){
            EnemyShot enemyShot = (EnemyShot) iteSH.next();
            enemyShot.getSprite().draw(g);
            if (enemyShot.Hit((ArrayList) tanks)) {
                //System.out.println("Попал!");
                iteSH.remove();
            }
        }
        //Tanks
        for (Tank tank : tanks) {
            tank.getSprite().draw(g);
        }

        ListIterator enTank = enemyTanks.listIterator();
        while (enTank.hasNext()){
            EnemyTank enemyTank = (EnemyTank) enTank.next();
            if (LifeStatus.Destroyed.equals(enemyTank.getLifeStatus()) && enemyTank.deathAnimationTime>0) {
                ImageIcon icon = new ImageIcon(enemyTank.blastContainer.getBlastUrl());
                Image image = icon.getImage();
                g.drawImage(image, enemyTank.getX() + enemyTank.blastContainer.getCorX(), enemyTank.getY() + enemyTank.blastContainer.getCorY(), null);
                enemyTank.deathAnimationTime = enemyTank.deathAnimationTime - 11;
                if (enemyTank.deathAnimationTime<=0){
                    icon.getImage().flush();
                }
            }
            enemyTank.getSprite().draw(g);
        }
        /*for (EnemyTank enemyTank : enemyTanks) {
            enemyTank.getSprite().draw(g);
        }*/
        //System.out.println("total shards= "+shards.size());
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
        }
	}

}

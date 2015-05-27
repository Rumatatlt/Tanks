/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vyki.game.tanks;

import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vyki0513
 */
class KeyInputHandler extends KeyAdapter {

    private static Game game;

    KeyInputHandler(Game game) {
        this.game = game;
    }

    public void keyPressed(KeyEvent e) {
        //player 1
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            setFalseArrows();
            GlobalVariables.leftPressed = true;
            //GlobalVariables.hero.getSprite().setImage(game.getImage("tankLeft.png"));

        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            setFalseArrows();
            GlobalVariables.rightPressed = true;
           // GlobalVariables.hero.getSprite().setImage(game.getImage("tankRight.png"));
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            setFalseArrows();
            GlobalVariables.downPressed = true;
           // GlobalVariables.hero.getSprite().setImage(game.getImage("tankDown.png"));
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            setFalseArrows();
            GlobalVariables.upPressed = true;
          //  GlobalVariables.hero.getSprite().setImage(game.getImage("tankUp.png"));
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            GlobalVariables.spacePressed = true;
        }

        //player 2
        if (e.getKeyCode() == KeyEvent.VK_A) {
            setFalseArrows();
            GlobalVariables.APressed = true;
            //GlobalVariables.hero.getSprite().setImage(game.getImage("tankLeft.png"));

        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            setFalseArrows();
            GlobalVariables.DPressed = true;
            // GlobalVariables.hero.getSprite().setImage(game.getImage("tankRight.png"));
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            setFalseArrows();
            GlobalVariables.SPressed = true;
            // GlobalVariables.hero.getSprite().setImage(game.getImage("tankDown.png"));
        } else if (e.getKeyCode() == KeyEvent.VK_W) {
            setFalseArrows();
            GlobalVariables.WPressed = true;
            //  GlobalVariables.hero.getSprite().setImage(game.getImage("tankUp.png"));
        }
        if (e.getKeyCode() == KeyEvent.VK_O) {
            GlobalVariables.OPressed = true;
        }

    }
    
    

    public void keyReleased(KeyEvent e) {
        //Player 1
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            GlobalVariables.leftPressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            GlobalVariables.rightPressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            GlobalVariables.downPressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            GlobalVariables.upPressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            
            GlobalVariables.spacePressed = false;
            GlobalVariables.spaceReleased = true;
        }

        //player 2
        if (e.getKeyCode() == KeyEvent.VK_A) {
            setFalseArrows();
            GlobalVariables.APressed = false;
            //GlobalVariables.hero.getSprite().setImage(game.getImage("tankLeft.png"));

        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            setFalseArrows();
            GlobalVariables.DPressed = false;
            // GlobalVariables.hero.getSprite().setImage(game.getImage("tankRight.png"));
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            setFalseArrows();
            GlobalVariables.SPressed = false;
            // GlobalVariables.hero.getSprite().setImage(game.getImage("tankDown.png"));
        } else if (e.getKeyCode() == KeyEvent.VK_W) {
            setFalseArrows();
            GlobalVariables.WPressed = false;
            //  GlobalVariables.hero.getSprite().setImage(game.getImage("tankUp.png"));
        }
        if (e.getKeyCode() == KeyEvent.VK_O) {
            GlobalVariables.OPressed = false;
            GlobalVariables.OReleased = true;
        }
    }
    
    private void setFalseArrows() {
        GlobalVariables.leftPressed = false;
        GlobalVariables.rightPressed = false;
        GlobalVariables.downPressed = false;
        GlobalVariables.upPressed = false;
    }
}